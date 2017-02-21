package com.fererlab.wowzajersey.core.log;

import java.util.logging.Logger;

public class ModuleLifecycleLogger extends BaseLogger {

    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    protected Logger getLogger() {
        return logger;
    }

    public void onStart(String applicationFullName) {
        log("application started, application name: " + applicationFullName);
    }

    public void onConnect(Object client) {
        log("client connected, client: " + client);
    }

    public void onConnectAccept(Object client) {
        log("client connection accepted, client: " + client);
    }

    public void onConnectReject(Object client) {
        log("client connection rejected, client: " + client);
    }

    public void onDisconnect(Object client) {
        log("client disconnected, client: " + client);
    }

    public void onStreamCreate(String streamName) {
        log("stream created, streamName: " + streamName);
    }

    public void onStreamDestroy(String streamName) {
        log("stream destroyed, streamName: " + streamName);
    }

    public void onHTTPSessionCreate(Object httpSession) {
        log("http session created, httpSession: " + httpSession);
    }

    public void onHTTPSessionDestroy(Object httpSession) {
        log("http session destroyed, httpSession: " + httpSession);
    }

    public void onStop(String applicationFullName) {
        log("application stopped, application name: " + applicationFullName);
    }

    public void onStreamSessionCreate(Object session) {
        log("stream session created, session: " + session);
    }

    public void onStreamSessionDestroy(Object session) {
        log("stream session destroyed, session: " + session);
    }

    public void onRTPSessionCreate(Object session) {
        log("rtp session created, session: " + session);
    }

    public void onRTPSessionDestroy(Object session) {
        log("rtp session destroy, session: " + session);
    }

    public void onCall(Object client) {
        log("called from client: " + client);
    }

}
