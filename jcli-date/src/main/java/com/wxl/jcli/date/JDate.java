package com.wxl.jcli.date;

import com.wxl.jcli.CommandExecutor;
import com.wxl.jcli.CommandExecutorBuilder;
import com.wxl.jcli.HelpCommand;
import com.wxl.jcli.date.addition.PatternCommand;
import org.apache.commons.cli.HelpFormatter;

/**
 * Create by wuxingle on 2020/08/06
 * jdate
 * 日期相关命令工具
 */
public class JDate {

    public static void main(String[] args) {
        var helpCommand = new HelpCommand(new HelpFormatter(),
                "默认格式化样式为: yyyy-MM-dd HH:mm:ss.SSS",
                null,
                false);

        var executor = new CommandExecutorBuilder()
                .setName("jdate")
                .addCommand(
                        new CalendarCommand(),
                        new PatternCommand(),

                        new FormatCommand(),
                        new ParseCommand(),
                        new AddCommand(),
                        new MinusCommand(),
                        helpCommand,
                        new NowCommand()
                )
                .build();

        executor.execute(args);
    }


}

