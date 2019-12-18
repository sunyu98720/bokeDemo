package com.example.demo.service.interfaces;

import com.example.demo.DTO.RegistDTO;
import com.example.demo.common.HttpResult;

public interface RegistService {
    
    HttpResult userRegist(RegistDTO registDTO);

    HttpResult changeUserInfo(RegistDTO registDTO);
}
