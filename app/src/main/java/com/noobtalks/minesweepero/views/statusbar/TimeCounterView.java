package com.noobtalks.minesweepero.views.statusbar;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class TimeCounterView extends android.support.v7.widget.AppCompatTextView {
    private Context context;
    private int currentTime = 0;
    public boolean isStarted = false;
    private String timeString;

    public TimeCounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public void init() {
        Typeface tf = null;
        try {
            tf = Typeface.createFromAsset(getResources().getAssets(), "fonts/digital_dismay.otf");
        } catch (Exception e) {
            Log.e("TimeCounterView", "Could not get typeface: " + e.getMessage());
        }
        setTypeface(tf);
        this.currentTime = 0;
        this.isStarted = false;
        showTime();
    }

    public void updateTime() {
        if (this.isStarted) {
            if (this.currentTime <= 1000) {
                this.currentTime++;
            }
            showTime();
        }
    }

    public void start() {
        this.isStarted = true;
    }

    public void stop() {
        this.isStarted = false;
    }

    private void showTime() {
        this.timeString = String.format("%03d", new Object[]{Integer.valueOf(this.currentTime)});
        setText(this.timeString);
    }
}
