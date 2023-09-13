/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nmt.formatters.*;

import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author admin
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.nmt.controllers",
    "com.nmt.repository",
    "com.nmt.service"
})
@PropertySource("classpath:configs.properties")
public class WebAppContextConfig implements WebMvcConfigurer {
//    @Value("${spring.mail.port}")
//    private int port;
//    @Value("${spring.mail.host}")
//    private String host;
//    @Value("${spring.mail.username}")
//    private String username;
//    @Value("${spring.mail.password}")
//    private String password;
//    @Value("${spring.mail.protocol}")
//    private String protocol;
//    @Value("${spring.mail.properties.mail.transport.protocol}")
//    private String transportProtocol;
//    @Value("${spring.mail.properties.mail.smtps.auth}")
//    private boolean auth;
//    @Value("${spring.mail.properties.mail.smtps.starttls.enable}")
//    private boolean starttlsEnable;
//    @Value("${spring.mail.properties.mail.smtps.timeout}")
//    private int timeout;
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
//    @Bean
//    public JavaMailSender javaMailSender() throws MessagingException {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost(host);
//        mailSender.setPort(port);
//
//        mailSender.setUsername(username);
//        mailSender.setPassword(password);
//        mailSender.setProtocol(protocol);
//
//        Properties props = new Properties();
//        props.put("mail.transport.protocol", transportProtocol);
//        props.put("mail.smtp.auth", auth);
//        props.put("mail.smtp.starttls.enable", starttlsEnable);
//        mailSender.setJavaMailProperties(props);
//
//        return mailSender;
//    }
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new FacultyFormatter());
        registry.addFormatter(new UserFormatter());
        registry.addFormatter(new MajorFormatter());
        registry.addFormatter(new ClassFormatter());
        registry.addFormatter(new StudentFormatter());
        registry.addFormatter(new ScoreColumnFormatter());
        registry.addFormatter(new ScoreFormatter());
        registry.addFormatter(new ScoreValueFormatter());
        registry.addFormatter(new StudentSubjectFormatter());
        registry.addFormatter(new SemeterFormatter());
        registry.addFormatter(new SubjectFormatter());
    }
    
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver
                = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource m = new ResourceBundleMessageSource();
        m.addBasenames("messages");

        return m;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean
                = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/js/");
    }
    
}
