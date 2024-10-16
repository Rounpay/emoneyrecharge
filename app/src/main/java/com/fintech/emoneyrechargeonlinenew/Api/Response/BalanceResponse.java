package com.fintech.emoneyrechargeonlinenew.Api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.fintech.emoneyrechargeonlinenew.Api.Object.BalanceData;

public class BalanceResponse {
    @SerializedName("data")
    @Expose
    public BalanceData balanceData;
    @SerializedName("statuscode")
    @Expose
    public Integer statuscode;
    @SerializedName("msg")
    @Expose
    public String msg;
    @SerializedName("isVersionValid")
    @Expose
    public Boolean isVersionValid;
    @SerializedName("isAppValid")
    @Expose
    public Boolean isAppValid;
    @SerializedName("checkID")
    @Expose
    public Integer checkID;
    @SerializedName("isPasswordExpired")
    @Expose
    private boolean isPasswordExpired;
    @SerializedName("isLookUpFromAPI")
    @Expose
    private boolean isLookUpFromAPI;
    @SerializedName("isShowPDFPlan")
    @Expose
    public boolean isShowPDFPlan;

    @SerializedName("isDTHInfoCall")
    @Expose
    public boolean isDTHInfoCall;

    @SerializedName("isDTHInfo")
    @Expose
    public boolean isDTHInfo;

    @SerializedName("isRoffer")
    @Expose
    public boolean isRoffer;

    @SerializedName("popup")
    @Expose
    private String popup;
    @SerializedName("isMoveToPrepaid")
    @Expose
    private boolean isMoveToPrepaid;
    @SerializedName("isMoveToUtility")
    @Expose
    private boolean isMoveToUtility;
    @SerializedName("isMoveToBank")
    @Expose
    private boolean isMoveToBank;
    @SerializedName("isFlatCommission")
    @Expose
    private boolean isFlatCommission;
    @SerializedName("activeFlatType")
    @Expose
    private Integer activeFlatType;
    @SerializedName("isReferral")
    @Expose
    private boolean isReferral;
    @SerializedName("isBulkQRGeneration")
    @Expose
    public boolean isBulkQRGeneration;
    @SerializedName("isSattlemntAccountVerify")
    @Expose
    private boolean isSattlemntAccountVerify;

    @SerializedName("isDrawOpImage")
    @Expose
    public boolean isDrawOpImage;

    public boolean isDrawOpImage() {
        return isDrawOpImage;
    }
    public boolean isSattlemntAccountVerify() {
        return isSattlemntAccountVerify;
    }

    public boolean isBulkQRGeneration() {
        return isBulkQRGeneration;
    }

    public boolean isMoveToPrepaid() {
        return isMoveToPrepaid;
    }

    public boolean isMoveToUtility() {
        return isMoveToUtility;
    }

    public boolean isMoveToBank() {
        return isMoveToBank;
    }

    public boolean isFlatCommission() {
        return isFlatCommission;
    }

    public Integer getActiveFlatType() {
        return activeFlatType;
    }

    public boolean isReferral() {
        return isReferral;
    }

    public boolean isShowPDFPlan() {
        return isShowPDFPlan;
    }

    public boolean isDTHInfoCall() {
        return isDTHInfoCall;
    }

    public boolean isDTHInfo() {
        return isDTHInfo;
    }

    public boolean isRoffer() {
        return isRoffer;
    }

    public String getPopup() {
        return popup;
    }

    public BalanceData getBalanceData() {
        return balanceData;
    }

    public Integer getStatuscode() {
        return statuscode;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getVersionValid() {
        return isVersionValid;
    }

    public Boolean getAppValid() {
        return isAppValid;
    }

    public Integer getCheckID() {
        return checkID;
    }

    public boolean getIsPasswordExpired() {
        return isPasswordExpired;
    }

    public boolean isPasswordExpired() {
        return isPasswordExpired;
    }

    public boolean isLookUpFromAPI() {
        return isLookUpFromAPI;
    }
}
