package com.fererlab.wowzajersey.core.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.UUID;

/**
 * BaseModel for POJOs
 */
public abstract class BaseModel implements Model {

    private String uuid = UUID.randomUUID().toString();

    protected BaseModel() {
    }

    protected BaseModel(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    /**
     * this method uses apache commons' reflective string builder,
     * extended objects should override whenever possible,
     * this method will be called from loggers
     *
     * @return String representation of object
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
