package com.wxl.jcli.random;

import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import org.apache.commons.cli.Option;

import java.util.UUID;

/**
 * Create by wuxingle on 2020/08/27
 * 生成默认uuid
 */
public class UuidCommand extends AbstractCommand {


    private static final Option UUID_OPT = Option.builder("u")
            .longOpt("uuid")
            .desc("生成默认uuid")
            .optionalArg(true)
            .hasArg(false)
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            // 生成个数
            int count = context.getAttr(JRandomConstant.ATTR_COUNT, JRandomConstant.DEFAULT_COUNT);

            for (int i = 0; i < count; i++) {
                context.stdout().println(UUID.randomUUID());
            }
            return;
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return UUID_OPT;
    }
}
