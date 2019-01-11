import jdk.nashorn.internal.objects.Global;
import org.csource.fastdfs.*;
import org.junit.Test;

public class TestFastDFS {
@Test
    public void uploadFile() throws Exception{
        //1.向工程中添加jar包
        //2.创建一个配置文件，配置tracker服务器的地址
        //3.加载配置文件
        ClientGlobal.init("C:\\Users\\wangz\\IdeaProjects\\taotaoparent\\taotao-manager-web\\src\\main\\resources\\resource\\client.conf");
        //4.创建一个TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //5.使用TrackerClient获得trackerserver对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //6.创建一个storageServer的引用null就可以
        StorageServer storageServer = null;
        //7.创建一个storageClient对象，传入trackerServer，storageServer两个参数
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //8.使用StorageClient对象上传文件
       String[] strings =  storageClient.upload_file("C:\\onedrive\\图片\\调通的程序\\实现商品类目选择.jpg","jpg",null);
       for (String string :strings){
           System.out.println(string);
       }


    }
}
