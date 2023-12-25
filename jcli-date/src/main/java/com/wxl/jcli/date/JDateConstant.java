package com.wxl.jcli.date;

import lombok.Getter;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

/**
 * Create by wuxingle on 2020/08/10
 * 常量
 */
public interface JDateConstant {

    /**
     * 指定日期格式
     */
    String ATTR_FORMAT = "format";


    /**
     * 默认格式化
     */
    DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * 时区
     */
    ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    /**
     * 星期格式
     */
    String[] WEEK_NAME = {
            "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"
    };


    /**
     * 时间单位
     */
    @Getter
    enum JDateUnit {

        SECONDS("s", ChronoUnit.SECONDS),
        MINUTES("m", ChronoUnit.MINUTES),
        HOURS("h", ChronoUnit.HOURS),

        DAYS("d", ChronoUnit.DAYS),
        WEEKS("w", ChronoUnit.WEEKS),
        MONTHS("M", ChronoUnit.MONTHS),
        YEARS("y", ChronoUnit.YEARS),
        ;

        public static final Pattern UNIT_EXP_REGEX = Pattern.compile("(\\d+[smhdwMy])+");
        public static final Pattern UNIT_REGEX = Pattern.compile("(\\d+)([smhdwMy])");

        private final String name;

        private final ChronoUnit unit;

        JDateUnit(String name, ChronoUnit unit) {
            this.name = name;
            this.unit = unit;
        }

        public static JDateUnit parse(String key) {
            for (JDateUnit unit : values()) {
                if (unit.getName().equals(key)) {
                    return unit;
                }
            }
            return null;
        }
    }

}
