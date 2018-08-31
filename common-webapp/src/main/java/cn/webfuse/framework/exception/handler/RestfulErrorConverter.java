package cn.webfuse.framework.exception.handler;


import org.springframework.core.convert.converter.Converter;

/**
 * RestfulError转换器，将RestfulError对象转换成其他对象
 */
public interface RestfulErrorConverter<T> extends Converter<RestfulError, T> {

    @Override
    T convert(RestfulError source);
}
