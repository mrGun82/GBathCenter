package g.oa.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class MyBatisUtil {
	private static SqlSessionFactory sqlMap;

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlMap;
	}
	
	public static SqlSession getSqlSession() {
		return getSqlSessionFactory().openSession();
	}
	
	public static SqlSession getSqlSession(boolean isAutoCommit) {
		return getSqlSessionFactory().openSession(isAutoCommit);
	}
	
	static {
		try {
			String resource = "MyBatisConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
