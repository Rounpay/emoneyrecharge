package com.fintech.emoneyrechargeonlinenew.MoveToWallet.Api;

public class MoveToBankReportRequest {
    private int topRows;
    private int status;
    private int oid;
    private String fromDate;
    private String toDate;
    private String childMobile;
    private String transactionId;
    private String userID;
    private String sessionID;
    private String session;
    private String appid;
    private String imei;
    private String regKey;
    private String version;
    private String serialNo;
    private String loginTypeID;



    public MoveToBankReportRequest(int topRows, int status, int oid, String fromDate, String toDate,
                                   String transactionId, String childMobile, String userID,
                                   String sessionID, String session, String appid, String imei, String regKey, String version, String serialNo, String loginTypeID) {
        this.topRows = topRows;
        this.status = status;
        this.oid = oid;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.transactionId = transactionId;
        this.childMobile = childMobile;
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
