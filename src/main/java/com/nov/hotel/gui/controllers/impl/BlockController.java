package com.nov.hotel.gui.controllers.impl;

import com.nov.hotel.collections.impl.BlockCollection;
import com.nov.hotel.entities.Block;
import com.nov.hotel.gui.controllers.abstr.AbstractTableController;
import com.nov.hotel.gui.windows.impl.BlockEditWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BlockController extends AbstractTableController<Block> {

    @FXML
    public TableView tableAppartStatus;
    @FXML
    public TableColumn columnName;

    @Override
    protected void initData() {
        setEditWindow(BlockEditWindow.getInstance());
        setCollection(BlockCollection.getInstance());
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @Override
    protected TableView getTable() {
        return tableAppartStatus;
    }

    @Override
    protected boolean isElemFound(Block elem) {
        return elem.getName().toLowerCase().contains(txtFind.getText().toLowerCase());
    }

    public void add(ActionEvent actionEvent) {
        addAbst(new Block());
    }

    public void copyAndEdit(ActionEvent actionEvent) {
        copyAbst(new Block((Block) getTable().getSelectionModel().getSelectedItem()));
    }
}
