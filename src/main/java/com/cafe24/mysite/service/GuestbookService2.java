package com.cafe24.mysite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.mysite.vo.GuestbookVo;

@Service
public class GuestbookService2 {
	
	
	public List<GuestbookVo> getContentList() {
		GuestbookVo vo1 = new GuestbookVo(150L, "user1", "1234", "test1", "2019-07-10 09:20");
		GuestbookVo vo2 = new GuestbookVo(151L, "user2", "1234", "test2", "2019-07-10 09:30" );
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		list.add(vo1);
		list.add(vo2);
		return list;
	}

	public GuestbookVo addContents(GuestbookVo guestbookVo) {
		guestbookVo.setNo(10L);
		guestbookVo.setRegDate("2019-07-10 00:00:00");
		return guestbookVo;
	}

	public Long deleteContents(Long no, String password) {
		return no;
	}
}
