package common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {
	public static SqlSession getSqlSession() {
		SqlSession session = null;
		String resource = "mybatis-config.xml"; //config 파일 설정
		
		try {
			//해당 리소스 경로(xml 파일)을 바탕으로 input stream 생성(config.xml파일의 값들을 읽어오기 위함)
			InputStream is = Resources.getResourceAsStream(resource);
			
			//SqlSessionFactory를 만들 수 있는 팩토리 빌더 생성() : 
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			
			//팩토리 빌더를 통해서 실제 사용할 SqlSessionFactory 생성
			SqlSessionFactory factory = builder.build(is); //Connection을 is 정보로 만들 수 있도록 설정
			
			session = factory.openSession(false); //false는 autoCommit을 하지 않는다라는 의미
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return session;
	}
}
