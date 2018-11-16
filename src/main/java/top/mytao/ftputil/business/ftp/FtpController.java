package top.mytao.ftputil.business.ftp;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.mytao.ftputil.business.ftp.service.FtpService;
import top.mytao.ftputil.platform.domain.BaseController;
import top.mytao.ftputil.platform.domain.Result;

import javax.annotation.Resource;

/**
 * @Description: 文件+图片上传
 * @Author: caomingtao
 * @Date Create In 14:09 2018/11/16
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/business/upload")
public class FtpController extends BaseController {

    /**
     * 图片ftp服务
     */
    @Resource
    private FtpService ftpService;

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/uploadFile")
    public Result uploadStandardManageFile(MultipartFile file) throws Exception {

        String path = "files/";
        String FileName = ftpService.uploadfile(file, path);

        if (FileName != null && !FileName.isEmpty()) {
            return this.success(FileName);
        } else {
            return this.message(1, "文件上传失败!");
        }

    }

    /**
     * 上传图片
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/uploadImg")
    public Result uploadStandardManageImg(MultipartFile file) throws Exception {

        String path = "imgs/";
        String FileName = ftpService.uploadImg(file, path);

        if (FileName != null && !FileName.isEmpty()) {
            return this.success(FileName);
        } else {
            return this.message(1, "图片上传失败!");
        }
    }
}
