package com.easyfin.helpers;

import com.easyfin.constructs.ColoredText;
import javafx.scene.paint.Color;

/**
 * Factory class for specifically creating TEXT_GREY colored text
 * objects for use in the ListView.
 */
public class TextFactory {
    // Color used for text in left list view: 0xbfbfbf
    private static final Color TEXT_GREY = new Color(
            (double) 0xbf / 0x100,
            (double) 0xbf / 0x100,
            (double) 0xbf/ 0x100,
            1
    );

    public static ColoredText createText(String text) {
        return new ColoredText(text, TEXT_GREY);
    }
}
