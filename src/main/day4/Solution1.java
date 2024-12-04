package main.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1 {

    public static int search2D(char[][] letters, int row, int col, char[] word) {
        int m = letters.length;
        int n = letters[0].length;
        
        if(letters[row][col] != word[0]) return 0;

        int len = word.length,
        occurrences = 0;

        int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for(int dir = 0; dir < 8; dir++) {
            int k, currX = row + x[dir],
                    currY = col + y[dir],
                    matches = 1;
            

            for(k = 1; k < len; k++) {
                
                if(currX < 0 || currX >= m || currY < 0 || currY >= n) {
                    break;
                }

                if(letters[currX][currY] == word[k]) matches++;
                
                if(matches == len) occurrences++;

                currX += x[dir];
                currY += y[dir];
            }

        }

        return occurrences;
    }

    public static int searchWord(char[][] letters, char[] word) {
        int m = letters.length;
        int n = letters[0].length;

        int occurrences = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                occurrences += search2D(letters, i, j, word);
            }
        }

        return occurrences;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        char[][] letters = new char[size][];
        try {
            
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                letters[i++] = line.toCharArray();
            }

        } catch (Exception e) {
            throw new IOException(e);
        }

        char[] word = {'X', 'M', 'A', 'S'};

        int result = searchWord(letters, word);

        System.out.println(result);
        
    }
}
