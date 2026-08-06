package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/save")
    public String save() {

        stringRedisTemplate.opsForValue()
                .set("name", "Tom", 60, TimeUnit.SECONDS);

        return "OK";
    }

    @GetMapping("/get")
    public Object get() {

        return stringRedisTemplate.opsForValue()
                .get("name");
    }

    @GetMapping("/saveUser")
    public String saveUser() {

        stringRedisTemplate.opsForHash()
                .put("user:1", "name", "Tom");

        stringRedisTemplate.opsForHash()
                .put("user:1", "age", "18");

        return "OK";
    }

    @GetMapping("/view")
    public String view() {

        Long count =
                stringRedisTemplate.opsForValue()
                        .increment("article:1:view");

        return "View Count = " + count;
    }


}