package com.forkhome.demo.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/ali")
@RestController
@CrossOrigin
public class aliTest {

    @RequestMapping("/signIn")
    @ResponseBody
    public String signIn(@RequestBody String data1){
        String str = "{\"userName\":\"admin\",\"passWord\":\"123456\"}";
        if(data1.equals(str))
            return "success";
        return "falseeee";
    }
}
