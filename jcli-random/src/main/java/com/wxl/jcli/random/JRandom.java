package com.wxl.jcli.random;


import com.wxl.jcli.CommandExecutor;
import com.wxl.jcli.CommandExecutorBuilder;
import com.wxl.jcli.HelpCommand;
import com.wxl.jcli.random.addition.*;

/**
 * Create by wuxingle on 2020/08/21
 * jrandom
 * 随机数相关命令
 */
public class JRandom {

    public static void main(String[] args) {
        HelpCommand helpCommand = new HelpCommand();

        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("jrdm")
                .addCommand(
                        helpCommand,
                        new CountCommand(),
                        new NumberCommand(),
                        new FloatCommand(),
                        new UuidCommand(),

                        new AlphaCommand(),
                        new DigitCommand(),
                        new UpperCommand(),
                        new LowerCommand(),
                        new ChooseCommand(),
                        new StringCommand()
                )
                .build();

        executor.execute(args);
    }
}
