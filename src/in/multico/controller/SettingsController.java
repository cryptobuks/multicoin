package in.multico.controller;

import in.multico.Main;
import in.multico.Settings;
import in.multico.listener.ShowListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Copyright © 2016 Marat Shmush. All rights reserved.
 * Date: 05.02.16
 * Time: 09:38
 */
public class SettingsController extends ControllerBased implements Initializable {

    @FXML public TitledPane security_page;
    @FXML public Accordion acrd;
    @FXML public CheckBox spendUnconfirm;
    @FXML public CheckBox refreshAddr;
    @FXML public Button exKeysAdd;
    @FXML public Button exKeysEdit;
    @FXML public Button exKeysDel;
    private Settings settings;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settings = Settings.getInstanse();
        acrd.setExpandedPane(security_page);
        spendUnconfirm.setSelected(settings.isAllowSpendUnconfirmed());
        refreshAddr.setSelected(settings.isAlwaysRefreshAddr());
        if (settings.getPoloKey() != null) {
            exKeysAdd.setVisible(false);
            exKeysDel.setVisible(true);
            exKeysEdit.setVisible(true);
        } else {
            exKeysAdd.setVisible(true);
            exKeysDel.setVisible(false);
            exKeysEdit.setVisible(false);
        }
    }

    public void showMnemonic(ActionEvent event) {
        Main.refreshLayout(event, new CheckPasswordController().getLayout(), new ShowListener() {
            @Override
            public void onShow(Object controller) {
                ((CheckPasswordController) controller).setNextStepMnemonic();
            }
        });
    }

    public void changePass(ActionEvent event) {
        Main.refreshLayout(event, new CheckPasswordController().getLayout(), new ShowListener() {
            @Override
            public void onShow(Object controller) {
                ((CheckPasswordController) controller).setNextChangePass();
            }
        });
    }

    public void togleSpendUnconfirmed(ActionEvent event) {
        settings.setAllowSpendUnconfirmed(spendUnconfirm.isSelected());
    }

    public void back(ActionEvent event) {
        Main.refreshLayout(event, new MainController().getLayout());
    }

    @Override
    public String getLayout() {
        return "settings.fxml";
    }

    @Override
    protected void refresh() {

    }

    public void togleRefreshAddr(ActionEvent event) {
        settings.setAlwaysRefreshAddr(refreshAddr.isSelected());
    }

    public void addExKeys(ActionEvent event) {
        Main.refreshLayout(event, new AddExchangeController().getLayout());
    }

    public void editExKeys(ActionEvent event) {
        // TODO:
    }

    public void deleteExKeys(ActionEvent event) {
        settings.deletePoloKey();
        exKeysAdd.setVisible(true);
        exKeysDel.setVisible(false);
        exKeysEdit.setVisible(false);
    }
}
