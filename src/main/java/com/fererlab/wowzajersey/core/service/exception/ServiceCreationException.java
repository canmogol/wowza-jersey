package com.fererlab.wowzajersey.core.service.exception;

public class ServiceCreationException extends Throwable {

    private final String serviceName;
    private final String errorMessage;

    public ServiceCreationException(String serviceName, String errorMessage) {
        this.serviceName = serviceName;
        this.errorMessage = errorMessage;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
