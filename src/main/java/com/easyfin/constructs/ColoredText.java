package com.easyfin.constructs;

import javafx.scene.paint.Color;
import lombok.Getter;

/**
 * Wrapper class for coloring text in a ListView.
 */
@Getter
public class ColoredText {
    private final String text ;
    private final Color color ;

    /**
     * Initializes this colored text object.
     *
     * @param text the text
     * @param color the color of the text
     */
    public ColoredText(String text, Color color) {
        this.text = text ;
        this.color = color ;
    }
}
