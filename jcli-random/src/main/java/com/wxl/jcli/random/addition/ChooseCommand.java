package com.wxl.jcli.random.addition;

import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import com.wxl.jcli.random.JRandomConstant;
import org.apache.commons.cli.Option;

/**
 * Create by wuxingle on 2020/08/21
 * 生成的随机字符串从候选字符中选取
 */
public class ChooseCommand extends AbstractCommand {

    private static final Option CHOOSE_OPT = Option.builder()
            .longOpt("choose")
            .desc("生成的随机字符串从候选字符中选取")
            .optionalArg(true)
            .numberOfArgs(1)
            .argName("str")
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            var candidateStr = getRequireOptionValue(context, 0);
            context.setAttr(JRandomConstant.ATTR_CHOOSE, candidateStr);
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return CHOOSE_OPT;
    }
}
