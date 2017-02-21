package com.fererlab.wowzajersey.app.model;

import com.fererlab.wowzajersey.core.model.BaseModel;

public class Client extends BaseModel {

    private boolean connected;
    private String streamType;
    private String ip;
    private String uri;
    private int id;

    private Client() {
    }

    private Client(String uuid) {
        super(uuid);
    }

    public static class Builder {

        private String uuid;
        private boolean connected;
        private String streamType;
        private String ip;
        private String uri;
        private int id;

        public Builder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder connected(boolean connected) {
            this.connected = connected;
            return this;
        }

        public Builder streamType(String streamType) {
            this.streamType = streamType;
            return this;
        }

        public Builder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Client build() {
            Client client;
            if (this.uuid != null) {
                client = new Client(this.uuid);
            } else {
                client = new Client();
            }
            client.connected = this.connected;
            client.id = this.id;
            client.ip = this.ip;
            client.streamType = this.streamType;
            client.uri = this.uri;
            return client;
        }

    }

    public boolean isConnected() {
        return connected;
    }

    public String getStreamType() {
        return streamType;
    }

    public String getIp() {
        return ip;
    }

    public String getUri() {
        return uri;
    }

    public int getId() {
        return id;
    }

}
