package com.sise.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sise.bean.FileV2;
import com.sise.bean.QueryStuUpLoadPage;
import com.sise.bean.TMessage;
import com.sise.common.JsonUtil;
import com.sise.mapper.FileMapper;
import com.sise.service.IFileService;
import com.sun.tools.internal.ws.processor.model.Response;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static com.sise.common.Assert.*;

/**
 * @Author: DMY
 * @Date: 2019/3/4 3:17
 * @Description:
 */

@RestController
@RequestMapping("file")
public class FileController {


    Logger logger = Logger.getLogger(FileController.class.getName());
    
    @Resource 
    private FileMapper fileMapper;

    @Resource
    private IFileService fileService;
    
    
    
    /**
     * 单个文件上传
     * @param request
     * @return
     */
    @RequestMapping(value="upload.do",produces="text/html;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile  partFile) {
        try {
            //上传后的地址
            /*String path = request.getServletContext().getRealPath("/upload");*/
            String path = "D:/apache-tomcat-7.0.86-windows-x64/apache-tomcat-7.0.86/webapps/bysj_upload_address";
            //获取文件名
            String filename = partFile.getOriginalFilename();
           /*
            //获取上传图片的拓展名
            String extensionName = filename.substring(filename.lastIndexOf('.')+1);
            //接收表单提交的文件名
            String name = request.getParameter("name");
            //设置上传后的文件名称
            String uploadName = name + "." + extensionName;
            //用户没有填文件名，则默认存储文件名
            if (isNull(name)){
                uploadName = filename;
            }
            */

            File dir = new File(path, filename);
            if (!dir.exists()){
                dir.mkdirs();
            }
            partFile.transferTo(dir);
            //数据库保存记录,存在就更新时间，不存在就新建
            com.sise.entity.File saveFile = new com.sise.entity.File();
            saveFile.setFileTime(new Date());
            Long file_user_id = Long.parseLong(request.getParameter("uploadUserId"));
            String uploadIdentity = request.getParameter("uploadIdentity");
            QueryWrapper<com.sise.entity.File> qw = new QueryWrapper<>();
            qw.eq("file_name", filename);
            Integer integer = fileMapper.selectCount(qw);
            if (integer>0){
                int update = fileMapper.update(saveFile, qw);
            } else {
                saveFile.setFileUserId(file_user_id);
                saveFile.setFileName(filename);
                saveFile.setFileUserIdentity(uploadIdentity);
                saveFile.setFileDownNum(0L);
                int insert = fileMapper.insert(saveFile);
            }
            return "文件上传成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "文件上传失败！";
        }
    }





    /**
     * 单个文件下载
     * @param request
     * @return
     */
    @RequestMapping(value="down.do",produces="text/html;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public void down(HttpServletRequest request, HttpServletResponse response, String file_id, String file_name) {
        try {
            if (isNull(file_id) || isNull(file_name)){
                logger.info("参数不足");
            } else {
                //文件路径名
                String path = "D:/apache-tomcat-7.0.86-windows-x64/apache-tomcat-7.0.86/webapps/bysj_upload_address/"+file_name;
                //得到要下载的文件
                File file = new File(path);
                if (!file.exists()){
                    response.setContentType("text/html; charset=UTF-8");//注意text/html，和application/html
                    response.getWriter().print("<html><body><script type='text/javascript'>alert('您要下载的资源已被删除！！！');</script></body></html>");
                    response.getWriter().close();
                    logger.info("您要下载的资源已被删除！！");
                } else {
                    /* 1.下载*/
                    //转码，免得文件名中文乱码
                    file_name = URLEncoder.encode(file_name, "UTF-8");
                    //设置文件下载头
                    response.addHeader("Content-Disposition", "attachment;filename=" + file_name);
                    //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                    // 读取要下载的文件，保存到文件输入流
                    FileInputStream in = new FileInputStream(path);
                    // 创建输出流
                    OutputStream out = response.getOutputStream();
                    // 创建缓冲区
                    byte buffer[] = new byte[1024]; // 缓冲区的大小设置
                    int len = 0;
                    //循环将输入流中的内容读取到缓冲区当中
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    //关闭文件输入流
                    in.close();
                    // 关闭输出流
                    out.close();


                    /*  2.数据库下载次数增加一 */
                    QueryWrapper<com.sise.entity.File> qw = new QueryWrapper<>();
                    qw.eq("file_id", Long.parseLong(file_id));
                    com.sise.entity.File qwFile = fileMapper.selectById(Long.parseLong(file_id));
                    if (notNull(qwFile)) {
                        com.sise.entity.File updateFile = new com.sise.entity.File();
                        updateFile.setFileDownNum(qwFile.getFileDownNum() + 1);
                        int update = fileMapper.update(updateFile, qw);
                    }

                    logger.info("下载成功！");



                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("系统出错！");
        }

    }



    /*
     * 查找教师下的毕业学生上传文件的情况(分页)
     */
    @RequestMapping(value = "getStuUpLoads.do", produces="application/json;charset=utf-8", method = RequestMethod.GET)
    public void getStuUpLoads(HttpServletRequest request, HttpServletResponse response, String teacher_id, String page_num) {
        if (isNull(teacher_id)) {
            logger.info("参数不足");
            JsonUtil.outPrint(response, "参数不足");
        } else {
            Long teacher_id_L = Long.parseLong(teacher_id);
            int page_num_I;
            if (isNull(page_num) || !isNumber(page_num)) {
                page_num_I = 1;
            } else {
                page_num_I = Integer.parseInt(page_num);
                if (page_num_I < 1) {
                    page_num_I = 1;
                }
            }
            QueryStuUpLoadPage stuUpLoads = fileService.getStuUpLoads(teacher_id_L, page_num_I);
            JsonUtil.outPrint(response, stuUpLoads);
        }
    }





    /*查找教师上传的资源*/
    @RequestMapping(value = "getTeaUpLoad.do", produces="application/json;charset=utf-8", method = RequestMethod.GET)
    public void getTeaUpLoad(HttpServletRequest request, HttpServletResponse response, String teacher_id) {
        if (isNull(teacher_id)){
            logger.info("参数不足！");
            JsonUtil.outPrint(response, "参数不足！");
        } else {
            List<FileV2> teaUpLoad = fileService.getTeaUpLoad(Long.parseLong(teacher_id));
            JsonUtil.outPrint(response, teaUpLoad);
        }

    }















    }
