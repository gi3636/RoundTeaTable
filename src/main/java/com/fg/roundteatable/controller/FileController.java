package com.fg.roundteatable.controller;

import com.fg.roundteatable.common.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("file")
public class FileController {

    public ResultVo addFile(){
        return ResultVo.ok();
    }

    @GetMapping("getAll")
    public ResultVo getAllFile(){
        return ResultVo.ok().data("测试","测试");
    }

    @DeleteMapping("delete/{id}")
    public ResultVo deleteFile(@PathVariable String id){
        return ResultVo.ok();
    }


    @DeleteMapping("delete")
    public ResultVo deleteFiles(){
        return ResultVo.ok();
    }

}
