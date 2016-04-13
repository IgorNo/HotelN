package com.nov.hotel.gui.controllers;

import com.nov.hotel.gui.windows.AuthorWindow;
import com.nov.hotel.gui.windows.impl.AbstractWindow;
import com.nov.hotel.gui.windows.impl.MainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class LoginController {

    public void login(ActionEvent actionEvent) throws IOException {
        AuthorWindow.hide();
        AbstractWindow mainWindow = MainWindow.getInstance();
        mainWindow.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        mainWindow.show();
    }
}
