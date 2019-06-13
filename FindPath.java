package leetcodetest;

public class FindPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public int climbStairs(int n) {
    	/*if(n==0) {
    		return 1;
    	}if(n==1) {
    		return 1;
    	}
        int totalNum = 0;
        if(n-2>=0) {
        	totalNum = climbStairs(n-1) + climbStairs(n-2);
        }
        return totalNum;*/
    	if(n <= 1) return 1;
        int[] nums = new int[n];
        nums[0] = 1;
        nums[1] = 2;
        for(int i=2; i<n; i++){
            nums[i] = nums[i-1] + nums[i-2];
        }
        return nums[n-1];
    }
	
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(i==0 && j==0) {
					dp[i][j] = grid[i][j];
				}
				else if(i==0 && j!=0) {
					dp[i][j] = dp[i][j-1] + grid[i][j];
				}
				else if(i!=0 && j==0) {
					dp[i][j] = dp[i-1][j] + grid[i][j];
				}
				else {
					if(dp[i - 1][j]>dp[i][j - 1]) {
						dp[i][j] = dp[i][j - 1] + grid[i][j];
					}else {
						dp[i][j] = dp[i - 1][j] + grid[i][j];
					}
				}
			}
		}
        return dp[m-1][n-1];
    }
	
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	if(obstacleGrid[0][0]==1) {
    		return 0;
    	}
    	int m = obstacleGrid.length;
    	int n = obstacleGrid[0].length;
    	int[][] dp = new int[m][n];
    	for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(i==0 && j==0) {
					dp[i][j] = 1;
				}
				else if(i==0 && j!=0) {
					if(obstacleGrid[i][j]==1 || obstacleGrid[i][j-1]==1) {
						dp[i][j] = 0;
					}else {
						dp[i][j] = dp[i][j-1];
					}
				}
				else if(i!=0 && j==0) {
					if(obstacleGrid[i][j]==1 || obstacleGrid[i-1][j]==1) {
						dp[i][j] = 0;
					}else {
						dp[i][j] = dp[i-1][j];
					}
				}
				else {
					if(obstacleGrid[i][j]==1 || (obstacleGrid[i][j-1]==1&&obstacleGrid[i-1][j]==1)) {
						dp[i][j] = 0;
					}else {
						dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
					}
				}
			}
		}
    	return dp[m-1][n-1];
    }
	
    public int uniquePaths(int m, int n) {
    	/*int count = 0;
    	count = name(m, n, 0, 0, 0);
    	return count;*/
    	int[][] dp = new int[m][n];
    	for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(i==0 || j==0) {
					dp[i][j] = 1;
				}
				else {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}
		}
    	return dp[m-1][n-1];
    }
    
    public int name(int m, int n, int x, int y, int count) {
		if(x==m-1&&y==n-1) {
			count++;
			return count;
		}
		if(x==m-1) {
			count = count + name(m, n, x, y+1, count);
			return count;
		}
		else if(y==n-1) {
			count = count + name(m, n, x+1, y, count);
			return count;
		}else {
			count = count + name(m, n, x, y+1, count) + name(m, n, x+1, y, count);
		}
		return count;
	}

}
