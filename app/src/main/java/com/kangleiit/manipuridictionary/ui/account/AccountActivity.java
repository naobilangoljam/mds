package com.kangleiit.manipuridictionary.ui.account;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.kangleiit.manipuridictionary.R;

public class AccountActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    GoogleApiClient mGoogleApiClient;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail;
    String personName, email, personPhotoUrl;
    Button btn_sign_out;
    LinearLayout llColorTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initViews();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, AccountActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        Bundle bundle = getIntent().getExtras();
        bundle = new Bundle();
        bundle.putString("email", "rblangso@gmail.com");
        bundle.putString("personPhotoUrl", "https://lh3.googleusercontent.com/-uq5P8OxihAM/AAAAAAAAAAI/AAAAAAAAACI/wssP64qxNGM/s60-p-rw-no/photo.jpg");
        bundle.putString("personName", "Radhabinod Langoljam");
        if (bundle != null) {
            personName = bundle.getString("personName");
            email = bundle.getString("email");
            personPhotoUrl = bundle.getString("personPhotoUrl");
            txtName.setText(personName);
            txtEmail.setText(email);
            Glide.with(this).load(personPhotoUrl).apply(RequestOptions.circleCropTransform()).into(imgProfilePic);

        }
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
                finish();
            }
        });
        llColorTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorThemeDialog();
            }
        });
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void initViews() {
        imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
        txtName = (TextView) findViewById(R.id.txtName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        btn_sign_out = (Button) findViewById(R.id.btn_sign_out);
        llColorTheme = (LinearLayout) findViewById(R.id.llColorTheme);
    }

    void openColorThemeDialog() {
        final Dialog dialogBuilder = new Dialog(AccountActivity.this);
        dialogBuilder.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialogBuilder.setContentView(R.layout.custom_alert);
        TextView tvCancel = (TextView) dialogBuilder.findViewById(R.id.tvCancel);
        final RadioButton rbYellow = (RadioButton) dialogBuilder.findViewById(R.id.rbYellow);
        final RadioButton rbBlue = (RadioButton) dialogBuilder.findViewById(R.id.rbBlue);
        final RadioButton rbOrange = (RadioButton) dialogBuilder.findViewById(R.id.rbOrange);
        final RadioButton rbPink = (RadioButton) dialogBuilder.findViewById(R.id.rbPink);
        final ActionBar actionBar = getSupportActionBar();
        rbYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f5a330")));
                dialogBuilder.dismiss();
                rbYellow.setChecked(true);
            }
        });
        rbPink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4081")));
                dialogBuilder.dismiss();
                rbPink.setChecked(true);
            }
        });
        rbBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
                dialogBuilder.dismiss();
                rbBlue.setChecked(true);
            }
        });
        rbOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f55830")));
                dialogBuilder.dismiss();
                rbOrange.setChecked(true);
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.show();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogBuilder.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogBuilder.getWindow().setAttributes(lp);
        dialogBuilder.show();
    }
}
