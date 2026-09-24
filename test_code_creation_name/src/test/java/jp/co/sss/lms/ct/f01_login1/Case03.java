package jp.co.sss.lms.ct.f01_login1;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト ログイン機能①
 * ケース03
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース03 受講生 ログイン 正常系")
public class Case03 {

	String url = "http://localhost:8080/lms";

	String title = "ログイン | LMS";

	String id = "StudentAA02";

	String pass = "StudentAA022";

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// TODO ここに追加
		goTo(url);

		assertEquals(title, webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		final WebElement loginid = webDriver.findElement(By.name("loginId"));
		final WebElement password = webDriver.findElement(By.name("password"));

		final WebElement loginclick = webDriver.findElement(By.className("btn-primary"));

		loginid.clear();
		password.clear();

		loginid.sendKeys(id);
		password.sendKeys(pass);

		assertEquals("StudentAA02", id, "ログインIDが一致していません");
		assertEquals("StudentAA022", pass, "passwordが一致していません");

		loginclick.click();

		getEvidence(new Object() {
		});
	}

}
