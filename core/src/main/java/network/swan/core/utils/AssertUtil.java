package network.swan.core.utils;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.helpers.MessageFormatter;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Assert工具类
 */
public class AssertUtil {

    private AssertUtil() {
    }

    public static final String DEFAULT_ASSERT_NULL_MESSAGE = "The validated object is not null";
    public static final String DEFAULT_ASSERT_NOT_NULL_MESSAGE = "The validated object is null";
    public static final String DEFAULT_ASSERT_TRUE_MESSAGE = "The validated expression is false";
    public static final String DEFAULT_ASSERT_FALSE_MESSAGE = "The validated expression is true";
    public static final String DEFAULT_ASSERT_HAS_TEXT_MESSAGE = "The validated string is empty";
    public static final String DEFAULT_ASSERT_MATCHES_MESSAGE = "The validated string '{}' does not match the pattern '{}'";

    public static void assertNull(Object target) {
        assertNull(target, DEFAULT_ASSERT_NULL_MESSAGE);
    }

    public static void assertCollectionNotEmpty(Collection collection, String message, Object... params) {
        if (CollectionUtils.isEmpty(collection)) {
            throwAssertionException(message, params);
        }
    }

    public static void assertCollectionEmpty(Collection<Object> collection, String message, Object... params) {
        if (CollectionUtils.isNotEmpty(collection)) {
            throwAssertionException(message, params);
        }
    }

    public static void assertNull(Object target, String message, Object... params) {
        if (target != null) {
            throwAssertionException(message, params);
        }
    }

    public static void assertNotNull(Object target) {
        assertNotNull(target, DEFAULT_ASSERT_NOT_NULL_MESSAGE);
    }

    public static void assertNotNull(Object target, String message, Object... params) {
        if (target == null) {
            throwAssertionException(message, params);
        }
    }

    public static void assertTrue(boolean target) {
        assertTrue(target, DEFAULT_ASSERT_TRUE_MESSAGE);
    }

    public static void assertTrue(boolean target, String message, Object... params) {
        if (!target) {
            throwAssertionException(message, params);
        }
    }

    public static void assertFalse(boolean target) {
        assertFalse(target, DEFAULT_ASSERT_FALSE_MESSAGE);
    }

    public static void assertFalse(boolean target, String message, Object... params) {
        assertTrue(!target, message, params);
    }

    public static void assertHasText(String target) {
        assertHasText(target, DEFAULT_ASSERT_HAS_TEXT_MESSAGE);
    }

    public static void assertHasText(String target, String message, Object... params) {
        if (!StringUtil.hasText(target)) {
            throwAssertionException(message, params);
        }
    }

    public static void assertMatches(String target, Pattern pattern) {
        assertMatches(target, pattern, DEFAULT_ASSERT_MATCHES_MESSAGE, target, pattern);
    }

    public static void assertMatches(String target, Pattern pattern, String message, Object... params) {
        if (!pattern.matcher(target).matches()) {
            throwAssertionException(message, params);
        }
    }

    private static void throwAssertionException(String message, Object[] params) {
        String errorMessage = params == null ? message : MessageFormatter.arrayFormat(message, params).getMessage();
        throw new AssertionException(errorMessage);
    }

    public static class AssertionException extends RuntimeException {

        private static final long serialVersionUID = -1023297548643274521L;

        public AssertionException(String message) {
            super(message);
        }
    }

}
