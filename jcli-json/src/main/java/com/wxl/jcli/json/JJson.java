package com.wxl.jcli.json;


import com.wxl.jcli.CommandExecutor;
import com.wxl.jcli.CommandExecutorBuilder;
import com.wxl.jcli.HelpCommand;
import com.wxl.jcli.json.addition.LenientCommand;
import com.wxl.jcli.json.addition.NullableCommand;

/**
 * Create by wuxingle on 2020/09/16
 * json
 * json相关命令
 */
public class JJson {

    public static void main(String[] args) {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.setEnd(true);

        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("json")
                .addCommand(
                        new NullableCommand(),
                        new LenientCommand(),

                        new CompressCommand(),
                        new ExpandCommand(),
                        new PathFilterCommand(),
                        helpCommand
                )
                .build();

        executor.execute(args);
    }
}
