package com.fg.roundteatable.common.ValidException;

import com.fg.roundteatable.common.ResultCode.ResultCode;
import com.fg.roundteatable.common.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;



@Slf4j
@RestControllerAdvice
public class ValidException {

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultVo handleValidException(MethodArgumentNotValidException e){
        Map<String ,String> map  =new HashMap<>();
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getFieldErrors().forEach((fieldError)->{
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return new  ResultVo(ResultCode.PARAM_ERROR).data("error",map);
    }
}
