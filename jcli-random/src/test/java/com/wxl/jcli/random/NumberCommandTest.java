package com.wxl.jcli.random;

import com.wxl.jcli.CommandExecutor;
import com.wxl.jcli.CommandExecutorBuilder;
import com.wxl.jcli.random.addition.CountCommand;
import org.junit.Test;

/**
 * Create by wuxingle on 2020/08/21
 */
public class NumberCommandTest {

    @Test
    public void test1() {
        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("jrdm")
                .addCommand(
                        new CountCommand(),
                        new NumberCommand()
                )
                .build();

        executor.execute(new String[]{"-n", "100", "1000", "-c", "10"});
    }
}