package com.wxl.jcli.encode;

import com.wxl.jcli.CommandExecutor;
import com.wxl.jcli.CommandExecutorBuilder;
import com.wxl.jcli.HelpCommand;

/**
 * Create by wuxingle on 2020/09/17
 * jenc
 * 编解码命令
 */
public class JEncode {

    public static void main(String[] args) {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.setEnd(true);

        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("jenc")
                .addCommand(
                        new URLEncodeCommand(),
                        new URLDecodeCommand(),
                        helpCommand
                )
                .build();

        executor.execute(args);
    }
}
