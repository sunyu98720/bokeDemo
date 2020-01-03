package com.example.demo.service.impl;

import com.example.demo.DTO.*;
import com.example.demo.common.HttpResult;
import com.example.demo.common.return_values.indexEnum;
import com.example.demo.mapper.MsgCommentMapper;
import com.example.demo.mapper.PublishMapper;
import com.example.demo.mapper.StudentFormMapper;
import com.example.demo.model.*;
import com.example.demo.service.interfaces.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private PublishMapper publishMapper;

    @Autowired
    private StudentFormMapper studentFormMapper;

    @Autowired
    private MsgCommentMapper msgCommentMapper;

    private static final Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);


    //    页面分页展示
    @Override
    public HttpResult indexShow(IndexDTO indexDTO) {
         PublishExample publishExample = new PublishExample();
         publishExample.createCriteria()
                 .andContentLike(indexDTO.getContent());
         Integer totalCount = publishMapper.countByExample(publishExample);
         if(totalCount == 0){
             return HttpResult.SUCCESS("1", "暂无数据");
         }
         indexDTO.setPage(indexDTO.getPage()-1);
         indexDTO.setIndex(indexDTO.getSize() * indexDTO.getPage());
         List<Publish> publishList = publishMapper.selectAllPublish(indexDTO.getIndex(), indexDTO.getSize(), indexDTO.getContent());
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
            MsgCommentExample msgCommentExample = new MsgCommentExample();
            msgCommentExample.createCriteria()
                    .andPublishidEqualTo(publish.getId());
            List<MsgComment> msgComment = msgCommentMapper.selectByExample(msgCommentExample);
            publishDTO.setMsgComments(msgComment);
            BeanUtils.copyProperties(publish, publishDTO);
            publishDTOS.add(publishDTO);
        }
        indexDTO.setPublishDTOS(publishDTOS);

        logger.info("返回数据:" + indexDTO);

        return HttpResult.SUCCESS(indexDTO);
    }

//    发送消息
    @Override
    public HttpResult sendMsg(SendMsgDTO sendMsgDTO,
                              HttpServletRequest request) {
        logger.info("入参:" + sendMsgDTO);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        StudentForm studentForm = (StudentForm) request.getSession().getAttribute("studentForm");
        Publish publish = new Publish();
        publish.setContent(sendMsgDTO.getContent());
        publish.setTitle(sendMsgDTO.getTitle());
        publish.setPublishurl(sendMsgDTO.getPhotoUrl());
        publish.setCreatetime(ts);
        publish.setName(studentForm.getName());
        publish.setUserid(studentForm.getToken());
        publish.setUpdatatime(ts);
        publishMapper.insert(publish);
        logger.info("发送成功:" + publish);
        return HttpResult.SUCCESS("发送成功");
    }


    @Override
//    写评论
    public HttpResult msgComment(MsgCommentDTO msgCommentDTO,HttpServletRequest request) {
        logger.info("评论内容:" + msgCommentDTO.getComment()  + "," + "评论PublishId:" + msgCommentDTO.getPublishId());
        StudentFormExample studentFormExample = new StudentFormExample();
        studentFormExample.createCriteria()
                .andTokenEqualTo(msgCommentDTO.getUserId());
        StudentForm studentForm = (StudentForm) studentFormMapper.selectByExample(studentFormExample);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        MsgComment msgComment = new MsgComment();
        msgComment.setComment(msgCommentDTO.getComment());
        msgComment.setCreatetime(ts);
        msgComment.setPublishid(msgCommentDTO.getPublishId());
        msgComment.setName(studentForm.getName());
        msgComment.setUserid(studentForm.getToken());
        msgCommentMapper.insert(msgComment);
        logger.info("评论成功:" + msgComment);
        return HttpResult.SUCCESS("评论成功:" + msgComment);
    }

//    删除评论
    @Override
    public HttpResult delMsgComment(Integer publishId,String userId,HttpServletRequest request) {
        MsgCommentExample msgCommentExample = new MsgCommentExample();
        msgCommentExample.createCriteria()
                .andPublishidEqualTo(publishId);
        if(msgCommentMapper.selectByExample(msgCommentExample) == null){
            return HttpResult.error(indexEnum.DELMSG_ERROR.getStatus(), indexEnum.DELMSG_ERROR.getMessage());
        }
        MsgCommentExample commentExample = new MsgCommentExample();
        commentExample.createCriteria()
                .andPublishidEqualTo(publishId)
                .andUseridEqualTo(userId);
        msgCommentMapper.deleteByExample(msgCommentExample);
        return HttpResult.SUCCESS("删除成功");

    }

//    删除说说
    @Transactional  //开启事务管理
    @Override
    public HttpResult delMsg(Integer id, String userid) {
        try {
            publishMapper.deleteByPrimaryKey(id);
            MsgCommentExample msgCommentExample = new MsgCommentExample();
            msgCommentExample.createCriteria()
                    .andIdEqualTo(id)
                    .andUseridEqualTo(userid);
            msgCommentMapper.deleteByExample(msgCommentExample);
            logger.info("评论删除成功," + "评论id:" + id + ",userid:" + userid);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.SYSTEM_ERROR();
        }
        return HttpResult.SUCCESS("success");
    }

}
