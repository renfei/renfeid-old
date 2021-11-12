package net.renfei;

import net.renfei.config.RenFeiBanner;
import net.renfei.utils.ApplicationContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 程序启动入口类
 *
 * @author renfei
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        SpringApplication build = builder.build();
        build.setBanner(new RenFeiBanner());
        ApplicationContextUtil.setApplicationContext(build.run(args));
    }

}
