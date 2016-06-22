package com.city.action;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.city.model.WeixinUser;
import com.city.service.IWeixinUserService;
import com.gis.wx.model.AccessToken;
import com.gis.wx.model.WechatMsg;
import com.gis.wx.utils.WeixinUtil2;
import net.sf.json.JSONObject;
@RestController
@RequestMapping("/sign")
public class SignAction2 {
	@Resource
	private IWeixinUserService WeixinUserServiceImpl;
	private String AppID = "wxf54e15e03b40426e";
	private String AppSecret = "bb5f7a0a7e57b0d928ef76215d8f0e94";
	@RequestMapping("wechatOauthsnsapiuserinfo")
	public String wechatOauthinfo(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException  {
		/**
		 *  1 第一步：用户同意授权，获取code
		 */
		
		String loginUrl = "http://qq799771291.xicp.cn/UrbanManagement/rest/sign/getAccessTokeninfo";
		String url_encode = java.net.URLEncoder.encode(loginUrl, "utf-8");
		//用户授权，获取code
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
					+ "appid="+AppID+""
					+ "&redirect_uri="+url_encode+""
					+ "&response_type=code"
					+ "&scope=snsapi_base"
					+ "#wechat_redirect";
		//forward redirect
		return "redirect:"+url+""; 
	}
	@RequestMapping("getAccessTokeninfo")
	public String getAccessToken2(HttpServletRequest request,HttpServletResponse response,Model model) {
		String code = request.getParameter("code");
		String url = null;
		try {
		if(code!=null){
			url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
					+ "appid="+AppID+""
					+ "&secret="+AppSecret+""
					+ "&code="+code+""
					+ "&grant_type=authorization_code";
			String requestMethod = "GET";
			String outputStr = "";
			String httpRequest = WeixinUtil2.httpRequest(url, requestMethod, outputStr);
			
			System.out.println("通过code换取网页授权access_token="+httpRequest);
			JSONObject msg1 = JSONObject.fromObject(httpRequest);
			AccessToken accTok = (AccessToken) JSONObject.toBean(msg1, AccessToken.class);
	/*		String urlUser ="https://api.weixin.qq.com/cgi-bin/user/info?"
					+ "access_token="+accTok.getAccess_token()+""
					+ "&openid="+accTok.getOpenid()+""
					+ "&lang=zh_CN" ;                     */                                            
			String urlUser = "https://api.weixin.qq.com/sns/userinfo?"
					+ "access_token="+accTok.getAccess_token()+""
					+ "&openid="+accTok.getOpenid()+""
					+ "&lang=zh_CN";
			
			String httpUser = WeixinUtil2.httpRequest(urlUser, requestMethod, outputStr);
			System.out.println("拉取用户信息=="+httpUser);
			JSONObject msg2 = JSONObject.fromObject(httpUser);
			
			WeixinUser weixinUser = (WeixinUser) JSONObject.toBean(msg2, WeixinUser.class);
			weixinUser.setAccess_token(accTok.getAccess_token());
			/**
			 *  5 附：检验授权凭证（access_token）是否有效
			 */
			WechatMsg checkAccessToken = checkAccessToken(weixinUser.getAccess_token(), weixinUser.getOpenid());
			if(checkAccessToken.getErrcode().equals("0")){
				if(weixinUser.getAccess_token() != ""){
						List<WeixinUser> listweixinuser=WeixinUserServiceImpl.queryByOpenid(weixinUser.getOpenid());
						if(listweixinuser.size() != 0){
						 System.out.println(listweixinuser.get(0).getNickname()+"111");
						 
						 return "redirect:http://qq799771291.xicp.cn/UrbanManagement/phone.html";
						}
						else{
							WeixinUserServiceImpl.insert(weixinUser);
						}
				}
			}
			else{
					 this.wechatOauthinfo(request, response, model); 
				}
			}else{
				//如果access_token失效，则再次进行调用，并存储access_token值，access_token有效期为2个小时
				this.wechatOauthinfo(request, response, model); 
			}
	} catch (Exception e) {
		System.out.println("===拉取用户出错===");
		e.printStackTrace();
	}
	return "redirect:http://qq799771291.xicp.cn/UrbanManagement/phone.html";
	}
	/*
	*//**
	 * 微信关联用户
	 * @Title: saveWechatUser
	 * @param @param mobilePhone
	 * @param @param password
	 * @param @param validataCode
	 * @param @return
	 * @return String
	 * @throws xinz
	 *//*
	@RequestMapping("saveWechatUser")
	public String saveWechatUser(HttpServletResponse response,String mobilePhone,String password,String validataCode){
		//使用手机号来判断该手机是否在注册
		UserRegister userRegister = userService.findUserByPhone(mobilePhone);
		WechatUser wechatUser = (WechatUser)CurrentSession.getAttribute("wechatUser");
		WechatUser wechatU = new WechatUser();
		wechatU.setOpenid(wechatUser.getOpenid());
		List<WechatUser> findWechatUser = wechatUserService.findWechatUser(wechatU);
		if(findWechatUser.size()>0 && userRegister.getOpenid()!=null){
			CurrentSession.setAttribute("user", userRegister);
			return "redirect:/user/userCenter";
		}else{
			//如果没有注册，开始注册
			if(userRegister==null){
				Result<UserRegister> saveUserInfoApp = userRegisterService.saveUserInfoApp(mobilePhone, password, validataCode,wechatUser);
				if(saveUserInfoApp.getState()==1){
					//进行微信和用户的关联
					wechatUserService.saveWechatUser(wechatUser);
					CurrentSession.setAttribute("user", userRegister);
					return "redirect:/user/userCenter";
				}
			}else if(userRegister.getOpenid()==null || userRegister.getOpenid().equals("")){
			//否则，查询出用户信息，放入session中，关联微信，跳转到用户中心	
				UserRegister userReg = new UserRegister();
				userReg.setId(userRegister.getId());
				//存入微信openid
				userReg.setOpenid(wechatUser.getOpenid());
				userService.upUser(userReg);
				UserInfo user = new UserInfo();
				//存入微信头像
				//图片类型
				String dateStr =DateUtil.format(DateUtil.getCurrentDate(), "yyyyMMdd")  + "/";
				//图片类型
				String imgType = "JPG";
				//微信头像名称
				String app2DBarNameAndType = UuidUtil.getUUID()+"."+imgType;
				//微信头像路径
				String path =   ZhtxHelper.getApplicationResourcesProp("application","app.img.projectpath")+ SysConstant.GOODS2DBARPATH + dateStr;
				File file1 = new File(path);
				file1.mkdirs();
				//图片全路径
				String imgUrl = SysConstant.GOODS2DBARPATH + dateStr+app2DBarNameAndType;
				FileUtil.fileUpload(wechatUser.getHeadimgurl(), path);
				user.setRegisterId(userRegister.getId());
				user.setImageUrl(imgUrl);
				userInfoService.updateUserInfo(user);
				//存入微信用户
				wechatUserService.saveWechatUser(wechatUser);
				
				UserRegister userW = userService.findUserByPhone(mobilePhone);
				CurrentSession.setAttribute("user", userW);
				return "redirect:/user/userCenter";
			}else{
				CurrentSession.setAttribute("user", userRegister);
				return "redirect:/user/userCenter";
			}
		}
		return "redirect:/user/userCenter";
	}
	
	*//**
	 * 检验授权凭证（access_token）是否有效
	 * @Title: checkAccessToken
	 * @param @param access_token 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同 
	 * @param @param openid 用户的唯一标识 
	 * @return WechatMsg   返回消息实体
	 * @throws xinz
	 *//**/
	public static WechatMsg checkAccessToken(String access_token,String openid){
		 String requestMethod = "GET";
		 String outputStr = "";	
		 String url = "https://api.weixin.qq.com/sns/auth?"
		 		+ "access_token="+access_token+""
		 		+ "&openid="+openid+"";
		 String httpmsg = WeixinUtil2.httpRequest(url, requestMethod, outputStr);
		 System.out.println("拉取用户信息返回消息=="+httpmsg);
	  	JSONObject msg1 = JSONObject.fromObject(httpmsg);
		 WechatMsg msg = (WechatMsg) JSONObject.toBean(msg1, WechatMsg.class);
		 return msg;
	}
}
