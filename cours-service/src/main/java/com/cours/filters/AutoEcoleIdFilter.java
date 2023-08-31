package com.cours.filters;



import java.io.IOException;



import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;



@Component
public class AutoEcoleIdFilter implements Filter {
    

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
    	 HttpServletRequest httpRequest = (HttpServletRequest) request;

         
         
//       String autoEcoleIdHeader = httpRequest.getHeader("AUTOECOLE");
//
//       Long autoEcoleId = null;
//       try {
//           autoEcoleId = Long.parseLong(autoEcoleIdHeader);
//       } catch (NumberFormatException e) {
//           System.out.println(e.getLocalizedMessage());
//       }
//
//       if (autoEcoleId != null) {
//       	 request.setAttribute("idA", autoEcoleId);
//       }

       
       request.setAttribute("idA", 1L);


        // Continuer la chaîne de filtres
        chain.doFilter(request, response);
    }


	
}
