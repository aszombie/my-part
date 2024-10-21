package com.finalproject.demo.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.demo.model.Member;
import com.finalproject.demo.model.MemberDetail;
import com.finalproject.demo.repository.MemberRepository;
import com.finalproject.demo.service.MemberService;
import com.finalproject.demo.service.sendEmailService;
import com.finalproject.demo.util.JsonWebTokenUtility;
import com.finalproject.demo.util.RandomCodeUtil;

import io.jsonwebtoken.ExpiredJwtException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private JsonWebTokenUtility jsonWebTokenUtility;
	@Autowired
	    private sendEmailService sendEmailService;
	
	@PostMapping("/register")
		String register(@RequestBody String json) throws JSONException, IOException {
		JSONObject responseJson=new JSONObject();	
		JSONObject obj=new JSONObject(json);
		String memberEmail=obj.isNull("memberEmail") ? null :obj.getString("memberEmail");
		String password=obj.isNull("password") ? null :obj.getString("password");
		boolean emailExist=memberService.checkMemberEmailExist(memberEmail);
			if(!emailExist) {
				memberService.addMember(memberEmail, password);
				Member member=memberRepo.findByMemberEmail(memberEmail);
				memberService.addDetail(member.getMemberId(), (memberEmail.split("@"))[0]);
				responseJson.put("success", true);
				MemberDetail memberDetail=memberService.getDetail(member.getMemberId());
				JSONObject detail=new JSONObject()
						.put("id",member.getMemberId())
						.put("name","")
						.put("gender","")
						.put("phone","")
						.put("level",(memberDetail.getLevel()).equals(null) ? "":memberDetail.getLevel())
						.put("nation","")
						.put("address","");
				String token=jsonWebTokenUtility.createToken(detail.toString(), null);
				String memberInfo=jsonWebTokenUtility.validateToken(token);
				responseJson.put("memberInfo", memberInfo);
				return responseJson.toString();
			}
			responseJson.put("success",false);
			responseJson.put("message","此帳號已經有人使用");
			return responseJson.toString();
		}
	@PostMapping("/login")
	public String login(@RequestBody String json) throws JSONException  {
		JSONObject memberResponseJson=new JSONObject();
		
		JSONObject obj=new JSONObject(json);
		String memberEmail=obj.isNull("memberEmail") ? null :obj.getString("memberEmail");
		String password=obj.isNull("password") ? null :obj.getString("password");
		
		if (memberEmail==null||memberEmail.length()==0||password==null||password.length()==0) {
			memberResponseJson.put("success", false);
			memberResponseJson.put("message", "輸入帳號與密碼");
			return memberResponseJson.toString();
		}
		
		Member member = memberService.checklogin(memberEmail, password);
		if (member==null) {
			memberResponseJson.put("success", false);
			memberResponseJson.put("message", "帳號或密碼錯誤");
		}else {
		memberResponseJson.put("success", true);
		memberResponseJson.put("message", "登入成功");
		
		MemberDetail memberDetail=memberService.getDetail(member.getMemberId());
		String memberPhoto=Base64.getEncoder().encodeToString(memberDetail.getImageFile());
		JSONObject detail=new JSONObject()
				.put("id",(memberDetail.getId()).equals(null) ? "":memberDetail.getId())
				.put("name",(memberDetail.getName()).equals(null) ? "":memberDetail.getName())
				.put("gender",(memberDetail.getGender()).equals(null) ? "":memberDetail.getGender())
				.put("level",(memberDetail.getLevel()).equals(null) ? "":memberDetail.getLevel())
				.put("phone",(memberDetail.getPhone()).equals(null) ? "":memberDetail.getPhone())
				.put("image",(memberDetail.getImageFile()).equals(null) ? "":memberPhoto)
				.put("nation",(memberDetail.getNation()).equals(null) ? "":memberDetail.getNation())
				.put("address",(memberDetail.getAddress()).equals(null) ? "":memberDetail.getAddress());
		String token=jsonWebTokenUtility.createToken(detail.toString(), null);
		String memberInfo=jsonWebTokenUtility.validateToken(token);
		memberResponseJson.put("memberInfo", memberInfo);
		memberResponseJson.put("memberEmail", memberEmail);		
		}
		return memberResponseJson.toString();
	}
	
		@PutMapping("/updateDetail/{id}")
		public String memberDetailUpdate(@PathVariable("id") Integer id,@RequestBody String json) throws JSONException {//將更新圖片與個人資料分開	
			JSONObject detailUpdateObj=new JSONObject(json);
			JSONObject responseJson=new JSONObject();
			String name=detailUpdateObj.isNull("name") ? null :detailUpdateObj.getString("name");
			String gender=detailUpdateObj.isNull("gender") ? null :detailUpdateObj.getString("gender");
			String phone=detailUpdateObj.isNull("phone") ? null :detailUpdateObj.getString("phone");
			String level=detailUpdateObj.isNull("level") ? null :detailUpdateObj.getString("level");
			String nation=detailUpdateObj.isNull("nation") ? null :detailUpdateObj.getString("nation");
			String address=detailUpdateObj.isNull("address") ? null :detailUpdateObj.getString("address");
			if(!name.equals(null)&&!gender.equals(null)&&!phone.equals(null)&&!level.equals(null)&&!nation.equals(null)&&!address.equals(null)) {
				memberService.updateDetail(id,name, gender, phone, level,nation,address);
				responseJson.put("success", true);
				responseJson.put("message", "資料修改成功");
				MemberDetail memberDetail=memberService.getDetail(id);
				String memberPhoto=Base64.getEncoder().encodeToString(memberDetail.getImageFile());
				JSONObject detail=new JSONObject()
						.put("id",(memberDetail.getId()).equals(null) ? "":memberDetail.getId())
						.put("name",(memberDetail.getName()).equals(null) ? "":memberDetail.getName())
						.put("gender",(memberDetail.getGender()).equals(null) ? "":memberDetail.getGender())
						.put("level",(memberDetail.getLevel()).equals(null) ? "":memberDetail.getLevel())
						.put("phone",(memberDetail.getPhone()).equals(null) ? "":memberDetail.getPhone())
						.put("image",(memberDetail.getImageFile()).equals(null) ? "":memberPhoto)
						.put("nation",(memberDetail.getNation()).equals(null) ? "":memberDetail.getNation())
						.put("address",(memberDetail.getAddress()).equals(null) ? "":memberDetail.getAddress());
				String token=jsonWebTokenUtility.createToken(detail.toString(), null);
				String memberInfo=jsonWebTokenUtility.validateToken(token);
				responseJson.put("memberInfo", memberInfo);
				return responseJson.toString();
				
			}else {
				responseJson.put("success", false);
				responseJson.put("message", "個人資料不得為空");
				return responseJson.toString();
			}
		}
		@PostMapping("/findMember/{id}")
		public String findMember(@PathVariable("id") Integer id) throws JSONException {//將更新圖片與個人資料分開	
			JSONObject responseJson=new JSONObject();
			if(memberService.checkMemberIdExist(id)) {
				MemberDetail memberDetail=memberService.getDetail(id);
				responseJson.put("id",(memberDetail.getId()).equals(null) ? "":memberDetail.getId())
						.put("name",(memberDetail.getName()).equals(null) ? "":memberDetail.getName())
						.put("gender",(memberDetail.getGender()).equals(null) ? "":memberDetail.getGender())
						.put("level",(memberDetail.getLevel()).equals(null) ? "":memberDetail.getLevel())
						.put("phone",(memberDetail.getPhone()).equals(null) ? "":memberDetail.getPhone())
						.put("nation",(memberDetail.getNation()).equals(null) ? "":memberDetail.getNation())
						.put("address",(memberDetail.getAddress()).equals(null) ? "":memberDetail.getAddress());
				return responseJson.toString();
			}else {
				responseJson.put("success", false);
				responseJson.put("message", "查無此會員");
				return responseJson.toString();
			}
		}
		@PutMapping("/updateUserPhoto/{id}")
		public String memberPhotoUpdate(@PathVariable("id") Integer id,@RequestBody String json) throws JSONException {//將更新圖片與個人資料分開	
			JSONObject detailUpdateObj=new JSONObject(json);
			JSONObject responseJson=new JSONObject();
			byte[] imageFile=detailUpdateObj.isNull("imageFile") ? null :(memberService.decodePhoto((detailUpdateObj.getString("imageFile"))));
			if(!imageFile.equals(null)) {
				memberService.updatePhoto(id,imageFile);
				responseJson.put("success", true);
				responseJson.put("message", "資料修改成功");
				MemberDetail memberDetail=memberService.getDetail(id);
				String memberPhoto=Base64.getEncoder().encodeToString(memberDetail.getImageFile());
				JSONObject detail=new JSONObject()
						.put("id",(memberDetail.getId()).equals(null) ? "":memberDetail.getId())
						.put("name",(memberDetail.getName()).equals(null) ? "":memberDetail.getName())
						.put("gender",(memberDetail.getGender()).equals(null) ? "":memberDetail.getGender())
						.put("level",(memberDetail.getLevel()).equals(null) ? "":memberDetail.getLevel())
						.put("phone",(memberDetail.getPhone()).equals(null) ? "":memberDetail.getPhone())
						.put("image",(memberDetail.getImageFile()).equals(null) ? "":memberPhoto)
						.put("nation",(memberDetail.getNation()).equals(null) ? "":memberDetail.getNation())
						.put("address",(memberDetail.getAddress()).equals(null) ? "":memberDetail.getAddress());
				String token=jsonWebTokenUtility.createToken(detail.toString(), null);
				String memberInfo=jsonWebTokenUtility.validateToken(token);
				responseJson.put("memberInfo", memberInfo);
				return responseJson.toString();
				
			}else {
				responseJson.put("success", false);
				responseJson.put("message", "個人資料不得為空");
				return responseJson.toString();
			}
		}
		

		@PostMapping("/resetPassword")
		public String resetPassword(@RequestBody String json) throws JSONException {			
			JSONObject requestJson=new JSONObject(json);
			JSONObject responseJson=new JSONObject();
			String memberEmail=requestJson.getString("memberEmail");
			boolean emailExist=memberService.checkMemberEmailExist(requestJson.getString("memberEmail"));
			if(emailExist) {
				RandomCodeUtil randomCodeUtil=new RandomCodeUtil();
				String randomCode=randomCodeUtil.createRandomCode();
				JSONObject detail=new JSONObject()
						.put("success", true)
						.put("memberId",memberRepo.findByMemberEmail(memberEmail).getMemberId())
						.put("randomCode", randomCode)
						.put("memberEmail",(memberEmail==null ? "":memberEmail));
				String token=jsonWebTokenUtility.createResetPassWordToken(detail.toString(), null);
				responseJson.put("success", true);
				responseJson.put("memberEmail",memberEmail );			
				responseJson.put("token",token);
				return responseJson.toString();
			}
			responseJson.put("success", false);
			responseJson.put("message", "查無此帳號");
			return responseJson.toString();
		}
		@PostMapping("/checkRamdonCode")
		public String checkRamdonCode(@RequestBody String json) throws JSONException {
			JSONObject requestJson=new JSONObject(json);
			String randomCode=requestJson.getString("randomCode");
		    JSONObject responseJson = new JSONObject();
		    if (jsonWebTokenUtility.validateToken(requestJson.getString("token")) == null) {
		    	responseJson.put("expired", true);
		    	responseJson.put("message", "重設密碼請求已超時");
		    	return responseJson.toString();
		    }
		    JSONObject requestToken=new JSONObject(jsonWebTokenUtility.validateToken(requestJson.getString("token")));
		    JSONObject memberEmail=new JSONObject()
		    		.put("memberEmail",(requestToken.get("memberEmail")));
		    String memberEmailToken=jsonWebTokenUtility.createResetPassWordToken(memberEmail.toString(), null);
		    if (randomCode.equals(requestToken.getString("randomCode"))) {
		        responseJson.put("expired", false);
		        responseJson.put("token", memberEmailToken);
		    } else {
		        responseJson.put("expired", true);
		        responseJson.put("message", "重設密碼請求已超時");
		    }
		    return responseJson.toString();
		}
		@PostMapping("/resetPasswordConfirm")
		public String resetPwd(@RequestBody String json) throws JSONException,ExpiredJwtException {
			JSONObject requestJson=new JSONObject(json);
			JSONObject responseJson=new JSONObject();
			 String validationResponse = jsonWebTokenUtility.validateToken(requestJson.getString("token"));
			 if (validationResponse == null) {
		        responseJson.put("expired", true);
		        responseJson.put("message", "重設密碼請求已超時");
		        return responseJson.toString();
		    }
			 JSONObject resetTokenDetail = new JSONObject(validationResponse);
			memberService.resetPassWord(resetTokenDetail.getString("memberEmail"), requestJson.getString("Password"));   
			if(memberService.checkPwdEqual(requestJson.getString("Password"), memberRepo.findByMemberEmail(resetTokenDetail.getString("memberEmail")).getPwd())) {
				responseJson.put("success", true);
				responseJson.put("message", "密碼變更成功");
				return responseJson.toString();
			}
			responseJson.put("success", false);
			responseJson.put("message", "密碼變更失敗");
			return responseJson.toString();
		}
		 @PostMapping("/sendEmail")
		    public String sendEmail(@RequestBody String json) throws JSONException {
			 JSONObject requestJson=new JSONObject(json);
		        String to = requestJson.getString("memberEmail");
		        String subject = "LuxeNest重設密碼請求";
		        String randomCode =requestJson.getString("randomCode");
		        String body="您的密碼重設驗證碼為\n"+randomCode+"\n如果您沒提除重設密碼的請求，請忽略這封郵件";
		        sendEmailService.sendEmail(to, subject, body);
		        return "Email sent successfully";
		    }

		 @PostMapping("/registerCheck")
			public String registerCheck(@RequestBody String json) throws JSONException {			
				JSONObject requestJson=new JSONObject(json);
				JSONObject responseJson=new JSONObject();
				boolean emailExist=memberService.checkMemberEmailExist(requestJson.getString("memberEmail"));
				if(!emailExist) {
					RandomCodeUtil randomCodeUtil=new RandomCodeUtil();
					String randomCode=randomCodeUtil.createRandomCode();
					String to = requestJson.getString("memberEmail");
					String subject = "LuxeNest帳號註冊驗證碼";
					String body="您的LuxeNest帳號註冊驗證碼為\n"+randomCode+"\n如果您沒提出註冊LuxeNest帳號的請求，請忽略這封郵件";
					sendEmailService.sendEmail(to, subject, body);
					responseJson.put("success", true);
					responseJson.put("memberEmail", requestJson.getString("memberEmail"));
					responseJson.put("password", requestJson.getString("password"));
					responseJson.put("randomCode", randomCode);
					return responseJson.toString();
				}
				responseJson.put("success", false);
				responseJson.put("message", "此帳號已經有人使用");
				return responseJson.toString();
			}

    @PostMapping("/find")
		public String findById(@RequestBody String json) throws JSONException {
			JSONObject member = new JSONObject(json);
			JSONObject responseJSON = new JSONObject();
		    String memberId = member.getString("memberId");
		    
		    if (memberId == null) {
		    	responseJSON.put("success", false);
		    	responseJSON.put("message", "找不到會員資料");
		        return responseJSON.toString();
		    }
		    MemberDetail memberDetail = memberService.getDetail(Integer.valueOf(memberId));
		    String memberPhoto=Base64.getEncoder().encodeToString(memberDetail.getImageFile());
		    System.out.println(memberPhoto);
		    if (memberDetail != null) {
		    	JSONObject detail=new JSONObject()
						.put("id",(memberDetail.getId()).equals(null) ? "":memberDetail.getId())
						.put("name",(memberDetail.getName()).equals(null) ? "":memberDetail.getName())
						.put("gender",(memberDetail.getGender()).equals(null) ? "":memberDetail.getGender())
						.put("level",(memberDetail.getLevel()).equals(null) ? "":memberDetail.getLevel())
						.put("phone",(memberDetail.getPhone()).equals(null) ? "":memberDetail.getPhone())
						.put("image",(memberDetail.getImageFile()).equals(null) ? "":memberPhoto)
						.put("nation",(memberDetail.getNation()).equals(null) ? "":memberDetail.getNation())
						.put("address",(memberDetail.getAddress()).equals(null) ? "":memberDetail.getAddress());
		        return detail.toString();
		    }
		    responseJSON.put("success", false);
	    	responseJSON.put("message", "找不到會員明細資料");
	        return responseJSON.toString();
		}
    @PostMapping("checkPassword")
    public String checkPassword(@RequestBody String json) throws JSONException  {
		JSONObject ResponseJson=new JSONObject();	
		JSONObject obj=new JSONObject(json);
		String memberEmail=obj.isNull("memberEmail") ? null :obj.getString("memberEmail");
		String password=obj.isNull("password") ? null :obj.getString("password");
		if (memberEmail==null||memberEmail.length()==0||password==null||password.length()==0) {
			ResponseJson.put("success", false);
			ResponseJson.put("message", "輸入密碼");
			return ResponseJson.toString();
		}
		JSONObject memberEmailJson=new JSONObject()
	    		.put("memberEmail",memberEmail);
		String token=jsonWebTokenUtility.createResetPassWordToken(memberEmailJson.toString(), null);
		Member member = memberService.checklogin(memberEmail, password);
		if (member==null) {
			ResponseJson.put("success", false);
			ResponseJson.put("message", "密碼錯誤");
		}else {
			
		ResponseJson.put("success", true);
		ResponseJson.put("token", token);
		ResponseJson.put("message", "登入成功");	
		}
		return ResponseJson.toString();
	}
    
    
    @PostMapping("/resetPasswordFromDetail")
	public String resetPasswordFromDetail(@RequestBody String json) throws JSONException,ExpiredJwtException {
		JSONObject requestJson=new JSONObject(json);
		JSONObject responseJson=new JSONObject();
		memberService.resetPassWord(requestJson.getString("memberEmail"), requestJson.getString("Password"));   
		if(memberService.checkPwdEqual(requestJson.getString("Password"), memberRepo.findByMemberEmail(requestJson.getString("memberEmail")).getPwd())) {
			responseJson.put("success", true);
			responseJson.put("message", "密碼變更成功");
			return responseJson.toString();
		}
		responseJson.put("success", false);
		responseJson.put("message", "密碼變更失敗");
		return responseJson.toString();
	}
}

