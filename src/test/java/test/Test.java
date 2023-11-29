package test;

import Pages.CadastroPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Test {


    WebDriver driver;
    CadastroPage cadastroPage;

    @Before
    public void setUp() {

        System.setProperty("file.encoding", "UTF-8");
        driver = new ChromeDriver();
        cadastroPage = new CadastroPage(driver);
    }

    @Given("estoy en la pagina de registro")
    public void abrirPaginaCadastro(){
        driver.get("https://cadastro-de-usuarios.s3.us-east-1.amazonaws.com/index.html");
    }

    @When("ingreso {string} en el campo nombre")
    public void ingreso_en_el_campo_nombre_el_valor(String name) {
        cadastroPage.enterName(name);
    }
    @When("ingreso {string} en el campo email")
    public void ingreso_en_el_campo_email_el_valor(String email) {
        cadastroPage.enterEmail(email);
    }

    @When("ingreso {string} en el campo contrasenia")
    public void ingreso_en_el_campo_contrasenia_el_valor(String password) {
        cadastroPage.enterPassword(password);
    }

    @When("hago clic en el boton {string}")
    public void se_da_click_en_el_boton(String string) {
        cadastroPage.clickCadastro();
    }

    @When("creo un usuario con nombre {string}, correo {string} y contrasenia {string}")
    public void creoUnUsuarioConNombreCorreoYContrasenia(String arg0, String arg1, String arg2) {
        cadastroPage.enterName(arg0);
        cadastroPage.enterEmail(arg1);
        cadastroPage.enterPassword(arg2);
        cadastroPage.click(cadastroPage.locatorButtonCadastro);
    }

    @And("presiono la opcion Eliminar desde un elemento de la tabla de usuario para el usuario con ID {string}")
    public void presionoLaOpcionEliminarDesdeUnElementoDeLaTablaDeUsuarioParaElUsuarioConID(String arg0) {
        cadastroPage.click(cadastroPage.locatorButtonExcluirUser2);
    }

    @Then("deberia ver el error {string} en el campo nombre")
    public void se_muestra_el_error_en_el_campo_nombre(String string) {
        if (cadastroPage.elementExist(cadastroPage.locatorErrorFullName)){
            assertEquals(string, cadastroPage.searchError(cadastroPage.locatorErrorFullName));
        } else  {
            Assert.fail();
        }

    }

    @Then("deberia ver el error {string} en el campo email")
    public void seMuestraElErrorEnElCampoEmail(String string) {
        if (cadastroPage.elementExist(cadastroPage.locatorErrorEmail)){
            assertEquals(string, cadastroPage.searchError(cadastroPage.locatorErrorEmail));
        } else  {
            Assert.fail();
        }
    }

    @Then("deberia ver el error {string} en el campo contrasenia")
    public void seMuestraElErrorEnElCampoPassword(String string) {
        if (cadastroPage.elementExist(cadastroPage.locatorErrorShortPassword)){
            assertEquals(string, cadastroPage.searchError(cadastroPage.locatorErrorShortPassword));
        } else  {
            Assert.fail();
        }
    }




    @Then("deberia ver los errores {string}, {string} y {string} en los campos vacios")
    public void seMuestraLosErroresAsdAsdYAsdEnLosCamposVacios(String errorName, String errorEmail, String errorPassword) {
        //assert name
        if (cadastroPage.elementExist(cadastroPage.locatorErrorEmptyName)){
            assertEquals(errorName, cadastroPage.searchError(cadastroPage.locatorErrorEmptyName));
        } else  {
            Assert.fail();
        }
        //assert email
        if (cadastroPage.elementExist(cadastroPage.locatorErrorEmptyEmail)){
            assertEquals(errorEmail, cadastroPage.searchError(cadastroPage.locatorErrorEmptyEmail));
        } else  {
            Assert.fail();
        }
        //assert password
        if (cadastroPage.elementExist(cadastroPage.locatorErrorEmptyPassword)){
            assertEquals(errorPassword, cadastroPage.searchError(cadastroPage.locatorErrorEmptyPassword));
        } else  {
            Assert.fail();
        }
    }


    @Then("deberia ver el usuario en la lista")
    public void deberiaVerElUsuarioEnLaLista() {
        String name = cadastroPage.searchNewUserName();
        assertEquals(name, "Tomas Brito");
        String email = cadastroPage.searchNewUserEmail();
        assertEquals(email, "tomas@gmail.com");
    }

    @And("deberia ver un boton para eliminar el usuario")
    public void deberiaVerUnBotonParaEliminarElUsuario() {
        Assert.assertTrue(cadastroPage.searchExcluirButton());
    }




    @After
    public void closeChrome(){
        if (driver != null) {
            driver.quit();
        }
    }



    @Then("deberia ver una tabla con los datos de los usuarios ordenados por ID")
    public void deberiaVerUnaTablaConLosDatosDeLosUsuariosOrdenadosPorID(io.cucumber.datatable.DataTable dataTable) {
        // Obtener todas las filas

        List<WebElement> filas = cadastroPage.getRows();
        // pasar las filas y comparar los valores con los esperados
        for (int i = 0; i < filas.size(); i++) {
            List<String> filaEsperada = dataTable.row(i);
            List<WebElement> columnas = cadastroPage.getColumns(filas.get(i));

            for (int j = 0; j < filaEsperada.size(); j++) {
                assertEquals(columnas.get(j).getText(), filaEsperada.get(j));
            }
        }
    }

    @Then("deberia ver el campo de nombre con el valor inicial vacio")
    public void deberiaVerElCampoDeNombreConElValorInicialVacio() {
        String inputName = cadastroPage.findInputNameText();
        Assert.assertTrue(inputName.isEmpty());
    }

    @Then("deberia ver el campo de correo electronico con el valor inicial vacio")
    public void deberia_ver_el_campo_de_correo_electronico_con_el_valor_inicial_vacio() {
        String inputEmail = cadastroPage.findInputEmailText();
        Assert.assertTrue(inputEmail.isEmpty());
    }
    @Then("deberia ver el campo de contrasenia con el valor inicial vacio y caracteres enmascarados")
    public void deberia_ver_el_campo_de_contrasenia_con_el_valor_inicial_vacio_y_caracteres_enmascarados() {
        String inputPassword = cadastroPage.findInputPasswordText();
        String inputType = cadastroPage.getInputType();
        Assert.assertTrue(inputPassword.isEmpty());
        Assert.assertTrue(inputType.equalsIgnoreCase("password"));
    }


    @Then("deberia ver el boton de registro")
    public void deberia_ver_el_boton_de_registro() {
        Assert.assertTrue(cadastroPage.elementExist());
    }

}
