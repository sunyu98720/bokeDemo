package com.example.demo.service.impl;

import com.example.demo.common.HttpResult;
import com.example.demo.service.interfaces.ActivityService;
import org.springframework.stereotype.Service;

/**
 * Author
 *
 * @param
 * @return
 * @time
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Override
    public HttpResult sign(String userId) {
        return HttpResult.SUCCESS("签到成功");
    }
}
