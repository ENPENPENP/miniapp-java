package com.elphen.miniapp.api.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * @ClassName FileTest
 * @Auth Elphen
 * @Description TODO
 **/
@SpringBootTest
public class FileTest {

    private static Logger logger = LoggerFactory.getLogger(FileTest.class);

    public static void main(String[] args)  {
        logger.info("testing................");
        try {
//            File path = new File(ResourceUtils.getURL("classpath:").getPath());
//            logger.info(path.getAbsolutePath());
//            if(!path.exists()) {
//                path = new File("");
//                File upload = new File(path.getAbsolutePath(),"static/");
//                if(!upload.exists()) {
////                upload.mkdirs();
//                    logger.info(upload.getPath());
//                    logger.info(upload.getAbsolutePath());
//                    logger.info(upload.getCanonicalPath());
//                }
//            }
            ApplicationHome home = new ApplicationHome(FileTest.class);
            File jarFile = home.getSource();
            logger.info(jarFile.getParentFile().getParentFile().getPath());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
