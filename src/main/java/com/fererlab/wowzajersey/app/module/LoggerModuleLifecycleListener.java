package com.fererlab.wowzajersey.app.module;

import com.fererlab.wowzajersey.core.log.ModuleLifecycleLogger;
import com.fererlab.wowzajersey.core.module.ModuleLifecycleListener;
import com.fererlab.wowzajersey.app.model.Client;
import com.fererlab.wowzajersey.app.model.HttpSession;
import com.fererlab.wowzajersey.app.model.RtpSession;

public class LoggerModuleLifecycleListener implements ModuleLifecycleListener {

    private ModuleLifecycleLogger logger = new ModuleLifecycleLogger();

    @Override
    public void onStart(String applicationFullName) {
        logger.onStart(applicationFullName);
    }

    @Override
    public void onConnect(Client client) {
        logger.onConnect(client);
    }

    @Override
    public void onConnectAccept(Client client) {
        logger.onConnectAccept(client);
    }

    @Override
    public void onConnectReject(Client client) {
        logger.onConnectReject(client);
    }

    @Override
    public void onDisconnect(Client client) {
        logger.onDisconnect(client);
    }

    @Override
    public void onStreamCreate(String streamName) {
        logger.onStreamCreate(streamName);
    }

    @Override
    public void onStreamDestroy(String streamName) {
        logger.onStreamDestroy(streamName);
    }

    @Override
    public void onHTTPSessionCreate(HttpSession HttpSession) {
        logger.onHTTPSessionCreate(HttpSession);
    }

    @Override
    public void onHTTPSessionDestroy(HttpSession HttpSession) {
        logger.onHTTPSessionDestroy(HttpSession);
    }

    @Override
    public void onStop(String applicationFullName) {
        logger.onStop(applicationFullName);
    }

    @Override
    public void onStreamSessionCreate(HttpSession HttpSession) {
        logger.onStreamSessionCreate(HttpSession);
    }

    @Override
    public void onStreamSessionDestroy(HttpSession HttpSession) {
        logger.onStreamSessionDestroy(HttpSession);
    }

    @Override
    public void onRTPSessionCreate(RtpSession rtpSession) {
        logger.onRTPSessionCreate(rtpSession);
    }

    @Override
    public void onRTPSessionDestroy(RtpSession rtpSession) {
        logger.onRTPSessionDestroy(rtpSession);
    }

    @Override
    public void onCall(Client client) {
        logger.onCall(client);
    }

}
