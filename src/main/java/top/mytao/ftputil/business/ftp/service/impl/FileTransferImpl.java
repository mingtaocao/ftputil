package top.mytao.ftputil.business.ftp.service.impl;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.mytao.ftputil.business.ftp.service.FileTransfer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.SocketException;

/**
 * @Description:
 * @Author: caomingtao
 * @Date Create In 14:14 2018/11/16
 * @Modified By:
 */

@Service(value = "ftpFileTransfer")
public class FileTransferImpl implements FileTransfer {

    private static final Logger logger = LoggerFactory.getLogger(FileTransferImpl.class);

    @Value("${myproject.ftp.ip}")
    private String serverIp;

    @Value("${myproject.ftp.port}")
    private int serverPort;

    @Value("${myproject.ftp.username}")
    private String password;

    @Value("${myproject.ftp.password}")
    private String userName;

    @Value("${myproject.ftp.path}")
    private String serverPath;

    boolean binaryMode = true;
    boolean passiveMode = true;

    public FileTransferImpl() {

    }

    @Override
    public byte[] download(String sourceFileName) {
        String s;
        if (serverPath != null) {
            s = serverPath + sourceFileName;
        } else {
            s = sourceFileName;
        }

        FTPClient ftpclient;

        ftpclient = new FTPClient();

        byte abyte0[];
        try {
            ftpclient.connect(serverIp, serverPort);
            int i = ftpclient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(i)) {
                ftpclient.disconnect();
                logger.error("ftpserver_refuse_connection");
                return null;
            }
            if (!ftpclient.login(userName, password)) {
                logger.error("ftpserver_refuse_login");
                return null;
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Remote system is ");
            }
            if (binaryMode) {
                ftpclient.setFileType(2);
            }
            if (passiveMode) {
                ftpclient.enterLocalPassiveMode();
            }
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            ftpclient.retrieveFile(s, bytearrayoutputstream);
            abyte0 = bytearrayoutputstream.toByteArray();
        } catch (IOException ioexception) {
            logger.error("fail_to_connect_ftpserver:", ioexception);
            return null;
        }

        //退出ftp并关闭连接
        disconnect(ftpclient);

        return abyte0;
    }


    /**
     * 直接将byte写到服务器指定目录下文件中
     */
    @Override
    public boolean upload(byte[] bt, String targetFileName) {

        String s;
        boolean flag = false;
        FTPClient ftpclient = new FTPClient();
        try {

            //连接ftp服务器
            ftpclient.connect(serverIp, serverPort);

            //是否成功登录服务器
            int i = ftpclient.getReplyCode();

            if (!FTPReply.isPositiveCompletion(i)) {
                ftpclient.disconnect();
                logger.error("ftpserver_refuse_connection");
                return false;
            }

            //登录ftp服务器
            if (!ftpclient.login(userName, password)) {
                logger.error("ftpserver_refuse_login");
                return false;
            }

            if (logger.isDebugEnabled()) {
                logger.debug("Remote system is " + ftpclient.getSystemName());
            }
            if (binaryMode) {
                ftpclient.setFileType(2);
            }
            if (passiveMode) {
                ftpclient.enterLocalPassiveMode();
            }

            ftpclient.setControlEncoding("UTF-8");
            s = serverPath + targetFileName;
            String s1 = new String(s.getBytes("UTF-8"), "iso-8859-1");
            flag = ftpclient.storeFile(s1, new ByteArrayInputStream(bt));

        } catch (SocketException e) {
            logger.error("FtpTransferUploadErr", e);
        } catch (IOException e) {
            logger.error("FtpTransferUploadIOErr", e);
        } finally {

            //退出ftp并关闭连接
            disconnect(ftpclient);
        }
        return flag;
    }

    /**
     * 将byte写到服务器指定目录下
     */
    @Override
    public boolean upload(byte[] bt,
                          String targetFileName,
                          String host,
                          int port,
                          String userName,
                          String pw,
                          String serverPath) {
        String s;
        FTPClient ftpclient = new FTPClient();
        try {
            ftpclient.connect(host, port);
            int i = ftpclient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(i)) {
                ftpclient.disconnect();
                logger.error("ftpserver_refuse_connection");
                return false;
            }
            if (!ftpclient.login(userName, pw)) {
                logger.error("ftpserver_refuse_login");
                return false;
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Remote system is " + ftpclient.getSystemName());
            }
            if (binaryMode) {
                ftpclient.setFileType(2);
            }
            if (passiveMode) {
                ftpclient.enterLocalPassiveMode();
            }

            s = serverPath + targetFileName;
            boolean success = ftpclient.storeFile(s, new ByteArrayInputStream(bt));
            return success;
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //退出ftp并关闭连接
            disconnect(ftpclient);
        }
        return true;
    }

    /**
     * 判断文件是否存在
     *
     * @param sourceFileName
     * @return
     */
    public boolean checkFileIsExist(String sourceFileName) {
        FTPClient ftpclient = new FTPClient();
        try {
            // 替换登陆
            ftpclient.connect(serverIp, serverPort);
            int i = ftpclient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(i)) {
                ftpclient.disconnect();
                logger.error("ftpserver_refuse_connection");
                return false;
            }
            if (!ftpclient.login(userName, password)) {
                logger.error("ftpserver_refuse_login");
                return false;
            }

            // 替换被动模式设置
            ftpclient.enterLocalPassiveMode();


            // 替换文件检查逻辑
            FTPFile[] result = ftpclient.listFiles(this.serverPath + sourceFileName);

            return result != null && result.length > 0;
        } catch (Exception e) {
            logger.error("the appoint file is not exist", e);
            return false;
        } finally {

            //退出ftp并关闭连接
            disconnect(ftpclient);
        }

    }


    /**
     * 判断文件夹是否存在，若不存创建一个
     */
    public void checkIsExistAndBuild(String dir) {
        FTPClient ftpclient = new FTPClient();
        try {

            // 替换登陆
            ftpclient.connect(serverIp, serverPort);
            int i = ftpclient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(i)) {
                ftpclient.disconnect();
                logger.error("ftpserver_refuse_connection");
            }
            if (!ftpclient.login(userName, password)) {
                logger.error("ftpserver_refuse_login");
            }
            if (!ftpclient.changeWorkingDirectory(dir)) {
                ftpclient.makeDirectory(dir);
            }
        } catch (Exception e) {
            logger.error("FtpFileTransferCheckAndBuildDirErr", e);
        }
    }


    /**
     * 检查文件夹在当前目录下是否存在
     *
     * @param dir
     * @return
     * @throws IOException
     */
    public boolean isDirExist(FTPClient fc, String dir) throws IOException {
        try {
            return fc.changeWorkingDirectory(dir);
        } catch (IOException e) {
            logger.error("Check Dir Err", e);
            throw e;
        }
    }

    /**
     * 在当前目录下创建文件夹
     *
     * @param dir
     * @return
     * @throws Exception
     */
    public boolean createDir(FTPClient fc, String dir) throws IOException {
        try {
            return fc.makeDirectory(dir);
        } catch (IOException e) {
            logger.error("CreateDir Err", e);
            throw e;
        }
    }

    /**
     * 关闭连接
     *
     * @param ftp
     */
    private void disconnect(FTPClient ftp) {
        try {
            ftp.logout();
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            try {
                ftp.disconnect();
            } catch (IOException e) {
                logger.error("", e);
            }
        }
    }

}
