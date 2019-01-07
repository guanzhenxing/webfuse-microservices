package cn.webfuse.framework.logging.access;

import cn.webfuse.framework.core.kit.id.IdGenerator;
import cn.webfuse.framework.kit.AspectjKits;
import cn.webfuse.framework.kit.HttpServletKits;
import cn.webfuse.framework.logging.AbstractLoggerAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@Aspect
public class AccessAbstractLoggerAspect extends AbstractLoggerAspect {


    @Pointcut("@annotation(cn.webfuse.framework.logging.access.AccessLogger)")
    public void recordLog() {
    }

    @Around("recordLog()")
    public Object aroundLogCalls(ProceedingJoinPoint joinPoint) throws Throwable {

        AccessLoggerInfo info = buildAccessLoggerInfo(joinPoint);

        Object response;
        try {
            response = joinPoint.proceed();
            info.setResponse(response);
        } catch (Exception e) {
            info.setException(e);
            throw e;
        } finally {
            info.setResponseTime(System.currentTimeMillis());
            loggerWriters.parallelStream().forEach(writer -> writer.write(info));
        }

        return response;
    }

    private AccessLoggerInfo buildAccessLoggerInfo(ProceedingJoinPoint joinPoint) {
        AccessLoggerInfo info = new AccessLoggerInfo();
        info.setId(IdGenerator.SNOW_FLAKE_STRING.generate());
        info.setRequestTime(System.currentTimeMillis());
        buildAspectInfo(info, joinPoint);
        buildAnnotationInfo(info, joinPoint);
        buildHttpRequestInfo(info);
        return info;
    }

    private void buildHttpRequestInfo(AccessLoggerInfo info) {
        HttpServletRequest request = HttpServletKits.getRequest();
        if (request != null) {
            info.setHttpMethod(request.getMethod());
            info.setIp(HttpServletKits.getIpAddress(request));
            info.setUrl(request.getRequestURL().toString());
            info.setRequestHeaders(HttpServletKits.getRequestHeaders(request));
        }
    }


    private void buildAspectInfo(AccessLoggerInfo info, ProceedingJoinPoint joinPoint) {
        Class target = AspectjKits.getClass(joinPoint);
        Method method = AspectjKits.getMethod(joinPoint);
        Map<String, Object> argsMap = AspectjKits.getArgsMap(joinPoint);

        info.setTarget(target);
        info.setMethod(method);
        info.setParameters(argsMap);
    }

    private void buildAnnotationInfo(AccessLoggerInfo info, ProceedingJoinPoint joinPoint) {
        Method method = AspectjKits.getMethod(joinPoint);
        Class clazz = AspectjKits.getClass(joinPoint);

        AccessLogger methodAnnotation = method.getAnnotation(AccessLogger.class);
        AccessLogger clazzAnnotation = clazz.getClass().getAnnotation(AccessLogger.class);

        String action = Stream.of(clazzAnnotation, methodAnnotation)
                .filter(Objects::nonNull)
                .map(AccessLogger::value)
                .reduce((c, m) -> c.concat("-").concat(m))
                .orElse("");

        String describe = Stream.of(clazzAnnotation, methodAnnotation)
                .filter(Objects::nonNull)
                .map(AccessLogger::describe)
                .flatMap(Stream::of)
                .reduce((c, s) -> c.concat("\n").concat(s))
                .orElse("");

        info.setAction(action);
        info.setDescribe(describe);
    }


}
