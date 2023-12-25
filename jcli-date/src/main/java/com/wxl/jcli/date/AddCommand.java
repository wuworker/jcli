package com.wxl.jcli.date;

import org.apache.commons.cli.Option;

import java.time.LocalDateTime;

/**
 * Create by wuxingle on 2020/08/18
 * 时间增加命令
 */
public class AddCommand extends DateCalculateCommand {

    private static final Option PLUS_OPT = Option.builder("a")
            .longOpt("add")
            .desc("时间增加某个增量")
            .optionalArg(true)
            .numberOfArgs(2)
            .argName("[time] unit:1d,1m...")
            .build();

    @Override
    protected LocalDateTime calculate(LocalDateTime dateTime, int val, JDateConstant.JDateUnit unit) {
        return dateTime.plus(val, unit.getUnit());
    }

    @Override
    public Option option() {
        return PLUS_OPT;
    }
}
