package com.vinilshop.application.exception.details;

/**
 * Class representing error details.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @since 1.0
 */
public class ExceptionDetails {

    private String title;
    private int status;
    private String detail;
    private long timestamp;
    private String developerMessage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public static class Builder {

        private String title;
        private int status;
        private String detail;
        private long timestamp;
        private String developerMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(final String title) {
            this.title = title;
            return this;
        }

        public Builder status(final int status) {
            this.status = status;
            return this;
        }

        public Builder detail(final String detail) {
            this.detail = detail;
            return this;
        }

        public Builder timestamp(final long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(final String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ExceptionDetails build() {
            ExceptionDetails errorDetails = new ExceptionDetails();
            errorDetails.setTitle(title);
            errorDetails.setStatus(status);
            errorDetails.setDetail(detail);
            errorDetails.setTimestamp(timestamp);
            errorDetails.setDeveloperMessage(developerMessage);
            return errorDetails;
        }
    }
}
