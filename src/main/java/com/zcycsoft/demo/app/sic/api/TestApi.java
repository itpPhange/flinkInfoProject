package com.zcycsoft.demo.app.sic.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luopeng
 * @version V1.0
 * @Title: TestApi
 * @Package com.zcycsoft.app.sic.api
 * @Description:
 * @Copyright: Copyright (c) 2021
 * @Company:四川志诚元创信息科技有限公司
 * @date 2023/3/13 17:50
 * @description:
 */
@Api(value = "Test", tags = "测试")
@RestController
@RequestMapping("/test")
@Slf4j
public class TestApi {

    @ApiOperation(value = "显示", tags = "测试")
    @GetMapping("/show")
    public String show() {
        System.out.println(">>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>");
        return ("您好");
    }


}
