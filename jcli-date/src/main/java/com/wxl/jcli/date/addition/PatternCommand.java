package com.wxl.jcli.date.addition;

import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import com.wxl.jcli.date.JDateConstant;
import org.apache.commons.cli.Option;

import java.time.format.DateTimeFormatter;

/**
 * Create by wuxingle on 2020/08/28
 * 指定日期格式
 */
public class PatternCommand extends AbstractCommand {

    private static final Option PATTERN_OPT = Option.builder()
            .longOpt("pattern")
            .desc("指定日期格式")
            .optionalArg(true)
            .numberOfArgs(1)
            .argName("[pattern]")
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            String pattern = getRequireOptionValue(context, 0);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            context.setAttr(JDateConstant.ATTR_FORMAT, dateTimeFormatter);
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return PATTERN_OPT;
    }

}
