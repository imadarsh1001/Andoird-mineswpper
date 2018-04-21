package com.noobtalks.minesweepero.views.statusbar;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class BombCounterView extends android.support.v7.widget.AppCompatTextView {
    private String bombString;
    private int bombsRemaining = 0;
    private Context context;

    public BombCounterView(Context context, AttributeSet attrs) {
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
        this.bombsRemaining = 0;
        showBombs();
    }

    public int getBombsRemaining() {
        return this.bombsRemaining;
    }

    public void updateBombs(int bombsRemaining) {
        this.bombsRemaining = bombsRemaining;
        showBombs();
    }

    private void showBombs() {
        this.bombString = String.format("%03d", new Object[]{Integer.valueOf(this.bombsRemaining)});
        setText(this.bombString);
    }
}
