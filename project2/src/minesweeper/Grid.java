
package minesweeper;

import java.util.Arrays;
import java.util.Random;

public class Grid {

	private boolean[][] bombGrid;
	private int[][] countGrid;
	private int numRows, numColumns;
	private int numBombs;

	public Grid() {

		this.numRows = 10;
		this.numColumns = 10;
		this.numBombs = 25;

		this.createBombGrid();
		this.createCountGrid();

	}

	public Grid(int rows, int columns) {
		this.numRows = rows;
		this.numColumns = columns;
		this.numBombs = 25;
		this.createBombGrid();
		this.createCountGrid();
	}

	public Grid(int rows, int columns, int numBombs) {
		this.numRows = rows;
		this.numColumns = columns;
		this.numBombs = numBombs;
		this.createBombGrid();
		this.createCountGrid();
	}

	
//getter methods
	public int getNumRows() {
		return this.numRows;
	}

	public int getNumColumns() {
		return this.numColumns;
	}

	public int getNumBombs() {
		return this.numBombs;
	}

	public boolean[][] getBombGrid() {
		if (this.bombGrid == null) {
			return null;
		}

		boolean[][] copyBombGrid = new boolean[this.bombGrid.length][];

		for (int i = 0; i < countGrid.length; i++) {
			copyBombGrid[i] = Arrays.copyOf(bombGrid[i], bombGrid[i].length);
		}
		return copyBombGrid;

	}

	public int[][] getCountGrid() {
		if (this.countGrid == null) {
			return null;
		}

		int[][] copyCountGrid = new int[this.countGrid.length][];

		for (int i = 0; i < countGrid.length; i++) {
			copyCountGrid[i] = Arrays.copyOf(countGrid[i], countGrid[i].length);
		}
		return copyCountGrid;
	}

	public boolean isBombAtLocation(int row, int column) {
		if (bombGrid[row][column] == true)
			return true;
		else
			return false;
	}

	public int getCountAtLocation(int row, int column) {
		int bombCount = 0;

		if (row >= 0 && column >= 0 && row < this.numRows && column < this.numColumns) {

			if (bombGrid[row][column] == true) {
				bombCount++;
			}
			if ((row + 1) < numRows && bombGrid[row + 1][column] == true) {
				bombCount++;
			}
			if ((row - 1) >= 0 && bombGrid[row - 1][column] == true) {
				bombCount++;
			}
			if ((column + 1) < numColumns && bombGrid[row][column + 1] == true) {
				bombCount++;
			}
			if ((column - 1) >= 0 && bombGrid[row][column - 1] == true) {
				bombCount++;
			}
			if ((row + 1) < numRows && (column + 1) < numColumns && bombGrid[row + 1][column + 1] == true) {
				bombCount++;
			}
			if ((row - 1) >= 0 && (column - 1) >= 0 && bombGrid[row - 1][column - 1] == true) {
				bombCount++;
			}
			if ((row + 1) < numRows && (column - 1) >= 0 && bombGrid[row + 1][column - 1] == true) {
				bombCount++;
			}
			if ((row - 1) >= 0 && (column + 1) < numColumns && bombGrid[row - 1][column + 1] == true) {
				bombCount++;
			}
			return bombCount;
		}
		return -1;
	}

	private void createBombGrid() {
		bombGrid = new boolean[numRows][numColumns];
		int totalBombs = 0;
		Random x = new Random();
		Random y = new Random();
		int randomRow = x.nextInt(numRows);
		int randomCol = y.nextInt(numColumns);
		while (totalBombs < this.numBombs) {
			randomRow = x.nextInt(numRows);
			randomCol = y.nextInt(numColumns);

			if (bombGrid[randomRow][randomCol] != true) {
				bombGrid[randomRow][randomCol] = true;
				totalBombs++;
			}
		}
	}

	private void createCountGrid() {

		countGrid = new int[numRows][numColumns];

		for (int row = 0; row < numRows; row++) {
			for (int column = 0; column < numColumns; column++) {
				if (bombGrid[row][column] == true) {
					countGrid[row][column]++;
				}
				if ((row + 1) < numRows && bombGrid[row + 1][column] == true) {
					countGrid[row][column]++;
				}
				if ((row - 1) >= 0 && bombGrid[row - 1][column] == true) {
					countGrid[row][column]++;
				}
				if ((column + 1) < numColumns && bombGrid[row][column + 1] == true) {
					countGrid[row][column]++;
				}
				if ((column - 1) >= 0 && bombGrid[row][column - 1] == true) {
					countGrid[row][column]++;
				}
				if ((row + 1) < numRows && (column + 1) < numColumns && bombGrid[row + 1][column + 1] == true) {
					countGrid[row][column]++;
				}
				if ((row - 1) >= 0 && (column - 1) >= 0 && bombGrid[row - 1][column - 1] == true) {
					countGrid[row][column]++;
				}
				if ((row + 1) < numRows && (column - 1) >= 0 && bombGrid[row + 1][column - 1] == true) {
					countGrid[row][column]++;
				}
				if ((row - 1) >= 0 && (column + 1) < numColumns && bombGrid[row - 1][column + 1] == true) {
					countGrid[row][column]++;
				}
			}
		}
	}

}