package com.neuedu.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.neuedu.pojo.User;





public class MybatisTest {

	// 根据id查询用户信息，得到一条记录结果
	@Test
	public void findUserByIdTest() throws IOException {

		// mybatis配置文件
		String resource = "config/SqlMapConfig.xml";
		// 得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂，传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);

		// 通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 通过SqlSession操作数据库
		// 第一个参数：映射文件中statement的id，等于=namespace+"."+statement的id
		// 第二个参数：指定和映射文件中所匹配的parameterType类型的参数
		// sqlSession.selectOne结果 是与映射文件中所匹配的resultType类型的对象
		// selectOne查询出一条记录
		User user = sqlSession.selectOne("test.findUserById", 1);

		System.out.println(user);

		// 释放资源
		sqlSession.close();

	}

	// 根据用户名称模糊查询用户列表
	@Test
	public void findUserByNameTest() throws IOException {
		// mybatis配置文件
		String resource = "config/SqlMapConfig.xml";
		// 得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂，传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);

		// 通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// list中的user和映射文件中resultType所指定的类型一致
//		List<User> list = sqlSession.selectOne("test.findUserByName", "小明");
		List<User> list = sqlSession.selectList("test.findUserByName", "小军");
		System.out.println(list);
		sqlSession.close();

	}

	// 添加用户信息
	@Test
	public void insertUserTest() throws IOException {
//		// mybatis配置文件
//		String resource = "config/SqlMapConfig.xml";
//		// 得到配置文件流
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//
//		// 创建会话工厂，传入mybatis的配置文件信息
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
//				.build(inputStream);
//
//		// 通过工厂得到SqlSession
//		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 插入用户对象
		User user = new User();
		user.setId(1001);
		user.setUsername("王小军");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("河南郑州");
		List<User> list=new ArrayList<User>();
		list.add(user);
		System.out.println(list);
//		System.out.println(user);
//
//		sqlSession.insert("test.insertUser", user);
//
//		// 提交事务
//		sqlSession.commit();
//
//		// 获取用户信息主键
//		System.out.println(user.getId());
//		// 关闭会话
//		sqlSession.close();

//		System.out.println("1001");

	}

	// 根据id删除 用户信息
	@Test
	public void deleteUserTest() throws IOException {
		// mybatis配置文件
		String resource = "config/SqlMapConfig.xml";
		// 得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂，传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);

		// 通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 传入id删除 用户
		sqlSession.delete("test.deleteUser", 1001);

		// 提交事务
		sqlSession.commit();

		// 关闭会话
		sqlSession.close();

	}

	// 更新用户信息
	@Test
	public void updateUserTest() throws IOException {
		// mybatis配置文件
		String resource = "config/SqlMapConfig.xml";
		// 得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂，传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);

		// 通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 更新用户信息
		
		User user = new User();
		//必须设置id
		user.setId(203);
		user.setUsername("王大军");
		user.setBirthday(new Date());
		user.setSex("2");
		user.setAddress("河南郑州");

		sqlSession.update("test.updateUser", user);
		
		// 提交事务
		sqlSession.commit();

		// 关闭会话
		sqlSession.close();

	}


}
