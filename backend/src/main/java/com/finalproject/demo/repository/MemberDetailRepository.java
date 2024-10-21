package com.finalproject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.demo.model.MemberDetail;

public interface MemberDetailRepository extends JpaRepository<MemberDetail, Integer> {

}
