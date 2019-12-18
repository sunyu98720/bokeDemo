package com.example.demo.service.impl;

import com.example.demo.DTO.*;
import com.example.demo.common.HttpResult;
import com.example.demo.common.return_values.indexEnum;
import com.example.demo.mapper.IndexMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.MsgComment;
import com.example.demo.model.Publish;
import com.example.demo.model.StudentForm;
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
    private IndexMapper indexMapper;

    @Autowired
    private StudentMapper studentMapper;

    private static final Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);


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
        publish.setPublishUrl(sendMsgDTO.getPhotoUrl());
        publish.setCreateTime(ts);
        publish.setName(studentForm.getName());
        publish.setUserid(studentForm.getToken());
        publish.setUpdataTime(ts);
        indexMapper.createPublish(publish);
        logger.info("发送成功:" + publish);
        return HttpResult.SUCCESS("发送成功");
    }


    @Override
//    写评论
    public HttpResult msgComment(MsgCommentDTO msgCommentDTO,HttpServletRequest request) {
        logger.info("评论内容:" + msgCommentDTO.getComment()  + "," + "评论PublishId:" + msgCommentDTO.getPublishId());
//        StudentForm studentForm = (StudentForm) request.getSession().getAttribute("studentForm");
        StudentForm studentForm = studentMapper.findByToken(msgCommentDTO.getUserId());
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        MsgComment msgComment = new MsgComment();
        msgComment.setComment(msgCommentDTO.getComment());
        msgComment.setCreateTime(ts);
        msgComment.setPublishId(msgCommentDTO.getPublishId());
        msgComment.setName(studentForm.getName());
        msgComment.setUserId(studentForm.getToken());
        indexMapper.createComment(msgComment);
        logger.info("评论成功:" + msgComment);
        return HttpResult.SUCCESS("评论成功:" + msgComment);
    }

//    删除评论
    @Override
    public HttpResult delMsgComment(Integer publishId,String userId,HttpServletRequest request) {
        if(indexMapper.findByMsgcomment(publishId) == null){
            return HttpResult.error(indexEnum.DELMSG_ERROR.getStatus(), indexEnum.DELMSG_ERROR.getMessage());
        }
        indexMapper.delMsgComment(publishId,userId);
        return HttpResult.SUCCESS("删除成功");
    }

//    删除说说
    @Transactional
    @Override
    public HttpResult delMsg(Integer id, String userid) {
        try {
            indexMapper.delMsg(id);
            indexMapper.delMsgComment(id,userid);
            logger.info("评论删除成功," + "评论id:" + id + ",userid:" + userid);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.SYSTEM_ERROR();
        }
        return HttpResult.SUCCESS("success");
    }

}
