package com.qiushengfei.cms.controller.myThread;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qiushengfei.cms.pojo.Article;
import com.qiushengfei.cms.pojo.Channel;
import com.qiushengfei.cms.service.ArticleService;
import com.qiushengfei.cms.service.CollectService;
import com.qiushengfei.cms.service.SlideService;
import com.qiushengfei.cms.service.UserService;

public class MyThread3 implements  Runnable {

	@Autowired
	private ArticleService articleService;
	List<Channel> channelList = articleService.getChannelAll();
	
	//编写一条线程获取右侧最新文章，存入作用域中。（4分）
	
	@Override
	public void run() {
		List<Article> newArticleList = articleService.getNewList(6);
	}
	
	
}
