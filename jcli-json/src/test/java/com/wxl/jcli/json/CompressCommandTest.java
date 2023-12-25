package com.wxl.jcli.json;

import com.wxl.jcli.CommandExecutor;
import com.wxl.jcli.CommandExecutorBuilder;
import org.junit.Test;

/**
 * Create by wuxingle on 2020/09/16
 */
public class CompressCommandTest {

    @Test
    public void test1() {
        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("json")
                .addCommand(
                        new CompressCommand()
                )
                .build();

        executor.execute(new String[]{"-c", "{\n" +
                "  \"user\": {\n" +
                "    \"name\": \"haha\",\n" +
                "    \"age\": 12,\n" +
                "    \"list\": [\n" +
                "      12,\n" +
                "      {\n" +
                "        \"h\": 4\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}"});
    }
}
