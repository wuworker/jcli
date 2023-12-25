package com.wxl.jcli;

import org.apache.commons.cli.Option;

/**
 * Create by wuxingle on 2020/08/06
 * 命令
 */
public interface Command {

    /**
     * 该选项执行的命令
     */
    void execute(CommandContext context, CommandChain chain);

    /**
     * 选项
     */
    Option option();

}
