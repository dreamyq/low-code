package com.simbest.metadata;

import com.simbest.cloud.feign.uums.annotation.EnableSimbestFeignClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@Slf4j
@SpringBootApplication
@EnableSimbestFeignClient
public class SimbestCloudApplication implements ApplicationListener<WebServerInitializedEvent>  {
    @Autowired
    private ApplicationContext appContext;

    /**
     * @param args 默认参数
     */
    public static void main(String[] args) {
        SpringApplication.run(SimbestCloudApplication.class, args);
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
//        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        String[] activeProfiles = appContext.getEnvironment().getActiveProfiles();
        for (String profile : activeProfiles) {
            log.warn("加载环境信息为: 【{}】", profile);
            log.warn("Application started successfully, lets go and have fun......");
        }

        WebServer server = event.getWebServer();
        WebServerApplicationContext context = event.getApplicationContext();
        Environment env = context.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        int port = server.getPort();
        String contextPath = env.getProperty("server.servlet.context-path");
        contextPath = StringUtils.isEmpty(contextPath) ? "":contextPath;
        log.warn("\n---------------------------------------------------------\n" +
                "\t应用已成功启动，运行地址如下：:\n" +
                "\tLocal:\t\thttp://localhost:{}{}" +
                "\n\tExternal:\thttp://{}:{}{}" +
                "\nAplication started successfully, lets go and have fun......" +
                "\n---------------------------------------------------------\n", port, contextPath, ip, port, contextPath);
    }
}
