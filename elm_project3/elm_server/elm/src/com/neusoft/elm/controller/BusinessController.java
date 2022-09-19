package com.neusoft.elm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elm.po.Business;
import com.neusoft.elm.service.BusinessService;
import com.neusoft.elm.service.impl.BusinessServiceImpl;

public class BusinessController {

	public Object listBusinessByOrderTypeId(HttpServletRequest request) throws Exception{
		Integer orderTypeId = Integer.valueOf(request.getParameter("orderTypeId"));
		BusinessService service = new BusinessServiceImpl();
		List<Business> list= service.listBusinessByOrderTypeId(orderTypeId);
		return list;
	}
}
