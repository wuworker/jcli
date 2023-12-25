package com.wxl.jcli;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by wuxingle on 2020/08/07
 * 命令执行builder
 */
public class CommandExecutorBuilder {

    private String name;

    private final List<Command> commands = new ArrayList<>();

    private CommandLineParser parser;

    private CommandErrorHandler errorHandler;

    private PrintStream stdout;

    private PrintStream stderr;

    public CommandExecutorBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CommandExecutorBuilder addCommand(Command... commands) {
        this.commands.addAll(Arrays.asList(commands));
        return this;
    }

    public CommandExecutorBuilder setParse(CommandLineParser parse) {
        this.parser = parse;
        return this;
    }

    public CommandExecutorBuilder setErrorHandler(CommandErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        return this;
    }

    public CommandExecutorBuilder setStdout(PrintStream printStream) {
        this.stdout = printStream;
        return this;
    }

    public CommandExecutorBuilder setStderr(PrintStream printStream) {
        this.stderr = printStream;
        return this;
    }

    public CommandExecutor build() {
        if (name == null) {
            throw new IllegalArgumentException("name can not null!");
        }
        if (parser == null) {
            parser = new DefaultParser();
        }
        if (errorHandler == null) {
            errorHandler = new DefaultCommandErrorHandler();
        }
        if (stdout == null) {
            stdout = System.out;
        }
        if (stderr == null) {
            stderr = System.err;
        }
        return new CommandExecutor(name, parser, commands, errorHandler, stdout, stderr);
    }
}
