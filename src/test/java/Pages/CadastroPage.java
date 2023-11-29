package Pages;

import Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CadastroPage extends BasePage {
    WebDriver driver;

    public CadastroPage (WebDriver driver){
        super(driver);
    }

    //buttons
    public By locatorButtonCadastro = By.xpath("//*[@id=\"register\"]");
    public By locatorButtonExcluir = By.xpath("//a[@id='removeUser1']");
    public By locatorButtonExcluirUser2 = By.xpath("//a[@id='removeUser2']");
    //inputs
    public By locatorInputName = By.xpath("//*[@id=\"name\"]");
    public By locatorInputEmail = By.xpath("//*[@id=\"email\"]");
    public By locatorInputPassword = By.xpath("//*[@id=\"password\"]");
    //errores
    public By locatorErrorFullName = By.xpath("//p[contains(.,'Por favor, insira um nome completo.')]");
    public By locatorErrorEmail = By.xpath("//p[contains(.,'Por favor, insira um e-mail')]");
    public By locatorErrorShortPassword = By.xpath("//p[contains(.,'A senha deve conter ao menos 8 caracteres.')]");
    public By locatorErrorEmptyName = By.xpath("//p[contains(.,'O campo Nome')]");
    public By locatorErrorEmptyEmail = By.xpath("//p[contains(.,'O campo E-mail')]");
    public By locatorErrorEmptyPassword = By.xpath("//p[contains(.,'O campo Senha')]");

    //userdata
    public By locatorIdUser = By.id("tdUserid1");
    public By locatorUserName = By.id("tdUserName1");
    public By locatorUserEmail = By.id("tdUserEmail1");
    public By locatorUserRemove = By.id("removeUser1");

    //table
    public By locatorRows = By.xpath("//table/tr");
    public By locatorColumns = By.tagName("td");


    public void enterEmail (String email) {
        click(locatorInputEmail);
        addText(locatorInputEmail,email);
    }

    public void enterName (String name) {
        click(locatorInputName);
        addText(locatorInputName,name);
    }

    public void enterPassword (String password) {
        click(locatorInputPassword);
        addText(locatorInputPassword, password);
    }

    public String searchNewUserName(){
        return getText(locatorUserName);
    }

    public String searchNewUserEmail(){
        return getText(locatorUserEmail);
    }

    public Boolean searchExcluirButton(){
        return elementExist(locatorButtonExcluir);
    }

    public void clickCadastro() {
        click(locatorButtonCadastro);
    }

    public String searchError(By locator){
        return getText(locator);
    }

    public List<WebElement> getRows() {
       return findRows(locatorRows);
    }

    public List<WebElement> getColumns(WebElement webElement) {
       return findColumns(webElement, locatorColumns);
    }

    public String findInputNameText (){
        return getText(locatorInputName);
    }

    public String findInputEmailText (){
        return getText(locatorInputEmail);
    }

    public String findInputPasswordText (){
        return getText(locatorInputPassword);
    }

    public String getInputType(){
        return getType(locatorInputPassword);
    }

    public Boolean elementExist (){
        return elementExist(locatorButtonCadastro);
    }


}
