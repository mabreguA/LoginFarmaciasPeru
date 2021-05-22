package com.iteraprocess.framework.controls.elements;

import com.iteraprocess.framework.controls.api.ImplementedBy;
import com.iteraprocess.framework.controls.internals.Control;


@ImplementedBy(HyperLinkBase.class)
public interface HyperLink extends Control {


    void ClickLink();

    String GetUrlText();

    boolean CheckUrlTextContains(String containsText);
}

