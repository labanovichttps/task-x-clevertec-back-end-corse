package ru.clevertec.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.clevertec.wrapper.CustomHttpServletRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class RequestWrapperFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        CustomHttpServletRequestWrapper wrappedRequest = new CustomHttpServletRequestWrapper(httpRequest);
        chain.doFilter(wrappedRequest, response);
    }
}
