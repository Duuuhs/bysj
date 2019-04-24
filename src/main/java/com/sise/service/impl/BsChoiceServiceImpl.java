package com.sise.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sise.bean.QueryStuBsChoiceStatusPage;
import com.sise.bean.StuBsChoiceStatus;
import com.sise.bean.TMessage;
import com.sise.bean.TeaQueryNotChoiceStu;
import com.sise.entity.BaseStudent;
import com.sise.entity.BsChoice;
import com.sise.mapper.BsChoiceMapper;
import com.sise.service.IBsChoiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sise.service.IBsTopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static com.sise.common.Assert.isNull;
import static com.sise.common.Assert.notNull;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DMY
 * @since 2019-02-13
 */
@Service
public class BsChoiceServiceImpl extends ServiceImpl<BsChoiceMapper, BsChoice> implements IBsChoiceService {

    Logger logger = Logger.getLogger(BsChoiceServiceImpl.class.getName());

    Map<String, Object> params = new HashMap<String, Object>();

    @Resource
    private IBsChoiceService bsChoiceService;

    @Resource
    private BsChoiceMapper bsChoiceMapper;

    @Resource
    private IBsTopicService bsTopicService;


    /*
     *  根据student_id查询该学生的课题选择转态，以及评级意见
     */
    public BsChoice getStuBsChoice(Long student_id){
        if (isNull(student_id)){
            logger.info("学生id不能拿为空！");
            return null;
        }
        QueryWrapper<BsChoice> qw = new QueryWrapper<BsChoice>();
        params.put("student_id", student_id);
        qw.allEq(true, params, true);
        BsChoice stuBsChoice = bsChoiceService.getOne(qw);
        return stuBsChoice;
    }



    /*
     *  删除学生选题
     */
    public TMessage deleteStuBsChoice(Long student_id, Long bs_topic_id){
        TMessage tMessage = new TMessage();
        try {
            if (isNull(student_id) || isNull(bs_topic_id)){
                logger.info("参数不足！");
                tMessage.setMessage("参数不足!");
            } else {
                QueryWrapper<BsChoice> qw = new QueryWrapper<>();
                params.put("student_id", student_id);
                params.put("bs_topic_id", bs_topic_id);
                qw.allEq(true,params,true);
                BsChoice bsChoice = bsChoiceMapper.selectOne(qw);
                if (isNull(bsChoice)){
                    logger.info("查无相关的选题记录！");
                    tMessage.setMessage("查无相关的选题记录!");
                } else if (notNull(bsChoice.getIsSure()) && "T".equals(bsChoice.getIsSure())){
                    logger.info("该题目已经教师确认过，请先取消该状态！");
                    tMessage.setMessage("该题目已经教师确认过，请先取消该状态!");
                } else {
                    /*删除选题记录*/
                    Long bsChoiceId = bsChoice.getBsChoiceId();
                    int i = bsChoiceMapper.deleteById(bsChoiceId);
                    if (notNull(i)){
                        /*将记录中的毕业设计题目的isChoice状态设置为F*/
                        String result = bsTopicService.changeBsChoice(bs_topic_id, "F");
                        if (notNull(result) && "SUCCESS".equals(result)){
                            logger.info("删除成功,该题目可以重新选择学生！");
                            tMessage.setMessage("删除成功,该题目可以重新选择学生!");
                        } else {
                            logger.info("删除失败！");
                            tMessage.setMessage("删除失败!");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错！");
            tMessage.setMessage("系统出错!");
        }
        return tMessage;
    }


    /*
     *  查询该教师下未选题学生
     *  params：bysj_teacher_id
     */
    public List<TeaQueryNotChoiceStu> getNotChoiceStu(Long bysj_teacher_id){
        try {
            if (isNull(bysj_teacher_id)){
                logger.info("参数不足！");
                return null;
            } else {
                List<TeaQueryNotChoiceStu> teaQueryNotChoiceStus = bsChoiceMapper.selectNotChoiceStu(bysj_teacher_id);
                return teaQueryNotChoiceStus;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错！");
            return null;
        }
    }


    /*
     *查看教师下学生的选题状态(评分意见模块)(分页)
     * params:bysj_teacher_id
     */
    public QueryStuBsChoiceStatusPage getStuBsChoiceStatusPage(int page_num, Long bysj_teacher_id){
        QueryStuBsChoiceStatusPage queryStuBsChoiceStatusPage = new QueryStuBsChoiceStatusPage();
        try {
            if (isNull(bysj_teacher_id) || isNull(page_num)){
                logger.info("参数不足！");
                queryStuBsChoiceStatusPage.setMessage("参数不足！");
            } else {
                int i = bsChoiceMapper.selectStuChoiceStatusCount(bysj_teacher_id);
                int pageRecord = 5;//默认设置每行5条数据
                int pag_max = (int)Math.ceil((double)i/(double)pageRecord);
                int dataStart;
                int dataEnd;
                if (page_num >= pag_max){
                    page_num = pag_max;
                    dataStart = (page_num - 1)* pageRecord + 1;
                    dataEnd = i;
                } else {
                    dataStart = (page_num - 1)* pageRecord + 1;
                    dataEnd = page_num * pageRecord;
                }
                Page<BaseStudent> baseStudentPage = new Page<>(page_num,pageRecord);
                List<StuBsChoiceStatus> stuBsChoiceStatuses = bsChoiceMapper.selectStuChoiceStatus(baseStudentPage, bysj_teacher_id);
                queryStuBsChoiceStatusPage.setPageMax(pag_max);
                queryStuBsChoiceStatusPage.setPageNum(page_num);
                queryStuBsChoiceStatusPage.setDataStart(dataStart);
                queryStuBsChoiceStatusPage.setDataEnd(dataEnd);
                queryStuBsChoiceStatusPage.setStuCount(i);
                queryStuBsChoiceStatusPage.setStuList(stuBsChoiceStatuses);
                queryStuBsChoiceStatusPage.setMessage("查找成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错！");
            queryStuBsChoiceStatusPage.setMessage("系统出错！");
        }
        return queryStuBsChoiceStatusPage;
    }



    /*
     * 保存学生的毕业设计评分意见
     * params： bs_choice_id
     */
    public TMessage saveStuBsChoiceById(Long bs_choice_id,Double review_point,Double reply_point,String guidance){
        TMessage tMessage = new TMessage();
        try {
            if (isNull(bs_choice_id)){
                logger.info("参数不足！");
                tMessage.setMessage("参数不足！");
            } else {
                BsChoice bsChoice = new BsChoice();
                if (notNull(guidance)){
                    bsChoice.setGuidance(guidance);
                }
                if (notNull(reply_point)){
                    bsChoice.setReplyPoint(reply_point);
                }
                if (notNull(review_point)){
                    bsChoice.setReviewPoint(review_point);
                }

                QueryWrapper<BsChoice> qw = new QueryWrapper<>();
                qw.eq("bs_choice_id", bs_choice_id);
                int update = bsChoiceMapper.update(bsChoice, qw);
                if (notNull(update)){
                    logger.info("保存成功！");
                    tMessage.setMessage("保存成功！");
                } else {
                    logger.info("保存失败！");
                    tMessage.setMessage("保存失败！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错！");
            tMessage.setMessage("系统出错！");
        }
        return tMessage;
    }


}
