package com.sise.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sise.bean.BsNoticeV2;
import com.sise.bean.TMessage;
import com.sise.entity.BsNotice;
import com.sise.mapper.BsNoticeMapper;
import com.sise.service.IBsNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.logging.Logger;

import static com.sise.common.Assert.*;
import static com.sise.common.DateUtils.getStrYMDHMByDate;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DMY
 * @since 2019-02-21
 */
@Service
public class BsNoticeServiceImpl extends ServiceImpl<BsNoticeMapper, BsNotice> implements IBsNoticeService {

    Logger logger = Logger.getLogger(LoginServiceImpl.class.getName());

    Map<String, Object> params = new HashMap<String, Object>();

    @Resource
    private BsNoticeMapper bsNoticeMapper;



    /*
     *  新增公告
     *  prarms: bs_teacher_id, bs_notice_content
     *  return: tMessage
     */
    public TMessage saveBsNotice(Long bs_teacher_id, String bs_notice_content){
        TMessage tMessage = new TMessage();
        try {
            if (isNull(bs_teacher_id) || isNull(bs_notice_content)){
                logger.info("bs_teacher_id或者bs_notice_content为空！");
                tMessage.setMessage("bs_teacher_id或者bs_notice_content为空！");
                tMessage.setStatus(0);
            } else {
                Date date = new Date();
                BsNotice bsNotice = new BsNotice();
                bsNotice.setBsTeacherId(bs_teacher_id);
                bsNotice.setBsNoticeContent(bs_notice_content);
                bsNotice.setBsNoticeDate(date);
                int insert = bsNoticeMapper.insert(bsNotice);
                if (notNull(insert) && insert != 0){
                    logger.info("成功发布！");
                    tMessage.setMessage("成功发布！");
                    tMessage.setStatus(200);
                } else {
                    logger.info("发布失败！");
                    tMessage.setMessage("发布失败！");
                    tMessage.setStatus(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错！");
            tMessage.setMessage("系统出错！");
            tMessage.setStatus(0);
        }
        return tMessage;
    }



    /*
     *  删除公告
     *  prarms: bs_notice_id
     *  return: tMessage
     */
    public TMessage deleteBsNotice(Long bs_notice_id){
        TMessage tMessage = new TMessage();
        try {
            if (isNull(bs_notice_id)){
                logger.info("bs_notice_id为空！");
                tMessage.setMessage("bs_notice_id为空！");
                tMessage.setStatus(0);
            } else {
                int i = bsNoticeMapper.deleteById(bs_notice_id);
                if (notNull(i) && i!= 0){
                    logger.info("成功删除！");
                    tMessage.setMessage("成功删除！");
                    tMessage.setStatus(200);
                } else {
                    logger.info("删除失败！");
                    tMessage.setMessage("删除失败！");
                    tMessage.setStatus(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错！");
            tMessage.setMessage("系统出错！");
            tMessage.setStatus(0);
        }
        return tMessage;
    }



    /*
     *  获取公告列表
     *  prarms: bs_teacher_id
     *  return: tMessage
     */
    public List<BsNoticeV2> getBsNotices(Long bs_teacher_id){
        try {
            if (isNull(bs_teacher_id)){
                logger.info("bs_teacher_id为空！");
                return null;
            } else {
                QueryWrapper<BsNotice> qw = new QueryWrapper<>();
                qw.eq("bs_teacher_id", bs_teacher_id);
                qw.orderByDesc("bs_notice_date");
                List<BsNotice> bsNotices = bsNoticeMapper.selectList(qw);
                List<BsNoticeV2> bsNoticeV2s = new ArrayList<>();
                for (BsNotice notice : bsNotices) {
                    BsNoticeV2 bsNoticeV2 = new BsNoticeV2();
                    bsNoticeV2.setBsNoticeId(notice.getBsNoticeId());
                    bsNoticeV2.setBsTeacherId(notice.getBsTeacherId());
                    bsNoticeV2.setBsNoticeContent(notice.getBsNoticeContent());
                    bsNoticeV2.setBsNoticeDate(getStrYMDHMByDate(notice.getBsNoticeDate()));
                    bsNoticeV2s.add(bsNoticeV2);
                }
                return bsNoticeV2s;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }







}
