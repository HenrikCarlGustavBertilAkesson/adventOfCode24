package main.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution2 {
    
    public static int mul(String instructions) {
        
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(instructions);
        int result = 0;
        while(matcher.find()) { 
            result += (Integer.parseInt(matcher.group(1))) * (Integer.parseInt(matcher.group(2)));
        }

        return result;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder rawInstructions = new StringBuilder();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                rawInstructions.append(line);
            }

        } catch (Exception e) {
            throw new IOException(e);
        }
        
        String[] instructions = rawInstructions.toString().split("don\\'t\\(\\)");
        Pattern pattern = Pattern.compile("do\\(\\)");
        Matcher matcher;
        int result = mul(instructions[0]);  // first instructions always executed
        for(int i = 1; i < instructions.length; i++) {
            matcher = pattern.matcher(instructions[i]);
            if(matcher.find()) {
                int start = matcher.end();
                result += mul(instructions[i].substring(start));
            }
        }
    
        System.out.println(result);
    }
}
