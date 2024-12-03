package main.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Solution1 {

    public static int mul(List<String> instructions) {
        
        int result = 0;
        Pattern pattern = Pattern.compile("\\d+,\\d+");
        for(int i = 0; i < instructions.size(); i++) {
            var matcher = pattern.matcher(instructions.get(i));
            if(matcher.find()) {
                String[] nums = matcher.group().split(",");
                result += Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
        List<String> instructions = new ArrayList<>();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                var matcher = pattern.matcher(line);
                while(matcher.find()) {
                    instructions.add(matcher.group(0));
                }
            }

        } catch (Exception e) {
            throw new IOException(e);
        }

        System.out.println(mul(instructions));
    }
}
