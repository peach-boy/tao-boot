

package com.wxt.tao.boot;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
public class WebLogPrinter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogPrinter.class);

    private static final List<Class> excludeParamTypes = Arrays.asList(ServletRequest.class, ServletResponse.class);


    @Pointcut("execution(public * com.wxt..*.*Controller.*(..))")
    public void logAspect() {
    }

    @Around("logAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        Method targetMethod = ((MethodSignature) signature).getMethod();

        Object[] params = joinPoint.getArgs();
        if (!checkIsPrintLog(signature, targetMethod)) {
            return joinPoint.proceed(params);
        }

        List<Object> paramsList = Arrays.asList(params);
        paramsList = paramsList.stream().filter(obj -> {
                    for (Class excludeType : excludeParamTypes) {
                        if (excludeType.isInstance(obj)) {
                            return false;
                        }
                    }
                    return true;
                }
        ).collect(Collectors.toList());

        String methodName = signature.getDeclaringType().getSimpleName() + "." + targetMethod.getName();
        LOGGER.info("WEB_REQ METHOD:{} PARAM:{}", methodName, LogUtil.truncate(JSON.toJSONString(paramsList)));
        try {
            Object resp = joinPoint.proceed(params);
            LOGGER.info("WEB_RSP METHOD:{} RESULT:{}", methodName, LogUtil.truncate(JSON.toJSONString(resp)));
            return resp;
        } catch (Throwable e) {
            LOGGER.error("WEB_EX METHOD:{} ERROR:", methodName, e);
            throw e;
        }
    }

    private boolean checkIsPrintLog(Signature signature, Method targetMethod) {
        if ("isLive".equals(targetMethod.getName())) {
            return false;
        }

        Class clazz = signature.getDeclaringType();
        Annotation clazzRespBody = AnnotationUtils.getAnnotation(clazz, ResponseBody.class);
        Annotation methodRespBody = targetMethod.getAnnotation(ResponseBody.class);
        if (clazzRespBody == null && methodRespBody == null) {
            return false;
        }

        Annotation logExclude = targetMethod.getAnnotation(LogExclude.class);
        if (logExclude != null) {
            return false;
        }

        return true;
    }

}
