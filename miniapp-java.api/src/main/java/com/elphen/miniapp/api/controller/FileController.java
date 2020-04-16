package com.elphen.miniapp.api.controller;

import com.alibaba.fastjson.JSON;
import com.elphen.miniapp.api.service.*;
import com.elphen.miniapp.common.dto.FileDto;
import com.elphen.miniapp.common.utils.ExcelUtils;
import com.elphen.miniapp.domain.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 * @ClassName FileController
 * @Auth Elphen
 * @Description TODO
 **/
@RestController
@RequestMapping("${api.miniapp.prefix}/file")
public class FileController {

    private final static String GET_ALL = "all";
    private final static String GET_INFO = "info";
    private final static String GET_DATA = "data";
    private final static String EXCEL_FILE_SUFFIX = ".xlsx";

    @Autowired
    private TFileInfoService infoService;

    @Autowired
    private TFileDataService dataService;

    @Autowired
    private TEventService eventService;

    @Autowired
    private TTokenService tokenService;

    @Autowired
    private TUserEventService tUserEventService;

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    /**
     * 获取指定文件的文件信息或数据
     *
     * @param fileId 目标文件id
     * @param type   获取的信息的类型 ，'all' 为获取文件信息和数据， 'info' 只获取文件的信息，'data' 只获取文件的数据
     * @return
     */
    @GetMapping("get/{fileId}")
    public FileDto getWholeFile(@PathVariable("fileId") Integer fileId, String type) {
        FileDto fileDto = null;
        try {
            if (null == fileId) {
                fileDto = FileDto.fail("fileId is null");
            } else {
                if (type.equals(GET_ALL)) {
                    TFileInfo fileInfo = infoService.selectByFileId(fileId);
                    List<TFileData> fileDataList = dataService.selectAllByFileId(fileId);
                    fileDto = FileDto.ok(fileInfo, fileDataList);
                } else if (type.equals(GET_INFO)) {
                    TFileInfo fileInfo = infoService.selectByFileId(fileId);
                    fileDto = FileDto.ok(fileInfo, null);
                } else if (type.equals(GET_DATA)) {
                    List<TFileData> fileDataList = dataService.selectAllByFileId(fileId);
                    fileDto = FileDto.ok(null, fileDataList);
                } else {
                    fileDto = FileDto.fail("unknown type");
                }
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return fileDto;
    }

    /**
     * 获取指定文件id的列名数据
     *
     * @param fileId 目标文件id
     * @return
     */
    @GetMapping("getColName/{fileId}")
    public FileDto getColName(@PathVariable("fileId") Integer fileId) {
        FileDto dto = null;
        try {
            if (null != fileId) {
                TFileData columnName = dataService.getColumnName(fileId);
                List<TFileData> fileDataList = new ArrayList<>();
                fileDataList.add(columnName);
                dto = FileDto.ok(null, fileDataList);
            } else {
                dto = FileDto.fail("fileId is null");
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return dto;
    }

    /**
     * 向指定的文件添加数据行
     *
     * @param fileId  目标文件id
     * @param userId  生成该数据行的用户id
     * @param rowType 数据行的类型
     * @param rowData json格式的行数据
     * @return
     */
    @PostMapping("addData")
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public FileDto addData(Integer fileId, Integer userId, Integer rowType, String rowData) {
        FileDto dto = null;
        try {
            if (null == fileId || null == userId || null == rowType || StringUtils.isBlank(rowData)) {
                dto = FileDto.fail("some param is null");
            } else {
//                查询出对应的事件
                TEvent event = eventService.getEventByFileId(fileId);
//                查询出对应的表格信息
                TFileInfo fileInfo = infoService.selectByFileId(fileId);
//                查询出表格对应的文件数据
                List<TFileData> fileDataList = dataService.selectAllByFileId(fileId);
//                判断查询出来的数据行数和文件信息的行数是否一致
                if (fileInfo.getRowCount() == fileDataList.size()) {
                    Date now = new Date();
//                    生成新文件数据行，设置行号
                    TFileData fileData = TFileData.dataRow(fileId, userId, rowData, fileInfo.getRowCount() - 1);
//                    修改文件信息的更新时间
                    fileInfo.setUpdateTime(now);
//                    修改文件信息的行数
                    fileInfo.setRowCount(fileInfo.getRowCount() + 1);
//                    修改事件的更新时间
                    event.setUpdateTime(now);
//                    开始插入数据
                    if (dataService.insert(fileData) != 0 && infoService.updateByPrimaryKey(fileInfo) != 0 && eventService.updateByPrimaryKey(event) != 0) {
//                        将用户和事件关联起来
                        TUserEvent userEvent = tUserEventService.getByUserIdAndEventId(userId, event.getEventId());
                        if (null == userEvent) {
                            userEvent = new TUserEvent();
                            userEvent.setId(null);
                            userEvent.setEventId(event.getEventId());
                            userEvent.setUserId(userId);
                            userEvent.setIsEdited(true);
                            tUserEventService.insert(userEvent);
                        } else {
                            userEvent.setIsEdited(true);
                            tUserEventService.updateByPrimaryKey(userEvent);
                        }
                        dto = FileDto.ok(fileInfo, null);
                    } else {
                        throw new Exception("插入失败");
                    }
                }
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());

        }
        return dto;
    }

    /**
     * 生成对应文件id的硬盘物理文件
     *
     * @param fileId  要生成的文件id
     * @param request 请求体
     * @return
     */
    @GetMapping("generate/{fileId}")
    @Transactional(rollbackFor = {Exception.class})
    public FileDto generateFile(@PathVariable("fileId") Integer fileId, HttpServletRequest request) {
        FileDto dto = null;
        File file = null;
        try {
//            查询出对应的文件信息
            TFileInfo fileInfo = infoService.selectByFileId(fileId);
//            获取对应文件的文件信息
            List<TFileData> fileDataList = dataService.selectAllByFileId(fileId);
//            获取static路径
            String path = request.getServletContext().getRealPath(".");
//            生成uuid文件名
            String UUIDfileName = UUID.randomUUID().toString() + EXCEL_FILE_SUFFIX;
//            拼接路径
            String filePath = path + "\\" + UUIDfileName;
//            生成xlsx文件
            ExcelUtils.exportDataToExcel(fileDataList, filePath);
            file = new File(filePath);
//            检查是否生成文件
            if (file.exists() && file.isFile()) {
                //                更新文件信息
                fileInfo.setUpdateTime(new Date());
                fileInfo.setIsGenerated(true);
                fileInfo.setFilePath(UUIDfileName);
//                更新数据库中的文件信息
                if (infoService.updateByPrimaryKey(fileInfo) != 0) {
//                    生成并更新成功
                    dto = FileDto.ok(fileInfo, null);
                } else {
                    dto = FileDto.fail("file create fail");
                }
            }
        } catch (Exception e) {
//            如果失败则删除文件
            file.delete();
            e.printStackTrace();
        }
        return dto;
    }

    /**
     * 下载对应文件的硬盘物理文件
     *
     * @param fileId   目标文件id
     * @param request  请求体
     * @param response 响应体
     * @return
     */
    @GetMapping("download/{fileId}")
    public FileDto downloadFile(@PathVariable("fileId") Integer fileId, String token, HttpServletRequest request, HttpServletResponse response) {
        BufferedInputStream bis = null;
        OutputStream os = null;
        File file = null;
        FileDto dto = null;
        try {
            if (null != fileId) {
//                获取文件信息
                TFileInfo fileInfo = infoService.selectByFileId(fileId);
//                获取token信息
                TToken tToken = tokenService.getByToken(token);
//                检查是否存在相应的文件信息和token信息
                if (null != fileInfo && null != tToken) {
//                    检查token是否过期
                    if (tokenService.checkTokenExpire(tToken)) {
                        String filePath = fileInfo.getFilePath();
                        String realFileName = fileInfo.getFileName();
//                        获取文件路径
                        file = new File(request.getServletContext().getRealPath(".") + "\\" + filePath);
//                        检查文件是否存在
                        if (file.exists()) {
                            byte[] buff = new byte[1024];
                            os = response.getOutputStream();
                            bis = new BufferedInputStream(new FileInputStream(file));
                            response.setHeader("Content-Type", "application/octet-stream");
//                            设置文件类型
                            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
                            response.setHeader("Pragma", "no-cache");
                            response.setHeader("Cache-Control", "no-cache");
                            response.setDateHeader("Expires", 0);
//                            响应头设置内容长度
                            response.setHeader("Content-Length", String.valueOf(bis.available()));
//                    响应头这里设置文件的真实名称
                            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(realFileName, "UTF-8") + EXCEL_FILE_SUFFIX);
                            int i = bis.read(buff);
                            while (i != -1) {
                                os.write(buff, 0, buff.length);
                                os.flush();
                                i = bis.read(buff);
                            }
                        }
                    } else {
                        dto = FileDto.fail("expired token");
                    }
                } else if (null == token) {
                    dto = FileDto.fail("unknown token");
                } else if (null == fileInfo) {
                    dto = FileDto.fail("fileId not exist");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info("download file success");
        return dto;
    }

    @PostMapping("updateData")
    @Transactional(rollbackFor = Exception.class)
    public FileDto updateData(@RequestParam("fileId") Integer fileId ,@RequestParam("fileDataList") String fileDataListJson) {
        FileDto dto = null;
        try {
            if (StringUtils.isNotEmpty(fileDataListJson)) {
                TFileInfo tFileInfo = infoService.selectByFileId(fileId);
                List<TFileData> tFileDataList = JSON.parseArray(fileDataListJson, TFileData.class);
                dataService.updateRowData(tFileDataList);
                tFileInfo.setUpdateTime(new Date());
                infoService.updateByPrimaryKey(tFileInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
