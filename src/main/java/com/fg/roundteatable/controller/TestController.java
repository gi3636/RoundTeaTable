package com.fg.roundteatable.controller;

import com.fg.roundteatable.common.ResultVo;
import com.fg.roundteatable.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
public class TestController {


    @ResponseBody
    @GetMapping("/getData")
    public ResultVo getData(HttpServletRequest request){
        List<Student> studentList = new ArrayList<>();
        for (int i = 0 ; i < 3 ;i++){
            Student student = new Student();
            student.setName("学生"+i);
            student.setAge(19+i);
            student.setId(i);
            studentList.add(student);
        }
        System.out.println(request.toString());
        return ResultVo.ok().data("studentList",studentList);
    }
}
