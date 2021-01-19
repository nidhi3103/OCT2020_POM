package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String ACCOUNTS_PAGE_HEADER = "Your Store";
	public static final int ACCOUNTS_PAGE_TOP_NAV_COUNT = 8;
	public static final String ACCOUNT_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "register";
	public static final String SEARCH_SHEET_NAME = "search";
	
	
	public static List<String> getAccTopNavList() {
		List<String> topNavList = new ArrayList<String>();
		topNavList.add("Desktops");
		topNavList.add("Laptops & Notebooks");
		topNavList.add("Components");
		topNavList.add("Tablets");
		topNavList.add("Software");
		topNavList.add("Phones & PDAs");
		topNavList.add("Cameras");
		topNavList.add("MP3 Players");
		return topNavList;
	}
}
