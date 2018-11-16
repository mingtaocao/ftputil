package top.mytao.ftputil.business.ftp.service;

/**
 * @Description:
 * @Author: caomingtao
 * @Date Create In 14:13 2018/11/16
 * @Modified By:
 */
public interface FileTransfer {

    public boolean upload(byte[] bt, String targetFileName);

    public boolean upload(byte[] bt, String targetFileName, String host, int port, String userName, String pw, String serverPath);

    public byte[] download(String sourceFileName);


}
