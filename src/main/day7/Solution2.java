package main.day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Solution2 {

    public static boolean canProduceValue(BigInteger[][] nums, int val, BigInteger tot, int next, BigInteger targetValue) {
        if (next >= nums[val].length) return tot.equals(targetValue);

        String left = tot.toString();
        String right = nums[val][next].toString();
        BigInteger concatination = new BigInteger(left + right);

        return canProduceValue(nums, val, tot.add(nums[val][next]), next + 1, targetValue) ||
                    canProduceValue(nums, val, tot.multiply(nums[val][next]), next + 1, targetValue) ||
                        canProduceValue(nums, val, concatination, next + 1, targetValue);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int rows = Integer.parseInt(br.readLine().strip());
        BigInteger[] values = new BigInteger[rows];
        BigInteger[][] nums = new BigInteger[rows][];

        try {
            int i = 0;
            String line = br.readLine();
            while (line != null && !line.isEmpty()) {
                String[] parts = line.split(":");
                values[i] = new BigInteger(parts[0].strip());

                String[] numStrings = parts[1].strip().split("\\s+");
                BigInteger[] row = new BigInteger[numStrings.length];
                for (int j = 0; j < numStrings.length; j++) {
                    row[j] = new BigInteger(numStrings[j]);
                }

                nums[i++] = row;
                line = br.readLine();
            }
        } catch (Exception e) {
            throw new IOException(e);
        }

        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < values.length; i++) {
            if (canProduceValue(nums, i, nums[i][0], 1, values[i])) {
                result = result.add(values[i]);
            }
        }

        System.out.println(result);
    }
}
