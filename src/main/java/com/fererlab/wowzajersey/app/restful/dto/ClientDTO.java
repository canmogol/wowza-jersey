package com.fererlab.wowzajersey.app.restful.dto;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClientDTO {

    private String uniqueId;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
