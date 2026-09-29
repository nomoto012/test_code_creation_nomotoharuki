package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 結合テスト レポート機能
 * ケース08
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {

	String url = "http://localhost:8080/lms";

	String title = "ログイン | LMS";

	String courseTitle = "コース詳細 | LMS";

	String helptitle = "ヘルプ | LMS";

	String questiontitle = "よくある質問 | LMS";

	String detailtitle = "セクション詳細 | LMS";

	String reporttitle = "レポート登録 | LMS";

	String usertitle = "ユーザー詳細";

	String reportDetailTitle = "レポート詳細 | LMS";

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
		final WebElement loginId = webDriver.findElement(By.name("loginId"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement loginClick = webDriver.findElement(By.className("btn-primary"));

		loginId.clear();
		password.clear();

		loginId.sendKeys(id);
		password.sendKeys(pass);

		loginClick.click();

		final WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.titleIs(courseTitle));

		assertEquals(courseTitle, webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		final List<WebElement> noSubmitted = webDriver.findElements(By.tagName("tr"));

		for (WebElement targetText : noSubmitted) {

			if (targetText.getText().contains("2025年7月9日(水)")) {
				//Java概要の詳細ボタンを押すための条件
				final WebElement detailClick = targetText.findElement(By.cssSelector("input[value=\"詳細\"]"));

				detailClick.click();

				break;
			}
		}
		final WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.titleIs(detailtitle));

		assertEquals(detailtitle, webDriver.getTitle());

		getEvidence(new Object() {
		});

	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		final WebElement reportClick = webDriver.findElement(By.cssSelector("input[value=\"提出済み週報【デモ】を確認する\"]"));

		scrollTo("100");
		reportClick.click();

		final WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.titleIs(reporttitle));

		assertEquals(reporttitle, webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() {
		// TODO ここに追加
		final WebElement courseContent = webDriver.findElement(By.cssSelector("input[name=\"intFieldNameArray[0]\"]"));
		courseContent.clear();
		courseContent.sendKeys("seleniumテストコードによる学習項目欄のテスト 1回目以降の提出による内容が反映されているかの確認");

		final Select level = new Select(
				webDriver.findElement(By.cssSelector("select[name=\"intFieldValueArray[0]\"]")));
		level.selectByValue("2");

		final WebElement achievement = webDriver.findElement(By.cssSelector("textarea[id^='content_0']"));
		achievement.clear();
		achievement.sendKeys("10");

		final WebElement impressions = webDriver.findElement(By.cssSelector("textarea[id^='content_1']"));
		impressions.clear();
		impressions.sendKeys("seleniumテストコードによる所感の欄のテスト 1回目以降の提出による内容が反映されているかの確認");

		final WebElement review = webDriver.findElement(By.cssSelector("textarea[id^='content_2']"));
		review.clear();
		review.sendKeys("seleniumテストコードによる１週間の振り返り欄のテスト 1回目以降の提出による内容が反映されているかの確認");

		final WebElement submission = webDriver.findElement(By.className("btn-primary"));
		submission.click();

		final WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.titleIs(detailtitle));

		assertEquals(detailtitle, webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() {
		// TODO ここに追加
		final WebElement userLink = webDriver.findElement(By.linkText("ようこそ受講生ＡＡ２さん"));

		userLink.click();

		final WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.titleIs(usertitle));

		assertEquals(usertitle, webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() {
		// TODO ここに追加
		final List<WebElement> noSubmitted = webDriver.findElements(By.tagName("tr"));

		for (WebElement targetText : noSubmitted) {

			if (targetText.getText().contains("2025年7月9日(水)")) {
				if (targetText.getText().contains("週報【デモ】")) {
					//Java概要の詳細ボタンを押すための条件
					final WebElement detailClick = targetText.findElement(By.cssSelector("input[value=\"詳細\"]"));

					scrollTo("600");

					detailClick.click();

					break;
				}
			}
		}

		final WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.titleIs(reportDetailTitle));

		assertEquals(reportDetailTitle, webDriver.getTitle());

		final List<WebElement> reportElements = webDriver.findElements(By.cssSelector(".table-hover td"));

		final WebElement courseContent = reportElements.get(1);
		assertEquals("seleniumテストコードによる学習項目欄のテスト 1回目以降の提出による内容が反映されているかの確認",
				courseContent.getText(), "週報の内容が一致しません");

		final WebElement level = reportElements.get(2);
		assertEquals("2", level.getText(), "週報の内容が一致しません");

		final WebElement achievement = reportElements.get(3);
		assertEquals("10", achievement.getText(), "週報の内容が一致しません");

		final WebElement impressions = reportElements.get(4);
		assertEquals("seleniumテストコードによる所感の欄のテスト 1回目以降の提出による内容が反映されているかの確認",
				impressions.getText(), "週報の内容が一致しません");

		final WebElement review = reportElements.get(5);
		assertEquals("seleniumテストコードによる１週間の振り返り欄のテスト 1回目以降の提出による内容が反映されているかの確認",
				review.getText(), "週報の内容が一致しません");

		getEvidence(new Object() {
		});

	}

}
