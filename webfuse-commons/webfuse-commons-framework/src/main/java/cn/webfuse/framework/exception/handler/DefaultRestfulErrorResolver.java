package cn.webfuse.framework.exception.handler;

import cn.webfuse.framework.core.exception.AbstractBizException;
import cn.webfuse.framework.core.kit.mapper.JsonMapper;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的RestfulError解析器。
 */
public class DefaultRestfulErrorResolver implements RestfulErrorResolver, MessageSourceAware, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRestfulErrorResolver.class);

    public static final String DEFAULT_MESSAGE_VALUE = "";
    public static final String DEFAULT_EXCEPTION_MESSAGE_VALUE = "";

    private Map<String, RestfulError> exceptionMappings = Collections.emptyMap();   //异常对应
    private Map<String, String> exceptionMappingDefinitions = Collections.emptyMap();   //异常与解析的对应

    private String defaultPrefixCode;   //默认的前缀
    //以下两个为国际化做准备
    private MessageSource messageSource;
    private LocaleResolver localeResolver;

    public DefaultRestfulErrorResolver() {
        this.defaultPrefixCode = "";
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @Override
    public RestfulError resolveError(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        RestfulError template = buildRuntimeError(ex);  //获得运行时期的异常

        RestfulError.Builder builder = new RestfulError.Builder();
        builder.setThrowable(ex);
        builder.setStatus(getStatus(template));
        builder.setCode(getCode(template));
        builder.setMessage(getMessage(template, request));
        builder.setDeveloperMessage(getDeveloperMessage(template, request));

        return builder.build();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, String> definitions = createDefaultExceptionMappingDefinitions();
        if (this.exceptionMappingDefinitions != null && !this.exceptionMappingDefinitions.isEmpty()) {
            definitions.putAll(this.exceptionMappingDefinitions);
        }
        this.exceptionMappings = definitionRestErrors(definitions);
    }


    /**
     * 默认的异常和处理代码匹配
     *
     * @return
     */
    private Map<String, String> createDefaultExceptionMappingDefinitions() {

        Map<String, String> map = new LinkedHashMap<>();
        // 400
        applyDef(map, HttpMessageNotReadableException.class, HttpStatus.BAD_REQUEST);
        applyDef(map, MissingServletRequestParameterException.class, HttpStatus.BAD_REQUEST);
        applyDef(map, TypeMismatchException.class, HttpStatus.BAD_REQUEST);
        applyDef(map, "javax.validation.ValidationException", HttpStatus.BAD_REQUEST);
        applyDef(map, BindException.class, HttpStatus.BAD_REQUEST);
        applyDef(map, ServletRequestBindingException.class, HttpStatus.BAD_REQUEST);
        applyDef(map, MethodArgumentNotValidException.class, HttpStatus.BAD_REQUEST);
        applyDef(map, MissingServletRequestPartException.class, HttpStatus.BAD_REQUEST);
        // 404
        applyDef(map, NoHandlerFoundException.class, HttpStatus.NOT_FOUND);
        // 405
        applyDef(map, HttpRequestMethodNotSupportedException.class, HttpStatus.METHOD_NOT_ALLOWED);
        // 406
        applyDef(map, HttpMediaTypeNotAcceptableException.class, HttpStatus.NOT_ACCEPTABLE);
        // 409
        //can't use the class directly here as it may not be an available dependency:
        applyDef(map, "org.springframework.dao.DataIntegrityViolationException", HttpStatus.CONFLICT);
        // 415
        applyDef(map, HttpMediaTypeNotSupportedException.class, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        // 500
        applyDef(map, RuntimeException.class, HttpStatus.INTERNAL_SERVER_ERROR);
        applyDef(map, MissingPathVariableException.class, HttpStatus.INTERNAL_SERVER_ERROR);
        applyDef(map, ConversionNotSupportedException.class, HttpStatus.INTERNAL_SERVER_ERROR);
        applyDef(map, HttpMessageNotWritableException.class, HttpStatus.INTERNAL_SERVER_ERROR);
        //503
        applyDef(map, AsyncRequestTimeoutException.class, HttpStatus.SERVICE_UNAVAILABLE);

        return map;
    }

    private void applyDef(Map<String, String> map, Class clazz, HttpStatus status) {
        applyDef(map, clazz.getName(), status);
    }

    private void applyDef(Map<String, String> map, String name, HttpStatus status) {

        //将status转换成定义的字符串格式
        Map<String, String> res = new ConcurrentHashMap<>();
        String code = status.getReasonPhrase().toUpperCase().replaceAll(" ", "_");
        res.put("code", code);  //此处设置默认的code
        res.put("status", status.toString());
        res.put("message", DEFAULT_MESSAGE_VALUE);

        map.put(name, JsonMapper.defaultMapper().toJson(res));
    }


    /**
     * 转换成为：异常类-RestfulError格式的对应map
     *
     * @param definitions
     * @return
     */
    private Map<String, RestfulError> definitionRestErrors(Map<String, String> definitions) {

        if (CollectionUtils.isEmpty(definitions)) {
            return Collections.emptyMap();
        }

        Map<String, RestfulError> res = new LinkedHashMap<>(definitions.size());
        for (Map.Entry<String, String> entry : definitions.entrySet()) {
            String key = entry.getKey();    //异常的类名
            String value = entry.getValue();    //定义好的异常的处理格式
            RestfulError template = definitionRestError(value);
            res.put(key, template);
        }
        return res;
    }

    /**
     * 构造一个RestfulError。
     * 所有的错误定义都在此
     *
     * @param value
     * @return
     */
    private RestfulError definitionRestError(String value) {

        Map<String, String> error = JsonMapper.defaultMapper().fromJson(value, Map.class);
        String code = error.get("code");
        if (StringUtils.isEmpty(code)) {    //如果没有定义code，那么默认就是INTERNAL_SERVER_ERROR
            code = "INTERNAL_SERVER_ERROR";
        }

        String message = error.get("message");
        if (StringUtils.isEmpty(message)) {
            message = DEFAULT_MESSAGE_VALUE;    //如果没有定义message,默认为 ""
        }

        String status = String.valueOf(error.get("status"));
        HttpStatus httpStatus;
        try {
            httpStatus = HttpStatus.valueOf(Integer.valueOf(status));
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;  //默认使用 HttpStatus.INTERNAL_SERVER_ERROR
        }

        String developerMessage = error.get("developerMessage");
        if (StringUtils.isEmpty(message)) {
            developerMessage = DEFAULT_EXCEPTION_MESSAGE_VALUE;    //如果没有定义developerMessage,默认为 ""
        }

        RestfulError.Builder builder = new RestfulError.Builder();
        builder.setCode(code);
        builder.setMessage(message);
        builder.setStatus(httpStatus);
        builder.setDeveloperMessage(developerMessage);

        return builder.build();
    }

    /**
     * 所有的异常数据构建都在此方法中进行
     *
     * @param ex
     * @return
     */
    private RestfulError buildRuntimeError(Exception ex) {

        Map<String, RestfulError> mappings = this.exceptionMappings;
        if (CollectionUtils.isEmpty(mappings)) {
            return null;
        }
        if (ex == null) {
            ex = new RuntimeException();   //如果为空，就直接用RuntimeException
        }

        Class clazz = ex.getClass();    //获得异常的类
        RestfulError restfulError = mappings.get(clazz.getName());  //获得定义好的异常的处理格式
        if (restfulError == null) { //如果没有定义，那么就一直追溯它的父类，直到获得定义的格式为止
            List<Class<?>> superClasses = ClassUtils.getAllSuperclasses(clazz);
            for (Class superClass : superClasses) {
                restfulError = mappings.get(superClass.getName());
                if (restfulError != null) {
                    break;
                }
            }
        }

        String code = restfulError.getCode();   //获得异常业务代码
        HttpStatus httpStatus = restfulError.getStatus();   //获得异常的HTTP代码
        String message = restfulError.getMessage();
        String developerMessage = restfulError.getDeveloperMessage();

        if (ex instanceof AbstractBizException) {   //如果异常继承于AbstractBizException时的处理
            code = ((AbstractBizException) ex).getCode();
            if (((AbstractBizException) ex).getStatus() != 0) {
                httpStatus = HttpStatus.valueOf(((AbstractBizException) ex).getStatus());
            } else {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            message = ex.getMessage();
            developerMessage = ((AbstractBizException) ex).getDeveloperMessage();
            if (StringUtils.isEmpty(developerMessage)) {
                developerMessage = StringUtils.join(ex.getStackTrace(), "\n");
            }
        }

        if (StringUtils.isEmpty(message)) {
            message = ex.getMessage();
        }
        if (StringUtils.isEmpty(developerMessage)) {
            developerMessage = ex.toString();
        }

        RestfulError.Builder builder = new RestfulError.Builder();
        builder.setStatus(httpStatus);
        builder.setCode(code);
        builder.setMessage(message);
        builder.setDeveloperMessage(developerMessage);
        builder.setThrowable(ex);

        return builder.build();
    }

    private HttpStatus getStatus(RestfulError template) {
        return template.getStatus();
    }

    private String getCode(RestfulError template) {
        return defaultPrefixCode + template.getCode();
    }

    private String getDeveloperMessage(RestfulError template, HttpServletRequest request) {
        return getMessage(template.getDeveloperMessage(), request);
    }

    private String getMessage(RestfulError template, HttpServletRequest request) {
        return getMessage(template.getMessage(), request);
    }

    private String getMessage(String message, HttpServletRequest request) {
        if (message != null) {
            if (message.equalsIgnoreCase("null") || message.equalsIgnoreCase("off")) {
                return "";
            }
            if (messageSource != null) {
                Locale locale = null;
                if (localeResolver != null) {
                    locale = localeResolver.resolveLocale(request);
                }
                message = messageSource.getMessage(message, null, message, locale);
            }
        }
        return message;
    }

    public void setExceptionMappingDefinitions(Map<String, String> exceptionMappingDefinitions) {
        this.exceptionMappingDefinitions = exceptionMappingDefinitions;
    }

    public void setLocaleResolver(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    public void setDefaultPrefixCode(String defaultPrefixCode) {
        this.defaultPrefixCode = defaultPrefixCode;
    }


}