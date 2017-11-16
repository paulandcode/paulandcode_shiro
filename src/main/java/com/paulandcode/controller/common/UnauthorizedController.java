package com.paulandcode.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @author 黄建峰
 * @date 2017年11月10日 下午1:39:08
 */
@Controller
public class UnauthorizedController {

	@RequestMapping("/unauthorized")
	public String unauthorized() {
		return "common/unauthorized";
	}
	
}
