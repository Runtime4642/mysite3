package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestBookDao;
import com.douzone.mysite.vo.GuestBookVo;


@Service
public class GuestbookService {
	
	@Autowired
	private GuestBookDao guestbookDao;
	

	public List<GuestBookVo>list() {
		List<GuestBookVo> list = guestbookDao.getList();
		
		return list;
	}
	
	public void delete(String no,String password) {
	 guestbookDao.delete(no, password);
		
	}
	
	public void write(GuestBookVo guestBookVo) {
		guestbookDao.insert(guestBookVo);
	}
}
