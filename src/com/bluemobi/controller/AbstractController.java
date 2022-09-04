package com.bluemobi.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.appcore.util.FileUtil;
import com.appcore.util.SftpUtil;
import com.bluemobi.conf.ImageServerConfig;
import com.bluemobi.constant.BaseConstant;

/**
 * 控制器抽象类  
 * 手机接口控制器 和 web控制器需继承此类
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-10-26 下午5:12:48 
 *
 */
public abstract class AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);
    
    /**
     * 获取userId
     * @author haojian
     * @date 2015-10-15 上午10:09:30 
     * @return
     * @return int
     */
    public abstract int getUserid();
    
    /**
     * 获取username
     * @author haojian
     * @date 2015-12-2 上午9:47:19 
     * @return
     * @return String
     */
    public abstract String getUsername();
    
    /**
     * 获取当前访问者ip地址
     * getAccessIp
     * 
     * @author kevin
     * @return 
     * @since JDK 7
     */
    public String getAccessIp(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getRemoteAddr();
    }
    
    /**
     * 图片上传，默认会上传到图片服务器
     * 
     * @auther zhangzheng
     * @date 2015-9-30
     * @param files
     *            要上传的图片文件
     * @param imageUrl
     *            图片上传子路径路径
     * @return resultMap,imageUrl:图片路径;flag:上传是否成功
     */
    public Map<String, Object> uploadImage(MultipartFile[] files, String imageUrl) {
        return this.uploadImage(files, imageUrl, true);
    }
    
    /**
     * 图片上传
     * 
     * @auther zhangzheng
     * @date 2015-9-30
     * @param files
     *            要上传的图片文件
     * @param imageUrl
     *            图片上传子路径路径
     * @param isUploadToImageServer
     *            是否需要上传到图片服务器
     * @return resultMap,imageUrl:图片路径;flag:上传是否成功
     */
    public Map<String, Object> uploadImage(MultipartFile[] files, String imageUrl, boolean isUploadToImageServer) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        for (int i = 0; i < files.length; i++) {
            LOGGER.info("fileName = " + files[i].getOriginalFilename());
            if (!files[i].isEmpty()) {
                int pre = (int) System.currentTimeMillis();
                try {
                    // 检验文件夹是否存在，不存在 就创建
                    FileUtil.makeDir(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl);
                    // 文件后缀名
                    String suffix = files[i].getOriginalFilename().substring(files[i].getOriginalFilename().lastIndexOf(".") + 1, files[i].getOriginalFilename().length());
                    // 拿到输出流，同时重命名上传的文件
//                    String imageName = new Date().getTime() + files[i].getOriginalFilename();
                    String imageName = UUID.randomUUID().toString() + "." + suffix;
                    FileOutputStream os = new FileOutputStream(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName);

                    // 拿到上传文件的输入流
                    InputStream in = files[i].getInputStream();
                    // 以写字节的方式写文件
                    int len = 0;
                    byte[] bb = new byte[4096];
                    while ((len = in.read(bb)) != -1) {
                        os.write(bb, 0, len);
                    }
                    os.flush();
                    os.close();
                    in.close();

                    // 上传图片到图片服务器
                    if (isUploadToImageServer) {
                        
                        String localFilePathName = BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName;
                        
                        String HOST = ImageServerConfig.HOST;
                        short PORT = ImageServerConfig.PORT;
                        String USER_NAME = ImageServerConfig.USER_NAME;
                        String PASSWORD = ImageServerConfig.PASSWORD;
                        String BASE_IMAGE_PATH = ImageServerConfig.BASE_IMAGE_PATH;
                        SftpUtil.upload(HOST, PORT, USER_NAME, PASSWORD, localFilePathName, BASE_IMAGE_PATH + imageUrl, imageName);
                    }
                    
//                    File file = new File(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName);
//                    String fileMD5 = FileUtil.md5(file);
                    resultMap.put("hashcode", imageName.substring(0, imageName.indexOf(".")));
//                    imageName = fileMD5 + "." + suffix;
//                    File renameFile = new File(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName);
//                    file.renameTo(renameFile);
                    resultMap.put("imageUrl", imageUrl + imageName);
                    
                    int finaltime = (int) System.currentTimeMillis();
                    LOGGER.info("消耗时间：", finaltime - pre);
                    resultMap.put("flag", true);
                } catch (Exception e) {
                    LOGGER.error("上传出错,错误信息：【{}】", e);
                    resultMap.put("flag", false);
                }
            }
        }
        return resultMap;
    }
    
    
    
}
