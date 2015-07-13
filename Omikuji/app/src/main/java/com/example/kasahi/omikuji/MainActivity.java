package com.example.kasahi.omikuji;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getFortune(View view) {
        TextView textView = (TextView) findViewById(R.id.fortuneText);
        Random randomGenerator = new Random();
        String[] results = {
                "大吉",
                "中吉",
                "小吉",
                "吉",
                "凶",
                "大凶"
        };
        int num = randomGenerator.nextInt(results.length);
        //String result = Integer.toString(num);
        if (num == 0) {
            textView.setTextColor(Color.RED);
            //textView.setTextColor(Color.argb(127, 255, 0, 0));  // 透過させる
            //textView.setTextColor(Color.parseColor("red"));
            //textView.setTextColor(Color.parseColor("#ff0000"));
        } else {
            textView.setTextColor(Color.BLACK);
        }
        textView.setText(results[num]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
