package com.example.ad00.randaround;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    private TextView textRand;
    private Button evenBtn;
    private Button oddBtn;
    private Button startRandBtn;
    public boolean hasStarted = false;
    private Random rn;
    private static SpotsDialog spotsDialog;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textRand = findViewById(R.id.textRand);
        evenBtn = findViewById(R.id.evenBtn);
        oddBtn = findViewById(R.id.oddBtn);
        startRandBtn = findViewById(R.id.startRandBtn);

        rn = new Random();
        handler = new Handler();

        startRandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  setting to the number blank before there has been a generated number
                textRand.setText("");

                // changing the startRandBtn text to Continue;
                if (!hasStarted) {
                    startRandBtn.setText(getResources().getString(R.string.continue_rand));
                    hasStarted = true;
                }

                spotsDialog(MainActivity.this, "Generating Random Number. . .");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 2s = 2000ms
                        textRand.setText(String.valueOf(rn.nextInt(6) + 1));
                        dismissSpotsDialog();
                    }
                }, 2000);
            }
        });

        oddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // checking first if the start button is clicked
                if (hasStarted) {

                    //  setting to the number blank before there has been a generated number
                    textRand.setText("");

                    int oddRand;
                    oddRand = rn.nextInt(6) + 1;
                    oddRand = (oddRand % 2 == 0) ? --oddRand : oddRand;

                    spotsDialog(MainActivity.this, "Generating Odd Number. . .");

                    final int finalOddRand = oddRand;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 2s = 2000ms

                            textRand.setText(String.valueOf(finalOddRand));
                            dismissSpotsDialog();
                        }
                    }, 2000);
                }

            }
        });

        evenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // checking first if the start button is clicked
                if (hasStarted) {

                    //  setting to the number blank before there has been a generated number
                    textRand.setText("");

                    int evenRand;
                    evenRand = rn.nextInt(6) + 1;
                    evenRand = (evenRand % 2 == 0) ? evenRand : ++evenRand;

                    spotsDialog(MainActivity.this, "Generating Even Number. . .");

                    final int finalEvenRand = evenRand;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 2s = 2000ms

                            textRand.setText(String.valueOf(finalEvenRand));
                            dismissSpotsDialog();
                        }
                    }, 2000);


                }

            }
        });
    }

    public static void  spotsDialog(Activity activity, String message) {
        spotsDialog = new SpotsDialog(activity, message);
        spotsDialog.setCancelable(false);
        spotsDialog.show();
    }

    public static void dismissSpotsDialog() {
        spotsDialog.dismiss();
    }
}
