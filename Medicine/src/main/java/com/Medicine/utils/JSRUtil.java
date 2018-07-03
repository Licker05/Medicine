package com.Medicine.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JSRUtil {
    /**
     * 如果返回null则表示没有错误
     * @param result
     * @return
     */
    public static String judgeValidate(BindingResult result, HttpServletResponse response) {
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            StringBuilder stringBuilder = new StringBuilder();
            for(ObjectError  error:list){
                stringBuilder.append("\n"+error.getDefaultMessage());
            }
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return stringBuilder.toString();
        }
        return "ok";
    }
}
