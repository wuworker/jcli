package com.wxl.jcli.date;

import com.wxl.jcli.CommandExecutor;
import com.wxl.jcli.CommandExecutorBuilder;
import com.wxl.jcli.date.MinusCommand;
import org.junit.Test;

/**
 * Create by wuxingle on 2020/08/20
 */
public class MinusCommandTest {

    @Test
    public void test() {
        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("jdate")
                .addCommand(new MinusCommand())
                .build();

        executor.execute(new String[]{"-m", "1d1w1M1y"});

        executor.execute(new String[]{"-m", "2020-08-10", "1d1w1M1y"});
        executor.execute(new String[]{"-m", "10:10:10", "1s1m1h"});
        executor.execute(new String[]{"-m", "2020-08-10 10:10:10", "1d1w1M1y1s1m1h"});
        executor.execute(new String[]{"-m", "1597894459742", "1d1w1M1y1s1m1h"});
        executor.execute(new String[]{"-m", "2020-08-10", "1h"});
    }
}
