package com.wxl.jcli.random;

import com.wxl.jcli.CommandExecutor;
import com.wxl.jcli.CommandExecutorBuilder;
import com.wxl.jcli.random.addition.*;
import org.junit.Test;

/**
 * Create by wuxingle on 2020/08/24
 */
public class StringCommandTest {

    @Test
    public void test1() {
        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("jrdm")
                .addCommand(
                        new CountCommand(),

                        new AlphaCommand(),
                        new DigitCommand(),
                        new ChooseCommand(),
                        new UpperCommand(),
                        new LowerCommand(),
                        new StringCommand()
                )
                .build();

        executor.execute(new String[]{"10", "20", "-c", "5"});
        System.out.println();
        executor.execute(new String[]{"-s", "10", "20", "-c", "5", "-a"});
        System.out.println();
        executor.execute(new String[]{"-s", "-c", "5", "-d"});
        System.out.println();
        executor.execute(new String[]{"-s", "10", "20", "-c", "5", "-a", "--upper"});
        System.out.println();
        executor.execute(new String[]{"-s", "-c", "5", "-a", "--lower"});
        System.out.println();
        executor.execute(new String[]{"-s", "-c", "5", "--choose", "qwerasdf[]_+"});
    }
}
