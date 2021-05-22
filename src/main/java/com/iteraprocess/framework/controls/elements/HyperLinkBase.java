package com.iteraprocess.framework.controls.elements;

import com.iteraprocess.framework.controls.internals.ControlBase;
import org.openqa.selenium.WebElement;

public class HyperLinkBase extends ControlBase implements HyperLink {

    public HyperLinkBase(WebElement element) {
        super(element);
    }

    @Override
    public void ClickLink() {
        getWrappedElement().click();
    }

    @Override
    public String GetUrlText() {
        return getWrappedElement().getText();
    }

    @Override
    public boolean CheckUrlTextContains(String containsText) {
        return getWrappedElement().getText().contains(containsText);
    }
}
