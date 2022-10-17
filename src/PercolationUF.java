/**
 * @author Keaton Hawkins Supreme Being
 * @date 11/6/2021
 */

public class PercolationUF implements IPercolate {
    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;
    private int size;

    public PercolationUF(IUnionFind finder, int size) {
        myGrid = new boolean[size][size];
        finder.initialize(size * size + 2);
        myFinder = finder;
        myOpenCount = 0;
        VTOP = size * size;
        VBOTTOM = size * size + 1;
        this.size = size;
    }

    @Override
    public void open(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));
        }

        if (myGrid[row][col]) {
            return;
        }

        myGrid[row][col] = true;
        int intVersion = integer(row, col);

        if (inBounds(row, col + 1) && isOpen(row, col + 1)) {
            int intAdjacent = integer(row, col + 1);
            myFinder.union(intVersion, intAdjacent);
        }
        if (inBounds(row, col - 1) && isOpen(row, col - 1)) {
            int intAdjacent = integer(row, col - 1);
            myFinder.union(intVersion, intAdjacent);
        }
        if (inBounds(row + 1, col) && isOpen(row + 1, col)) {
            int intAdjacent = integer(row + 1, col);
            myFinder.union(intVersion, intAdjacent);
        }
        if (inBounds(row - 1, col) && isOpen(row - 1, col)) {
            int intAdjacent = integer(row - 1, col);
            myFinder.union(intVersion, intAdjacent);
        }

        if (row == 0) {
            myFinder.union(intVersion, VTOP);
        }
        if (row == myGrid.length - 1) {
            myFinder.union(intVersion, VBOTTOM);
        }

        myOpenCount++;
    }

    @Override
    public boolean isOpen(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));
        }

        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));
        }

        int intVersion = integer(row, col);
        return myFinder.connected(intVersion, VTOP);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

    private boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }

    private int integer(int row, int col) {
        return row*myGrid.length + col;
    }
}
