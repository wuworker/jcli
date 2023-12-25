package com.wxl.jcli.date;

import com.wxl.jcli.CommandExecutor;
import com.wxl.jcli.CommandExecutorBuilder;
import com.wxl.jcli.date.CalendarCommand;
import org.junit.Test;

/**
 * Create by wuxingle on 2020/08/14
 */
public class CalendarCommandTest {

    @Test
    public void test() {
        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("jdate")
                .addCommand(new CalendarCommand())
                .build();

        executor.execute(new String[]{"-c", "2020"});
    }
}
