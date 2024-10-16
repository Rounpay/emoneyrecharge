package com.fintech.emoneyrechargeonlinenew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BalanceData implements Serializable {

    @SerializedName("isPackageDeducionForRetailor")
    @Expose
    public boolean isPackageDeducionForRetailor;
    @SerializedName("balance")
    @Expose
    public Double balance;
    @SerializedName("isBalance")
    @Expose
    public boolean isBalance;
    @SerializedName("uBalance")
    @Expose
    public Double uBalance;
    @SerializedName("isUBalance")
    @Expose
    public boolean isUBalance;
    @SerializedName("bBalance")
    @Expose
    public Double bBalance;
    @SerializedName("isBBalance")
    @Expose
    public boolean isBBalance;
    @SerializedName("cBalance")
    @Expose
    public Double cBalance;
    @SerializedName("isCBalance")
    @Expose
    public boolean isCBalance;
    @SerializedName("idBalnace")
    @Expose
    public Double idBalnace;
    @SerializedName("isIDBalance")
    @Expose
    public boolean isIDBalance;
    @SerializedName("aepsBalnace")
    @Expose
    public Double aepsBalnace;
    @SerializedName("isAEPSBalance")
    @Expose
    public boolean isAEPSBalance;
    @SerializedName("packageBalnace")
    @Expose
    public Double packageBalnace;
    @SerializedName("isPacakgeBalance")
    @Expose
    public boolean isPacakgeBalance;
    @SerializedName("isP")
    @Expose
    public boolean isP;
    @SerializedName("isPN")
    @Expose
    public boolean isPN;
    @SerializedName("isAEPSBalanceFund")
    @Expose
    public boolean isAEPSBalanceFund;

    @SerializedName("isBalanceFund")
    @Expose
    public boolean isBalanceFund;
    @SerializedName("uCapping")
    @Expose
    public double uCapping;
    @SerializedName("isUBalanceFund")
    @Expose
    public boolean isUBalanceFund;
    @SerializedName("bbCapping")
    @Expose
    public double bbCapping;
    @SerializedName("isBBalanceFund")
    @Expose
    public boolean isBBalanceFund;
    @SerializedName("cCapping")
    @Expose
    public double cCapping;
    @SerializedName("isCBalanceFund")
    @Expose
    public boolean isCBalanceFund;
    @SerializedName("idCapping")
    @Expose
    public double idCapping;
    @SerializedName("isIDBalanceFund")
    @Expose
    public boolean isIDBalanceFund;
    @SerializedName("pacakgeBalance")
    @Expose
    public double pacakgeBalance;
    @SerializedName("packageCapping")
    @Expose
    public double packageCapping;
    @SerializedName("isPacakgeBalanceFund")
    @Expose
    public boolean isPacakgeBalanceFund;
    @SerializedName("isLowBalance")
    @Expose
    public boolean isLowBalance;
    @SerializedName("commRate")
    @Expose
    public double commRate;
    @SerializedName("isQRMappedToUser")
    @Expose
    public boolean isQRMappedToUser;
    @SerializedName("osBalance")
    @Expose
    public double osBalance;

    public double getOsBalance() {
        return osBalance;
    }

    public boolean isQRMappedToUser() {
        return isQRMappedToUser;
    }

    public boolean isPackageDeducionForRetailor() {
        return isPackageDeducionForRetailor;
    }

    public Double getBalance() {
        return balance;
    }

    public boolean isBalance() {
        return isBalance;
    }

    public Double getuBalance() {
        return uBalance;
    }

    public boolean isUBalance() {
        return isUBalance;
    }

    public Double getbBalance() {
        return bBalance;
    }

    public boolean isBBalance() {
        return isBBalance;
    }

    public Double getcBalance() {
        return cBalance;
    }

    public boolean isCBalance() {
        return isCBalance;
    }

    public Double getIdBalnace() {
        return idBalnace;
    }

    public boolean isIDBalance() {
        return isIDBalance;
    }

    public Double getAepsBalnace() {
        return aepsBalnace;
    }

    public boolean isAEPSBalance() {
        return isAEPSBalance;
    }

    public Double getPackageBalnace() {
        return packageBalnace;
    }

    public boolean isPacakgeBalance() {
        return isPacakgeBalance;
    }

    public boolean isP() {
        return isP;
    }

    public boolean isPN() {
        return isPN;
    }

    public boolean isAEPSBalanceFund() {
        return isAEPSBalanceFund;
    }

    public boolean isBalanceFund() {
        return isBalanceFund;
    }

    public double getuCapping() {
        return uCapping;
    }

    public boolean isUBalanceFund() {
        return isUBalanceFund;
    }

    public double getBbCapping() {
        return bbCapping;
    }

    public boolean isBBalanceFund() {
        return isBBalanceFund;
    }

    public double getcCapping() {
        return cCapping;
    }

    public boolean isCBalanceFund() {
        return isCBalanceFund;
    }

    public double getIdCapping() {
        return idCapping;
    }

    public boolean isIDBalanceFund() {
        return isIDBalanceFund;
    }

    public double getPacakgeBalance() {
        return pacakgeBalance;
    }

    public double getPackageCapping() {
        return packageCapping;
    }

    public boolean isPacakgeBalanceFund() {
        return isPacakgeBalanceFund;
    }

    public boolean isLowBalance() {
        return isLowBalance;
    }

    public double getCommRate() {
        return commRate;
    }
}
