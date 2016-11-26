package rj.product.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import rj.categorysecond.entity.CategorySecond;
import rj.categorysecond.service.CategorySecondService;
import rj.product.entity.Product;
import rj.product.service.ProductService;
import rj.utils.PageBean;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.apache.struts2.ServletActionContext.getServletContext;

/**
 * Created by 隽 on 2016/11/26.
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{


    private Product product=new Product();
    @Override
    public Product getModel() {
        return product;
    }
    // 注入CategoryService
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    // 注入CategorySecondService
    private CategorySecondService categorySecondService;

    public void setCategorySecondService(
            CategorySecondService categorySecondService) {
        this.categorySecondService = categorySecondService;
    }
    private Integer page;

    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * 查询所有商品方法
     */
    public String findAll(){
        //调用service得到分页，将分页保存到值栈
        PageBean<Product> pageBean= productService.findByPage(page);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAll";
    }

    /**
     * 进入添加页面
     * 同时查询到商品所属的所有二级分类
     * @return
     */
    public String addPage(){
        //查询所有的二级分类
        List<CategorySecond> csList=categorySecondService.findAll();
        //放入值栈
        ActionContext.getContext().getValueStack().set("csList",csList);
        return "addPage";
    }

    //struts文件上传时需要的参数,而且一定要给set方法
    //文件的name属性，要与表单中相同
    private File upload;

    public void setUpload(File upload) {
        this.upload = upload;
    }

    //用于接收上传的文件名
    private String uploadFileName;

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    //用于接收文件的类型
    private String uploadContextType;

    public void setUploadContextType(String uploadContextType) {
        this.uploadContextType = uploadContextType;
    }

    /**
     *  完成修改，返回productlist页面
     *  上传文件
     *  把product信息加入数据库
     */
    public String save(){
        product.setPdate(new Date());

        if (upload!=null){
            //获得文件上传的磁盘路径
            String realPath= getServletContext().getRealPath("/products");
            //创建一个磁盘文件
            File diskFile=new File(realPath+"//"+uploadFileName);//为什么要加“//”?
            try {
                FileUtils.copyFile(upload,diskFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            product.setImage("products/"+uploadFileName);
        }
        productService.save(product);
        return "saveSuccess";
    }

    //删除商品
    public String delete(){
        //先查询再删除
        product=productService.findByPid(product.getPid());
        //删除上传的照片
        String path=product.getImage();
        if (path!=null){
            String realPath=ServletActionContext.getServletContext().getRealPath("/"+path);
            File file=new File(realPath);
            file.delete();
        }
        productService.delete(product);
        return "deleteSuccess";
    }

    public String edit() {
        //根据商品id查询商品
        product=productService.findByPid(product.getPid());
        //显示所有二级分类的集合
        List<CategorySecond> csList=categorySecondService.findAll();
        ActionContext.getContext().getValueStack().set("csList",csList);
        return "editSuccess";
    }

    /**
     * 自己修改的方法
     * @return
     */
    public String update(){
        // 将信息修改到数据库
        product.setPdate(new Date());

        // 上传:
        if(upload != null ){
            //先删除原来的图片
            String delPath = ServletActionContext.getServletContext().getRealPath(
                    "/" + product.getImage());
            File file = new File(delPath);
            file.delete();
            //再更新新的图片
            // 获得上传图片的服务器端路径.
            String path = ServletActionContext.getServletContext().getRealPath(
                    "/products");
            // 创建文件类型对象:
            File diskFile = new File(path + "//" + uploadFileName);
            // 文件上传:
            try {
                FileUtils.copyFile(upload, diskFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            product.setImage("products/" + uploadFileName);
        }
        productService.update(product);
        // 页面跳转
        return "updateSuccess";
    }
}
