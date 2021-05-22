package com.iteraprocess.test.pages;

import com.iteraprocess.framework.base.BasePage;
import com.iteraprocess.framework.base.DriverContext;
import com.iteraprocess.framework.utilities.CommonUtil;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

//Inkafarma

    @FindBy(how = How.XPATH, using = "//*[@id=\"onesignal-slidedown-allow-button\"]")
    private WebElement btnAceptar;

    @FindBy(how = How.XPATH, using = "//*[@id=\"lb--open-login-modal\"]/span[1]")
    private WebElement loginNodal1;

    @FindBy(how = How.XPATH, using = "//*[@id=\"btn--open-modal-login-email\"]/button")
    private WebElement btntipoLogin;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ctrl--login-email\"]")
    private WebElement inputCorreo;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ctrl--login-password\"]")
    private WebElement inputContrasena;

    @FindBy(how = How.XPATH, using = "//*[@id=\"btn--login-email\"]/button")
    private WebElement btnAcceder;

    @FindBy(how = How.XPATH, using = "//*[@id=\"loginOk\"]/span[2]")
    private WebElement loginNodal2;

    @FindBy(how = How.XPATH, using = "//*[@id=\"lb--sign-off\"]")
    private WebElement signout;

    public void Message() {
        CommonUtil.clickElement(btnAceptar);
    }

    public void LoginF() {
        CommonUtil.clickElement(loginNodal1);
        CommonUtil.clickElement(btntipoLogin);
        CommonUtil.sendText(inputCorreo, "marcelino.abregu@gmail.com");
        CommonUtil.sendText(inputContrasena, "Automatizacion1");
        CommonUtil.clickElement(btnAcceder);

    }

    public void LoginF1(String correo, String contrasena) {
        CommonUtil.waitPageLoad();
        CommonUtil.clickElement(loginNodal1);
        CommonUtil.clickElement(btntipoLogin);
        CommonUtil.sendText(inputCorreo, correo);
        CommonUtil.sendText(inputContrasena, contrasena);
        CommonUtil.clickElement(btnAcceder);
    }

    public void cierro() {
        CommonUtil.clickElement(loginNodal2);
        CommonUtil.clickElement(signout);
    }
}
