package com.olcow.tsso.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix="return")
@PropertySource(value = "classpath:ReturnCode.properties",encoding ="UTF-8")
class ReturnCode {
    private Map<String,String> code;

    public Map<String, String> getCode() {
        return code;
    }

    public void setCode(Map<String, String> code) {
        this.code = code;
    }
}
