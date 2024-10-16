package com.fintech.emoneyrechargeonlinenew.Api.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsRequest extends BasicRequest {

    @SerializedName("isLoginNews")
    @Expose
    private boolean isLoginNews;

    public NewsRequest(boolean isLoginNews, String userID, String loginTypeID, String appid, String imei, String regKey, String version, String serialNo, String sessionID, String session) {
        this.isLoginNews = isLoginNews;
        this.userID = userID;
        this.loginTypeID = loginTypeID;
        this.appid = appid;
        this.imei = imei;
        this.regKey = regKey;
        this.version = version;
        this.serialNo = serialNo;
        this.sessionID = sessionID;
        this.session = session;
    }
}

