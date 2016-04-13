package com.nov.hotel.gui.windows;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ResourceBundle;

public class AuthorWindow {

    private static Scene scene;
    private static JFrame frame;
    private static JFXPanel panel;

    private static final String FXML_FILE = "/fxml/author.fxml";
    private static final String HEADER = "header.authoriz";
    private static final String STYLE = "/styles/styles.css";

    private static Logger LOG = Logger.getLogger(AuthorWindow.class);

    public static void create(JFrame frame, JFXPanel panel) {
        AuthorWindow.frame = frame;
        AuthorWindow.panel = panel;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AuthorWindow.class.getResource(FXML_FILE));
        loader.setResources(ResourceBundle.getBundle("bundles.Locale"));

        try{
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            scene.getStylesheets().add(STYLE);
            panel.setScene(scene);
        } catch (IOException e) {
            LOG.error("Can't load resource", e);
            throw new RuntimeException(e);
        }
    }

    public static Scene getScene() {
        return scene;
    }

    public static void show(){
        frame.setVisible(true);
    }

    public static void hide() {
        frame.setVisible(false);
    }

}
