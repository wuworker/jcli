package com.wxl.jcli.date;

import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import org.apache.commons.cli.Option;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Create by wuxingle on 2020/08/07
 * 时间字符串解析为时间戳命令
 */
public class ParseCommand extends AbstractCommand {

    private static final Option PARSE_OPT = Option.builder("p")
            .longOpt("parse")
            .desc("时间字符串解析为时间戳")
            .optionalArg(true)
            .numberOfArgs(1)
            .argName("time")
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            // 第一个参数，时间
            String time = getRequireOptionValue(context, 0);
            // 格式化参数
            DateTimeFormatter formatter = context.getAttr(JDateConstant.ATTR_FORMAT);

            LocalDateTime dateTime;
            if (formatter != null) {
                dateTime = JDateUtils.parseDateTime(formatter, time);
            } else {
                dateTime = JDateUtils.parseDateTime(time);
            }
            if (dateTime == null) {
                throw new IllegalArgumentException("parse error by: '" + time + "'");
            }

            long timestamp = JDateUtils.toTimestamp(dateTime);
            context.stdout().println(timestamp);
            return;
        }

        chain.doNext(context);
    }

    @Override
    public Option option() {
        return PARSE_OPT;
    }
}
