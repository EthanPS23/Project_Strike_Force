package sample;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class Controller {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private JFXButton btnMainMenu;

  @FXML
  private JFXButton btnPackagesOverview;

  @FXML
  private JFXButton btnPackages;

  @FXML
  private JFXButton btnCustomers;

  @FXML
  private JFXButton btnBookings;

  @FXML
  private JFXButton btnSettings;

  @FXML
  private JFXButton btnLoginTab;

  @FXML
  private Pane pnlMainMenu;

  @FXML
  private JFXButton btnLogout;

  @FXML
  private Pane pnlPackagesOverview;

  @FXML
  private Pane pnlPackages;

  @FXML
  private Pane pnlCustomers;

  @FXML
  private Pane pnlBookings;

  @FXML
  private Pane pnlSettings;

  @FXML
  private Pane pnlLogin;

  @FXML
  private TextField txtUserName;

  @FXML
  private PasswordField txtPassword;

  @FXML
  private JFXButton btnLogin;

  @FXML
  private JFXButton btnCancelLogin;

  @FXML
  void onActionBookings(ActionEvent event) {
    pnlBookings.toFront();
  }

  @FXML
  void onActionCancelLogin(ActionEvent event) {
    txtUserName.setText("");
    txtPassword.setText("");
  }

  @FXML
  void onActionCustomers(ActionEvent event) {
    pnlCustomers.toFront();
  }

  @FXML
  void onActionLoginTab(ActionEvent event) {
    pnlLogin.toFront();
  }

  @FXML
  void onActionLogout(ActionEvent event) {
    pnlLogin.toFront();
    btnLoginTab.setVisible(true);
    btnMainMenu.setVisible(false);
    btnPackagesOverview.setVisible(false);
    btnPackages.setVisible(false);
    btnCustomers.setVisible(false);
    btnBookings.setVisible(false);
    btnSettings.setVisible(false);
  }

  @FXML
  void onActionLogin(ActionEvent event) {
    pnlMainMenu.toFront();
    pnlLogin.toBack();
    btnLoginTab.setVisible(false);
    btnMainMenu.setVisible(true);
    btnPackagesOverview.setVisible(true);
    btnPackages.setVisible(true);
    btnCustomers.setVisible(true);
    btnBookings.setVisible(true);
    btnSettings.setVisible(true);
  }

  @FXML
  void onActionMainMenu(ActionEvent event) {
    pnlMainMenu.toFront();
  }

  @FXML
  void onActionPackages(ActionEvent event) {
    pnlPackages.toFront();
  }

  @FXML
  void onActionPackagesOverview(ActionEvent event) {
    pnlPackagesOverview.toFront();
  }

  @FXML
  void onActionSettings(ActionEvent event) {
    pnlSettings.toFront();
  }

  @FXML
  void initialize() {
    assert btnMainMenu != null : "fx:id=\"btnMainMenu\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnPackagesOverview != null : "fx:id=\"btnPackagesOverview\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnPackages != null : "fx:id=\"btnPackages\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnCustomers != null : "fx:id=\"btnCustomers\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnBookings != null : "fx:id=\"btnBookings\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnSettings != null : "fx:id=\"btnSettings\" was not injected: check your FXML file 'sample.fxml'.";
    assert pnlMainMenu != null : "fx:id=\"pnlMainMenu\" was not injected: check your FXML file 'sample.fxml'.";
    assert pnlPackagesOverview != null : "fx:id=\"pnlPackagesOverview\" was not injected: check your FXML file 'sample.fxml'.";
    assert pnlPackages != null : "fx:id=\"pnlPackages\" was not injected: check your FXML file 'sample.fxml'.";
    assert pnlCustomers != null : "fx:id=\"pnlCustomers\" was not injected: check your FXML file 'sample.fxml'.";
    assert pnlBookings != null : "fx:id=\"pnlBookings\" was not injected: check your FXML file 'sample.fxml'.";
    assert pnlSettings != null : "fx:id=\"pnlSettings\" was not injected: check your FXML file 'sample.fxml'.";

  }
}
