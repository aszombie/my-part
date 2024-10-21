package com.finalproject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.demo.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{
	
		Member findByMemberEmail(String memberEmail);
		Member findByMemberId(int memberId);
	

}
