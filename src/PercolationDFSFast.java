/**
 * @author Keaton Hawkins Supreme Being
 * @date 11/6/2021
 */

public class PercolationDFSFast extends PercolationDFS{

    public PercolationDFSFast(int size) {
        super(size);
    }

    @Override
    protected void updateOnOpen(int row, int col) {
        if (row == 0) {
            dfs(row, col);
        } else if (col + 1 <= myGrid[0].length - 1 && myGrid[row][col + 1] == FULL) {
            dfs(row, col);
        } else if (col - 1 >= 0 && myGrid[row][col - 1] == FULL) {
            dfs(row, col);
        } else if (row + 1 <= myGrid.length - 1 && myGrid[row + 1][col] == FULL) {
            dfs(row, col);
        } else if (row - 1 >= 0 && myGrid[row - 1][col] == FULL) {
            dfs(row, col);
        }
    }
}
