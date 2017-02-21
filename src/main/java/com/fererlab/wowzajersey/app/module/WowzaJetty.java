package com.fererlab.wowzajersey.app.module;

import com.fererlab.wowzajersey.app.model.Client;
import com.fererlab.wowzajersey.app.model.HttpSession;
import com.fererlab.wowzajersey.app.model.RtpSession;
import com.fererlab.wowzajersey.core.module.MediaServerModule;
import com.wowza.wms.amf.AMFDataList;
import com.wowza.wms.application.IApplicationInstance;
import com.wowza.wms.client.IClient;
import com.wowza.wms.httpstreamer.cupertinostreaming.httpstreamer.HTTPStreamerSessionCupertino;
import com.wowza.wms.httpstreamer.model.IHTTPStreamerSession;
import com.wowza.wms.httpstreamer.smoothstreaming.httpstreamer.HTTPStreamerSessionSmoothStreamer;
import com.wowza.wms.module.ModuleBase;
import com.wowza.wms.request.RequestFunction;
import com.wowza.wms.rtp.model.RTPSession;
import com.wowza.wms.stream.IMediaStream;

import java.util.HashMap;
import java.util.Map;

/**
 * WowzaJetty class is the module that will be loaded by Wowza media server
 * This class should be registered to Wowza at Application.xml file's modules section
 */
public class WowzaJetty extends ModuleBase implements MediaServerModule {

    /**
     * client map should hold the connected clients
     */
    private Map<String, IClient> clientMap = new HashMap<>();

    /**
     * WowzaJettyModule is the isolation class from the wowza specific API.
     * Wowza API should not be imported/used other than WowzaJettyModule class
     */
    private WowzaJettyModule module = new WowzaJettyModule(this);

    /*
     * below are Wowza Module lifecycle methods 
     */

    public void onAppStart(IApplicationInstance appInstance) {
        String applicationFullName = appInstance.getApplication().getName() + "/" + appInstance.getName();
        // call module lifecycle method
        module.onStart(applicationFullName);
    }

    public void onAppStop(IApplicationInstance appInstance) {
        String applicationFullName = appInstance.getApplication().getName() + "/" + appInstance.getName();
        // call module lifecycle method
        module.onStop(applicationFullName);
    }

    public void onConnect(IClient client, RequestFunction function, AMFDataList params) {
        // build internal client object
        Client c = this.buildClient(client);
        // add client to map
        clientMap.put(c.getUuid(), client);
        // call module lifecycle method
        module.onConnect(c);
        // authenticate method should authenticate client object from an authenticator
        if (!module.authenticate(c)) {
            // this will close the client's connection
            client.rejectConnection();
        } else {
            // this is the default action
            client.acceptConnection();
        }
    }

    public void onConnectAccept(IClient client) {
        // build internal client object
        Client c = this.buildClient(client);
        // call module lifecycle method
        module.onConnectAccept(c);
    }

    public void onConnectReject(IClient client) {
        // build internal client object
        Client c = this.buildClient(client);
        // call module lifecycle method
        module.onConnectReject(c);
    }

    public void onDisconnect(IClient client) {
        // build internal client object
        Client c = this.buildClient(client);
        // remove client from map
        clientMap.remove(c.getUuid());
        // call module lifecycle method
        module.onDisconnect(c);
    }

    public void onStreamCreate(IMediaStream stream) {
        // call module lifecycle method
        module.onStreamCreate(stream.getName());
    }

    public void onStreamDestroy(IMediaStream stream) {
        // call module lifecycle method
        module.onStreamDestroy(stream.getName());
    }

    public void onHTTPSessionCreate(IHTTPStreamerSession streamerSession) {
        // build internal http session object
        HttpSession HttpSession = this.buildHttpSession(streamerSession);
        // authorize method should authorize http session and its client from an authorizor
        if (!module.authorize(HttpSession)) {
            // this will close the http session
            streamerSession.rejectSession();
            // this will close the client's connection
            streamerSession.getConnectionHolder().getClient().rejectConnection();
        } else {
            // this is the default action
            streamerSession.acceptSession();
        }
        // call module lifecycle method
        module.onHTTPSessionCreate(HttpSession);
    }

    public void onHTTPSessionDestroy(IHTTPStreamerSession streamerSession) {
        // build internal http session object
        HttpSession HttpSession = this.buildHttpSession(streamerSession);
        // call module lifecycle method
        module.onHTTPSessionDestroy(HttpSession);
    }

    public void onHTTPCupertinoStreamingSessionCreate(HTTPStreamerSessionCupertino streamerSession) {
        // build internal http session object
        HttpSession HttpSession = this.buildHttpSession(streamerSession);
        // call module lifecycle method
        module.onStreamSessionCreate(HttpSession);
    }

    public void onHTTPCupertinoStreamingSessionDestroy(HTTPStreamerSessionCupertino streamerSession) {
        // build internal http session object
        HttpSession HttpSession = this.buildHttpSession(streamerSession);
        // call module lifecycle method
        module.onStreamSessionDestroy(HttpSession);
    }

    public void onHTTPSmoothStreamingSessionCreate(HTTPStreamerSessionSmoothStreamer streamerSession) {
        // build internal http session object
        HttpSession HttpSession = this.buildHttpSession(streamerSession);
        // call module lifecycle method
        module.onStreamSessionCreate(HttpSession);
    }

