package com.qiushengfei.cms.controller.myThread;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.qiushengfei.cms.pojo.Article;
import com.qiushengfei.cms.service.ArticleService;
import com.qiushengfei.cms.service.CollectService;
import com.qiushengfei.cms.service.SlideService;
import com.qiushengfei.cms.service.UserService;

public class MyThread1 implements  Runnable {

	@Autowired
	private ArticleService articleService;
	
	
	//编写一条线程获取首页热门文章，存入作用域中。（4分）
	@Override
	public void run() {
		PageInfo<Article> hotList = articleService.getHotList(1,5);
	}
	
	
}
