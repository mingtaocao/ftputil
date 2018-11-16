package top.mytao.ftputil.business.ftp.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Author: caomingtao
 * @Date Create In 14:09 2018/11/16
 * @Modified By:
 */
public interface FtpService {

    String uploadImg(MultipartFile file, String folder);

    String uploadfile(MultipartFile file, String folder);
}