    public void onHTTPSmoothStreamingSessionDestroy(HTTPStreamerSessionSmoothStreamer streamerSession) {
        // build internal http session object
        HttpSession HttpSession = this.buildHttpSession(streamerSession);
        // call module lifecycle method
        module.onStreamSessionDestroy(HttpSession);
    }

    public void onRTPSessionCreate(RTPSession session) {
        // build internal rtp session object
        RtpSession rtpSession = this.buildRtpSession(session);
        // call module lifecycle method
        module.onRTPSessionCreate(rtpSession);
    }

    public void onRTPSessionDestroy(RTPSession session) {
        // build internal rtp session object
        RtpSession rtpSession = this.buildRtpSession(session);
        // call module lifecycle method
        module.onRTPSessionDestroy(rtpSession);
    }

    public void onCall(String handlerName, IClient client, RequestFunction function, AMFDataList params) {
        // build internal client object
        Client c = this.buildClient(client);
        // call module lifecycle method
        module.onCall(c);
    }

    /**
     * this method should create an internal immutable client object
     *
     * @param client {@link IClient} wowza's client
     * @return internal {@link Client} object
     */
    private Client buildClient(IClient client) {
        // use a builder pattern to create immutable internal client object,
        // it is imperative to separate internal client and wowza's client API
        // these two objects might only share primitive and common data types
        Client c = new Client.Builder()
                .connected(client.isConnected())
                .id(client.getClientId())
                .ip(client.getIp())
                .streamType(client.getStreamType())
                .uri(client.getUri())
                .build();
        return c;
    }

    /**
     * this method should create an internal immutable HttpSession object
     *
     * @param streamerSession HttpSession
     * @return HttpSession
     */
    private HttpSession buildHttpSession(IHTTPStreamerSession streamerSession) {
        // get wowza's client
        IClient client = streamerSession.getConnectionHolder().getClient();
        // build internal client
        Client c = this.buildClient(client);

        // prepare necessary data
        String ipAddressClient = streamerSession.getIpAddress();
        String ipAddressServer = streamerSession.getServerIp();
        String queryStr = streamerSession.getQueryStr();
        String referrer = streamerSession.getReferrer();
        String cookieStr = streamerSession.getCookieStr();
        String userAgent = streamerSession.getUserAgent();
        String uri = streamerSession.getUri();
        String sessionId = streamerSession.getSessionId();
        IApplicationInstance appInstance = streamerSession.getAppInstance();
        String applicationFullName = appInstance.getApplication().getName() + "/" + appInstance.getName();
        String streamName = streamerSession.getStreamName();

        // use a builder pattern to create immutable internal httpSession object,
        // it is imperative to separate internal httpSession and wowza's httpSession API
        // these two objects might only share primitive and common data types
        HttpSession httpSession = new HttpSession.Builder()
                .ipAddressClient(ipAddressClient)
                .ipAddressServer(ipAddressServer)
                .queryStr(queryStr)
                .referrer(referrer)
                .cookieStr(cookieStr)
                .userAgent(userAgent)
                .uri(uri)
                .sessionId(sessionId)
                .applicationFullName(applicationFullName)
                .streamName(streamName)
                .client(c)
                .build();
        return httpSession;
    }

    /**
     * this method should create an internal immutable RtpSession object
     *
     * @param session RtpSession
     * @return RtpSession
     */
    private RtpSession buildRtpSession(RTPSession session) {
        // get wowza's client
        IClient client = session.getConnectionHolder().getClient();
        // build internal client
        Client c = this.buildClient(client);

        // prepare necessary data
        String ipAddressClient = session.getIp();
        String ipAddressServer = session.getServerIp();
        String queryStr = session.getQueryStr();
        String referrer = session.getReferrer();
        String cookieStr = session.getCookieStr();
        String userAgent = session.getUserAgent();
        String uri = session.getUri();
        String sessionId = session.getSessionId();
        IApplicationInstance appInstance = session.getAppInstance();
        String applicationFullName = appInstance.getApplication().getName() + "/" + appInstance.getName();

        // use a builder pattern to create immutable internal rtpSession object,
        // it is imperative to separate internal rtpSession and wowza's rtpSession API
        // these two objects might only share primitive and common data types
        RtpSession rtpSession = new RtpSession.Builder()
                .ipAddressClient(ipAddressClient)
                .ipAddressServer(ipAddressServer)
                .queryStr(queryStr)
                .referrer(referrer)
                .cookieStr(cookieStr)
                .userAgent(userAgent)
                .uri(uri)
                .sessionId(sessionId)
                .applicationFullName(applicationFullName)
                .client(c)
                .build();
        return rtpSession;
    }

    /*
     * below are MediaServerModule specific methods
     */

    @Override
    public boolean hasClient(String uniqueId) {
        return this.clientMap.containsKey(uniqueId);
    }

    @Override
    public Client getClient(String uniqueId) {
        IClient client = this.clientMap.get(uniqueId);
        return this.buildClient(client);
    }

    @Override
    public boolean disconnectClient(Client client) {
        try {
            String uniqueId = client.getUuid();
            IClient iclient = this.clientMap.get(uniqueId);
            iclient.shutdownClient();
            return true;
        } catch (Exception pokemon) {
            getLogger().info("exception while disconnecting/shutting down client: " + client + " error: " + pokemon.getMessage());
            return false;
        }
    }
}
