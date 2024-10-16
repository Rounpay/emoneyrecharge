package com.fintech.emoneyrechargeonlinenew.DMROld.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fintech.emoneyrechargeonlinenew.Activities.DMRReport;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ActivityActivityMessage;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;
import com.fintech.emoneyrechargeonlinenew.Util.GlobalBus;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DMRLogin extends AppCompatActivity implements View.OnClickListener {

    RadioButton login, create;
    EditText senderNo, senderFirstName, lastNameval, pincodeval, addressval, areaval;
    TextView DateOfBirth;
    Button submit;
    LinearLayout dmr_create, sender_layout, dmr_layout;
    TextView sender_num, sender_name, remaining;
    CustomLoader loader = null;
    ImageView dmr_logout;
    RelativeLayout add_bene, bene_list;
    LinearLayout dmr_report;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmrlogin);

        UtilMethods.INSTANCE.setDashboardStatus(DMRLogin.this, false);
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
        senderNo = findViewById(R.id.sender_number);
        senderFirstName = findViewById(R.id.first_name);
        lastNameval = findViewById(R.id.last_name);
        pincodeval = findViewById(R.id.pincode);
        addressval = findViewById(R.id.address);
        areaval = findViewById(R.id.area);
        DateOfBirth = findViewById(R.id.DateOfBirth);
        DateOfBirth.setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(DMRLogin.this, R.drawable.ic_calendar_icon), null);
        login = findViewById(R.id.login);
        create = findViewById(R.id.create);
        submit = findViewById(R.id.submit);
        dmr_create = findViewById(R.id.dmr_create);
        dmr_layout = findViewById(R.id.dmr_layout);
        sender_layout = findViewById(R.id.sender_layout);
        bene_list = findViewById(R.id.bene_list);
        add_bene = findViewById(R.id.add_bene);
        dmr_report = findViewById(R.id.dmr_report);
        dmr_logout = findViewById(R.id.dmr_logout);
        sender_num = findViewById(R.id.sender_num);
        sender_name = findViewById(R.id.sender_name);
        remaining = findViewById(R.id.remaining);

        loader = new CustomLoader(DMRLogin.this, android.R.style.Theme_Translucent_NoTitleBar);
        submit.setText("Login");
        submit.setVisibility(View.GONE);
        senderNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 10) {
                    loader.show();
                    UtilMethods.INSTANCE.GetSender(DMRLogin.this, senderNo.getText().toString(), loader);
                }
            }
        });
        SetListener();
        DMRStatus();
    }

    private void DMRStatus() {
        dmr_layout.setVisibility(View.VISIBLE);
        sender_layout.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);

    }


    private void SetListener() {
        login.setOnClickListener(this);
        create.setOnClickListener(this);
        submit.setOnClickListener(this);
        dmr_logout.setOnClickListener(this);
        add_bene.setOnClickListener(this);
        bene_list.setOnClickListener(this);
        dmr_report.setOnClickListener(this);
        DateOfBirth.setOnClickListener(this);
    }

    public void setCurrentDetail(String number, String name, String balance) {
        sender_num.setText("" + number);
        sender_name.setText(name);
        remaining.setText(DMRLogin.this.getResources().getString(R.string.rupiya) + " : " + balance);
        dmr_layout.setVisibility(View.GONE);
        sender_layout.setVisibility(View.VISIBLE);
        submit.setVisibility(View.GONE);
    }

    @Subscribe
    public void onActivityActivityMessage(ActivityActivityMessage activityActivityMessage) {
        if (activityActivityMessage.getFrom().equalsIgnoreCase("GetSender")) {
            String[] data = activityActivityMessage.getMessage().split(",");
            setCurrentDetail(data[0], data[1], data[2]);
        } else if (activityActivityMessage.getFrom().equalsIgnoreCase("CallgetSenderSender")) {
            login.setChecked(false);
            create.setChecked(true);
            dmr_create.setVisibility(View.VISIBLE);
            submit.setVisibility(View.VISIBLE);
            submit.setText("Create");
        }
    }


    @Override
    public void onClick(View v) {

        if (v == login) {

            login.setChecked(true);
            create.setChecked(false);
            dmr_create.setVisibility(View.GONE);
            submit.setVisibility(View.GONE);
            submit.setText("Login");
        }
        if (v == create) {
            login.setChecked(false);
            create.setChecked(true);
            dmr_create.setVisibility(View.VISIBLE);
            submit.setVisibility(View.VISIBLE);
            submit.setText("Create");
        }

        if (v == DateOfBirth) {
            final Calendar myCalendar = Calendar.getInstance();
            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    String myFormat = "dd MMM yyyy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                    DateOfBirth.setText(sdf.format(myCalendar.getTime()));
                }

            };
            new DatePickerDialog(DMRLogin.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }

        if (v == submit) {
            if (UtilMethods.INSTANCE.isNetworkAvialable(DMRLogin.this)) {
                loader.show();
                loader.setCancelable(false);
                loader.setCanceledOnTouchOutside(false);
                if (create.isChecked()) {
                    UtilMethods.INSTANCE.CreateSender(DMRLogin.this, senderNo.getText().toString(), senderFirstName.getText().toString(),
                            lastNameval.getText().toString(),
                            pincodeval.getText().toString(),
                            addressval.getText().toString(),
                            "",
                            DateOfBirth.getText().toString(), loader);
                }
            } else {
                UtilMethods.INSTANCE.NetworkError(DMRLogin.this, getResources().getString(R.string.err_msg_network_title),
                        getResources().getString(R.string.err_msg_network));
            }
        }
        if (v == dmr_logout) {
            UtilMethods.INSTANCE.setSenderNumber(DMRLogin.this, "", "", "", "");
            UtilMethods.INSTANCE.setSenderInfo(DMRLogin.this, "", "", false, null);
            UtilMethods.INSTANCE.setBeneficiaryList(DMRLogin.this, "");
            login.setChecked(true);
            create.setChecked(false);
            dmr_create.setVisibility(View.GONE);
            dmr_layout.setVisibility(View.VISIBLE);
            sender_layout.setVisibility(View.GONE);
            submit.setVisibility(View.GONE);
        }
        if (v == add_bene) {

            Intent i = new Intent(DMRLogin.this, AddBeneficiary.class);
            startActivity(i);

        }
        if (v == bene_list) {
            SharedPreferences prefs = DMRLogin.this.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, Context.MODE_PRIVATE);
            if (UtilMethods.INSTANCE.isNetworkAvialable(DMRLogin.this)) {
                loader.show();
                loader.setCancelable(false);
                loader.setCanceledOnTouchOutside(false);
                UtilMethods.INSTANCE.GetBeneficiary(DMRLogin.this, prefs.getString(ApplicationConstant.INSTANCE.senderNumberPref, ""), loader, new UtilMethods.ApiCallBack() {
                    @Override
                    public void onSucess(Object object) {
                        Intent i = new Intent(DMRLogin.this, BeneficiaryListScreen.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                });


            } else {
                UtilMethods.INSTANCE.NetworkError(DMRLogin.this, getResources().getString(R.string.network_error_title),
                        getResources().getString(R.string.network_error_message));
            }
        }
        if (v == dmr_report) {

            Intent m = new Intent(DMRLogin.this, DMRReport.class);
            m.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(m);

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
        GlobalBus.getBus().unregister(this);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

    }


}
