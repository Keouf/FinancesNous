package com.example.pcportablevidjay.financesnous;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoadingScreenActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(500);
                } catch (Exception e) {

                } finally {

                    Intent i = new Intent(LoadingScreenActivity.this,
                            Accueil_Activity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }

}
