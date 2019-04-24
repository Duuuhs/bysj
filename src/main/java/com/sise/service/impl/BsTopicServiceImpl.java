package com.sise.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sise.bean.*;
import com.sise.common.DateUtils;
import com.sise.entity.BsChoice;
import com.sise.entity.BsTopic;
import com.sise.mapper.BsChoiceMapper;
import com.sise.mapper.BsTopicMapper;
import com.sise.service.IBsChoiceService;
import com.sise.service.IBsTopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class BsTopicServiceImpl extends ServiceImpl<BsTopicMapper, BsTopic> implements IBsTopicService {

    Logger logger = Logger.getLogger(LoginServiceImpl.class.getName());

    Map<String, Object> params = new HashMap<String, Object>();

    @Resource
    private IBsTopicService bsTopicService;

    @Resource
    private IBsChoiceService bsChoiceService;

    @Resource
    private BsChoiceMapper bsChoiceMapper;

    @Resource
    private BsTopicMapper bsTopicMapper;




    /*
     *  改变毕业设计题目的is_Choice属性
     *  params: bs_topic_id, is_choice(T/F)
     *  return: SUCCESS/FAIL
     */
    public String changeBsChoice(Long bs_topic_id, String is_choice){
        String result = null;
        try {
            if (isNull(bs_topic_id) || isNull(is_choice)){
                logger.info("bs_topic_id或者is_choice不能为空！");
            } else if ("T".equals(is_choice) || "F".equals(is_choice)){
                BsTopic bsTopic = new BsTopic();
                bsTopic.setIsChoice(is_choice);
                UpdateWrapper<BsTopic> uw = new UpdateWrapper<>();
                uw.eq("bs_topic_id", bs_topic_id);
                int update = bsTopicMapper.update(bsTopic, uw);
                if (isNull(update) || update == 0){
                    result = "FAIL";
                } else {
                    result = "SUCCESS";
                }
            } else {
                logger.info("is_choice必须为T或F！");

            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错");
        }
        return result;
    }


    /*
     *  根据teacher_id查询该教师布置了多少条毕设题目
     *  params: teacher_id
     */
    @Override
    public TeaBsTopics getTeaBsTopics(Long bs_teacher_id){
        TeaBsTopics teaBsTopics = new TeaBsTopics();
        try {
            if (isNull(bs_teacher_id)){
                logger.info("教师id不能为空！");
                teaBsTopics.setMessage("教师id不能为空！");
            } else {
                QueryWrapper<BsTopic> qw = new QueryWrapper<BsTopic>();
                params.put("bs_teacher_id", bs_teacher_id);
                qw.allEq(true, params, true);
                List<BsTopic> bsTopics = bsTopicService.list(qw);
                int count = bsTopicService.count(qw);
                if (isNull(bsTopics)){
                    logger.info("查无该教师的毕业设计题目");
                    teaBsTopics.setMessage("查无该教师的毕业设计题目");
                    teaBsTopics.setCoutBsTopic(count);
                } else {
                    logger.info("查到该教师的毕业设计题目");
                    teaBsTopics.setMessage("查到该教师的毕业设计题目");
                    teaBsTopics.setCoutBsTopic(count);
                    teaBsTopics.setBsTopics(bsTopics);

                }
            }
        } catch (Exception e) {
            logger.info("系统出错");
            e.printStackTrace();
        }
        return teaBsTopics;
    }


    /*
     *  查询该教师布置了多少条毕设题目,和该学生是否已经选题，教师是否确认题目
     *  params: teacher_id, student_id
     */
    public StuQueryTeaBsTopics getTeaBsTopicsV2(Long bs_teacher_id, Long student_id){
        StuQueryTeaBsTopics stuQueryTeaBsTopics = new StuQueryTeaBsTopics();
        try {
            TeaBsTopics teaBsTopics = bsTopicService.getTeaBsTopics(bs_teacher_id);
            stuQueryTeaBsTopics.setTeaBsTopics(teaBsTopics);
            if (isNull(teaBsTopics)){
                logger.info(teaBsTopics.getMessage());
                return stuQueryTeaBsTopics;
            }
            /*查询学生是否已经选过题目了*/
            BsChoice stuBsChoice = bsChoiceService.getStuBsChoice(student_id);
            if (isNull(stuBsChoice)){
                stuQueryTeaBsTopics.setHasChoice("F");
                stuQueryTeaBsTopics.setHasTeaChoice("F");
            } else {
                stuQueryTeaBsTopics.setHasChoice("T");
                /*教师是否确认过该学生选的题目*/
                if ("T".equals(stuBsChoice.getIsSure())){
                    stuQueryTeaBsTopics.setHasTeaChoice("T");
                } else {
                    stuQueryTeaBsTopics.setHasTeaChoice("F");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stuQueryTeaBsTopics;
    }



    /*
     *  选择毕业设计题目(教师已确认无法选题在前端进行控制！)
     *  prarms: bs_topic_id, student_id
     *  return: tMessage
     */
    public TMessage saveOrUpdateStuChoice(Long bs_topic_id, Long student_id){
        TMessage tMessage = new TMessage();
        try {
            if (isNull(bs_topic_id) || isNull(student_id)) {
                logger.info("bs_topic_id或者student_id不能拿为空！");
                tMessage.setMessage("bs_topic_id或者student_id不能拿为空！");
                tMessage.setStatus(0);
            } else {
                /**/
                /*查询题目是否被选*/
                BsTopic bsTopic1 = bsTopicMapper.selectById(bs_topic_id);
                if (isNull(bsTopic1)){
                    logger.info("该题目不存在！");
                    tMessage.setMessage("bs_topic_id或者student_id不能拿为空！");
                    tMessage.setStatus(0);
                } else if ("T".equals(bsTopic1.getIsChoice())){
                    logger.info("该题目已被选！");
                    tMessage.setMessage("该题目已被选！");
                    tMessage.setStatus(0);
                } else {
                    /*删除原有的bs_choice记录并将原有题目isChoice设置为F*/
                    QueryWrapper<BsChoice> qw = new QueryWrapper<>();
                    qw.eq("student_id", student_id);
                    BsChoice bsChoice1 = bsChoiceMapper.selectOne(qw);
                    if (notNull(bsChoice1)){
                        /*params.put("student_id", student_id);
                        int i = bsChoiceMapper.deleteByMap(params);*/
                        Long bsTopicId = bsChoice1.getBsTopicId();
                        bsChoiceMapper.delete(qw);
                        String T = bsTopicService.changeBsChoice(bsTopicId, "F");
                    }
                    /*新增一条bs_choice记录*/
                    BsChoice bsChoice = new BsChoice();
                    bsChoice.setStudentId(student_id);
                    bsChoice.setBsTopicId(bs_topic_id);
                    bsChoice.setIsSure("F");
                    int insert = bsChoiceMapper.insert(bsChoice);
                    if (insert == 0){
                        logger.info("新增一条记录失败");
                        tMessage.setStatus(0);
                        tMessage.setMessage("新增一条记录失败");
                    } else {
                        /*isChoice设置为T*/
                        String changeBsChoice = bsTopicService.changeBsChoice(bs_topic_id, "T");
                        if ("SUCCESS".equals(changeBsChoice)){
                            logger.info("成功选择！");
                            tMessage.setMessage("成功选择！");
                            tMessage.setStatus(200);
                        } else {
                            logger.info("更新失败！");
                            tMessage.setMessage("更新失败！");
                            tMessage.setStatus(0);
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错");
            tMessage.setMessage("系统出错");
            tMessage.setStatus(0);
        }

        return tMessage;
    }


    /*
     *  删除无人选择的毕业设计题目
     *  prarms: bs_topic_id
     */
    public TMessage deleteBsTopic(Long bs_topic_id){
        TMessage tMessage = new TMessage();
        try {
            if (isNull(bs_topic_id)){
                logger.info("参数不足！");
                tMessage.setMessage("参数不足!");
            } else {
                BsTopic bsTopic = bsTopicMapper.selectById(bs_topic_id);
                if (isNull(bsTopic)){
                    logger.info("不存在该毕业设计题目！");
                    tMessage.setMessage("不存在该毕业设计题目!");
                } else if ("F".equals(bsTopic.getIsChoice())){
                    int i = bsTopicMapper.deleteById(bs_topic_id);
                    if (notNull(i)){
                        logger.info("删除成功！");
                        tMessage.setMessage("删除成功!");
                    }
                } else {
                    logger.info("该题目已被选择，不能被删除！");
                    tMessage.setMessage("该题目已被选择，不能被删除!");
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
     *  添加毕业设计题目
     *  prarms: bs_topic_id
     */
    public TMessage addBsTopic(String bs_topic_name, Long bs_teacher_id){
        TMessage tMessage = new TMessage();
        try {
            if (isNull(bs_topic_name) || isNull(bs_teacher_id)){
                logger.info("参数不足！");
                tMessage.setMessage("参数不足!");
            } else {
                BsTopic bsTopic = new BsTopic();
                bsTopic.setBsTopicName(bs_topic_name);
                bsTopic.setBsTeacherId(bs_teacher_id);
                bsTopic.setIsChoice("F");
                int insert = bsTopicMapper.insert(bsTopic);
                if (insert == 1){
                    logger.info("添加毕业设计题目成功！");
                    tMessage.setMessage("添加毕业设计题目成功!");
                } else {
                    logger.info("添加毕业设计题目失败！");
                    tMessage.setMessage("添加毕业设计题目失败!");
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
     * 教师确认毕业设计题目
     *  prarms: bs_topic_id
     */
    public  TMessage sureBsTopic(Long bs_topic_id){
        TMessage tMessage = new TMessage();
        try {
            if (isNull(bs_topic_id)){
                logger.info("参数不足！");
                tMessage.setMessage("参数不足!");
            } else {
                BsTopic bsTopic = bsTopicMapper.selectById(bs_topic_id);
                if (isNull(bsTopic) || isNull(bsTopic.getIsChoice())){
                    logger.info("不存在该毕业设计题目！");
                    tMessage.setMessage("不存在该毕业设计题目!");
                } else if ("F".equals(bsTopic.getIsChoice())){
                    logger.info("未有学生选择过过该毕业设计题目！");
                    tMessage.setMessage("未有学生选择过过该毕业设计题目!");
                } else {
                    QueryWrapper<BsChoice> qw = new QueryWrapper<>();
                    qw.eq("bs_topic_id", bs_topic_id);
                    BsChoice bsChoice = bsChoiceMapper.selectOne(qw);
                    if ("T".equals(bsChoice.getIsSure())){
                        logger.info("不需要再确认过该题目！");
                        tMessage.setMessage("不需要再确认过该题目!");
                    } else {
                        BsChoice updateBsChoice = new BsChoice();
                        updateBsChoice.setIsSure("T");
                        int update = bsChoiceMapper.update(updateBsChoice, qw);
                        logger.info("确认成功！");
                        tMessage.setMessage("确认成功!");
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
     * 教师取消确认过的毕业设计题目
     *  prarms: bs_topic_id,student_id
     */
    public  TMessage noSureBsTopic(Long bs_topic_id, Long student_id){
        TMessage tMessage = new TMessage();
        try {
            if (isNull(bs_topic_id) || isNull(student_id)){
                logger.info("参数不足！");
                tMessage.setMessage("参数不足!");
            } else {
                QueryWrapper<BsChoice> qw = new QueryWrapper<>();
                /*params.put("bs_topic_id", bs_topic_id);
                params.put("student_id", student_id);
                qw.allEq(true, params, true);*/
                qw.eq("bs_topic_id", bs_topic_id);
                qw.eq("student_id",student_id);
                BsChoice bsChoice = bsChoiceMapper.selectOne(qw);
                if (isNull(bsChoice)){
                    logger.info("查无相关记录！");
                    tMessage.setMessage("查无相关记录!");
                } else if ("F".equals(bsChoice.getIsSure())){
                    logger.info("该题目为未确认状态！");
                    tMessage.setMessage("该题目为未确认状态!");
                } else {
                    BsChoice bsChoiceUpdate = new BsChoice();
                    bsChoiceUpdate.setIsSure("F");
                    int update = bsChoiceMapper.update(bsChoiceUpdate, qw);
                    if (notNull(update)){
                        logger.info("取消确认成功！");
                        tMessage.setMessage("取消确认成功!");
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
     * 根据教师id查找相关的毕业设计题目及相关选题状况，进行分页,默认5条为一页
     * prarms: bysj_teacher_id, page_num(页码)
     */
    public QueryTopicByTeaPage getTopicListByTea(Long bs_teacher_id, int page_num){
        QueryTopicByTeaPage queryTopicByTeaPage = new QueryTopicByTeaPage();
        try {
            if (isNull(bs_teacher_id) || isNull(page_num)) {
                queryTopicByTeaPage.setMessage("参数不足");
                logger.info("参数不足");
            } else {
                QueryWrapper<BsTopic> qw = new QueryWrapper<>();
                qw.eq("bs_teacher_id", bs_teacher_id);
                Integer topicCount = bsTopicMapper.selectCount(qw);
                int pageRecord = 5;//默认设置每行5条数据
                int pag_max = (int)Math.ceil((double)topicCount/(double)pageRecord);
                int dataStart;
                int dataEnd;
                if (page_num >= pag_max){
                    page_num = pag_max;
                    dataStart = (page_num - 1)* pageRecord + 1;
                    dataEnd = topicCount;
                } else {
                    dataStart = (page_num - 1)* pageRecord + 1;
                    dataEnd = page_num * pageRecord;
                }
                Page<BsTopic> baseStudentPage = new Page<>(page_num,pageRecord);
                List<TeaQueryBsTopic> teaQueryBsTopics = bsTopicMapper.selectBsTopicByTea(baseStudentPage, bs_teacher_id);
                queryTopicByTeaPage.setPageMax(pag_max);
                queryTopicByTeaPage.setPageNum(page_num);
                queryTopicByTeaPage.setDataStart(dataStart);
                queryTopicByTeaPage.setDataEnd(dataEnd);
                queryTopicByTeaPage.setTopicCount(topicCount);
                queryTopicByTeaPage.setTopicList(teaQueryBsTopics);
                queryTopicByTeaPage.setMessage("查找成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            queryTopicByTeaPage.setMessage("系统出错！");
            logger.info("系统出错！");
        }
        return queryTopicByTeaPage;
    }
}
