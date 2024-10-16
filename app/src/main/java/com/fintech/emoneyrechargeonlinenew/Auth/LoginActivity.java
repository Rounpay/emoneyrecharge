package com.fintech.emoneyrechargeonlinenew.Auth;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.fintech.emoneyrechargeonlinenew.Auth.dto.SignupActivity;
import com.fintech.emoneyrechargeonlinenew.DashBoard.UI.DashBoardLayout;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseIntArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.fintech.emoneyrechargeonlinenew.BuildConfig;
import com.fintech.emoneyrechargeonlinenew.Notification.app.Config;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.InstallReferral;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_PERMISSIONS = 20;
    private static String IMEI;
    // Declare Button.....
    public Button btLogin;
    public Button FwdokButton;
    public Button cancelButton;
    TextView tvContactus;
    HashMap<String, String> recentNumberMap = new HashMap<>();
    // Declare RelativeLayout.....
    ArrayAdapter<String> adapter;
    public RelativeLayout rlForgotPass;
    // Declare TextInputLayout.....
    public TextInputLayout tilMobile;
    public TextInputLayout tilPass;
    public TextInputLayout tilMobileFwp;
    // Declare TextInputLayout.....
    public AutoCompleteTextView edMobile;
    public AutoCompleteTextView edPass;
    public EditText edMobileFwp;
    // Declare CustomLoader.....
    CustomLoader loader;
    ArrayList<String> recentNumber = new ArrayList<>();
    CheckBox rememberCheck,check_term;
    private AlertDialog alertDialog;
    private SparseIntArray mErrorString;
    LinearLayout llLogin;
    RelativeLayout rlLayoutFwdPass;
    TextView privacyPolicyTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getId();

        RecentNumbers();
        UtilMethods.INSTANCE.openImageDialog(this, ApplicationConstant.INSTANCE.appBeforeLoginURl);
        if( !UtilMethods.INSTANCE.isReferrerIdSetData(this)){
            new InstallReferral(this).InstllReferralData();
        }

    }

    public void RecentNumbers() {
        String abcd = UtilMethods.INSTANCE.getRecentLogin(this);
        if (abcd != null && abcd.length() > 4) {
            recentNumberMap = new Gson().fromJson(abcd, new TypeToken<HashMap<String, String>>() {
            }.getType());

            int index = 0;
            for (Map.Entry<String, String> e : recentNumberMap.entrySet()) {
                String key = e.getKey();
                /* String value = e.getValue();*/
                recentNumber.add(key);
                index++;

            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recentNumber);
            edMobile.setAdapter(adapter);
            edMobile.setThreshold(1);
        }


        edMobile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edPass.setText(recentNumberMap.get(recentNumber.get(position)));

            }
        });
    }

    public void getId() {
        loader = new CustomLoader(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        btLogin = findViewById(R.id.bt_login);
        rememberCheck = findViewById(R.id.check_pass);
        tvContactus = findViewById(R.id.tv_contactus);
        edMobileFwp = (EditText) findViewById(R.id.ed_mobile_fwp);
        tilMobileFwp = (TextInputLayout) findViewById(R.id.til_mobile_fwp);
        FwdokButton = (Button) findViewById(R.id.okButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        TextView currentVersion = findViewById(R.id.currentVersion);
        currentVersion.setText(getString(R.string.app_name) + " Version " + BuildConfig.VERSION_NAME);
        rlForgotPass = findViewById(R.id.rl_fwd_pass);
        rlLayoutFwdPass = findViewById(R.id.rl_layout_fwd_pass);
        llLogin = findViewById(R.id.ll_login);
        TextView title = findViewById(R.id.title);
        title.setText("Welcome to " + getString(R.string.app_name));
        tilMobile = findViewById(R.id.til_mobile);
        tilPass = findViewById(R.id.til_pass);
        edMobile = findViewById(R.id.ed_mobile);
        edPass = findViewById(R.id.ed_pass);
        check_term = (CheckBox) findViewById(R.id.check_term);
        privacyPolicyTv = (TextView) findViewById(R.id.privacyPolicyTv);
        setListners();
        edMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!validateMobile()) {
                    return;
                }
            }
        });
        edPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!validatePass()) {
                    return;
                }
            }
        });

        edMobileFwp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!validateMobileFwp()) {
                    return;
                }
            }
        });

        findViewById(R.id.tv_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
            }









        });

        tvContactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ContactUsActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
            }
        });
    }

    public void setListners() {
        btLogin.setOnClickListener(this);
        FwdokButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        rlForgotPass.setOnClickListener(this);
        privacyPolicyTv.setOnClickListener(this);


    }
    private boolean validateTerms() {
        if (check_term.isChecked()){
            btLogin.setEnabled(true);
            return true;

        }else
        {
            btLogin.setEnabled(false);
            Toast.makeText(this, "Please Accept Privacy & Policy Before Login", Toast.LENGTH_SHORT).show();
        }
        btLogin.setEnabled(true);
        return false;

    }


    private boolean validateMobile() {
        if (edMobile.getText().toString().trim().isEmpty()) {
            tilMobile.setError(getString(R.string.err_msg_mobile));
            requestFocus(edMobile);
            btLogin.setEnabled(false);
            return false;
        } else {
            tilMobile.setErrorEnabled(false);
            // edPass.requestFocus();
        }

        return true;
    }

    private boolean validatePass() {
        if (edPass.getText().toString().trim().isEmpty()) {
            tilPass.setError(getString(R.string.err_msg_pass));
            requestFocus(edPass);
            btLogin.setEnabled(false);
            return false;
        } else {
            tilPass.setErrorEnabled(false);
            btLogin.setEnabled(true);
        }
        return true;
    }

    private boolean validateMobileFwp() {
        if (edMobileFwp.getText().toString().trim().isEmpty()) {
            tilMobileFwp.setError(getString(R.string.err_msg_mobile));
            //requestFocus(edMobileFwp);
            FwdokButton.setEnabled(false);
            return false;
        } else if (edMobileFwp.getText() != null && edMobileFwp.getText().toString().trim().length() < 10) {
            tilMobileFwp.setError(getString(R.string.err_msg_mobile_length));
            FwdokButton.setEnabled(false);
            return false;
        } else if (!(UtilMethods.INSTANCE.isValidMobile(edMobileFwp.getText().toString().trim()) &&
                (edMobileFwp.getText().toString().trim().charAt(0) == '6' ||
                        edMobileFwp.getText().toString().trim().charAt(0) == '7' ||
                        edMobileFwp.getText().toString().trim().charAt(0) == '8' ||
                        edMobileFwp.getText().toString().trim().charAt(0) == '9'))) {
            tilMobileFwp.setError(getString(R.string.err_msg_valid_mobile));
            FwdokButton.setEnabled(false);
            return false;
        } else if ((edMobileFwp.getText() != null && edMobileFwp.getText().toString().trim().length() > 0 &&
                UtilMethods.INSTANCE.isValidMobile(edMobileFwp.getText().toString().trim()) &&
                !(edMobileFwp.getText().toString().trim().length() < 10) &&
                (edMobileFwp.getText().toString().trim().charAt(0) == '6' ||
                        edMobileFwp.getText().toString().trim().charAt(0) == '7' ||
                        edMobileFwp.getText().toString().trim().charAt(0) == '8' ||
                        edMobileFwp.getText().toString().trim().charAt(0) == '9'))) {
            tilMobileFwp.setErrorEnabled(false);
            FwdokButton.setEnabled(true);
            return true;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        if (v == btLogin) {

            if (!validateMobile()) {
                return;
            }
            if (!validateTerms()) {
                return;
            }

            if (!validatePass()) {
                return;
            }

            if (rememberCheck.isChecked()) {
                recentNumberMap.put(edMobile.getText().toString(), edPass.getText().toString());
                recentNumber.add(edMobile.getText().toString());
                adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recentNumber);
                edMobile.setAdapter(adapter);
                edMobile.setThreshold(1);

                UtilMethods.INSTANCE.setRecentLogin(this, new Gson().toJson(recentNumberMap));

            }

           // Boolean vv = UtilMethods.INSTANCE.isVpnConnected(this);

            /* if (!(UtilMethods.INSTANCE.isVpnConnected(this) || UtilMethods.INSTANCE.isSimAvailable(this))) {*/
            if (UtilMethods.INSTANCE.isNetworkAvialable(LoginActivity.this)) {
                loader.show();
                loader.setCancelable(false);
                loader.setCanceledOnTouchOutside(false);
                btLogin.setEnabled(true);
                UtilMethods.INSTANCE.secureLogin(LoginActivity.this, edMobile.getText().toString().trim(),
                        edPass.getText().toString().trim(), loader);
            } else {
                UtilMethods.INSTANCE.NetworkError(LoginActivity.this, getResources().getString(R.string.err_msg_network_title),
                        getResources().getString(R.string.err_msg_network));
            }
        }

        if (v == rlForgotPass) {
            //  OpenDialogFwd();
            llLogin.setVisibility(View.GONE);
            rlLayoutFwdPass.setVisibility(View.VISIBLE);
        }

        if (v == FwdokButton) {
            if (!validateMobileFwp()) {
                return;
            }

            if (UtilMethods.INSTANCE.isNetworkAvialable(LoginActivity.this)) {
                loader.show();
                UtilMethods.INSTANCE.ForgotPass(LoginActivity.this, edMobileFwp.getText().toString().trim(), loader);

            } else {
                UtilMethods.INSTANCE.NetworkError(LoginActivity.this, getResources().getString(R.string.err_msg_network_title),
                        getResources().getString(R.string.err_msg_network));
            }
        }
        if (v == cancelButton) {
            llLogin.setVisibility(View.VISIBLE);
            rlLayoutFwdPass.setVisibility(View.GONE);
            edMobileFwp.setText("");
        }
        if (v == privacyPolicyTv){
            Intent intent=new Intent(this, WebViewPrivacyPolicy.class);
            startActivity(intent);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Utils.goAnotherActivity(this,LoginActivity.class);
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void startDashboard() {
        FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
        Intent intent = new Intent(LoginActivity.this, DashBoardLayout.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


}
