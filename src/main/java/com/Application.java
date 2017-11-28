package com;

import com.my.space.repository.BaseRepositoryFactoryBean;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author:冯大双
 * @Date:2017/8/4
 * @Desc: spring boot启动入口类
 */
@SpringBootApplication

// 开启
// @Configuration
// @EnableEncryptableProperties
@ComponentScan({"com"})
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class Application {

    //@Autowired
    //StringEncryptor stringEncryptor;// 密码解码器自动注入


    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
