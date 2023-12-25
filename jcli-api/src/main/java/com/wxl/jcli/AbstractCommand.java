package com.wxl.jcli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;

/**
 * Create by wuxingle on 2020/08/12
 * 命令
 * 提供公共方法
 */
public abstract class AbstractCommand implements Command {

    /**
     * 获取选项名
     */
    public String getOptName() {
        String opt = option().getOpt();
        if (StringUtils.isNotBlank(opt)) {
            return opt;
        }
        return option().getLongOpt();
    }

    /**
     * 获取当前选项值
     */
    public String[] getOptValues(CommandContext context) {
        String opt = getOptName();
        String[] values = context.commandLine().getOptionValues(opt);
        return values == null ? new String[0] : values;
    }

    /**
     * 获取命令的参数
     */
    public String[] getCommandArgs(CommandContext context) {
        return context.commandLine().getArgs();
    }

    /**
     * 用户输入的是否包含当前命令
     */
    public boolean isCurrentCommand(CommandContext context) {
        CommandLine commandLine = context.commandLine();
        return commandLine.hasOption(getOptName());
    }

    /**
     * 检查选项参数长度
     *
     * @param context
     * @param min     最小个数
     * @param max     最大个数
     */
    public void checkOptionValueLen(CommandContext context, int min, int max) {
        String[] values = getOptValues(context);
        if (values.length < min) {
            throw new IllegalArgumentException("absent value!");
        }
        if (values.length > max) {
            throw new IllegalArgumentException("too many value!");
        }
    }

    /**
     * 检查命令参数长度
     *
     * @param context
     * @param min     最小个数
     * @param max     最大个数
     */
    public void checkArgValueLen(CommandContext context, int min, int max) {
        String[] args = getCommandArgs(context);
        if (args.length < min) {
            throw new IllegalArgumentException("absent value!");
        }
        if (args.length > max) {
            throw new IllegalArgumentException("too many value!");
        }
    }

    /**
     * 获取选项值
     *
     * @param index 参数索引
     */
    public String getOptionValue(CommandContext context, int index) {
        return getOptionValue(context, index, false);
    }

    public String getOptionValue(CommandContext context, int index, String defaultVal) {
        String val = getOptionValue(context, index, false);
        return val == null ? defaultVal : val;
    }

    public Boolean getOptionBoolean(CommandContext context, int index) {
        return getOptionBoolean(context, index, false);
    }

    public Boolean getOptionBoolean(CommandContext context, int index, Boolean defaultVal) {
        Boolean val = getOptionBoolean(context, index, false);
        return val == null ? defaultVal : val;
    }

    public Integer getOptionInteger(CommandContext context, int index) {
        return getOptionInteger(context, index, false);
    }

    public Integer getOptionInteger(CommandContext context, int index, Integer defaultVal) {
        Integer val = getOptionInteger(context, index, false);
        return val == null ? defaultVal : val;
    }

    public Long getOptionLong(CommandContext context, int index) {
        return getOptionLong(context, index, false);
    }

    public Long getOptionLong(CommandContext context, int index, Long defaultVal) {
        Long val = getOptionLong(context, index, false);
        return val == null ? defaultVal : val;
    }

    public Float getOptionFloat(CommandContext context, int index) {
        return getOptionFloat(context, index, false);
    }

    public Float getOptionFloat(CommandContext context, int index, Float defaultVal) {
        Float val = getOptionFloat(context, index, false);
        return val == null ? defaultVal : val;
    }

    public Double getOptionDouble(CommandContext context, int index) {
        return getOptionDouble(context, index, false);
    }

    public Double getOptionDouble(CommandContext context, int index, Double defaultVal) {
        Double val = getOptionDouble(context, index, false);
        return val == null ? defaultVal : val;
    }

    /**
     * 获取参数值
     *
     * @param index 参数索引
     */
    public String getArgValue(CommandContext context, int index) {
        return getArgValue(context, index, false);
    }

    public String getArgValue(CommandContext context, int index, String defaultVal) {
        String val = getArgValue(context, index, false);
        return val == null ? defaultVal : val;
    }

    public Boolean getArgBoolean(CommandContext context, int index) {
        return getArgBoolean(context, index, false);
    }

    public Boolean getArgBoolean(CommandContext context, int index, Boolean defaultVal) {
        Boolean val = getArgBoolean(context, index, false);
        return val == null ? defaultVal : val;
    }

    public Integer getArgInteger(CommandContext context, int index) {
        return getArgInteger(context, index, false);
    }

    public Integer getArgInteger(CommandContext context, int index, Integer defaultVal) {
        Integer val = getArgInteger(context, index, false);
        return val == null ? defaultVal : val;
    }

    public Long getArgLong(CommandContext context, int index) {
        return getArgLong(context, index, false);
    }

    public Long getArgLong(CommandContext context, int index, Long defaultVal) {
        Long val = getArgLong(context, index, false);
        return val == null ? defaultVal : val;
    }

