package cn.gzsxt.pms.config;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.DriverManagerDataSource;



@Configuration
@EnableTransactionManagement
public class DataConfig {
	@Value(value="${db.driver}")
	private String driver;
	
	@Value(value="${db.url}")
	private String url;
	
	@Value(value="${db.username}")
	private String username;
	
	@Value(value="${db.password}")
	private String password;
	
	@Bean(name="dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClass(driver);
		dataSource.setJdbcUrl(url);
		dataSource.setUser(username);
		dataSource.setPassword(password);
		return dataSource;
	} 
//	会话工厂
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory getSqlSessionFactory() {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(this.getDataSource());
		try {
			factoryBean.afterPropertiesSet();
			return factoryBean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//	创建mapper动态对象，并注入到容器中
	@Bean
	public static MapperScannerConfigurer getMapperScannerConfigurer() {
		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
		//指定会话工厂
		configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		//指定映射接口
		configurer.setBasePackage("cn.gzsxt.pms.mapper");
		//指定扫描的注解
		configurer.setAnnotationClass(Mapper.class);
		return configurer;
	}
	//事务代理
	@Bean
	public DataSourceTransactionManager getDataSourceTransactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(this.getDataSource());
		return transactionManager;
	}
}
