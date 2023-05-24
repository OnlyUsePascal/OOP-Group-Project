package com.groupproject.controller.component;

import com.groupproject.controller.popup.ItemInfoCartController;
import com.groupproject.entity.generic.Item;
import com.groupproject.controller.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemBoxController implements Initializable {
    @FXML
    private Rectangle imgFrame;
    @FXML
    private Label itemTitle;
    // @FXML
    // private Label itemPrice;

    private Item item;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setData(Item _item) {
        item = _item;
        setTitle(_item.getTitle());

        setImg();
    }

    public void setImg() {
        ViewHandler.fillShapeWithImage(item.getImgName(), imgFrame);
    }

    public void setTitle(String titleNew) {
        itemTitle.setText(titleNew);
    }

    public void getPopup(ActionEvent event){
        FXMLLoader itemLoader = new FXMLLoader(getClass().getResource(PathHandler.getPopupItemInfoCart()));
        try {
            AnchorPane itemInfoPane = itemLoader.load();

            ItemInfoCartController itemInfoCartController = itemLoader.getController();
            itemInfoCartController.setData(item);

            ViewHandler.getPopup(itemInfoPane, null);
            // ShopSystem.showPopup(popup);
        } catch (IOException err){
            err.printStackTrace();
        }
    }
}
