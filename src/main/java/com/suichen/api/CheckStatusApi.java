package com.suichen.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckStatusApi {
    @RequestMapping("/check_status")
    public String checkStatus(@RequestParam(value = "name",required = false) String name) {
        return "SUCCESS "+name;
    }
}
