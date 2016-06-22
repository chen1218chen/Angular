package com.gis.wx.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 证书信任管理器（用于https请求）
 * 
 * @author xinz
 * @date 2015-10-14
 */
public class MyX509TrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

 //微信返回参数的一个POJO类：
 
  private String  openid;  //用户的唯一标识 
  private String  nickname;//用户昵称 
  private Integer sex;// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 
  private String  province;//用户个人资料填写的省份 
  private String  city;//普通用户个人资料填写的城市 
  private String  country;// 国家，如中国为CN 
  private String  headimgurl;  // 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 
  private String  privilege;// 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom） 
  private String  unionid;// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制） 
  private String access_token;
 
// 授权凭证验证的类：
 
private String errcode;
private String errmsg;
// 通过code换取网页授权access_token
 
	//private String access_token;
	private String expires_in;
	private String refresh_token;
	//private String openid;
	private String scope;
	//private String unionid;
 //关于微信头像的，获取的是一个http的url，则需要将图片下载到服务器存储，然后获得相对路径：
 
/**
	 * 使用url或者http存入文件
	 * @Title: fileUpload
	 * @param @param fileUrl  文件url，可以是http
	 * @param @param path     文件存储路径
	 * @return void
	 * @throws xinz
	 */
	public static void fileUpload (String fileUrl,String path){
		 //读取文件
		  String s1 = fileUrl;   
		  java.io.InputStream is = null; //定义一个输入流。
		  BufferedInputStream bis = null;//定义一个带缓冲的输入流 。 
		//写到本地 
		  BufferedOutputStream bos = null; //定义一个带缓冲的输出流。
		  try{ 
			java.net.URL url = new java.net.URL(s1);//创建一个URL对象。
		  	is = url.openStream();//打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream。
		  	bis = new java.io.BufferedInputStream(is);     
		    File file = new File(path);   
			if(!file.exists()){ //测试此抽象路径名表示的文件或目录是否存在。  
				file.createNewFile();   //创建此抽象路径名表示的文件或目录。
			}   
		  bos = new BufferedOutputStream(new FileOutputStream(file));;     
		  byte[] b = new byte[1024]; //创建字节数组。
		  while(bis.read(b)!=-1){//输入流中的数据如果还有下一行(!=-1)将继续循环
			  bos.write(b);//将字节数组写入输出流。    
		  } 
		  }catch(Exception   e){     
			  System.out.println(e.toString());       
		  }finally{     
			  try{     
				  bos.flush();//刷新此缓冲的输出流。 
				  bis.close(); //关闭此输入流 。 
			  }catch(Exception   e){     
				  System.out.println(e.toString());       
			  }     
		  }  
	}
}
 