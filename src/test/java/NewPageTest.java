import com.sun.source.tree.AssertTree;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

@TestInstance(Lifecycle.PER_CLASS)

public class NewPageTest {

  WebDriver webdriver;

  @BeforeAll
  public void setupAll(){
    System.setProperty("webdriver.chrome.driver",
        "src/test/resources/chromedriver_linux64/chromedriver.exe");
  }

  @BeforeEach
  public void setup(){
    webdriver = new ChromeDriver();
    webdriver.manage().window().maximize();
  }


  @AfterEach
  public void closeDriver(){
    webdriver.close();
  }


  @Test
  public void openNewPage(){
    webdriver.get("https://sip.trf5.jus.br/sip/login.php?sigla_orgao_sistema=TRF5&sigla_sistema=SEI");
    Assertions.assertEquals("https://sip.trf5.jus.br/sip/login.php?sigla_orgao_sistema=TRF5&sigla_sistema=SEI",
        webdriver.getCurrentUrl());
  }


  @Test
  public void openFacebookPage(){
    webdriver.get("https://www.terra.com.br/economia");
    WebElement botao = webdriver.findElement(
        By.id("social-btn-facebook"));
    botao.click();
  }


  @Test
  public void openPageHeaderCopa(){
    webdriver.get("https://www.terra.com.br/");
    WebElement botaoFB = webdriver.findElement(By.xpath("/html/body/div[4]/nav/ul/li[1]/a"));
    botaoFB.click();
    Assertions.assertEquals("https://www.terra.com.br/esportes/futebol/copa-2022/",
        webdriver.getCurrentUrl());
  }


  @Test
  public void filtraVariosInput(){
    webdriver.get("https://sip.trf5.jus.br/sip/login.php?sigla_orgao_sistema=TRF5&sigla_sistema=SEI&infra_url=L3NlaS8=");
    List<WebElement> inputs = webdriver.findElements(By.name("input-group mb-3 d-flex"));

    for(WebElement  input : inputs){
      System.out.println(input.getText());
    }


  }

  @Test
  public void openPaginaBuscaSite (){
    webdriver.get("https://www.terra.com.br/busca/?q=");

    WebElement search = webdriver.findElement(By.id("gsc-i-id1"));

    search.sendKeys("terra");
    search.submit();
  }

  @Test
  public void abrirPaginatablaCopa(){
    webdriver.get("https://www.terra.com.br/");
    Actions actions = new Actions(webdriver);
    WebElement botao = webdriver.findElement(
        By.xpath("//*[@id=\"zaz-app-t360-navbar\"]/div[1]/header/div/div[1]/a[2]"));
    actions.moveToElement(botao).perform();
  }


  @Test
  public void openTwitterPage(){
    webdriver.get("https://www.terra.com.br/economia");
    WebElement botao = webdriver.findElement(By.id("social-btn-twitter"));
    botao.click();

  }


  @Test
  public void tooltipTest(){
    webdriver.get("https://www.terra.com.br/");
    Actions actions = new Actions(webdriver);

    WebElement ge = webdriver.findElement(By.xpath("//*[@id=\"zaz-app-t360-navbar\"]/div[1]/header/div/div[2]/a"));
    Assertions.assertEquals("E-mail do Terra", ge.getAttribute("title"));
    actions.moveToElement(ge).perform();
    Assertions.assertTrue(ge.isEnabled());
  }


  @Test
  public void tooltipTestFals(){
    webdriver.get("https://www.terra.com.br/");
    Actions actions = new Actions(webdriver);

    WebElement ge = webdriver.findElement(By.xpath("//*[@id=\"zaz-app-t360-navbar\"]/div[1]/header/div/div[2]/a"));
    Assertions.assertEquals("E-mail do Terra", ge.getAttribute("title"));
    actions.moveToElement(ge).perform();
    Assertions.assertFalse(ge.isSelected());
  }

  @Test
  public void openInstragrankPage(){
    webdriver.get("https://www.terra.com.br/economia");
    WebElement botao = webdriver.findElement(
            By.id("social-btn-instagram"));
    botao.click();
  }

  @Test
  public void SelectDropTest()  {
    webdriver.get("https://sip.trf5.jus.br/sip/login.php?sigla_orgao_sistema=TRF5&sigla_sistema=SEI");
    WebElement ddown = webdriver.findElement(By.name("selOrgao"));
    Select select = new Select(ddown);

    select.selectByValue("1");

    select.selectByVisibleText("JFPE");

    select.selectByIndex(5);
  }

  @Test
  public void tooltiTest(){
    webdriver.get("https://www.terra.com.br/esportes/futebol/copa-2022/tabela");
    Actions actions = new Actions(webdriver);

    WebElement ge = webdriver.findElement(By.xpath("/html/body/main/div[2]/article/div[2]/div/ul/li[7]/div/div[1]/table/thead/tr/th[2]/a"));
    Assertions.assertFalse(Boolean.parseBoolean("points desc"), ge.getAttribute("Pontos"));
    actions.moveToElement(ge).perform();
    Assertions.assertFalse(ge.isSelected());
  }



}
