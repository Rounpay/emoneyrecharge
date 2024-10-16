package com.fintech.emoneyrechargeonlinenew.Api.Request;

public class UpdateUPIRequest {

    public String userID;

    public String sessionID;

    public String session;

    public String appid;

    public String imei;

    public String regKey;

    public String version;

    public String serialNo;
    public String loginTypeID;
    String tid;
    String bankStatus;

    public UpdateUPIRequest(String tid, String bankStatus, String userID, String loginTypeID, String appid, String imei, String regKey, String version, String serialNo, String sessionID, String session) {
        this.tid = tid;
        this.bankStatus = bankStatus;
        this.userID = userID;
        this.sessionID = sessionID;
        this.session = session;
        this.appid = appid;
        this.imei = imei;
        this.regKey = regKey;
        this.version = version;
        this.serialNo = serialNo;
        this.loginTypeID = loginTypeID;
    }
}
