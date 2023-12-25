package com.wxl.jcli.json.addition;


import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import com.wxl.jcli.json.JsonConstant;
import org.apache.commons.cli.Option;

/**
 * Create by wuxingle on 2020/09/16
 * json格式化宽松校验
 */
public class LenientCommand extends AbstractCommand {

    private static final Option LENIENT_OPT = Option.builder()
            .longOpt("lenient")
            .desc("宽松校验")
            .optionalArg(true)
            .hasArg(false)
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            context.setAttr(JsonConstant.ATTR_LENIENT, true);
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return LENIENT_OPT;
    }
}
