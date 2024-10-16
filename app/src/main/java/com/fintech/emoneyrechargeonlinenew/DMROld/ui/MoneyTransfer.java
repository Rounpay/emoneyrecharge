package com.fintech.emoneyrechargeonlinenew.DMROld.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.fintech.emoneyrechargeonlinenew.Api.Object.BankListObject;
import com.fintech.emoneyrechargeonlinenew.Api.Response.BankListResponse;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ActivityActivityMessage;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;
import com.fintech.emoneyrechargeonlinenew.Util.GlobalBus;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class MoneyTransfer extends AppCompatActivity implements View.OnClickListener {

    EditText transferAmount, pinPassEt;
    TextView submitButton, closeButton, charge;
    Toolbar toolbar;
    String name, bank, bankAccount, beneficiaryCode, channel = "true", amount, senderNumber, ifsc;

    RelativeLayout neftContainer, impsContainer;
    RadioButton neftRadio, impsRadio;

    CustomLoader loader;
    ArrayList<BankListObject> operator = new ArrayList<>();
    BankListResponse operatorList = new BankListResponse();
    boolean NEFTFlag = false;
    boolean IMPSFlag = false;
    int selectedFlag = 2;
    LinearLayout charge_layout;
    TextView bankname, accountNo, Namebene, total;
    int flag = 1;
    private TextView tvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_transfer);

        GetId();
    }

    private void GetId() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Money Transfer");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        loader = new CustomLoader(this, android.R.style.Theme_Translucent_NoTitleBar);

        name = getIntent().getExtras().getString("name");
        bank = getIntent().getExtras().getString("bank");
        bankAccount = getIntent().getExtras().getString("bankAccount");
        beneficiaryCode = getIntent().getExtras().getString("beneficiaryCode");
        ifsc = getIntent().getExtras().getString("ifsc");

        transferType();

        neftContainer = findViewById(R.id.neftContainer);
        impsContainer = findViewById(R.id.impsContainer);
        neftRadio = findViewById(R.id.neftRadio);
        impsRadio = findViewById(R.id.impsRadio);
        transferAmount = findViewById(R.id.transferAmount);
        pinPassEt = findViewById(R.id.pinPassEt);
        submitButton = findViewById(R.id.submitButton);
        bankname = findViewById(R.id.bankname);
        bankname.setText(bank);
        accountNo = findViewById(R.id.accountNo);
        accountNo.setText(bankAccount);
        Namebene = findViewById(R.id.Namebene);
        Namebene.setText(name);
        total = findViewById(R.id.total);
        charge = findViewById(R.id.charge);
        closeButton = findViewById(R.id.closeButton);
        charge_layout = findViewById(R.id.charge_layout);
        SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        senderNumber = prefs.getString(ApplicationConstant.INSTANCE.senderNumberPref, null);
        if (flag == 1) {
            submitButton.setText("SEND");
        }

        if (UtilMethods.INSTANCE.getPinRequiredPref(this) || UtilMethods.INSTANCE.getDoubleFactorPref(this)) {
            pinPassEt.setVisibility(View.VISIBLE);
        } else {
            pinPassEt.setVisibility(View.GONE);
        }
        SetListener();
    }

    private void SetListener() {
        neftRadio.setOnClickListener(this);
        impsRadio.setOnClickListener(this);
        neftContainer.setOnClickListener(this);
        impsContainer.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);
    }

    public void transferType() {
        SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = prefs.getString(ApplicationConstant.INSTANCE.bankListPref, null);

        Gson gson = new Gson();
        operatorList = gson.fromJson(response, BankListResponse.class);
        operator = operatorList.getBankMasters();

        for (BankListObject object : operator) {
            if (object.getBankName().equalsIgnoreCase(bank)) {
                if (object.getIsNEFT().equalsIgnoreCase("1")) {
                    NEFTFlag = true;
                }

                if (object.getIsIMPS().equalsIgnoreCase("1")) {
                    IMPSFlag = true;
                }
            } else {
                NEFTFlag = false;
                IMPSFlag = false;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == neftContainer || v == neftRadio) {
            neftRadio.setChecked(true);
            impsRadio.setChecked(false);

            selectedFlag = 1;
            channel = "false";
        }

        if (v == impsContainer || v == impsRadio) {
            neftRadio.setChecked(false);
            impsRadio.setChecked(true);

            selectedFlag = 2;
            channel = "true";
        }

        if (v == closeButton) {
            finish();
        }

        if (v == submitButton) {
            if (validationForm() == 0) {
                if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                    loader.show();
                    loader.setCancelable(false);
                    loader.setCanceledOnTouchOutside(false);

                    //  UtilMethods.INSTANCE.GetChargedAmount(this, senderNumber, bankAccount, amount, beneficiaryCode, channel,senderTableInfo.getRemaining(), loader);
                    if (flag == 1)
                        UtilMethods.INSTANCE.GetChargedAmount(this, amount, beneficiaryCode, senderNumber, ifsc, bankAccount, channel, bank, name, loader, MoneyTransfer.this);
                    else if (flag == 2)
                        UtilMethods.INSTANCE.SendMoney(this, pinPassEt.getText().toString(), beneficiaryCode, senderNumber, ifsc, bankAccount, amount, channel, bank, name, loader, MoneyTransfer.this, submitButton);
                } else {
                    UtilMethods.INSTANCE.NetworkError(this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message));
                }
            }
        }
    }

    public int validationForm() {
        int flag = 0;

        if (selectedFlag != 0 && channel != null && channel.length() > 0) {

        } else {
            UtilMethods.INSTANCE.Error(this, "Please specify transfer type");
            flag++;
        }

        if (transferAmount.getText() != null && transferAmount.getText().toString().trim().length() > 0) {
            amount = transferAmount.getText().toString().trim();
        } else {
            transferAmount.setError("Please enter amount");
            transferAmount.requestFocus();
            flag++;
        }

        if (charge_layout.getVisibility() == View.VISIBLE && pinPassEt.getVisibility() == View.VISIBLE && pinPassEt.getText().toString().isEmpty()) {
            pinPassEt.setError("Please Enter Pin Password");
            pinPassEt.requestFocus();
            flag++;
        }

        return flag;
    }

    @Subscribe
    public void onActivityActivityMessage(ActivityActivityMessage activityActivityMessage) {
        if (activityActivityMessage.getFrom().equalsIgnoreCase("SendMoney")) {
            String[] str = activityActivityMessage.getMessage().split(",");
            charge_layout.setVisibility(View.VISIBLE);
            charge.setText(str[0]);
            total.setText(str[1]);
            flag = 2;
            if (flag == 2) {
                submitButton.setText("Comfirm");
            }

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this);
    }

}
