package com.wxl.jcli.encode;

import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import org.apache.commons.cli.Option;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Create by wuxingle on 2020/09/17
 * url解码
 */
public class URLDecodeCommand extends AbstractCommand {

    private static final Option URL_DECODE_OPT = Option.builder()
            .longOpt("urldecode")
            .desc("url解码")
            .optionalArg(true)
            .numberOfArgs(1)
            .argName("[url]")
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            var url = getRequireOptionValue(context, 0);

            try {
                var decodeUrl = URLDecoder.decode(url, JEncodeConstant.DEFAULT_CHARSET);
                context.stdout().println(decodeUrl);
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(e);
            }
            return;
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return URL_DECODE_OPT;
    }
}
