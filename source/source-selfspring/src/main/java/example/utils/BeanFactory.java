package example.utils;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName: BeanFactory
 * @Description: 模拟spring的beanFactory。
 *               实现IOC的注入
 * @Author: Uetec
 * @Date: 2020-12-01-15:45
 * @Version: 1.0
 **/
public class BeanFactory {
    //相当于缓存中间层，存储XML解析的对象
    Map<String,Object> map = new HashMap<String,Object>();
    public BeanFactory(String xml){
        parseXml(xml);
    }
    public void parseXml(String xml) throws SelfSpringException{
        //File file = new File(this.getClass().getResource("/").getPath()+"/"+xml);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(this.getClass().getResource("/").getPath()+"/"+xml);
            //根节点 beans
            Element elementRoot = document.getRootElement();
            //自动注入
            Attribute attribute = elementRoot.attribute("default-autowired");
            boolean flag=false;
            if (attribute!=null){
                flag=true;
            }

            for (Iterator<Element> itFirlst = elementRoot.elementIterator(); itFirlst.hasNext();) {
                /**
                 * setup1、实例化对象，bean
                 * <bean id="dao" class="example.dao.impl.UserDaoImpl1"></bean>
                 */
                Element elementFirstChild = itFirlst.next();
                Attribute attributeId = elementFirstChild.attribute("id");
                String beanName = attributeId.getValue();
                Attribute attributeClass = elementFirstChild.attribute("class");
                String clazzName  = attributeClass.getValue();

                Class clazz = Class.forName(clazzName);
                /**
                 * 维护依赖关系
                 * 看这个对象有没有依赖（判断XML是否有property。）
                 * 如果有则注入
                 */
                Object object = null;
                for (Iterator<Element> itSecond = elementFirstChild.elementIterator(); itSecond.hasNext();){
                    // 得到ref的value，通过value得到对象（map）
                    // 得到name的值，然后根据值获取一个Filed的对象
                    //通过field的set方法set那个对象

                    //<property name="dao" ref="dao"></property>
                    Element elementSecondChild = itSecond.next();
                    if(elementSecondChild.getName().equals("property")){
                        //是setter，沒有特殊的构造方法
                        object= clazz.newInstance();
                        String refVlaue = elementSecondChild.attribute("ref").getValue();
                        Object injectObject= map.get(refVlaue);
                        String nameVlaue = elementSecondChild.attribute("name").getValue();
                        //setDao
                        Field field = clazz.getDeclaredField(nameVlaue);
                        field.setAccessible(true);
                        //this.dao = dao
                        field.set(object,injectObject);
                    //<constructor-args name="dao" ref="dao"></constructor-args>
                    }else if(elementSecondChild.getName().equals("constructor-args")){
                        //说明有特殊构造方法
                        String refVlaue = elementSecondChild.attribute("ref").getValue();
                        Object injectObject= map.get(refVlaue);
                        Class injectObjectClazz = injectObject.getClass();
                        Constructor constructor = clazz.getConstructor(injectObjectClazz.getInterfaces()[0]);
                        object = constructor.newInstance(injectObject);
                    }

                }
                //处理自动注入，自动注入与手动注入都存在，以手动注入为主
                if(object==null) {
                    if (flag) {
                        if (attribute.getValue().equals("byType")) {
                            //判斷是否有依賴
                            Field fields[] = clazz.getDeclaredFields();
                            for (Field field : fields) {
                                //得到属性类型，比如String aa那麽這裏的field.getType()=String.class
                                Class injectObjectClazz = field.getType();
                                /**
                                 * 由于是bytype 所以需要遍历map当中的所有对象
                                 * 判断对象的类型是不是和这个injectObjectClazz相同
                                 */
                                int count = 0;
                                Object injectObject = null;
                                for (String key : map.keySet()) {
                                    Class temp = map.get(key).getClass().getInterfaces()[0];
                                    if (temp.getName().equals(injectObjectClazz.getName())) {
                                        injectObject = map.get(key);
                                        //记录找到一个，因为可能找到多个count
                                        count++;
                                    }
                                }

                                if (count > 1) {
                                    throw new SelfSpringException("需要一个对象，但是找到了两个对象");
                                } else {
                                    object = clazz.newInstance();
                                    field.setAccessible(true);
                                    field.set(object, injectObject);
                                }
                            }
                        }
                    }
                }

                if(object==null){//沒有子标签
                    object = clazz.newInstance();
                }
                map.put(beanName,object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(map);
    }

    public Object getBean(String beanName){
        return map.get(beanName);
    }
}
