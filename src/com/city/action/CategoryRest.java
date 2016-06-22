package com.city.action;

import java.util.List;
import javax.annotation.Resource;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.city.model.Category;
import com.city.service.ICategoryService;

@Controller
@RequestMapping("/category")
public class CategoryRest {
	@Resource
	private ICategoryService categoryImpl;

	@RequestMapping(value = "/queryAll", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray queryAll() {
		
		List<Category> dataList = categoryImpl.queryAll();
		JSONArray userJSONList2 = JSONArray.fromObject(dataList);
		return userJSONList2;
	}
	
	@RequestMapping(value = "/queryById", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray queryById(@RequestParam("cid") String cid) {

		Category category=null;
		JSONArray userJSONList2 =null;
		try {
			category = categoryImpl.queryById(Integer.parseInt(cid));
			userJSONList2 = JSONArray.fromObject(category);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}

		return userJSONList2;
	}
	
	@RequestMapping(value = "/queryByLevel", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray queryByLevel(@RequestParam("level") String level) {

		List<Category> category=null;
		JSONArray userJSONList2 =null;
		try {
			category = categoryImpl.queryByLevel(level);
			userJSONList2 = JSONArray.fromObject(category);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}

		return userJSONList2;
	}
	
	@RequestMapping(value = "/queryByParent", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray queryByParent(@RequestParam("parent") String parent) {

		List<Category> category=null;
		JSONArray userJSONList2 =null;
		try {
			category = categoryImpl.queryByParent(parent);
			System.out.println(category.size());
			userJSONList2 = JSONArray.fromObject(category);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}

		return userJSONList2;
	}
}
