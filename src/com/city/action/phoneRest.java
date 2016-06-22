package com.city.action;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.city.model.Category;
import com.city.model.Uploadinfo;
import com.city.service.IItemService;
import com.city.util.Common;
import com.gis.wx.utils.WXUtil;
@Controller
@RequestMapping("/Uploadinfo")
public class phoneRest {
	public MyTask mytask;
	public MyTask mytask2;
	public Thread thread;
	public Thread thread2;
	@Resource
	private IItemService itemServiceImpl;

	@RequestMapping(value = "/Uploadphoneinfo", method = RequestMethod.POST)
	@ResponseBody
	public String  Uploadphoneinfo(@RequestParam String telephone,@RequestParam String name,
			@RequestParam String content,@RequestParam String dataTime,@RequestParam String picturepath1,@RequestParam String picturepath2,@RequestParam String jingdu,@RequestParam String weidu,@RequestParam String address,@RequestParam String wentidizhi) throws Exception {
		Uploadinfo uploadinfo = new Uploadinfo();
		uploadinfo.setId(1);
    
		uploadinfo.setTelephone(telephone);
		uploadinfo.setName(name);
		uploadinfo.setContent(content);
		uploadinfo.setDataTime(Common.toDateBase("yyyy-MM-dd HH:mm:ss", dataTime));
		
		if(!"".equals(picturepath1) && !"undefined".equals(picturepath1)){
			uploadinfo.setPicturepath1("http://xawx.xacg.gov.cn//picture/"+picturepath1+".jpg");
			
		}
		else{
			uploadinfo.setPicturepath1("picture/meizhaopian.png");
		}
		
		if(!"".equals(picturepath2) && !"undefined".equals(picturepath2)){
			uploadinfo.setPicturepath2("http://xawx.xacg.gov.cn//picture/"+picturepath2+".jpg");
			
		}
		else{
			uploadinfo.setPicturepath2("picture/meizhaopian.png");
		}
		Category classfic = new Category();
		classfic.setCid(42);
		uploadinfo.setClassfic(classfic);
		
		uploadinfo.setLon(jingdu);
		uploadinfo.setLat(weidu);
		uploadinfo.setWentidizhi(wentidizhi);
		
		System.out.println(wentidizhi);
		
		uploadinfo.setAddress(address);
		try{
		itemServiceImpl.insert(uploadinfo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "falure";
		}
		if(picturepath1!="undefined"){
			mytask = new MyTask(WXUtil.getToken(), picturepath1);
			thread = new Thread(mytask);
			thread.start();
		}
		
		if(picturepath2!="undefined"){
			mytask2 = new MyTask(WXUtil.getToken(), picturepath2);
			thread2 = new Thread(mytask2);
			thread2.start();
		}
		
        return "success";
	}
	
	class MyTask implements Runnable{
		String token;
		String picName;
		public MyTask(String token,String picName){
			this.token=token;
			this.picName=picName;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
			//鑸ぉ鍩庢湰鍦板瓨鏀惧浘鐗囧湴鍧�
			downLoadFromUrl(WXUtil.getToken(),picName,picName,"D:\\apache-tomcat-7.0.63\\webapps\\picture\\");
			//鍩庣鏈湴瀛樻斁鍥剧墖鍦板潃
			//downLoadFromUrl(WXUtil.getToken(),picName,picName,"D:\\鏁板瓧閫歕\xawx\\apache-tomcat-7.0.63\\webapps\\picture\\");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
		
		
	}

	
	/** 
     * 浠庣綉缁淯rl涓笅杞芥枃浠� 
     * @param urlStr 
     * @param fileName 
     * @param savePath 
     * @throws IOException 
     */  
    public static void  downLoadFromUrl(String token,String media_id,String fileName,String savePath) throws IOException{  
        String baseUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get";
    	URL url = new URL(baseUrl+"?access_token="+token+"&media_id="+media_id);    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
                //璁剧疆瓒呮椂闂翠负3绉�  
        conn.setConnectTimeout(3*1000);  
        //闃叉灞忚斀绋嬪簭鎶撳彇鑰岃繑鍥�403閿欒  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
  
        //寰楀埌杈撳叆娴�  
        InputStream inputStream = conn.getInputStream();    
        //鑾峰彇鑷繁鏁扮粍  
        byte[] getData = readInputStream(inputStream);      
  
        //鏂囦欢淇濆瓨浣嶇疆  
        File saveDir = new File(savePath);  
        if(!saveDir.exists()){  
            saveDir.mkdir();  
        }  
        File file = new File(saveDir+File.separator+fileName+".jpg");      
        FileOutputStream fos = new FileOutputStream(file);       
        fos.write(getData);   
        if(fos!=null){  
            fos.close();    
        }  
        if(inputStream!=null){  
            inputStream.close();  
        }  
  
  
        System.out.println("info:"+url+" download success");   
  
    }  
	
	
    /** 
     * 浠庤緭鍏ユ祦涓幏鍙栧瓧鑺傛暟缁� 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }    
  
	

	

}