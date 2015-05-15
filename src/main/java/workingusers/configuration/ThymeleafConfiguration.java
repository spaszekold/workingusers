package workingusers.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;

/**
 * Created by Tomek on 2015-05-06.
 */
@Configuration
public class ThymeleafConfiguration {





    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();

    }


}
