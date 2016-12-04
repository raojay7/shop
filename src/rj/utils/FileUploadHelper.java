package rj.utils;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;

import static org.apache.struts2.ServletActionContext.getServletContext;

/**
 * 文件上传下载的工具
 * 首先必须有struts的三个属性
 * 在通过构造函数，来传入文件放置的路径
 *
 *
 * 后期可能会增加文件命名，以方便管理
 * Created by 隽 on 2016/12/4.
 */
public class FileUploadHelper{
    //struts文件上传时需要的参数,而且一定要给set方法
    //文件的name属性，要与表单中相同
    private File upload;

    //用于接收上传的文件名
    private String uploadFileName;

    //用于接收文件的类型
    private String uploadContextType;
    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContextType() {
        return uploadContextType;
    }

    public void setUploadContextType(String uploadContextType) {
        this.uploadContextType = uploadContextType;
    }
    //多个文件所放置的文件夹名字,直接放在web目录下
    private String imagesName;

    /**
     * 构造函数传入多个文件所放置的文件夹名字以及struts的文件上传的三个属性
     * new的时候只能在具体的方法中进行
     * @param imagesName
     */
    public FileUploadHelper(File upload, String uploadFileName, String uploadContextType, String imagesName) {
        this.upload = upload;
        this.uploadFileName = uploadFileName;
        this.uploadContextType = uploadContextType;
        this.imagesName = imagesName;
    }

    /**
     * 调用上传方法，返回一个图片路径的字符串、
     * 在具体的实体类中只要调用setimage就可以
     * @return
     */
    public String upload(){
        if (upload!=null){
            //获得文件上传的磁盘路径
            String realPath= getServletContext().getRealPath("/"+imagesName);
            //创建一个磁盘文件
            File diskFile=new File(realPath+"//"+uploadFileName);//
            try {
                FileUtils.copyFile(upload,diskFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return imagesName+"/"+uploadFileName;
        }
        return null;
    }

    /**
     * 文件删除的方法，若删除成功返回true
     * @param path 传入数据库中放置的图片路径
     * @return
     */
    public boolean deleteFile(String path){
        if (path!=null){
            String realPath= ServletActionContext.getServletContext().getRealPath("/"+path);
            File file=new File(realPath);
            file.delete();
            return true;
        }
        return false;
    }

    /**
     * 通过原来图片的路径来更新,只需要原数据库的路径
     * @param oldpath
     * @return
     */
    public String updateFile(String oldpath){
        // 上传:
        if(upload != null ){
            //先删除原来的图片
            String delPath = ServletActionContext.getServletContext().getRealPath(
                    "/" + oldpath);
            File file = new File(delPath);
            file.delete();
            //再更新新的图片
            // 获得上传图片的服务器端路径.
            String path = ServletActionContext.getServletContext().getRealPath(
                    "/"+imagesName);
            // 创建文件类型对象:
            File diskFile = new File(path + "//" + uploadFileName);
            // 文件上传:
            try {
                FileUtils.copyFile(upload, diskFile);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return imagesName+"/" + uploadFileName;
        }
        return null;
    }

}
