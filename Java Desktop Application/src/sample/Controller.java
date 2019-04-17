package sample;

        import com.jfoenix.controls.*;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.input.*;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.layout.Pane;
        import javafx.scene.paint.Color;
        import sample.Model.*;
        import sample.Model.Package;

        import javax.swing.*;
        import java.lang.Class;
        import java.net.URL;
        import java.security.NoSuchAlgorithmException;
        import java.sql.*;
        import java.text.DateFormat;
        import java.time.LocalDate;
        import java.time.format.DateTimeFormatter;
        import java.util.ResourceBundle;

public class Controller implements Initializable {

    // this is the set password prompt text on application load

    String textColour = "#000000";
    String backgroundColour = "#64B5F6";
    String menuColour = "#03A9F4";
    String secondaryColour = "#1E88E5";
    String tertiaryColour = "#5E35B1";

    // Vars for Login page
    final String user = "admin";
    final String passw = PasswordEncryption.MD5("password");

    // customer page observable list
    private ObservableList<Customer> custData = FXCollections.observableArrayList();

    // ---------variables for bookings page----------
    private int customerSelectedBookingDetailId;
    LocalDate bookingStart;
    LocalDate bookingEnd;

    // bookings page observablelists
    private ObservableList<Booking> bookingData = FXCollections.observableArrayList();
    private ObservableList<Region> regionData = FXCollections.observableArrayList();
    private ObservableList<Class1> classData = FXCollections.observableArrayList();
    private ObservableList<Fee> feeData = FXCollections.observableArrayList();
    //objects
    Booking customerSelectedBooking = null;


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
    private TableView<Package> tblPackages;

    @FXML
    private TableColumn<Package, String> colPkgPkgName;

    @FXML
    private TableColumn<Package, LocalDate> colPkgPkgStartDate;

    @FXML
    private TableColumn<Package, LocalDate> colPkgPkgEndDate;

    @FXML
    private TableColumn<Package, String> colPkgPkgDesc;

    @FXML
    private TableColumn<Package, Float> colPkgBasePrice;

    @FXML
    private TableColumn<Package, Float> colPkgAgencyCommission;

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

    // Beginning of Booking Pane

    @FXML
    private Pane pnlBookings;

    @FXML
    private JFXTextField txtBkSearch;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtDestination;

    @FXML
    private JFXTextField txtBasePrice;

    @FXML
    private JFXTextField txtAgencyCommission;

    @FXML
    private Label lblBkSearch;

    @FXML
    private TableView<Booking> gvBookings;

    @FXML
    private TableColumn<Booking, Date> colBkTripStart;

    @FXML
    private TableColumn<Booking, Date> colBkTripEnd;

    @FXML
    private TableColumn<Booking, String> colBkDescription;

    @FXML
    private TableColumn<Booking, String> colBkDestination;

    @FXML
    private TableColumn<Booking, String> colBkBasePrice;

    @FXML
    private TableColumn<Booking, String> colBkAgencyCommission;

    @FXML
    private TableColumn<Booking, String> colBkRegionId;

    @FXML
    private TableColumn<Booking, String> colBkClassId;

    @FXML
    private TableColumn<Booking, String> colBkFeeId;

    @FXML
    private JFXButton btnBkEdit;

    @FXML
    private JFXButton btnBkDelete;

    @FXML
    private JFXButton btnBkSave;

    @FXML
    private ComboBox<Region> cbRegionId;

    @FXML
    private ComboBox<Class1> cbClassId;

    @FXML
    private ComboBox<Fee> cbFeeId;

    @FXML
    private JFXDatePicker txtTripStart;

    @FXML
    private JFXDatePicker txtTripEnd;


    // End of Booking Pane

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
        if (btnAddEditPkg.getText().equals("Save New Package")) {
            saveNewPackage();
        } else if (btnAddEditPkg.getText().equals("Update Package")) {
            updatePackage();
        }


