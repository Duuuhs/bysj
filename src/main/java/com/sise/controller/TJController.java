package com.sise.controller;


import com.sise.bean.TJbs;
import com.sise.bean.TJclass;
import com.sise.bean.TJdepart;
import com.sise.bean.TJsex;
import com.sise.common.JsonUtil;
import com.sise.mapper.BaseClassMapper;
import com.sise.service.IBaseClassService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.logging.Logger;

import static com.sise.common.Assert.isNull;
import static com.sise.common.Assert.notNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DMY
 * @since 2019-03-03
 */
@RestController
@RequestMapping("tj")
public class TJController  {

    Logger logger = Logger.getLogger(TJController.class.getName());

    @Resource
    private IBaseClassService baseClassService;

    @Resource
    private BaseClassMapper baseClassMapper;


    /*
     *统计班级人数(系)
     */
    @RequestMapping(value = "tjclass.do", method = RequestMethod.GET)
    public void tjclass(HttpServletRequest request, HttpServletResponse response, String base_department_id) {
        if (isNull(base_department_id)){
            logger.info("参数不足");
            JsonUtil.outPrint(response, "参数不足！");
        } else {
            TJclass tJclass = baseClassService.selectClasses(Long.parseLong(base_department_id));
            JsonUtil.outPrint(response, tJclass);
        }

    }


    /*
     *统计性别(SM/DM)
     */
    @RequestMapping(value = "getTJStuSex.do", method = RequestMethod.GET)
    public void getTJStuSex(HttpServletRequest request, HttpServletResponse response, String department_id) {
        Long department_id_L = null;
        if (notNull(department_id)){
            department_id_L = Long.parseLong(department_id);
        }
        List<TJsex> tJsexes = baseClassMapper.selectStuSex(department_id_L);
        JsonUtil.outPrint(response, tJsexes);
    }


    /*
     *统计性别(SM/DM)
     */
    @RequestMapping(value = "getDepart.do", method = RequestMethod.GET)
    public void getDepart(HttpServletRequest request, HttpServletResponse response) {
        List<TJdepart> tJdeparts = baseClassMapper.selectDepart();
        JsonUtil.outPrint(response, tJdeparts);
    }



    /*
     *统计毕设选题情况(SM/DM)
     */
    @RequestMapping(value = "getBsStatus.do", method = RequestMethod.GET)
    public void getBsStatus(HttpServletRequest request, HttpServletResponse response, String department_id) {
        Long deparement_id_L = null;
        if (notNull(department_id)){
            deparement_id_L = Long.parseLong(department_id);
        }
        //没有毕设老师
        int i = baseClassMapper.selectNotTea(deparement_id_L);
        //有毕设老师未选题
        int i1 = baseClassMapper.selectHasTeaNoChoice(deparement_id_L);
        //有毕设老师已选题
        int i2 = baseClassMapper.selectHasTeaHasChoice(deparement_id_L);
        TJbs tJbs = new TJbs();
        tJbs.setHasTeaHasChoice(i2);
        tJbs.setHasTeaNoChoice(i1);
        tJbs.setNotTea(i);
        JsonUtil.outPrint(response, tJbs);
    }

}
