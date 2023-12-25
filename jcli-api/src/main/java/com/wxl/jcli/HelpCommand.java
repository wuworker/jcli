package com.wxl.jcli;

import lombok.Setter;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;

import java.io.PrintWriter;

/**
 * Create by wuxingle on 2020/08/07
 * 帮助命令
 */
@Setter
public class HelpCommand extends AbstractCommand {

    private static final Option HELP = Option.builder("h")
            .longOpt("help")
            .desc("帮助信息")
            .build();

    private HelpFormatter formatter;

    private String head;

    private String foot;

    private boolean autoUsage;

    private boolean isEnd;

    public HelpCommand() {
        this(new HelpFormatter());
    }

    public HelpCommand(HelpFormatter formatter) {
        this(formatter, null, null, false);
    }

    public HelpCommand(HelpFormatter formatter, String head, String foot, boolean autoUsage) {
        this.formatter = formatter;
        this.head = head;
        this.foot = foot;
        this.autoUsage = autoUsage;
    }

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context) || isEnd) {
            PrintWriter pw = new PrintWriter(context.stdout());
            formatter.printHelp(pw, formatter.getWidth(), context.name(),
                    head,
                    context.options(),
                    formatter.getLeftPadding(),
                    formatter.getDescPadding(),
                    foot,
                    autoUsage);
            pw.flush();
            return;
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return HELP;
    }
}
