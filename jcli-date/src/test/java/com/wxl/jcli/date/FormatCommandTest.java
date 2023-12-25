package com.wxl.jcli.date;

import com.wxl.jcli.CommandExecutor;
import com.wxl.jcli.CommandExecutorBuilder;
import com.wxl.jcli.date.addition.PatternCommand;
import com.wxl.jcli.date.FormatCommand;
import org.junit.Test;

/**
 * Create by wuxingle on 2020/08/14
 */
public class FormatCommandTest {

    @Test
    public void test() {
        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("jdate")
                .addCommand(new PatternCommand(),
                        new FormatCommand())
                .build();

        executor.execute(new String[]{"-f", "1597398956967"});
    }
}


