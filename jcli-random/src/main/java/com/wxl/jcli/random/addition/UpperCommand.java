package com.wxl.jcli.random.addition;

import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import com.wxl.jcli.random.JRandomConstant;
import org.apache.commons.cli.Option;

/**
 * Create by wuxingle on 2020/08/21
 * 生成的随机字母全部大写
 */
public class UpperCommand extends AbstractCommand {

    private static final Option UPPER_OPT = Option.builder()
            .longOpt("upper")
            .desc("生成的随机字母全部大写")
            .optionalArg(true)
            .hasArg(false)
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            context.setAttr(JRandomConstant.ATTR_UPPER, true);
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return UPPER_OPT;
    }
}
