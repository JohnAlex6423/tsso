package com.olcow.tsso.config;

import com.olcow.tsso.dto.ResultDTO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

@ControllerAdvice
public class ResponseBodyAnalysis implements ResponseBodyAdvice {

    @Resource
    private ReturnCode returnCode;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResultDTO) {
            ResultDTO result = (ResultDTO) body;
            if (result.getMessage() != null && !result.getMessage().equals("")) {
                result.setMessage(returnCode.getCode().get(result.getMessage()));
            }
            return result;
        }
        return body;
    }
}
