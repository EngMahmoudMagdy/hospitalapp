package com.hospitalapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hospitalapp.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try {
            new Thread() {
                public void run() {
                    try {
                        sleep(SPLASH_TIME_OUT);
                        Intent i;
                        i = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(i);
                        finish();

                       /* try {
                            OkHttpClient client = new OkHttpClient();
                            final Request request = new Request.Builder()
                                    .url("https://zari.spreago.com/update")
                                    .addHeader("Authorization", "Bearer " + PrefManager.getInstance(getBaseContext()).getAPIToken())
                                    .get()
                                    .build();
                            client.newCall(request).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                                }

                                @Override
                                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                                }
                            });
                        } catch (Exception e) {

                        }*/

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
