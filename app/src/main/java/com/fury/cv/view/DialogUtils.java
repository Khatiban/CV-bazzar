package com.fury.cv.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fury.cv.R;

import java.util.Locale;
import java.util.Objects;

@TargetApi(21)
public class DialogUtils {

    public interface DialogBtnClickListener {
        void onPositiveClick();
    }

    public interface DialogBtnClickListener_set {
        void onPositiveClick(String s);
    }

    public interface DialogBtnClickListener_file {
        void onPositiveClick_1();
        void onPositiveClick_2();
        void onPositiveClick_3();
    }

    static String tru = null;


    static class C15418 implements View.OnClickListener {
        private final /* synthetic */ Dialog val$d;
        private final /* synthetic */ DialogBtnClickListener val$mListener;

        C15418(DialogBtnClickListener dialogBtnClickListener, Dialog dialog) {
            this.val$mListener = dialogBtnClickListener;
            this.val$d = dialog;
        }

        public void onClick(View rippleView) {
            this.val$mListener.onPositiveClick();
            this.val$d.dismiss();
        }
    }


    static class C15419 implements View.OnClickListener {
        private final /* synthetic */ Dialog val$d;
        private final /* synthetic */ DialogBtnClickListener_set val$mListener;
        private /* synthetic */ Activity a;

        C15419(Activity a,DialogBtnClickListener_set dialogBtnClickListener, Dialog dialog) {
            this.a = a;
            this.val$mListener = dialogBtnClickListener;
            this.val$d = dialog;
        }

