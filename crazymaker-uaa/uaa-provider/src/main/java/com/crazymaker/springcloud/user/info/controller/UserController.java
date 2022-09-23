package com.crazymaker.springcloud.user.info.controller;

import com.alibaba.fastjson.JSONObject;
import com.crazymaker.springcloud.common.dto.UserDTO;
import com.crazymaker.springcloud.common.result.RestOut;
import com.crazymaker.springcloud.user.info.service.impl.FrontUserEndSessionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api(value = "用户信息、基础学习DEMO", tags = {"用户信息、基础学习DEMO"})
@RestController
@RequestMapping("/api/user" )
public class UserController
{

    @Resource
    private FrontUserEndSessionServiceImpl userService;
    /**
     * 注入全局的加密器
     */
    @Resource
    PasswordEncoder passwordEncoder;

    @GetMapping("/detail/v1" )
    @ApiOperation(value = "获取用户信息" )
    public RestOut<UserDTO> getUser(@RequestParam(value = "userId", required = true) Long userId)
    {
        UserDTO dto = userService.getUser(userId);
        if (null == dto)
        {
            return RestOut.error("没有找到用户" );
        }
        return RestOut.success(dto).setRespMsg("操作成功" );
    }


    @GetMapping("/passwordEncoder/v1" )
    @ApiOperation(value = "密码加密" )
    public RestOut<String> passwordEncoder(
            @RequestParam(value = "raw", required = true) String raw)
    {

//        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encode = passwordEncoder.encode(raw);
        return RestOut.success(encode);
    }
}