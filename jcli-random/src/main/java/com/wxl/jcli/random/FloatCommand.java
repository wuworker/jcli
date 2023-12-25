package com.wxl.jcli.random;

import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.RandomUtils;

/**
 * Create by wuxingle on 2020/08/21
 * 生成随机浮点数
 */
public class FloatCommand extends AbstractCommand {

    private static final Option FLOAT_OPT = Option.builder("f")
            .longOpt("float")
            .desc("生成随机浮点数")
            .optionalArg(true)
            .numberOfArgs(2)
            .argName("[min] [max]")
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            checkOptionValueLen(context, 0, 2);
            // 生成个数
            int count = context.getAttr(JRandomConstant.ATTR_COUNT, JRandomConstant.DEFAULT_COUNT);

            // 生成的数据范围
            Double min = getOptionDouble(context, 0);
            Double max = getOptionDouble(context, 1);

            for (int i = 0; i < count; i++) {
                if (min == null) {
                    context.stdout().println(RandomUtils.nextDouble());
                } else if (max == null) {
                    context.stdout().println(RandomUtils.nextDouble(0, min));
                } else {
                    context.stdout().println(RandomUtils.nextDouble(min, max));
                }
            }
            return;
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return FLOAT_OPT;
    }
}
