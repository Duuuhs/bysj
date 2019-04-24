package com.sise.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sise.bean.FileV2;
import com.sise.bean.QueryStuUpLoad;
import com.sise.bean.QueryStuUpLoadPage;
import com.sise.bean.QueryStuUpLoadV2;
import com.sise.common.DateUtils;
import com.sise.entity.File;
import com.sise.mapper.FileMapper;
import com.sise.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.sise.common.Assert.isNull;
import static com.sise.common.Assert.notNull;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DMY
 * @since 2019-03-04
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

    @Resource
    private FileMapper fileMapper;

    Logger logger = Logger.getLogger(FileServiceImpl.class.getName());


    /*查找教师下的毕业学生上传文件的情况(分页)*/
    public QueryStuUpLoadPage getStuUpLoads(Long teacher_id, int page_num){
        QueryStuUpLoadPage queryStuUpLoadPage = new QueryStuUpLoadPage();
        try {
            if (isNull(teacher_id) || isNull(page_num)){
                logger.info("参数不足！");
                queryStuUpLoadPage.setMessage("参数不足！");
            } else {
                int fileCount = fileMapper.selectStuUpLoadCount(teacher_id);
                int pageRecord = 5;//默认设置每行5条数据
                int pag_max = (int)Math.ceil((double)fileCount/(double)pageRecord);
                int dataStart;
                int dataEnd;
                if (page_num >= pag_max){
                    page_num = pag_max;
                    dataStart = (page_num - 1)* pageRecord + 1;
                    dataEnd = fileCount;
                } else {
                    dataStart = (page_num - 1)* pageRecord + 1;
                    dataEnd = page_num * pageRecord;
                }
                Page<QueryStuUpLoad> page = new Page<>(page_num, pageRecord);
                List<QueryStuUpLoad> queryStuUpLoads = fileMapper.selectStuUpLoadPage(page, teacher_id);
                //转换日期格式
                List<QueryStuUpLoadV2> queryStuUpLoadV2s = new ArrayList<>();
                QueryStuUpLoadV2 queryStuUpLoadV2 = null;
                for (QueryStuUpLoad q: queryStuUpLoads) {
                    queryStuUpLoadV2 = new QueryStuUpLoadV2();
                    if (notNull(q.getStudentId())){
                        queryStuUpLoadV2.setStudentId(q.getStudentId());
                    }
                    if (notNull(q.getStudentName())){
                        queryStuUpLoadV2.setStudentName(q.getStudentName());
                    }
                    if (notNull(q.getFileId())){
                        queryStuUpLoadV2.setFileId(q.getFileId());
                    }
                    if (notNull(q.getFileName())){
                        queryStuUpLoadV2.setFileName(q.getFileName());
                    }
                    if (notNull(q.getFileTime())){
                        queryStuUpLoadV2.setFileTime(DateUtils.getStrYMDHMByDate(q.getFileTime()));
                    }
                    queryStuUpLoadV2s.add(queryStuUpLoadV2);
                }
                //封装数据
                queryStuUpLoadPage.setPageMax(pag_max);
                queryStuUpLoadPage.setPageNum(page_num);
                queryStuUpLoadPage.setDataStart(dataStart);
                queryStuUpLoadPage.setDataEnd(dataEnd);
                queryStuUpLoadPage.setFileCount(fileCount);
                queryStuUpLoadPage.setFileContent(queryStuUpLoadV2s);
                queryStuUpLoadPage.setMessage("查找成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            queryStuUpLoadPage.setMessage("系统出错！");
            logger.info("系统出错！");
        }
        return queryStuUpLoadPage;
    }



    /*查找教师上传的资源*/
    public List<FileV2> getTeaUpLoad(Long teacher_id){
        List<FileV2> teaUpList = null;

        try {
            if (isNull(teacher_id)){
                logger.info("参数不足！");
            } else {
                QueryWrapper<File> qw = new QueryWrapper<>();
                qw.eq("file_user_id", teacher_id);
                List<File> files = fileMapper.selectList(qw);
                if (notNull(files)){
                    teaUpList = new ArrayList<>();
                    FileV2 fileV2;
                    for (File f: files) {
                        fileV2 = new FileV2();
                        if (notNull(f.getFileId())){
                            fileV2.setFileId(f.getFileId());
                        }
                        if (notNull(f.getFileName())){
                            fileV2.setFileName(f.getFileName());
                        }
                        if (notNull(f.getFileUserId())){
                            fileV2.setFileUserId(f.getFileUserId());
                        }
                        if (notNull(f.getFileDownNum())){
                            fileV2.setFileDownNum(f.getFileDownNum());
                        }
                        if (notNull(f.getFileTime())){
                            fileV2.setFileTime(DateUtils.getStrYMDHMByDate(f.getFileTime()));
                        }
                        teaUpList.add(fileV2);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错！");
        }
        return teaUpList;
    }




}
