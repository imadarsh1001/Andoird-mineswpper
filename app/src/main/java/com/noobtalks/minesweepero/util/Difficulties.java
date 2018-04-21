package com.noobtalks.minesweepero.util;

public class Difficulties {

    public enum Difficulty {
        EASY(9, 9, 10),
        MEDIUM(16, 16, 40),
        HARD(16, 30, 99);
        
        public final int bombNumber;
        public final int height;
        public final int width;

        private Difficulty(int width, int height, int bombnumber) {
            this.width = width;
            this.height = height;
            this.bombNumber = bombnumber;
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

        public int getBombNumber() {
            return this.bombNumber;
        }
    }
}
