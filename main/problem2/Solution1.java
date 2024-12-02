package main.problem2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution1 {

    public static boolean safeReport(int[] report) {
        int diff = report[0] - report[1];

        if((Math.abs(diff) > 3) || (Math.abs(diff) < 1)) return false;

        boolean isDecreasing = diff > 0; 

        for(int i = 1; i < report.length - 1; i++) {
            diff = report[i] - report[i + 1];
            if (
                (Math.abs(diff) > 3) ||
                (Math.abs(diff) < 1) ||
                (isDecreasing != (diff > 0))
                ) return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numRows = Integer.parseInt(br.readLine().trim());
        int[][] reports = new int[numRows][];
        int[] report = new int[numRows];
        try {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] levels = line.split(" ");
                for (int col = 0; col < levels.length; col++) {
                    report[col] = Integer.parseInt(levels[col]);
                }
                reports[row++] = Arrays.copyOf(report, levels.length);
            }
        } catch (Exception e) {
            throw new IOException(e);
        }


        int result = 0;
        for (int[] r : reports) { if(safeReport(r))  result++; }

        System.out.println(result);
    }
}
