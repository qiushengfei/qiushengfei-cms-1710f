package com.qiushengfei.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiushengfei.cms.dao.CollectDao;
import com.qiushengfei.cms.pojo.Collect;
import com.qiushengfei.cms.service.CollectService;

@Service
public class CollectServiceImpl implements CollectService {

	
	@Autowired
	private  CollectDao collectDao;
	
	
	@Override
	public int insert(Collect collect) {
		// TODO Auto-generated method stub
		return collectDao.insert(collect);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return collectDao.delete(id);
	}

	

	@Override
	public Collect selectByTitleAndUserId(String title, Integer userId) {
		// TODO Auto-generated method stub
		return collectDao.selectByTitleAndUserId(title, userId);
	}

	@Override
	public PageInfo<Collect> selects(Integer userId, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<Collect> list = collectDao.selects(userId);
		
		return new PageInfo<>(list);
	}

	

}
