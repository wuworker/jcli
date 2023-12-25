package com.wxl.jcli.date;

import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import org.apache.commons.cli.Option;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Create by wuxingle on 2020/08/07
 * 输出当前时间
 */
public class NowCommand extends AbstractCommand {

    private static final Option NOW_OPT = Option.builder("n")
            .longOpt("now")
            .desc("输出当前时间")
            .optionalArg(true)
            .hasArg(false)
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        DateTimeFormatter formatter = context.getAttr(JDateConstant.ATTR_FORMAT, JDateConstant.DEFAULT_FORMAT);

        LocalDateTime now = LocalDateTime.now();

        context.stdout().println(now.format(formatter));
        context.stdout().println(now.atZone(JDateConstant.DEFAULT_ZONE).toInstant().toEpochMilli());
    }

    @Override
    public Option option() {
        return NOW_OPT;
    }
}
