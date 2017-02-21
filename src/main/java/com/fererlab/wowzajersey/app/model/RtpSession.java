package com.fererlab.wowzajersey.app.model;

import com.fererlab.wowzajersey.core.model.BaseModel;

public class RtpSession extends BaseModel {

    private String ipAddressClient;
    private String ipAddressServer;
    private String queryStr;
    private String referrer;
    private String cookieStr;
    private String userAgent;
    private String uri;
    private String sessionId;
    private String applicationFullName;
    private Client client;

    public static class Builder {

        private String ipAddressClient;
        private String ipAddressServer;
        private String queryStr;
        private String referrer;
        private String cookieStr;
        private String userAgent;
        private String uri;
        private String sessionId;
        private String applicationFullName;
        private Client client;

        public Builder() {
        }

        public Builder ipAddressClient(String ipAddressClient) {
            this.ipAddressClient = ipAddressClient;
            return this;
        }

        public Builder ipAddressServer(String ipAddressServer) {
            this.ipAddressServer = ipAddressServer;
            return this;
        }

        public Builder queryStr(String queryStr) {
            this.queryStr = queryStr;
            return this;
        }

        public Builder referrer(String referrer) {
            this.referrer = referrer;
            return this;
        }

        public Builder cookieStr(String cookieStr) {
            this.cookieStr = cookieStr;
            return this;
        }

        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public Builder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder applicationFullName(String applicationFullName) {
            this.applicationFullName = applicationFullName;
            return this;
        }

        public Builder client(Client client) {
            this.client = client;
            return this;
        }

        public RtpSession build() {
            RtpSession rtpSession = new RtpSession();
            rtpSession.ipAddressClient = this.ipAddressClient;
            rtpSession.ipAddressServer = this.ipAddressServer;
            rtpSession.queryStr = this.queryStr;
            rtpSession.referrer = this.referrer;
            rtpSession.cookieStr = this.cookieStr;
            rtpSession.userAgent = this.userAgent;
            rtpSession.uri = this.uri;
            rtpSession.sessionId = this.sessionId;
            rtpSession.applicationFullName = this.applicationFullName;
            rtpSession.client = this.client;
            return rtpSession;
        }


    }

    private RtpSession() {
    }

    public String getIpAddressClient() {
        return ipAddressClient;
    }

    public String getIpAddressServer() {
        return ipAddressServer;
    }

    public String getQueryStr() {
        return queryStr;
    }

    public String getReferrer() {
        return referrer;
    }

    public String getCookieStr() {
        return cookieStr;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getUri() {
        return uri;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getApplicationFullName() {
        return applicationFullName;
    }

    public Client getClient() {
        return client;
    }

}
