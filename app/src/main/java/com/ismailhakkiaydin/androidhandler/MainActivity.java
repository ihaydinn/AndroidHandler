package com.ismailhakkiaydin.androidhandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Thread thread;
    ProgressBar progressBar;
    TextView textView;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);
        thread = new Thread(new MyThread());
        thread.start();
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                progressBar.setProgress(msg.arg2);
                textView.setText("" + msg.arg1);
            }
        };
    }


    private class MyThread implements Runnable {

        int i = 0;

        @Override
        public void run() {

            for (i = 0; i < 100; i++) {

                try {
                    Thread.sleep(500);
                    /*runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(""+i);
                        }
                    });*/

                    /*textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(""+i);
                        }
                    });*/

                    Message mesajim = Message.obtain();
                    mesajim.arg1 = i;
                    mesajim.arg2 = i;

                    //handler.sendMessage(mesajim);
                    handler.sendMessageDelayed(mesajim, 1000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        }
    }
}