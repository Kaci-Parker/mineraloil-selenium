package com.lithium.mineraloil.selenium.elements;

import java.util.List;

public interface SelectList {
    void select(String optionText);

    void select(String optionText, boolean closeSelectListAfterClick);

    void selectIfContains(String optionText);

    boolean isDisplayed();

    String getSelectedOption();

    List<String> getAvailableOptions();
}
