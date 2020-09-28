package pages;

import testUI.elements.UIElement;
import static testUI.Utils.By.*;
import static testUI.elements.TestUI.E;

public class GoogleLanding{
    public UIElement searchInput = E(byName("q"));
    public UIElement agreeButton = E(byCssSelector("#introAgreeButton"));
}