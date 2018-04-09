package com.kangleiit.manipuridictionary.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity implements BaseActivityCallback {
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showProgress() {
        if (progress == null) {
            progress = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
            progress.setCancelable(false);
        }
        progress.show();
    }

    @Override
    public void hideProgress() {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
