package com.lithium.mineraloil.selenium.elements;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.core.ConditionFactory;
import org.openqa.selenium.WebDriverException;

class Waiter {
    public static final int STALE_ELEMENT_WAIT_MS = 500;
    public static final int INTERACT_WAIT_S = 5;
    public static final int DISPLAY_WAIT_S = 20;

    public static ConditionFactory await() {
        return Awaitility.await().ignoreExceptionsInstanceOf(WebDriverException.class);
    }
}
