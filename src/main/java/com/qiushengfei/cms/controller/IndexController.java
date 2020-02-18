package com.qiushengfei.cms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.qiushengfei.cms.common.CmsConst;
import com.qiushengfei.cms.controller.myThread.MyThread1;
import com.qiushengfei.cms.controller.myThread.MyThread2;
import com.qiushengfei.cms.controller.myThread.MyThread3;
import com.qiushengfei.cms.controller.myThread.MyThread4;
import com.qiushengfei.cms.controller.myThread.MyThread5;
import com.qiushengfei.cms.pojo.Article;
import com.qiushengfei.cms.pojo.Category;
import com.qiushengfei.cms.pojo.Channel;
import com.qiushengfei.cms.pojo.Collect;
import com.qiushengfei.cms.pojo.Slide;
import com.qiushengfei.cms.pojo.User;
import com.qiushengfei.cms.service.ArticleService;
import com.qiushengfei.cms.service.CollectService;
import com.qiushengfei.cms.service.SlideService;
import com.qiushengfei.cms.service.UserService;

@Controller
public class IndexController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private UserService userService;
	

	@Autowired
	private CollectService  collectService;
	
	/**
	 * @Title: index   
	 * @Description: 首页   
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/")
	public String index(Model model) {
		hot(model, 1);
		return "index";
	}
	/**
	 * @Title: hot   
	 * @Description: 热门分页   
	 * @param: @param model
	 * @param: @param pageNum
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/hot/{pageNum}.html")
	public String hot(Model model,@PathVariable Integer pageNum) {
		List<Channel> channelList = articleService.getChannelAll();
		List<Slide> slideList = slideService.getAll();
		PageInfo<Article> pageInfo = articleService.getHotList(pageNum,5);
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("channelList", channelList);
		model.addAttribute("slideList", slideList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("newArticleList", newArticleList);
		//最新图片 分页为10
				//因为只有六条关于图片的所以展示6个 
				List<Article> newimg =articleService.getimage(10);
				model.addAttribute("newimg", newimg);
		return "index";
	}
	
	/**
	 * @Title: channel   
	 * @Description: 频道页   
	 * @param: @param model
	 * @param: @param channelId
	 * @param: @param cateId
	 * @param: @param pageNum
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/{channelId}/{cateId}/{pageNum}.html")
	public String channel(Model model,@PathVariable Integer channelId,@PathVariable Integer cateId,@PathVariable Integer pageNum) {
		List<Channel> channelList = articleService.getChannelAll();
		List<Slide> slideList = slideService.getAll();
		PageInfo<Article> pageInfo = articleService.getList(channelId,cateId,pageNum,2);
		List<Category> cateList = articleService.getCateListByChannelId(channelId);
		Channel channel = articleService.getChannelByChannelId(channelId);
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("channelList", channelList);
		model.addAttribute("cateList", cateList);
		model.addAttribute("slideList", slideList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("channel", channel);
		model.addAttribute("newArticleList", newArticleList);
		//最新图片 分页为10
		List<Article> newimg =articleService.getimage(2);
		model.addAttribute("newimg", newimg);
		return "index";
	}
	/**
	 * @Title: articleDetail   
	 * @Description: 文章详情页  
	 * @param: @param id
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/article/detail/{id}.html")
	public String articleDetail(@PathVariable Integer id,Model model,HttpSession session) {
		Article article = articleService.getById(id);
		User user = userService.getById(article.getUser_id());
		article.setNickname(user.getNickname());
		model.addAttribute("article", article);
		/** 设置文章点击量，若点击量大于20成为热点文章 **/
		articleService.setHitsAndHot(id);
		//查询该文章是否被用户收藏过/从session 获取当前登录用户并根据查询条件获取collect
		User user2 = (User)session.getAttribute(CmsConst.UserSessionKey);
		
		 if(null!= user) {
			Collect collect = collectService.selectByTitleAndUserId(article.getTitle(), user2.getId());
			model.addAttribute("collect", collect);
		 }
		
		
		
		return "article-detail";
	}
	
	@ResponseBody
	@RequestMapping("collect")
	 public boolean collect(Collect collect,HttpSession session) {
		
		User user = (User)session.getAttribute(CmsConst.UserSessionKey);
		if(null==user) {//session过期。
			return false;
		}
		collect.setUser_id(user.getId());
		collect.setCreated(new Date());
		return collectService.insert(collect) >0;
	 }
	
//取消收藏
	@ResponseBody
	@RequestMapping("unCollect")
	public boolean unCollect(Integer  id,HttpSession session) {
		User user = (User)session.getAttribute(CmsConst.UserSessionKey);
		if(null ==user) {//session过期。
			return false;
		}
		return collectService.delete(id) >0;
	}
	
	@GetMapping("/xc")
	public  String main() {
		//线程
		MyThread1 myThread1 = new MyThread1();
		MyThread2 myThread2 = new MyThread2();
		MyThread3 myThread3 = new MyThread3();
		MyThread4 myThread4 = new MyThread4();
		MyThread5 myThread5 = new MyThread5();
		Thread thread1= new Thread(myThread1);
		Thread thread2= new Thread(myThread2);
		Thread thread3= new Thread(myThread3);
		Thread thread4= new Thread(myThread4);
		Thread thread5 = new Thread(myThread5);
		//开启线程
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		return "index";
	}
}
