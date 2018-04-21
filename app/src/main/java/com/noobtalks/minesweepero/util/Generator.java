package com.noobtalks.minesweepero.util;

import com.noobtalks.minesweepero.GameEngine;
import java.lang.reflect.Array;
import java.util.Random;

public class Generator {

    public static int[][] generate( int bombnumber ,  int width ,  int height) {
        int x = 0;
        Random r = new Random();
        int[][] grid = (int[][]) Array.newInstance(Integer.TYPE, new int[]{width, height});
        /*for (x = 0; x < width; x++) {

        }*/
        while (true) {

            if (x >= width) {
                break;
            }
            grid[x] = new int[height];
            x++;
        }
                while (bombnumber > 0) {
                    x = r.nextInt(width);
                    int y = r.nextInt(height);
                    if (grid[x][y] != -1) {
                        grid[x][y] = -1;
                        bombnumber--;
                    }
                }
                return calculateNeigbours(grid, width, height);
            }




    private static int[][] calculateNeigbours( int[][] grid , int width , int height){
        for( int x = 0 ; x < width ; x++){
            for( int y = 0 ; y < height ; y++){
                grid[x][y] = getNeighbourNumber(grid,x,y,width,height);
            }
        }
        return grid;
    }

    private static int getNeighbourNumber( final int grid[][] , final int x , final int y , final int width , final int height){
        if( grid[x][y] == -1 ){
            return -1;
        }

        int count = 0;

        if( isMineAt(grid,x - 1 ,y + 1,width,height)) count++; // top-left
        if( isMineAt(grid,x     ,y + 1,width,height)) count++; // top
        if( isMineAt(grid,x + 1 ,y + 1,width,height)) count++; // top-right
        if( isMineAt(grid,x - 1 ,y    ,width,height)) count++; // left
        if( isMineAt(grid,x + 1 ,y    ,width,height)) count++; // right
        if( isMineAt(grid,x - 1 ,y - 1,width,height)) count++; // bottom-left
        if( isMineAt(grid,x     ,y - 1,width,height)) count++; // bottom
        if( isMineAt(grid,x + 1 ,y - 1,width,height)) count++; // bottom-right

        return count;
    }

    private static boolean isMineAt( final int [][] grid, final int x , final int y , final int width , final int height){
        if( x >= 0 && y >= 0 && x < width && y < height ){
            if( grid[x][y] == -1 ){
                return true;
            }
        }
        return false;
    }

}

  /*  private static int getNeighbourNumber(  int grid[][] ,  int x ,  int y , int width ,int height) {
        width = -1;
        if (grid[x][y] != -1) {
            height = 0;

            if (isMineAt(grid, x - 1, y + 1)) {
                height = 0+1;
            }
            width=height;
            if (isMineAt(grid, x, y + 1)) {
                width=height+1;
            }
            height=width;
            if (isMineAt(grid, x + 1, y + 1)) {
                height=width+1;
            }
            width=height;
            if (isMineAt(grid, x - 1, y)) {
                width=height+1;
            }
            height=width;
            if (isMineAt(grid, x + 1, y)) {
                height=width+1;
            }
            width=height;
            if (isMineAt(grid, x - 1, y - 1)) {
                width=height+1;
            }
            height=width;
            if (isMineAt(grid, x, y - 1)) {
                height=width+1;
            }
            width=height;
            if (isMineAt(grid, x + 1, y - 1)) {
                return height + 1;
            }
        }
        return width;
    }
    private static boolean isMineAt(  int [][] grid, int x , int y){
        boolean v;
        if( x >= 0 && y >= 0 && x<GameEngine.getInstance().WIDTH && y < GameEngine.getInstance().HEIGHT && grid[x][y]!=1 ){
                v=true;
            }
            else {
            v= false;
        }
        return v;
    }
}
*/