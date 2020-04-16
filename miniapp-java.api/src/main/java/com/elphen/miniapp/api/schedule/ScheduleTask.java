package com.elphen.miniapp.api.schedule;

import com.elphen.miniapp.api.service.TEventService;
import com.elphen.miniapp.api.service.TFileInfoService;
import com.elphen.miniapp.domain.entity.TEvent;
import com.elphen.miniapp.domain.entity.TFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

/*
 * @ClassName ScheduleTask
 * @Auth Elphen
 * @Description TODO
 **/
@Component
@EnableScheduling
public class ScheduleTask {

    private final static String EXCEL_FILE_SUFFIX = ".xlsx";

    private Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
    private TFileInfoService tFileInfoService;

    @Autowired
    private TEventService tEventService;

    //    @Scheduled(cron = "0 */10 * * * ?")
    public void checkFileGenerated() {
        try {
            File jarFile = new ApplicationHome(getClass()).getSource();
            String jarPath = jarFile.getParentFile().getPath();
            String staticDirPath = jarPath + "\\static";
            File staticDir = new File(staticDirPath);
            // 获取已生成的文件信息集合
            List<TFileInfo> allGeneratedFile = tFileInfoService.getAllGeneratedFile();
            if (staticDir.exists() && staticDir.isDirectory()) {
//                获取static目录下生成的xlsx文件数组
                File[] filesMatrix = staticDir.listFiles();
                if (null != filesMatrix) {
                    List<File> fileList = new ArrayList<>(filesMatrix.length);
                    List<File> deleteList = new ArrayList<>();
//                    将文件数据转换成集合
                    Collections.addAll(fileList, filesMatrix);
                    for (File file : fileList) {
//                        deleteMap.put(file,true);
                        for (TFileInfo fileInfo : allGeneratedFile) {
                            if (file.getName().equals(fileInfo.getFilePath())) {
//                                deleteMap.put(file, false);
                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
    }

    /**
     * 每隔一分钟检查事件是否截止，如果截止则修改表格的截止状态
     */
    @Scheduled(cron = "0 */1 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void checkEventStopped() {
        List<TEvent> allEvent = tEventService.getAllEvent();
        Date now = new Date();
        for (TEvent event : allEvent) {
//            检查是否设置截止时间和是否到达截止时间
            if (null != event.getStopTime() && event.getStopTime().before(now) && !event.getIsStoped()) {
                event.setIsStoped(true);
                TFileInfo fileInfo = event.getFileInfo();
                fileInfo.setIsStoped(true);
//                更新数据库
                try {
                    tEventService.updateByPrimaryKey(event);
                    tFileInfoService.updateByPrimaryKey(fileInfo);
                    logger.info(String.format("Event[%s] is stopped.", event.getEventId()));
                    logger.info(String.format("File[%s] is stopped.", fileInfo.getFileId()));
                } catch (Exception e) {
                    logger.debug(e.getMessage());
                }
            }
        }
    }
}
