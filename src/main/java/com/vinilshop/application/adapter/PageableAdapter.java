package com.vinilshop.application.adapter;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Application extension of {@link WebMvcConfigurationAdapter}.
 *
 * @author Roberto Amadeu Neto
 * @sice 07/02/2019
 * @since 1.0
 */
@Configuration
public class PageableAdapter extends WebMvcConfigurerAdapter {

    /**
     * Modifies the paging configurations.
     * 
     * @param argumentResolvers 
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver phmar = new PageableHandlerMethodArgumentResolver();
        phmar.setFallbackPageable(new PageRequest(0, 10));
        argumentResolvers.add(phmar);
    }
}
