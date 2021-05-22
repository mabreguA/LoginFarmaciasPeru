package com.iteraprocess.test.pages;

import com.iteraprocess.framework.base.BasePage;
import com.iteraprocess.framework.utilities.CommonUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class InkaFarmaPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//*[@id=\"ctrl-product-searcher\"]")
    private WebElement inputBusca;

    @FindBy(how = How.XPATH, using = "//*[@id=\"lb--trigger-search-all-result\"]/label/span")
    private WebElement btnBusca;

    @FindBy(how = How.XPATH, using = "//*[@id=\"mat-dialog-0\"]/fp-home-location-alert/div/div[1]/div/div[2]/span")
    private WebElement close;

    public void busca(String producto) {
        CommonUtil.waitPageLoad();
        CommonUtil.clickElement(close);
        CommonUtil.sendText(inputBusca,producto);
        CommonUtil.clickElement(btnBusca);
        CommonUtil.waitPageLoad();
        CommonUtil.clickElement(inputBusca);
    }
}
