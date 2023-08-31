//package com.condidat.filters;
//
//
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//
//@Component
//public class MyFilter implements Filter {
//
//    private static final Long FAKE_AUTOECOLE_ID = (long) 1; // faux ID auto ecole
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//    	
//        request.setAttribute("idA", FAKE_AUTOECOLE_ID);
//
//        
//        
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // Initialisation du filtre (peut être laissé vide)
//    }
//
//    @Override
//    public void destroy() {
//        // Nettoyage du filtre (peut être laissé vide)
//    }
//}
//
