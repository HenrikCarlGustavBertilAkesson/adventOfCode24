package main.day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1 {

    public static int visited(int[][] grid, int xPos, int yPos) {

        int[][] dir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        int currDir = 0;
        int xDir = 0;
        int yDir = -1;
        int visited = 1;
        xPos += xDir;
        yPos += yDir;
        while (xPos >= 0 && xPos < grid[0].length && yPos >= 0 && yPos < grid.length) {

            if (grid[yPos][xPos] == -1) {
                // if obstacle, go back and turn right

                xPos -= xDir;
                yPos -= yDir;

                xDir = dir[++currDir % 4][0];
                yDir = dir[currDir % 4][1];
            } 
            
            if (grid[yPos][xPos] == 0) {
                visited++;
                grid[yPos][xPos]++;
            }

            xPos += xDir;
            yPos += yDir;

        }

        return visited;
    } 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int rows = Integer.parseInt(br.readLine().strip());
        int cols;
        int[][] grid = new int[rows][];
        int xPos = -1, yPos = -1;
        // -1   : obstacle
        // 0    : empty
        // 1    : visited position

        try {
            int i = 0;
            String line = br.readLine();
            cols = line.length();
            while(line != null) {
                char[] positions = line.toCharArray();
                int[] row = new int[cols];
                for(int j = 0; j < row.length; j++) {
                    if(positions[j] == '#') {
                        row[j] = -1;
                    } else if(positions[j] == '^') {
                        xPos = j;
                        yPos = i;
                        row[j] = 1;
                    }
                }
                grid[i++] = row;
                line = br.readLine();
            }

        } catch(Exception e) {
            throw new IOException(e);
        }

        System.out.println(visited(grid, xPos, yPos));

    }
}