package com.nov.hotel.gui.controllers.impl;

import com.nov.hotel.collections.impl.ApartStatusCollection;
import com.nov.hotel.entities.ApartStatus;
import com.nov.hotel.gui.controllers.abstr.AbstractTableController;
import com.nov.hotel.gui.util.ColorUtil;
import com.nov.hotel.gui.windows.impl.AbstractWindow;
import com.nov.hotel.gui.windows.impl.ApartStatusEditWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class ApartStatusController extends AbstractTableController<ApartStatus> {

    @FXML
    public TableView tableAppartStatus;
    @FXML
    public TableColumn<ApartStatus, String> columnName;
    @FXML
    public TableColumn<ApartStatus, String> columnColor;

    @Override
    protected void initData() {
        setEditWindow(ApartStatusEditWindow.getInstance());
        setCollection(ApartStatusCollection.getInstance());
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        // Custom rendering of the table cell.
        columnColor.setCellFactory(column -> {
            return new TableCell<ApartStatus, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                     //   setText(item);
                        Color color = ColorUtil.createColor(item);
                        // Style all line with a different color.
                        if (color != null) {
                            setStyle("-fx-background-color: "+ColorUtil.createRGBString(color));
                        } else {
                            setStyle("");
                        }
                    }
                }
            };
        });
    }

    @Override
    protected TableView getTable() {
        return tableAppartStatus;
    }

    @Override
    protected boolean isElemFound(ApartStatus elem) {
        return elem.getName().toLowerCase().contains(txtFind.getText().toLowerCase());
    }

    public void add(ActionEvent actionEvent) {
        addAbst(new ApartStatus());
    }

    public void copyAndEdit(ActionEvent actionEvent) {
        copyAbst(new ApartStatus((ApartStatus) getTable().getSelectionModel().getSelectedItem()));
    }
}
