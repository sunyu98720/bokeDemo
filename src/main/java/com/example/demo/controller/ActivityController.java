package com.example.demo.controller;

import com.example.demo.common.HttpResult;
import com.example.demo.service.interfaces.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Author
 *
 * @param
 * @return
 * @time
 */

@RestController
@RequestMapping(value = "/Activity")
@Validated
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/sign",method = RequestMethod.POST)
    public HttpResult signIn(@RequestParam(value = "userId") @NotNull(message = "用户ID不能为空") @NotBlank(message = "用户ID不能为空") String userId,
                           HttpServletRequest request){
        return activityService.signIn(userId,request);
    }
}
