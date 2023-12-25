package com.wxl.jcli.json;

import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import org.apache.commons.cli.Option;

/**
 * Create by wuxingle on 2020/09/16
 * json 展开
 * 默认
 */
public class ExpandCommand extends JsonCommand {

    private static final Option EXPAND_OPT = Option.builder("e")
            .longOpt("expand")
            .desc("json展开，默认")
            .optionalArg(true)
            .hasArg(false)
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            var element = parseJson(context);

            var gson = getGsonBuilder(context).setPrettyPrinting().create();
            var expandJson = gson.toJson(element);

            context.stdout().println(expandJson);
            return;
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return EXPAND_OPT;
    }
}
