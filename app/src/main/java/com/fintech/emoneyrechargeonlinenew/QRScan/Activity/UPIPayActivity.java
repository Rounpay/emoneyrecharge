package com.fintech.emoneyrechargeonlinenew.QRScan.Activity;

import static com.google.android.material.textfield.TextInputLayout.END_ICON_NONE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fintech.emoneyrechargeonlinenew.Activities.Adapter.BalanceTypeAdapter;
import com.fintech.emoneyrechargeonlinenew.Api.Object.BalanceType;
import com.fintech.emoneyrechargeonlinenew.Api.Response.BalanceResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.RechargeReportResponse;
import com.fintech.emoneyrechargeonlinenew.Auth.dto.LoginResponse;
import com.fintech.emoneyrechargeonlinenew.BuildConfig;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.ReqSendMoney;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.UPIPaymentReq;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.VPAListUPIPaymentRequest;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.VPAVerifyResponse;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ApiClient;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;
import com.fintech.emoneyrechargeonlinenew.Util.EndPointInterface;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;


public class UPIPayActivity extends AppCompatActivity implements View.OnClickListener{

    private String vpaVal,nameVal;
    private boolean isVerified;
    private AppCompatTextView securityKeyText;
    private TextInputLayout vpaTxt,amountTxt,beneNameTxt,pinPasswordTxt;
    private TextInputEditText vpaEdt,amountEdt,beneNamEdt,pinPassEt;
    private MaterialButton upiPayBtn;
    private Button verifyVpaBtn;
    private CustomLoader loader;
    private BalanceResponse balanceCheckResponse;
    private ArrayList<BalanceType> mBalanceTypes=new ArrayList<>();
    private RecyclerView walletBalance;
    private LoginResponse mLoginDataResponse;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_p_i_pay);
        if(getIntent()!=null){
            vpaVal=getIntent().getStringExtra("VPA");
            nameVal=getIntent().getStringExtra("beneName");
            isVerified=getIntent().getBooleanExtra("verified",false);
        }
        mLoginDataResponse = new Gson().fromJson(UtilMethods.INSTANCE.getLoginPref(UPIPayActivity.this), LoginResponse.class);
        loader = new CustomLoader(this, android.R.style.Theme_Translucent_NoTitleBar);
        upiPayBtn=findViewById(R.id.btn_upiPay);
        walletBalance =findViewById(R.id.walletBalance);
        walletBalance.setLayoutManager(new LinearLayoutManager(this));
        vpaTxt=findViewById(R.id.txt_vpa);
        vpaEdt=findViewById(R.id.edit_vpa);
        amountEdt=findViewById(R.id.edit_amount);
        beneNamEdt=findViewById(R.id.edit_beneName);
        securityKeyText = findViewById(R.id.securityKeyText);
        pinPassEt = findViewById(R.id.edit_pinPassword);
        amountTxt = findViewById(R.id.txt_amount);
        beneNameTxt = findViewById(R.id.txt_beneName);
        pinPasswordTxt = findViewById(R.id.txt_pinPassword);
        verifyVpaBtn = findViewById(R.id.verifyVpaBtn);
        verifyVpaBtn.setVisibility(View.GONE);

        Toolbar toolbar =findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("UPI Payment");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (UtilMethods.INSTANCE.getPinRequiredPref(this) || UtilMethods.INSTANCE.getDoubleFactorPref(this)) {
            securityKeyText.setVisibility(View.VISIBLE);
            pinPassEt.setVisibility(View.VISIBLE);
        } else {
            pinPassEt.setVisibility(View.GONE);
            securityKeyText.setVisibility(View.GONE);
        }

        if(!isVerified){
            verifyVpaBtn.setVisibility(View.VISIBLE);
        }else{
            verifyVpaBtn.setVisibility(View.GONE);
        }

        if(vpaVal!=null && !vpaVal.isEmpty())
        {
            vpaEdt.setText(vpaVal);
            vpaEdt.setEnabled(false);
            vpaTxt.setEndIconMode(END_ICON_NONE);
        }

        if(nameVal!=null && !nameVal.isEmpty())
        {
            beneNamEdt.setText(nameVal);
            beneNamEdt.setEnabled(true);
            beneNameTxt.setEndIconMode(END_ICON_NONE);
        }

        vpaEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence editText, int start, int before, int count) {
                beneNamEdt.setText("");
                beneNamEdt.setError(null);
                verifyVpaBtn.setVisibility(View.VISIBLE);
                /*upiPayBtn.setVisibility(View.GONE);*/
                if(editText.toString().isEmpty())
                {
                    vpaTxt.setError(getResources().getString(R.string.err_empty_field));
                }else if(!UtilMethods.INSTANCE.validateUPI(vpaEdt.getText().toString())){
                    vpaTxt.setError(getResources().getString(R.string.err_vpa_invalid));
                }else{
                    vpaEdt.setError(null);
                    vpaTxt.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                
            }
        });

        amountEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence editText, int start, int before, int count) {
                if(editText.toString().isEmpty())
                {
                    amountTxt.setError(getResources().getString(R.string.err_empty_field));
                    //vpaEdt.requestFocus();
                }else{
                    amountEdt.setError(null);
                    amountTxt.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //vpaTxt.setErrorEnabled(false);
            }
        });

        beneNamEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence editText, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editText) {
                if(beneNamEdt.toString().isEmpty())
                {
                    beneNameTxt.setError(getResources().getString(R.string.err_empty_field));
                }else{
                    beneNamEdt.setError(null);
                    beneNameTxt.setErrorEnabled(false);
                }
            }
        });

        pinPassEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence editText, int start, int before, int count) {
                if(editText.toString().isEmpty())
                {
                    pinPasswordTxt.setError(getResources().getString(R.string.err_empty_field));
                }else{
                    pinPassEt.setError(null);
                    pinPasswordTxt.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //vpaTxt.setErrorEnabled(false);
            }
        });

        upiPayBtn.setOnClickListener(this::onClick);
        verifyVpaBtn.setOnClickListener(this::onClick);

        balanceCheckResponse = (BalanceResponse) getIntent().getSerializableExtra("BalanceData");
        showWalletListPopupWindow();
    }

    private void showWalletListPopupWindow() {

        if (balanceCheckResponse != null) {
            mBalanceTypes.clear();
            if (balanceCheckResponse.getBalanceData().isBalance() && balanceCheckResponse.getBalanceData().isBalanceFund()) {
                mBalanceTypes.add(new BalanceType("Prepaid Wallet", UtilMethods.INSTANCE.formatedAmount(balanceCheckResponse.getBalanceData().getBalance() + "")));
            }
            if (balanceCheckResponse.getBalanceData().isUBalance() && balanceCheckResponse.getBalanceData().isUBalanceFund()) {
                mBalanceTypes.add(new BalanceType("Utility Wallet", UtilMethods.INSTANCE.formatedAmount(balanceCheckResponse.getBalanceData().getuBalance() + "")));

            }
            if (balanceCheckResponse.getBalanceData().isBBalance() && balanceCheckResponse.getBalanceData().isBBalanceFund()) {
                mBalanceTypes.add(new BalanceType("Bank Wallet", UtilMethods.INSTANCE.formatedAmount(balanceCheckResponse.getBalanceData().getbBalance() + "")));
                //  isBankWalletActive = true;
            }
            if (balanceCheckResponse.getBalanceData().isCBalance() && balanceCheckResponse.getBalanceData().isCBalanceFund()) {
                mBalanceTypes.add(new BalanceType("Card Wallet", UtilMethods.INSTANCE.formatedAmount(balanceCheckResponse.getBalanceData().getcBalance() + "")));
            }
            if (balanceCheckResponse.getBalanceData().isIDBalance() && balanceCheckResponse.getBalanceData().isIDBalanceFund()) {
                mBalanceTypes.add(new BalanceType("Registration Wallet", UtilMethods.INSTANCE.formatedAmount(balanceCheckResponse.getBalanceData().getIdBalnace() + "")));
            }

            if (balanceCheckResponse.getBalanceData().isPacakgeBalance() && balanceCheckResponse.getBalanceData().isPacakgeBalanceFund()) {
                mBalanceTypes.add(new BalanceType("Package Wallet", UtilMethods.INSTANCE.formatedAmount(balanceCheckResponse.getBalanceData().getPacakgeBalance() + "")));
            }
            if (mLoginDataResponse.isAccountStatement()) {
                mBalanceTypes.add(new BalanceType("Outstanding Wallet", balanceCheckResponse.getBalanceData().getOsBalance()+""));
            }
            if (mBalanceTypes != null && mBalanceTypes.size() > 0) {
                BalanceTypeAdapter mAdapter = new BalanceTypeAdapter(mBalanceTypes, this);
                walletBalance.setAdapter(mAdapter);
            }
        } else {
            SharedPreferences myPreferences = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
            String balanceResponse = myPreferences.getString(ApplicationConstant.INSTANCE.balancePref, "");
            balanceCheckResponse = new Gson().fromJson(balanceResponse, BalanceResponse.class);
            if (balanceCheckResponse != null) {
                showWalletListPopupWindow();
            }
            return;
        }

    }

    @Override
    public void onClick(View clickView) {

        if(clickView==upiPayBtn){
            if(validView()){
                doUPIPayment(this);
            }
        }else if(clickView==verifyVpaBtn){
            if(vpaEdt.getText().toString().isEmpty())
            {
                vpaTxt.setError(getResources().getString(R.string.err_empty_field));
                vpaEdt.requestFocus();
            }else if(!UtilMethods.INSTANCE.validateUPI(vpaEdt.getText().toString())){
                vpaTxt.setError(getResources().getString(R.string.err_vpa_invalid));
                vpaEdt.requestFocus();
            }else{
                vpaTxt.setErrorEnabled(false);
                verifyVPA(this);
            }
        }
    }

    private void verifyVPA(final Activity context) {

        try {
            loader.show();
            VPAListUPIPaymentRequest paymentReq=new VPAListUPIPaymentRequest(
                    vpaEdt.getText().toString(),
                    mLoginDataResponse.getData().getUserID() + "",
                    mLoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(UPIPayActivity.this),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(UPIPayActivity.this),mLoginDataResponse.getData().getSessionID(),mLoginDataResponse.getData().getSession());

            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<VPAVerifyResponse> call = git.VerifyUPI(paymentReq);

            call.enqueue(new Callback<VPAVerifyResponse>() {

                @Override
                public void onResponse(Call<VPAVerifyResponse> call, retrofit2.Response<VPAVerifyResponse> response) {

                    if (loader != null && loader.isShowing())
                        loader.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {

                            if (response.body().getStatuscode()==1) {

                                if(response.body().getData()!=null && response.body().getData().getStatuscode()==2){
                                    UtilMethods.INSTANCE.Successful(context,response.body().getMsg());
                                    verifyVpaBtn.setVisibility(View.GONE);
                                    upiPayBtn.setVisibility(View.VISIBLE);
                                    if(response.body().getData().getAccountHolder()!=null && !response.body().getData().getAccountHolder().isEmpty()){
                                        beneNamEdt.setText(response.body().getData().getAccountHolder());
                                        beneNamEdt.setEnabled(false);
                                        beneNameTxt.setEndIconMode(END_ICON_NONE);
                                    }
                                }else if(response.body().getData()!=null && response.body().getData().getStatuscode()==1){
                                    verifyVpaBtn.setVisibility(View.VISIBLE);
                                    UtilMethods.INSTANCE.Processing(context,response.body().getData().getMsg()+"");
                                    /*upiPayBtn.setVisibility(View.GONE);*/
                                }else if(response.body().getData()!=null && response.body().getData().getStatuscode()==-1){

                                    verifyVpaBtn.setVisibility(View.VISIBLE);
                                    UtilMethods.INSTANCE.Error(context,response.body().getData().getMsg()+"");
                                    /*upiPayBtn.setVisibility(View.GONE);*/
                                }else{

                                    verifyVpaBtn.setVisibility(View.VISIBLE);
                                    UtilMethods.INSTANCE.Error(context,response.body().getData().getMsg()+"");
                                    /*upiPayBtn.setVisibility(View.GONE);*/
                                }
                            }else if (response.body().getStatuscode()==3) {

                                UtilMethods.INSTANCE.Error(context,response.body().getMsg()+"");
                                /*upiPayBtn.setVisibility(View.GONE);*/

                            } else {
                                if (!response.body().isVersionValid()) {
                                    UtilMethods.INSTANCE.versionDialog(context);
                                } else {
                                    UtilMethods.INSTANCE.Error(context, response.body().getMsg() + "");
                                }
                                /*upiPayBtn.setVisibility(View.GONE);*/
                            }

                        }
                    } else {
                        /*upiPayBtn.setVisibility(View.GONE);*/
                        UtilMethods.INSTANCE.apiErrorHandle(context, response.code(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<VPAVerifyResponse> call, Throwable t) {
                    /*upiPayBtn.setVisibility(View.GONE);*/
                    if (loader != null && loader.isShowing())
                        loader.dismiss();

                    try {
                        UtilMethods.INSTANCE.apiFailureError(context, t);
                    } catch (IllegalStateException ise) {
                        UtilMethods.INSTANCE.Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            /*upiPayBtn.setVisibility(View.GONE);*/
            if (loader != null && loader.isShowing())
                loader.dismiss();
        }
    }


    public void doUPIPayment(final Activity context) {
        try {
            loader.show();
            UPIPaymentReq paymentReq=new UPIPaymentReq(new ReqSendMoney(vpaEdt.getText().toString().trim(), amountEdt.getText().toString(),beneNamEdt.getText().toString(),mLoginDataResponse.getData().getMobileNo()),pinPassEt.getText().toString(), mLoginDataResponse.getData().getUserID() + "", mLoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(UPIPayActivity.this),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(UPIPayActivity.this),mLoginDataResponse.getData().getSession(),mLoginDataResponse.getData().getSessionID());

            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.doUPIPayment(paymentReq);

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {

                    if (loader != null && loader.isShowing())
                        loader.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {

                            if (response.body().getStatuscode() .equalsIgnoreCase("1")|| response.body().getStatuscode() .equalsIgnoreCase("2")) {
                                vpaEdt.setText("");
                                amountEdt.setText("");
                                pinPassEt.setText("");
                                beneNamEdt.setText("");
                                UtilMethods.INSTANCE.Successful(context,response.body().getMsg());
                                setResult(RESULT_OK);

                            }else if (response.body().getStatuscode() .equalsIgnoreCase("3")) {

                                UtilMethods.INSTANCE.Error(context,response.body().getMsg()+"");

                            } else {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    UtilMethods.INSTANCE.versionDialog(context);
                                } else {
                                    UtilMethods.INSTANCE.Error(context, response.body().getMsg() + "");
                                }
                            }

                        }
                    } else {
                        UtilMethods.INSTANCE.apiErrorHandle(context, response.code(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {

                    if (loader != null && loader.isShowing())
                        loader.dismiss();

                    try {
                        UtilMethods.INSTANCE.apiFailureError(context, t);
                    } catch (IllegalStateException ise) {
                        UtilMethods.INSTANCE.Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader != null && loader.isShowing())
                loader.dismiss();
        }

    }

    private boolean validView() {
        if(vpaEdt.getText().toString().isEmpty())
        {
            vpaTxt.setError(getResources().getString(R.string.err_empty_field));
            vpaEdt.requestFocus();
            return false;
        }else if(!UtilMethods.INSTANCE.validateUPI(vpaEdt.getText().toString())){
            vpaTxt.setError(getResources().getString(R.string.err_vpa_invalid));
            vpaEdt.requestFocus();
            return false;
        }else if(beneNamEdt.getText().toString().isEmpty())
        {
            beneNameTxt.setError(getResources().getString(R.string.err_empty_field));
            beneNamEdt.requestFocus();
            return false;
        }else if(amountEdt.getText().toString().isEmpty())
        {
            amountTxt.setError(getResources().getString(R.string.err_empty_field));
            amountEdt.requestFocus();
            return false;
        }else if (pinPassEt.getVisibility() == View.VISIBLE && pinPassEt.getText().toString().isEmpty()) {
            pinPasswordTxt.setError(getString(R.string.err_empty_field));
            pinPassEt.requestFocus();
            return false;
        }
        vpaEdt.setError(null);
        beneNamEdt.setError(null);
        amountEdt.setError(null);
        pinPassEt.setError(null);
        vpaTxt.setErrorEnabled(false);
        beneNameTxt.setErrorEnabled(false);
        amountTxt.setErrorEnabled(false);
        pinPasswordTxt.setErrorEnabled(false);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}