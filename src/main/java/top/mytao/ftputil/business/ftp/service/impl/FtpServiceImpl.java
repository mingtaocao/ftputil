package top.mytao.ftputil.business.ftp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.mytao.ftputil.business.ftp.service.FileTransfer;
import top.mytao.ftputil.business.ftp.service.FtpService;
import top.mytao.ftputil.platform.domain.Result;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

import static top.mytao.ftputil.platform.util.ResultUtil.error;
import static top.mytao.ftputil.platform.util.ResultUtil.success;

/**
 * @Description:
 * @Author: caomingtao
 * @Date Create In 14:10 2018/11/16
 * @Modified By:
 */
@Service
public class FtpServiceImpl implements FtpService {

    private static final Logger logger = LoggerFactory.getLogger(FtpServiceImpl.class);

    @Resource(name = "ftpFileTransfer")
    private FileTransfer ftpFileTransfer;

    @Value("${myproject.ftp.httpurl}")
    private String httpurl;

    /**
     * 上传图片
     *
     * @param file
     * @param folder
     * @return
     */
    @Override
    public String uploadImg(MultipartFile file, String folder) {

        String newFileName = null;
        boolean flag = false;
        try {
            if (file != null && !file.isEmpty()) {

                // image/png(jpg,gif等)
                String contentType = file.getContentType();
                String fileType[] = contentType.split("/");
                newFileName = UUID.randomUUID().toString().replace("-", "");

                if ("image".equals(fileType[0])) {
                    newFileName = newFileName + "." + fileType[1];
                    String targetFileName = folder + newFileName;

                    flag = this.ftpFileTransfer.upload(file.getBytes(), targetFileName);

                    newFileName = httpurl + folder + newFileName;
                }
            }
        } catch (IOException e) {
            logger.error("图片上传失败" + e.toString());
            throw new RuntimeException();
        }

        if (flag) {

            return newFileName;
        } else {
            return "";
        }
    }

    @Override
    public String uploadfile(MultipartFile file, String folder) {
        String newFileName = null;
        boolean flag = false;
        try {
            if (file != null && !file.isEmpty()) {

                String getOriginalFilename = file.getOriginalFilename();

                String[] fileTypes = getOriginalFilename.split("\\.");

                // 取最后个点的后缀,如:1231.34534.57756,后缀为:57756
                String fileType = fileTypes[fileTypes.length - 1];
                newFileName = UUID.randomUUID().toString().replace("-", "");
                newFileName = newFileName + "." + fileType;
                String targetFileName = folder + newFileName;

                flag = this.ftpFileTransfer.upload(file.getBytes(), targetFileName);

                newFileName = httpurl + folder + newFileName;

            }
        } catch (IOException e) {
            logger.error("文件上传失败" + e.toString());
            throw new RuntimeException();
        }

        if (flag) {

            return newFileName;
        } else {
            return "";
        }
    }
}
