package com.wxl.jcli.random.addition;


import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import com.wxl.jcli.random.JRandomConstant;
import org.apache.commons.cli.Option;

/**
 * Create by wuxingle on 2020/08/21
 * 生成随机数中包含字母
 */
public class AlphaCommand extends AbstractCommand {

    private static final Option ALPHA_OPT = Option.builder("a")
            .longOpt("alpha")
            .desc("生成随机字符串中包含大小写字母")
            .optionalArg(true)
            .hasArg(false)
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            context.setAttr(JRandomConstant.ATTR_HAS_ALPHA, true);
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return ALPHA_OPT;
    }
}
