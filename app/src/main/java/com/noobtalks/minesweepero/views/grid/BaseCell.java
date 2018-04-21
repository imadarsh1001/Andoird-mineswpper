package com.noobtalks.minesweepero.views.grid;

import android.content.Context;
import android.view.View;
import com.noobtalks.minesweepero.GameEngine;


public abstract class BaseCell extends View {
    private boolean isBomb;
    private boolean isClicked;
    private boolean isFlagged;
    private boolean isRevealed;
    private int position;
    private int value;
    private int x;
    private int y;

    public BaseCell(Context context) {
        super(context);
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.isBomb = false;
        this.isRevealed = false;
        this.isClicked = false;
        this.isFlagged = false;
        if (value == -1) {
            this.isBomb = true;
        }
        this.value = value;
    }

    public boolean isFlagged() {
        return this.isFlagged;
    }

    public void setFlagged(boolean isFlagged) {
        this.isFlagged = isFlagged;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.position = (GameEngine.getInstance().WIDTH * y) + x;
        this.invalidate();
    }

    public int getYPos() {
        return this.y;
    }

    public int getXPos() {
        return this.x;
    }

    public void setRevealed() {
        this.isRevealed = true;
    }

    public boolean isRevealed() {
        return this.isRevealed;
    }

    public boolean isClicked() {
        return this.isClicked;
    }

    public void setClicked() {
        this.isClicked = true;
        this.isRevealed = true;
        invalidate();
    }

    public boolean isBomb() {
        return this.isBomb;
    }
}
