package com.city.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.city.model.Category;
import com.city.model.Uploadinfo;
import com.city.service.IItemService;
import com.city.util.Common;

@Controller
@RequestMapping("/Uploadinfo")
public class ItemRest {

	@Resource
	private IItemService itemServiceImpl;

	@RequestMapping(value = "/queryAllinfo", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray queryAllItem() {
		List<Uploadinfo> itemList = itemServiceImpl.queryAll();
		JSONArray itemJSONList = JSONArray.fromObject(itemList, Common.getJsonConfig());
		return itemJSONList;
	}
	
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray queryAll() {
		List<Uploadinfo> itemList = itemServiceImpl.queryAll();
		JSONArray jsonArr = new JSONArray();
		for(int i=0;i<itemList.size();i++){
			JSONObject obj  = new JSONObject();
			obj.put("id", itemList.get(i).getId());
			obj.put("content", itemList.get(i).getContent());
			obj.put("telephone", itemList.get(i).getTelephone());
			obj.put("name", itemList.get(i).getName());
			obj.put("remessage", itemList.get(i).getRemessage());
			obj.put("picturepath1", itemList.get(i).getPicturepath1());
			obj.put("picturepath2", itemList.get(i).getPicturepath2());
			String date = Common.dateFormatStr(itemList.get(i).getDataTime()); 
			
			obj.put("dataTime", date);
			obj.put("cname", itemList.get(i).getClassfic().getCname());
			jsonArr.add(obj);
		}
		return jsonArr;
	}
	
	@RequestMapping(value = "/saveClassfi", method = RequestMethod.POST)
	@ResponseBody
	public boolean saveClassfi(@RequestParam("id") int id, @RequestParam("ids") int[] ids) {
	
		for(int i=0;i<ids.length;i++){
			Uploadinfo info = itemServiceImpl.queryById(ids[i]);
			info.setId(ids[i]);
			Category category = new Category();
			category.setCid(id);
			info.setClassfic(category);
			itemServiceImpl.update(info);
		}
		return true;
	}
	
	@RequestMapping(value = "/searchDate", method = RequestMethod.POST)
	@ResponseBody
	public List<Uploadinfo> searchDate(@RequestParam("start") String start,@RequestParam("end") String end) {
		
		List<Uploadinfo> itemList = itemServiceImpl.dateRange(start,end);
		return itemList;
	}
	
	@RequestMapping(value = "/onePageData", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray onePageData(@RequestParam("pageNow") String pageNow, @RequestParam("pageSize") int pageSize, @RequestParam("type") String type) {
	
		List<Uploadinfo> itemList = itemServiceImpl.onePage(Integer.parseInt(pageNow), pageSize,type);
		JSONArray itemJSONList = JSONArray.fromObject(itemList, Common.getJsonConfig());
		return itemJSONList;
	}

	@RequestMapping(value = "/totlePages", method = RequestMethod.POST)
	@ResponseBody
	public int totlePages(@RequestParam("type") String type) {
		List<Uploadinfo> itemList = itemServiceImpl.totalPage(type);
		int size = itemList.size();
		return size;
	}

	@RequestMapping(value = "/searchInfos", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray searchInfos(@RequestParam("value") String value) {
		//System.out.print(value);
		List<Uploadinfo> itemList = itemServiceImpl.searchInfos(value);
		JSONArray itemJSONList = JSONArray.fromObject(itemList, Common.getJsonConfig());
		return itemJSONList;
	}
	
	@RequestMapping(value = "/getInfo", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getInfo() {
		List<Uploadinfo> itemList = itemServiceImpl.queryAll();
		JSONArray itemJSONList = JSONArray.fromObject(itemList, Common.getJsonConfig());
		Map<String, JSONArray> model = new HashMap<String, JSONArray>();
		model.put("infolist", itemJSONList);
		return new ModelAndView("search.jsp", model);
	}

	@RequestMapping(value = "/queryByID", method = RequestMethod.POST)
	@ResponseBody
	public Uploadinfo queryByID(@RequestParam("id") int id) {
		Uploadinfo info = itemServiceImpl.queryById(id);
		return info;
	}
	
	@RequestMapping(value = "/classfiSearch", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray classfiSearch(@RequestParam("id") int id) {
		List<Uploadinfo> itemList = itemServiceImpl.queryClassfic(id);
		JSONArray itemJSONList = JSONArray.fromObject(itemList, Common.getJsonConfig());
		return itemJSONList;
	}


	@RequestMapping(value = "/insertremessage", method = RequestMethod.POST)
	@ResponseBody
	public String insertRetrunMessage(@RequestParam("id") int id,@RequestParam("returnmessage") String returnmessage) {

		Uploadinfo uploadinfo = itemServiceImpl.queryById(id);
		uploadinfo.setId(id);
		uploadinfo.setRemessage(returnmessage);
		System.out.println(id);

		try{
		itemServiceImpl.update(uploadinfo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "falure";
		}

        return "success";
		
	}


	@RequestMapping(value = "/delInfo", method = RequestMethod.POST)
	@ResponseBody
	public boolean delInfo(@RequestParam("id") int id) {
		System.out.println(id);
		Uploadinfo Uploadinfo = new Uploadinfo();
		try {
			
			Uploadinfo.setId(id);
			itemServiceImpl.delete(Uploadinfo);
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@RequestMapping(value = "/delInfos", method = RequestMethod.POST)
	@ResponseBody
	public boolean delInfos(@RequestParam("ids") int[] ids) {
		System.out.println(ids);
		Uploadinfo Uploadinfo = new Uploadinfo();
		try {
			for(int i=0;i<ids.length;i++){
				Uploadinfo.setId(ids[i]);
				itemServiceImpl.delete(Uploadinfo);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
