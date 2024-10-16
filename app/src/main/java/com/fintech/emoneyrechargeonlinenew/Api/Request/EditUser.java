package com.fintech.emoneyrechargeonlinenew.Api.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditUser {
    @SerializedName("commRate")
    @Expose
    private Integer commRate;
    @SerializedName("aadhar")
    @Expose
    private Object aadhar;
    @SerializedName("pan")
    @Expose
    private Object pan;
    @SerializedName("gstin")
    @Expose
    private Object gstin;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("userID")
    @Expose
    private Integer userID;
    @SerializedName("mobileNo")
    @Expose
    private Object mobileNo;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("outletName")
    @Expose
    private Object outletName;
    @SerializedName("emailID")
    @Expose
    private Object emailID;
    @SerializedName("isGSTApplicable")
    @Expose
    private Boolean isGSTApplicable;
    @SerializedName("isTDSApplicable")
    @Expose
    private Boolean isTDSApplicable;
    @SerializedName("isCCFGstApplicable")
    @Expose
    private Boolean isCCFGstApplicable;
    @SerializedName("pincode")
    @Expose
    private Object pincode;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("shoptype")
    @Expose
    private String shoptype;
    @SerializedName("qualification")
    @Expose
    private String qualification;
    @SerializedName("poupulation")
    @Expose
    private String poupulation;
    @SerializedName("locationType")
    @Expose
    private String locationType;
    @SerializedName("landmark")
    @Expose
    private String landmark;
    @SerializedName("alternateMobile")
    @Expose
    private String alternateMobile;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("ifsc")
    @Expose
    private String ifsc;
    @SerializedName("branchName")
    @Expose
    private String branchName;
    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("accountName")
    @Expose
    private String accountName;
    @SerializedName("profilePic")
    @Expose
    public String profilePic;


   /* @SerializedName("commRate")
    @Expose
    public Integer commRate;
    @SerializedName("profilePic")
    @Expose
    public String profilePic;
    @SerializedName("aadhar")
    @Expose
    public String aadhar;
    @SerializedName("pan")
    @Expose
    public String pan;
    @SerializedName("gstin")
    @Expose
    public String gstin;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("userID")
    @Expose
    public Integer userID;
    @SerializedName("mobileNo")
    @Expose
    public String mobileNo;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("outletName")
    @Expose
    public String outletName;
    @SerializedName("emailID")
    @Expose
    public String emailID;
    @SerializedName("isGSTApplicable")
    @Expose
    public Boolean isGSTApplicable;
    @SerializedName("isTDSApplicable")
    @Expose
    public Boolean isTDSApplicable;
    @SerializedName("isCCFGstApplicable")
    @Expose
    public Boolean isCCFGstApplicable;
    @SerializedName("pincode")
    @Expose
    public String pincode;*/

    public EditUser(Integer commRate, String profilePic, String aadhar, String pan, String gstin, String address, Integer userID,
                    String mobileNo, String name, String outletName, String emailID, Boolean isGSTApplicable, Boolean isTDSApplicable,
                    Boolean isCCFGstApplicable, String pincode, String dob, String shoptype, String qualification, String poupulation,
                    String locationType, String landmark, String alternateMobile, String bankName, String ifsc, String branchName, String accountNumber,
                    String accountName) {
        this.commRate = commRate;
        this.profilePic = profilePic;
        this.aadhar = aadhar;
        this.pan = pan;
        this.gstin = gstin;
        this.address = address;
        this.userID = userID;
        this.mobileNo = mobileNo;
        this.name = name;
        this.outletName = outletName;
        this.emailID = emailID;
        this.isGSTApplicable = isGSTApplicable;
        this.isTDSApplicable = isTDSApplicable;
        this.isCCFGstApplicable = isCCFGstApplicable;
        this.pincode = pincode;
        this.dob = dob;
        this.shoptype = shoptype;
        this.qualification = qualification;
        this.poupulation = poupulation;
        this.locationType = locationType;
        this.poupulation = poupulation;
        this.landmark = landmark;
        this.alternateMobile = alternateMobile;
        this.bankName = bankName;
        this.ifsc = ifsc;
        this.branchName = branchName;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
    }

    public EditUser(String selectedBank, String acctNo, String acctName, String branchName, String ifsc) {
        this.bankName=selectedBank;
        this.accountNumber=acctNo;
        this.accountName=acctName;
        this.branchName=branchName;
        this.ifsc=ifsc;
    }
}