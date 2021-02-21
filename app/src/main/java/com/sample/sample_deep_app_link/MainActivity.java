package com.sample.sample_deep_app_link;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SCREEN1_LINK = "https://deepakdroid.page.link/qbvQ";
    private static final String SCREEN2_LINK = "https://deepakdroid.page.link/jofZ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btScreen1).setOnClickListener(this);
        findViewById(R.id.btScreen2).setOnClickListener(this);

        openSpecificScreen();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btScreen1) {
            // open screen 1 link
            openLink(SCREEN1_LINK);
        } else if (v.getId() == R.id.btScreen2) {
            // open screen2 link
            openLink(SCREEN2_LINK);
        }
    }

    // open the provided link via intent
    private void openLink(String link) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        openSpecificScreen();
    }

    // open specific screen from Android Intent
    private void openSpecificScreen() {
        Intent intent = getIntent();
        if (intent != null) {
            String action = intent.getAction();
            Uri data = intent.getData();

            if (data != null) {
                // Here just comparing the urls
                // not using the Firebase dynamic link library to get the deep link from FDL

                // we have the data from Intent
                if (data.equals(Uri.parse(SCREEN1_LINK))) {
                    // open screen 1
                    startActivity(new Intent(this, Screen1.class));
                } else if (data.equals(Uri.parse(SCREEN2_LINK))) {
                    // open screen 2
                    startActivity(new Intent(this, Screen2.class));
                }
            }
        }
    }
}