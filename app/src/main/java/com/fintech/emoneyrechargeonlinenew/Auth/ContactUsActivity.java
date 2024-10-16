package com.fintech.emoneyrechargeonlinenew.Auth;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.fintech.emoneyrechargeonlinenew.Api.Request.ContactUsRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Response.BasicResponse;
import com.fintech.emoneyrechargeonlinenew.BuildConfig;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ApiClient;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;
import com.fintech.emoneyrechargeonlinenew.Util.EndPointInterface;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;

import retrofit2.Call;
import retrofit2.Callback;

public class ContactUsActivity extends AppCompatActivity {

    private TextView title;

    private TextInputLayout tilName;
    private AutoCompleteTextView etName;
    private TextInputLayout tilMobile;
    private AutoCompleteTextView etMobile;
    private TextInputLayout tilEmail;
    private AutoCompleteTextView etEmail;
    private TextInputLayout tilMsg;
    private AutoCompleteTextView etMsg;
    private Button btLogin;
    CustomLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        loader = new CustomLoader(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        findViews();
    }

    private void findViews() {

        title = findViewById(R.id.title);
        tilName = findViewById(R.id.til_name);
        etName = findViewById(R.id.et_name);
        tilMobile = findViewById(R.id.til_mobile);
        etMobile = findViewById(R.id.et_mobile);
        tilEmail = findViewById(R.id.til_email);
        etEmail = findViewById(R.id.et_email);
        tilMsg = findViewById(R.id.til_msg);
        etMsg = findViewById(R.id.et_msg);
        btLogin = findViewById(R.id.bt_login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

    }


    void submit() {
        if (etName.getText().toString().isEmpty()) {
            tilName.setError(getString(R.string.err_empty_field));
            etName.requestFocus();
            return;
        } else if (etMobile.getText().toString().isEmpty()) {
            tilMobile.setError(getString(R.string.err_empty_field));
            etMobile.requestFocus();
            return;
        } else if (etMobile.getText().toString().trim().length() != 10) {
            tilMobile.setError(getString(R.string.mobilenumber_error));
            etMobile.requestFocus();
            return;
        } else if (etEmail.getText().toString().isEmpty()) {
            tilEmail.setError(getString(R.string.err_empty_field));
            etEmail.requestFocus();
            return;
        } else if (!etEmail.getText().toString().contains("@") && !etEmail.getText().toString().contains(".")) {
            tilEmail.setError(getString(R.string.err_msg_email));
            etEmail.requestFocus();
            return;
        } else if (etMsg.getText().toString().isEmpty()) {
            tilMsg.setError(getString(R.string.err_empty_field));
            etMsg.requestFocus();
            return;
        }
        GetInTouch(this);
    }

    public void GetInTouch(final Activity context) {
        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BasicResponse> call = git.GetInTouch(new ContactUsRequest(
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context),
                    etMsg.getText().toString(), etEmail.getText().toString(), etMobile.getText().toString(), etName.getText().toString(),
                    "ContactPageApp"));
            call.enqueue(new Callback<BasicResponse>() {
                @Override
                public void onResponse(Call<BasicResponse> call, final retrofit2.Response<BasicResponse> response) {

                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                    try {
                        if (response.body() != null) {
                            if (response.body().getStatuscode() == 1) {
                                UtilMethods.INSTANCE.Successful(context, response.body().getMsg() + "");

                            } else {
                                UtilMethods.INSTANCE.Error(context, response.body().getMsg() + "");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        UtilMethods.INSTANCE.Error(context, e.getMessage());
                    }

                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {

                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                UtilMethods.INSTANCE.NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                UtilMethods.INSTANCE.Error(context, t.getMessage());

                            }

                        } else {
                            UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        UtilMethods.INSTANCE.Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }
            UtilMethods.INSTANCE.Error(context, e.getMessage());
        }
    }
}
