package com.finalproject.demo.service;



import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.finalproject.demo.model.Member;
import com.finalproject.demo.model.MemberDetail;
import com.finalproject.demo.repository.MemberDetailRepository;
import com.finalproject.demo.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private MemberDetailRepository memberDetailRepo;
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	public Member addMember(String memberEmail,String pwd) {
		String encodedPwd =pwdEncoder.encode(pwd);
		Member members=new Member();
		members.setMemberEmail(memberEmail);
		members.setPwd(encodedPwd);
		
		return memberRepo.save(members);
	}
	public MemberDetail addDetail(Integer id,String name) throws IOException {
		ClassPathResource defaultImg=new ClassPathResource("static/image/122.png");
		byte[] imgByte=defaultImg.getInputStream().readAllBytes();
		MemberDetail details=new MemberDetail();
		details.setId(id);
		details.setName(name);
		details.setGender("");
		details.setPhone("");
		details.setImageFile(imgByte);

		details.setLevel("一般會員");

		details.setNation("");
		details.setAddress("");
		return memberDetailRepo.save(details);
	}
	public boolean checkMemberEmailExist(String memberEmail) {
		Member member=memberRepo.findByMemberEmail(memberEmail);
		if(member!=null) {
			return true;
		}
		return false;
	}
	public boolean checkMemberIdExist(Integer id) {
		Member member=memberRepo.findByMemberId(id);
		if(member!=null) {
			return true;
		}
		return false;
	}
	public Member checklogin(String memberEmail,String password) {
			Member dbmember=memberRepo.findByMemberEmail(memberEmail);
			if (dbmember!=null) {
				String dbpwd=dbmember.getPwd();
				boolean result=pwdEncoder.matches(password, dbpwd);
				if (result) {
					return dbmember;
				}
			}return null;
	}
	public Member findMemberById(Integer id) {
		Optional<Member> optional=memberRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}return null;
	}
	public MemberDetail getDetail(Integer id) {
		Optional<MemberDetail> optional=memberDetailRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}return null;
	}
	public MemberDetail updateDetail(Integer id,String name,String gender,String phone,String level,String nation,String address) {
		Optional<MemberDetail> details=memberDetailRepo.findById(id);
		if(details.isPresent()) {
			MemberDetail updatedDetail=details.get();
			updatedDetail.setName(name);
			updatedDetail.setGender(gender);
			updatedDetail.setPhone(phone);
			updatedDetail.setLevel(level);
			updatedDetail.setNation(nation);
			updatedDetail.setAddress(address);
			return memberDetailRepo.save(updatedDetail);
		}else {
			return null;
		}
	}
	public MemberDetail updatePhoto(Integer id,byte[] imageFile) {
		Optional<MemberDetail> details=memberDetailRepo.findById(id);
		if(details.isPresent()) {
			MemberDetail updatedDetail=details.get();
			updatedDetail.setImageFile(imageFile);
			return memberDetailRepo.save(updatedDetail);
		}else {
			return null;
		}
	}
	public byte[] decodePhoto(String base64Photo) {
		base64Photo=base64Photo.substring(base64Photo.indexOf(",")+1);
		byte[] bytePhoto=Base64.getDecoder().decode(base64Photo);
		return bytePhoto;
	}
	public Member resetPassWord(String memberEmail,String PassWord) {
		Member member=memberRepo.findByMemberEmail(memberEmail);
		String encodedPwd =pwdEncoder.encode(PassWord);
		member.setPwd(encodedPwd);
		return memberRepo.save(member);
	}
	public boolean checkPwdEqual(String updatePWD,String DBPwd) {
			boolean result=pwdEncoder.matches(updatePWD, DBPwd);
			if (result) {
				return true;
			}
		return false;
	}
}

