package cn.webfuse.framework.exception.handler;

import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * Restful错误
 */
public class RestfulError {

    private final HttpStatus status;
    private final String code;
    private final String message;
    private final String developerMessage;
    private final Throwable throwable;
    private final Date serverTime = new Date();


    private RestfulError(HttpStatus status, String code, String message, String developerMessage, Throwable throwable) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.developerMessage = developerMessage;
        this.throwable = throwable;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public Date getServerTime() {
        return serverTime;
    }

    public static class Builder {
        private HttpStatus status;
        private String code;
        private String message;
        private String developerMessage;
        private Throwable throwable;

        public Builder() {
        }

        public Builder setStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public Builder setThrowable(Throwable throwable) {
            this.throwable = throwable;
            return this;
        }

        public RestfulError build() {
            if (this.status == null) {
                this.status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return new RestfulError(this.status, this.code, this.message, this.developerMessage, this.throwable);
        }

    }
}
