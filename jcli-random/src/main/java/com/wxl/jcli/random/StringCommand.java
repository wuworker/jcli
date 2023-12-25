package com.wxl.jcli.random;

import com.wxl.jcli.AbstractCommand;
import com.wxl.jcli.CommandChain;
import com.wxl.jcli.CommandContext;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by wuxingle on 2020/08/21
 * 设置生成随机字符串
 */
public class StringCommand extends AbstractCommand {

    private static final Option STR_OPT = Option.builder("s")
            .longOpt("string")
            .desc("生成随机字符串")
            .optionalArg(true)
            .numberOfArgs(2)
            .argName("[min] [max]")
            .build();

    private static final int DEFAULT_LENGTH = 6;

    @Override
    public void execute(CommandContext context, CommandChain chain) {

        Integer min;
        Integer max;
        if (isCurrentCommand(context)) {
            checkOptionValueLen(context, 0, 2);
            min = getOptionInteger(context, 0);
            max = getOptionInteger(context, 1);
        } else {
            checkArgValueLen(context, 0, 2);
            min = getArgInteger(context, 0);
            max = getArgInteger(context, 1);
        }
        if (min == null) {
            min = DEFAULT_LENGTH;
            max = min + 1;
        } else if (max == null) {
            max = min + 1;
        }

        // 生成个数
        int count = context.getAttr(JRandomConstant.ATTR_COUNT, JRandomConstant.DEFAULT_COUNT);

        // 大小写
        Boolean upper = context.getAttr(JRandomConstant.ATTR_UPPER);
        Boolean lower = context.getAttr(JRandomConstant.ATTR_LOWER);
        if (upper == null && lower == null) {
            upper = true;
            lower = true;
        } else if (upper == null) {
            upper = false;
        } else if (lower == null) {
            lower = false;
        }

        // 包含字母，数字
        Boolean hasAlpha = context.getAttr(JRandomConstant.ATTR_HAS_ALPHA);
        Boolean hasDigit = context.getAttr(JRandomConstant.ATTR_HAS_DIGIT);
        if (hasAlpha == null && hasDigit == null) {
            hasAlpha = true;
            hasDigit = true;
        } else if (hasAlpha == null) {
            hasAlpha = false;
        } else if (hasDigit == null) {
            hasDigit = false;
        }

        // 候选字符
        String chooseStr = context.getAttr(JRandomConstant.ATTR_CHOOSE);

        var randoms = new ArrayList<String>(count);
        String randomStr;
        for (int i = 0; i < count; i++) {
            int strLength = RandomUtils.nextInt(min, max);
            if (StringUtils.isNotBlank(chooseStr)) {
                randomStr = RandomStringUtils.random(strLength, 0, 0, false, false, chooseStr.toCharArray());
            } else {
                randomStr = RandomStringUtils.random(strLength, 0, 0, hasAlpha, hasDigit);
            }
            randoms.add(randomStr);
        }

        // 大小写
        for (String random : randoms) {
            if (upper && !lower) {
                random = random.toUpperCase();
            } else if (!upper && lower) {
                random = random.toLowerCase();
            }
            context.stdout().println(random);
        }
    }

    @Override
    public Option option() {
        return STR_OPT;
    }
}
