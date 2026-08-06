package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.component.MyBean;

@RestController
public class BeanController {

    private final MyBean myBean;

    public BeanController(
            MyBean myBean) {

        this.myBean = myBean;

    }

    @GetMapping("/bean")
    public String bean() {

        return myBean.getMessage();

    }

}