package com.wxl.jcli.json;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import org.apache.commons.cli.Option;

/**
 * Create by wuxingle on 2020/09/28
 * json 路径提取
 */
public class PathFilterCommand extends JsonCommand {

    private static final Option FILTER_OPT = Option.builder("f")
            .longOpt("filter")
            .desc("json path过滤")
            .optionalArg(true)
            .numberOfArgs(1)
            .argName("[json_path]")
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            var path = getRequireOptionValue(context, 0);

            var element = parseJson(context);
            var gson = getGsonBuilder(context).setPrettyPrinting().create();
            var jsonStr = gson.toJson(element);

            try {
                Object value = JsonPath.read(jsonStr, path);
                context.stdout().println(gson.toJson(value));
            } catch (PathNotFoundException e) {
                context.stdout().println("null");
            }

            return;
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return FILTER_OPT;
    }
}
