package main.day2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution2 {

    public static boolean safeLevels(int diff, boolean isDecreasing) {
        return !((Math.abs(diff) > 3) ||
        (Math.abs(diff) < 1) ||
        (isDecreasing != (diff > 0)));
    }

    public static boolean safeReport(int[] report) {

        int left = 0;
        int right = 1;
        int diff;
        boolean decr;
        while(left >= 0 && right < report.length && left < right) {
            diff = report[left] - report[right];
            decr = (diff > 0);

            if(safeLevels(diff, decr)) {
                left++;
                right++;
            } else if(left > 0) {
                left--;
                diff = report[left] - report[right];
                if(safeLevels(diff, decr)) {
                    left += 2;
                    right++;
                } else if(right < report.length - 1) {
                    right++;
                    diff = report[left] - report[right];
                    if(!safeLevels(diff, decr)) return false;
                }
            } else if(right < report.length - 1){
                right++;
                diff = report[left] - report[right];
                if(!safeLevels(diff, decr)) return false;
            } else {
                return false;
            }

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

