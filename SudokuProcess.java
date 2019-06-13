package leetcodetest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class SudokuProcess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char[][] board = new char[][]{
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };
    SudokuProcess solution = new SudokuProcess();
    solution.printBoard(board);
    solution.solveSudoku(board);
    solution.printBoard(board);
	}
	
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(board[i][j]=='.') continue;
				for (int k = 8; k > j; k--) {
					if(board[i][j]==board[i][k])
						return false;
				}
				for (int k = 8; k > i; k--) {
					if(board[i][j]==board[k][j])
						return false;
				}
				for (int k = i+1; k%3!=0; k++) {
					for (int h = j/3*3; h < j/3*3+3; h++) {
						if(board[i][j] == board[k][h])
							return false;
					}
				}
			}
		}
        return true;
    }
    
    private void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public void solveSudoku(char[][] board) {
    	/**
                            * 记录某行，某位数字是否已经被摆放
         */
        boolean[][] row = new boolean[9][10];
        /**
                            * 记录某列，某位数字是否已经被摆放
         */
        boolean[][] col = new boolean[9][10];
        /**
                            * 记录某 3x3 宫格内，某位数字是否已经被摆放
         */
        boolean[][] block = new boolean[9][10];
        
        for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(board[i][j] != '.') {
					int num  = board[i][j] - '0';
					row[i][num] = true;
					col[j][num] = true;
					block[i / 3 * 3 + j / 3][num] = true;
				}
			}
		}
        dfs(board, row, col, block, 0, 0);
    }

    private boolean dfs(char[][] board, boolean[][] row, boolean[][] col, boolean[][] block, int i, int j) {
    	//寻找空位置
    	while (board[i][j]!='.') {
			if(++j>=9) {
				i++;
				j = 0;
			}
			if(i>=9) {
				return true;
			}
		}
    	for (int num = 1; num <= 9; num++) {
			int blockIndex = i / 3 * 3 + j / 3;
			if(!row[i][num] && !col[j][num] && !block[blockIndex][num]) {
				//递归
				board[i][j] = (char)('0' + num);
				row[i][num] = true;
				col[j][num] = true;
				block[blockIndex][num] = true;
				if(dfs(board, row, col, block, i, j)) {
					return true;
				}else {
					//回溯
					row[i][num] = false;
					col[j][num] = false;
					block[blockIndex][num] = false;
					board[i][j] = '.';
				}
			}
		}
    	return false;
    }
}
