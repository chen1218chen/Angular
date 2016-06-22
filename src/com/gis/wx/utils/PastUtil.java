package com.gis.wx.utils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

import net.sf.json.JSONObject;
 
 
public class PastUtil {
    public static JSONObject getSign(String url){
    	String jsapi_ticket = WXUtil.getSignature();
        return sign(jsapi_ticket, url);
    }
    public static JSONObject sign(String jsapi_ticket, String url) {
    	JSONObject ret = new JSONObject();
        String nonce_str = create_nonce_str();
        long timestamp = create_timestamp();
        String str;
        String signature = "";
 
        str = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
 
        return ret;
    }
 
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
 
    private static String create_nonce_str() {
        return UUID.randomUUID().toString().replace("-", "");
    }
 
    private static long create_timestamp() {
        return System.currentTimeMillis() / 1000;
    }
}