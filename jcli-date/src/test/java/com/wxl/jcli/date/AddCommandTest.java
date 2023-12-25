package com.wxl.jcli.date;

import com.wxl.jcli.CommandExecutor;
import com.wxl.jcli.CommandExecutorBuilder;
import com.wxl.jcli.date.addition.PatternCommand;
import com.wxl.jcli.date.AddCommand;
import com.wxl.jcli.date.JDateConstant;
import org.junit.Test;

import java.util.regex.Matcher;

/**
 * Create by wuxingle on 2020/08/20
 */
public class AddCommandTest {

    @Test
    public void test() {
        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("jdate")
                .addCommand(new PatternCommand(), new AddCommand())
                .build();

//        executor.execute(new String[]{"-a", "1d1w1M1y"});

        executor.execute(new String[]{"-a", "2020-08-10", "1d1w1M1y", "--pattern","HH:mm:ss"});
//        executor.execute(new String[]{"-a", "10:10:10", "1s1m1h"});
//        executor.execute(new String[]{"-a", "2020-08-10 10:10:10", "1d1w1M1y1s1m1h"});
//        executor.execute(new String[]{"-a", "1597894459742", "1d1w1M1y1s1m1h"});
//        executor.execute(new String[]{"-a", "2020-08-10", "1h"});
    }

    @Test
    public void test2() {
        String unit = "1s1m1h1g";
        Matcher matcher = JDateConstant.JDateUnit.UNIT_REGEX.matcher(unit);
        System.out.println(JDateConstant.JDateUnit.UNIT_EXP_REGEX.matcher(unit).matches());
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }

    }
}