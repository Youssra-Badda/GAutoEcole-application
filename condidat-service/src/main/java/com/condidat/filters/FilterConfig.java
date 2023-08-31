package com.condidat.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {

//    @Autowired
//    private MyFilter fakeAutoEcoleFilter;
//    
//    
//    @Bean
//    public FilterRegistrationBean<MyFilter> fakeAutoEcoleFilterRegistration() {
//        FilterRegistrationBean<MyFilter> registration = new FilterRegistrationBean<>();
//        registration.setFilter(fakeAutoEcoleFilter);
//        registration.addUrlPatterns("/*"); // Ajoutez ici les URL que vous souhaitez filtrer
//        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return registration;
//    }
//    
    
	@Autowired
     private AutoEcoleIdFilter fakeAutoEcoleFilter;
	
    @Bean
    public FilterRegistrationBean<AutoEcoleIdFilter> customAutoEcoleIdFilter() {
        FilterRegistrationBean<AutoEcoleIdFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(fakeAutoEcoleFilter);
        registrationBean.addUrlPatterns("/*"); // Replace "/api/*" with the appropriate URL pattern
        return registrationBean;
    }



}
