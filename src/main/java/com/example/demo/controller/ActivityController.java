package com.example.demo.controller;

import com.example.demo.common.HttpResult;
import com.example.demo.service.interfaces.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Author
 *
 * @param
 * @return
 * @time
 */

@RestController
@RequestMapping(value = "/Activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/sign",method = RequestMethod.POST)
    public HttpResult sign(@RequestParam(value = "userId") String userId,
                           HttpServletRequest request){
        return activityService.sign(userId,request);
    }
}
