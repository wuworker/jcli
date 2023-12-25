package com.wxl.jcli.date;

import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.ArrayUtils;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;

/**
 * Create by wuxingle on 2020/08/14
 * 输出日历
 */
public class CalendarCommand extends AbstractCommand {

    private static final Option CALENDAR_OPT = Option.builder("c")
            .longOpt("calendar")
            .desc("输出日历")
            .optionalArg(true)
            .numberOfArgs(2)
            .argName("[year] [month]")
            .build();


    @Override
    public void execute(CommandContext context, CommandChain chain) {
        CommandLine commandLine = context.commandLine();
        if (isCurrentCommand(context)) {
            checkOptionValueLen(context, 0, 2);

            int year;
            int month;
            LocalDate now = LocalDate.now();
            if (!ArrayUtils.isEmpty(commandLine.getOptionValues("c"))) {
                year = getRequireOptionInteger(context, 0);
                month = getRequireOptionInteger(context, 1);
            } else {
                year = now.getYear();
                month = now.getMonthValue();
            }
            if (year < 1900 || year > 9999) {
                throw new IllegalArgumentException("year must in [1900,9999], " +
                        "but is: " + year);
            }
            if (month < 1 || month > 12) {
                throw new IllegalArgumentException("month must in [1,12], " +
                        "but is: " + month);
            }

            // 当月第一天是周几
            int firstDayWeek = 1 + sumDay(year, month) % 7;
            if (firstDayWeek == 7) {
                firstDayWeek = 0;
            }
            // 当月总天数
            int maxDay = getDayOfMonth(year, month);

            context.stdout().println("\t\t" + year + "年 " + month + "月");
            context.stdout().println("日\t一\t二\t三\t四\t五\t六");

            int day = 1;
            out:
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    if (i == 0 && j < firstDayWeek) {
                        context.stdout().print("\t");
                        continue;
                    }
                    if (day > maxDay) {
                        break out;
                    }
                    if (day == now.getDayOfMonth() && month == now.getMonthValue()
                            && year == now.getYear()) {
                        context.stdout().print("\033[31;4m" + day + "\033[0m");
                        context.stdout().print("\t");
                    } else {
                        context.stdout().print(day + "\t");
                    }
                    day++;
                }
                context.stdout().println();
            }
            context.stdout().println();
            return;
        }
        chain.doNext(context);
    }

    /**
     * 计算输入日期到1900年的天数
     *
     * @param year
     * @param month
     * @return
     */
    private int sumDay(int year, int month) {
        int daySum = 0;
        for (int i = 1900; i < year; i++) {
            if (IsoChronology.INSTANCE.isLeapYear(i)) {
                daySum += 366;
            } else {
                daySum += 365;
            }
        }
        for (int i = 1; i < month; i++) {
            daySum += getDayOfMonth(year, i);
        }
        return daySum;
    }

    /**
     * 返回这个月的天数
     *
     * @param year
     * @param month
     * @return
     */
    private int getDayOfMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return IsoChronology.INSTANCE.isLeapYear(year) ? 29 : 28;
            default:
                return 30;
        }
    }

    @Override
    public Option option() {
        return CALENDAR_OPT;
    }

}
