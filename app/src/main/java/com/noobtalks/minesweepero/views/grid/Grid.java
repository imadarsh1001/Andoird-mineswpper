package com.noobtalks.minesweepero.views.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import com.noobtalks.minesweepero.GameEngine;


public class Grid extends GridView{

    private class MineSweeperGridAdapter extends BaseAdapter {
        private MineSweeperGridAdapter() {
        }

        public int getCount() {
            return GameEngine.getInstance().WIDTH * GameEngine.getInstance().HEIGHT;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            return GameEngine.getInstance().getCellAt(position);
        }
    }

    public Grid(Context context) {
        super(context);
        setNumColumns(GameEngine.getInstance().WIDTH);
        setAdapter(new MineSweeperGridAdapter());
    }

    public Grid(Context context, AttributeSet attrs) {
        this(context);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
