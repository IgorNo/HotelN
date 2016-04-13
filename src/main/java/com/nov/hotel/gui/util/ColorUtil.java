package com.nov.hotel.gui.util;

import javafx.scene.paint.Color;

public class ColorUtil {

    public static Color createColor(String stringColor) {
        if (stringColor != null && stringColor.length() == 10 && "0x".equalsIgnoreCase(stringColor.substring(0,2))) {
            double red = Integer.decode(stringColor.substring(0,4))/255.0;
            double green = Integer.decode(stringColor.substring(0,2)+stringColor.substring(4,6))/255.0;
            double blue = Integer.decode(stringColor.substring(0,2)+stringColor.substring(6,8))/255.0;
            double opacity = Integer.decode(stringColor.substring(0,2)+stringColor.substring(8))/255.0;
            return new Color(red, green, blue, opacity);
        }
        return null;
    }

    public static String createRGBString(Color c) {
        return "rgb(" + (c.getRed() * 255) + "," + (c.getGreen() * 255) + "," + (c.getBlue() * 255) + ");";
    }



}
