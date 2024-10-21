package com.finalproject.demo.util;

import java.security.SecureRandom;

public class RandomCodeUtil {

	private static final  String characters="ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz0123456789";

	private static final int codeLength=6;
	
	public String createRandomCode() {
		SecureRandom random=new SecureRandom();
		StringBuilder randomCode=new StringBuilder(codeLength);
		
		for (int i=0;i<codeLength;i++) {
			int index=random.nextInt(characters.length());
			randomCode.append(characters.charAt(index));
		}
		return randomCode.toString();
	}
}
