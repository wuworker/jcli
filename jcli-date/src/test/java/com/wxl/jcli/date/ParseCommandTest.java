package com.wxl.jcli.date;

import com.wxl.jcli.CommandExecutor;
import com.wxl.jcli.CommandExecutorBuilder;
import com.wxl.jcli.date.ParseCommand;
import org.junit.Test;

/**
 * Create by wuxingle on 2020/08/16
 */
public class ParseCommandTest {

    @Test
    public void test() {
        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("jdate")
                .addCommand(new ParseCommand())
                .build();

        executor.execute(new String[]{"-p", "2020-08-13 12:12:19"});
    }
}