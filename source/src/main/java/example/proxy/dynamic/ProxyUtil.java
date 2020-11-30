package example.proxy.dynamic;

import example.proxy.handler.AnotherSelfInvocationHanler;
import example.proxy.handler.SelfInvocationHandlerInf;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * @ClassName: ProxyUtil
 * @Description: 动态代理工具类。
 * @Author: Uetec
 * @Date: 2020-11-25-11:33
 * @Version: 1.0
 **/
public class ProxyUtil {

    /**
     *  参考IndexDaoTime模板生成动态代理工具类
     *  content --->string
     *  .java  io
     * .class
     *  new   反射----》class
     * @param target 目标对象，如：IndexDaoTime
     * @return
     */
    /*不需要手动创建类文件（因为一旦手动创建类文件，就会产生类爆炸），通过接口反射生成一个类文件，然后调用第三方的编译技术，
    动态编译这个产生的类文件成class文件，继而利用UrlclassLoader(因为这个动态产生的class不在工程当中所以需要使用UrlclassLoader)把这个动态编译的类加载到jvm当中，
    最后通过反射把这个类实例化。
    缺点：首先要生成文件 java
    缺点：动态编译文件 class
    缺点：需要一个URLclassloader
    软件性能的最终体现在IO操作*/

    public static Object newInstance(Object target){
        Object proxy=null;
        //获取接口，如：IndexDao
        Class targetInf = target.getClass().getInterfaces()[0];
        //获取方法
        Method methods[] =targetInf.getDeclaredMethods();
        String line="\n";
        String tab ="\t";
        //接口名称
        String infName = targetInf.getSimpleName();
        String content ="";
        //包名
        String packageContent = "package example.proxy.dao;"+line;
        //包路径
        String importContent = "import "+targetInf.getName()+";"+line;
        //类第一行
        String clazzFirstLineContent = "public class $Proxy implements "+infName+"{"+line;
        //属性
        String attributeContent  =tab+"private "+infName+" target;"+line;
        //有参构造
        String constructorContent =tab+"public $Proxy ("+infName+" target){" +line
                +tab+tab+"this.target =target;"
                +line+tab+"}"+line;
        //方法
        String methodContent = "";
        for (Method method : methods) {
            //返回类型
            String returnTypeName = method.getReturnType().getSimpleName();
            //方法名
            String methodName =method.getName();
            // Sting.class String.class
            Class args[] = method.getParameterTypes();
            //方法参数
            String argsContent = "";
            String paramsContent="";
            int flag =0;
            for (Class arg : args) {
                String temp = arg.getSimpleName();
                //String
                //String p0,Sting p1,
                argsContent+=temp+" p"+flag+",";
                paramsContent+="p"+flag+",";
                flag++;
            }
            //去除最后，
            if (argsContent.length()>0){
                argsContent=argsContent.substring(0,argsContent.lastIndexOf(",")-1);
                paramsContent=paramsContent.substring(0,paramsContent.lastIndexOf(",")-1);
            }

            methodContent+=tab+"public "+returnTypeName+" "+methodName+"("+argsContent+") {"+line
                    +tab+tab+"System.out.println(\"proxy content\");"+line;
                if(!returnTypeName.equals("void")){
                    methodContent+=tab+tab+"return target."+methodName+"("+paramsContent+");"+line;
                }else{
                    methodContent+=tab+tab+"target."+methodName+"("+paramsContent+");"+line;
                }
            methodContent+=tab+"}"+line;

        }

        content=packageContent+importContent+clazzFirstLineContent+attributeContent+constructorContent+methodContent+"}";

        File file =new File("C:\\Users\\Uetec\\Desktop\\example\\proxy\\dao\\$Proxy.java");
        File tempfile =new File("C:\\Users\\Uetec\\Desktop\\example\\proxy\\dao");
        if (!tempfile.exists()) {
            tempfile.mkdirs();
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            //字符流
            FileWriter fw = new FileWriter(file);
            fw.write(content);
            fw.flush();
            fw.close();

            //JDK编译
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            //.java-》.class
            StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
            Iterable units = fileMgr.getJavaFileObjects(file);

            JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
            t.call();
            fileMgr.close();

            //newInstance
            URL[] urls = new URL[]{new URL("file:C:\\Users\\Uetec\\Desktop\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class clazz = urlClassLoader.loadClass("example.proxy.dao.$Proxy");

            Constructor constructor = clazz.getConstructor(targetInf);
            //目标对象
            proxy = constructor.newInstance(target);
            //clazz.newInstance();
            //Class.forName()
        }catch (Exception e){
            e.printStackTrace();
        }

        return proxy;
    }

    /**
     * 模拟JDK动态代理
     * @param
     * @return
     */
    public static Object newProxyInstance(Class targetInf, SelfInvocationHandlerInf h){
        Object proxy=null;
        //获取接口，如：IndexDao
        //获取方法
        Method methods[] =targetInf.getDeclaredMethods();
        String line="\n";
        String tab ="\t";
        //接口名称
        String infName = targetInf.getSimpleName();
        String content ="";
        //包名
        String packageContent = "package example.proxy.dao;"+line;
        //包路径
        String importContent = "import "+targetInf.getName()+";"+line
                             + "import example.proxy.handler.SelfInvocationHandlerInf;"+line
                             + "import java.lang.Exception;"+line
                             + "import java.lang.reflect.Method;"+line;
        //类第一行
        String clazzFirstLineContent = "public class $Proxy implements "+infName+"{"+line;
        //属性
        String attributeContent  =tab+"private SelfInvocationHandlerInf h;"+line;
        //有参构造
        String constructorContent =tab+"public $Proxy (SelfInvocationHandlerInf h){" +line
                +tab+tab+"this.h = h;"
                +line+tab+"}"+line;
        //方法
        String methodContent = "";
        for (Method method : methods) {
            //返回类型
            String returnTypeName = method.getReturnType().getSimpleName();
            //方法名
            String methodName =method.getName();
            // Sting.class String.class
            Class args[] = method.getParameterTypes();
            //方法参数
            String argsContent = "";
            String paramsContent="";
            int flag =0;
            for (Class arg : args) {
                String temp = arg.getSimpleName();
                //String
                //String p0,Sting p1,
                argsContent+=temp+" p"+flag+",";
                paramsContent+="p"+flag+",";
                flag++;
            }
            //去除最后，
            if (argsContent.length()>0){
                argsContent=argsContent.substring(0,argsContent.lastIndexOf(",")-1);
                paramsContent=paramsContent.substring(0,paramsContent.lastIndexOf(",")-1);
            }

            methodContent+=tab+"public "+returnTypeName+" "+methodName+"("+argsContent+") throws Exception{"+line
                    +tab+tab+"Method method = Class.forName(\""+targetInf.getName()+"\").getDeclaredMethod(\""+methodName+"\");"+line
                    +tab+tab+"return ("+returnTypeName+")h.invoke(method);"+line;
            methodContent+=tab+"}"+line;

        }

        content=packageContent+importContent+clazzFirstLineContent+attributeContent+constructorContent+methodContent+"}";

        File file =new File("C:\\Users\\Uetec\\Desktop\\example\\proxy\\dao\\$Proxy.java");
        File tempfile =new File("C:\\Users\\Uetec\\Desktop\\example\\proxy\\dao");
        if (!tempfile.exists()) {
            tempfile.mkdirs();
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            //字符流
            FileWriter fw = new FileWriter(file);
            fw.write(content);
            fw.flush();
            fw.close();

            //JDK编译
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            //.java-》.class
            StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
            Iterable units = fileMgr.getJavaFileObjects(file);

            JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
            t.call();
            fileMgr.close();

            //newInstance
            URL[] urls = new URL[]{new URL("file:C:\\Users\\Uetec\\Desktop\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class clazz = urlClassLoader.loadClass("example.proxy.dao.$Proxy");

            Constructor constructor = clazz.getConstructor(SelfInvocationHandlerInf.class);
            //目标对象
            proxy = constructor.newInstance(h);
            //clazz.newInstance();
            //Class.forName()
        }catch (Exception e){
            e.printStackTrace();
        }

        return proxy;
    }
}
