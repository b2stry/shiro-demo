package com.shallowan.controller;

import com.shallowan.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @PostMapping(value = "/subLogin", produces = "application/json;charset=utf-8")
    public String subLogin(UserVO user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }

        if (subject.hasRole("admin")) {
            return "有admin权限";
        }
        return "无admin权限";
    }

    @RequiresRoles("admin")
    @GetMapping("/testRole")
    public String testRole() {
        return "testRole success";
    }

    @RequiresRoles("user")
    @GetMapping("/testRole1")
    public String testRole1() {
        return "testRole1 success";
    }
}
