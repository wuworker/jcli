package com.wxl.jcli.random;

/**
 * Create by wuxingle on 2020/08/21
 * 常量
 */
public interface JRandomConstant {

    /**
     * 生成个数
     */
    String ATTR_COUNT = "count";

    /**
     * 生成随机数是否包含数字
     */
    String ATTR_HAS_DIGIT = "hasDigit";

    /**
     * 生成随机数是否包含字母
     */
    String ATTR_HAS_ALPHA = "hasAlpha";

    /**
     * 生成随机数的待选定字符
     */
    String ATTR_CHOOSE = "chooseStr";

    /**
     * 生成的字母全部大写
     */
    String ATTR_UPPER = "alphaUpper";

    /**
     * 生成的字母全部小写
     */
    String ATTR_LOWER = "alphaLower";

    /**
     * 默认个数
     */
    int DEFAULT_COUNT = 1;
}
