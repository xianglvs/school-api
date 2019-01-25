package org.spring.springboot.app.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: eric
 * Date: 2017-12-13
 * Time: 17:22
 */
@Component
@Slf4j
public class StartupRunner implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        log.info(">>>>>>>>>>>>>>>服务启动执行<<<<<<<<<<<<<");
        log.info(">>>>>>>>>>>>>>>服务启动完成<<<<<<<<<<<<<");
    }
}
