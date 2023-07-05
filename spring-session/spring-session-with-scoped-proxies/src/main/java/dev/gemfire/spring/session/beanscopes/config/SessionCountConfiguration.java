package dev.gemfire.spring.session.beanscopes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static dev.gemfire.spring.session.beanscopes.Application.INDEX_TEMPLATE_VIEW_NAME;

@Configuration
@ComponentScan(basePackages = {"dev.gemfire.spring.session.beanscopes.beans", "dev.gemfire.spring.session.beanscopes.controller"})
public class SessionCountConfiguration {
    @Bean
    public WebMvcConfigurer webMvcConfig() {
        return new WebMvcConfigurer() {

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName(INDEX_TEMPLATE_VIEW_NAME);
            }
        };
    }
}
