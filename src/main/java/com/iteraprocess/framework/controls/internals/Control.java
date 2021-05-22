    package com.iteraprocess.framework.controls.internals;

    import com.iteraprocess.framework.controls.api.ImplementedBy;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.interactions.Locatable;
    import org.openqa.selenium.internal.WrapsElement;

//import org.openqa.selenium.internal.Locatable;

    @ImplementedBy(ControlBase.class)
    public interface Control extends WebElement, WrapsElement, Locatable {
    }

