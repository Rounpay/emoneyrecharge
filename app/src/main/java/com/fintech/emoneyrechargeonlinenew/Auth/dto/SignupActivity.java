package com.fintech.emoneyrechargeonlinenew.Auth.dto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.fintech.emoneyrechargeonlinenew.Api.Object.UserCreateSignup;
import com.fintech.emoneyrechargeonlinenew.Api.Request.SignupRequest;
import com.fintech.emoneyrechargeonlinenew.Auth.LoginActivity;
import com.fintech.emoneyrechargeonlinenew.BuildConfig;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ApiClient;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;
import com.fintech.emoneyrechargeonlinenew.Util.CustomFilterDialogUtils.CustomFilterDialog;
import com.fintech.emoneyrechargeonlinenew.Util.EndPointInterface;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;

public class SignupActivity extends AppCompatActivity {

    private LinearLayout rr1;
    private LinearLayout llSignup;
    private TextInputLayout tilName;
    private AutoCompleteTextView etName;
    private TextInputLayout tilOutletName;
    private AutoCompleteTextView etOutletName;
    private TextInputLayout tilRoll;
    private AutoCompleteTextView etRoll;
    private TextInputLayout tilMobile;
    private AutoCompleteTextView etMobile;
    private TextInputLayout tilEmail;
    private AutoCompleteTextView etEmail;
    private TextInputLayout tilAddress;
    private AutoCompleteTextView etAddress;
    private TextInputLayout tilPincode;
    private AutoCompleteTextView etPincode;
    private Button btLogin;
    private TextView tvLogin;
    CustomLoader loader;
    private HashMap<String, Integer> rollsMap = new HashMap<>();
    private String[] rollArray = {"Sub Admin", "Master Distributor", "Distributor", "Retailer", "API User"};
    CustomFilterDialog mCustomFilterDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupnew);
        loader = new CustomLoader(this, android.R.style.Theme_Translucent_NoTitleBar);
        mCustomFilterDialog=new CustomFilterDialog(this);
        findViews();

        rollsMap.put("Sub Admin", 5);
        rollsMap.put("Master Distributor", 6);
        rollsMap.put("Distributor", 7);
        rollsMap.put("Retailer", 3);
        rollsMap.put("API User", 2);

    }

    private void findViews() {
        rr1 = (LinearLayout) findViewById(R.id.rr_1);
        llSignup = (LinearLayout) findViewById(R.id.ll_signup);
        tilName = (TextInputLayout) findViewById(R.id.til_name);
        etName = (AutoCompleteTextView) findViewById(R.id.et_name);
        tilOutletName = (TextInputLayout) findViewById(R.id.til_outletName);
        etOutletName = (AutoCompleteTextView) findViewById(R.id.et_outletName);
        tilRoll = (TextInputLayout) findViewById(R.id.til_rollid);
        etRoll = (AutoCompleteTextView) findViewById(R.id.et_roll);
        etRoll.setText("Retailer");
        tilMobile = (TextInputLayout) findViewById(R.id.til_mobile);
        etMobile = (AutoCompleteTextView) findViewById(R.id.et_mobile);
        tilEmail = (TextInputLayout) findViewById(R.id.til_email);
        etEmail = (AutoCompleteTextView) findViewById(R.id.et_email);
        tilAddress = (TextInputLayout) findViewById(R.id.til_address);
        etAddress = (AutoCompleteTextView) findViewById(R.id.et_address);
        tilPincode = (TextInputLayout) findViewById(R.id.til_pincode);
        etPincode = (AutoCompleteTextView) findViewById(R.id.et_pincode);
        btLogin = (Button) findViewById(R.id.bt_login);
        tvLogin = (TextView) findViewById(R.id.tv_login);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        etRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRoll();
            }
        });
    }


    void setRoll(){
        int selectedIndex = 0;
        if (etRoll.getText().toString().length() == 0) {
            selectedIndex = -1;
        } else {
            selectedIndex = Arrays.asList(rollArray).indexOf(etRoll.getText().toString());
        }
        mCustomFilterDialog.showSingleChoiceAlert(rollArray, selectedIndex, "Date Type", "Choose Date Type", new CustomFilterDialog.SingleChoiceDialogCallBack() {
            @Override
            public void onPositiveClick(int index) {
                etRoll.setText(rollArray[index]);
            }

            @Override
            public void onNegativeClick() {

            }
        });
    }

    void signup() {
        if (etName.getText().toString().isEmpty()) {
            tilName.setError(getString(R.string.err_empty_field));
            etName.requestFocus();
            return;
        } else if (etOutletName.getText().toString().isEmpty()) {
            tilOutletName.setError(getString(R.string.err_empty_field));
            etOutletName.requestFocus();
            return;
        } else if (etRoll.getText().toString().isEmpty()) {
            tilRoll.setError(getString(R.string.err_empty_field));
            etRoll.requestFocus();
            return;
        } else if (etMobile.getText().toString().isEmpty()) {
            tilMobile.setError(getString(R.string.err_empty_field));
            etMobile.requestFocus();
            return;
        } else if (etMobile.getText().toString().length() != 10) {
            tilMobile.setError(getString(R.string.err_msg_mobile_length));
            etMobile.requestFocus();
            return;
        } else if (etEmail.getText().toString().isEmpty()) {
            tilEmail.setError(getString(R.string.err_empty_field));
            etEmail.requestFocus();
            return;
        } else if (!etEmail.getText().toString().contains("@") || !etEmail.getText().toString().contains(".")) {
            tilEmail.setError(getString(R.string.err_msg_email));
            etEmail.requestFocus();
            return;
        } else if (etAddress.getText().toString().isEmpty()) {
            tilAddress.setError(getString(R.string.err_empty_field));
            etAddress.requestFocus();
            return;
        } else if (etPincode.getText().toString().isEmpty()) {
            tilPincode.setError(getString(R.string.err_empty_field));
            etPincode.requestFocus();
            return;
        } else if (etPincode.getText().toString().length() != 6) {
            tilPincode.setError(getString(R.string.pincode_error));
            etPincode.requestFocus();
            return;
        }

        Register(this);
    }


    public void Register(final Activity context) {
        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            UserCreateSignup mUserCreateSignup = new UserCreateSignup(etAddress.getText().toString(), etMobile.getText().toString(),
                    etName.getText().toString(), etOutletName.getText().toString(), etEmail.getText().toString(), rollsMap.get(etRoll.getText().toString()),
                    etPincode.getText().toString());
            Call<LoginResponse> call = git.AppUserSignup(new SignupRequest(
                    ApplicationConstant.INSTANCE.Domain,
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context), mUserCreateSignup));


            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                UtilMethods.INSTANCE.SuccessfulWithFinsh(context, response.body().getMsg() + "");
                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    UtilMethods.INSTANCE.versionDialog(context);
                                } else {
                                    UtilMethods.INSTANCE.Error(context, response.body().getMsg() + "");
                                }
                            }
                        }else{
                            UtilMethods.INSTANCE.Error(context, getString(R.string.some_thing_error) + "");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    if (t.getMessage().contains("No address associated with hostname")) {
                        UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.network_error));
                    } else {
                        UtilMethods.INSTANCE.Error(context, t.getMessage());
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

