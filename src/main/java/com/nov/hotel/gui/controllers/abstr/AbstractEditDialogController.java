package com.nov.hotel.gui.controllers.abstr;

import com.nov.hotel.entities.interfaces.Entity;
import com.nov.hotel.gui.windows.DialogManager;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.decoration.StyleClassValidationDecoration;

import java.net.URL;
import java.util.ResourceBundle;


abstract public class AbstractEditDialogController<K, E extends Entity<K,E>>  extends AbstractController implements Initializable {

    private E elem;

    private E backupElem;

    protected ResourceBundle resourceBundle;

    private boolean isSaveAction = false;

    protected ValidationSupport validationSupport = new ValidationSupport();

    abstract protected void  fillField();

    abstract protected void saveField();

    abstract protected E copyElem(E elem);

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        validationSupport.setValidationDecorator(new StyleClassValidationDecoration());
    }
    // Pattern Template Method
    protected void setElem(E elem) {
        if (elem == null){
            return;
        }
        this.elem = copyElem(elem);
        backupElem = elem;
        fillField();
    }

    public boolean isSaveAction() {
        return isSaveAction;
    }

    protected E getElem() {
        return elem;
    }

    public void actionClose(ActionEvent actionEvent) {
        isSaveAction = false;
        assign(elem,backupElem);
        closeWindow();
    }


// Pattern Template Method
   public void actionSave(ActionEvent actionEvent) {
        try {
            saveField();
            isSaveAction = true;
            closeWindow();
        } catch (NumberFormatException e){
            DialogManager.showInfoDialog(resourceBundle.getString("message.error"), resourceBundle.getString("message.invalid.data"));
            return;
        }
   }

    protected void assign(E leftValue, E rightValue) {
        leftValue.assign(rightValue);
    }

}
