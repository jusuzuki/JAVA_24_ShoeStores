import java.util.ArrayList;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.rules.ExternalResource;
import org.sql2o.*;


public class AppIntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule  database= new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Shoe Stores");
  }

  @Test
  public void brandIsAddedCorrectlyTest() {
    goTo("http://localhost:4567/brands");
    fill("#name").with("Salomon");
    submit("#addBrand");
    assertThat(pageSource()).contains("Salomon");
  }

  @Test
  public void storeIsAddedCorrectlyTest() {
    goTo("http://localhost:4567/stores");
    fill("#name").with("The Shoe Store");
    fill("#address").with("1603 Alberta Street");
    fill("#area").with("NE");
    submit("#addStore");
    assertThat(pageSource()).contains("The Shoe Store");
  }

  @Test
    public void storeIsDeleted() {
      goTo("http://localhost:4567/stores");
      fill("#name").with("REI");
      fill("#address").with("1603 Alberta Street");
      fill("#area").with("NE");
      submit("#addStore");
      click("a", withText("Delete"));
      assertThat(pageSource()).doesNotContain("REI");
    }

  @Test
    public void storeIsUpdated() {
      goTo("http://localhost:4567/stores");
      fill("#name").with("REI");
      fill("#address").with("1603 Alberta Street");
      fill("#area").with("NE");
      submit("#addStore");
      goTo("http://localhost:4567/stores");
      click("a", withText("Update"));
      fill("#name").with("Patagonia");
      submit("#updateStore");
      goTo("http://localhost:4567/stores");
      assertThat(pageSource()).contains("Patagonia");
    }


}
