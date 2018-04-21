package com.noobtalks.minesweepero.views.statusbar;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import com.noobtalks.minesweepero.GameEngine;
import com.noobtalks.minesweepero.GameEngine.GameStates;
import com.noobtalks.minesweepero.R;

public class GameStatusButton extends ImageButton implements OnTouchListener {
    private Context context;
    private Drawable lose;
    private Drawable normal;
    private Drawable pressed;
    private Resources res = getResources();
    private Drawable win;


    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchGame = new int[GameStates.values().length];

        static {
            try {
                $SwitchGame [GameStates.PLAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchGame [GameStates.WON.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchGame [GameStates.LOST.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public GameStatusButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setScaleType(ScaleType.CENTER_CROP);
        setOnTouchListener(this);
        getDrawables();
        setImage(GameStates.PLAY);
    }

    public void setImage(GameStates state) {
        switch (AnonymousClass1.$SwitchGame[state.ordinal()]) {
            case R.styleable.View_android_focusable /*1*/:
                setImageDrawable(this.normal);
                return;
            case R.styleable.View_paddingStart /*2*/:
                setImageDrawable(this.win);
                return;
            case R.styleable.View_paddingEnd /*3*/:
                setImageDrawable(this.lose);
                return;
            default:
                return;
        }
    }

    private void getDrawables() {
        this.normal = this.res.getDrawable(R.drawable.face_normal);
        this.pressed = this.res.getDrawable(R.drawable.face_pressed);
        this.win = this.res.getDrawable(R.drawable.face_win);
        this.lose = this.res.getDrawable(R.drawable.face_lose);
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case R.styleable.View_android_theme /*0*/:
                setImageDrawable(this.pressed);
                break;
            case R.styleable.View_android_focusable /*1*/:
                setImageDrawable(this.normal);
                GameEngine.getInstance().createGrid(this.context);
                break;
        }
        return false;
    }
}
