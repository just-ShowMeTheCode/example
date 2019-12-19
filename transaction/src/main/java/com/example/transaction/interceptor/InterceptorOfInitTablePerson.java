package com.example.transaction.interceptor;

import com.example.transaction.dao.PersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/1814:04
 */
@Component
@Slf4j
public class InterceptorOfInitTablePerson implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("request uri :{}",request.getRequestURI());
        if(request.getRequestURI().contains("person")){
            PersonMapper personMapper = getDao(PersonMapper.class,request);
            personMapper.truncateTable();
        }
        return true;

    }


    private <T> T  getDao(Class<T> t,HttpServletRequest request){
        BeanFactory beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return beanFactory.getBean(t);

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
