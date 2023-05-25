package sample.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static sample.client.Application.INDEX_TEMPLATE_VIEW_NAME;

@Configuration
@ComponentScan(basePackages = {"sample.client.model", "sample.client.controller"})
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
