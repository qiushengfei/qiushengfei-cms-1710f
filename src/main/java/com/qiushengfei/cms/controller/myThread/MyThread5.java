package com.qiushengfei.cms.controller.myThread;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qiushengfei.cms.pojo.Channel;
import com.qiushengfei.cms.service.ArticleService;
import com.qiushengfei.cms.service.CollectService;
import com.qiushengfei.cms.service.SlideService;
import com.qiushengfei.cms.service.UserService;

public class MyThread5 implements  Runnable {

	@Autowired
	private ArticleService articleService;

	
	@Override
	public void run() {
		//编写一条线程获取栏目和分类，存入作用域中。（4分）
		List<Channel> channelList = articleService.getChannelAll();
	}
	
	
}
