package sample;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.Model.Customer;
import sample.Model.PasswordEncryption;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // this is the set password prompt text on application load

    String textColour = "#000000";
    String backgroundColour = "#64B5F6";
    String menuColour = "#03A9F4";
    String secondaryColour = "#1E88E5";
    String tertiaryColour = "#5E35B1";

    final String user = "admin";
    final String passw = PasswordEncryption.MD5("password");

    public Controller() throws NoSuchAlgorithmException {
    }

    private void setTextColour() {
        apEverything.setStyle("-label-text-colour: " + textColour + ";");
    }

    private void setBackgroundColour() {
        pnlBookings.setStyle("-fx-background-color: " + backgroundColour + ";");
        pnlCustomers.setStyle("-fx-background-color: " + backgroundColour + ";");
        pnlLogin.setStyle("-fx-background-color: " + backgroundColour + ";");
        pnlMainMenu.setStyle("-fx-background-color: " + backgroundColour + ";");
        pnlPackages.setStyle("-fx-background-color: " + backgroundColour + ";");
        pnlPackagesOverview.setStyle("-fx-background-color: " + backgroundColour + ";");
        pnlSettings.setStyle("-fx-background-color: " + backgroundColour + ";");
    }

    private void setMenuColour() {
        apMenu.setStyle("-fx-background-color: " + menuColour + ";");
        apToolbar.setStyle("-fx-background-color: " + menuColour + ";");
    }

    private void setSecondaryColour() {
        apItems.setStyle("-btn-bg-colour: " + secondaryColour + ";" +
                "-secondary-bg-colour: " + secondaryColour + ";" +
                "-btn-brdr-colour: " + tertiaryColour + ";" +
                "-tertiary-brdr-colour: " + tertiaryColour + ";");
    }

    private void setTertiaryColour() {
        apItems.setStyle("-btn-brdr-colour: " + tertiaryColour + ";" +
                "-tertiary-brdr-colour: " + tertiaryColour + ";" +
                "-btn-bg-colour: " + secondaryColour + ";" +
                "-secondary-bg-colour: " + secondaryColour + ";");
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane apEverything;

    @FXML
    private AnchorPane apMenu;

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
    private AnchorPane apItems;

    @FXML

    private Pane pnlSettings;

    @FXML
    private Pane pnlLogin;

    @FXML
    private Label lblLoginUserName;

    @FXML
    private Label lblLoginPassword;

    @FXML
    private Label lblPasswordMessage;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnCancelLogin;

    @FXML
    private JFXButton btnMainLogout;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private Pane pnlMainMenu;

    @FXML
    private JFXButton btnLogout;

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

    // Start of Customer Pane
    @FXML
    private Pane pnlCustomers;

    @FXML
    private JFXTextField txtCustSearch;

    @FXML
    private Label lblCustSearch;

    @FXML
    private TableView<Customer> gvCustomer;

    @FXML
    private TableColumn<Customer, String> colCustFirstName;

    @FXML
    private TableColumn<Customer, String> colCustLastName;

    @FXML
    private TableColumn<Customer, String> colCustAddress;

    @FXML
    private TableColumn<Customer, String> colCustCity;

    @FXML
    private TableColumn<Customer, String> colCustProvince;

    @FXML
    private TableColumn<Customer, String> colCustPostalCode;

    @FXML
    private TableColumn<Customer, String> colCustCountry;

    @FXML
    private TableColumn<Customer, String> colCustHomePhone;

    @FXML
    private TableColumn<Customer, String> colCustBusinessPhone;

    @FXML
    private TableColumn<Customer, String> colCustEmail;

    @FXML
    private JFXButton btnCustAdd;

    @FXML
    private JFXButton btnCustEdit;

    @FXML
    private JFXButton btnCustDelete;

    @FXML
    private JFXButton btnCustSave;

    //End of Customer Pane

    @FXML

    private Pane pnlBookings;

    @FXML
    private JFXTextField txtBkSearch;

    @FXML
    private Label lblBkSearch;

    @FXML
    private TableView<?> gvBookings;

    @FXML
    private TableColumn<?, ?> colBkTripStart;

    @FXML
    private TableColumn<?, ?> colBkTripEnd;

    @FXML
    private TableColumn<?, ?> colBkDescription;

    @FXML
    private TableColumn<?, ?> colBkDestination;

    @FXML
    private TableColumn<?, ?> colBkBasePrice;

    @FXML
    private TableColumn<?, ?> colBkAgencyCommission;

    @FXML
    private TableColumn<?, ?> colBkRegionId;

    @FXML
    private TableColumn<?, ?> colBkClassId;

    @FXML
    private TableColumn<?, ?> colBkFeeId;

    @FXML
    private JFXButton btnBkAdd;

    @FXML
    private JFXButton btnBkEdit;

    @FXML
    private JFXButton btnBkDelete;

    @FXML
    private JFXButton btnBkSave;

    @FXML
    private JFXColorPicker cpSettingsTextColour;

    @FXML
    private Label lblSettingsTextColour;

    @FXML
    private JFXColorPicker cpSettingsBgColour;

    @FXML
    private Label lblSettingsBgColour;

    @FXML
    private JFXColorPicker cpSettingsSecondaryColour;

    @FXML
    private Label lblSettingsSecondaryColour;

    @FXML
    private JFXColorPicker cpSettingsTertiaryColour;

    @FXML
    private Label lblSettingsTertiaryColour;

    @FXML
    private JFXColorPicker cpSettingsMenuColour;

    @FXML
    private Label lblSettingsMenuColour;

    @FXML
    private AnchorPane apToolbar;

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXButton fxMinimize;


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
    void onActionBkAdd(ActionEvent event) {

    }

    @FXML
    void onActionBkDelete(ActionEvent event) {

    }

    @FXML
    void onActionBkEdit(ActionEvent event) {

    }

    @FXML
    void onActionBkSave(ActionEvent event) {

    }

    @FXML
    void onActionBkSearch(ActionEvent event) {

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
    void onActionCustAdd(ActionEvent event) {

    }

    @FXML
    void onActionCustDelete(ActionEvent event) {

    }

    @FXML
    void onActionCustEdit(ActionEvent event) {

    }

    @FXML
    void onActionCustSave(ActionEvent event) {

    }

    @FXML
    void onActionCustSearch(ActionEvent event) {

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
    void onActionLogin(ActionEvent event) throws NoSuchAlgorithmException {
        Login();
    }


    @FXML
    void onActionLoginTab(ActionEvent event) {
        pnlLogin.toFront();
        PromptTextLogin();

    }

    @FXML
    void onActionLogout(ActionEvent event) {
        pnlLogin.toFront();
        btnLoginTab.setVisible(true);
    }

    @FXML
    void onMainLogout(ActionEvent event) {
        Logout();
        DisableMenu();
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
    void onActionSettingsBgColour(ActionEvent event) {
        backgroundColour = hexi(cpSettingsBgColour);
        setBackgroundColour();
    }

    @FXML
    void onActionSettingsMenuColour(ActionEvent event) {
        menuColour = hexi(cpSettingsMenuColour);
        setMenuColour();
    }

    @FXML
    void onActionSettingsSecondaryColour(ActionEvent event) {
        secondaryColour = hexi(cpSettingsSecondaryColour);
        setSecondaryColour();
    }

    @FXML
    void onActionSettingsTertiaryColour(ActionEvent event) {
        tertiaryColour = hexi(cpSettingsTertiaryColour);
        setTertiaryColour();
    }

    @FXML
    void onActionSettingsTextColour(ActionEvent event) {
        textColour = hexi(cpSettingsTextColour);
        setTextColour();
    }

    @FXML
    void initialize() {
        cpSettingsTextColour.setValue(Color.web(textColour));
        cpSettingsBgColour.setValue(Color.web(backgroundColour));
        cpSettingsMenuColour.setValue(Color.web(menuColour));
        cpSettingsSecondaryColour.setValue(Color.web(secondaryColour));
        cpSettingsTertiaryColour.setValue(Color.web(tertiaryColour));

        assert apEverything != null : "fx:id=\"apEverything\" was not injected: check your FXML file 'sample.fxml'.";
        assert apMenu != null : "fx:id=\"apMenu\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnLoginTab != null : "fx:id=\"btnLoginTab\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnMainMenu != null : "fx:id=\"btnMainMenu\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnPackagesOverview != null : "fx:id=\"btnPackagesOverview\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnPackages != null : "fx:id=\"btnPackages\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnCustomers != null : "fx:id=\"btnCustomers\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnBookings != null : "fx:id=\"btnBookings\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnSettings != null : "fx:id=\"btnSettings\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnClose != null : "fx:id=\"btnClose\" was not injected: check your FXML file 'sample.fxml'.";
        assert fxMinimize != null : "fx:id=\"fxMinimize\" was not injected: check your FXML file 'sample.fxml'.";
        assert apItems != null : "fx:id=\"apItems\" was not injected: check your FXML file 'sample.fxml'.";
        assert pnlBookings != null : "fx:id=\"pnlBookings\" was not injected: check your FXML file 'sample.fxml'.";
        assert pnlLogin != null : "fx:id=\"pnlLogin\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblLoginUserName != null : "fx:id=\"lblLoginUserName\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblLoginPassword != null : "fx:id=\"lblLoginPassword\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnCancelLogin != null : "fx:id=\"btnCancelLogin\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtUserName != null : "fx:id=\"txtUserName\" was not injected: check your FXML file 'sample.fxml'.";
        assert pnlMainMenu != null : "fx:id=\"pnlMainMenu\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'sample.fxml'.";
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
        assert pnlCustomers != null : "fx:id=\"pnlCustomers\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtCustSearch != null : "fx:id=\"txtCustSearch\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblCustSearch != null : "fx:id=\"lblCustSearch\" was not injected: check your FXML file 'sample.fxml'.";
        assert gvCustomer != null : "fx:id=\"gvCustomer\" was not injected: check your FXML file 'sample.fxml'.";
        assert colCustFirstName != null : "fx:id=\"colCustFirstName\" was not injected: check your FXML file 'sample.fxml'.";
        assert colCustLastName != null : "fx:id=\"colCustLastName\" was not injected: check your FXML file 'sample.fxml'.";
        assert colCustAddress != null : "fx:id=\"colCustAddress\" was not injected: check your FXML file 'sample.fxml'.";
        assert colCustCity != null : "fx:id=\"colCustCity\" was not injected: check your FXML file 'sample.fxml'.";
        assert colCustProvince != null : "fx:id=\"colCustProvince\" was not injected: check your FXML file 'sample.fxml'.";
        assert colCustPostalCode != null : "fx:id=\"colCustPostalCode\" was not injected: check your FXML file 'sample.fxml'.";
        assert colCustCountry != null : "fx:id=\"colCustCountry\" was not injected: check your FXML file 'sample.fxml'.";
        assert colCustHomePhone != null : "fx:id=\"colCustHomePhone\" was not injected: check your FXML file 'sample.fxml'.";
        assert colCustBusinessPhone != null : "fx:id=\"colCustBusinessPhone\" was not injected: check your FXML file 'sample.fxml'.";
        assert colCustEmail != null : "fx:id=\"colCustEmail\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnCustAdd != null : "fx:id=\"btnCustAdd\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnCustEdit != null : "fx:id=\"btnCustEdit\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnCustDelete != null : "fx:id=\"btnCustDelete\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnCustSave != null : "fx:id=\"btnCustSave\" was not injected: check your FXML file 'sample.fxml'.";
        assert pnlSettings != null : "fx:id=\"pnlSettings\" was not injected: check your FXML file 'sample.fxml'.";
        assert cpSettingsTextColour != null : "fx:id=\"cpSettingsTextColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblSettingsTextColour != null : "fx:id=\"lblSettingsTextColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert cpSettingsBgColour != null : "fx:id=\"cpSettingsBgColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblSettingsBgColour != null : "fx:id=\"lblSettingsBgColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert cpSettingsSecondaryColour != null : "fx:id=\"cpSettingsSecondaryColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblSettingsSecondaryColour != null : "fx:id=\"lblSettingsSecondaryColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert cpSettingsTertiaryColour != null : "fx:id=\"cpSettingsTertiaryColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblSettingsTertiaryColour != null : "fx:id=\"lblSettingsTertiaryColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert cpSettingsMenuColour != null : "fx:id=\"cpSettingsMenuColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblSettingsMenuColour != null : "fx:id=\"lblSettingsMenuColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert apToolbar != null : "fx:id=\"apToolbar\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnClose != null : "fx:id=\"btnClose\" was not injected: check your FXML file 'sample.fxml'.";
        assert fxMinimize != null : "fx:id=\"fxMinimize\" was not injected: check your FXML file 'sample.fxml'.";

        setTextColour();
        setMenuColour();
        setBackgroundColour();
        setSecondaryColour();
        setTertiaryColour();
    }

    private String hexi(ColorPicker cp) {
        Color c = cp.getValue();
        return String.format("#%02X%02X%02X",
                (int) (c.getRed() * 255),
                (int) (c.getGreen() * 255),
                (int) (c.getBlue() * 255));
    }

    //this populates the Customer table on form load 1st step
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            ObservableList<Customer> custData = FXCollections.observableArrayList();
            // sets the columns to the customer object properties

            colCustFirstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustFirstName"));
            colCustLastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustLastName"));
            colCustAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustFirstName"));
            colCustCity.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustCity"));
            colCustProvince.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustProv"));
            colCustPostalCode.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustPostal"));
            colCustCountry.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustCountry"));
            colCustHomePhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustHomePhone"));
            colCustBusinessPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustBusPhone"));
            colCustEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustEmail"));

            try {
//                Class.forName("com.mysql.jdbc.Driver");

//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts",
//                        "Chris", "password");// this is temporary till tomorrow until I can bring in Harv's Travel Experts
                Connection conn = DBConnect.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from customers");
                while (rs.next()) {
                    custData.add(new Customer(rs.getString(2),
                            rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8),
                            rs.getString(9), rs.getString(10),
                            rs.getString(11)));
                }
                gvCustomer.setItems(custData);
            } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //this is the login method
    private void Login() throws NoSuchAlgorithmException {

        String name = txtUserName.getText();
        String password =  PasswordEncryption.MD5(txtPassword.getText());


        if (name.isEmpty() || password.isEmpty()) {
            PromptTextLogin();
            lblPasswordMessage.setText("All fields are required");
            lblPasswordMessage.setTextFill(Color.rgb(210, 39, 30));
            DisableMenu();

        } else {
            if (name.equals(user) && password.equals(passw)) {
                lblPasswordMessage.setText("Successfully logged in");
                lblPasswordMessage.setTextFill(Color.rgb(21, 117, 84));
                EnableMenu();

            } else {
                PromptTextLogin();
                lblPasswordMessage.setText("Incorrect login information");
                lblPasswordMessage.setTextFill(Color.rgb(210, 39, 30));
                DisableMenu();
            }

        }


    }

    private void Logout ()
    {
        btnLogin.setDisable(false);
        btnLogout.setDisable(false);
        txtPassword.setText("");
        txtUserName.setText("");
        lblPasswordMessage.setText("");
        pnlLogin.toFront();
        PromptTextLogin();
    }


    private void PromptTextLogin() {
        txtUserName.setPromptText("Please enter your username");
        txtPassword.setPromptText("Please enter your password");
    }

    private void EnableMenu() {

        pnlMainMenu.toFront();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("You are now successfully logged in");
        alert.showAndWait();

        pnlLogin.toBack();
        btnLoginTab.setVisible(false);
        btnMainMenu.setVisible(true);
        btnPackagesOverview.setVisible(true);
        btnPackages.setVisible(true);
        btnCustomers.setVisible(true);
        btnBookings.setVisible(true);
        btnSettings.setVisible(true);
    }


    private void DisableMenu() {
        btnMainMenu.setVisible(false);
        btnPackagesOverview.setVisible(false);
        btnPackages.setVisible(false);
        btnCustomers.setVisible(false);
        btnBookings.setVisible(false);
        btnSettings.setVisible(false);
    }

}
