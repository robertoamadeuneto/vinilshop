package com.vinilshop.application.exception.details;

import com.vinilshop.application.exception.ExceptionDetails;
import javax.validation.ValidationException;

/**
 * Class responsible for all details involving the {@link ValidationException}.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
public class ValidationExceptionDetails extends ExceptionDetails {

    private String field;
    private String fieldMessage;

    public String getField() {
        return field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public static final class Builder {

        private String title;
        private int status;
        private String detail;
        private long timestamp;
        private String developerMessage;
        private String field;
        private String fieldMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(final String value) {
            this.title = value;
            return this;
        }

        public Builder status(final int value) {
            this.status = value;
            return this;
        }

        public Builder detail(final String value) {
            this.detail = value;
            return this;
        }

        public Builder timestamp(final long value) {
            this.timestamp = value;
            return this;
        }

        public Builder developerMessage(final String value) {
            this.developerMessage = value;
            return this;
        }

        public Builder field(final String value) {
            this.field = value;
            return this;
        }

        public Builder fieldMessage(final String value) {
            this.fieldMessage = value;
            return this;
        }

        public ValidationExceptionDetails build() {
            ValidationExceptionDetails validationDetails = new ValidationExceptionDetails();
            validationDetails.setDeveloperMessage(developerMessage);
            validationDetails.setTitle(title);
            validationDetails.setDetail(detail);
            validationDetails.setTimestamp(timestamp);
            validationDetails.setStatus(status);
            validationDetails.field = field;
            validationDetails.fieldMessage = fieldMessage;
            return validationDetails;
        }
    }
}
