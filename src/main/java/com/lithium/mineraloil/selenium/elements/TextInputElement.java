package com.lithium.mineraloil.selenium.elements;

import com.lithium.mineraloil.waiters.WaitCondition;
import lombok.experimental.Delegate;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class TextInputElement implements Element {

    @Delegate
    private final ElementImpl<TextInputElement> elementImpl;

    public TextInputElement(By by) {
        elementImpl = new ElementImpl(this, by);
    }

    public TextInputElement(By by, int index) {
        elementImpl = new ElementImpl(this, by, index);
    }

    public void clear() {
        new WaitCondition() {
            public boolean isSatisfied() {
                elementImpl.locateElement().clear();
                return true;
            }
        }.waitUntilSatisfied();
    }

    /**
     * Clears out the value of the input field first then types specified text.
     *
     * @param text the text to put into the text area
     */
    public void type(final String text) {
        if (text == null) return;
        new WaitCondition() {
            public boolean isSatisfied() {
                WebElement element = elementImpl.locateElement();
                element.clear();
                element.sendKeys(text);
                return true;
            }
        }.waitUntilSatisfied();
    }

    /**
     * Only sends the given keystroke
     *
     * @param key the Keys to put into the text area
     */
    public void pressKey(final Keys key) {
        if (key == null) return;
        new WaitCondition() {
            public boolean isSatisfied() {
                WebElement element = elementImpl.locateElement();
                element.sendKeys(key);
                return true;
            }
        }.waitUntilSatisfied();
    }

    /**
     * Only sends the given keystroke
     *
     * @param key the Keys to put into the text area
     */
    public void pressKey(final String key) {
        if (key == null) return;
        new WaitCondition() {
            public boolean isSatisfied() {
                WebElement element = elementImpl.locateElement();
                element.sendKeys(key);
                return true;
            }
        }.waitUntilSatisfied();
    }

    /**
     * Appends the specified text to the input field.  If there is no pre-existing text,
     * this will have the same affect as type(String).
     *
     * @param text the text to append to the current text within the input field
     */
    public void appendType(final String text) {
        if (text == null) return;
        new WaitCondition() {
            public boolean isSatisfied() {
                elementImpl.locateElement().sendKeys(text);
                return true;
            }
        }.waitUntilSatisfied();
    }

    /**
     * Prepends the specified text to the input field.  If there is no pre-existing text,
     * this will have the same affect as type(String).
     *
     * @param text the text to append to the current text within the input field
     */
    public void prependType(final String text) {
        if (text == null) return;
        new WaitCondition() {
            public boolean isSatisfied() {
                elementImpl.locateElement().sendKeys(Keys.chord(Keys.COMMAND, Keys.ARROW_UP) + text);
                return true;
            }
        }.waitUntilSatisfied();
        moveCursorToEndOfInput();
    }

    private void moveCursorToEndOfInput() {
        new WaitCondition() {
            public boolean isSatisfied() {
                WebElement element = elementImpl.locateElement();
                element.sendKeys(Keys.chord(Keys.COMMAND, Keys.ARROW_DOWN));
                element.click();
                return true;
            }
        }.waitUntilSatisfied();
    }

    public void pressReturn() {
        pressKey(Keys.RETURN);
    }

    public void pressEnter() {
        pressKey(Keys.ENTER);
    }

    public boolean isEmpty() {
        return new WaitCondition() {
            @Override
            public boolean isSatisfied() {
                if (isDisplayed()) {
                    return StringUtils.isBlank(locateElement().getText().trim());
                } else {
                    return true;
                }
            }
        }.setTimeout(TimeUnit.SECONDS, 1).waitAndIgnoreExceptions().isSuccessful();
    }
}
