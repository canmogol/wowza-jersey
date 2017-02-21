package com.fererlab.wowzajersey.app.restful.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum StatusEnum {
    SUCCESS, FAIL_OPERATIONAL, FAIL_INTERNAL, FAIL_MEDIA_SERVER
}
