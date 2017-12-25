package hw3.puzzle;

import edu.princeton.cs.algs4.Queue;



public class Board implements WorldState {3
    private final int[][] board;

    public Board(int[][] tiles) {
        this.board = new int[tiles.length][];
        for (int i = 0; i < tiles.length; i++) {
            this.board[i] = java.util.Arrays.copyOf(tiles[i], tiles[i].length);

        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i > board.length || j < 0 || j > board.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (board[i][j] > 0) {
            return board[i][j];
        } else {
            return 0;
        }
    }

    public int size() {
        return board.length;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == 0) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = 0;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = 0;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int result = 0;
        int counter = 1;
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                if (tileAt(i, j) != counter && tileAt(i, j) != 0) {
                    result = result + 1;
                }
                counter = counter + 1;
            }

        }

        return result;
    }

    private int returnYindex(int num) {
        num = num - 1;
        return num % this.size();
    }

    private int returnXindex(int num) {
        num = num - 1;
        return num / this.size();
    }

    public int manhattan() {
        int tileNum;
        int result = 0;
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                tileNum = this.tileAt(i, j);
                if (tileNum != 0) {
                    int x = returnYindex(tileNum) - j;
                    int y = returnYindex(tileNum) - j;
                    result += Math.abs(returnYindex(tileNum) - j);
                    result += Math.abs(returnXindex(tileNum) - i);
                }
            }
        }

        return result;
    }

    public int estimatedDistanceToGoal() {
        return this.manhattan();
    }

    public boolean isGoal() {
        return this.manhattan() == 0;
    }

    public boolean equals(Object y) {
        if (y instanceof Board) {
            Board o = (Board) y;
            return java.util.Arrays.deepEquals(this.board, o.board);

        }
        return false;

    }
    @Override
    public int hashCode() {
        return 0;
    }


    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
