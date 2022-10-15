package com.example._8puzzlegame.puzzle;


import com.example._8puzzlegame.pieces.num;

public class puzzleBoard {

    private final num[][] board;


    public puzzleBoard() {
        this.board = new num[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = new num();
            }
        }
    }

    public puzzleBoard(num[][] board) {
        this.board = board;
    }

}
