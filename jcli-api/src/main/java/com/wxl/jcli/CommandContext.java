package com.wxl.jcli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import java.io.PrintStream;

/**
 * Create by wuxingle on 2020/08/07
 * 命令上下文
 */
public interface CommandContext {

    /**
     * 命令名
     */
    String name();

    /**
     * 解析后的CommandLine
     */
    CommandLine commandLine();

    /**
     * 所有选项
     */
    Options options();

    /**
     * 正确输出
     */
    PrintStream stdout();

    /**
     * 错误输出
     */
    PrintStream stderr();

    /**
     * 设置自定义属性
     */
    void setAttr(String key, Object value);

    /**
     * 获取自定义属性
     */
    <T> T getAttr(String key);

    /**
     * 获取自定义属性
     */
    <T> T getAttr(String key, T defaultVal);
}
