package com.fintech.emoneyrechargeonlinenew.Api.Request;

public class ChangePinPasswordRequest {

    boolean isPin;
    String oldP;
    String NewP;
    String ConfirmP;
    String userID;
    String sessionID;
    String session;
    String appid;
    String imei;
    String regKey;
    String version;
    String serialNo;
    String loginTypeID;

    public ChangePinPasswordRequest(boolean isPin, String oldP, String NewP, String ConfirmP, String userID, String loginTypeID, String appid, String imei, String regKey, String version, String serialNo, String sessionID, String session) {
        this.isPin = isPin;
        this.NewP = NewP;
        this.ConfirmP = ConfirmP;
        this.userID = userID;
        this.loginTypeID = loginTypeID;
        this.appid = appid;
        this.imei = imei;
        this.regKey = regKey;
        this.version = version;
        this.serialNo = serialNo;
        this.sessionID = sessionID;
        this.session = session;
        this.oldP = oldP;
    }
}