    public Float getArgFloat(CommandContext context, int index) {
        return getArgFloat(context, index, false);
    }

    public Float getArgFloat(CommandContext context, int index, Float defaultVal) {
        Float val = getArgFloat(context, index, false);
        return val == null ? defaultVal : val;
    }

    public Double getArgDouble(CommandContext context, int index) {
        return getArgDouble(context, index, false);
    }

    public Double getArgDouble(CommandContext context, int index, Double defaultVal) {
        Double val = getArgDouble(context, index, false);
        return val == null ? defaultVal : val;
    }

    /**
     * 获取必选选项值
     *
     * @param index 参数索引
     */
    public String getRequireOptionValue(CommandContext context, int index) {
        return getOptionValue(context, index, true);
    }

    public Boolean getRequireOptionBoolean(CommandContext context, int index) {
        return getOptionBoolean(context, index, true);
    }

    public Integer getRequireOptionInteger(CommandContext context, int index) {
        return getOptionInteger(context, index, true);
    }

    public Long getRequireOptionLong(CommandContext context, int index) {
        return getOptionLong(context, index, true);
    }

    public Float getRequireOptionFloat(CommandContext context, int index) {
        return getOptionFloat(context, index, true);
    }

    public Double getRequireOptionDouble(CommandContext context, int index) {
        return getOptionDouble(context, index, true);
    }

    /**
     * 获取必选选项值
     *
     * @param index 参数索引
     */
    public String getRequireArgValue(CommandContext context, int index) {
        return getArgValue(context, index, true);
    }

    public Boolean getRequireArgBoolean(CommandContext context, int index) {
        return getArgBoolean(context, index, true);
    }

    public Integer getRequireArgInteger(CommandContext context, int index) {
        return getArgInteger(context, index, true);
    }

    public Long getRequireArgLong(CommandContext context, int index) {
        return getArgLong(context, index, true);
    }

    public Float getRequireArgFloat(CommandContext context, int index) {
        return getArgFloat(context, index, true);
    }

    public Double getRequireArgDouble(CommandContext context, int index) {
        return getArgDouble(context, index, true);
    }

    /**
     * 获取选项值
     *
     * @param index   参数索引
     * @param require 是否必选
     */
    private String getOptionValue(CommandContext context, int index, boolean require) {
        String[] values = getOptValues(context);
        if (index >= values.length) {
            if (require) {
                throw new IllegalArgumentException("absent value!");
            }
            return null;
        }
        return values[index];
    }

    private Boolean getOptionBoolean(CommandContext context, int index, boolean require) {
        return getOptionValue0(context, index, require, Boolean::parseBoolean);
    }

    private Integer getOptionInteger(CommandContext context, int index, boolean require) {
        return getOptionValue0(context, index, require, Integer::parseInt);
    }

    private Long getOptionLong(CommandContext context, int index, boolean require) {
        return getOptionValue0(context, index, require, Long::parseLong);
    }

    private Float getOptionFloat(CommandContext context, int index, boolean require) {
        return getOptionValue0(context, index, require, Float::parseFloat);
    }

    private Double getOptionDouble(CommandContext context, int index, boolean require) {
        return getOptionValue0(context, index, require, Double::parseDouble);
    }

    private <T> T getOptionValue0(CommandContext context, int index, boolean require, Function<String, T> convert) {
        String val = getOptionValue(context, index, require);
        if (val == null) {
            return null;
        }
        try {
            return convert.apply(val);
        } catch (Exception e) {
            throw new IllegalArgumentException("type error: " + e.getMessage());
        }
    }

    /**
     * 获取命令参数值
     *
     * @param index   参数索引
     * @param require 是否必须
     */
    private String getArgValue(CommandContext context, int index, boolean require) {
        String[] values = getCommandArgs(context);
        if (index >= values.length) {
            if (require) {
                throw new IllegalArgumentException("absent value!");
            }
            return null;
        }
        return values[index];
    }

    private Boolean getArgBoolean(CommandContext context, int index, boolean require) {
        return getArgValue0(context, index, require, Boolean::parseBoolean);
    }

    private Integer getArgInteger(CommandContext context, int index, boolean require) {
        return getArgValue0(context, index, require, Integer::parseInt);
    }

    private Long getArgLong(CommandContext context, int index, boolean require) {
        return getArgValue0(context, index, require, Long::parseLong);
    }

    private Float getArgFloat(CommandContext context, int index, boolean require) {
        return getArgValue0(context, index, require, Float::parseFloat);
    }

    private Double getArgDouble(CommandContext context, int index, boolean require) {
        return getArgValue0(context, index, require, Double::parseDouble);
    }

    private <T> T getArgValue0(CommandContext context, int index, boolean require, Function<String, T> convert) {
        String val = getArgValue(context, index, require);
        if (val == null) {
            return null;
        }
        try {
            return convert.apply(val);
        } catch (Exception e) {
            throw new IllegalArgumentException("type error: " + e.getMessage());
        }
    }
}
