package com.fererlab.wowzajersey.core.module;

import com.fererlab.wowzajersey.app.model.Client;
import com.fererlab.wowzajersey.app.model.HttpSession;
import com.fererlab.wowzajersey.app.model.RtpSession;

public interface ModuleLifecycleListener {

    void onStart(String applicationFullName);

    void onConnect(Client client);

    void onConnectAccept(Client client);

    void onConnectReject(Client client);

    void onDisconnect(Client client);

    void onStreamCreate(String streamName);

    void onStreamDestroy(String streamName);

    void onHTTPSessionCreate(HttpSession HttpSession);

    void onHTTPSessionDestroy(HttpSession HttpSession);

    void onStop(String applicationFullName);

    void onStreamSessionCreate(HttpSession HttpSession);

    void onStreamSessionDestroy(HttpSession HttpSession);

    void onRTPSessionCreate(RtpSession rtpSession);

    void onRTPSessionDestroy(RtpSession rtpSession);

    void onCall(Client c);

}
