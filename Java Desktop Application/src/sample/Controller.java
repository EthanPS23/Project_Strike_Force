package sample;

import com.jfoenix.controls.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
/*import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;*/
import javafx.scene.control.*;

public class Controller {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private JFXButton btnLoginTab;

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
  private JFXButton btnClose;

  @FXML
  private JFXButton fxMinimize;

  @FXML
  private Pane pnlCustomers;

  @FXML
  private Pane pnlBookings;

  @FXML
  private Pane pnlSettings;

  @FXML
  private Pane pnlMainMenu;

  @FXML
  private JFXButton btnLogout;

  @FXML
  private Pane pnlLogin;

  @FXML
  private JFXButton btnLogin;

  @FXML
  private JFXButton btnCancelLogin;

  @FXML
  private JFXPasswordField txtPassword;

  @FXML
  private JFXTextField txtUserName;

  @FXML
  private Pane pnlPackages;

  @FXML
  private Label lblPkgName;

  @FXML
  private Label lblPkgStartDate;

  @FXML
  private Label lblPkgEndDate;

  @FXML
  private Label lblPkgDesc;

  @FXML
  private Label lblPkgBasePrice;

  @FXML
  private Label lblPkgAgencyCommission;

  @FXML
  private JFXDatePicker txtPkgEndDate;

  @FXML
  private JFXTextField txtPackageName;

  @FXML
  private JFXTextField txtPkgDesc;

  @FXML
  private JFXTextField txtPkgBasePrice;

  @FXML
  private JFXTextField txtPkgAgencyCommission;

  @FXML
  private JFXDatePicker txtPkgStartDate;

  @FXML
  private JFXButton btnAddEditPkg;

  @FXML
  private JFXButton btnClearPkg;

  @FXML
  private JFXButton btnNewPkgProdSup;

  @FXML
  private JFXButton btnDeletePkgProdSup;

  @FXML
  private TableView<?> gvProdSup_pkg;

  @FXML
  private TableView<?> gvProdSup_all_pkgs;

  @FXML
  private JFXButton btnAddPkgProdSup;

  @FXML
  private Pane pnlPackagesOverview;

  @FXML
  private TableView<?> tblPackages;

  @FXML
  private TableColumn<?, ?> colPkgPkgName;

  @FXML
  private TableColumn<?, ?> colPkgPkgStartDate;

  @FXML
  private TableColumn<?, ?> colPkgPkgEndDate;

  @FXML
  private TableColumn<?, ?> colPkgPkgDesc;

  @FXML
  private TableColumn<?, ?> colPkgBasePrice;

  @FXML
  private TableColumn<?, ?> colPkgAgencyCommission;

  @FXML
  private TableView<?> tblExistProductsSuppliers;

  @FXML
  private TableColumn<?, ?> colPkgExistProdName;

  @FXML
  private TableColumn<?, ?> colPkgExistSupName;

  @FXML
  private TableView<?> tblNonExistProductsSuppliers;

  @FXML
  private TableColumn<?, ?> colPkgNonExistProdName;

  @FXML
  private TableColumn<?, ?> colPkgNonExistSupName;

  @FXML
  private JFXButton btnAddPkg;

  @FXML
  private JFXButton btnDeletePkg;

  @FXML
  private JFXButton btnEditPkg;

  @FXML
  private JFXButton btnSavePkg;

  @FXML
  private JFXButton btnProdSupRemove;

  @FXML
  private JFXButton btnProdSupAdd;

  @FXML
  void onActionAddEditPkg(ActionEvent event) {

  }

  @FXML
  void onActionAddPkg(ActionEvent event) {

  }

  @FXML
  void onActionAddPkgProdSup(ActionEvent event) {

  }

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
  void onActionClearPkg(ActionEvent event) {

  }

  @FXML
  void onActionClose(ActionEvent event) {
    System.exit(0);
  }

  @FXML
  void onActionCustomers(ActionEvent event) {
    pnlCustomers.toFront();
  }

  @FXML
  void onActionDeletePkg(ActionEvent event) {

  }

  @FXML
  void onActionDeletePkgProdSup(ActionEvent event) {

  }

