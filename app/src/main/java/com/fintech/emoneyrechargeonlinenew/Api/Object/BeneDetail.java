package com.fintech.emoneyrechargeonlinenew.Api.Object;

public class BeneDetail {
    private String mobileNo;
    private String beneName;
    private String ifsc;
    private String accountNo;
    private String bankID;
    private String bankName;
    private String beneID;
    private int transMode;

    public BeneDetail(String mobileNo, String beneName, String ifsc, String accountNo, String bankName, String bankID) {
        this.mobileNo = mobileNo;
        this.beneName = beneName;
        this.ifsc = ifsc;
        this.accountNo = accountNo;
        this.bankName = bankName;
        this.bankID = bankID;
    }
    public BeneDetail(String mobileNo, String beneName, String ifsc, String accountNo, String bankName) {
        this.mobileNo = mobileNo;
        this.beneName = beneName;
        this.ifsc = ifsc;
        this.accountNo = accountNo;
        this.bankName = bankName;

    }

    public BeneDetail(String mobileNo, String beneName, String ifsc, String accountNo, String bankName, String bankID, int transMode) {
        this.mobileNo = mobileNo;
        this.beneName = beneName;
        this.ifsc = ifsc;
        this.accountNo = accountNo;
        this.bankName = bankName;
        this.bankID = bankID;
        this.transMode = transMode;
    }

    public BeneDetail(String mobileNo, String ifsc, String accountNo, String bankName) {
        this.mobileNo = mobileNo;
        this.ifsc = ifsc;
        this.accountNo = accountNo;
        this.bankName = bankName;
    }

    public BeneDetail(String beneID) {

        this.beneID = beneID;
    }

}
