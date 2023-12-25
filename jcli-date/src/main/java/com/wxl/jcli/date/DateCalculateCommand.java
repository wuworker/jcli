package com.wxl.jcli.date;

import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;

/**
 * Create by wuxingle on 2020/09/15
 * 日期计算
 */
public abstract class DateCalculateCommand extends AbstractCommand {


    @Override
    public final void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            checkOptionValueLen(context, 1, 2);
            // 选项值
            String[] optValues = getOptValues(context);

            boolean isTimestamp = false;
            DateTimeFormatter dateTimeFormatter;
            LocalDateTime dateTime;
            // 最后一个参数为时间单位
            String unitStr = optValues[optValues.length - 1];

            // 1个参数为单位，时间基数为当前时间
            if (optValues.length == 1) {
                dateTimeFormatter = context.getAttr(JDateConstant.ATTR_FORMAT, JDateConstant.DEFAULT_FORMAT);
                dateTime = LocalDateTime.now();
            }
            // 第一个参数为时间，第二个参数为单位
            else {
                String dateStr = getOptionValue(context, 0);
                dateTimeFormatter = context.getAttr(JDateConstant.ATTR_FORMAT);
                if (dateTimeFormatter != null) {
                    dateTime = JDateUtils.parseDateTime(dateTimeFormatter, dateStr);
                }
                // 没指定格式化，自动检测
                else {
                    isTimestamp = JDateUtils.isTimestamp(dateStr);
                    if (isTimestamp) {
                        dateTime = JDateUtils.toLocalDateTime(Long.parseLong(dateStr));
                    } else {
                        dateTimeFormatter = JDateUtils.detectFormatter(dateStr);
                        if (dateTimeFormatter == null) {
                            throw new IllegalArgumentException("parse error by: '" + dateStr + "'");
                        }
                        dateTime = JDateUtils.parseDateTime(dateTimeFormatter, dateStr);
                    }
                }
            }
            if (dateTime == null) {
                throw new IllegalArgumentException("parse error by: '" + StringUtils.join(optValues, " ") + "'");
            }
            // 时间单位处理
            if (!JDateConstant.JDateUnit.UNIT_EXP_REGEX.matcher(unitStr).matches()) {
                throw new IllegalArgumentException("parse error by unit: '" + unitStr
                        + "', should match: " + JDateConstant.JDateUnit.UNIT_EXP_REGEX.pattern());
            }
            Matcher matcher = JDateConstant.JDateUnit.UNIT_REGEX.matcher(unitStr);

            while (matcher.find()) {
                int val = Integer.parseInt(matcher.group(1));
                JDateConstant.JDateUnit unit = JDateConstant.JDateUnit.parse(matcher.group(2));
                dateTime = calculate(dateTime, val, unit);
            }

            if (isTimestamp) {
                context.stdout().println(dateTime.atZone(JDateConstant.DEFAULT_ZONE).toInstant().toEpochMilli());
            } else {
                context.stdout().println(dateTime.format(dateTimeFormatter));
            }
            return;
        }
        chain.doNext(context);
    }

    /**
     * 日期计算
     *
     * @param dateTime
     * @param val
     * @param unit
     * @return
     */
    protected abstract LocalDateTime calculate(LocalDateTime dateTime, int val, JDateConstant.JDateUnit unit);

}
