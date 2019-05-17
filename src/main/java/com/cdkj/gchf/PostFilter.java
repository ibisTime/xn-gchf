package com.cdkj.gchf;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PostFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(getClass());

    private FilterConfig filterConfig = null;

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (filterConfig == null)
            return;

        Enumeration<String> names = request.getParameterNames();
        StringBuilder output = new StringBuilder();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            output.append(name).append("=");
            String values[] = request.getParameterValues(name);
            for (int i = 0; i < values.length; i++) {
                if (i > 0) {
                    output.append("' ");
                }

                output.append(values[i]);
            }
            if (names.hasMoreElements())
                output.append("&");
        }
        request.setAttribute("postdata", output);
        logger.info("postdata: " + output);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
}
