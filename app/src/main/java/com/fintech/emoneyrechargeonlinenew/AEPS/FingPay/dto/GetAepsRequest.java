package com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.dto;

public class GetAepsRequest {
    PidData pidData;
    String aadhar, amount;
    int interfaceType, bankIIN;
    private String userID, bankName;
    private String loginTypeID;
    private String appid;
    private String imei;
    private String regKey;
    private String version;
    private String serialNo;
    private String sessionID;
    private String session;


    public GetAepsRequest(PidData pidData, String aadhar, int interfaceType, int bankIIN, String userID, String loginTypeID, String appid, String imei, String regKey, String version, String serialNo, String sessionID, String session) {
        this.pidData = pidData;
        this.aadhar = aadhar;
        this.interfaceType = interfaceType;
        this.bankIIN = bankIIN;
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

    public GetAepsRequest(PidData pidData, String aadhar, int interfaceType, int bankIIN, String bankName, String userID, String loginTypeID, String appid, String imei, String regKey, String version, String serialNo, String sessionID, String session) {
        this.pidData = pidData;
        this.aadhar = aadhar;
        this.interfaceType = interfaceType;
        this.bankIIN = bankIIN;
        this.bankName = bankName;
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

    public GetAepsRequest(PidData pidData, String aadhar, String amount, int interfaceType, int bankIIN, String userID, String loginTypeID, String appid, String imei, String regKey, String version, String serialNo, String sessionID, String session) {
        this.pidData = pidData;
        this.aadhar = aadhar;
        this.amount = amount;
        this.interfaceType = interfaceType;
        this.bankIIN = bankIIN;
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
