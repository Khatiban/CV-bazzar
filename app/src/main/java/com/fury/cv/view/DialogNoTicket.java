package com.fury.cv.view;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.fury.cv.R;

public class DialogNoTicket {

    private Dialog mDialog;
    private TextView mDialogOKButton;
    private TextView dialog_universal_info_store;
    private TextView text1;
    private TextView text2;
    private ViewVideo mDialogUniversalInfoActivity;
    private Cut mDialogUniversalInfoActivity2;
    private Join mDialogUniversalInfoActivity3;
    private Create mDialogUniversalInfoActivity4;
    private Format mDialogUniversalInfoActivity5;
    private Gif mDialogUniversalInfoActivity6;
    private Logo mDialogUniversalInfoActivity7;
    private Music mDialogUniversalInfoActivity8;
    private Voice mDialogUniversalInfoActivity9;
    int s;

    public DialogNoTicket(ViewVideo var1) {
        this.mDialogUniversalInfoActivity = var1;
        s = 1;
    }
    public DialogNoTicket(Cut var1) {
        this.mDialogUniversalInfoActivity2 = var1;
        s = 2;
    }
    public DialogNoTicket(Join var1) {
        this.mDialogUniversalInfoActivity3 = var1;
        s = 3;
    }
    public DialogNoTicket(Create var1) {
        this.mDialogUniversalInfoActivity4 = var1;
        s = 4;
    }
    public DialogNoTicket(Format var1) {
        this.mDialogUniversalInfoActivity5 = var1;
        s = 5;
    }
    public DialogNoTicket(Gif var1) {
        this.mDialogUniversalInfoActivity6 = var1;
        s = 6;
    }
    public DialogNoTicket(Logo var1) {
        this.mDialogUniversalInfoActivity7 = var1;
        s = 7;
    }
    public DialogNoTicket(Music var1) {
        this.mDialogUniversalInfoActivity8 = var1;
        s = 8;
    }
    public DialogNoTicket(Voice var1) {
        this.mDialogUniversalInfoActivity9 = var1;
        s = 9;
    }

    private void initDialogButtons() {
        this.mDialogOKButton.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {

                DialogNoTicket.this.mDialog.dismiss();

            }
        });
        this.dialog_universal_info_store.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {

                if (s==1){
                    mDialogUniversalInfoActivity.startActivity(new Intent(mDialogUniversalInfoActivity, Store.class));
                }else if (s==2){
                    mDialogUniversalInfoActivity2. startActivity(new Intent(mDialogUniversalInfoActivity2, Store.class));
                }else if (s==3){
                    mDialogUniversalInfoActivity3. startActivity(new Intent(mDialogUniversalInfoActivity3, Store.class));
                }else if (s==4){
                    mDialogUniversalInfoActivity4. startActivity(new Intent(mDialogUniversalInfoActivity4, Store.class));
                }else if (s==5){
                    mDialogUniversalInfoActivity5. startActivity(new Intent(mDialogUniversalInfoActivity5, Store.class));
                }else if (s==6){
                    mDialogUniversalInfoActivity6. startActivity(new Intent(mDialogUniversalInfoActivity6, Store.class));
                }else if (s==7){
                    mDialogUniversalInfoActivity7. startActivity(new Intent(mDialogUniversalInfoActivity7, Store.class));
                }else if (s==8){
                    mDialogUniversalInfoActivity8. startActivity(new Intent(mDialogUniversalInfoActivity8, Store.class));
                }else if (s==9){
                    mDialogUniversalInfoActivity9. startActivity(new Intent(mDialogUniversalInfoActivity9, Store.class));
                }

                DialogNoTicket.this.mDialog.dismiss();

            }
        });
    }


    public void dismissDialog() {
        this.mDialog.dismiss();
    }

    public void showDialog() {
        if (this.mDialog == null) {
            if (s == 1){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity, R.style.CustomDialogTheme);
            }else if (s == 2){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity2, R.style.CustomDialogTheme);
            }else if (s == 3){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity3, R.style.CustomDialogTheme);
            }else if (s == 4){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity4, R.style.CustomDialogTheme);
            }else if (s == 5){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity5, R.style.CustomDialogTheme);
            }else if (s == 6){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity6, R.style.CustomDialogTheme);
            }else if (s == 7){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity7, R.style.CustomDialogTheme);
            }else if (s == 8){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity8, R.style.CustomDialogTheme);
            }else if (s == 9){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity9, R.style.CustomDialogTheme);
            }
        }
        this.mDialog.setContentView(R.layout.dialog_notiket);
        this.mDialog.setCancelable(true);
        this.mDialog.show();
        this.mDialogOKButton = (TextView) this.mDialog.findViewById(R.id.dialog_universal_info_cancel);
        this.dialog_universal_info_store = (TextView) this.mDialog.findViewById(R.id.dialog_universal_info_store);


        initDialogButtons();
    }
}
