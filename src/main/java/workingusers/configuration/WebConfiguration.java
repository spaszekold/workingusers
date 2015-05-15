package workingusers.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;

import org.springframework.data.web.PageableHandlerMethodArgumentResolver;

import java.util.List;

/**
 * Created by Tomek on 2015-05-07.
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {



}