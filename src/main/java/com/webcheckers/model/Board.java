package com.webcheckers.model;

/**
 * Model tier class that represents the checkers board.
 *
 * @author Dan Wang
 * @author Emily Lederman
 * @author Kevin Paradis
 * @author Nathan Farrell
 */
public class Board {
    //TODO build up this class- create 2D array, initialize with pieces, etc.

    private Space[][] board;

    //Used to determine the size of the board
    public static final int size = 8;

//    Iterator<Space> spaceIterator = new Iterator<Space>() {
//
//        @Override
//        public boolean hasNext() {
//            return false;
//        }
//
//        @Override
//        public Space next() {
//            return null;
//        }
//    };

    /**
     * Creates a board used for keeping track of the state
     * of the game.
     *
     * @throws Exception occurs if the given cellIdx is greater or less than
     * the bounds established by a standard game board
     */
    public Board() throws Exception {
        this.board = new Space[size][size];

        //Iterates through the board to populate array indices
        //with spaces
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                //Black spaces
                if ((i%2 == 0 && j%2 == 1) || (i%2 == 1 && j%2 == 0)) {
                    this.board[i][j] = new Space(i, j, Space.Color.BLACK);
                }
                //White spaces
                else {
                    this.board[i][j] = new Space(i, j, Space.Color.WHITE);
                }
            }
        }
    }

    /**
     * Places all of the pieces on the board to represent a new game.
     *
     * @return a boolean value of whether or not the board was setup
     * properly
     */
    public boolean newGame() {
        //Places white pieces at the top of the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < size; j++) {

                Space space = this.board[i][j];
                if (space.isValid()) {
                    space.setPiece(new Piece(Piece.Color.WHITE,
                            Piece.Type.SINGLE));
                }
                else {
                    return false;
                }
            }
        }

        //Places red pieces at the bottom of the board
        for (int i = size - 3; i < size; i++) {
            for (int j = 0; j < size; j++) {

                Space space = this.board[i][j];
                if (space.isValid()) {
                    space.setPiece(new Piece(Piece.Color.RED,
                            Piece.Type.SINGLE));
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }
}
