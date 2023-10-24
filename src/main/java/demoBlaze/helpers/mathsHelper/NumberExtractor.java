package demoBlaze.helpers.mathsHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberExtractor {
    public static int extract(String inputText){
        String regex = "[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputText);
        String number = "";
        while (matcher.find()){
            number = matcher.group();
        }
        return Integer.parseInt(number);
    }
}
