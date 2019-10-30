package main.logic.controllers.adapters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author F0urth
 */
@Configuration
@EnableWebMvc
@ComponentScan({"main.logic.controllers.adapters"})
public class SpringConfiguration {
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
