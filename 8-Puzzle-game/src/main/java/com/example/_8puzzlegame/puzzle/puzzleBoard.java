package com.example._8puzzlegame.puzzle;


import static com.example._8puzzlegame.Constants.*;


public class puzzleBoard {

    private final char[][] board;


    public puzzleBoard() {
        this.board = new char[XO_WIDTH][XO_WIDTH];
        for (int i = 0; i < XO_WIDTH; i++) {
            for (int j = 0; j < XO_WIDTH; j++) {
                this.board[i][j] = '0';
            }
        }
    }

    public puzzleBoard(char[][] board) {
        this.board = board;
    }

}
