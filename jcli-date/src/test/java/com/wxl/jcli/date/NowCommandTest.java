package com.wxl.jcli.date;

import com.wxl.jcli.CommandExecutor;
import com.wxl.jcli.CommandExecutorBuilder;
import com.wxl.jcli.date.NowCommand;
import org.junit.Test;

/**
 * Create by wuxingle on 2020/08/14
 */
public class NowCommandTest {


    @Test
    public void test() {
        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("jdate")
                .addCommand(new NowCommand())
                .build();

        executor.execute(new String[]{});
    }
}