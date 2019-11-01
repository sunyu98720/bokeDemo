package com.example.demo.service.impl;

import com.example.demo.DTO.*;
import com.example.demo.common.HttpResult;
import com.example.demo.mapper.IndexMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.MsgComment;
import com.example.demo.model.Publish;
import com.example.demo.model.StudentForm;
import com.example.demo.service.interfaces.IndexService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexMapper indexMapper;

    @Autowired
    private StudentMapper studentMapper;

//    页面分页展示
    @Override
    public HttpResult indexShow(IndexDTO indexDTO) {
         Integer totalCount = indexMapper.count(indexDTO.getContent());
         if(totalCount == 0){
             return HttpResult.SUCCESS("1", "暂无数据");
         }
         indexDTO.setPage(indexDTO.getPage()-1);
         indexDTO.setIndex(indexDTO.getSize() * indexDTO.getPage());
        List<Publish> publishList = indexMapper.selectAllPublish(indexDTO.getIndex(), indexDTO.getSize(), indexDTO.getContent());
        if(publishList == null || publishList.isEmpty()){
            return HttpResult.SUCCESS("1", "暂无数据");
        }
        if(totalCount % indexDTO.getSize() == 0){
            indexDTO.setPages(totalCount/indexDTO.getSize());
        }else{
            indexDTO.setPages(totalCount/indexDTO.getSize() + 1);
        }
        List<PublishDTO> publishDTOS = new ArrayList<>();
        for(Publish publish : publishList){
            PublishDTO publishDTO = new PublishDTO();
            List<MsgComment> msgComment = indexMapper.findByMsgcomment(publish.getId());
            publishDTO.setMsgComments(msgComment);
            BeanUtils.copyProperties(publish, publishDTO);
            publishDTOS.add(publishDTO);
        }
        indexDTO.setPublishDTOS(publishDTOS);
        return HttpResult.SUCCESS(indexDTO);
    }

//    发送消息
    @Override
    public HttpResult sendMsg(SendMsgDTO sendMsgDTO,
                              HttpServletRequest request) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        StudentForm studentForm = (StudentForm) request.getSession().getAttribute("studentForm");
        if(studentForm == null){
            return HttpResult.error("-1", "用户名未登录");
        }
        Publish publish = new Publish();
        publish.setContent(sendMsgDTO.getContent());
        publish.setTitle(sendMsgDTO.getTitle());
        publish.setPublishUrl(sendMsgDTO.getPhotoUrl());
        publish.setCreateTime(ts);
        publish.setName(studentForm.getName());
        publish.setUserid(studentForm.getToken());
        publish.setUpdataTime(ts);
        indexMapper.createPublish(publish);
        return HttpResult.SUCCESS("发送成功");
    }


    @Override
//    写评论
    public HttpResult msgComment(MsgCommentDTO msgCommentDTO,HttpServletRequest request) {
        StudentForm studentForm = (StudentForm) request.getSession().getAttribute("studentForm");
        if(studentForm == null){
            return HttpResult.error("-1", "用户名未登录");
        }
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        MsgComment msgComment = new MsgComment();
        msgComment.setComment(msgCommentDTO.getComment());
        msgComment.setCreateTime(ts);
        msgComment.setPublishId(msgCommentDTO.getPublishId());
        msgComment.setName(studentForm.getName());
        msgComment.setUserId(studentForm.getToken());
        indexMapper.createComment(msgComment);
        return HttpResult.SUCCESS("评论成功");
    }

    @Override
    public HttpResult delMsgComment(Integer publishId,String userId) {
        indexMapper.delMsgComment(publishId,userId);
        return HttpResult.SUCCESS("删除成功");
    }

    @Transactional
    @Override
    public HttpResult delMsg(Integer id, String userid) {
        indexMapper.delMsg(id);
        indexMapper.delMsgComment(id, userid);
        return HttpResult.SUCCESS("success");
    }

}
