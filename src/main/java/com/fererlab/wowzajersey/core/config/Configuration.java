package com.fererlab.wowzajersey.core.config;

import com.fererlab.wowzajersey.core.controller.Controller;
import com.fererlab.wowzajersey.core.service.type.Service;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Configuration {

    private List<Class<? extends Service>> services;
    private List<Class<? extends Controller>> controllers;
    private String authorizationUrl;
    private String authenticationUrl;
    private String sessionKey;

    public List<Class<? extends Service>> getServices() {
        return services;
    }

    public void setServices(List<Class<? extends Service>> services) {
        this.services = services;
    }

    public List<Class<? extends Controller>> getControllers() {
        return controllers;
    }

    public void setControllers(List<Class<? extends Controller>> controllers) {
        this.controllers = controllers;
    }

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public void setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    public String getAuthenticationUrl() {
        return authenticationUrl;
    }

    public void setAuthenticationUrl(String authenticationUrl) {
        this.authenticationUrl = authenticationUrl;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

}
