package com.wxl.jcli.date;

import org.apache.commons.cli.Option;

import java.time.LocalDateTime;

/**
 * Create by wuxingle on 2020/08/18
 * 时间减少命令
 */
public class MinusCommand extends DateCalculateCommand {

    private static final Option MINUS_OPT = Option.builder("m")
            .longOpt("minus")
            .desc("时间减少某个增量")
            .optionalArg(true)
            .numberOfArgs(2)
            .argName("[time] unit:1d,1m...")
            .build();

    @Override
    protected LocalDateTime calculate(LocalDateTime dateTime, int val, JDateConstant.JDateUnit unit) {
        return dateTime.minus(val, unit.getUnit());
    }

    @Override
    public Option option() {
        return MINUS_OPT;
    }
}
