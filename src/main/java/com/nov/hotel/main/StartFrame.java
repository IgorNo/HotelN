package com.nov.hotel.main;

import com.nov.hotel.gui.windows.AuthorWindow;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.util.ResourceBundle;

public class StartFrame extends JFrame{

    public void init() {
        JFrame frame = new JFrame("LogIn WindowInit");
        final JFXPanel panel = new JFXPanel();

        frame.setLocation(400,200);
        frame.add(panel);
        frame.setSize(400,240);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
               AuthorWindow.create(frame, panel);
            }
        });
    }

}

