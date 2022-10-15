package com.example._8puzzlegame.puzzle;



public class puzzleBoard {

    private final char[][] board;


    public puzzleBoard() {
        this.board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = '0';
            }
        }
    }

    public puzzleBoard(char[][] board) {
        this.board = board;
    }

}
