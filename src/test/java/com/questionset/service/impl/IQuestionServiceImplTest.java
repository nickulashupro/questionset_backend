package com.questionset.service.impl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class IQuestionServiceImplTest {

    @Test
    void strToJson() {
        String normalStr = "A. cover\tB. will cover\tC. have covered\tD. covered";
        Pattern itemPattern = Pattern.compile("[ABCDEFG][\\.|、]?\\s*[^ABCDEFG]*");
        Matcher itemMatcher = itemPattern.matcher(normalStr);

        Pattern subPattern = Pattern.compile("[^ABCDEFG\\s.、]{1}.+");

        StringBuffer result = new StringBuffer("[");
        while (itemMatcher.find()) {
            String strTemp=itemMatcher.group(0);

            Matcher subMatcher = subPattern.matcher(strTemp);
            boolean isMatch = subMatcher.find();
            String key = strTemp.substring(0,1);
            result.append("{\"key\":\"");
            result.append(key);
            result.append("\",");

            String value = "";
            if(isMatch){
                value = subMatcher.group(0);

                result.append("\"value\":\"");
                result.append(value);
                result.append("\"},");
            }
        }
        result.deleteCharAt(result.length() - 1);
        result.append("]");
        String resutlstr = result.toString().replace("\t"," ");

        log.info(resutlstr);
    }
}