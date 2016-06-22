package com.gis.wx.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.gis.wx.model.AccessToken;
import com.gis.wx.model.Ticket;
import com.gis.wx.model.WXType;

import net.sf.json.JSONObject;

public class WXUtil {	
	private static String tokenURL ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx2d084b04f3af79da&secret=6582cbff0cb81adefcd77cc766fc5f15";
	private static String ticketURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
	public static Map<String,Long> saveParamMap = new HashMap<String,Long>();
	public static Map<String,String> savaToken = new HashMap<String,String>();
	public static Map<String,String> savaTicket = new HashMap<String,String>();
	private static long expiresTime = 7000*1000;
	private static int i = 0;
	public static String getToken() {
		String token = null;
		if(isExpires(WXType.TOKEN)){
			JSONObject  atJson = JSONObject.fromObject(getResultByUrl(tokenURL));
			AccessToken accessToken = (AccessToken) JSONObject.toBean(atJson, AccessToken.class);
			token = accessToken.getAccess_token();
			if(token!=null){
				saveParamMap.put(WXType.TOKEN, System.currentTimeMillis());
				savaToken.put(WXType.TOKEN,token);
			}else{
				JSONObject  atJson2 = JSONObject.fromObject(getResultByUrl(tokenURL));
				AccessToken accessToken2 = (AccessToken) JSONObject.toBean(atJson, AccessToken.class);
				token = accessToken2.getAccess_token();
			}
		}else{
			token= savaToken.get(WXType.TOKEN);
		}
		i++;
		System.out.println(i);
		System.out.println("token:"+token);
		return token;
	}
	
	private static boolean isExpires(String type){
		boolean isExpire = true;
		if(saveParamMap.containsKey(type)){
			long getTime = saveParamMap.get(type);
			long currentTime = System.currentTimeMillis();
			long timeDifference = currentTime - getTime;
			if(timeDifference>=expiresTime){
				isExpire = true;
			}else{
				isExpire = false;
			}
		}
		return isExpire;
	}
	
	public static String getSignature() {
		String ticketStr = null;
		if(isExpires(WXType.TICKET)){//过期了
			String token = getToken();
			if(token != null){
				String ticketTempURL = String.format(ticketURL, token);
				JSONObject  ticketJson = JSONObject.fromObject(getResultByUrl(ticketTempURL));
				if(ticketJson.getString("errmsg").equals("ok")){
					Ticket ticket = (Ticket) JSONObject.toBean(ticketJson, Ticket.class);
					ticketStr = ticket.getTicket();
					if(ticketStr!=null){
						saveParamMap.put(WXType.TICKET, System.currentTimeMillis());
						savaTicket.put(WXType.TICKET,ticketStr);
					}else{
						Ticket ticket2 = (Ticket) JSONObject.toBean(ticketJson, Ticket.class);
						ticketStr = ticket2.getTicket();
					}
				}
			}else{
				getToken();
			}
		}else{
			String token=getToken();
			ticketStr= savaTicket.get(ticketStr);
			if(ticketStr==null){
				String ticketTempURL = String.format(ticketURL, token);
				JSONObject  ticketJson = JSONObject.fromObject(getResultByUrl(ticketTempURL));
				if(ticketJson.getString("errmsg").equals("ok")){
					Ticket ticket = (Ticket) JSONObject.toBean(ticketJson, Ticket.class);
					ticketStr = ticket.getTicket();
					if(ticketStr!=null){
						saveParamMap.put(WXType.TICKET, System.currentTimeMillis());
						savaTicket.put(WXType.TICKET,ticketStr);
					}else{
						Ticket ticket2 = (Ticket) JSONObject.toBean(ticketJson, Ticket.class);
						ticketStr = ticket2.getTicket();
					}
				}
			}
		}

		System.out.println(i);
		System.out.println("ticketStr:"+ticketStr);
		return ticketStr;
	}
	
	private static  String getResultByUrl(String urlstr){
		StringBuffer sbf  = null;
		try {
			URL url = new URL(urlstr);
			 HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	         connection.setDoInput(true);
	         connection.setDoOutput(true);
	         connection.setRequestMethod("GET");
	         connection.setUseCaches(false);
	         connection.setInstanceFollowRedirects(true);
	         connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	         connection.connect();
	         BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	         String lines;
	         sbf  = new StringBuffer();
	          while ((lines = reader.readLine()) != null) {
	                 lines = new String(lines.getBytes(), "utf-8");
	                 sbf.append(lines);
	             }
	             reader.close();
	             connection.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return sbf.toString();
	}
}
