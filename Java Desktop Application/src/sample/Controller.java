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
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

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

    // variables for CustomerDetails Pane that populates the textfields after a mouse event from the table view
    Customer customerSelectedDetails;
    private int customerSelectedDetailId;

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

    ObservableList<Package> packData = FXCollections.observableArrayList();
    Package selectedPackage = null;
    private int packId;
    LocalDate pStart;
    LocalDate pEnd;

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

    @FXML // fx:id="lblRegionId"
    private Label lblRegionId; // Value injected by FXMLLoader

    @FXML // fx:id="lblClassId"
    private Label lblClassId; // Value injected by FXMLLoader

    @FXML // fx:id="lblFeeId"
    private Label lblFeeId; // Value injected by FXMLLoader

    @FXML // fx:id="lblTripStart"
    private Label lblTripStart; // Value injected by FXMLLoader

    @FXML // fx:id="lblTripEnd"
    private Label lblTripEnd; // Value injected by FXMLLoader

    @FXML // fx:id="lblDescription"
    private Label lblDescription; // Value injected by FXMLLoader

    @FXML // fx:id="lblDestination"
    private Label lblDestination; // Value injected by FXMLLoader

    @FXML // fx:id="lblBasePrice"
    private Label lblBasePrice; // Value injected by FXMLLoader

    @FXML
    private JFXDatePicker txtPkgStartDate;

    @FXML
    private JFXButton btnAddEditPkg;

    @FXML
    private JFXButton btnClearPkg;

    @FXML // fx:id="txtPkgSearch"
    private JFXTextField txtPkgSearch; // Value injected by FXMLLoader

    @FXML // fx:id="lblPkgSearch"
    private Label lblPkgSearch; // Value injected by FXMLLoader

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

    @FXML // fx:id="lblCustFirstName"
    private Label lblCustFirstName; // Value injected by FXMLLoader

    @FXML // fx:id="lblCustLastName"
    private Label lblCustLastName; // Value injected by FXMLLoader

    @FXML // fx:id="lblCustAddress"
    private Label lblCustAddress; // Value injected by FXMLLoader

    @FXML // fx:id="lblCustCity"
    private Label lblCustCity; // Value injected by FXMLLoader

    @FXML // fx:id="lblCustProv"
    private Label lblCustProv; // Value injected by FXMLLoader

    @FXML // fx:id="lblCustPostal"
    private Label lblCustPostal; // Value injected by FXMLLoader

    @FXML // fx:id="lblCustCountry"
    private Label lblCustCountry; // Value injected by FXMLLoader

    @FXML // fx:id="lblCustHomePhone"
    private Label lblCustHomePhone; // Value injected by FXMLLoader

    @FXML // fx:id="lblCustBusPhone"
    private Label lblCustBusPhone; // Value injected by FXMLLoader

    @FXML // fx:id="lblCustEmail"
    private Label lblCustEmail; // Value injected by FXMLLoader

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

    @FXML // fx:id="lblAgencyCommission"
    private Label lblAgencyCommission; // Value injected by FXMLLoader

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
            clear();
            pnlPackagesOverview.toFront();
        }
        else if (btnAddEditPkg.getText().equals("Update Package")) {
            updatePackage();
            clear();
            pnlPackagesOverview.toFront();
        }
        else if (btnAddEditPkg.getText().equals("Delete Package")) {
            deletePackage();
            clear();
            pnlPackagesOverview.toFront();
        }

    }

    @FXML
    void onActionAddPkg(ActionEvent event) {

        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to create a new package?", "Create New Package", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
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

    }

    @FXML
    void onActionBkDelete(ActionEvent event) {

    }

    @FXML
    void onActionBkEdit(ActionEvent event) {

    }

    // save event for bookings page
    // James Cockriell, April 10/19
    @FXML
    void onActionBkSave(ActionEvent event) {

        bookingStart = txtTripStart.getValue();
        bookingEnd = txtTripEnd.getValue();

        if (txtBkSearch.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't searched for a customer");
            alert.showAndWait();
        } else if (checkBookingDates(bookingStart, bookingEnd) == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Trip start date needs to be an earlier date than trip end date.");
            alert.showAndWait();
        } else if (txtDescription.getText().equals("") || txtDestination.getText().equals("") || txtBasePrice.getText().equals("") || txtAgencyCommission.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You need to fill out all of the fields");
            alert.showAndWait();
        } else if (bkTextIsNonNegativeDouble(txtBasePrice.getText()) == false || bkTextIsNonNegativeDouble(txtAgencyCommission.getText()) == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Base Price and Agency Commission fields need to be populated with a non negative number value");
            alert.showAndWait();
        } else {
            saveBookingDetails();
            getCustomerBooking();
        }
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
        clear();
        txtPackageName.requestFocus();
    }

    @FXML
    void onActionClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionCustAdd(ActionEvent event) {
        int addcustomer = JOptionPane.showConfirmDialog(null, "Are you sure you want to add a customer record?",
                "Add a Customer", JOptionPane.YES_NO_OPTION);

        if ((addcustomer == JOptionPane.YES_OPTION) && (valFields(txtCustFirstName.getText()) || valFields(txtCustLastName.getText()) ||
                valFields(txtCustAddress.getText()) || valFields(txtCustCity.getText()) || valFields(txtCustProv.getText())) ||
                valFields(txtCustPostal.getText()) || valFields(txtCustCountry.getText()) || valFields(txtCustHomePhone.getText()) ||
                valFields(txtCustBusPhone.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "All of the Customer Information needs to be filled out");
            alert.showAndWait();
        }

        else if ((addcustomer == JOptionPane.YES_OPTION) && ! valPhone(txtCustHomePhone.getText()) || ! valPhone(txtCustBusPhone.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "The phone number entered needs to be in proper numeric format");
            alert.showAndWait();
        }

        else if ((addcustomer == JOptionPane.YES_OPTION) && ! valEmail(txtCustEmail.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "The customer email needs to be in proper format");
            alert.showAndWait();
        }

        else if ((addcustomer == JOptionPane.YES_OPTION) && ! valPostalCode(txtCustPostal.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Postal code needs to be in a proper format");
            alert.showAndWait();
        }

        else if (addcustomer == JOptionPane.YES_OPTION)
        {
            CustomerDB.insertCustomer(customer());
            //insertCustomer();
            clear();
            getCustomerSearch();
            getCustomerSearch();
        }

        else
        {
            pnlCustomers.toFront();
            txtCustSearch.requestFocus();
        }
    }
    private Customer customer(){
        Customer cust = new Customer(-1, txtCustFirstName.getText(), txtCustLastName.getText(), txtCustAddress.getText(),
                txtCustCity.getText(), txtCustProv.getText(), txtCustPostal.getText(), txtCustCountry.getText(), txtCustHomePhone.getText(),
                txtCustBusPhone.getText(), txtCustEmail.getText());
        return cust;
    }

    @FXML
    void onActionCustDelete(ActionEvent event) {
        DeleteCustomer();
    }

    @FXML
    void onActionCustEdit(ActionEvent event) {
       EnableFields();
    }

    @FXML
    void onActionCustSave(ActionEvent event) {

        int savecustomer = JOptionPane.showConfirmDialog(null, "Are you sure you want to add a customer record?",
                "Add a Customer", JOptionPane.YES_NO_OPTION);

        if ((savecustomer == JOptionPane.YES_OPTION) && (valFields(txtCustFirstName.getText()) || valFields(txtCustLastName.getText()) ||
                valFields(txtCustAddress.getText()) || valFields(txtCustCity.getText()) || valFields(txtCustProv.getText())) ||
                valFields(txtCustPostal.getText()) || valFields(txtCustCountry.getText()) || valFields(txtCustHomePhone.getText()) ||
                valFields(txtCustBusPhone.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "All of the Customer Information needs to be filled out");
            alert.showAndWait();
        }

        else if ((savecustomer == JOptionPane.YES_OPTION) && ! valPhone(txtCustHomePhone.getText()) || ! valPhone(txtCustBusPhone.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "The phone number entered needs to be in proper numeric format");
            alert.showAndWait();
        }

        else if ((savecustomer == JOptionPane.YES_OPTION) && ! valEmail(txtCustEmail.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "The customer email needs to be in proper format");
            alert.showAndWait();
        }

        else if ((savecustomer == JOptionPane.YES_OPTION) && ! valPostalCode(txtCustPostal.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Postal code needs to be in a proper format");
            alert.showAndWait();
        }

        else if (savecustomer == JOptionPane.YES_OPTION)
        {
            saveCustomerDetails();
            getCustomerSearch();
            pnlCustomers.toFront();
            txtCustSearch.requestFocus();
        }

    }

    @FXML
    void onActionCustSearch(ActionEvent event) {

    }

    @FXML
    private JFXTextField txtCustFirstName;

    @FXML
    private JFXTextField txtCustLastName;

    @FXML
    private JFXTextField txtCustAddress;

    @FXML
    private JFXTextField txtCustCity;

    @FXML
    private JFXTextField txtCustProv;

    @FXML
    private JFXTextField txtCustPostal;

    @FXML
    private JFXTextField txtCustCountry;

    @FXML
    private JFXTextField txtCustHomePhone;

    @FXML
    private JFXTextField txtCustBusPhone;

    @FXML
    private JFXTextField txtCustEmail;


    @FXML
    void onActionCustomers(ActionEvent event) {
        pnlCustomers.toFront();
    }

    @FXML
    void onActionDeletePkg(ActionEvent event) {
        int reply = JOptionPane.showConfirmDialog( null,"Are you sure you want to continue to delete package?", "Delete Package", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            selectedPackage();
            btnAddEditPkg.setText("Delete Package");
            btnClearPkg.setVisible(false);
        }
        else {
            pnlPackagesOverview.toFront();
            btnClearPkg.setVisible(true);
        }
    }

    @FXML
    void onActionDeletePkgProdSup(ActionEvent event) {

    }

    @FXML
    void onActionEditPkg(ActionEvent event) {
        selectedPackage();
        btnAddEditPkg.setText("Update Package");
    }

    @FXML
    void onActionLogin(ActionEvent event) throws NoSuchAlgorithmException {
        Login();
    }


    @FXML
    void onActionLoginTab(ActionEvent event) {
        pnlLogin.toFront();
    }

    @FXML
    void onActionLogout(ActionEvent event) {
        pnlLogin.toFront();
        btnLoginTab.setVisible(true);
        Logout();
        EnableMenu(false);
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
    void onActionPackagesOverview(ActionEvent event) {
        pnlPackagesOverview.toFront();
    }

    @FXML
    void onActionPkgSearch(ActionEvent event) {

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

    }

    @FXML
    void onKeyPressedPkgSearch(KeyEvent event) {

    }

    @FXML
    void onKeyTypedBkSearch(KeyEvent event) {
        getCustomerBooking();

    }

    @FXML
    void onKeyTypedCustSearch(KeyEvent event) {
        getCustomerSearch();
    }

    @FXML
    void onKeyTypedPkgSearch(KeyEvent event) {

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

    @FXML
    void getCustomerDetails(MouseEvent event) {
        populateCustomerDetails();
    }

    private void populateCustomerDetails ()
    {
        customerSelectedDetails = gvCustomer.getItems().get(gvCustomer.getSelectionModel().getFocusedIndex());
        txtCustFirstName.setText(customerSelectedDetails.getCustFirstName());
        txtCustLastName.setText(customerSelectedDetails.getCustLastName());
        txtCustAddress.setText(customerSelectedDetails.getCustAddress());
        txtCustCity.setText(customerSelectedDetails.getCustCity());
        txtCustProv.setText(customerSelectedDetails.getCustProv());
        txtCustPostal.setText(customerSelectedDetails.getCustPostal());
        txtCustCountry.setText(customerSelectedDetails.getCustCountry());
        txtCustHomePhone.setText(customerSelectedDetails.getCustHomePhone());
        txtCustBusPhone.setText(customerSelectedDetails.getCustBusPhone());
        txtCustEmail.setText(customerSelectedDetails.getCustEmail());

        customerSelectedDetailId = customerSelectedDetails.getCustomerId();
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
        assert btnCustomers != null : "fx:id=\"btnCustomers\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnBookings != null : "fx:id=\"btnBookings\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnSettings != null : "fx:id=\"btnSettings\" was not injected: check your FXML file 'sample.fxml'.";
        assert apItems != null : "fx:id=\"apItems\" was not injected: check your FXML file 'sample.fxml'.";
        assert pnlSettings != null : "fx:id=\"pnlSettings\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblSettingsTextColour != null : "fx:id=\"lblSettingsTextColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert cpSettingsTextColour != null : "fx:id=\"cpSettingsTextColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblSettingsBgColour != null : "fx:id=\"lblSettingsBgColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert cpSettingsBgColour != null : "fx:id=\"cpSettingsBgColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblSettingsMenuColour != null : "fx:id=\"lblSettingsMenuColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert cpSettingsMenuColour != null : "fx:id=\"cpSettingsMenuColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblSettingsSecondaryColour != null : "fx:id=\"lblSettingsSecondaryColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert cpSettingsSecondaryColour != null : "fx:id=\"cpSettingsSecondaryColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblSettingsTertiaryColour != null : "fx:id=\"lblSettingsTertiaryColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert cpSettingsTertiaryColour != null : "fx:id=\"cpSettingsTertiaryColour\" was not injected: check your FXML file 'sample.fxml'.";
        assert pnlMainMenu != null : "fx:id=\"pnlMainMenu\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'sample.fxml'.";
        assert pnlLogin != null : "fx:id=\"pnlLogin\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblLoginUserName != null : "fx:id=\"lblLoginUserName\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtUserName != null : "fx:id=\"txtUserName\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblLoginPassword != null : "fx:id=\"lblLoginPassword\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnCancelLogin != null : "fx:id=\"btnCancelLogin\" was not injected: check your FXML file 'sample.fxml'.";
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
        assert lblCustFirstName != null : "fx:id=\"lblCustFirstName\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtCustFirstName != null : "fx:id=\"txtCustFirstName\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblCustLastName != null : "fx:id=\"lblCustLastName\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtCustLastName != null : "fx:id=\"txtCustLastName\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblCustAddress != null : "fx:id=\"lblCustAddress\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtCustAddress != null : "fx:id=\"txtCustAddress\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblCustCity != null : "fx:id=\"lblCustCity\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtCustCity != null : "fx:id=\"txtCustCity\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblCustProv != null : "fx:id=\"lblCustProv\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtCustProv != null : "fx:id=\"txtCustProv\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblCustPostal != null : "fx:id=\"lblCustPostal\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtCustPostal != null : "fx:id=\"txtCustPostal\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblCustCountry != null : "fx:id=\"lblCustCountry\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtCustCountry != null : "fx:id=\"txtCustCountry\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblCustHomePhone != null : "fx:id=\"lblCustHomePhone\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtCustHomePhone != null : "fx:id=\"txtCustHomePhone\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblCustBusPhone != null : "fx:id=\"lblCustBusPhone\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtCustBusPhone != null : "fx:id=\"txtCustBusPhone\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblCustEmail != null : "fx:id=\"lblCustEmail\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtCustEmail != null : "fx:id=\"txtCustEmail\" was not injected: check your FXML file 'sample.fxml'.";
        assert pnlBookings != null : "fx:id=\"pnlBookings\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtBkSearch != null : "fx:id=\"txtBkSearch\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblBkSearch != null : "fx:id=\"lblBkSearch\" was not injected: check your FXML file 'sample.fxml'.";
        assert gvBookings != null : "fx:id=\"gvBookings\" was not injected: check your FXML file 'sample.fxml'.";
        assert colBkTripStart != null : "fx:id=\"colBkTripStart\" was not injected: check your FXML file 'sample.fxml'.";
        assert colBkTripEnd != null : "fx:id=\"colBkTripEnd\" was not injected: check your FXML file 'sample.fxml'.";
        assert colBkDescription != null : "fx:id=\"colBkDescription\" was not injected: check your FXML file 'sample.fxml'.";
        assert colBkDestination != null : "fx:id=\"colBkDestination\" was not injected: check your FXML file 'sample.fxml'.";
        assert colBkBasePrice != null : "fx:id=\"colBkBasePrice\" was not injected: check your FXML file 'sample.fxml'.";
        assert colBkAgencyCommission != null : "fx:id=\"colBkAgencyCommission\" was not injected: check your FXML file 'sample.fxml'.";
        assert colBkRegionId != null : "fx:id=\"colBkRegionId\" was not injected: check your FXML file 'sample.fxml'.";
        assert colBkClassId != null : "fx:id=\"colBkClassId\" was not injected: check your FXML file 'sample.fxml'.";
        assert colBkFeeId != null : "fx:id=\"colBkFeeId\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnBkEdit != null : "fx:id=\"btnBkEdit\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnBkDelete != null : "fx:id=\"btnBkDelete\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnBkSave != null : "fx:id=\"btnBkSave\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblAgencyCommission != null : "fx:id=\"lblAgencyCommission\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtAgencyCommission != null : "fx:id=\"txtAgencyCommission\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblRegionId != null : "fx:id=\"lblRegionId\" was not injected: check your FXML file 'sample.fxml'.";
        assert cbRegionId != null : "fx:id=\"cbRegionId\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblClassId != null : "fx:id=\"lblClassId\" was not injected: check your FXML file 'sample.fxml'.";
        assert cbClassId != null : "fx:id=\"cbClassId\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblFeeId != null : "fx:id=\"lblFeeId\" was not injected: check your FXML file 'sample.fxml'.";
        assert cbFeeId != null : "fx:id=\"cbFeeId\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblTripStart != null : "fx:id=\"lblTripStart\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtTripStart != null : "fx:id=\"txtTripStart\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblTripEnd != null : "fx:id=\"lblTripEnd\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtTripEnd != null : "fx:id=\"txtTripEnd\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblDescription != null : "fx:id=\"lblDescription\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtDescription != null : "fx:id=\"txtDescription\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblDestination != null : "fx:id=\"lblDestination\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtDestination != null : "fx:id=\"txtDestination\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblBasePrice != null : "fx:id=\"lblBasePrice\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtBasePrice != null : "fx:id=\"txtBasePrice\" was not injected: check your FXML file 'sample.fxml'.";
        assert pnlPackagesOverview != null : "fx:id=\"pnlPackagesOverview\" was not injected: check your FXML file 'sample.fxml'.";
        assert tblPackages != null : "fx:id=\"tblPackages\" was not injected: check your FXML file 'sample.fxml'.";
        assert colPkgPkgName != null : "fx:id=\"colPkgPkgName\" was not injected: check your FXML file 'sample.fxml'.";
        assert colPkgPkgStartDate != null : "fx:id=\"colPkgPkgStartDate\" was not injected: check your FXML file 'sample.fxml'.";
        assert colPkgPkgEndDate != null : "fx:id=\"colPkgPkgEndDate\" was not injected: check your FXML file 'sample.fxml'.";
        assert colPkgPkgDesc != null : "fx:id=\"colPkgPkgDesc\" was not injected: check your FXML file 'sample.fxml'.";
        assert colPkgBasePrice != null : "fx:id=\"colPkgBasePrice\" was not injected: check your FXML file 'sample.fxml'.";
        assert colPkgAgencyCommission != null : "fx:id=\"colPkgAgencyCommission\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnAddPkg != null : "fx:id=\"btnAddPkg\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnDeletePkg != null : "fx:id=\"btnDeletePkg\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnEditPkg != null : "fx:id=\"btnEditPkg\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnSavePkg != null : "fx:id=\"btnSavePkg\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblPkgName != null : "fx:id=\"lblPkgName\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblPkgStartDate != null : "fx:id=\"lblPkgStartDate\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblPkgEndDate != null : "fx:id=\"lblPkgEndDate\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtPkgEndDate != null : "fx:id=\"txtPkgEndDate\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtPackageName != null : "fx:id=\"txtPackageName\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtPkgStartDate != null : "fx:id=\"txtPkgStartDate\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblPkgDesc != null : "fx:id=\"lblPkgDesc\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblPkgBasePrice != null : "fx:id=\"lblPkgBasePrice\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblPkgAgencyCommission != null : "fx:id=\"lblPkgAgencyCommission\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtPkgDesc != null : "fx:id=\"txtPkgDesc\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtPkgBasePrice != null : "fx:id=\"txtPkgBasePrice\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtPkgAgencyCommission != null : "fx:id=\"txtPkgAgencyCommission\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnAddEditPkg != null : "fx:id=\"btnAddEditPkg\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnClearPkg != null : "fx:id=\"btnClearPkg\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtPkgSearch != null : "fx:id=\"txtPkgSearch\" was not injected: check your FXML file 'sample.fxml'.";
        assert lblPkgSearch != null : "fx:id=\"lblPkgSearch\" was not injected: check your FXML file 'sample.fxml'.";
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

        getCustomerBooking();

        // to load packages table
        getPackages();

        getCustomerSearch();

        // load the customer table
//        getCustomerDetails();

        setTextColour();
        setMenuColour();
        setBackgroundColour();
        setSecondaryColour();
        setTertiaryColour();

    }
    //this populates the Customer table on form load 1st step


    //Author: Christopher Potvin
    //this is the login method

    private void Login() throws NoSuchAlgorithmException {
        // to do switch

        String name = txtUserName.getText();
        String password = PasswordEncryption.MD5(txtPassword.getText());

        if (name.isEmpty() || password.isEmpty()) {
            InvalidateLogin();
            EnableMenu(false);

        } else {
            if (name.equals(user) && password.equals(passw)) {
                ValidateLogin();
                pnlMainMenu.toFront();
                EnableMenu(true);

            } else {
                InvalidateLogin();
                EnableMenu(false);
            }
        }
    }

    private void Logout() {
        btnLogin.setDisable(false);
        btnLogout.setDisable(false);
        txtPassword.setText("");
        txtUserName.setText("");
        pnlLogin.toFront();
    }

    // Ethan Shipley
    // Either hides or unhides the menu buttons
    // if b is true then it hides login button and shows all the other buttons
    private void EnableMenu(boolean b) {
        btnLoginTab.setVisible(!b);
        btnMainMenu.setVisible(b);
        btnPackagesOverview.setVisible(b);
        btnCustomers.setVisible(b);
        btnBookings.setVisible(b);
        btnSettings.setVisible(b);
        btnLogout.setVisible(b);
    }

    private void ValidateLogin (){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Authentication Validated");
        alert.setHeaderText(null);
        alert.setContentText("You are now successfully logged in");
        alert.showAndWait();
    }

    private void InvalidateLogin (){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Authentication Error");
        alert.setHeaderText("Please check your login details");
        alert.showAndWait();
    }

    // Author: Christopher Potvin

    private void getCustomerSearch()
    {
        gvCustomer.getItems().clear(); // this clears the table view before the search field is used

        String lastName = txtCustSearch.getText(); // this gets the customer text and puts the value into a String var

        try {

            Connection conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * from customers WHERE CustLastName LIKE '%" + lastName + "%' " +
                    "ORDER BY CustLastName DESC;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                custData.add(new Customer(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10),
                        rs.getString(11)));
            }

            colCustFirstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustFirstName"));
            colCustLastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustLastName"));
            colCustAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustAddress"));
            colCustCity.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustCity"));
            colCustProvince.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustProv"));
            colCustPostalCode.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustPostal"));
            colCustCountry.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustCountry"));
            colCustHomePhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustHomePhone"));
            colCustBusinessPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustBusPhone"));
            colCustEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustEmail"));

            gvCustomer.setItems(custData);
            conn.close();
            // this method disables the fields so the agent cannot play with the object until he hits the edit button
//            DisableFields();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update method
    private void saveCustomerDetails()
    {
            String custFirstName = txtCustFirstName.getText();
            String custLastName = txtCustLastName.getText();
            String custAddress = txtCustAddress.getText();
            String custCity = txtCustCity.getText();
            String custProv = txtCustProv.getText();
            String custPostal = txtCustPostal.getText();
            String custCountry = txtCustCountry.getText();
            String custHomePhone = txtCustHomePhone.getText();
            String custBusPhone = txtCustBusPhone.getText();
            String custEmail = txtCustEmail.getText();


            Connection conn = DBConnect.getConnection();

            String sql = "update customers set CustFirstName=" + "'" + custFirstName + "'" + ", CustLastName=" + "'" + custLastName + "'"
                    + ", CustAddress=" + "'" + custAddress + "'" + ", CustCity=" + "'" + custCity + "'" + ", CustProv=" + "'"
                    + custProv + "'" + ", CustPostal= " + "'" + custPostal + "'" + ", CustCountry=" + "'" + custCountry + "'"
                    + ", CustHomePhone=" + "'" + custHomePhone + "'" + ", CustBusPhone=" + "'" + custBusPhone + "'" + ", CustEmail=" + "'"
                    + custEmail + "'" + " where CustomerId=" + "'" + customerSelectedDetailId + "'";
            try{
                PreparedStatement stmt = conn.prepareStatement(sql);
                int numRows = stmt.executeUpdate();
            if (numRows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were updated.");
                alert.showAndWait();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer information updated");
            alert.showAndWait();

            } catch (SQLException e) {
            e.printStackTrace();
            }

            getCustomerSearch();
            populateCustomerDetails();
            txtCustSearch.setText("");
            clear();
    }

    //Author: Christopher Potvin
    //This method will delete a customer record

    private void DeleteCustomer ()
    {
        Connection conn = DBConnect.getConnection();
        String sql = "delete from customers where CustomerId=" + "'" +  customerSelectedDetailId + "'";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

            int reply = JOptionPane.showConfirmDialog( null,"Are you sure you want to delete this customer?", "Delete Customer", JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog( null,"Customer deleted successfully.");

                conn.close();
            }
            else {
                pnlCustomers.toFront();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        getCustomerSearch();
        populateCustomerDetails();
    }

    private void EnableFields()
    {
        txtCustFirstName.setDisable(false);
        txtCustLastName.setDisable(false);
        txtCustAddress.setDisable(false);
        txtCustCity.setDisable(false);
        txtCustProv.setDisable(false);
        txtCustPostal.setDisable(false);
        txtCustCountry.setDisable(false);
        txtCustHomePhone.setDisable(false);
        txtCustBusPhone.setDisable(false);
        txtCustEmail.setDisable(false);

    }

    // this is the start of the validation for the fields in the Customer table ie. Tel number and email, and fields required.
    // Author: Christopher Potvin

    private boolean valFields (String input)
    {
        return input.equals("");
    }

    private boolean valPhone (String input)
    {
        return input.length() == 10 && input.matches("[0-9]+");
    }

    private boolean valEmail (String email)
    {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(email);
        return matcher.find();
    }

    private boolean valPostalCode (String postalcode)
    {
        String regex = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";
        Pattern pc = Pattern.compile(regex);
        Matcher matcher = pc.matcher(postalcode);
        boolean result = matcher.find();
        return result;
        // this matcher.find will return a true or false
    }

    // this is the start of the packages pane, Brando's work
    private void getPackages() {
        ObservableList<Package> packData = FXCollections.observableArrayList();
        try {
            /*Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "harv", "password");*/
            Connection conn = DBConnect.getConnection();
            String sql = "select * from packages";
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
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        selectedPackage = tblPackages.getItems().get(tblPackages.getSelectionModel().getFocusedIndex());
        packId = selectedPackage.getPackageId();
    }


    // method to get booking details of specific customer by last name,
    // txtBkSearch TextField input string value, and return bookingData ObservableList object
    // Author: James Cockriell, Date April 1, 2019
    private void getCustomerBooking() {
        gvBookings.getItems().clear();
        String lname = txtBkSearch.getText();
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT BookingDetailId, TripStart, TripEnd, Description, Destination, cast(BasePrice as char) as BasePrice, cast(AgencyCommission as char) as AgencyCommission, RegionName, ClassName, FeeName " +
                "from (((((bookingdetails " +
                "Inner Join bookings on bookingdetails.BookingId = bookings.BookingId) " +
                "Inner Join customers on bookings.CustomerId = customers.CustomerId) " +
                "Inner Join regions on bookingdetails.RegionId = regions.RegionId) " +
                "Inner Join classes on bookingdetails.ClassId = classes.ClassId) " +
                "Inner Join fees on bookingdetails.FeeId = fees.FeeId) " +
                "where customers.CustLastName LIKE '%" + lname + "%' " +
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
        String sql = "select * from regions";
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
        String sql = "select ClassId, ClassName from classes";
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

            /*Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "harv", "password");*/
            Connection conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into packages(PkgName,PkgStartDate,PkgEndDate,PkgDesc,PkgBasePrice,PkgAgencyCommission) " +
                    "VALUES ('" + pkgName + "','" + pkgStartDate + "','" + pkgEndDate + "','" + pkgDesc + "','" + pkgPrice + "','" + pkgCommission + "')");

            JOptionPane.showMessageDialog(null, "New Package Added");

            clear();

            pnlPackagesOverview.toFront();

            getPackages();
            conn.close();
        } catch (SQLException e) {
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
        String sql = "update bookingdetails set TripStart=" + "'" + TripStart + "'" + ", TripEnd=" + "'" + TripEnd + "'" + ", Description=" + "'" + Description + "'" + ", Destination=" + "'" + Destination + "'" + ", BasePrice=" + "'" + BasePrice + "'" + ", AgencyCommission= " + "'" + AgencyCommission + "'" + ", RegionId=" + "'" + RegionId + "'" + ", ClassId=" + "'" + ClassId + "'" + ", FeeId=" + "'" + FeeId + "'" + " where BookingDetailId=" + "'" + customerSelectedBookingDetailId + "'";

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
        } else acceptableBookingDates = !bkEnd.isBefore(bkStart);
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
        selectedPackage = tblPackages.getItems().get(tblPackages.getSelectionModel().getFocusedIndex());
        packId = selectedPackage.getPackageId();

        String pkgName = txtPackageName.getText();
        String pkgStartDate = txtPkgStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String pkgEndDate = txtPkgEndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String pkgDesc = txtPkgDesc.getText();
        Float pkgPrice = Float.valueOf(txtPkgBasePrice.getText());
        Float pkgCommission = Float.valueOf(txtPkgAgencyCommission.getText());

        Connection conn = DBConnect.getConnection();
        String sql = "update packages set PkgName=" + "'" + pkgName + "'" + ", PkgStartDate=" + "'" + pkgStartDate + "'" + ", PkgEndDate=" + "'" + pkgEndDate + "'" + ", PkgDesc=" + "'" + pkgDesc + "'" + ", PkgBasePrice=" + "'" + pkgPrice + "'" +  ", PkgAgencyCommission=" + "'" + pkgCommission + "'" + " where PackageId=" + "'" + packId + "'";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            int numRows = stmt.executeUpdate();
            if (numRows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were updated.");
                alert.showAndWait();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update Successful");
            alert.showAndWait();

            pnlPackagesOverview.toFront();

            getPackages();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Brandon - method to delete package
    private void deletePackage()
    {
        selectedPackage = tblPackages.getItems().get(tblPackages.getSelectionModel().getFocusedIndex());
        packId = selectedPackage.getPackageId();

        Connection conn = DBConnect.getConnection();
        String sql = "delete packages_products_suppliers, packages from packages_products_suppliers inner join packages where packages_products_suppliers.PackageId and packages.PackageId= " + "'" + packId + "'";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            int reply = JOptionPane.showConfirmDialog( null,"Are you sure you want to delete this package", "Delete Package", JOptionPane.YES_NO_OPTION);
           if (reply == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog( null,"Package deleted successfully.");
                pnlPackagesOverview.toFront();
                activePkg();
                getPackages();
                conn.close();
            }
            else {
                pnlPackagesOverview.toFront();
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }


    }

    private void selectedPackage() {
        txtPackageName.setText(tblPackages.getSelectionModel().getSelectedItem().getPkgName());
        txtPkgStartDate.setValue(tblPackages.getSelectionModel().getSelectedItem().getPkgStartDate());
        txtPkgEndDate.setValue(tblPackages.getSelectionModel().getSelectedItem().getPkgEndDate());
        txtPkgDesc.setText(tblPackages.getSelectionModel().getSelectedItem().getPkgDesc());
        txtPkgBasePrice.setText(tblPackages.getSelectionModel().getSelectedItem().getPkgBasePrice().toString());
        txtPkgAgencyCommission.setText(tblPackages.getSelectionModel().getSelectedItem().getPkgAgencyCommission().toString());
    }

    private void activePkg()
    {
        txtPackageName.setEditable(true);
        txtPkgStartDate.setEditable(true);
        txtPkgEndDate.setEditable(true);
        txtPkgDesc.setEditable(true);
        txtPkgBasePrice.setEditable(true);
        txtPkgAgencyCommission.setEditable(true);
    }

    private boolean pkgValidateSave() {
        pStart = txtPkgStartDate.getValue();
        pEnd = txtPkgStartDate.getValue();
        boolean result;

        if (pEnd.isBefore(pStart)) {
            result = false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Package start date needs to be an earlier date than package end date.");
            alert.showAndWait();
        } else if (txtPkgDesc.getText().equals("") || txtPkgBasePrice.getText().equals("") || txtPkgAgencyCommission.getText().equals("") || txtPackageName.getText().equals("")) {
            result = false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You need to fill out all of the fields");
            alert.showAndWait();
        } else if (Float.valueOf(txtPkgBasePrice.getText()) < 0 || Float.valueOf(txtPkgAgencyCommission.getText()) < 0) {
            result = false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Base Price and Agency Commission fields need to be populated with a non negative number value");
            alert.showAndWait();
        } else {
            result = true;
            saveNewPackage();
            getPackages();
        }
        return result;
    }


    private boolean pkgValidateUpdate() {
        pStart = txtPkgStartDate.getValue();
        pEnd = txtPkgStartDate.getValue();

        boolean result;

        if (pEnd.isBefore(pStart)) {
            result = false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Package start date needs to be an earlier date than package end date.");
            alert.showAndWait();
        } else if (txtPkgDesc.getText().equals("") || txtPkgBasePrice.getText().equals("") || txtPkgAgencyCommission.getText().equals("") || txtPackageName.getText().equals("")) {
            result = false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You need to fill out all of the fields");
            alert.showAndWait();
        } else if (Float.valueOf(txtPkgBasePrice.getText()) < 0 || Float.valueOf(txtPkgAgencyCommission.getText()) < 0) {
            result = false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Base Price and Agency Commission fields need to be populated with a non negative number value");
            alert.showAndWait();
        } else {
            result = true;
            updatePackage();
            getPackages();
        }
        return result;
    }

}
