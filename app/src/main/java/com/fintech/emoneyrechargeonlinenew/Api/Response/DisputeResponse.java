package com.fintech.emoneyrechargeonlinenew.Api.Response;

import com.fintech.emoneyrechargeonlinenew.Api.Object.Dispute;

import java.util.ArrayList;

/**
 * Created by Lalit on 10-04-2017.
 */

public class DisputeResponse {

    private String RESPONSESTATUS;
    private String message;
    private ArrayList<com.fintech.emoneyrechargeonlinenew.Api.Object.Dispute> Dispute;

    public ArrayList<Dispute> getDispute() {
        return Dispute;
    }

    public void setDispute(ArrayList<Dispute> dispute) {
        Dispute = dispute;
    }

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
}
