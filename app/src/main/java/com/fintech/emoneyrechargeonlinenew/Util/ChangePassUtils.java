package com.fintech.emoneyrechargeonlinenew.Util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;

public class ChangePassUtils {
    Activity mActivity;
    private CustomLoader loader;
    private Dialog dialog;

    public ChangePassUtils(Activity mActivity) {
        this.mActivity = mActivity;
        loader = new CustomLoader(mActivity, android.R.style.Theme_Translucent_NoTitleBar);
    }


    public void changePassword(final boolean isPin, boolean isCancelable) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.change_password, null);

        TextView title = view.findViewById(R.id.title);
        final TextInputLayout currentPasswordLayout = view.findViewById(R.id.currentPasswordLayout);
        final EditText currentPassword = view.findViewById(R.id.currentPassword);
        final TextInputLayout newPasswordLayout = view.findViewById(R.id.newPasswordLayout);
        final EditText newPassword = view.findViewById(R.id.newPassword);
        final TextInputLayout confirmPasswordLayout = view.findViewById(R.id.confirmPasswordLayout);
        final EditText confirmPassword = view.findViewById(R.id.confirmPassword);
        if (isPin) {
            title.setText("Change Pin Password");
            currentPasswordLayout.setHint("Current Password");
            newPasswordLayout.setHint("New Pin Password");
            confirmPasswordLayout.setHint("Confirm Pin Password");
        }
        final AppCompatButton okButton = view.findViewById(R.id.okButton);
        final AppCompatButton cancelButton = view.findViewById(R.id.cancelButton);
        if (isCancelable) {
            cancelButton.setVisibility(View.VISIBLE);
        } else {
            cancelButton.setVisibility(View.GONE);
        }
        dialog = new Dialog(mActivity, R.style.alert_dialog_light);

        dialog.setTitle("Forgot Password");
        dialog.setCancelable(isCancelable);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int flag = 0;

                if (currentPassword.getText() != null && currentPassword.getText().toString().trim().isEmpty()) {
                    flag++;
                    currentPassword.setError(mActivity.getResources().getString(R.string.password_error));
                    currentPassword.requestFocus();
                } else if (newPassword.getText() != null && newPassword.getText().toString().trim().isEmpty()) {
                    flag++;
                    newPassword.setError(mActivity.getResources().getString(R.string.password_error));
                    newPassword.requestFocus();
                } else if (newPassword.getText().toString().trim().equalsIgnoreCase(currentPassword.getText().toString().trim())) {
                    flag++;
                    newPassword.setError(mActivity.getResources().getString(R.string.samepass_error));
                    newPassword.requestFocus();
                } else if (confirmPassword.getText() != null && newPassword.getText() != null &&
                        !newPassword.getText().toString().trim().equalsIgnoreCase(confirmPassword.getText().toString().trim())) {
                    flag++;
                    confirmPassword.setError(mActivity.getResources().getString(R.string.newpass_error));
                    confirmPassword.requestFocus();
                }


                if (flag == 0) {
                    if (UtilMethods.INSTANCE.isNetworkAvialable(mActivity)) {
                        loader.show();
                        loader.setCancelable(false);
                        loader.setCanceledOnTouchOutside(false);
                        UtilMethods.INSTANCE.ChangePinPassword(mActivity, isPin, currentPassword.getText().toString().trim()
                                , newPassword.getText().toString().trim(), confirmPassword.getText().toString().trim(), loader, dialog);
                    } else {

                        UtilMethods.INSTANCE.NetworkError(mActivity, mActivity.getResources().getString(R.string.err_msg_network_title),
                                mActivity.getResources().getString(R.string.err_msg_network));
                    }
                }

            }
        });
        dialog.show();
    }
}
