package com.fury.cv.view;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.fury.cv.R;

public class DialogAbout {

    private Dialog mDialog;
    private ImageView mDialogOKButton;
    private Page_org mDialogUniversalInfoActivity;


    public DialogAbout(Page_org var1) {
        this.mDialogUniversalInfoActivity = var1;

    }

    private void initDialogButtons() {
        this.mDialogOKButton.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {

                DialogAbout.this.mDialog.dismiss();


            }
        });
    }


    public void dismissDialog() {
        this.mDialog.dismiss();
    }

    public void showDialog() {
        if (this.mDialog == null) {
            this.mDialog = new Dialog(this.mDialogUniversalInfoActivity, R.style.CustomDialogTheme);
        }
        this.mDialog.setContentView(R.layout.dialog_about);
        this.mDialog.setCancelable(true);
        this.mDialog.show();
        this.mDialogOKButton = (ImageView) this.mDialog.findViewById(R.id.dialog_universal_info_ok);

        initDialogButtons();
    }
}
