package com.noobtalks.minesweepero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.noobtalks.minesweepero.util.Difficulties.Difficulty;
import com.noobtalks.minesweepero.util.Difficulties;

/**
 * Created by asp_l on 28-Apr-17.
 */

public class Main extends Activity {
    Button button,difficulty;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        button=(Button)findViewById(R.id.button);
        difficulty=(Button)findViewById(R.id.difficulty_button);
        ImageView imageView=(ImageView)findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });

        difficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Main.this, difficulty);
                popup.getMenuInflater().inflate(R.menu.main_action, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.easy /*2131558523*/:
                                setContentView(R.layout.activity_main);
                                GameEngine.getInstance().createGrid(Main.this);
                                GameEngine.getInstance().setDifficulty(Difficulties.Difficulty.EASY);
                                break;
                            case R.id.medium /*2131558524*/:
                               setContentView(R.layout.activity_main);
                                GameEngine.getInstance().createGrid(Main.this);
                                GameEngine.getInstance().setDifficulty(Difficulties.Difficulty.MEDIUM);
                                break;
                            case R.id.hard /*2131558525*/:
                                setContentView(R.layout.activity_main);
                                GameEngine.getInstance().createGrid(Main.this);
                                GameEngine.getInstance().setDifficulty(Difficulties.Difficulty.HARD);
                                break;
                        }
                        Log.e("Main", "Item " + item.getItemId() + " pressed");
                        return true;
                    }
                });

            }
        });

    }

    public void showPopup(View v)
    {
        PopupMenu popup = new PopupMenu(this, difficulty);
        popup.getMenuInflater().inflate(R.menu.actions, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.new_game /*2131558522*/:
                        GameEngine.getInstance().newGame() ;
                        break;
                    case R.id.easy /*2131558523*/:
                        GameEngine.getInstance().setDifficulty(Difficulties.Difficulty.EASY);
                        break;
                    case R.id.medium /*2131558524*/:
                        GameEngine.getInstance().setDifficulty(Difficulties.Difficulty.MEDIUM);
                        break;
                    case R.id.hard /*2131558525*/:
                        GameEngine.getInstance().setDifficulty(Difficulties.Difficulty.HARD);
                        break;
                }
                Log.e("MainActivity", "Item " + item.getItemId() + " pressed");
                return true;
            }
        });
    }
}

