package com.noobtalks.minesweepero;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.view.MenuItem;
import com.noobtalks.minesweepero.util.Difficulties.Difficulty;

public class MainActivity extends Activity {

    MediaPlayer player;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("main","error");
        GameEngine.getInstance().createGrid(this);
        Log.e("main","error");
      if (GameEngine.getInstance().getCurrentGameState()== GameEngine.GameStates.WON){
          player=MediaPlayer.create(this,R.raw.victory);
          player.start();
      }
     if(GameEngine.getInstance().getCurrentGameState()== GameEngine.GameStates.LOST)
     {
         player=MediaPlayer.create(this,R.raw.mine);
         player.start();
     }
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.actions, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.new_game /*2131558522*/:
                        GameEngine.getInstance().newGame() ;

                        break;
                    case R.id.easy /*2131558523*/:
                        GameEngine.getInstance().setDifficulty(Difficulty.EASY);
                        break;
                    case R.id.medium /*2131558524*/:
                        GameEngine.getInstance().setDifficulty(Difficulty.MEDIUM);
                        break;
                    case R.id.hard /*2131558525*/:
                        GameEngine.getInstance().setDifficulty(Difficulty.HARD);
                        break;
                }
                Log.e("MainActivity", "Item " + item.getItemId() + " pressed");
                return true;
            }
        });
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();

    }

    protected void onDestroy() {
        super.onDestroy();
    }
}