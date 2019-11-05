package com.example.demo.controller;


import com.example.demo.DTO.IndexDTO;
import com.example.demo.DTO.MsgCommentDTO;
import com.example.demo.DTO.SendMsgDTO;
import com.example.demo.common.HttpResult;
import com.example.demo.service.interfaces.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/index")
public class IndexController {
    @Autowired
    private IndexService indexService;


    @RequestMapping(value = "/indexShow",method = RequestMethod.POST)
    /**
    *@explain 分页显示
    *Author
    *@param indexDTO
     * page
     * size
     * content
    *@return com.example.demo.common.HttpResult
    *@time 2019/10/30 18:06
    */
    public HttpResult indexShow(@RequestBody IndexDTO indexDTO){
        return indexService.indexShow(indexDTO);
    }

    @RequestMapping(value = "/sendMsg",method = RequestMethod.POST)
    /**
    *@explain 发送消息
    *Author
    *@param sendMsgDTO
     * content
     * publishUrl
     * title
    *@param request
    *@return com.example.demo.common.HttpResult
    *@time 2019/10/30 18:06
    */
    public HttpResult sendMsg(@RequestBody SendMsgDTO sendMsgDTO, HttpServletRequest request){
        return indexService.sendMsg(sendMsgDTO,request);
    }

    @RequestMapping(value = "/msgComment",method = RequestMethod.POST)
    /**
    *@explain 消息评论
    *Author
    *@param msgCommentDTO
     * comment
     * publishId
    *@param request
    *@return com.example.demo.common.HttpResult
    *@time 2019/10/30 18:06
    */
    public HttpResult msgComment(@RequestBody MsgCommentDTO msgCommentDTO,HttpServletRequest request){
        return indexService.msgComment(msgCommentDTO, request);
    }

    @RequestMapping(value = "delMsgComment",method = RequestMethod.DELETE)
    /**
    *@explain 删除评论
    *Author
    *@param publishId
    *@param userId
    *@return com.example.demo.common.HttpResult
    *@time 2019/10/30 18:06
    */
    public HttpResult delMsgComment(@RequestParam(value = "publishId") Integer publishId,
                                    @RequestParam(value = "userId") String userId){
        return indexService.delMsgComment(publishId,userId);
    }

    @RequestMapping(value = "delMsg",method = RequestMethod.DELETE)
    /**
    *@explain 删除消息
    *Author
    *@param id
    *@param userid
    *@return com.example.demo.common.HttpResult
    *@time 2019/10/30 18:06
    */
    public HttpResult delMsg(@RequestParam(value = "id") Integer id,
                             @RequestParam(value = "userid") String userid){
        return indexService.delMsg(id,userid);
    }
}
