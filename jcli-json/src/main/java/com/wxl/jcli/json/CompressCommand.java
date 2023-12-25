package com.wxl.jcli.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import org.apache.commons.cli.Option;

/**
 * Create by wuxingle on 2020/09/16
 * json 压缩
 */
public class CompressCommand extends JsonCommand {

    private static final Option COMPRESS_OPT = Option.builder("c")
            .longOpt("compress")
            .desc("json压缩")
            .optionalArg(true)
            .hasArg(false)
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            JsonElement element = parseJson(context);

            GsonBuilder gsonBuilder = getGsonBuilder(context);
            Gson gson = gsonBuilder.create();
            String compressJson = gson.toJson(element);

            context.stdout().println(compressJson);
            return;
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return COMPRESS_OPT;
    }
}
