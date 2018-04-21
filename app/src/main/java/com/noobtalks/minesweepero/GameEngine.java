package com.noobtalks.minesweepero;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.widget.Toast;
import android.os.Handler;
import android.widget.RelativeLayout;
import com.noobtalks.minesweepero.util.Difficulties;
import com.noobtalks.minesweepero.util.Generator;
import com.noobtalks.minesweepero.views.grid.Cell;
import com.noobtalks.minesweepero.views.grid.Grid;
import com.noobtalks.minesweepero.views.statusbar.BombCounterView;
import com.noobtalks.minesweepero.views.statusbar.GameStatusButton;
import com.noobtalks.minesweepero.views.statusbar.TimeCounterView;
import java.lang.reflect.Array;

public class  GameEngine {
    private static GameEngine instance;
    public int BOMB_NUMBER ;
    public int HEIGHT ;
    public int WIDTH ;
    private Cell[][] MineSweeperGrid ;
    public BombCounterView bombCounterView;
    private Context context;
    private GameStates currentGameState = GameStates.PLAY;
    private Difficulties.Difficulty difficulty;
    private GameStatusButton gameStatusButton;
    private Handler handler;
    public TimeCounterView timeCounterView;
    MediaPlayer player;
    private GameEngine() {
        this.difficulty = Difficulties.Difficulty.HARD;
        this.WIDTH = this.difficulty.getWidth();
        this.HEIGHT = this.difficulty.getHeight();
        this.BOMB_NUMBER = this.difficulty.getBombNumber();
        this.currentGameState = GameEngine.GameStates.PLAY;
        this.MineSweeperGrid = (Cell[][])Array.newInstance(Cell.class, new int[]{this.WIDTH, this.HEIGHT});
    }

    public enum GameStates {
        PLAY,
        WON,
        LOST
    }

    public void newGame() {
        createGrid(this.context);
    }

    public static GameEngine getInstance() {
        if( instance == null ){
            instance = new GameEngine();
        }
        return instance;
    }

    public void createGrid(Context context){
        this.context = context;
        this.WIDTH = this.difficulty.getWidth();
        this.HEIGHT = this.difficulty.getHeight();
        this.BOMB_NUMBER = this.difficulty.getBombNumber();
        Activity act = (Activity) context;
        RelativeLayout gridContainer = (RelativeLayout) act.findViewById(R.id.gridContainer);
        gridContainer.removeAllViews();
        gridContainer.addView(new Grid(context));
        this.gameStatusButton = (GameStatusButton) act.findViewById(R.id.gameStatusButton);
        this.timeCounterView = (TimeCounterView) ((Activity) context).findViewById(R.id.timecounter);
        this.timeCounterView.init();
        this.bombCounterView = (BombCounterView) ((Activity) context).findViewById(R.id.bombCounter);
        this.bombCounterView.init();
        this.bombCounterView.updateBombs(this.BOMB_NUMBER);
        setGrid(context, Generator.generate(this.BOMB_NUMBER, this.WIDTH, this.HEIGHT));
        this.currentGameState = GameStates.PLAY;
        onGameStart();
    }
    private void setGrid(Context context, int[][] grid) {
        for (int x = 0; x < this.WIDTH; x++) {
            for (int y = 0; y < this.HEIGHT; y++) {
                if (this.MineSweeperGrid[x][y] == null) {
                    this.MineSweeperGrid[x][y] = new Cell(context, x, y);
                }
                this.MineSweeperGrid[x][y].setValue(grid[x][y]);
                this.MineSweeperGrid[x][y].invalidate();
            }
        }
    }
    public void setDifficulty(Difficulties.Difficulty diff) {
        this.difficulty = diff;
        createGrid(this.context);
    }
    public Cell getCellAt(int position) {
        int x = position % WIDTH;
        int y = position / WIDTH;

        return MineSweeperGrid[x][y];
    }

    public Cell getCellAt( int x , int y ){
        return MineSweeperGrid[x][y];
    }

    public void click( int x , int y ){
        if(!this.timeCounterView.isStarted) {
            this.timeCounterView.start();
        }
        if(  x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !getCellAt(x,y).isClicked() ){
            getCellAt(x,y).setClicked();
            if( getCellAt(x,y).getValue() == 0 ){
                for( int xt = -1 ; xt <= 1 ; xt++ ){
                    for( int yt = -1 ; yt <= 1 ; yt++){
                        if( xt != yt ){
                            click(x + xt , y + yt);
                        }
                    }
                }
            }

            if( getCellAt(x,y).isBomb() ){
                onGameLost();
            }
        }

        checkEnd();
    }

    private boolean checkEnd(){
        int bombNotFound = this.BOMB_NUMBER;
        int notRevealed = this.WIDTH * this.HEIGHT;
        int x = 0;
        while (x < this.WIDTH) {
            int y = 0;
            while (y < this.HEIGHT) {
                if (getCellAt(x, y).isRevealed() || getCellAt(x, y).isFlagged()) {
                    notRevealed--;
                }
                if (getCellAt(x, y).isFlagged() && getCellAt(x, y).isBomb()) {
                    bombNotFound--;
                }
                y++;
            }
            x++;
        }
        if (bombNotFound == 0 && notRevealed == 0) {
            this.currentGameState = GameStates.WON;
            this.gameStatusButton.setImage(GameStates.WON);
            this.timeCounterView.stop();
        }
        return false;
    }

    public void flag(int x, int y) {
        boolean isFlagged = getInstance().getCellAt(x, y).isFlagged();
        getCellAt(x, y).setFlagged(!isFlagged);
        getCellAt(x, y).invalidate();
        if (isFlagged) {
            this.bombCounterView.updateBombs(this.bombCounterView.getBombsRemaining() + 1);
        } else {
            this.bombCounterView.updateBombs(this.bombCounterView.getBombsRemaining() - 1);
        }
        checkEnd();
    }

    public GameStates getCurrentGameState() {
        return this.currentGameState;
    }
    private void onGameLost(){
        this.timeCounterView.stop();
        this.currentGameState = GameEngine.GameStates.LOST;
        for ( int x = 0 ; x < this.WIDTH ; x++ ) {
            for (int y = 0; y < this.HEIGHT; y++) {
                this.getCellAt(x,y).setRevealed();
                this.getCellAt(x,y).invalidate();
            }
        }
        this.gameStatusButton.setImage(GameEngine.GameStates.LOST);
        this.timeCounterView.stop();
    }

  private MediaPlayer createMediaplayer(String str){

    return  null;
  }

    public boolean isGameEnded() {
        boolean v;
        if(this.currentGameState == GameStates.LOST || this.currentGameState == GameStates.WON)
        {
            v =false;
        }
        else{
            v=true;
        }
        return v ;
    }
    public void onGameStart() {

        if (this.handler == null) {
            this.handler = new Handler();
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    GameEngine.this.timeCounterView.updateTime();
                    GameEngine.this.handler.postDelayed(this, 1000);
                }
            }, 1000);
        }
    }
}
