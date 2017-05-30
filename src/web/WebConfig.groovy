package web

import groovy.util.logging.Slf4j
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by admin on 2017/5/30.
 */

@Slf4j
@Configuration
class WebConfig  extends WebMvcConfigurerAdapter {
    void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/page/**")
        super.addInterceptors(registry)
    }

    class  MyInterceptor implements HandlerInterceptor {
        @Override
        boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
            log.info "MyInterceptor before controller"
            httpServletResponse.sendRedirect("/rest/cookbook/list")
            return false
        }

        @Override
        void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
            log.info "MyInterceptor post controller"
        }

        @Override
        void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
            log.info "MyInterceptor after controller"
        }
    }
}
