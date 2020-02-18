package com.qiushengfei.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qiushengfei.cms.dao.CollectDao;
import com.qiushengfei.cms.pojo.Collect;
import com.qiushengfei.cms.service.CollectService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class test {


	@Autowired
	private CollectService  collectService;
	
	
	@Autowired
	private CollectDao collectDao;
	
	@Test
	public void test() {
		Collect selectByTitleAndUserId = collectService.selectByTitleAndUserId("aa", 166);
		System.out.println(selectByTitleAndUserId);
		
		
	}

}
