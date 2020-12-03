package example.utils;

import example.anno.SelfService;

import java.io.File;

/**
 * @ClassName: AnnotationConfigApplicationContext
 * @Description: 注解版的spring上下文。
 *               自动扫描，将类实例化。
 * @Author: Uetec
 * @Date: 2020-12-02-15:27
 * @Version: 1.0
 **/
public class AnnotationConfigApplicationContext {

    /**
     * @param basePackage 如：example.dao
     */
    public void scan(String basePackage){
        //环境根目录，classes文件夹下
        //String rootPath = this.getClass().getResource("/").getPath();
        String rootPath = "D:\\Program Files\\Java\\JetBrains\\Intellij IEDA 2020.2 Project\\framework-study\\source-selfspring\\target\\classes";
        String  basePackagePath =basePackage.replaceAll("\\.","\\\\");
        File file = new File(rootPath+"//"+basePackagePath);
        String names[]=file.list();
        for (String name : names) {
            name=name.replaceAll(".class","");
            try {
                //格式：Class.forName(“example.dao.impl.UserDaoImpl1”)
                Class clazz =  Class.forName(basePackage+"."+name);
                //判斷是否属于@XXX注解
                if(clazz.isAnnotationPresent(SelfService.class)){
                    SelfService selfService = (SelfService) clazz.getAnnotation(SelfService.class);
                    System.out.println(selfService.value());
                    System.out.println(clazz.newInstance());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
