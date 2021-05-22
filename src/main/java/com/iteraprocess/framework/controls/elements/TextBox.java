package com.iteraprocess.framework.controls.elements;

import com.iteraprocess.framework.controls.api.ImplementedBy;
import com.iteraprocess.framework.controls.internals.Control;


@ImplementedBy(TextBoxBase.class)
public interface TextBox extends Control {

    void EnterText(String text);

    String GetTextValue();

}