        public void onClick(View rippleView) {
            if (tru != null){
                val$mListener.onPositiveClick(tru);
                val$d.dismiss();
            }else {
                Toast.makeText(a.getApplicationContext(), "عدد در محدوده نیست!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    static class C15429 implements View.OnClickListener {
        private final /* synthetic */ Dialog val$d;

        C15429(Dialog dialog) {
            this.val$d = dialog;
        }

        public void onClick(View rippleView) {
            this.val$d.dismiss();
        }
    }

    static class C15430 implements View.OnClickListener {
        private final /* synthetic */ Dialog val$d;
        private final /* synthetic */ DialogBtnClickListener_file val$mListener;

        C15430(DialogBtnClickListener_file dialogBtnClickListener,Dialog dialog) {
            this.val$mListener = dialogBtnClickListener;
            this.val$d = dialog;
        }

        public void onClick(View rippleView) {
            this.val$mListener.onPositiveClick_1();
            this.val$d.dismiss();
        }
    }

    static class C15431 implements View.OnClickListener {
        private final /* synthetic */ Dialog val$d;
        private final /* synthetic */ DialogBtnClickListener_file val$mListener;

        C15431(DialogBtnClickListener_file dialogBtnClickListener,Dialog dialog) {
            this.val$mListener = dialogBtnClickListener;
            this.val$d = dialog;
        }

        public void onClick(View rippleView) {
            this.val$mListener.onPositiveClick_2();
            this.val$d.dismiss();
        }
    }

    static class C15432 implements View.OnClickListener {
        private final /* synthetic */ Dialog val$d;
        private final /* synthetic */ DialogBtnClickListener_file val$mListener;

        C15432(DialogBtnClickListener_file dialogBtnClickListener,Dialog dialog) {
            this.val$mListener = dialogBtnClickListener;
            this.val$d = dialog;
        }

        public void onClick(View rippleView) {
            this.val$mListener.onPositiveClick_3();
            this.val$d.dismiss();
        }
    }

    public static void check(String check){
        if (Objects.equals(check, "25")){
            tru = check;
        }else if (Objects.equals(check, "26")){
            tru = check;
        }else if (Objects.equals(check, "27")){
            tru = check;
        }else if (Objects.equals(check, "28")){
            tru = check;
        }else if (Objects.equals(check, "29")){
            tru = check;
        }else if (Objects.equals(check, "30")){
            tru = check;
        }else if (Objects.equals(check, "31")){
            tru = check;
        }else if (Objects.equals(check, "32")){
            tru = check;
        }else if (Objects.equals(check, "33")){
            tru = check;
        }else if (Objects.equals(check, "34")){
            tru = check;
        }else if (Objects.equals(check, "35")){
            tru = check;
        }else if (Objects.equals(check, "36")){
            tru = check;
        }else if (Objects.equals(check, "37")){
            tru = check;
        }else if (Objects.equals(check, "38")){
            tru = check;
        }else if (Objects.equals(check, "39")){
            tru = check;
        }else if (Objects.equals(check, "40")){
            tru = check;
        }else {
            tru = null;
        }
    }

    public static void showDeleteDialog(Activity a, DialogBtnClickListener mListener) {
        Dialog d = new Dialog(a, R.style.CustomDialogTheme);
        View v2 = a.getLayoutInflater().inflate(R.layout.delete_dialog, null);
        Typeface face = Typeface.createFromAsset(a.getAssets(), "fa_font_1.ttf");
        ((TextView) v2.findViewById(R.id.textView2)).setTypeface(face);
        ((TextView) v2.findViewById(R.id.tv_dialogText)).setTypeface(face);
        d.setContentView(v2);
        v2.findViewById(R.id.rlDelete).setOnClickListener(new C15418(mListener, d));
        v2.findViewById(R.id.rlCancel).setOnClickListener(new C15429(d));
        d.show();
    }

    public static void showfileDialog(Activity a, DialogBtnClickListener_file mListener) {
        Dialog d = new Dialog(a, R.style.CustomDialogTheme);
        View v2 = a.getLayoutInflater().inflate(R.layout.file_dialog, null);
        Typeface face = Typeface.createFromAsset(a.getAssets(), "fa_font_1.ttf");
        ((TextView) v2.findViewById(R.id.textView2)).setTypeface(face);
        ((TextView) v2.findViewById(R.id.textView3)).setTypeface(face);
        ((TextView) v2.findViewById(R.id.textView4)).setTypeface(face);
        d.setContentView(v2);
        v2.findViewById(R.id.delete_file).setOnClickListener(new C15430(mListener,d));
        v2.findViewById(R.id.shire_file).setOnClickListener(new C15431(mListener,d));
        v2.findViewById(R.id.detail_file).setOnClickListener(new C15432(mListener,d));
        d.show();
    }

    public static void showDetailDialog(Activity a,String name , String size , String uri, String dir) {
        Dialog d = new Dialog(a, R.style.CustomDialogTheme);
        View v2 = a.getLayoutInflater().inflate(R.layout.detail_dialog, null);
        Typeface face = Typeface.createFromAsset(a.getAssets(), "fa_font_1.ttf");
        ((TextView) v2.findViewById(R.id.name_f)).setText(name);
        ((TextView) v2.findViewById(R.id.size_f)).setText(size);
        ((TextView) v2.findViewById(R.id.time_f)).setText(dir);
        ((TextView) v2.findViewById(R.id.time_f)).setTypeface(face);
        ((TextView) v2.findViewById(R.id.path_f)).setText(uri);
        d.setContentView(v2);
        v2.findViewById(R.id.tvDelete).setOnClickListener(new C15429(d));
        d.show();
    }

    public static void showSettingDialog(final Activity a, DialogBtnClickListener_set mListener, String code) {
        Dialog d = new Dialog(a, R.style.CustomDialogTheme);
        final View v2 = a.getLayoutInflater().inflate(R.layout.setting_dialog, null);
        Typeface face = Typeface.createFromAsset(a.getAssets(), "fa_font_1.ttf");
        ((TextView) v2.findViewById(R.id.textView2)).setTypeface(face);
        ((TextView) v2.findViewById(R.id.help_setting)).setTypeface(face);
        //((EditText) v2.findViewById(R.id.serach_item)).setTypeface(face);
        ((EditText) v2.findViewById(R.id.serach_item)).setHint(code);
        ((TextView) v2.findViewById(R.id.tvCancel)).setTypeface(face);
        ((TextView) v2.findViewById(R.id.tvSave)).setTypeface(face);
        d.setContentView(v2);
        v2.findViewById(R.id.rlSave).setOnClickListener(new C15419(a,mListener,d));
        v2.findViewById(R.id.rlCancel).setOnClickListener(new C15429(d));

        ((EditText) v2.findViewById(R.id.serach_item)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = ((EditText) v2.findViewById(R.id.serach_item)).getText().toString().toLowerCase(Locale.getDefault());
                check(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        d.show();
    }

}
