package com.qiushengfei.cms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.qiushengfei.cms.common.CmsConst;
import com.qiushengfei.cms.common.JsonResult;
import com.qiushengfei.cms.pojo.Article;
import com.qiushengfei.cms.pojo.Category;
import com.qiushengfei.cms.pojo.Channel;
import com.qiushengfei.cms.pojo.Genericitys;
import com.qiushengfei.cms.pojo.User;
import com.qiushengfei.cms.service.ArticleService;

@Controller
@RequestMapping("/article/")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	/**
	 * @Title: add   
	 * @Description: 跳转到文章编辑页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@GetMapping("/add")
	public String toAdd(Integer id,Model model) {
		List<Channel> channelList = articleService.getChannelAll();
		model.addAttribute("channelList", channelList);
		if(id!=null) {
			Article article = articleService.getById(id);
			List<Category> cateList = articleService.getCateListByChannelId(article.getChannel_id());
			model.addAttribute("article", article);
			model.addAttribute("cateList", cateList);
		}
		return "article/add";
	}
	/**
	 * @Title: save   
	 * @Description: 保存文章   
	 * @param: @param article
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@PostMapping("/save")
	public @ResponseBody JsonResult save(Article article,HttpSession session) {
		User userInfo = (User)session.getAttribute(CmsConst.UserSessionKey);
		article.setUser_id(userInfo.getId());
		articleService.save(article);
		return JsonResult.sucess();
	}
	/**
	 * @Title: getCateListByChannelId   
	 * @Description: 根据频道Id查询分类列表  
	 * @param: @param channelId
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@GetMapping("/getCateListByChannelId")
	public @ResponseBody JsonResult getCateListByChannelId(Integer channelId) {
		List<Category> cateList = articleService.getCateListByChannelId(channelId);
		return JsonResult.sucess(cateList);
	}
	
	/**
	 * @Title: articles   
	 * @Description: 文章管理（我的文章）   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/articles")
	public String articles(Article article,Model model,HttpSession session,
			@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,
			@RequestParam(value="pageSize",defaultValue="2") Integer pageSize) {
		User userInfo = (User)session.getAttribute(CmsConst.UserSessionKey);
		article.setUser_id(userInfo.getId());
		PageInfo<Article> pageInfo = articleService.getPageInfo(article, pageNum, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "article/articles";
	}
	/**
	 * @Title: deleteByIds   
	 * @Description: 根据Ids批量删除文章   
	 * @param: @param ids
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@PostMapping("/deleteByIds")
	public @ResponseBody JsonResult deleteByIds(String ids) {
		articleService.deleteByIds(ids);
		return JsonResult.sucess();
	}
	
	
	/**
	 * @Title: add   
	 * @Description: 跳转到图片发布页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@GetMapping("/addimg")
	public String addimg(Integer id,Model model) {
		List<Channel> channelList = articleService.getChannelAll();
		model.addAttribute("channelList", channelList);
		if(id!=null) {
			Article article = articleService.getById(id);
			List<Category> cateList = articleService.getCateListByChannelId(article.getChannel_id());
			model.addAttribute("article", article);
			model.addAttribute("cateList", cateList);
		}
		return "article/addimg";
	}
	
	
	/**
	 * @Title: save   
	 * @Description: 发布图片
	 * @param: @param article
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@PostMapping("/saveimg")
	public @ResponseBody JsonResult saveimg(Article article,HttpSession session) {
		User userInfo = (User)session.getAttribute(CmsConst.UserSessionKey);
		article.setUser_id(userInfo.getId());
		
		//判断文章内是否包含图片  吐过包含图片则把枚举类写进去，是图片写进去1
		if(article.getContent().contains("<img")) {
			//存储下标为图片的下标1
			article.setGenericitys(1);
		}else {
			//否则存储0
			article.setGenericitys(0);;
		}
		articleService.save(article);
		return JsonResult.sucess();
	}
	
	
}
