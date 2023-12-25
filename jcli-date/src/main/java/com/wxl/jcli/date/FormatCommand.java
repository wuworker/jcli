package com.wxl.jcli.date;

import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import org.apache.commons.cli.Option;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Create by wuxingle on 2020/08/07
 * 时间戳格式化命令
 */
public class FormatCommand extends AbstractCommand {

    private static final Option FORMAT_OPT = Option.builder("f")
            .longOpt("format")
            .desc("时间戳格式化")
            .optionalArg(true)
            .numberOfArgs(1)
            .argName("timestamp")
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            // 第一个参数，时间戳
            var timestamp = getRequireOptionLong(context, 0);
            // 格式化fmt
            DateTimeFormatter formatter = context.getAttr(
                    JDateConstant.ATTR_FORMAT, JDateConstant.DEFAULT_FORMAT);

            var dateTime = JDateUtils.toLocalDateTime(timestamp);

            var result = formatter.format(dateTime);
            context.stdout().println(result);
            return;
        }

        chain.doNext(context);
    }

    @Override
    public Option option() {
        return FORMAT_OPT;
    }
}
