package com.test.billing.testcase;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.timevale.qa.apitest.RestAssuredUtil;

import io.restassured.response.Response;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;

public class LoginCase extends BaseInterface {
	String cookieValue;

	@DataProvider(name = "logindataprovider")
	public Object[][] login() throws Exception {
		Object[][] testObjArray = { { "/pwdauth/login/mobile",
				"{\"principal\":\"13042159730\",\"credentials\":\"29ad0e3fd3db681fb9f8091c756313f7\"}" } };

		logger.info(testObjArray.length);
		return (testObjArray);

	}

	@Features("@Features计费模块的接口测试")
	@Stories("@Stories登陆")
	@Step("@Step登陆输入用户密码密码")
	@Test(dataProvider = "logindataprovider")
	public void testLoginCase(String path, String params) {

		String baseURI1 = "http://ttapi.tsign.cn/account-app";
		// 或者直接输入url
		// Response mResponse = RestAssuredUtil.sendPostAndReturnResponse(baseURI1,
		// path, params);
		Response mResponse = RestAssuredUtil.sendPostAndReturnRepSaveCookie(baseURI, path, params);
		logger.info("testLoginCaseNoExtract 提取的mResponse值：" + mResponse.asString());

		String message = mResponse.path("msg");
		logger.info("testLoginCaseNoExtract 提取的msg值：" + message);

		Assert.assertNull(message);
		String cookieValue = mResponse.cookie("SESSION");
		logger.info("testLoginCaseNoExtract 提取的cookie值：" + cookieValue);
	}

	@DataProvider(name = "getUserOrganListPro")
	public Object[][] getUserOrganList() throws Exception {
		Object[][] testObjArray = { { "/rest/company/getUserOrganList", "{}" } };

		return testObjArray;

	}

	@Features("@Features计费模块的接口测试")
	@Stories("@Stories获取关联企业信息")
	@Step("@Step不需要输入参数")
	@Test(dataProvider = "getUserOrganListPro")
	public void testgetUserOrganListCase(String path, String params) {
		Response mResponse = RestAssuredUtil.sendPostAndReturnRep(baseURI, path, params);
		logger.info("testgetUserOrganListCase 提取的mResponse值：" + mResponse.asString());
		Assert.assertEquals(mResponse.path("errCode"), 0);

	}

}
