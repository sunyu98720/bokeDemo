package com.example.demo.service.interfaces;

import com.example.demo.DTO.IndexDTO;
import com.example.demo.DTO.MsgCommentDTO;
import com.example.demo.DTO.SendMsgDTO;
import com.example.demo.common.HttpResult;

import javax.servlet.http.HttpServletRequest;

public interface IndexService {
    HttpResult indexShow(IndexDTO indexDTO);
    HttpResult sendMsg (SendMsgDTO sendMsgDTO, HttpServletRequest request);
    HttpResult msgComment(MsgCommentDTO msgCommentDTO,HttpServletRequest request);
    HttpResult delMsgComment(Integer publishId,String userId);
    HttpResult delMsg(Integer id, String userid);
}
