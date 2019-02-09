package com.vinilshop.application.exception.details;

import com.vinilshop.application.exception.ResourceNotFoundException;

/**
 * Class responsible for all details involving the
 * {@link ResourceNotFoundException}.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
public class ResourceNotFoundExceptionDetails extends ExceptionDetails {

    public static final class Builder {

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

        public ResourceNotFoundExceptionDetails build() {
            ResourceNotFoundExceptionDetails resourceNotFoundDetails = new ResourceNotFoundExceptionDetails();
            resourceNotFoundDetails.setDeveloperMessage(developerMessage);
            resourceNotFoundDetails.setTitle(title);
            resourceNotFoundDetails.setDetail(detail);
            resourceNotFoundDetails.setTimestamp(timestamp);
            resourceNotFoundDetails.setStatus(status);
            return resourceNotFoundDetails;
        }
    }
}
