package cn.gzsxt.pms.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.gzsxt.pms.config.ContextConfig;
import cn.gzsxt.pms.config.DataConfig;
import cn.gzsxt.pms.mapper.AdminMapper;

public class ConfigTest {

	@Test
	public void addAdmin() {
		try {
		Class<?>[] clases = {DataConfig.class,ContextConfig.class};
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(clases);
		AdminMapper mapper = context.getBean(AdminMapper.class);
		Map<String, Object> entity= new HashMap<>();
		
			entity.put("admin_name", "wangwu");
			entity.put("admin_account", "123456");
			mapper.insert(entity);
			context.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
