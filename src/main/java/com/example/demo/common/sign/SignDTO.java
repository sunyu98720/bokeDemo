package com.example.demo.common.sign;

import lombok.Data;

import java.lang.ref.PhantomReference;
import java.sql.Timestamp;

/**
 * Author
 *
 * @param
 * @return
 * @time
 */
@Data
public class SignDTO {
    /**
     * 用户userid
     */
    private String token;
    /**
     * 时间戳
     */
    private Timestamp timestamp;
    /**
     * 固定随机串
     */
    private String RandomStr;
}
