package com.noobtalks.minesweepero.views.grid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import com.noobtalks.minesweepero.GameEngine;
import com.noobtalks.minesweepero.R;


public class Cell extends BaseCell implements OnClickListener, OnLongClickListener {
    private Resources res;

    public Cell(Context context, int x, int y) {
        super(context);
        setOnClickListener(this);
        setOnLongClickListener(this);
        setPosition(x, y);
        this.res = getResources();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isFlagged()) {
            drawFlag(canvas);
        } else if (isRevealed() && isBomb() && !isClicked()) {
            drawNormalBomb(canvas);
        } else {
            if (isClicked()) {
                if (getValue() == -1) {
                    drawBombExploded(canvas);
                } else {
                    drawNumber(canvas);
                }
            } else {
                drawButton(canvas);
            }
        }
    }
    private void drawFlag(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.flag);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    private void drawButton(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.button);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    private void drawBombExploded(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_exploded);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    private void drawNormalBomb(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_normal);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    private void drawNumber(Canvas canvas) {
        Drawable drawable = null;
        switch (getValue()) {
            case 0 /*0*/:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_0);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_1);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_3);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_4);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_5);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_6);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_7);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_8);
                break;
        }
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    public void onClick(View v) {
        GameEngine.getInstance().click(getXPos(), getYPos());
        invalidate();
    }

    public boolean onLongClick(View v) {
        GameEngine.getInstance().flag(getXPos(), getYPos());
        return true;
    }
}
