package com.condidat.filters;



import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;


@Component
public class AutoEcoleIdFilter implements Filter {
    


    
    
    
    @Override    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        
        
//        String autoEcoleIdHeader = httpRequest.getHeader("AUTOECOLE");
//
//        Long autoEcoleId = null;
//        try {
//            autoEcoleId = Long.parseLong(autoEcoleIdHeader);
//        } catch (NumberFormatException e) {
//            System.out.println(e.getLocalizedMessage());
//        }
//
//        if (autoEcoleId != null) {
//        	 request.setAttribute("idA", autoEcoleId);
//        }

        
        request.setAttribute("idA", 1L);

        // Continuer la chaîne de filtres
        chain.doFilter(request, response);
    }


	
}
