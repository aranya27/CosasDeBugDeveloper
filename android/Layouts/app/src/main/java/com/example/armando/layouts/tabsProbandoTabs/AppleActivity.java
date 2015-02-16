package com.example.armando.layouts.tabsProbandoTabs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by armando on 15/02/2015.
 */
public class AppleActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is Apple tab");
        setContentView(textview);
    }
}
