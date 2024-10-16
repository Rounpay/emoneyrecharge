package com.fintech.emoneyrechargeonlinenew.Fragments;

import com.fintech.emoneyrechargeonlinenew.Api.Object.BcResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SdkDetail implements Serializable {

    @SerializedName("apiOutletID")
    @Expose
    private String apiOutletID;
    @SerializedName("apiOutletPassword")
    @Expose
    private String apiOutletPassword;
    @SerializedName("apiPartnerID")
    @Expose
    private String apiPartnerID;
    @SerializedName("apiOutletMob")
    @Expose
    private String apiOutletMob;

    @SerializedName("bcResponse")
    @Expose
    private List<BcResponse> bcResponse = null;

    public List<BcResponse> getBcResponse() {
        return bcResponse;
    }


    public String getApiOutletID() {
        return apiOutletID;
    }

    public void setApiOutletID(String apiOutletID) {
        this.apiOutletID = apiOutletID;
    }

    public String getApiOutletPassword() {
        return apiOutletPassword;
    }

    public void setApiOutletPassword(String apiOutletPassword) {
        this.apiOutletPassword = apiOutletPassword;
    }

    public String getApiPartnerID() {
        return apiPartnerID;
    }

    public void setApiPartnerID(String apiPartnerID) {
        this.apiPartnerID = apiPartnerID;
    }

    public String getApiOutletMob() {
        return apiOutletMob;
    }

    public void setApiOutletMob(String apiOutletMob) {
        this.apiOutletMob = apiOutletMob;
    }

}
