package com.fintech.emoneyrechargeonlinenew.Api.Response;

import com.fintech.emoneyrechargeonlinenew.Api.Object.DMRTransactions;

import java.util.ArrayList;



public class DMRReportResponse {

    private String RESPONSESTATUS;
    private String message;
    private ArrayList<com.fintech.emoneyrechargeonlinenew.Api.Object.DMRTransactions> DMRTransactions;

    public String getRESPONSESTATUS() {
        return RESPONSESTATUS;
    }

    public void setRESPONSESTATUS(String RESPONSESTATUS) {
        this.RESPONSESTATUS = RESPONSESTATUS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<DMRTransactions> getDMRTransactions() {
        return DMRTransactions;
    }

    public void setDMRTransactions(ArrayList<DMRTransactions> DMRTransactions) {
        this.DMRTransactions = DMRTransactions;
    }
}
