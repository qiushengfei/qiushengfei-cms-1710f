package com.qiushengfei.cms.controller.myThread;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qiushengfei.cms.pojo.Slide;
import com.qiushengfei.cms.service.ArticleService;
import com.qiushengfei.cms.service.CollectService;
import com.qiushengfei.cms.service.SlideService;
import com.qiushengfei.cms.service.UserService;

public class MyThread2 implements  Runnable {

	@Autowired
	private SlideService slideService;

	
	//编写一条线程获取顶部幻灯片数据，存入作用域中。
	@Override
	public void run() {
		
		List<Slide> slideList = slideService.getAll();
	}
	
	
}
