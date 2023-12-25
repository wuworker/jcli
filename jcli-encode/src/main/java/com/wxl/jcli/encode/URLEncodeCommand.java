package com.wxl.jcli.encode;

import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import org.apache.commons.cli.Option;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Create by wuxingle on 2020/09/17
 * url编码
 */
public class URLEncodeCommand extends AbstractCommand {

    private static final Option URL_ENCODE_OPT = Option.builder()
            .longOpt("urlencode")
            .desc("url编码")
            .optionalArg(true)
            .numberOfArgs(1)
            .argName("[url]")
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            String url = getRequireOptionValue(context, 0);

            try {
                String encodeUrl = URLEncoder.encode(url, JEncodeConstant.DEFAULT_CHARSET);
                context.stdout().println(encodeUrl);
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(e);
            }
            return;
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return URL_ENCODE_OPT;
    }
}
