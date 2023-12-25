package com.wxl.jcli.json;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandContext;

import java.io.StringReader;

/**
 * Create by wuxingle on 2020/09/17
 * json命令
 */
public abstract class JsonCommand extends AbstractCommand {


    /**
     * json解析
     */
    protected JsonElement parseJson(CommandContext context) {
        var jsonStr = getRequireArgValue(context, 0);
        if (context.getAttr(JsonConstant.ATTR_LENIENT, false)) {
            var reader = new JsonReader(new StringReader(jsonStr));
            return JsonParser.parseReader(reader);
        }
        return JsonParser.parseString(jsonStr);
    }

    /**
     * 获取gson builder
     */
    protected GsonBuilder getGsonBuilder(CommandContext context) {
        var gsonBuilder = new GsonBuilder();
        if (context.getAttr(JsonConstant.ATTR_NULLABLE, false)) {
            gsonBuilder.serializeNulls();
        }
        if (context.getAttr(JsonConstant.ATTR_LENIENT, false)) {
            gsonBuilder.setLenient();
        }
        return gsonBuilder;
    }

}
