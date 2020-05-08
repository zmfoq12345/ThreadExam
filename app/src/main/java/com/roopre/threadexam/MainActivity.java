package com.roopre.threadexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String TAG = getClass().getSimpleName();

    TextView progress_tv;
    ProgressBar progressBar;
    Button start_btn;

    Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress_tv = findViewById(R.id.progress_tv);
        progressBar = findViewById(R.id.progressBar);
        start_btn = findViewById(R.id.start_btn);

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoDownload();
            }
        });

    }

    private void DoDownload(){
        TestAsyncTask testAsyncTask = new TestAsyncTask();
        testAsyncTask.execute("test");
    }

    class TestAsyncTask extends AsyncTask<String, Integer, String>{

        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this, "시작 됨", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG, "params[0] = "+params[0]);
            for(int i=0;i<=100;i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return params[0];
        }

        @Override
        protected void onPostExecute(String s) {

            Toast.makeText(MainActivity.this, "작업 완료", Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            progress_tv.setText( values[0] +"%");
            progressBar.setProgress(values[0]);
        }
    }




}
