/**
 * @author Keaton Hawkins Supreme Being
 * @date 11/6/2021
 */


import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast {

    public PercolationBFS(int size) {
        super(size);
    }

    @Override
    protected void dfs(int row, int col) {
        if (myGrid[row][col] == FULL || myGrid[row][col] == BLOCKED) {
            return;
        }

        myGrid[row][col] = FULL;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row,col});

        while (q.size() > 0) {
            int[] cell = q.poll();
            if (cell[1] + 1 <= myGrid[0].length - 1 && myGrid[cell[0]][cell[1] + 1] == OPEN) {
                myGrid[cell[0]][cell[1] + 1] = FULL;
                q.add(new int[] {cell[0], cell[1] + 1});
            }
            if (cell[1] - 1 >= 0 && myGrid[cell[0]][cell[1] - 1] == OPEN) {
                myGrid[cell[0]][cell[1] - 1] = FULL;
                q.add(new int[] {cell[0], cell[1] - 1});
            }
            if (cell[0] + 1 <= myGrid.length - 1 && myGrid[cell[0] + 1][cell[1]] == OPEN) {
                myGrid[cell[0] + 1][cell[1]] = FULL;
                q.add(new int[] {cell[0] + 1, cell[1]});
            }
            if (cell[0] - 1 >= 0 && myGrid[cell[0] - 1][cell[1]] == OPEN) {
                myGrid[cell[0] - 1][cell[1]] = FULL;
                q.add(new int[] {cell[0] - 1, cell[1]});
            }
        }
    }
}
