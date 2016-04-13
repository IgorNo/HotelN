package com.nov.hotel.gui.controllers.abstr;

import com.nov.hotel.gui.controllers.interfaces.Controller;
import com.nov.hotel.gui.windows.impl.AbstractWindow;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractController implements Controller {

    AbstractWindow itsWindow;

    public AbstractWindow getItsWindow() {
        return itsWindow;
    }

    public void setItsWindow(AbstractWindow itsWindow) {
        this.itsWindow = itsWindow;
    }

    public Stage getItsStage() {
        return itsWindow.getStage();
    }

    public Scene getItsScene() {
        return itsWindow.getScene();
    }

    protected void closeWindow(){
        getItsStage().hide();
    }

    abstract public void actionClose(ActionEvent actionEvent);

}
