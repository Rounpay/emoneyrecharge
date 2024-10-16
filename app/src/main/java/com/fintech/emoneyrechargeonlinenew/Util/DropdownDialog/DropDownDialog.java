package com.fintech.emoneyrechargeonlinenew.Util.DropdownDialog;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.fintech.emoneyrechargeonlinenew.R;

import java.util.ArrayList;
import java.util.List;

public class DropDownDialog {

    Activity mContext;
    private PopupWindow popup;

    public DropDownDialog(Activity mContext) {
        this.mContext = mContext;
    }


    public void showDropDownBluetoothPopup(View v, final int selectPos, List<BluetoothDevice> mDropDownList, ClickDropDownItem mClickDropDownItem) {
        if (popup != null && popup.isShowing()) {
            return;
        }
        popup = new PopupWindow(mContext);
        View layout = mContext.getLayoutInflater().inflate(R.layout.dialog_drop_down, null);
        RecyclerView recyclerView = layout.findViewById(R.id.recyclerView);
        LinearLayout view = layout.findViewById(R.id.view);
        view.getLayoutParams().width = v.getMeasuredWidth();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));


        DropDownBluetoothListAdapter mDropDownListAdapter = new DropDownBluetoothListAdapter(mContext, mDropDownList, selectPos, (clickPosition, value1, object) -> {
            if (popup != null) {
                popup.dismiss();
            }


            if (mClickDropDownItem != null) {
                mClickDropDownItem.onClick(clickPosition, value1, object);
            }

        });
        recyclerView.setAdapter(mDropDownListAdapter);

        popup.setContentView(layout);
        popup.setFocusable(true);
        popup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int margine = 10;
        popup.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            popup.setOverlapAnchor(true);
            popup.showAsDropDown(v, -margine, -margine);
        } else {
            // Show anchored to button
            int[] location = new int[2];
            v.getLocationOnScreen(location);
            popup.showAtLocation(v, Gravity.NO_GRAVITY, (int) (location[0] - margine), (int) (location[1] - margine));

        }
        // popup.showAsDropDown(v);

    }

    public void showDropDownPopup(View v, final int selectPos, ArrayList<String> mDropDownList, ClickDropDownItem mClickDropDownItem) {
        if (popup != null && popup.isShowing()) {
            return;
        }
        popup = new PopupWindow(mContext);
        View layout = mContext.getLayoutInflater().inflate(R.layout.dialog_drop_down, null);
        RecyclerView recyclerView = layout.findViewById(R.id.recyclerView);
        LinearLayout view = layout.findViewById(R.id.view);
        view.getLayoutParams().width = v.getMeasuredWidth();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));


        DropDownStringListAdapter mDropDownStringListAdapter = new DropDownStringListAdapter(mContext, mDropDownList, selectPos, (clickPosition, value1, object) -> {
            if (popup != null) {
                popup.dismiss();
            }


            if (mClickDropDownItem != null) {
                mClickDropDownItem.onClick(clickPosition, value1, object);
            }

        });
        recyclerView.setAdapter(mDropDownStringListAdapter);
        popup.setContentView(layout);
        popup.setFocusable(true);
        popup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int margine = 10;
        popup.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            popup.setOverlapAnchor(true);
            popup.showAsDropDown(v, -margine, -margine);
        } else {
            // Show anchored to button
            int[] location = new int[2];
            v.getLocationOnScreen(location);
            popup.showAtLocation(v, Gravity.NO_GRAVITY, (int) (location[0] - margine), (int) (location[1] - margine));

        }
        //  popup.showAsDropDown(v);

    }


    public interface ClickDropDownItem {
        void onClick(int clickPosition, String value, Object object);
    }
}
