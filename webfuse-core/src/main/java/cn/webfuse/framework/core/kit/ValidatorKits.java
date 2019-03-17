package cn.webfuse.framework.core.kit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 数值校验取值器
 * <p>
 * From: io.github.forezp.scrorpio.text.ValidatorUtils
 */
public class ValidatorKits {

    private static Logger logger = LoggerFactory.getLogger(ValidatorKits.class);

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void validateEntity(T obj) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && set.size() > 0) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        checkError(result);
    }

    private static void checkError(ValidationResult result) {
        if (result.isHasErrors()) {
            logger.error("校验实体有错误,错误为{}", result.toString());
            if (result.getErrorMsg().size() > 0) {
                throw new ValidationException(mapValueToString(result.getErrorMsg()));
            }
            throw new ValidationException();
        }
    }

    private static String mapValueToString(Map<String, String> errorMsg) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<String, String>> set = errorMsg.entrySet();
        for (Map.Entry<String, String> entry : set) {
            sb.append(entry.getValue());
            sb.append(",");
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }


    public static <T> void validateProperty(T obj, String propertyName) {
        logger.debug("校验参数 {}", propertyName);
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        if (set != null && set.size() > 0) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(propertyName, cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        checkError(result);
    }

    private static class ValidationResult {

        /**
         * 校验结果是否有错
         */
        private boolean hasErrors;

        /**
         * 校验错误信息
         */
        private Map<String, String> errorMsg;

        public boolean isHasErrors() {
            return hasErrors;
        }

        public void setHasErrors(boolean hasErrors) {
            this.hasErrors = hasErrors;
        }

        public Map<String, String> getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(Map<String, String> errorMsg) {
            this.errorMsg = errorMsg;
        }

        @Override
        public String toString() {
            return "ValidationResult [hasErrors=" + hasErrors + ", errorMsg="
                    + errorMsg + "]";
        }
    }

}
