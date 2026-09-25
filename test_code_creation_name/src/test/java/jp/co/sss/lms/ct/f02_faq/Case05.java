package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
//import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
 * 結合テスト よくある質問機能
 * ケース05
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース05 キーワード検索 正常系")
public class Case05 {

	String url = "http://localhost:8080/lms";

	String title = "ログイン | LMS";

	String helptitle = "ヘルプ | LMS";

	String questiontitle = "よくある質問 | LMS";

	String id = "StudentAA02";

	String pass = "StudentAA022";

	String word = "キャ"; //よくある質問画面でのキーワード検索テストで使用

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

		assertEquals("StudentAA02", id, "ログインIDが一致していません");
		assertEquals("StudentAA022", pass, "passwordが一致していません");

		loginClick.click();

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// TODO ここに追加
		final WebElement functionClick = webDriver.findElement(By.className("dropdown-toggle"));
		functionClick.click();

		final WebElement helpClick = webDriver.findElement(By.linkText("ヘルプ"));
		helpClick.click();

		assertEquals(helptitle, webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// TODO ここに追加
		final WebElement questionClick = webDriver.findElement(By.linkText("よくある質問"));

		questionClick.click();

		Set<String> handles = webDriver.getWindowHandles();

		List<String> list = new ArrayList<>(handles);

		webDriver.switchTo().window(list.get(list.size() - 1));

		assertEquals(questiontitle, webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 キーワード検索で該当キーワードを含む検索結果だけ表示")
	void test05() {
		// TODO ここに追加
		final WebElement inputKeyword = webDriver.findElement(By.className("form-control"));
		final WebElement searchClick = webDriver.findElement(By.className("btn-primary"));

		inputKeyword.clear();

		inputKeyword.sendKeys(word);

		searchClick.click();

		//検索結果のテキストを取得・検索結果の数だけ行う
		final List<WebElement> anser = webDriver.findElements(By.className("mb10"));

		for (WebElement anserElement : anser) {
			String anserText = anserElement.getText();

			//検索結果が取れているかの確認コード
			//System.out.println("検索結果：" + anserText);

			assertThat("キーワードが含まれていません", anserText, containsString(word));

		}

		scrollTo("200");

		getEvidence(new Object() {
		});

		scrollTo("-200");

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 「クリア」ボタン押下で入力したキーワードを消去")
	void test06() {
		// TODO ここに追加
		final WebElement inputKeyword = webDriver.findElement(By.className("form-control"));
		final List<WebElement> clearButton = webDriver.findElements(By.cssSelector(".btn-primary"));
		WebElement clearClick = clearButton.get(1);

		clearClick.click();

		String actualValue = inputKeyword.getAttribute("value");

		assertThat(actualValue, is(""));

		getEvidence(new Object() {
		});
	}

}
