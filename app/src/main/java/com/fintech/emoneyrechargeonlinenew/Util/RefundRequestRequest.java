package com.fintech.emoneyrechargeonlinenew.Util;


public class RefundRequestRequest {

    public String tid;
    public String transactionID;
    public String userID;
    public String sessionID;
    public String session;
    public String appid;
    public String imei;
    public String regKey;
    public String version;
    public String serialNo;
    public String loginTypeID;

    public RefundRequestRequest(String tid, String transactionID, String userID, String sessionID, String session, String appid, String imei, String regKey, String version, String serialNo, String loginTypeID){
        this.tid=tid;
        this.transactionID=transactionID;
        this.userID=userID;
        this.sessionID=sessionID;
        this.session=session;
        this.appid=appid;
        this.imei=imei;
        this.regKey=regKey;
        this.version=version;
        this.serialNo=serialNo;
        this.loginTypeID=loginTypeID;
    }
}
