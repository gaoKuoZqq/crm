package com.crm.util;

public class Test {
	@org.junit.Test
	public void a() {
		//System.out.println(MD5Util.EncodeUtf8("a"));
		System.out.println(MD5Util.EncodeUtf8AddSalt("1"));
		//System.out.println(MD5Util.MD5Encode("a", "utf-8"));
	}
}
