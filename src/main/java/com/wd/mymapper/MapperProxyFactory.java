package com.wd.mymapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by woody on 2017/7/29.
 * 生成mapper 的动态代理工厂
 * 文件演示了可能的动态生成mapper的方法，在不考虑事务的情况下。
 * 实际情况是这样的：
 * 代理对象构建时，传入了接口class 和 sqlSession，这个session就可以由外部事务管理了。
 * 然后同样的分发任务。
 * http://blog.csdn.net/oh_mourinho/article/details/51858962
 */
public class MapperProxyFactory implements InvocationHandler {


    SqlSessionFactory sqlSessionFactory;

    public MapperProxyFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        init();
    }

    private Map<String,String> sqlMap = new HashMap<>();
    private Map<String,String> sqlMethod = new HashMap<>();
    private Map<String,Class> mappers = new HashMap<>();
    Map<String,String> idInfo = new HashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String sql = sqlMap.get(method.getName());
        SqlSession session = sqlSessionFactory.openSession();
        /**
         *  第一个参数的值，是从扫描mapper 注册到的语句来的。可以按标签判断sql 动作
         *  会有一个存储map 类似于 sqlMap   Key = com.wd.mapper.OrderMapper.get   Value = sql 语句，然后执行
         *  key  = proxyedInterface.name + 句子id
         *  参数具体选几个，动作如何，可以由xml 中的resultType 和 params推断。
         *  此处为演示先写死了参数
         *  根据 mapper 的id即方法，匹配相应的动作<select><insert><update><delete>等
        **/
        String proxyedInterfaceName = method.getDeclaringClass().getName(); //可得到被代理的接口名字，及接口
        String idInXml = proxyedInterfaceName+"."+method.getName();
        String sessionMethod = idInfo.get(idInXml);
        //根据注册的方法和参数，选择适当的session方法，会使用策略模式？
        if(sessionMethod.equals("selectOne")){
            Object obj = session.selectOne(idInXml,args[0]);
            return obj;
        }else if(sessionMethod.equals("insert")){
            return session.insert(idInXml,args[0]);
        }
        return null;
    }

    public <T> T create(Class<T> clazz){
        //根据扫描得到的mapper Interface 信息 动态生成接口实现，并在Spring中注册。
        Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},this);
        return clazz.cast(proxy);
    }

    private void init(){

        //有扫描mapper.xml 文件可以得到很多定义好的信息。
        sqlMap.put("com.wd.mapper.OrderMapper.get","select id,order_price as price from t_order where id=#{id}")    ;
        mappers.put("MyOrderMapper",MyOrderMapper.class);
        idInfo.put("com.wd.mymapper.MyOrderMapper.get","selectOne");
        idInfo.put("com.wd.mymapper.MyOrderMapper.save","insert");
    }
}