  @FXML
  void onActionEditPkg(ActionEvent event) {

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
  void onActionMainMenu(ActionEvent event) {
    pnlMainMenu.toFront();
  }

  @FXML
  void onActionMinimize(ActionEvent event) {

  }

  @FXML
  void onActionNewPkgProdSup(ActionEvent event) {

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
  void onActionProdSupAdd(ActionEvent event) {

  }

  @FXML
  void onActionProdSupRemove(ActionEvent event) {

  }

  @FXML
  void onActionSavePkg(ActionEvent event) {

  }

  @FXML
  void onActionSettings(ActionEvent event) {
    pnlSettings.toFront();
  }

  @FXML
  void initialize() {
    assert btnLoginTab != null : "fx:id=\"btnLoginTab\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnMainMenu != null : "fx:id=\"btnMainMenu\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnPackagesOverview != null : "fx:id=\"btnPackagesOverview\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnPackages != null : "fx:id=\"btnPackages\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnCustomers != null : "fx:id=\"btnCustomers\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnBookings != null : "fx:id=\"btnBookings\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnSettings != null : "fx:id=\"btnSettings\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnClose != null : "fx:id=\"btnClose\" was not injected: check your FXML file 'sample.fxml'.";
    assert fxMinimize != null : "fx:id=\"fxMinimize\" was not injected: check your FXML file 'sample.fxml'.";
    assert pnlCustomers != null : "fx:id=\"pnlCustomers\" was not injected: check your FXML file 'sample.fxml'.";
    assert pnlBookings != null : "fx:id=\"pnlBookings\" was not injected: check your FXML file 'sample.fxml'.";
    assert pnlSettings != null : "fx:id=\"pnlSettings\" was not injected: check your FXML file 'sample.fxml'.";
    assert pnlMainMenu != null : "fx:id=\"pnlMainMenu\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'sample.fxml'.";
    assert pnlLogin != null : "fx:id=\"pnlLogin\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnCancelLogin != null : "fx:id=\"btnCancelLogin\" was not injected: check your FXML file 'sample.fxml'.";
    assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'sample.fxml'.";
    assert txtUserName != null : "fx:id=\"txtUserName\" was not injected: check your FXML file 'sample.fxml'.";
    assert pnlPackages != null : "fx:id=\"pnlPackages\" was not injected: check your FXML file 'sample.fxml'.";
    assert lblPkgName != null : "fx:id=\"lblPkgName\" was not injected: check your FXML file 'sample.fxml'.";
    assert lblPkgStartDate != null : "fx:id=\"lblPkgStartDate\" was not injected: check your FXML file 'sample.fxml'.";
    assert lblPkgEndDate != null : "fx:id=\"lblPkgEndDate\" was not injected: check your FXML file 'sample.fxml'.";
    assert lblPkgDesc != null : "fx:id=\"lblPkgDesc\" was not injected: check your FXML file 'sample.fxml'.";
    assert lblPkgBasePrice != null : "fx:id=\"lblPkgBasePrice\" was not injected: check your FXML file 'sample.fxml'.";
    assert lblPkgAgencyCommission != null : "fx:id=\"lblPkgAgencyCommission\" was not injected: check your FXML file 'sample.fxml'.";
    assert txtPkgEndDate != null : "fx:id=\"txtPkgEndDate\" was not injected: check your FXML file 'sample.fxml'.";
    assert txtPackageName != null : "fx:id=\"txtPackageName\" was not injected: check your FXML file 'sample.fxml'.";
    assert txtPkgDesc != null : "fx:id=\"txtPkgDesc\" was not injected: check your FXML file 'sample.fxml'.";
    assert txtPkgBasePrice != null : "fx:id=\"txtPkgBasePrice\" was not injected: check your FXML file 'sample.fxml'.";
    assert txtPkgAgencyCommission != null : "fx:id=\"txtPkgAgencyCommission\" was not injected: check your FXML file 'sample.fxml'.";
    assert txtPkgStartDate != null : "fx:id=\"txtPkgStartDate\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnAddEditPkg != null : "fx:id=\"btnAddEditPkg\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnClearPkg != null : "fx:id=\"btnClearPkg\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnNewPkgProdSup != null : "fx:id=\"btnNewPkgProdSup\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnDeletePkgProdSup != null : "fx:id=\"btnDeletePkgProdSup\" was not injected: check your FXML file 'sample.fxml'.";
    assert gvProdSup_pkg != null : "fx:id=\"gvProdSup_pkg\" was not injected: check your FXML file 'sample.fxml'.";
    assert gvProdSup_all_pkgs != null : "fx:id=\"gvProdSup_all_pkgs\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnAddPkgProdSup != null : "fx:id=\"btnAddPkgProdSup\" was not injected: check your FXML file 'sample.fxml'.";
    assert pnlPackagesOverview != null : "fx:id=\"pnlPackagesOverview\" was not injected: check your FXML file 'sample.fxml'.";
    assert tblPackages != null : "fx:id=\"tblPackages\" was not injected: check your FXML file 'sample.fxml'.";
    assert colPkgPkgName != null : "fx:id=\"colPkgPkgName\" was not injected: check your FXML file 'sample.fxml'.";
    assert colPkgPkgStartDate != null : "fx:id=\"colPkgPkgStartDate\" was not injected: check your FXML file 'sample.fxml'.";
    assert colPkgPkgEndDate != null : "fx:id=\"colPkgPkgEndDate\" was not injected: check your FXML file 'sample.fxml'.";
    assert colPkgPkgDesc != null : "fx:id=\"colPkgPkgDesc\" was not injected: check your FXML file 'sample.fxml'.";
    assert colPkgBasePrice != null : "fx:id=\"colPkgBasePrice\" was not injected: check your FXML file 'sample.fxml'.";
    assert colPkgAgencyCommission != null : "fx:id=\"colPkgAgencyCommission\" was not injected: check your FXML file 'sample.fxml'.";
    assert tblExistProductsSuppliers != null : "fx:id=\"tblExistProductsSuppliers\" was not injected: check your FXML file 'sample.fxml'.";
    assert colPkgExistProdName != null : "fx:id=\"colPkgExistProdName\" was not injected: check your FXML file 'sample.fxml'.";
    assert colPkgExistSupName != null : "fx:id=\"colPkgExistSupName\" was not injected: check your FXML file 'sample.fxml'.";
    assert tblNonExistProductsSuppliers != null : "fx:id=\"tblNonExistProductsSuppliers\" was not injected: check your FXML file 'sample.fxml'.";
    assert colPkgNonExistProdName != null : "fx:id=\"colPkgNonExistProdName\" was not injected: check your FXML file 'sample.fxml'.";
    assert colPkgNonExistSupName != null : "fx:id=\"colPkgNonExistSupName\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnAddPkg != null : "fx:id=\"btnAddPkg\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnDeletePkg != null : "fx:id=\"btnDeletePkg\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnEditPkg != null : "fx:id=\"btnEditPkg\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnSavePkg != null : "fx:id=\"btnSavePkg\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnProdSupRemove != null : "fx:id=\"btnProdSupRemove\" was not injected: check your FXML file 'sample.fxml'.";
    assert btnProdSupAdd != null : "fx:id=\"btnProdSupAdd\" was not injected: check your FXML file 'sample.fxml'.";

  }
}
