package com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fintech.emoneyrechargeonlinenew.Activities.Adapter.BankListScreenAdapter;
import com.fintech.emoneyrechargeonlinenew.Api.Object.BankListObject;
import com.fintech.emoneyrechargeonlinenew.Api.Response.BankListResponse;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;
import com.google.gson.Gson;

import java.util.ArrayList;


public class AEPSBankListScreen extends AppCompatActivity {

    RecyclerView recycler_view;
    TextView noData;
    BankListScreenAdapter mAdapter;
    ArrayList<BankListObject> operator=new ArrayList<>();
    BankListResponse operatorList = new BankListResponse();
    TextView title;
    EditText search_all;
    private CustomLoader loader;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aeps_bank_list_screen);
        search_all = findViewById(R.id.search_all);
        title = findViewById(R.id.title);
        loader = new CustomLoader(this, android.R.style.Theme_Translucent_NoTitleBar);

        title.setText("Bank List");
        findViewById(R.id.backIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        findViewById(R.id.clearIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_all.setText("");
            }
        });


        recycler_view = findViewById(R.id.recycler_view);
        noData = findViewById(R.id.noData);

        getOperatorList();


        search_all.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newText = s.toString().toLowerCase();
                ArrayList<BankListObject> newlist = new ArrayList<>();
                for (BankListObject op : operator) {
                    String getName = op.getBankName().toLowerCase();
                    if (getName.contains(newText)) {
                        newlist.add(op);
                    }
                }
                mAdapter.filter(newlist);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void getOperatorList() {

        operatorList = (BankListResponse) getIntent().getSerializableExtra("BankList");
        if (operatorList != null && operatorList.getAepsBanks() != null && operatorList.getAepsBanks().size() > 0) {
            operator = operatorList.getAepsBanks();
            setData();
        }else {
            SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
            String response = prefs.getString(ApplicationConstant.INSTANCE.bankAEPSListPref, null);
            operatorList = new Gson().fromJson(response, BankListResponse.class);
            if (operatorList != null && operatorList.getAepsBanks() != null && operatorList.getAepsBanks().size() > 0) {
                operator = operatorList.getAepsBanks();
                setData();
            }else{
                UtilMethods.INSTANCE.GetAEPSBanklist(this, loader, object -> {
                    operatorList = (BankListResponse) object;
                    if (operatorList != null && operatorList.getAepsBanks() != null && operatorList.getAepsBanks().size() > 0) {
                        operator = operatorList.getAepsBanks();
                        setData();
                    }
                });
            }

        }


    }

    void setData(){
        if (operator != null && operator.size() > 0) {
            noData.setVisibility(View.GONE);

            mAdapter = new BankListScreenAdapter(operator, AEPSBankListScreen.this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.setAdapter(mAdapter);

        } else {
            noData.setVisibility(View.VISIBLE);
        }
    }

    public void ItemClick(BankListObject operator) {
        Intent clickIntent = new Intent();
        clickIntent.putExtra("Bank", operator);
        setResult(RESULT_OK, clickIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //  mAdapter.filter(newText);
                newText = newText.toLowerCase();
                ArrayList<BankListObject> newlist = new ArrayList<>();
                for (BankListObject op : operator) {
                    String getName = op.getBankName().toLowerCase();
                    if (getName.contains(newText)) {
                        newlist.add(op);
                    }
                }
                mAdapter.filter(newlist);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

/*
public class BankListScreen extends AppCompatActivity {

    RecyclerView recycler_view;
    TextView noData;
    BankListScreenAdapter mAdapter;
    ArrayList<BankListObject> operator = new ArrayList<>();
    BankListResponse operatorList = new BankListResponse();
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bank List");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        noData = findViewById(R.id.noData);

        getOperatorList();

        if (operator != null && operator.size() > 0) {
            noData.setVisibility(View.GONE);

            mAdapter = new BankListScreenAdapter(operator, BankListScreen.this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.setAdapter(mAdapter);

        } else {
            noData.setVisibility(View.VISIBLE);
        }
    }

    public void getOperatorList() {

        SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = prefs.getString(ApplicationConstant.INSTANCE.bankListPref, null);

        Gson gson = new Gson();
        operatorList = gson.fromJson(response, BankListResponse.class);
        operator = operatorList.getBanks();
    }

    public void ItemClick(String id, String name, String accVerification, String shortCode) {
        Intent clickIntent = new Intent();
        clickIntent.putExtra("bankName", name);
        clickIntent.putExtra("bankId", id);
        clickIntent.putExtra("accVerification", accVerification);
        clickIntent.putExtra("shortCode", shortCode);
        setResult(1, clickIntent);
        finish();
    }

}
*/
