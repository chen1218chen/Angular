package com.city.action;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gis.wx.utils.PastUtil;

import net.sf.json.JSONObject;
@RestController
@RequestMapping("/sign")
public class SignAction {

	@RequestMapping(value = "/getSignature")
	public JSONObject getSignature(@RequestParam String url) throws Exception {
		return PastUtil.getSign(url);
	}
}