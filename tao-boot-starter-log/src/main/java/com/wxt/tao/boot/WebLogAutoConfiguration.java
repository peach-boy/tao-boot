
package com.wxt.tao.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class WebLogAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public WebLogPrinter webLogPrinter() {

        return new WebLogPrinter();
    }
}
