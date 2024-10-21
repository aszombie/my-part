package com.finalproject.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "memberDetail")
public class MemberDetail {
	@Id
	@Column(name = "memberId")
	private Integer id;
	private String name;
	private String gender;
	private String phone;
	@Lob
	@Column(name = "imageFile")
	private byte[] imageFile;
	private String level;
	private String nation;
	private String address;
	@JoinColumn(name="memberId")
	@OneToOne(fetch=FetchType.LAZY)
	private Member member;
	
	
}
