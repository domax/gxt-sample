package com.domax.gwt.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * A simple servlet filter implementation of <a
 * href='http://www.gwtproject.org/doc/latest/DevGuideCompilingAndDebugging.html#perfect_caching'>GWT Perfect
 * Caching</a>
 *
 * @author <a href="mailto:max@dominichenko.com">Maxim Dominichenko</a>
 */
@Slf4j
public class GWTPerfectCacheFilter implements Filter {

    private static final String CACHE = ".cache.";
    private static final String NO_CACHE = ".nocache.";

    private static final String CACHE_PARAM = "cacheUrlSubstrings";
    private static final String NO_CACHE_PARAM = "noCacheUrlSubstrings";

    private final Set<String> cacheUrlSubstrings = new TreeSet<>();
    private final Set<String> noCacheUrlSubstrings = new TreeSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        cacheUrlSubstrings.add(CACHE);
        Optional.ofNullable(filterConfig.getInitParameter(CACHE_PARAM))
                .filter(StringUtils::isNotBlank)
                .ifPresent(v -> Stream.of(v.split(","))
                        .filter(StringUtils::isNotBlank)
                        .map(String::trim)
                        .forEach(cacheUrlSubstrings::add));
        log.info("{}: {}", CACHE_PARAM, cacheUrlSubstrings);

        noCacheUrlSubstrings.add(NO_CACHE);
        Optional.ofNullable(filterConfig.getInitParameter(NO_CACHE_PARAM))
                .filter(StringUtils::isNotBlank)
                .ifPresent(v -> Stream.of(v.split(","))
                        .filter(StringUtils::isNotBlank)
                        .map(String::trim)
                        .forEach(noCacheUrlSubstrings::add));
        log.info("{}: {}", NO_CACHE_PARAM, noCacheUrlSubstrings);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletResponse r = (HttpServletResponse) response;
        final String requestUrl = ((HttpServletRequest) request).getRequestURL().toString();
        if (!StringUtils.isEmpty(requestUrl)) {
            if (noCacheUrlSubstrings.stream().anyMatch(requestUrl::contains)) {
                log.debug("{} should not be cached", requestUrl);
                r.setHeader("ExpiresActive", "on");
                r.setHeader("ExpiresDefault", "now");
                r.setHeader("Cache-Control", "public, max-age=0, no-cache, no-store, must-revalidate");
                r.setHeader("Pragma", "no-cache");
                r.setDateHeader("Date", System.currentTimeMillis());
                r.setDateHeader("Expires", System.currentTimeMillis() - 86400000L);
            } else if (cacheUrlSubstrings.stream().anyMatch(requestUrl::contains)) {
                log.debug("{} marked as cached", requestUrl);
                r.setHeader("ExpiresActive", "on");
                r.setHeader("ExpiresDefault", "now plus 1 year");
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        cacheUrlSubstrings.clear();
        noCacheUrlSubstrings.clear();
        log.info("Cache filter destroyed");
    }
}
