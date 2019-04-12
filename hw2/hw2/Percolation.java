package hw2;
/**
 * @author yue
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.List;

public class Percolation {
    private int[][] openArray;
    private int openSize;
    private WeightedQuickUnionUF percolateSet;
    private WeightedQuickUnionUF isFullSet;
    private int N;  //edge size
    private int source;
    private int receiver;

    /** create N-by-N grid, with all sites initially blocked
     */
    public Percolation(int N) {
        openArray = new int[N][N];
        openSize = 0;
        percolateSet = new WeightedQuickUnionUF(N * N + 2);
        isFullSet = new WeightedQuickUnionUF(N * N + 2);
        this.N = N;
        source = N * N;
        receiver = N * N + 1;
    }

    /** change grid axis (x,y) to disjint set number*/
    private int getNum(int x, int y) {
        return x * N + y;
    }

    /** check if (x,y) is in bound*/
    private boolean inBound(int row, int col) {
        return (row >= 0 && col >= 0 && row < N && col < N);
    }

    /**open the site (row, col) if it is not open already*/
    public void open(int row, int col) {
        if (!inBound(row, col)) {
            throw new IndexOutOfBoundsException("illegeal entry");
        }
        openArray[row][col] = 1;
        openSize += 1;
        int num = getNum(row, col);

        //if first row, then connect to water source
        if (row == 0) {
            percolateSet.union(source, num);
            isFullSet.union(source, num);
        }
        // if last row, connect to water reciver
        if (row == N - 1) {
            percolateSet.union(receiver, num);
        }

        List<Integer> neighbors = List.of(-1, +1, -N, +N);
        for (int ind : neighbors) {
            int num2 = num + ind;
            int x = num2 / N;
            int y = num2 % N;
            if (!inBound(x, y)) continue;
            if (openArray[x][y] == 1) {
                percolateSet.union(num, num2);
                isFullSet.union(num, num2);
            }

        }


    }

    /** check if the site (row, col) is open*/
    public boolean isOpen(int row, int col) {
        if (!inBound(row, col)) {
            throw new IndexOutOfBoundsException("illegeal entry");
        }
        return openArray[row][col] == 1;
    }

    /** number of open sites */
    public int numberOfOpenSites() {
        return openSize;
    }

    public boolean isFull(int row, int col) {
        if (!inBound(row, col)) {
            throw new IndexOutOfBoundsException("illegeal entry");
        }
        int num = getNum(row, col);
        return isFullSet.connected(num, source);

    }

    /** returns if the system is perlocated
     *
     */
    public boolean percolates() {
        return percolateSet.connected(source, receiver);
    }

    /** use for unit testing*/
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(0, 1);
        p.open(1,1);
        p.open(2,1);
        Boolean t = p.percolates();

    }
}