       /* String pkgName = txtPackageName.getText();
        String pkgStartDate = txtPkgStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String pkgEndDate = txtPkgEndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String pkgDesc = txtPkgDesc.getText();
        Float pkgPrice = Float.valueOf(txtPkgBasePrice.getText());
        Float pkgCommission = Float.valueOf(txtPkgAgencyCommission.getText());



        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "harv", "password");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into Packages(PkgName,PkgStartDate,PkgEndDate,PkgDesc,PkgBasePrice,PkgAgencyCommission) " +
                    "VALUES ('" +pkgName+ "','"+pkgStartDate+"','"+pkgEndDate+"','"+pkgDesc+"','"+pkgPrice+"','"+pkgCommission+"')");

            JOptionPane.showMessageDialog( null,"New Package Added");

            clear();

            pnlPackagesOverview.toFront();

            getPackages();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }*/

    }

    @FXML
    void onActionAddPkg(ActionEvent event) {

        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to create a new package?", "Create New Package", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            pnlPackages.toFront();
            txtPackageName.requestFocus();
            btnAddEditPkg.setText("Save New Package");
        } else {
            pnlPackagesOverview.toFront();
        }
    }

    @FXML
    void onActionAddPkgProdSup(ActionEvent event) {

    }

    @FXML
    void onActionBkAdd(ActionEvent event) {

        //getCustomerBooking();

    }

    @FXML
    void onActionBkDelete(ActionEvent event) {

    }

    @FXML
    void onActionBkEdit(ActionEvent event) {

    }

    //save event for bookings page
    // James Cockriell, April 10/19
    @FXML
    void onActionBkSave(ActionEvent event) {

        bookingStart = txtTripStart.getValue();
        bookingEnd = txtTripEnd.getValue();


        if (txtBkSearch.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't searched for a customer");
            alert.showAndWait();
        } else if (checkBookingDates(bookingStart, bookingEnd) == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Trip start date needs to be an earlier date than trip end date.");
            alert.showAndWait();
        } else if (txtDescription.getText().equals("") || txtDestination.getText().equals("") || txtBasePrice.getText().equals("") || txtAgencyCommission.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You need to fill out all of the fields");
            alert.showAndWait();
        } else if (bkTextIsNonNegativeDouble(txtBasePrice.getText()) == false || bkTextIsNonNegativeDouble(txtAgencyCommission.getText()) == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Base Price and Agency Commission fields need to be populated with a non negative number value");
            alert.showAndWait();
        } else {
            saveBookingDetails();
            getCustomerBooking();
        }
    }

    @FXML
    void onActionBkSearch(ActionEvent event) {
        /*getCustomerBooking();*/
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
        clear();

        txtPackageName.requestFocus();
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
        selectedAgent();
        pnlPackages.toFront();
        btnAddEditPkg.setText("Update Package");
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
    void onKeyPressedBkSearch(KeyEvent event) {
        //getCustomerBooking();
    }

    @FXML
    void onKeyTypedBkSearch(KeyEvent event) {
        getCustomerBooking();

    }

    // on mouse event for when user clicks on bookings tableview
    //Author James Cockriell, April 8/19
    @FXML
    void GetCustomerBookingDetails(MouseEvent event) {


        // this is a key line of code to allow me to get the data from the table view and put into object
        customerSelectedBooking = gvBookings.getItems().get(gvBookings.getSelectionModel().getFocusedIndex());

        txtTripStart.setValue(customerSelectedBooking.getTripStart().toLocalDate());
        txtTripEnd.setValue(customerSelectedBooking.getTripEnd().toLocalDate());
        txtDescription.setText(customerSelectedBooking.getDescription());
        txtDestination.setText(customerSelectedBooking.getDestination());

        String bp = customerSelectedBooking.getBasePrice().indexOf(".") < 0 ? customerSelectedBooking.getBasePrice() : customerSelectedBooking.getBasePrice().replaceAll("0*$", "").replaceAll("\\.$", "");
        txtBasePrice.setText(bp);
        String ac = customerSelectedBooking.getAgencyComission().indexOf(".") < 0 ? customerSelectedBooking.getAgencyComission() : customerSelectedBooking.getAgencyComission().replaceAll("0*$", "").replaceAll("\\.$", "");
        txtAgencyCommission.setText(ac);
        autoSelectClass(customerSelectedBooking.getClassId());
        autoSelectRegion(customerSelectedBooking.getRegionId());
        autoSelectFee(customerSelectedBooking.getFeeId());

        enableBkControls();
        // set value in to variable so it can be used class wide
        customerSelectedBookingDetailId = customerSelectedBooking.getBookingDetailId();
    }

    //Ethan Shipley
    //April 7, 2019
    private void autoSelectRegion(String regionCode) {
        for (Region region : regionData) {
            if (region.getRegionName().equals(regionCode)) {
                cbRegionId.setValue(region);
            }
        }
    }

    //Ethan Shipley
    //April 7, 2019
    private void autoSelectClass(String classCode) {
        for (Class1 class1 : classData) {
            if (class1.getClassName().equals(classCode)) {
                cbClassId.setValue(class1);
            }
        }
    }

    //Ethan Shipley
    //April 7, 2019
    private void autoSelectFee(String feeCode) {
        for (Fee fee : feeData) {
            if (fee.getFeeName().equals(feeCode)) {
                cbFeeId.setValue(fee);
            }
        }
    }

    private ObservableList<Package> data = FXCollections.observableArrayList();


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
        getCustomerBooking();
        clearBkControls();

    }

    private String hexi(ColorPicker cp) {
        Color c = cp.getValue();
        return String.format("#%02X%02X%02X",
                (int) (c.getRed() * 255),
                (int) (c.getGreen() * 255),
                (int) (c.getBlue() * 255));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cpSettingsTextColour.setValue(Color.web(textColour));
        cpSettingsBgColour.setValue(Color.web(backgroundColour));
        cpSettingsMenuColour.setValue(Color.web(menuColour));
        cpSettingsSecondaryColour.setValue(Color.web(secondaryColour));
        cpSettingsTertiaryColour.setValue(Color.web(tertiaryColour));

        /*setTextColour();
        setMenuColour();
        setBackgroundColour();
        setSecondaryColour();
        setTertiaryColour();
        getCustomerBooking();*/

        // to load packages table
        getPackages();

        // load the customer table
        getCustomerDetails();

        setTextColour();
        setMenuColour();
        setBackgroundColour();
        setSecondaryColour();
        setTertiaryColour();

    }
    //this populates the Customer table on form load 1st step


    //this is the login method
    private void Login() throws NoSuchAlgorithmException {
        // to do switch

        String name = txtUserName.getText();
        String password = PasswordEncryption.MD5(txtPassword.getText());


        if (name.isEmpty() || password.isEmpty()) {
            PromptTextLogin();
            lblPasswordMessage.setText("All fields are required");
            lblPasswordMessage.setTextFill(Color.rgb(210, 39, 30));
            DisableMenu();

        } else {
            if (name.equals(user) && password.equals(passw)) {
                //lblPasswordMessage.setText("Successfully logged in");
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

    private void Logout() {
        btnLogin.setDisable(false);
        btnLogout.setDisable(false);
        txtPassword.setText("");
        txtUserName.setText("");
        lblPasswordMessage.setText("");
        pnlLogin.toFront();
        PromptTextLogin();
    }


    private void PromptTextLogin() {
        txtUserName.setPromptText("");
        txtPassword.setPromptText("");
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

    // this is the start of the customers pane, Chris' work
    // next step initialize search function on the table view

    private void getCustomerDetails()
    {
        gvCustomer.getItems().clear(); // this clears the table view before the search field is used

        String lastName = txtCustSearch.getText(); // this gets the customer text and puts the value into a String var

        try {
//
            Connection conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "Select * from Customers" + "where CustLastName LIKE '%" + lastName + "%' " +
                    "order by CustLastName DESC;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                custData.add(new Customer(rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10),
                        rs.getString(11)));

            }

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

            gvCustomer.setItems(custData);
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // this is the start of the packages pane, Brando's work
    private void getPackages() {
        ObservableList<Package> packData = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "harv", "password");
            String sql = "select * from Packages";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                packData.add(new Package(rs.getInt(1), rs.getString(2), rs.getDate(3).toLocalDate(),
                        rs.getDate(4).toLocalDate(), rs.getString(5), rs.getFloat(6),
                        rs.getFloat(7)));
            }

            colPkgPkgName.setCellValueFactory(new PropertyValueFactory<Package, String>("pkgName"));
            colPkgPkgStartDate.setCellValueFactory(new PropertyValueFactory<Package, LocalDate>("pkgStartDate"));
            colPkgPkgEndDate.setCellValueFactory(new PropertyValueFactory<Package, LocalDate>("pkgEndDate"));
            colPkgPkgDesc.setCellValueFactory(new PropertyValueFactory<Package, String>("pkgDesc"));
            colPkgBasePrice.setCellValueFactory(new PropertyValueFactory<Package, Float>("pkgBasePrice"));
            colPkgAgencyCommission.setCellValueFactory(new PropertyValueFactory<Package, Float>("pkgAgencyCommission"));

            tblPackages.setItems(packData);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    // method to get booking details of specific customer by last name,
    // txtBkSearch TextField input string value, and return bookingData ObservableList object
    // Author: James Cockriell, Date April 1, 2019
    private void getCustomerBooking() {
        gvBookings.getItems().clear();
        String lname = txtBkSearch.getText();
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT BookingDetailId, TripStart, TripEnd, Description, Destination, cast(BasePrice as char) as BasePrice, cast(AgencyCommission as char) as AgencyCommission, RegionName, ClassName, FeeName " +
                "from (((((bookingDetails " +
                "Inner Join Bookings on BookingDetails.BookingId = Bookings.BookingId) " +
                "Inner Join Customers on Bookings.CustomerId = Customers.CustomerId) " +
                "Inner Join Regions on BookingDetails.RegionId = Regions.RegionId) " +
                "Inner Join Classes on BookingDetails.ClassId = Classes.ClassId) " +
                "Inner Join Fees on BookingDetails.FeeId = Fees.FeeId) " +
                "where Customers.CustLastName LIKE '%" + lname + "%' " +
                "order by TripStart DESC;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                Booking booking = new Booking(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));

                bookingData.add(booking);
            }

            colBkTripStart.setCellValueFactory(new PropertyValueFactory<Booking, Date>("tripStart"));
            colBkTripEnd.setCellValueFactory(new PropertyValueFactory<Booking, Date>("tripEnd"));
            colBkDescription.setCellValueFactory(new PropertyValueFactory<Booking, String>("description"));
            colBkDestination.setCellValueFactory(new PropertyValueFactory<Booking, String>("destination"));
            colBkBasePrice.setCellValueFactory(new PropertyValueFactory<Booking, String>("basePrice"));
            colBkAgencyCommission.setCellValueFactory(new PropertyValueFactory<Booking, String>("agencyComission"));
            colBkRegionId.setCellValueFactory(new PropertyValueFactory<Booking, String>("regionId"));
            colBkClassId.setCellValueFactory(new PropertyValueFactory<Booking, String>("classId"));
            colBkFeeId.setCellValueFactory(new PropertyValueFactory<Booking, String>("feeId"));

            gvBookings.setItems(bookingData);
            conn.close(); // connection close
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadcbRegionId();
        loadcbClassId();
        loadcbFeeId();
    }

    // James Cockriell, April 05/19
    private void loadcbRegionId() {
        regionData.clear();
        Connection conn = DBConnect.getConnection();
        String sql = "select * from Regions";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                regionData.add(new Region(rs.getString(1), rs.getString(2)));
            }


            cbRegionId.setItems(regionData);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // James Cockriell, April 05/19
    private void loadcbClassId() {
        classData.clear();
        Connection conn = DBConnect.getConnection();
        String sql = "select ClassId, ClassName from Classes";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                classData.add(new Class1(rs.getString(1), rs.getString(2)));
                /*Class1 class1 = new Class1(rs.getString(1), rs.getString(2), rs.getString(3));
                classData.add(class1.getClassName());*/
            }

            cbClassId.setItems(classData);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // James Cockriell, April 05/19
    private void loadcbFeeId() {
        feeData.clear();
        Connection conn = DBConnect.getConnection();
        String sql = "select * from fees";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                feeData.add(new Fee(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4)));
            }
            cbFeeId.setItems(feeData);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void clear() {
        txtPackageName.clear();
        txtPkgStartDate.setValue(null);
        txtPkgEndDate.setValue(null);
        txtPkgDesc.clear();
        txtPkgBasePrice.clear();
        txtPkgAgencyCommission.clear();
    }

    private void saveNewPackage() {
        try {
            String pkgName = txtPackageName.getText();
            String pkgStartDate = txtPkgStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String pkgEndDate = txtPkgEndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String pkgDesc = txtPkgDesc.getText();
            Float pkgPrice = Float.valueOf(txtPkgBasePrice.getText());
            Float pkgCommission = Float.valueOf(txtPkgAgencyCommission.getText());

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "brandon", "password");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into Packages(PkgName,PkgStartDate,PkgEndDate,PkgDesc,PkgBasePrice,PkgAgencyCommission) " +
                    "VALUES ('" + pkgName + "','" + pkgStartDate + "','" + pkgEndDate + "','" + pkgDesc + "','" + pkgPrice + "','" + pkgCommission + "')");

            JOptionPane.showMessageDialog(null, "New Package Added");

            clear();

            pnlPackagesOverview.toFront();

            getPackages();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // class to get all of the booking details
    // Author: James Cockriell, Date: April 05/19
    private void saveBookingDetails() {
        String TripStart = txtTripStart.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String TripEnd = txtTripEnd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String Description = txtDescription.getText();
        String Destination = txtDestination.getText();
        String BasePrice = txtBasePrice.getText();
        String AgencyCommission = txtAgencyCommission.getText();
        String RegionId = cbRegionId.getSelectionModel().getSelectedItem().getRegionId();
        String ClassId = cbClassId.getSelectionModel().getSelectedItem().getClassId();
        String FeeId = cbFeeId.getSelectionModel().getSelectedItem().getFeeId();

        Connection conn = DBConnect.getConnection();
        String sql = "update bookingDetails set TripStart=" + "'" + TripStart + "'" + ", TripEnd=" + "'" + TripEnd + "'" + ", Description=" + "'" + Description + "'" + ", Destination=" + "'" + Destination + "'" + ", BasePrice=" + "'" + BasePrice + "'" + ", AgencyCommission= " + "'" + AgencyCommission + "'" + ", RegionId=" + "'" + RegionId + "'" + ", ClassId=" + "'" + ClassId + "'" + ", FeeId=" + "'" + FeeId + "'" + " where BookingDetailId=" + "'" + customerSelectedBookingDetailId + "'";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            int numRows = stmt.executeUpdate();
            if (numRows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were updated.");
                alert.showAndWait();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update Successful");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // change state of booking tab after saving
        getCustomerBooking();
        clearBkControls();
        bookingStart = null;
        bookingEnd = null;
        customerSelectedBooking = null;
        txtBkSearch.setText("");
    }

    private void clearBkControls() {
        txtTripStart.getEditor().setDisable(true);
        //txtTripStart.hide();
        txtTripStart.getEditor().clear();
        txtTripEnd.getEditor().setDisable(true);
        //txtTripEnd.hide();
        txtTripEnd.getEditor().clear();
        txtDescription.setDisable(true);
        txtDescription.setText("");
        txtDestination.setDisable(true);
        txtDestination.setText("");
        txtBasePrice.setDisable(true);
        txtBasePrice.setText("");
        txtAgencyCommission.setDisable(true);
        txtAgencyCommission.setText("");
        cbRegionId.setDisable(true);
        cbRegionId.setValue(null);
        cbClassId.setDisable(true);
        cbClassId.setValue(null);
        cbFeeId.setDisable(true);
        cbFeeId.setValue(null);
    }

    public void enableBkControls() {
        txtTripStart.getEditor().setDisable(false);
        txtTripEnd.getEditor().setDisable(false);
        txtDescription.setDisable(false);
        txtDestination.setDisable(false);
        txtBasePrice.setDisable(false);
        txtAgencyCommission.setDisable(false);
        cbRegionId.setDisable(false);
        cbClassId.setDisable(false);
        cbFeeId.setDisable(false);
    }

    // code to check if booking start date is not later than booking end date.
    //James Cockriell April 10/19
    public boolean checkBookingDates(LocalDate bkStart, LocalDate bkEnd) {
        boolean acceptableBookingDates;
        if (bkEnd.isEqual(bkStart)) {
            acceptableBookingDates = true;
        } else if (bkEnd.isBefore(bkStart)) {
            acceptableBookingDates = false;
        } else {
            acceptableBookingDates = true;
        }
        return acceptableBookingDates;
    }

    // code to verify that text fields have non negative number values.
    // james cockriell, April 12/19
    public boolean bkTextIsNonNegativeDouble(String value) {
        boolean result = true;
        double numberValue = 0;
        try {
            numberValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            result = false;
        }

        if (result == true) {
            if (numberValue < 0) {
                result = false;

            }
        }
        return result;
    }

    private void updatePackage() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "brandon", "password");
            String sql = "update Packages set PkgName=?, PkgStartDate=?, PkgEndDate=?," +
                    "PkgDesc=?,PkgBasePrice=?,PkgAgencyCommission=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtPackageName.getText());
            stmt.setDate(2, Date.valueOf(txtPkgStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            stmt.setDate(3, Date.valueOf(txtPkgEndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            stmt.setString(4, txtPkgDesc.getText());
            stmt.setFloat(5, Float.valueOf(txtPkgBasePrice.getText()));
            stmt.setFloat(6, Float.valueOf(txtPkgAgencyCommission.getText()));

            JOptionPane.showMessageDialog(null, "Package Updated");

            pnlPackagesOverview.toFront();

            getPackages();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void selectedAgent() {
        txtPackageName.setText(tblPackages.getSelectionModel().getSelectedItem().getPkgName());
        txtPkgStartDate.setValue(tblPackages.getSelectionModel().getSelectedItem().getPkgStartDate());
        txtPkgEndDate.setValue(tblPackages.getSelectionModel().getSelectedItem().getPkgEndDate());
        txtPkgDesc.setText(tblPackages.getSelectionModel().getSelectedItem().getPkgDesc());
        txtPkgBasePrice.setText(tblPackages.getSelectionModel().getSelectedItem().getPkgBasePrice().toString());
        txtPkgAgencyCommission.setText(tblPackages.getSelectionModel().getSelectedItem().getPkgAgencyCommission().toString());
    }
}
