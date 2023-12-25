package com.wxl.jcli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Create by wuxingle on 2020/08/07
 * 命令执行
 */
public class CommandExecutor {

    private final String name;

    private final List<Command> commands;

    private final CommandLineParser parser;

    private final Options options;

    private final CommandErrorHandler errorHandler;

    private final PrintStream stdout;

    private final PrintStream stderr;

    CommandExecutor(String name,
                    CommandLineParser parser,
                    List<Command> commands,
                    CommandErrorHandler errorHandler,
                    PrintStream stdout,
                    PrintStream stderr) {
        this.name = name;
        this.parser = parser;
        this.commands = commands;
        this.errorHandler = errorHandler;
        this.stdout = stdout;
        this.stderr = stderr;
        this.options = new Options();

        for (Command command : commands) {
            this.options.addOption(command.option());
        }
    }

    /**
     * 执行
     *
     * @param args
     */
    public void execute(String[] args) {
        execute(args, null);
    }

    /**
     * 执行
     *
     * @param args           参数
     * @param ctxInitializer context初始化
     */
    public void execute(String[] args, Consumer<CommandContext> ctxInitializer) {
        DefaultCommandContext context = new DefaultCommandContext();
        try {
            if (ctxInitializer != null) {
                ctxInitializer.accept(context);
            }

            context.commandLine = parser.parse(options, args);

            new DefaultCommandChain().doNext(context);
        } catch (Throwable e) {
            Throwable throwable = e;
            Command cmd = null;
            if (throwable instanceof ExecuteException) {
                cmd = ((ExecuteException) throwable).getCommand();
                throwable = throwable.getCause();
            }
            errorHandler.handleError(context, cmd, throwable);
        }
    }

    /**
     * command chain impl
     */
    private class DefaultCommandChain implements CommandChain {

        private int index = 0;

        @Override
        public void doNext(CommandContext context) {
            Command cmd = null;
            try {
                if (index < commands.size()) {
                    cmd = commands.get(index++);
                    cmd.execute(context, this);
                }
                index = 0;
            } catch (Throwable e) {
                if (e instanceof ExecuteException) {
                    throw e;
                }
                throw new ExecuteException(cmd, e);
            }
        }
    }

    /**
     * 执行异常
     */
    private static class ExecuteException extends RuntimeException {

        private static final long serialVersionUID = 2550224515375239796L;

        private Command command;

        public ExecuteException(Command command, Throwable cause) {
            super(cause);
            this.command = command;
        }

        public Command getCommand() {
            return command;
        }
    }

    /**
     * context impl
     */
    private class DefaultCommandContext implements CommandContext {

        private Map<String, Object> attrMap = new HashMap<>();

        private CommandLine commandLine;

        public DefaultCommandContext() {
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public CommandLine commandLine() {
            return commandLine;
        }

        @Override
        public Options options() {
            return options;
        }

        @Override
        public PrintStream stdout() {
            return stdout;
        }

        @Override
        public PrintStream stderr() {
            return stderr;
        }

        @Override
        public void setAttr(String key, Object value) {
            attrMap.put(key, value);
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T getAttr(String key) {
            return (T) attrMap.get(key);
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T getAttr(String key, T defaultVal) {
            return (T) attrMap.getOrDefault(key, defaultVal);
        }
    }

}

