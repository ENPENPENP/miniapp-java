package com.elphen.miniapp.common.dto;

import com.elphen.miniapp.domain.entity.TFileData;
import com.elphen.miniapp.domain.entity.TFileInfo;
import lombok.Data;

import java.util.List;

/*
 * @ClassName FileDto
 * @Auth Elphen
 * @Description TODO
 **/
@Data
public class FileDto extends BaseDto {

   private TFileInfo fileInfo;

   private List<TFileData> fileDataList;

   public static FileDto ok(TFileInfo fileInfo, List<TFileData> fileDataList){
      FileDto fileDto = new FileDto();
      fileDto.setFileInfo(fileInfo);
      fileDto.setFileDataList(fileDataList);
      fileDto.setErrMsg("ok");
      fileDto.setStatus(STATUS_OK);
      return fileDto;
}

   public  static FileDto fail(String errMsg){
      FileDto fileDto = new FileDto();
      fileDto.setFileInfo(null);
      fileDto.setFileDataList(null);
      fileDto.setErrMsg(errMsg);
      fileDto.setStatus(STATUS_FAIL);
      return fileDto;
   }

}
