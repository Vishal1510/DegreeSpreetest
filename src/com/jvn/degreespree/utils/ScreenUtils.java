/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvn.degreespree.utils;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author john
 */
public class ScreenUtils {
    private static double width;
    private static double height;
    
    public static void init() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        width = screenBounds.getWidth();
        height = screenBounds.getHeight();
    }
    
    public static double getWidth() {
        return width;
    }
    
    public static double getHeight() {
        return height;
    }
    
    
}
