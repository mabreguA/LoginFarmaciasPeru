package com.iteraprocess.framework.controls.elements;

import com.iteraprocess.framework.controls.api.ImplementedBy;
import com.iteraprocess.framework.controls.internals.Control;


@ImplementedBy(ButtonBase.class)
public interface Button extends Control {

    void PerformClick();

    String GetButtonText();

    void PerformSubmit();
}
