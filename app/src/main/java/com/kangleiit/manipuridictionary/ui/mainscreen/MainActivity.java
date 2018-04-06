package com.kangleiit.manipuridictionary.ui.mainscreen;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kangleiit.manipuridictionary.R;
import com.kangleiit.manipuridictionary.ui.account.AccountActivity;
import com.kangleiit.manipuridictionary.ui.searchResult.SearchResultFragment;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    ListView navList;
    LinearLayout dragView;
    FrameLayout fl;
    AppBarLayout appbar;
    public static Toolbar toolbar;
    NavigationView navigationView;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvSpeechInput)
    TextView tvSpeechInput;
    @BindView(R.id.ivSpeak)
    ImageView ivSpeak;
    @BindView(R.id.content)
    RelativeLayout content;
    @BindView(R.id.listcolor)
    LinearLayout listcolor;
    @BindView(R.id.list)
    ListView list;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    SearchDialog searchDialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchDialog = new SearchDialog(MainActivity.this, R.style.DialogTheme);
        ButterKnife.bind(this);
        init();
        setupNavigation();
        tvSpeechInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchDialog.show();
                //startActivity(new Intent(MainActivity.this, SearchActivity.class));
                /*SearchFragment fragment=SearchFragment.newInstance("");
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment,fragment).commit();*/
            }
        });
    }

    void setupNavigation() {
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }
        };
        drawer.setScrimColor(Color.TRANSPARENT);
        drawer.setElevation(0);
        drawer.setDrawerElevation(0f);
        drawer.setDrawerListener(toggle);


        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
                toolbar.setNavigationIcon(null);

            }
        });

        drawer.setScrimColor(Color.TRANSPARENT);
        drawer.setElevation(0);
        drawer.setDrawerElevation(0f);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);

            }
        });
        //getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment, new MainFragment()).commit();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
        NavAdapter adapter = new NavAdapter(this);
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //toolbar.setNavigationIcon(R.drawable.ic_menu);
                drawer.closeDrawer(GravityCompat.START);

                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, AccountActivity.class));
                        break;
                    case 4:
                        break;
                    case 5:
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"yogitadocs2@gmail.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Hi!!! Please check this app.");
                        startActivity(Intent.createChooser(intent, "Contact Us!"));
                        break;
                    case 6:

                        break;
                    case 7:
                        break;
                }
            }

        });
    }

    void init() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navList = (ListView) findViewById(R.id.list);
        fl = (FrameLayout) findViewById(R.id.fragment);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appbar = (AppBarLayout) findViewById(R.id.appbar);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    @OnClick({R.id.tvTitle, R.id.ivSpeak, R.id.content, R.id.listcolor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvTitle:
                break;
            case R.id.ivSpeak:
                promptSpeechInput();
                break;
            case R.id.content:
                break;
            case R.id.listcolor:
                break;
        }
    }

    /**
     * Showing google speech input dialog
     */
    private void promptSpeechInput() {
        Intent intent;
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    searchDialog.search(result.get(0));
                }
                break;
            }

        }
    }

    void openSearchFragment(ArrayList<String> result) {
        SearchResultFragment searchFragment = new SearchResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString("searchKey", result.get(0));
        searchFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment, searchFragment).commit();
    }

    void hideKeyBoard() {
        View view1 = getCurrentFocus();
        if (view1 != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }
    }
}