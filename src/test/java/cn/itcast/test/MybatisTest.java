package cn.itcast.test;


import cn.itcast.dao.IUserDao;
import cn.itcast.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws IOException {

        InputStream  in= Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//3.使用构建者创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
//4.使用 SqlSessionFactory 生产 SqlSession 对象
        SqlSession session = factory.openSession();
//5.使用 SqlSession 创建 dao 接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
//6.使用代理对象执行查询所有方法
        List<User> users = userDao.findAll();
        for(User user : users) {
            System.out.println(user);
        }
//7.释放资源
        session.close();
        in.close();

    }
}
