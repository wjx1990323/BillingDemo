package com.test.billing.testcase;


import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.timevale.qa.apitest.init.SystemProperties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;

public class BaseInterface {
	public Logger logger = Logger.getLogger(getClass());
	public HashMap<String, String> dataMap = new HashMap<String, String>();
	public RequestSpecification commonRequest;
	public String environment;
	public String baseURI;
	public String basePath;

	@BeforeSuite
	public void beforesuite() {
		
		SystemProperties.initData("test");
		baseURI=SystemProperties.getPropValue("account_app");
		logger.info("baseURI:::::" + SystemProperties.getPropValue("account_app"));
	}

	@BeforeClass
	public void beforeClass() throws Exception {

	}





	@AfterClass
	public void afterClass() {

	}

	@AfterSuite
	public void aftersuite() {

	}


}
