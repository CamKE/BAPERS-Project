/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.gui;

import bapers.AutoBackupConfig;
import bapers.controller.Controller;
import bapers.customer.CustomerDetails;
import bapers.job.Invoice;
import bapers.job.Job;
import bapers.job.JobStandardJob;
import bapers.job.Material;
import bapers.job.StandardJob;
import bapers.job.Task;
import bapers.payment.Card;
import bapers.payment.Payment;
import bapers.payment.PaymentCard;
import bapers.payment.PaymentCash;
import bapers.user.UserDetails;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author CameronE
 */
public class MainFrame extends javax.swing.JFrame {

    private final CardLayout card1;
    private final CardLayout card2;
    private final Controller controller;

    private UserDetails loggedInUser;
    CustomerDetails selectedCustomer = null;

    DefaultTableModel tblModel;
    String[] durations;

    Invoice invoice;

    // Lists
    List<Material> materials;
    List<StandardJob> stdJobs;
    List<Task> tasks;
    List<Invoice> selectedInvoices;
    List<StandardJob> selectedStdJobs;
    List<Task> selectedTasks;

    // list models that are used to for the scroll
    DefaultListModel list1;
    DefaultListModel list2;
    DefaultTableModel m;

    // collect job model for table
    DefaultListModel t = new DefaultListModel();
    DefaultListModel t2 = new DefaultListModel();

    // jobs model for table
    DefaultTableModel jobModelTable;
    DefaultTableModel standardJobModelTable;
    DefaultTableModel taskModelTable;

    // other model for table
    DefaultTableModel invoiceModelTable;
    DefaultTableModel defaultCustomerModelTable;
    DefaultTableModel customerModelTable;

    Job job = null;
    JobStandardJob jobStandardJob = null;

    AutoBackupConfig configData;

    String currentPage, previousPage;

    /**
     * Creates new form MainFrame
     *
     * @param controller
     */
    public MainFrame(Controller controller) {
        selectedInvoices = new ArrayList<>();
        selectedTasks = new ArrayList<>();
        selectedStdJobs = new ArrayList<>();
        stdJobs = new ArrayList<>();
        materials = new ArrayList<>();
        list1 = new DefaultListModel();
        list2 = new DefaultListModel();
        durations = new String[]{"Select a time"};
        currentPage = "";
        previousPage = "";
        // Set the controller 
        this.controller = controller;
        loggedInUser = null;
        initComponents();
        // set the card layout for the main section of the screen
        card1 = (CardLayout) cardPanel1.getLayout();
        // set the card layout for the top section of the screen
        card2 = (CardLayout) cardPanel2.getLayout();
        // hide the button on the welcome bar
        backButton.setVisible(false);
        homeButton.setVisible(false);
        initAutoBackup();
    }

    public final void initAutoBackup() {
        final boolean countAutoConfigData = controller.checkAutoBackupConfigExist();

        if (countAutoConfigData != false) {
            this.configData = controller.getAutoBackupConfigData();
            autoBackupFrequency(configData);
        } else {
            JOptionPane.showMessageDialog(this, "No auto update");
        }
    }

    public void autoBackupFrequency(AutoBackupConfig configData) {
        switch (configData.getBackupFrequency()) {
            case "weekly":
                System.out.println("weekly");
                //Timer timer = new Timer();
                //timer.scheduleAtFixedRate(new AutoBackup(configData, this), TimeUnit.DAYS.toMillis(1), TimeUnit.DAYS.toMillis(1));
                break;

            case "monthly":
                System.out.println("monthly");
                break;

            default:
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jComboBox1 = new javax.swing.JComboBox<>();
        cardPanel1 = new javax.swing.JPanel();
        welcomePage = new javax.swing.JPanel();
        BAPERSLabel = new javax.swing.JLabel();
        loginPageButton = new javax.swing.JButton();
        RestorePageButton = new javax.swing.JButton();
        loginPage = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        userIDField = new javax.swing.JTextField();
        restorePage = new javax.swing.JPanel();
        restoreLabel = new javax.swing.JLabel();
        RestoreButton = new javax.swing.JButton();
        chooseFileButton = new javax.swing.JButton();
        fileChosenField = new javax.swing.JTextField();
        userSearchPage = new javax.swing.JPanel();
        findUserLabel = new javax.swing.JLabel();
        userNumberLabel = new javax.swing.JLabel();
        UserNumberField = new javax.swing.JTextField();
        cancelSearchButton = new javax.swing.JButton();
        UserFirstnameField = new javax.swing.JTextField();
        userFirstnameLabel = new javax.swing.JLabel();
        UserLastnameField = new javax.swing.JTextField();
        userLastnameLabel = new javax.swing.JLabel();
        UserRoleSearchDrop = new javax.swing.JComboBox<>();
        userLastnameLabel1 = new javax.swing.JLabel();
        searchUserButton = new javax.swing.JButton();
        backupPage = new javax.swing.JPanel();
        BackupDataLabel = new javax.swing.JLabel();
        backupButton = new javax.swing.JButton();
        chooseLocationButton = new javax.swing.JButton();
        locationChosenField = new javax.swing.JTextField();
        backupDestinationLabel = new javax.swing.JLabel();
        userResultsPage = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userResultsTable = new javax.swing.JTable();
        searchAgainButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        changeRoleButton = new javax.swing.JButton();
        selectUserButton = new javax.swing.JButton();
        createUserPage = new javax.swing.JPanel();
        newUserLabel = new javax.swing.JLabel();
        userLastNameField = new javax.swing.JTextField();
        userFirstNameField = new javax.swing.JTextField();
        NewRepeatPasswordField = new javax.swing.JPasswordField();
        NewPasswordField = new javax.swing.JPasswordField();
        userRoleComboBox = new javax.swing.JComboBox<>();
        firstnameLabel = new javax.swing.JLabel();
        lastnameLabel = new javax.swing.JLabel();
        RoleLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        ReenterPasswordLabel = new javax.swing.JLabel();
        createUserButton = new javax.swing.JButton();
        usernameField = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        createTaskPage = new javax.swing.JPanel();
        newTaskLabel1 = new javax.swing.JLabel();
        descriptionNewTaskField = new javax.swing.JTextField();
        durationNewTaskDD = new javax.swing.JComboBox<>();
        descriptionLabel = new javax.swing.JLabel();
        durationLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        departmentNewTaskLabel = new javax.swing.JLabel();
        shelfSlotNewTaskLabel = new javax.swing.JLabel();
        createNewTaskButton = new javax.swing.JButton();
        shelfSlotTaskDD = new javax.swing.JComboBox<>();
        departmentNewTaskDD = new javax.swing.JComboBox<>();
        durationNewTaskMinsDD = new javax.swing.JComboBox<>();
        priceNewTaskField = new javax.swing.JTextField();
        hoursLabel = new javax.swing.JLabel();
        minutesLabel = new javax.swing.JLabel();
        reportHomePage = new javax.swing.JPanel();
        manageReportsPageButton = new javax.swing.JButton();
        createReportPageButton = new javax.swing.JButton();
        homePage = new javax.swing.JPanel();
        jobMenuPageButton = new javax.swing.JButton();
        reportsMenuPageButton = new javax.swing.JButton();
        tasksMenuPageButton = new javax.swing.JButton();
        usersMenuPageButton = new javax.swing.JButton();
        manageCustomersMenuPageButton = new javax.swing.JButton();
        settingsMenuPageButton = new javax.swing.JButton();
        standardJobsMenuPageButton = new javax.swing.JButton();
        acceptPaymentPageButton = new javax.swing.JButton();
        jobHomePage = new javax.swing.JPanel();
        acceptJobPageButton = new javax.swing.JButton();
        collectJobPageButton = new javax.swing.JButton();
        jobEnquiryPageButton = new javax.swing.JButton();
        taskHomePage = new javax.swing.JPanel();
        manageTasksPageButton = new javax.swing.JButton();
        createTaskPageButton = new javax.swing.JButton();
        userHomePage = new javax.swing.JPanel();
        manageUsersPageButton = new javax.swing.JButton();
        createUserPageButton = new javax.swing.JButton();
        standardJobHomePage = new javax.swing.JPanel();
        manageSJobPageButton = new javax.swing.JButton();
        createSJobPageButton = new javax.swing.JButton();
        settingsHomePage = new javax.swing.JPanel();
        backupPageButton = new javax.swing.JButton();
        backupSettingsPageButton = new javax.swing.JButton();
        reportSettingsPageButton = new javax.swing.JButton();
        restoreSettingsPageButton = new javax.swing.JButton();
        createCustomerPage = new javax.swing.JPanel();
        prefixjLabel = new javax.swing.JLabel();
        firstNamejLabel = new javax.swing.JLabel();
        LastnamejLabel = new javax.swing.JLabel();
        phoneNumberjLabel = new javax.swing.JLabel();
        postCodejLabel = new javax.swing.JLabel();
        streetNamejLabel = new javax.swing.JLabel();
        prefixjComboBox = new javax.swing.JComboBox<>();
        createCustomerjButton = new javax.swing.JButton();
        cancelCreationjButton = new javax.swing.JButton();
        postCodeField = new javax.swing.JTextField();
        surnameField = new javax.swing.JTextField();
        phoneNumberField = new javax.swing.JTextField();
        firstNameField = new javax.swing.JTextField();
        buildingNumberField = new javax.swing.JTextField();
        streetNameField = new javax.swing.JTextField();
        cityField = new javax.swing.JTextField();
        cityjLabel = new javax.swing.JLabel();
        accountHolderNamejTextField = new javax.swing.JTextField();
        accountHolderNameLabel = new javax.swing.JLabel();
        phoneNumberjLabel1 = new javax.swing.JLabel();
        searchCustomerPage = new javax.swing.JPanel();
        searchCustomerjPanel = new javax.swing.JPanel();
        receptionistjPanel = new javax.swing.JPanel();
        searchCustomerAccountNojLabel = new javax.swing.JLabel();
        searchContactFirstNamejLabel = new javax.swing.JLabel();
        searchContactSurnamejLabel = new javax.swing.JLabel();
        searchAccountHolderNamejLabel = new javax.swing.JLabel();
        custAccountNoField = new javax.swing.JTextField();
        custFirstnameField = new javax.swing.JTextField();
        custLastnameField = new javax.swing.JTextField();
        custAccountHNameField = new javax.swing.JTextField();
        searchCustomerFJobjButton = new javax.swing.JButton();
        cancelCustomerFJobjButton = new javax.swing.JButton();
        managerjPanel = new javax.swing.JPanel();
        streetNameSjLabel = new javax.swing.JLabel();
        postcodeSjLabel = new javax.swing.JLabel();
        citySjLabel = new javax.swing.JLabel();
        phoneSjLabel = new javax.swing.JLabel();
        customerTypeSjLabel = new javax.swing.JLabel();
        accountStatusjLabel = new javax.swing.JLabel();
        inDefaultSjLabel = new javax.swing.JLabel();
        registrationDateSjLabel = new javax.swing.JLabel();
        streetNameField1 = new javax.swing.JTextField();
        postCodeField1 = new javax.swing.JTextField();
        cityField1 = new javax.swing.JTextField();
        phoneField1 = new javax.swing.JTextField();
        customerTypeDD = new javax.swing.JComboBox<>();
        accountStatusDD = new javax.swing.JComboBox<>();
        inDefaultDD = new javax.swing.JComboBox<>();
        customerRegDateField = new com.toedter.calendar.JDateChooser();
        acceptJobPage = new javax.swing.JPanel();
        jobTotalField = new javax.swing.JTextField();
        addMaterialButton = new javax.swing.JButton();
        addJobButton = new javax.swing.JButton();
        totalLabel = new javax.swing.JLabel();
        stdJobDD = new javax.swing.JComboBox<>();
        selectPriority = new javax.swing.JComboBox<>();
        searchCustomerButton = new javax.swing.JButton();
        createCustomerButton = new javax.swing.JButton();
        specialInstructionsLabel = new javax.swing.JLabel();
        materialsjScrollPane = new javax.swing.JScrollPane();
        materialList = new javax.swing.JList<>();
        customerInfoField = new javax.swing.JTextField();
        materialSubmittedLabel = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        materialsjTextField = new javax.swing.JTextField();
        specialInstructionjTextField = new javax.swing.JTextField();
        materialsjScrollPane1 = new javax.swing.JScrollPane();
        standardJobList = new javax.swing.JList<>();
        removeJobButton = new javax.swing.JButton();
        removeMaterialButton = new javax.swing.JButton();
        stipulatedFields = new javax.swing.JPanel();
        surchargeLabel = new javax.swing.JLabel();
        surchargejTextField = new javax.swing.JTextField();
        completionTimeDD = new javax.swing.JComboBox<>();
        searchInvoicePage = new javax.swing.JPanel();
        searchInvoicejPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        invoicejTable = new javax.swing.JTable();
        selectSelectedInvoicejButton = new javax.swing.JButton();
        cancelInvoiceSeletionjButton = new javax.swing.JButton();
        createStandardJobPage = new javax.swing.JPanel();
        descriptionField = new javax.swing.JTextField();
        codeField = new javax.swing.JTextField();
        selectATaskBox = new javax.swing.JComboBox<>();
        addButton = new javax.swing.JButton();
        removeTaskButton = new javax.swing.JButton();
        stdJobTotalField = new javax.swing.JTextField();
        createButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        stdJobLabel = new javax.swing.JLabel();
        codeLabel = new javax.swing.JLabel();
        descLabel = new javax.swing.JLabel();
        totalLabel1 = new javax.swing.JLabel();
        materialsjScrollPane2 = new javax.swing.JScrollPane();
        standardJobList1 = new javax.swing.JList<>();
        customerResultsPage = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        customerResultsTable = new javax.swing.JTable();
        searchAgainButton1 = new javax.swing.JButton();
        selectCustomerButton = new javax.swing.JButton();
        createReportPage = new javax.swing.JPanel();
        searchPanel = new javax.swing.JPanel();
        infoField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        newUserLabel1 = new javax.swing.JLabel();
        reportTypeDD = new javax.swing.JComboBox<>();
        reportTypeLabel = new javax.swing.JLabel();
        reportPeriodLabel = new javax.swing.JLabel();
        periodFromLabel = new javax.swing.JLabel();
        createReportButton = new javax.swing.JButton();
        reportStartPeriod = new com.toedter.calendar.JDateChooser();
        reportEndPeriod = new com.toedter.calendar.JDateChooser();
        periodToLabel = new javax.swing.JLabel();
        summaryReportPage = new javax.swing.JScrollPane();
        summaryReportPageData = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        shift1Label = new javax.swing.JLabel();
        shift2Label = new javax.swing.JLabel();
        shift3Label = new javax.swing.JLabel();
        periodLabel = new javax.swing.JLabel();
        summaryReportLabel = new javax.swing.JLabel();
        reportBackButton = new javax.swing.JButton();
        printButton1 = new javax.swing.JButton();
        individualReportPage = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        iPLabel = new javax.swing.JLabel();
        reportBackButton1 = new javax.swing.JButton();
        printButton2 = new javax.swing.JButton();
        customerReportPage = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        cPLabel = new javax.swing.JLabel();
        reportBackButton2 = new javax.swing.JButton();
        printButton3 = new javax.swing.JButton();
        searchInvoice = new javax.swing.JPanel();
        searchInvoicejPanel1 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        invoicejTable1 = new javax.swing.JTable();
        selectSelectedInvoicejButton1 = new javax.swing.JButton();
        cancelInvoiceSeletionjButton1 = new javax.swing.JButton();
        searchInvoiceByInvoiceNojTextField = new javax.swing.JTextField();
        searchInvoiceByJobNumberjTextField = new javax.swing.JTextField();
        searchInvoiceByInvoiceNojLabel = new javax.swing.JLabel();
        searchInvoiceByJobNumberjLabel = new javax.swing.JLabel();
        AutoBackupConfigjPanel = new javax.swing.JPanel();
        currentAutoBackupModeDatajTextField = new javax.swing.JTextField();
        backupLocationjLabel = new javax.swing.JLabel();
        backupFrequencyjLabel = new javax.swing.JLabel();
        currentConfigjLabel = new javax.swing.JLabel();
        backupModejComboBox = new javax.swing.JComboBox<>();
        backupFrequencyjComboBox = new javax.swing.JComboBox<>();
        selectAutoBackupLocationjButton = new javax.swing.JButton();
        confirmAutoBackupConfigjButton = new javax.swing.JButton();
        backupModejLabel = new javax.swing.JLabel();
        currentBackupModejLabel = new javax.swing.JLabel();
        currentBackupFrequencyjLabel = new javax.swing.JLabel();
        currentBackupLocationjLabel = new javax.swing.JLabel();
        currentAutoBackupFrequencyDatajTextField = new javax.swing.JTextField();
        autoBackupLocationjTextField = new javax.swing.JTextField();
        currentAutoBackupLocationDatajTextField = new javax.swing.JTextField();
        changeConfigjLabel = new javax.swing.JLabel();
        SelectDiscountPlan = new javax.swing.JPanel();
        discountPlanTypejLabel = new javax.swing.JLabel();
        discountPlanTypejComboBox = new javax.swing.JComboBox<>();
        discountCanceljButton = new javax.swing.JButton();
        applyDiscountjButton = new javax.swing.JButton();
        DiscountTypeInformation = new javax.swing.JPanel();
        FixedDiscountType = new javax.swing.JPanel();
        fixedDiscountRatejLabel = new javax.swing.JLabel();
        fixedPercentageSymbol = new javax.swing.JLabel();
        fixedDiscountRatejTextField = new javax.swing.JTextField();
        VariableDiscountType = new javax.swing.JPanel();
        JobsjPanel = new javax.swing.JPanel();
        JobsjScrollPane = new javax.swing.JScrollPane();
        JobsjTable = new javax.swing.JTable();
        StandardJobsjPanel = new javax.swing.JPanel();
        StandardJobsjScrollPane = new javax.swing.JScrollPane();
        StandardJobsjTable = new javax.swing.JTable();
        TasksjPanel = new javax.swing.JPanel();
        TasksjScrollPane = new javax.swing.JScrollPane();
        TaskjTable = new javax.swing.JTable();
        FlexiableDiscountType = new javax.swing.JPanel();
        boundjLabel = new javax.swing.JLabel();
        upperBoundjLabel = new javax.swing.JLabel();
        lowerBoundjLabel = new javax.swing.JLabel();
        flexibleDiscountRatejLabel = new javax.swing.JLabel();
        flexiablePercentageSymbol = new javax.swing.JLabel();
        upperBoundjTextField = new javax.swing.JTextField();
        lowerBoundjTextField = new javax.swing.JTextField();
        flexibleDiscountRatejTextField = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        boundsjList = new javax.swing.JList<>();
        removeFlexibleBoundjButton = new javax.swing.JButton();
        addFlexibleBoundjButton = new javax.swing.JButton();
        SelectCustomer = new javax.swing.JPanel();
        customersjScrollPane = new javax.swing.JScrollPane();
        customersjTable = new javax.swing.JTable();
        selectCustomerjButton = new javax.swing.JButton();
        ViewCustomerDetail = new javax.swing.JPanel();
        CustomerInfo = new javax.swing.JPanel();
        customerAccHolderNamejLabel = new javax.swing.JLabel();
        customerPrefixjLabel = new javax.swing.JLabel();
        customerFirstNamejLabel = new javax.swing.JLabel();
        customerLastNamejLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        customerStatusjTextField = new javax.swing.JTextField();
        customerTypejTextField = new javax.swing.JTextField();
        cutomerInDefaultjTextField = new javax.swing.JTextField();
        customerDiscountjTextField = new javax.swing.JTextField();
        customerNumberjLabel = new javax.swing.JLabel();
        customerRegistationDatejLabel = new javax.swing.JLabel();
        registrationDatejLabel = new javax.swing.JLabel();
        CustomerAction = new javax.swing.JPanel();
        customerActionjLabel = new javax.swing.JLabel();
        assignDiscountPlanjButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        OtherCustomerInfo = new javax.swing.JPanel();
        customerStreetNamejTextField = new javax.swing.JTextField();
        customerPostcodejTextField = new javax.swing.JTextField();
        customerCityjTextField = new javax.swing.JTextField();
        customerBuildingNojTextField = new javax.swing.JTextField();
        acceptPayment = new javax.swing.JPanel();
        acceptLatePaymentjPanel = new javax.swing.JPanel();
        expiryDatejLabel = new javax.swing.JLabel();
        last4DigitjTextField = new javax.swing.JTextField();
        selectInvoicejButton = new javax.swing.JButton();
        paymentTypejLabel = new javax.swing.JLabel();
        expiryDatejTextField = new javax.swing.JTextField();
        paymentTypeComboBox = new javax.swing.JComboBox<>();
        TotalLatePayjTextField = new javax.swing.JTextField();
        cardTypejLabel = new javax.swing.JLabel();
        totaljLabel = new javax.swing.JLabel();
        cardTypejComboBox = new javax.swing.JComboBox<>();
        last4DigitjLabel = new javax.swing.JLabel();
        invoicejScrollPane = new javax.swing.JScrollPane();
        invoicejList = new javax.swing.JList<>();
        latePaymentSubmitjButton = new javax.swing.JButton();
        technicianHomePage = new javax.swing.JPanel();
        technicianPage = new javax.swing.JPanel();
        jobEnquiryTechnician = new javax.swing.JButton();
        taskPage = new javax.swing.JPanel();
        manageTasksButton = new javax.swing.JButton();
        createTaskButton = new javax.swing.JButton();
        receptionistHomePage = new javax.swing.JPanel();
        receptionHomePage = new javax.swing.JPanel();
        jobReceptionist = new javax.swing.JButton();
        acceptPaymentReceptionist = new javax.swing.JButton();
        editTaskPage = new javax.swing.JPanel();
        newDescriptionLabel = new javax.swing.JLabel();
        newDescriptionLabel1 = new javax.swing.JLabel();
        newDescriptionLabel2 = new javax.swing.JLabel();
        newDescriptionLabel3 = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        descriptionText = new javax.swing.JTextField();
        shelfSlotText = new javax.swing.JTextField();
        priceText = new javax.swing.JTextField();
        departmentComboBox = new javax.swing.JComboBox<>();
        taskIDLabel = new javax.swing.JLabel();
        taskIDText = new javax.swing.JTextField();
        manageTasksPage = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        taskTable = new javax.swing.JTable();
        editButton = new javax.swing.JButton();
        deleteButton1 = new javax.swing.JButton();
        officeManagerCustomerPage = new javax.swing.JPanel();
        searchCustomerBtn = new javax.swing.JButton();
        reminderLettersButton = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        reminderLettersTablePage = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        reminderLettersTable = new javax.swing.JTable();
        backCustomerPageButton = new javax.swing.JButton();
        viewReminderLetterButton = new javax.swing.JButton();
        taskSearchResultsJobEnquiryPage = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        standardJobCodeLabel = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        taskResultsTable = new javax.swing.JTable();
        updateTaskButton = new javax.swing.JButton();
        taskEnquiryBackButton = new javax.swing.JButton();
        standardJobIndexLabel = new javax.swing.JLabel();
        searchJobPage = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jobNumberTextField = new javax.swing.JTextField();
        searchJobButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        customerNumberText = new javax.swing.JTextField();
        searchCustomerButton1 = new javax.swing.JButton();
        backRecpHPButton = new javax.swing.JButton();
        jobEnquiryPage = new javax.swing.JPanel();
        jobStatusLabel = new javax.swing.JLabel();
        jobNumberText = new javax.swing.JTextField();
        searchJobNumberJobEnquiryButton = new javax.swing.JButton();
        jobNumberLabel1 = new javax.swing.JLabel();
        jobPriorityLabel = new javax.swing.JLabel();
        jobPriorityLabel2 = new javax.swing.JLabel();
        cancelJobEnquiryButton = new javax.swing.JButton();
        searchJobEnquiryButton = new javax.swing.JButton();
        jobStatusComboBox = new javax.swing.JComboBox<>();
        jobCollectedComboBox = new javax.swing.JComboBox<>();
        jobPriorityComboBox = new javax.swing.JComboBox<>();
        jobEnquirySearchResultsPage = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jobEnquiryTableResults = new javax.swing.JTable();
        viewJobEnquiryButton = new javax.swing.JButton();
        backJobEnquiryButton = new javax.swing.JButton();
        jobSearchResultsPage = new javax.swing.JPanel();
        backButon = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        jobSearchResultsTable = new javax.swing.JTable();
        collectJobButton = new javax.swing.JButton();
        standardJobSearchResultsJobEnquiryPage = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        standardJobResults = new javax.swing.JTable();
        viewStandardJobButton = new javax.swing.JButton();
        backJobSearchResultsButton = new javax.swing.JButton();
        jobNumberLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jobIndexLabel = new javax.swing.JLabel();
        cardPanel2 = new javax.swing.JPanel();
        welcomeBar = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        welcomePageLabel = new javax.swing.JLabel();
        homeBar = new javax.swing.JPanel();
        homeButton = new javax.swing.JButton();
        logOutButton = new javax.swing.JButton();
        pageLabel = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 700));
        setResizable(false);

        cardPanel1.setBackground(new java.awt.Color(255, 204, 204));
        cardPanel1.setMaximumSize(new java.awt.Dimension(900, 640));
        cardPanel1.setMinimumSize(new java.awt.Dimension(900, 640));
        cardPanel1.setPreferredSize(new java.awt.Dimension(900, 640));
        cardPanel1.setLayout(new java.awt.CardLayout());

        welcomePage.setBackground(new java.awt.Color(61, 96, 146));
        welcomePage.setMaximumSize(new java.awt.Dimension(900, 640));
        welcomePage.setMinimumSize(new java.awt.Dimension(900, 640));
        welcomePage.setPreferredSize(new java.awt.Dimension(900, 640));

        BAPERSLabel.setFont(new java.awt.Font("Tahoma", 1, 90)); // NOI18N
        BAPERSLabel.setForeground(new java.awt.Color(255, 255, 255));
        BAPERSLabel.setText("B   A   P   E   R   S");

        loginPageButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        loginPageButton.setText("Login");
        loginPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginPageButtonActionPerformed(evt);
            }
        });

        RestorePageButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        RestorePageButton.setText("Restore");
        RestorePageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RestorePageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout welcomePageLayout = new javax.swing.GroupLayout(welcomePage);
        welcomePage.setLayout(welcomePageLayout);
        welcomePageLayout.setHorizontalGroup(
            welcomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePageLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(welcomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(BAPERSLabel)
                    .addComponent(loginPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RestorePageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );
        welcomePageLayout.setVerticalGroup(
            welcomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePageLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(BAPERSLabel)
                .addGap(119, 119, 119)
                .addComponent(loginPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(RestorePageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        cardPanel1.add(welcomePage, "welcome");

        loginPage.setBackground(new java.awt.Color(61, 96, 146));
        loginPage.setMaximumSize(new java.awt.Dimension(900, 640));
        loginPage.setMinimumSize(new java.awt.Dimension(900, 640));
        loginPage.setPreferredSize(new java.awt.Dimension(900, 640));
        loginPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                loginPageComponentHidden(evt);
            }
        });

        loginLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        loginLabel.setForeground(new java.awt.Color(255, 255, 255));
        loginLabel.setText("Login");

        loginButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        passwordField.setMaximumSize(new java.awt.Dimension(250, 42));
        passwordField.setMinimumSize(new java.awt.Dimension(250, 42));
        passwordField.setPreferredSize(new java.awt.Dimension(250, 42));

        userIDField.setMaximumSize(new java.awt.Dimension(250, 42));
        userIDField.setMinimumSize(new java.awt.Dimension(250, 42));
        userIDField.setPreferredSize(new java.awt.Dimension(250, 42));

        javax.swing.GroupLayout loginPageLayout = new javax.swing.GroupLayout(loginPage);
        loginPage.setLayout(loginPageLayout);
        loginPageLayout.setHorizontalGroup(
            loginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPageLayout.createSequentialGroup()
                .addContainerGap(326, Short.MAX_VALUE)
                .addGroup(loginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginLabel)
                    .addComponent(userIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(324, 324, 324))
        );
        loginPageLayout.setVerticalGroup(
            loginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPageLayout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(loginLabel)
                .addGap(40, 40, 40)
                .addComponent(userIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );

        cardPanel1.add(loginPage, "login");

        restorePage.setBackground(new java.awt.Color(61, 96, 146));
        restorePage.setMaximumSize(new java.awt.Dimension(900, 640));
        restorePage.setMinimumSize(new java.awt.Dimension(900, 640));
        restorePage.setPreferredSize(new java.awt.Dimension(900, 640));

        restoreLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        restoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        restoreLabel.setText("Restore");

        RestoreButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        RestoreButton.setText("Restore");
        RestoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RestoreButtonActionPerformed(evt);
            }
        });

        chooseFileButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        chooseFileButton.setText("Choose file");
        chooseFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseFileButtonActionPerformed(evt);
            }
        });

        fileChosenField.setMaximumSize(new java.awt.Dimension(250, 37));
        fileChosenField.setMinimumSize(new java.awt.Dimension(250, 37));
        fileChosenField.setPreferredSize(new java.awt.Dimension(250, 37));

        javax.swing.GroupLayout restorePageLayout = new javax.swing.GroupLayout(restorePage);
        restorePage.setLayout(restorePageLayout);
        restorePageLayout.setHorizontalGroup(
            restorePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, restorePageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(restorePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(RestoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(restoreLabel))
                .addGap(380, 380, 380))
            .addGroup(restorePageLayout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addComponent(chooseFileButton)
                .addGap(17, 17, 17)
                .addComponent(fileChosenField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(242, 242, 242))
        );
        restorePageLayout.setVerticalGroup(
            restorePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(restorePageLayout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(restoreLabel)
                .addGap(40, 40, 40)
                .addGroup(restorePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chooseFileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fileChosenField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(RestoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        cardPanel1.add(restorePage, "restore");

        userSearchPage.setBackground(new java.awt.Color(61, 96, 146));
        userSearchPage.setMaximumSize(new java.awt.Dimension(900, 640));
        userSearchPage.setMinimumSize(new java.awt.Dimension(900, 640));

        findUserLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        findUserLabel.setForeground(new java.awt.Color(255, 255, 255));
        findUserLabel.setText("Find User");

        userNumberLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        userNumberLabel.setForeground(new java.awt.Color(255, 255, 255));
        userNumberLabel.setText("User number:");

        UserNumberField.setMaximumSize(new java.awt.Dimension(250, 37));
        UserNumberField.setMinimumSize(new java.awt.Dimension(250, 37));
        UserNumberField.setPreferredSize(new java.awt.Dimension(250, 37));

        cancelSearchButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cancelSearchButton.setText("Cancel");
        cancelSearchButton.setMaximumSize(new java.awt.Dimension(163, 37));
        cancelSearchButton.setMinimumSize(new java.awt.Dimension(163, 37));
        cancelSearchButton.setPreferredSize(new java.awt.Dimension(163, 37));
        cancelSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelSearchButtonActionPerformed(evt);
            }
        });

        UserFirstnameField.setMaximumSize(new java.awt.Dimension(250, 37));
        UserFirstnameField.setMinimumSize(new java.awt.Dimension(250, 37));
        UserFirstnameField.setPreferredSize(new java.awt.Dimension(250, 37));

        userFirstnameLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        userFirstnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        userFirstnameLabel.setText("Firstname:");

        UserLastnameField.setMaximumSize(new java.awt.Dimension(250, 37));
        UserLastnameField.setMinimumSize(new java.awt.Dimension(250, 37));
        UserLastnameField.setPreferredSize(new java.awt.Dimension(250, 37));

        userLastnameLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        userLastnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        userLastnameLabel.setText("Lastname:");

        UserRoleSearchDrop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Any", "Office Manager","Shift Manager","Receptionist","Technician" }));
        UserRoleSearchDrop.setMaximumSize(new java.awt.Dimension(250, 45));
        UserRoleSearchDrop.setMinimumSize(new java.awt.Dimension(250, 45));
        UserRoleSearchDrop.setPreferredSize(new java.awt.Dimension(250, 45));
        UserRoleSearchDrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserRoleSearchDropActionPerformed(evt);
            }
        });

        userLastnameLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        userLastnameLabel1.setForeground(new java.awt.Color(255, 255, 255));
        userLastnameLabel1.setText("Role:");

        searchUserButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        searchUserButton.setText("Search");
        searchUserButton.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                searchUserButtonComponentHidden(evt);
            }
        });
        searchUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchUserButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userSearchPageLayout = new javax.swing.GroupLayout(userSearchPage);
        userSearchPage.setLayout(userSearchPageLayout);
        userSearchPageLayout.setHorizontalGroup(
            userSearchPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userSearchPageLayout.createSequentialGroup()
                .addGroup(userSearchPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userSearchPageLayout.createSequentialGroup()
                        .addGroup(userSearchPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(userSearchPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(userSearchPageLayout.createSequentialGroup()
                                    .addGap(364, 364, 364)
                                    .addComponent(findUserLabel))
                                .addGroup(userSearchPageLayout.createSequentialGroup()
                                    .addGap(235, 235, 235)
                                    .addComponent(userNumberLabel)
                                    .addGap(18, 18, 18)
                                    .addComponent(UserNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(userSearchPageLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(userSearchPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(userSearchPageLayout.createSequentialGroup()
                                        .addComponent(userFirstnameLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(UserFirstnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(userSearchPageLayout.createSequentialGroup()
                                        .addGroup(userSearchPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(userLastnameLabel)
                                            .addComponent(userLastnameLabel1))
                                        .addGap(18, 18, 18)
                                        .addGroup(userSearchPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(UserRoleSearchDrop, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(UserLastnameField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userSearchPageLayout.createSequentialGroup()
                        .addComponent(cancelSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(searchUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        userSearchPageLayout.setVerticalGroup(
            userSearchPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userSearchPageLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(findUserLabel)
                .addGap(25, 25, 25)
                .addGroup(userSearchPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNumberLabel)
                    .addComponent(UserNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(userSearchPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserFirstnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userFirstnameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(userSearchPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserLastnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userLastnameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(userSearchPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserRoleSearchDrop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userLastnameLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addGroup(userSearchPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );

        cardPanel1.add(userSearchPage, "userSearch");

        backupPage.setBackground(new java.awt.Color(61, 96, 146));
        backupPage.setMaximumSize(new java.awt.Dimension(900, 640));
        backupPage.setMinimumSize(new java.awt.Dimension(900, 640));

        BackupDataLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        BackupDataLabel.setForeground(new java.awt.Color(255, 255, 255));
        BackupDataLabel.setText("Backup Data");

        backupButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        backupButton.setText("Backup");
        backupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backupButtonActionPerformed(evt);
            }
        });

        chooseLocationButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        chooseLocationButton.setText("Choose location");
        chooseLocationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseLocationButtonActionPerformed(evt);
            }
        });

        locationChosenField.setEditable(false);
        locationChosenField.setMaximumSize(new java.awt.Dimension(250, 37));
        locationChosenField.setMinimumSize(new java.awt.Dimension(250, 37));
        locationChosenField.setPreferredSize(new java.awt.Dimension(250, 37));

        backupDestinationLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        backupDestinationLabel.setForeground(new java.awt.Color(255, 255, 255));
        backupDestinationLabel.setText("Back up destination:");

        javax.swing.GroupLayout backupPageLayout = new javax.swing.GroupLayout(backupPage);
        backupPage.setLayout(backupPageLayout);
        backupPageLayout.setHorizontalGroup(
            backupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backupPageLayout.createSequentialGroup()
                .addContainerGap(334, Short.MAX_VALUE)
                .addGroup(backupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backupPageLayout.createSequentialGroup()
                        .addComponent(backupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backupPageLayout.createSequentialGroup()
                        .addComponent(BackupDataLabel)
                        .addContainerGap(335, Short.MAX_VALUE))))
            .addGroup(backupPageLayout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(backupDestinationLabel)
                .addGap(18, 18, 18)
                .addGroup(backupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chooseLocationButton)
                    .addComponent(locationChosenField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backupPageLayout.setVerticalGroup(
            backupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backupPageLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(BackupDataLabel)
                .addGap(18, 18, 18)
                .addGroup(backupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locationChosenField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backupDestinationLabel))
                .addGap(25, 25, 25)
                .addComponent(chooseLocationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
                .addComponent(backupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        cardPanel1.add(backupPage, "backup");

        userResultsPage.setBackground(new java.awt.Color(61, 96, 146));
        userResultsPage.setMaximumSize(new java.awt.Dimension(900, 640));
        userResultsPage.setMinimumSize(new java.awt.Dimension(900, 640));
        userResultsPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                userResultsPageComponentHidden(evt);
            }
        });

        jScrollPane1.setPreferredSize(new java.awt.Dimension(750, 400));

        userResultsTable.setAutoCreateRowSorter(true);
        userResultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Account number", "Firstname", "Lastname", "Role", "Date registered"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userResultsTable.setMaximumSize(new java.awt.Dimension(750, 500));
        userResultsTable.setMinimumSize(new java.awt.Dimension(750, 500));
        userResultsTable.setPreferredSize(new java.awt.Dimension(750, 500));
        userResultsTable.getTableHeader().setReorderingAllowed(false);
        userResultsTable.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                userResultsTableComponentHidden(evt);
            }
        });
        jScrollPane1.setViewportView(userResultsTable);
        if (userResultsTable.getColumnModel().getColumnCount() > 0) {
            userResultsTable.getColumnModel().getColumn(0).setResizable(false);
            userResultsTable.getColumnModel().getColumn(1).setResizable(false);
            userResultsTable.getColumnModel().getColumn(2).setResizable(false);
            userResultsTable.getColumnModel().getColumn(3).setResizable(false);
            userResultsTable.getColumnModel().getColumn(4).setResizable(false);
        }

        searchAgainButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        searchAgainButton.setText("Search Again");
        searchAgainButton.setMaximumSize(new java.awt.Dimension(175, 45));
        searchAgainButton.setMinimumSize(new java.awt.Dimension(175, 45));
        searchAgainButton.setPreferredSize(new java.awt.Dimension(175, 45));
        searchAgainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAgainButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.setMaximumSize(new java.awt.Dimension(130, 45));
        deleteButton.setMinimumSize(new java.awt.Dimension(130, 45));
        deleteButton.setPreferredSize(new java.awt.Dimension(130, 45));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        changeRoleButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        changeRoleButton.setText("Change role");
        changeRoleButton.setMaximumSize(new java.awt.Dimension(165, 45));
        changeRoleButton.setMinimumSize(new java.awt.Dimension(165, 45));
        changeRoleButton.setPreferredSize(new java.awt.Dimension(165, 45));
        changeRoleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeRoleButtonActionPerformed(evt);
            }
        });

        selectUserButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        selectUserButton.setText("Select User");
        selectUserButton.setMaximumSize(new java.awt.Dimension(210, 45));
        selectUserButton.setMinimumSize(new java.awt.Dimension(210, 45));
        selectUserButton.setPreferredSize(new java.awt.Dimension(210, 45));
        selectUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectUserButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userResultsPageLayout = new javax.swing.GroupLayout(userResultsPage);
        userResultsPage.setLayout(userResultsPageLayout);
        userResultsPageLayout.setHorizontalGroup(
            userResultsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userResultsPageLayout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(userResultsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(searchAgainButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, userResultsPageLayout.createSequentialGroup()
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(changeRoleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(selectUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75))
        );
        userResultsPageLayout.setVerticalGroup(
            userResultsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userResultsPageLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(userResultsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeRoleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(searchAgainButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        cardPanel1.add(userResultsPage, "userResults");

        createUserPage.setBackground(new java.awt.Color(61, 96, 146));
        createUserPage.setMaximumSize(new java.awt.Dimension(900, 640));
        createUserPage.setMinimumSize(new java.awt.Dimension(900, 640));
        createUserPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                createUserPageComponentHidden(evt);
            }
        });

        newUserLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        newUserLabel.setForeground(new java.awt.Color(255, 255, 255));
        newUserLabel.setText("New User");

        userLastNameField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        userLastNameField.setMaximumSize(new java.awt.Dimension(250, 42));
        userLastNameField.setMinimumSize(new java.awt.Dimension(250, 42));
        userLastNameField.setPreferredSize(new java.awt.Dimension(250, 42));

        userFirstNameField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        userFirstNameField.setMaximumSize(new java.awt.Dimension(250, 42));
        userFirstNameField.setMinimumSize(new java.awt.Dimension(250, 42));
        userFirstNameField.setPreferredSize(new java.awt.Dimension(250, 42));
        userFirstNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userFirstNameFieldActionPerformed(evt);
            }
        });

        NewRepeatPasswordField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        NewRepeatPasswordField.setMaximumSize(new java.awt.Dimension(250, 42));
        NewRepeatPasswordField.setMinimumSize(new java.awt.Dimension(250, 42));
        NewRepeatPasswordField.setPreferredSize(new java.awt.Dimension(250, 42));
        NewRepeatPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewRepeatPasswordFieldActionPerformed(evt);
            }
        });

        NewPasswordField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        NewPasswordField.setMaximumSize(new java.awt.Dimension(250, 42));
        NewPasswordField.setMinimumSize(new java.awt.Dimension(250, 42));
        NewPasswordField.setPreferredSize(new java.awt.Dimension(250, 42));
        NewPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewPasswordFieldActionPerformed(evt);
            }
        });

        userRoleComboBox.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        userRoleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Technician", "Shift Manager", "Office Manager", "Receptionist" }));
        userRoleComboBox.setMaximumSize(new java.awt.Dimension(250, 42));
        userRoleComboBox.setMinimumSize(new java.awt.Dimension(250, 42));
        userRoleComboBox.setPreferredSize(new java.awt.Dimension(250, 42));
        userRoleComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userRoleComboBoxActionPerformed(evt);
            }
        });

        firstnameLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        firstnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        firstnameLabel.setText("Firstname:");

        lastnameLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lastnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        lastnameLabel.setText("Lastname:");

        RoleLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        RoleLabel.setForeground(new java.awt.Color(255, 255, 255));
        RoleLabel.setText("Role:");

        passwordLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel.setText("Password:");

        ReenterPasswordLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ReenterPasswordLabel.setForeground(new java.awt.Color(255, 255, 255));
        ReenterPasswordLabel.setText("Reenter password:");

        createUserButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        createUserButton.setText("Create User");
        createUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createUserButtonActionPerformed(evt);
            }
        });

        usernameField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        usernameField.setMaximumSize(new java.awt.Dimension(250, 42));
        usernameField.setMinimumSize(new java.awt.Dimension(250, 42));
        usernameField.setPreferredSize(new java.awt.Dimension(250, 42));
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        usernameLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setText("Username:");

        javax.swing.GroupLayout createUserPageLayout = new javax.swing.GroupLayout(createUserPage);
        createUserPage.setLayout(createUserPageLayout);
        createUserPageLayout.setHorizontalGroup(
            createUserPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createUserPageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(createUserPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createUserPageLayout.createSequentialGroup()
                        .addComponent(createUserButton)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createUserPageLayout.createSequentialGroup()
                        .addComponent(newUserLabel)
                        .addGap(362, 362, 362))))
            .addGroup(createUserPageLayout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addGroup(createUserPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(createUserPageLayout.createSequentialGroup()
                        .addComponent(usernameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(createUserPageLayout.createSequentialGroup()
                        .addGroup(createUserPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lastnameLabel)
                            .addComponent(RoleLabel)
                            .addComponent(passwordLabel)
                            .addComponent(ReenterPasswordLabel)
                            .addComponent(firstnameLabel))
                        .addGap(18, 18, 18)
                        .addGroup(createUserPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userRoleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NewPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NewRepeatPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(204, Short.MAX_VALUE))
        );
        createUserPageLayout.setVerticalGroup(
            createUserPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createUserPageLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(newUserLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createUserPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(createUserPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstnameLabel))
                .addGap(25, 25, 25)
                .addGroup(createUserPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastnameLabel))
                .addGap(25, 25, 25)
                .addGroup(createUserPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userRoleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RoleLabel))
                .addGap(25, 25, 25)
                .addGroup(createUserPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NewPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel))
                .addGap(25, 25, 25)
                .addGroup(createUserPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NewRepeatPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ReenterPasswordLabel))
                .addGap(40, 40, 40)
                .addComponent(createUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        cardPanel1.add(createUserPage, "createUser");

        createTaskPage.setBackground(new java.awt.Color(61, 96, 146));
        createTaskPage.setMaximumSize(new java.awt.Dimension(900, 640));
        createTaskPage.setMinimumSize(new java.awt.Dimension(900, 640));
        createTaskPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                createTaskPageComponentHidden(evt);
            }
        });

        newTaskLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        newTaskLabel1.setForeground(new java.awt.Color(255, 255, 255));
        newTaskLabel1.setText("New Task");

        descriptionNewTaskField.setMaximumSize(new java.awt.Dimension(250, 42));
        descriptionNewTaskField.setMinimumSize(new java.awt.Dimension(250, 42));
        descriptionNewTaskField.setPreferredSize(new java.awt.Dimension(250, 42));

        durationNewTaskDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"}));
        durationNewTaskDD.setMaximumSize(new java.awt.Dimension(250, 42));
        durationNewTaskDD.setMinimumSize(new java.awt.Dimension(250, 42));
        durationNewTaskDD.setPreferredSize(new java.awt.Dimension(250, 42));
        durationNewTaskDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                durationNewTaskDDActionPerformed(evt);
            }
        });

        descriptionLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        descriptionLabel.setForeground(new java.awt.Color(255, 255, 255));
        descriptionLabel.setText("Description:");

        durationLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        durationLabel.setForeground(new java.awt.Color(255, 255, 255));
        durationLabel.setText("Duration:");

        priceLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        priceLabel.setForeground(new java.awt.Color(255, 255, 255));
        priceLabel.setText("Price:");

        departmentNewTaskLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        departmentNewTaskLabel.setForeground(new java.awt.Color(255, 255, 255));
        departmentNewTaskLabel.setText("Department:");

        shelfSlotNewTaskLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        shelfSlotNewTaskLabel.setForeground(new java.awt.Color(255, 255, 255));
        shelfSlotNewTaskLabel.setText("Shelf slot:");

        createNewTaskButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        createNewTaskButton.setText("Create ");
        createNewTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewTaskButtonActionPerformed(evt);
            }
        });

        shelfSlotTaskDD.setModel(new javax.swing.DefaultComboBoxModel<>(controller.getShelfSlots()));
        shelfSlotTaskDD.setMaximumSize(new java.awt.Dimension(250, 42));
        shelfSlotTaskDD.setMinimumSize(new java.awt.Dimension(250, 42));
        shelfSlotTaskDD.setPreferredSize(new java.awt.Dimension(250, 42));
        shelfSlotTaskDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shelfSlotTaskDDActionPerformed(evt);
            }
        });

        departmentNewTaskDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Copy room","Dark room","Development area", "Printing room", "Finishing room", "Packaging department"}));
        departmentNewTaskDD.setMaximumSize(new java.awt.Dimension(250, 42));
        departmentNewTaskDD.setMinimumSize(new java.awt.Dimension(250, 42));
        departmentNewTaskDD.setPreferredSize(new java.awt.Dimension(250, 42));
        departmentNewTaskDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentNewTaskDDActionPerformed(evt);
            }
        });

        durationNewTaskMinsDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"0","15","30","45"}));
        durationNewTaskMinsDD.setMaximumSize(new java.awt.Dimension(250, 42));
        durationNewTaskMinsDD.setMinimumSize(new java.awt.Dimension(250, 42));
        durationNewTaskMinsDD.setPreferredSize(new java.awt.Dimension(250, 42));
        durationNewTaskMinsDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                durationNewTaskMinsDDActionPerformed(evt);
            }
        });

        priceNewTaskField.setMaximumSize(new java.awt.Dimension(250, 42));
        priceNewTaskField.setMinimumSize(new java.awt.Dimension(250, 42));
        priceNewTaskField.setPreferredSize(new java.awt.Dimension(250, 42));

        hoursLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        hoursLabel.setForeground(new java.awt.Color(255, 255, 255));
        hoursLabel.setText("hrs");

        minutesLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        minutesLabel.setForeground(new java.awt.Color(255, 255, 255));
        minutesLabel.setText("mins");

        javax.swing.GroupLayout createTaskPageLayout = new javax.swing.GroupLayout(createTaskPage);
        createTaskPage.setLayout(createTaskPageLayout);
        createTaskPageLayout.setHorizontalGroup(
            createTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createTaskPageLayout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addGroup(createTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(durationLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(priceLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(departmentNewTaskLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(shelfSlotNewTaskLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(createTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionNewTaskField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shelfSlotTaskDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departmentNewTaskDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(createTaskPageLayout.createSequentialGroup()
                        .addComponent(durationNewTaskDD, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(hoursLabel)
                        .addGap(18, 18, 18)
                        .addComponent(durationNewTaskMinsDD, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(minutesLabel))
                    .addComponent(priceNewTaskField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(134, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createTaskPageLayout.createSequentialGroup()
                .addContainerGap(361, Short.MAX_VALUE)
                .addGroup(createTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createTaskPageLayout.createSequentialGroup()
                        .addComponent(newTaskLabel1)
                        .addGap(362, 362, 362))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createTaskPageLayout.createSequentialGroup()
                        .addComponent(createNewTaskButton)
                        .addGap(50, 50, 50))))
        );
        createTaskPageLayout.setVerticalGroup(
            createTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createTaskPageLayout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(newTaskLabel1)
                .addGap(18, 18, 18)
                .addGroup(createTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionNewTaskField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descriptionLabel))
                .addGap(26, 26, 26)
                .addGroup(createTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(durationLabel)
                    .addComponent(durationNewTaskDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(durationNewTaskMinsDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hoursLabel)
                    .addComponent(minutesLabel))
                .addGap(23, 23, 23)
                .addGroup(createTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceLabel)
                    .addComponent(priceNewTaskField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(createTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(departmentNewTaskLabel)
                    .addComponent(departmentNewTaskDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(createTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shelfSlotNewTaskLabel)
                    .addComponent(shelfSlotTaskDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(createNewTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        cardPanel1.add(createTaskPage, "createTask");

        reportHomePage.setBackground(new java.awt.Color(61, 96, 146));
        reportHomePage.setMaximumSize(new java.awt.Dimension(900, 640));
        reportHomePage.setMinimumSize(new java.awt.Dimension(900, 640));
        reportHomePage.setPreferredSize(new java.awt.Dimension(900, 640));

        manageReportsPageButton.setText("Manage Reports");

        createReportPageButton.setText("Create Report");
        createReportPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createReportPageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reportHomePageLayout = new javax.swing.GroupLayout(reportHomePage);
        reportHomePage.setLayout(reportHomePageLayout);
        reportHomePageLayout.setHorizontalGroup(
            reportHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportHomePageLayout.createSequentialGroup()
                .addContainerGap(248, Short.MAX_VALUE)
                .addComponent(manageReportsPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createReportPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(248, Short.MAX_VALUE))
        );
        reportHomePageLayout.setVerticalGroup(
            reportHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportHomePageLayout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addGroup(reportHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageReportsPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createReportPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(254, Short.MAX_VALUE))
        );

        cardPanel1.add(reportHomePage, "reportHomePage");

        homePage.setBackground(new java.awt.Color(61, 96, 146));
        homePage.setMaximumSize(new java.awt.Dimension(900, 640));
        homePage.setMinimumSize(new java.awt.Dimension(900, 640));

        jobMenuPageButton.setText("Job");
        jobMenuPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jobMenuPageButtonActionPerformed(evt);
            }
        });

        reportsMenuPageButton.setText("Reports");
        reportsMenuPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsMenuPageButtonActionPerformed(evt);
            }
        });

        tasksMenuPageButton.setText("Tasks");
        tasksMenuPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tasksMenuPageButtonActionPerformed(evt);
            }
        });

        usersMenuPageButton.setText("Users");
        usersMenuPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersMenuPageButtonActionPerformed(evt);
            }
        });

        manageCustomersMenuPageButton.setText("Manage Customers");
        manageCustomersMenuPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageCustomersMenuPageButtonActionPerformed(evt);
            }
        });

        settingsMenuPageButton.setText("Settings");
        settingsMenuPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsMenuPageButtonActionPerformed(evt);
            }
        });

        standardJobsMenuPageButton.setText("Standard Jobs");
        standardJobsMenuPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                standardJobsMenuPageButtonActionPerformed(evt);
            }
        });

        acceptPaymentPageButton.setText("Accept Payment");
        acceptPaymentPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptPaymentPageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout homePageLayout = new javax.swing.GroupLayout(homePage);
        homePage.setLayout(homePageLayout);
        homePageLayout.setHorizontalGroup(
            homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePageLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(homePageLayout.createSequentialGroup()
                        .addComponent(jobMenuPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reportsMenuPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tasksMenuPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(usersMenuPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(homePageLayout.createSequentialGroup()
                        .addComponent(acceptPaymentPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(standardJobsMenuPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(settingsMenuPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(manageCustomersMenuPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        homePageLayout.setVerticalGroup(
            homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePageLayout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addGroup(homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jobMenuPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tasksMenuPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usersMenuPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportsMenuPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addGroup(homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageCustomersMenuPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(settingsMenuPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(standardJobsMenuPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceptPaymentPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        cardPanel1.add(homePage, "homePage");

        jobHomePage.setBackground(new java.awt.Color(61, 96, 146));
        jobHomePage.setMaximumSize(new java.awt.Dimension(900, 640));
        jobHomePage.setMinimumSize(new java.awt.Dimension(900, 640));

        acceptJobPageButton.setText("Accept Job");
        acceptJobPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptJobPageButtonActionPerformed(evt);
            }
        });

        collectJobPageButton.setText("Collect Job");
        collectJobPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collectJobPageButtonActionPerformed(evt);
            }
        });

        jobEnquiryPageButton.setText("Job Enquiry");
        jobEnquiryPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jobEnquiryPageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jobHomePageLayout = new javax.swing.GroupLayout(jobHomePage);
        jobHomePage.setLayout(jobHomePageLayout);
        jobHomePageLayout.setHorizontalGroup(
            jobHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jobHomePageLayout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addComponent(acceptJobPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(collectJobPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jobEnquiryPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jobHomePageLayout.setVerticalGroup(
            jobHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jobHomePageLayout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addGroup(jobHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acceptJobPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(collectJobPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jobEnquiryPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(254, Short.MAX_VALUE))
        );

        cardPanel1.add(jobHomePage, "jobHomePage");

        taskHomePage.setBackground(new java.awt.Color(61, 96, 146));
        taskHomePage.setMaximumSize(new java.awt.Dimension(900, 640));
        taskHomePage.setMinimumSize(new java.awt.Dimension(900, 640));

        manageTasksPageButton.setText("Manage Tasks");
        manageTasksPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageTasksPageButtonActionPerformed(evt);
            }
        });

        createTaskPageButton.setText("Create Task");
        createTaskPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTaskPageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout taskHomePageLayout = new javax.swing.GroupLayout(taskHomePage);
        taskHomePage.setLayout(taskHomePageLayout);
        taskHomePageLayout.setHorizontalGroup(
            taskHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taskHomePageLayout.createSequentialGroup()
                .addContainerGap(248, Short.MAX_VALUE)
                .addComponent(manageTasksPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createTaskPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(248, Short.MAX_VALUE))
        );
        taskHomePageLayout.setVerticalGroup(
            taskHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskHomePageLayout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addGroup(taskHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageTasksPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createTaskPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(254, Short.MAX_VALUE))
        );

        cardPanel1.add(taskHomePage, "taskHomePage");

        userHomePage.setBackground(new java.awt.Color(61, 96, 146));
        userHomePage.setMaximumSize(new java.awt.Dimension(900, 640));
        userHomePage.setMinimumSize(new java.awt.Dimension(900, 640));

        manageUsersPageButton.setText("Manage Users");
        manageUsersPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageUsersPageButtonActionPerformed(evt);
            }
        });

        createUserPageButton.setText("Create User");
        createUserPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createUserPageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userHomePageLayout = new javax.swing.GroupLayout(userHomePage);
        userHomePage.setLayout(userHomePageLayout);
        userHomePageLayout.setHorizontalGroup(
            userHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userHomePageLayout.createSequentialGroup()
                .addContainerGap(248, Short.MAX_VALUE)
                .addComponent(manageUsersPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createUserPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(248, Short.MAX_VALUE))
        );
        userHomePageLayout.setVerticalGroup(
            userHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userHomePageLayout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addGroup(userHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageUsersPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createUserPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(254, Short.MAX_VALUE))
        );

        cardPanel1.add(userHomePage, "userHomePage");

        standardJobHomePage.setBackground(new java.awt.Color(61, 96, 146));
        standardJobHomePage.setMaximumSize(new java.awt.Dimension(900, 640));
        standardJobHomePage.setMinimumSize(new java.awt.Dimension(900, 640));

        manageSJobPageButton.setText("Manage Standard Jobs");

        createSJobPageButton.setText("Create Standard Job");
        createSJobPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createSJobPageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout standardJobHomePageLayout = new javax.swing.GroupLayout(standardJobHomePage);
        standardJobHomePage.setLayout(standardJobHomePageLayout);
        standardJobHomePageLayout.setHorizontalGroup(
            standardJobHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, standardJobHomePageLayout.createSequentialGroup()
                .addContainerGap(248, Short.MAX_VALUE)
                .addComponent(manageSJobPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createSJobPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(248, Short.MAX_VALUE))
        );
        standardJobHomePageLayout.setVerticalGroup(
            standardJobHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(standardJobHomePageLayout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addGroup(standardJobHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageSJobPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createSJobPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(254, Short.MAX_VALUE))
        );

        cardPanel1.add(standardJobHomePage, "standardJobHomePage");

        settingsHomePage.setBackground(new java.awt.Color(61, 96, 146));
        settingsHomePage.setMaximumSize(new java.awt.Dimension(900, 640));
        settingsHomePage.setMinimumSize(new java.awt.Dimension(900, 640));

        backupPageButton.setText("Backup Data");
        backupPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backupPageButtonActionPerformed(evt);
            }
        });

        backupSettingsPageButton.setText("Backup Settings");
        backupSettingsPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backupSettingsPageButtonActionPerformed(evt);
            }
        });

        reportSettingsPageButton.setText("Report Settings");

        restoreSettingsPageButton.setText("Restore Settings");

        javax.swing.GroupLayout settingsHomePageLayout = new javax.swing.GroupLayout(settingsHomePage);
        settingsHomePage.setLayout(settingsHomePageLayout);
        settingsHomePageLayout.setHorizontalGroup(
            settingsHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsHomePageLayout.createSequentialGroup()
                .addContainerGap(248, Short.MAX_VALUE)
                .addGroup(settingsHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settingsHomePageLayout.createSequentialGroup()
                        .addComponent(reportSettingsPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(restoreSettingsPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(settingsHomePageLayout.createSequentialGroup()
                        .addComponent(backupPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(backupSettingsPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(248, Short.MAX_VALUE))
        );
        settingsHomePageLayout.setVerticalGroup(
            settingsHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsHomePageLayout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addGroup(settingsHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backupPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backupSettingsPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97)
                .addGroup(settingsHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reportSettingsPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(restoreSettingsPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        cardPanel1.add(settingsHomePage, "settingsHomePage");

        createCustomerPage.setBackground(new java.awt.Color(61, 96, 146));
        createCustomerPage.setMaximumSize(new java.awt.Dimension(900, 640));
        createCustomerPage.setMinimumSize(new java.awt.Dimension(900, 640));
        createCustomerPage.setName(""); // NOI18N
        createCustomerPage.setPreferredSize(new java.awt.Dimension(900, 640));
        createCustomerPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                createCustomerPageComponentHidden(evt);
            }
        });

        prefixjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        prefixjLabel.setForeground(new java.awt.Color(255, 255, 255));
        prefixjLabel.setText("Prefix:");
        prefixjLabel.setMaximumSize(new java.awt.Dimension(128, 29));
        prefixjLabel.setMinimumSize(new java.awt.Dimension(128, 29));
        prefixjLabel.setPreferredSize(new java.awt.Dimension(128, 29));

        firstNamejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        firstNamejLabel.setForeground(new java.awt.Color(255, 255, 255));
        firstNamejLabel.setText("First Name:");
        firstNamejLabel.setMaximumSize(new java.awt.Dimension(128, 29));
        firstNamejLabel.setMinimumSize(new java.awt.Dimension(128, 29));
        firstNamejLabel.setPreferredSize(new java.awt.Dimension(128, 29));

        LastnamejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LastnamejLabel.setForeground(new java.awt.Color(255, 255, 255));
        LastnamejLabel.setText("Lastname:");
        LastnamejLabel.setMaximumSize(new java.awt.Dimension(128, 29));
        LastnamejLabel.setMinimumSize(new java.awt.Dimension(128, 29));
        LastnamejLabel.setPreferredSize(new java.awt.Dimension(128, 29));

        phoneNumberjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        phoneNumberjLabel.setForeground(new java.awt.Color(255, 255, 255));
        phoneNumberjLabel.setText("Phone Number:");
        phoneNumberjLabel.setMaximumSize(new java.awt.Dimension(128, 29));
        phoneNumberjLabel.setMinimumSize(new java.awt.Dimension(128, 29));
        phoneNumberjLabel.setPreferredSize(new java.awt.Dimension(128, 29));

        postCodejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        postCodejLabel.setForeground(new java.awt.Color(255, 255, 255));
        postCodejLabel.setText("Post Code:");
        postCodejLabel.setMaximumSize(new java.awt.Dimension(128, 29));
        postCodejLabel.setMinimumSize(new java.awt.Dimension(128, 29));
        postCodejLabel.setPreferredSize(new java.awt.Dimension(128, 29));

        streetNamejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        streetNamejLabel.setForeground(new java.awt.Color(255, 255, 255));
        streetNamejLabel.setText("Street Name:");
        streetNamejLabel.setMaximumSize(new java.awt.Dimension(128, 29));
        streetNamejLabel.setMinimumSize(new java.awt.Dimension(128, 29));
        streetNamejLabel.setPreferredSize(new java.awt.Dimension(128, 29));

        prefixjComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        prefixjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a prefix", "Mr", "Mrs", "Miss", "Mx" }));
        prefixjComboBox.setMaximumSize(new java.awt.Dimension(161, 42));
        prefixjComboBox.setMinimumSize(new java.awt.Dimension(161, 42));
        prefixjComboBox.setName("Prefix"); // NOI18N
        prefixjComboBox.setPreferredSize(new java.awt.Dimension(161, 42));
        prefixjComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prefixjComboBoxActionPerformed(evt);
            }
        });

        createCustomerjButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        createCustomerjButton.setText("Create");
        createCustomerjButton.setMaximumSize(new java.awt.Dimension(101, 37));
        createCustomerjButton.setMinimumSize(new java.awt.Dimension(101, 37));
        createCustomerjButton.setPreferredSize(new java.awt.Dimension(101, 37));
        createCustomerjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createCustomerjButtonActionPerformed(evt);
            }
        });

        cancelCreationjButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cancelCreationjButton.setText("Cancel");
        cancelCreationjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelCreationjButtonActionPerformed(evt);
            }
        });

        postCodeField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        postCodeField.setMaximumSize(new java.awt.Dimension(161, 42));
        postCodeField.setMinimumSize(new java.awt.Dimension(161, 42));
        postCodeField.setName("Post code"); // NOI18N
        postCodeField.setPreferredSize(new java.awt.Dimension(161, 42));

        surnameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        surnameField.setMaximumSize(new java.awt.Dimension(250, 42));
        surnameField.setMinimumSize(new java.awt.Dimension(250, 42));
        surnameField.setName("Lastname"); // NOI18N
        surnameField.setPreferredSize(new java.awt.Dimension(250, 42));

        phoneNumberField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        phoneNumberField.setMaximumSize(new java.awt.Dimension(250, 42));
        phoneNumberField.setMinimumSize(new java.awt.Dimension(250, 42));
        phoneNumberField.setName("Phone number"); // NOI18N
        phoneNumberField.setPreferredSize(new java.awt.Dimension(250, 42));

        firstNameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        firstNameField.setMaximumSize(new java.awt.Dimension(250, 42));
        firstNameField.setMinimumSize(new java.awt.Dimension(250, 42));
        firstNameField.setName("Firstname"); // NOI18N
        firstNameField.setPreferredSize(new java.awt.Dimension(250, 42));
        firstNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameFieldActionPerformed(evt);
            }
        });

        buildingNumberField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buildingNumberField.setMaximumSize(new java.awt.Dimension(250, 42));
        buildingNumberField.setMinimumSize(new java.awt.Dimension(250, 42));
        buildingNumberField.setName("Building number"); // NOI18N
        buildingNumberField.setPreferredSize(new java.awt.Dimension(250, 42));
        buildingNumberField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildingNumberFieldActionPerformed(evt);
            }
        });

        streetNameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        streetNameField.setMaximumSize(new java.awt.Dimension(250, 42));
        streetNameField.setMinimumSize(new java.awt.Dimension(250, 42));
        streetNameField.setName("Street name"); // NOI18N
        streetNameField.setPreferredSize(new java.awt.Dimension(250, 42));

        cityField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cityField.setMaximumSize(new java.awt.Dimension(250, 42));
        cityField.setMinimumSize(new java.awt.Dimension(250, 42));
        cityField.setName("City"); // NOI18N
        cityField.setPreferredSize(new java.awt.Dimension(250, 42));

        cityjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        cityjLabel.setForeground(new java.awt.Color(255, 255, 255));
        cityjLabel.setText("City:");

        accountHolderNamejTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        accountHolderNamejTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        accountHolderNamejTextField.setName("Account holder name"); // NOI18N
        accountHolderNamejTextField.setPreferredSize(new java.awt.Dimension(250, 42));

        accountHolderNameLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        accountHolderNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        accountHolderNameLabel.setText("Account Holder Name:");
        accountHolderNameLabel.setMaximumSize(new java.awt.Dimension(128, 29));
        accountHolderNameLabel.setMinimumSize(new java.awt.Dimension(128, 29));
        accountHolderNameLabel.setPreferredSize(new java.awt.Dimension(128, 29));

        phoneNumberjLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        phoneNumberjLabel1.setForeground(new java.awt.Color(255, 255, 255));
        phoneNumberjLabel1.setText("Building Number:");
        phoneNumberjLabel1.setMaximumSize(new java.awt.Dimension(128, 29));
        phoneNumberjLabel1.setMinimumSize(new java.awt.Dimension(128, 29));
        phoneNumberjLabel1.setPreferredSize(new java.awt.Dimension(128, 29));

        javax.swing.GroupLayout createCustomerPageLayout = new javax.swing.GroupLayout(createCustomerPage);
        createCustomerPage.setLayout(createCustomerPageLayout);
        createCustomerPageLayout.setHorizontalGroup(
            createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createCustomerPageLayout.createSequentialGroup()
                .addContainerGap(118, Short.MAX_VALUE)
                .addGroup(createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createCustomerPageLayout.createSequentialGroup()
                        .addGap(470, 470, 470)
                        .addComponent(cancelCreationjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(createCustomerjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(createCustomerPageLayout.createSequentialGroup()
                        .addGroup(createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(accountHolderNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(prefixjLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(firstNamejLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(phoneNumberjLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(postCodejLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(streetNamejLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cityjLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(LastnamejLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(phoneNumberjLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(accountHolderNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(surnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(postCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(streetNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buildingNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prefixjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(72, 72, 72))
        );
        createCustomerPageLayout.setVerticalGroup(
            createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createCustomerPageLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(accountHolderNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountHolderNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prefixjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prefixjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(surnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LastnamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(phoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumberjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buildingNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumberjLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(postCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(postCodejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(streetNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(streetNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cityjLabel))
                .addGap(18, 18, 18)
                .addGroup(createCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createCustomerjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelCreationjButton))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        accountHolderNamejTextField.getAccessibleContext().setAccessibleName("");

        cardPanel1.add(createCustomerPage, "createCustomer");

        searchCustomerPage.setMaximumSize(new java.awt.Dimension(900, 640));
        searchCustomerPage.setMinimumSize(new java.awt.Dimension(900, 640));
        searchCustomerPage.setPreferredSize(new java.awt.Dimension(900, 640));
        searchCustomerPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                searchCustomerPageComponentHidden(evt);
            }
        });

        searchCustomerjPanel.setBackground(new java.awt.Color(61, 96, 146));
        searchCustomerjPanel.setMaximumSize(new java.awt.Dimension(900, 700));
        searchCustomerjPanel.setMinimumSize(new java.awt.Dimension(900, 700));
        searchCustomerjPanel.setPreferredSize(new java.awt.Dimension(900, 640));

        receptionistjPanel.setBackground(new java.awt.Color(34, 54, 81));
        receptionistjPanel.setMaximumSize(new java.awt.Dimension(900, 250));
        receptionistjPanel.setMinimumSize(new java.awt.Dimension(900, 250));

        searchCustomerAccountNojLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        searchCustomerAccountNojLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchCustomerAccountNojLabel.setText("Customer account number:");

        searchContactFirstNamejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        searchContactFirstNamejLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchContactFirstNamejLabel.setText("Contact firstname:");

        searchContactSurnamejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        searchContactSurnamejLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchContactSurnamejLabel.setText("Contact lastname:");

        searchAccountHolderNamejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        searchAccountHolderNamejLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchAccountHolderNamejLabel.setText("Account Holder Name:");

        custAccountNoField.setMaximumSize(new java.awt.Dimension(250, 42));
        custAccountNoField.setMinimumSize(new java.awt.Dimension(250, 42));
        custAccountNoField.setPreferredSize(new java.awt.Dimension(250, 42));

        custFirstnameField.setMaximumSize(new java.awt.Dimension(250, 42));
        custFirstnameField.setMinimumSize(new java.awt.Dimension(250, 42));
        custFirstnameField.setPreferredSize(new java.awt.Dimension(250, 42));

        custLastnameField.setMaximumSize(new java.awt.Dimension(250, 42));
        custLastnameField.setMinimumSize(new java.awt.Dimension(250, 42));
        custLastnameField.setPreferredSize(new java.awt.Dimension(250, 42));

        custAccountHNameField.setMaximumSize(new java.awt.Dimension(250, 42));
        custAccountHNameField.setMinimumSize(new java.awt.Dimension(250, 42));
        custAccountHNameField.setPreferredSize(new java.awt.Dimension(250, 42));

        javax.swing.GroupLayout receptionistjPanelLayout = new javax.swing.GroupLayout(receptionistjPanel);
        receptionistjPanel.setLayout(receptionistjPanelLayout);
        receptionistjPanelLayout.setHorizontalGroup(
            receptionistjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receptionistjPanelLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(receptionistjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchAccountHolderNamejLabel)
                    .addComponent(searchContactSurnamejLabel)
                    .addComponent(searchContactFirstNamejLabel)
                    .addComponent(searchCustomerAccountNojLabel))
                .addGap(10, 10, 10)
                .addGroup(receptionistjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(custAccountNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(custFirstnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(custLastnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(custAccountHNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        receptionistjPanelLayout.setVerticalGroup(
            receptionistjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receptionistjPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(receptionistjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchCustomerAccountNojLabel)
                    .addComponent(custAccountNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receptionistjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchContactFirstNamejLabel)
                    .addComponent(custFirstnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receptionistjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchContactSurnamejLabel)
                    .addComponent(custLastnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receptionistjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchAccountHolderNamejLabel)
                    .addComponent(custAccountHNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        searchCustomerFJobjButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        searchCustomerFJobjButton.setText("Search");
        searchCustomerFJobjButton.setMaximumSize(new java.awt.Dimension(163, 37));
        searchCustomerFJobjButton.setMinimumSize(new java.awt.Dimension(163, 37));
        searchCustomerFJobjButton.setPreferredSize(new java.awt.Dimension(163, 37));
        searchCustomerFJobjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCustomerFJobjButtonActionPerformed(evt);
            }
        });

        cancelCustomerFJobjButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cancelCustomerFJobjButton.setText("Cancel");
        cancelCustomerFJobjButton.setMaximumSize(new java.awt.Dimension(163, 37));
        cancelCustomerFJobjButton.setMinimumSize(new java.awt.Dimension(163, 37));
        cancelCustomerFJobjButton.setPreferredSize(new java.awt.Dimension(163, 37));
        cancelCustomerFJobjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelCustomerFJobjButtonActionPerformed(evt);
            }
        });

        managerjPanel.setBackground(new java.awt.Color(34, 54, 81));
        managerjPanel.setMaximumSize(new java.awt.Dimension(900, 250));
        managerjPanel.setMinimumSize(new java.awt.Dimension(900, 250));
        managerjPanel.setVisible(false);

        streetNameSjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        streetNameSjLabel.setForeground(new java.awt.Color(255, 255, 255));
        streetNameSjLabel.setText("Street name:");

        postcodeSjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        postcodeSjLabel.setForeground(new java.awt.Color(255, 255, 255));
        postcodeSjLabel.setText("Postcode:");

        citySjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        citySjLabel.setForeground(new java.awt.Color(255, 255, 255));
        citySjLabel.setText("City:");

        phoneSjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        phoneSjLabel.setForeground(new java.awt.Color(255, 255, 255));
        phoneSjLabel.setText("Phone:");

        customerTypeSjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        customerTypeSjLabel.setForeground(new java.awt.Color(255, 255, 255));
        customerTypeSjLabel.setText("Customer type:");

        accountStatusjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        accountStatusjLabel.setForeground(new java.awt.Color(255, 255, 255));
        accountStatusjLabel.setText("Account status:");

        inDefaultSjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        inDefaultSjLabel.setForeground(new java.awt.Color(255, 255, 255));
        inDefaultSjLabel.setText("In Default:");

        registrationDateSjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        registrationDateSjLabel.setForeground(new java.awt.Color(255, 255, 255));
        registrationDateSjLabel.setText("Registration date:");

        streetNameField1.setMaximumSize(new java.awt.Dimension(250, 42));
        streetNameField1.setMinimumSize(new java.awt.Dimension(250, 42));
        streetNameField1.setPreferredSize(new java.awt.Dimension(250, 42));

        postCodeField1.setMaximumSize(new java.awt.Dimension(250, 42));
        postCodeField1.setMinimumSize(new java.awt.Dimension(250, 42));
        postCodeField1.setPreferredSize(new java.awt.Dimension(250, 42));

        cityField1.setMaximumSize(new java.awt.Dimension(250, 42));
        cityField1.setMinimumSize(new java.awt.Dimension(250, 42));
        cityField1.setPreferredSize(new java.awt.Dimension(250, 42));
        cityField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityField1ActionPerformed(evt);
            }
        });

        phoneField1.setMaximumSize(new java.awt.Dimension(250, 42));
        phoneField1.setMinimumSize(new java.awt.Dimension(250, 42));
        phoneField1.setPreferredSize(new java.awt.Dimension(250, 42));

        customerTypeDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any", "Valued", "Normal" }));
        customerTypeDD.setMaximumSize(new java.awt.Dimension(200, 42));
        customerTypeDD.setMinimumSize(new java.awt.Dimension(200, 42));
        customerTypeDD.setPreferredSize(new java.awt.Dimension(200, 42));

        accountStatusDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any", "Active", "Suspended" }));
        accountStatusDD.setMaximumSize(new java.awt.Dimension(200, 42));
        accountStatusDD.setMinimumSize(new java.awt.Dimension(200, 42));
        accountStatusDD.setPreferredSize(new java.awt.Dimension(200, 42));

        inDefaultDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any", "True", "False" }));
        inDefaultDD.setMaximumSize(new java.awt.Dimension(200, 42));
        inDefaultDD.setMinimumSize(new java.awt.Dimension(200, 42));
        inDefaultDD.setPreferredSize(new java.awt.Dimension(200, 42));

        javax.swing.GroupLayout managerjPanelLayout = new javax.swing.GroupLayout(managerjPanel);
        managerjPanel.setLayout(managerjPanelLayout);
        managerjPanelLayout.setHorizontalGroup(
            managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(managerjPanelLayout.createSequentialGroup()
                .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(managerjPanelLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(citySjLabel)
                            .addComponent(phoneSjLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cityField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managerjPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managerjPanelLayout.createSequentialGroup()
                                .addComponent(streetNameSjLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(streetNameField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managerjPanelLayout.createSequentialGroup()
                                .addComponent(postcodeSjLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(postCodeField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20)
                .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerTypeSjLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(accountStatusjLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(inDefaultSjLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(registrationDateSjLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(customerTypeDD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(accountStatusDD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inDefaultDD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customerRegDateField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(91, 91, 91))
        );
        managerjPanelLayout.setVerticalGroup(
            managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managerjPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(managerjPanelLayout.createSequentialGroup()
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(customerTypeSjLabel)
                            .addComponent(customerTypeDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(streetNameField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managerjPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(streetNameSjLabel)
                        .addGap(17, 17, 17)))
                .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(managerjPanelLayout.createSequentialGroup()
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(postCodeField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(postcodeSjLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cityField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(citySjLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(phoneField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneSjLabel)))
                    .addGroup(managerjPanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(accountStatusDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(accountStatusjLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inDefaultSjLabel)
                            .addComponent(inDefaultDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registrationDateSjLabel)
                            .addComponent(customerRegDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        javax.swing.GroupLayout searchCustomerjPanelLayout = new javax.swing.GroupLayout(searchCustomerjPanel);
        searchCustomerjPanel.setLayout(searchCustomerjPanelLayout);
        searchCustomerjPanelLayout.setHorizontalGroup(
            searchCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(receptionistjPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchCustomerjPanelLayout.createSequentialGroup()
                .addComponent(managerjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchCustomerjPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelCustomerFJobjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchCustomerFJobjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
        );
        searchCustomerjPanelLayout.setVerticalGroup(
            searchCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchCustomerjPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(receptionistjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(managerjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(searchCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchCustomerFJobjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelCustomerFJobjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout searchCustomerPageLayout = new javax.swing.GroupLayout(searchCustomerPage);
        searchCustomerPage.setLayout(searchCustomerPageLayout);
        searchCustomerPageLayout.setHorizontalGroup(
            searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(searchCustomerPageLayout.createSequentialGroup()
                    .addComponent(searchCustomerjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        searchCustomerPageLayout.setVerticalGroup(
            searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
            .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(searchCustomerPageLayout.createSequentialGroup()
                    .addComponent(searchCustomerjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        cardPanel1.add(searchCustomerPage, "searchCustomer");

        acceptJobPage.setBackground(new java.awt.Color(61, 96, 146));
        acceptJobPage.setMaximumSize(new java.awt.Dimension(900, 640));
        acceptJobPage.setMinimumSize(new java.awt.Dimension(900, 640));
        acceptJobPage.setPreferredSize(new java.awt.Dimension(900, 640));
        acceptJobPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                acceptJobPageComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                acceptJobPageComponentShown(evt);
            }
        });

        jobTotalField.setEditable(false);
        jobTotalField.setText("0");
        jobTotalField.setMaximumSize(new java.awt.Dimension(72, 42));
        jobTotalField.setMinimumSize(new java.awt.Dimension(72, 42));
        jobTotalField.setPreferredSize(new java.awt.Dimension(72, 42));

        addMaterialButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        addMaterialButton.setText("Add");
        addMaterialButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMaterialButtonActionPerformed(evt);
            }
        });

        addJobButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        addJobButton.setText("Add");
        addJobButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJobButtonActionPerformed(evt);
            }
        });

        totalLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        totalLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalLabel.setText("Total (excluding surcharge): £");

        stdJobDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select a standard job"}));
        stdJobDD.setMaximumSize(new java.awt.Dimension(250, 42));
        stdJobDD.setMinimumSize(new java.awt.Dimension(250, 42));
        stdJobDD.setPreferredSize(new java.awt.Dimension(250, 42));
        stdJobDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stdJobDDActionPerformed(evt);
            }
        });

        selectPriority.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Priority", "Normal", "Urgent", "Stipulated"}));
        selectPriority.setMaximumSize(new java.awt.Dimension(250, 42));
        selectPriority.setMinimumSize(new java.awt.Dimension(250, 42));
        selectPriority.setPreferredSize(new java.awt.Dimension(250, 42));
        selectPriority.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectPriorityActionPerformed(evt);
            }
        });

        searchCustomerButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        searchCustomerButton.setText("Search Customer");
        searchCustomerButton.setMaximumSize(new java.awt.Dimension(230, 37));
        searchCustomerButton.setMinimumSize(new java.awt.Dimension(230, 37));
        searchCustomerButton.setPreferredSize(new java.awt.Dimension(230, 37));
        searchCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCustomerButtonActionPerformed(evt);
            }
        });

        createCustomerButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        createCustomerButton.setText("Create Customer");
        createCustomerButton.setMaximumSize(new java.awt.Dimension(230, 37));
        createCustomerButton.setMinimumSize(new java.awt.Dimension(230, 37));
        createCustomerButton.setPreferredSize(new java.awt.Dimension(230, 37));
        createCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createCustomerButtonActionPerformed(evt);
            }
        });

        specialInstructionsLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        specialInstructionsLabel.setForeground(new java.awt.Color(255, 255, 255));
        specialInstructionsLabel.setText("Special Instructions:");

        materialList.setMaximumSize(new java.awt.Dimension(85, 507));
        materialList.setMinimumSize(new java.awt.Dimension(85, 507));
        materialList.setPreferredSize(new java.awt.Dimension(85, 507));
        materialList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                materialListValueChanged(evt);
            }
        });
        materialsjScrollPane.setViewportView(materialList);

        customerInfoField.setEditable(false);
        customerInfoField.setMaximumSize(new java.awt.Dimension(308, 42));
        customerInfoField.setMinimumSize(new java.awt.Dimension(308, 42));
        customerInfoField.setPreferredSize(new java.awt.Dimension(308, 42));

        materialSubmittedLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        materialSubmittedLabel.setForeground(new java.awt.Color(255, 255, 255));
        materialSubmittedLabel.setText("Materials Submitted:");

        submitButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        materialsjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialsjTextFieldActionPerformed(evt);
            }
        });

        standardJobList.setMaximumSize(new java.awt.Dimension(85, 507));
        standardJobList.setMinimumSize(new java.awt.Dimension(85, 507));
        standardJobList.setPreferredSize(new java.awt.Dimension(85, 507));
        standardJobList.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                standardJobListPropertyChange(evt);
            }
        });
        standardJobList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                standardJobListValueChanged(evt);
            }
        });
        materialsjScrollPane1.setViewportView(standardJobList);

        removeJobButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        removeJobButton.setText("Remove Job");
        removeJobButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeJobButtonActionPerformed(evt);
            }
        });

        removeMaterialButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        removeMaterialButton.setText("Remove Item");
        removeMaterialButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMaterialButtonActionPerformed(evt);
            }
        });

        stipulatedFields.setBackground(new java.awt.Color(61, 96, 146));
        stipulatedFields.setMaximumSize(new java.awt.Dimension(250, 42));
        stipulatedFields.setMinimumSize(new java.awt.Dimension(250, 42));
        stipulatedFields.setPreferredSize(new java.awt.Dimension(250, 42));
        stipulatedFields.setVisible(false);

        surchargeLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        surchargeLabel.setForeground(new java.awt.Color(255, 255, 255));
        surchargeLabel.setText("Surcharge:");
        surchargeLabel.setName(""); // NOI18N

        surchargejTextField.setEditable(false);
        surchargejTextField.setMaximumSize(new java.awt.Dimension(72, 42));
        surchargejTextField.setMinimumSize(new java.awt.Dimension(72, 42));
        surchargejTextField.setPreferredSize(new java.awt.Dimension(72, 42));

        completionTimeDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select time"}));
        completionTimeDD.setMaximumSize(new java.awt.Dimension(250, 42));
        completionTimeDD.setMinimumSize(new java.awt.Dimension(250, 42));
        completionTimeDD.setPreferredSize(new java.awt.Dimension(250, 42));
        completionTimeDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completionTimeDDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout stipulatedFieldsLayout = new javax.swing.GroupLayout(stipulatedFields);
        stipulatedFields.setLayout(stipulatedFieldsLayout);
        stipulatedFieldsLayout.setHorizontalGroup(
            stipulatedFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stipulatedFieldsLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(completionTimeDD, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(surchargeLabel)
                .addGap(18, 18, 18)
                .addComponent(surchargejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        stipulatedFieldsLayout.setVerticalGroup(
            stipulatedFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stipulatedFieldsLayout.createSequentialGroup()
                .addGroup(stipulatedFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(surchargeLabel)
                    .addComponent(surchargejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(completionTimeDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout acceptJobPageLayout = new javax.swing.GroupLayout(acceptJobPage);
        acceptJobPage.setLayout(acceptJobPageLayout);
        acceptJobPageLayout.setHorizontalGroup(
            acceptJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(acceptJobPageLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(acceptJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(acceptJobPageLayout.createSequentialGroup()
                        .addGroup(acceptJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stdJobDD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acceptJobPageLayout.createSequentialGroup()
                                .addComponent(removeJobButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addJobButton))
                            .addComponent(selectPriority, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(materialSubmittedLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acceptJobPageLayout.createSequentialGroup()
                                .addComponent(removeMaterialButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addMaterialButton))
                            .addComponent(specialInstructionsLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(acceptJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(materialsjScrollPane)
                            .addComponent(materialsjTextField)
                            .addComponent(specialInstructionjTextField)
                            .addComponent(materialsjScrollPane1)
                            .addComponent(stipulatedFields, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)))
                    .addGroup(acceptJobPageLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(acceptJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(acceptJobPageLayout.createSequentialGroup()
                                .addComponent(searchCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(createCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(customerInfoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(acceptJobPageLayout.createSequentialGroup()
                                .addComponent(totalLabel)
                                .addGap(18, 18, 18)
                                .addComponent(jobTotalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(41, 41, 41))
        );
        acceptJobPageLayout.setVerticalGroup(
            acceptJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(acceptJobPageLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(acceptJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerInfoField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createCustomerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchCustomerButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(acceptJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(materialSubmittedLabel)
                    .addComponent(materialsjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(acceptJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(materialsjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(acceptJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addMaterialButton)
                        .addComponent(removeMaterialButton)))
                .addGap(11, 11, 11)
                .addGroup(acceptJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(acceptJobPageLayout.createSequentialGroup()
                        .addComponent(stdJobDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(acceptJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addJobButton)
                            .addComponent(removeJobButton)))
                    .addComponent(materialsjScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(acceptJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(selectPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stipulatedFields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(acceptJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(acceptJobPageLayout.createSequentialGroup()
                        .addComponent(specialInstructionjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(acceptJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jobTotalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(specialInstructionsLabel))
                .addGap(84, 84, 84))
        );

        customerInfoField.setEditable(false);

        cardPanel1.add(acceptJobPage, "acceptJob");

        searchInvoicePage.setMaximumSize(new java.awt.Dimension(900, 700));
        searchInvoicePage.setMinimumSize(new java.awt.Dimension(900, 700));

        searchInvoicejPanel.setBackground(new java.awt.Color(61, 96, 146));
        searchInvoicejPanel.setMaximumSize(new java.awt.Dimension(900, 700));
        searchInvoicejPanel.setPreferredSize(new java.awt.Dimension(900, 700));

        invoicejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice No.", "Job Job No.", "Total Payable", "Date Issued", "Invoice Status", "Invoice Location"
            }
        ));
        jScrollPane2.setViewportView(invoicejTable);

        selectSelectedInvoicejButton.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        selectSelectedInvoicejButton.setText("Select");
        selectSelectedInvoicejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectSelectedInvoicejButtonActionPerformed(evt);
            }
        });

        cancelInvoiceSeletionjButton.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        cancelInvoiceSeletionjButton.setText("Cancel");
        cancelInvoiceSeletionjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelInvoiceSeletionjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchInvoicejPanelLayout = new javax.swing.GroupLayout(searchInvoicejPanel);
        searchInvoicejPanel.setLayout(searchInvoicejPanelLayout);
        searchInvoicejPanelLayout.setHorizontalGroup(
            searchInvoicejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchInvoicejPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelInvoiceSeletionjButton)
                .addGap(18, 18, 18)
                .addComponent(selectSelectedInvoicejButton)
                .addGap(69, 69, 69))
            .addGroup(searchInvoicejPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        searchInvoicejPanelLayout.setVerticalGroup(
            searchInvoicejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchInvoicejPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(searchInvoicejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelInvoiceSeletionjButton)
                    .addComponent(selectSelectedInvoicejButton))
                .addContainerGap(172, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout searchInvoicePageLayout = new javax.swing.GroupLayout(searchInvoicePage);
        searchInvoicePage.setLayout(searchInvoicePageLayout);
        searchInvoicePageLayout.setHorizontalGroup(
            searchInvoicePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(searchInvoicePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(searchInvoicePageLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(searchInvoicejPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        searchInvoicePageLayout.setVerticalGroup(
            searchInvoicePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(searchInvoicePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(searchInvoicePageLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(searchInvoicejPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        cardPanel1.add(searchInvoicePage, "searchInvoice");

        createStandardJobPage.setBackground(new java.awt.Color(61, 96, 146));
        createStandardJobPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                createStandardJobPageComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                createStandardJobPageComponentShown(evt);
            }
        });

        descriptionField.setMaximumSize(new java.awt.Dimension(250, 42));
        descriptionField.setMinimumSize(new java.awt.Dimension(250, 42));
        descriptionField.setPreferredSize(new java.awt.Dimension(250, 42));
        descriptionField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descriptionFieldActionPerformed(evt);
            }
        });

        codeField.setMaximumSize(new java.awt.Dimension(250, 42));
        codeField.setMinimumSize(new java.awt.Dimension(250, 42));
        codeField.setPreferredSize(new java.awt.Dimension(250, 42));
        codeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codeFieldActionPerformed(evt);
            }
        });

        selectATaskBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        selectATaskBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a Task" }));

        addButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        removeTaskButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        removeTaskButton.setText("Remove task");
        removeTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTaskButtonActionPerformed(evt);
            }
        });

        stdJobTotalField.setEditable(false);
        stdJobTotalField.setText("0");
        stdJobTotalField.setMaximumSize(new java.awt.Dimension(100, 42));
        stdJobTotalField.setMinimumSize(new java.awt.Dimension(100, 42));
        stdJobTotalField.setPreferredSize(new java.awt.Dimension(100, 42));

        createButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        createButton.setText("Create");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cancelButton.setText("Cancel");

        stdJobLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        stdJobLabel.setForeground(new java.awt.Color(255, 255, 255));
        stdJobLabel.setText("New Standard Job");

        codeLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        codeLabel.setForeground(new java.awt.Color(255, 255, 255));
        codeLabel.setText("Code:");

        descLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        descLabel.setForeground(new java.awt.Color(255, 255, 255));
        descLabel.setText("Description:");

        totalLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        totalLabel1.setForeground(new java.awt.Color(255, 255, 255));
        totalLabel1.setText("Total: £");

        standardJobList1.setMaximumSize(new java.awt.Dimension(85, 507));
        standardJobList1.setMinimumSize(new java.awt.Dimension(85, 507));
        standardJobList1.setPreferredSize(new java.awt.Dimension(85, 507));
        standardJobList1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                standardJobList1PropertyChange(evt);
            }
        });
        standardJobList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                standardJobList1ValueChanged(evt);
            }
        });
        materialsjScrollPane2.setViewportView(standardJobList1);

        javax.swing.GroupLayout createStandardJobPageLayout = new javax.swing.GroupLayout(createStandardJobPage);
        createStandardJobPage.setLayout(createStandardJobPageLayout);
        createStandardJobPageLayout.setHorizontalGroup(
            createStandardJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createStandardJobPageLayout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addComponent(stdJobLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createStandardJobPageLayout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addGroup(createStandardJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createStandardJobPageLayout.createSequentialGroup()
                        .addGroup(createStandardJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addButton)
                            .addComponent(selectATaskBox, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(createStandardJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(createStandardJobPageLayout.createSequentialGroup()
                                .addComponent(removeTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(totalLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(stdJobTotalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(materialsjScrollPane2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createStandardJobPageLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addGap(18, 18, 18)
                        .addComponent(createButton))
                    .addGroup(createStandardJobPageLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(createStandardJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(codeLabel)
                            .addComponent(descLabel))
                        .addGap(18, 18, 18)
                        .addGroup(createStandardJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(createStandardJobPageLayout.createSequentialGroup()
                                .addComponent(codeField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(descriptionField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(189, 189, 189))
        );
        createStandardJobPageLayout.setVerticalGroup(
            createStandardJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createStandardJobPageLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(stdJobLabel)
                .addGap(18, 18, 18)
                .addGroup(createStandardJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeLabel))
                .addGap(25, 25, 25)
                .addGroup(createStandardJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descLabel))
                .addGap(25, 25, 25)
                .addGroup(createStandardJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(createStandardJobPageLayout.createSequentialGroup()
                        .addComponent(selectATaskBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addButton))
                    .addComponent(materialsjScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(createStandardJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createStandardJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(stdJobTotalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(totalLabel1))
                    .addComponent(removeTaskButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(createStandardJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createButton)
                    .addComponent(cancelButton))
                .addGap(240, 240, 240))
        );

        cardPanel1.add(createStandardJobPage, "createStandardJob");

        customerResultsPage.setBackground(new java.awt.Color(61, 96, 146));
        customerResultsPage.setMaximumSize(new java.awt.Dimension(900, 640));
        customerResultsPage.setMinimumSize(new java.awt.Dimension(900, 640));
        customerResultsPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                customerResultsPageComponentHidden(evt);
            }
        });

        jScrollPane4.setPreferredSize(new java.awt.Dimension(750, 400));

        customerResultsTable.setAutoCreateRowSorter(true);
        customerResultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Account number", "Account holder name", "Contact name", "Customer type", "Account status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customerResultsTable.setMaximumSize(new java.awt.Dimension(750, 500));
        customerResultsTable.setMinimumSize(new java.awt.Dimension(750, 500));
        customerResultsTable.setPreferredSize(new java.awt.Dimension(750, 500));
        customerResultsTable.getTableHeader().setReorderingAllowed(false);
        customerResultsTable.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                customerResultsTableComponentMoved(evt);
            }
        });
        jScrollPane4.setViewportView(customerResultsTable);
        if (customerResultsTable.getColumnModel().getColumnCount() > 0) {
            customerResultsTable.getColumnModel().getColumn(0).setResizable(false);
            customerResultsTable.getColumnModel().getColumn(1).setResizable(false);
            customerResultsTable.getColumnModel().getColumn(2).setResizable(false);
            customerResultsTable.getColumnModel().getColumn(3).setResizable(false);
            customerResultsTable.getColumnModel().getColumn(4).setResizable(false);
        }

        searchAgainButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        searchAgainButton1.setText("Search Again");
        searchAgainButton1.setMaximumSize(new java.awt.Dimension(175, 45));
        searchAgainButton1.setMinimumSize(new java.awt.Dimension(175, 45));
        searchAgainButton1.setPreferredSize(new java.awt.Dimension(175, 45));
        searchAgainButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAgainButton1ActionPerformed(evt);
            }
        });

        selectCustomerButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        selectCustomerButton.setText("Select Customer");
        selectCustomerButton.setMaximumSize(new java.awt.Dimension(210, 45));
        selectCustomerButton.setMinimumSize(new java.awt.Dimension(210, 45));
        selectCustomerButton.setPreferredSize(new java.awt.Dimension(210, 45));
        selectCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCustomerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout customerResultsPageLayout = new javax.swing.GroupLayout(customerResultsPage);
        customerResultsPage.setLayout(customerResultsPageLayout);
        customerResultsPageLayout.setHorizontalGroup(
            customerResultsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerResultsPageLayout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(customerResultsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchAgainButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );
        customerResultsPageLayout.setVerticalGroup(
            customerResultsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerResultsPageLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(selectCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(searchAgainButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        cardPanel1.add(customerResultsPage, "customerResults");

        createReportPage.setBackground(new java.awt.Color(61, 96, 146));
        createReportPage.setMaximumSize(new java.awt.Dimension(900, 640));
        createReportPage.setMinimumSize(new java.awt.Dimension(900, 640));
        createReportPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                createReportPageComponentHidden(evt);
            }
        });

        searchPanel.setBackground(new java.awt.Color(61, 96, 146));
        searchPanel.setVisible(false);

        infoField.setEditable(false);
        infoField.setMaximumSize(new java.awt.Dimension(308, 42));
        infoField.setMinimumSize(new java.awt.Dimension(308, 42));
        infoField.setPreferredSize(new java.awt.Dimension(308, 42));

        searchButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        searchButton.setText("Search");
        searchButton.setMaximumSize(new java.awt.Dimension(230, 37));
        searchButton.setMinimumSize(new java.awt.Dimension(230, 37));
        searchButton.setPreferredSize(new java.awt.Dimension(230, 37));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(infoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        customerInfoField.setEditable(false);

        newUserLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        newUserLabel1.setForeground(new java.awt.Color(255, 255, 255));
        newUserLabel1.setText("New Report");

        reportTypeDD.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        reportTypeDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a report type.", "Individual performance", "Summary performance", "Customer" }));
        reportTypeDD.setMaximumSize(new java.awt.Dimension(250, 42));
        reportTypeDD.setMinimumSize(new java.awt.Dimension(250, 42));
        reportTypeDD.setPreferredSize(new java.awt.Dimension(250, 42));
        reportTypeDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportTypeDDActionPerformed(evt);
            }
        });

        reportTypeLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        reportTypeLabel.setForeground(new java.awt.Color(255, 255, 255));
        reportTypeLabel.setText("Report type:");

        reportPeriodLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        reportPeriodLabel.setForeground(new java.awt.Color(255, 255, 255));
        reportPeriodLabel.setText("Report period");

        periodFromLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        periodFromLabel.setForeground(new java.awt.Color(255, 255, 255));
        periodFromLabel.setText("From:");

        createReportButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        createReportButton.setText("Create Report");
        createReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createReportButtonActionPerformed(evt);
            }
        });

        reportStartPeriod.setMinimumSize(new java.awt.Dimension(250, 42));
        reportStartPeriod.setPreferredSize(new java.awt.Dimension(250, 42));

        reportEndPeriod.setMinimumSize(new java.awt.Dimension(250, 42));
        reportEndPeriod.setPreferredSize(new java.awt.Dimension(250, 42));

        periodToLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        periodToLabel.setForeground(new java.awt.Color(255, 255, 255));
        periodToLabel.setText("To:");

        javax.swing.GroupLayout createReportPageLayout = new javax.swing.GroupLayout(createReportPage);
        createReportPage.setLayout(createReportPageLayout);
        createReportPageLayout.setHorizontalGroup(
            createReportPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createReportPageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createReportButton)
                .addGap(88, 88, 88))
            .addGroup(createReportPageLayout.createSequentialGroup()
                .addGroup(createReportPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(createReportPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(createReportPageLayout.createSequentialGroup()
                            .addGap(343, 343, 343)
                            .addGroup(createReportPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(newUserLabel1)
                                .addComponent(reportPeriodLabel)))
                        .addGroup(createReportPageLayout.createSequentialGroup()
                            .addGap(278, 278, 278)
                            .addComponent(reportTypeLabel)
                            .addGap(18, 18, 18)
                            .addComponent(reportTypeDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(createReportPageLayout.createSequentialGroup()
                            .addGap(100, 100, 100)
                            .addComponent(periodFromLabel)
                            .addGap(18, 18, 18)
                            .addComponent(reportStartPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(periodToLabel)
                            .addGap(18, 18, 18)
                            .addComponent(reportEndPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        createReportPageLayout.setVerticalGroup(
            createReportPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createReportPageLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(newUserLabel1)
                .addGap(34, 34, 34)
                .addGroup(createReportPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reportTypeLabel)
                    .addComponent(reportTypeDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(reportPeriodLabel)
                .addGap(25, 25, 25)
                .addGroup(createReportPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(periodFromLabel)
                    .addComponent(reportStartPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(periodToLabel)
                    .addComponent(reportEndPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(createReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );

        cardPanel1.add(createReportPage, "createReport");

        summaryReportPage.setMaximumSize(new java.awt.Dimension(900, 640));
        summaryReportPage.setMinimumSize(new java.awt.Dimension(900, 640));
        summaryReportPage.setPreferredSize(new java.awt.Dimension(900, 640));
        summaryReportPage.setRequestFocusEnabled(false);
        summaryReportPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                summaryReportPageComponentHidden(evt);
            }
        });

        summaryReportPageData.setBackground(new java.awt.Color(61, 96, 146));
        summaryReportPageData.setMaximumSize(new java.awt.Dimension(900, 900));
        summaryReportPageData.setMinimumSize(new java.awt.Dimension(900, 900));
        summaryReportPageData.setPreferredSize(new java.awt.Dimension(900, 900));
        summaryReportPageData.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                summaryReportPageDataComponentHidden(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Copy Room", "Development", "Finishing", "Packaging"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Copy Room", "Development", "Finishing", "Packaging"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(4).setResizable(false);
        }

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Copy Room", "Development", "Finishing", "Packaging"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(4).setResizable(false);
        }

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Copy Room", "Development", "Finishing", "Packaging"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(4).setResizable(false);
        }

        shift1Label.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        shift1Label.setForeground(new java.awt.Color(255, 255, 255));
        shift1Label.setText("Day shift 1 (5:00 am – 2:30 pm)");

        shift2Label.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        shift2Label.setForeground(new java.awt.Color(255, 255, 255));
        shift2Label.setText("Day shift 2 (2:30 pm – 10 pm)");

        shift3Label.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        shift3Label.setForeground(new java.awt.Color(255, 255, 255));
        shift3Label.setText("Night shift 1 (10 pm – 5 am)");

        periodLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        periodLabel.setForeground(new java.awt.Color(255, 255, 255));
        periodLabel.setText("For period");

        summaryReportLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        summaryReportLabel.setForeground(new java.awt.Color(255, 255, 255));

        reportBackButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        reportBackButton.setText("Back");
        reportBackButton.setMaximumSize(new java.awt.Dimension(175, 45));
        reportBackButton.setMinimumSize(new java.awt.Dimension(175, 45));
        reportBackButton.setPreferredSize(new java.awt.Dimension(175, 45));
        reportBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportBackButtonActionPerformed(evt);
            }
        });

        printButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        printButton1.setText("Print");
        printButton1.setMaximumSize(new java.awt.Dimension(175, 45));
        printButton1.setMinimumSize(new java.awt.Dimension(175, 45));
        printButton1.setPreferredSize(new java.awt.Dimension(175, 45));
        printButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout summaryReportPageDataLayout = new javax.swing.GroupLayout(summaryReportPageData);
        summaryReportPageData.setLayout(summaryReportPageDataLayout);
        summaryReportPageDataLayout.setHorizontalGroup(
            summaryReportPageDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(summaryReportPageDataLayout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addGroup(summaryReportPageDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shift1Label)
                    .addComponent(shift2Label)
                    .addComponent(shift3Label)
                    .addComponent(periodLabel)
                    .addComponent(summaryReportLabel))
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, summaryReportPageDataLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(printButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reportBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );
        summaryReportPageDataLayout.setVerticalGroup(
            summaryReportPageDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, summaryReportPageDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(summaryReportPageDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reportBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(summaryReportLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(summaryReportPageDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(summaryReportPageDataLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, summaryReportPageDataLayout.createSequentialGroup()
                        .addComponent(shift1Label)
                        .addGap(153, 153, 153)
                        .addComponent(shift2Label)
                        .addGap(153, 153, 153)
                        .addComponent(shift3Label)
                        .addGap(153, 153, 153)
                        .addComponent(periodLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        summaryReportPage.setViewportView(summaryReportPageData);

        cardPanel1.add(summaryReportPage, "summaryReport");

        individualReportPage.setBackground(new java.awt.Color(61, 96, 146));
        individualReportPage.setMaximumSize(new java.awt.Dimension(900, 640));
        individualReportPage.setMinimumSize(new java.awt.Dimension(900, 640));
        individualReportPage.setPreferredSize(new java.awt.Dimension(900, 640));
        individualReportPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                individualReportPageComponentHidden(evt);
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Code", "Task IDs", "Department", "Date", "Start time", "Time taken", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTable5);
        if (jTable5.getColumnModel().getColumnCount() > 0) {
            jTable5.getColumnModel().getColumn(0).setResizable(false);
            jTable5.getColumnModel().getColumn(1).setResizable(false);
            jTable5.getColumnModel().getColumn(2).setResizable(false);
            jTable5.getColumnModel().getColumn(3).setResizable(false);
            jTable5.getColumnModel().getColumn(4).setResizable(false);
            jTable5.getColumnModel().getColumn(5).setResizable(false);
            jTable5.getColumnModel().getColumn(5).setHeaderValue("Start time");
            jTable5.getColumnModel().getColumn(6).setResizable(false);
            jTable5.getColumnModel().getColumn(6).setHeaderValue("Time taken");
            jTable5.getColumnModel().getColumn(7).setResizable(false);
            jTable5.getColumnModel().getColumn(7).setHeaderValue("Total");
        }

        iPLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        iPLabel.setForeground(new java.awt.Color(255, 255, 255));

        reportBackButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        reportBackButton1.setText("Back");
        reportBackButton1.setMaximumSize(new java.awt.Dimension(175, 45));
        reportBackButton1.setMinimumSize(new java.awt.Dimension(175, 45));
        reportBackButton1.setPreferredSize(new java.awt.Dimension(175, 45));
        reportBackButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportBackButton1ActionPerformed(evt);
            }
        });

        printButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        printButton2.setText("Print");
        printButton2.setMaximumSize(new java.awt.Dimension(175, 45));
        printButton2.setMinimumSize(new java.awt.Dimension(175, 45));
        printButton2.setPreferredSize(new java.awt.Dimension(175, 45));
        printButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout individualReportPageLayout = new javax.swing.GroupLayout(individualReportPage);
        individualReportPage.setLayout(individualReportPageLayout);
        individualReportPageLayout.setHorizontalGroup(
            individualReportPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(individualReportPageLayout.createSequentialGroup()
                .addContainerGap(450, Short.MAX_VALUE)
                .addComponent(iPLabel)
                .addContainerGap(450, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, individualReportPageLayout.createSequentialGroup()
                .addGroup(individualReportPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(individualReportPageLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(individualReportPageLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(printButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reportBackButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(99, 99, 99))
        );
        individualReportPageLayout.setVerticalGroup(
            individualReportPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, individualReportPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(individualReportPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reportBackButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iPLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );

        cardPanel1.add(individualReportPage, "individualReport");

        customerReportPage.setBackground(new java.awt.Color(61, 96, 146));
        customerReportPage.setMaximumSize(new java.awt.Dimension(900, 640));
        customerReportPage.setMinimumSize(new java.awt.Dimension(900, 640));
        customerReportPage.setPreferredSize(new java.awt.Dimension(900, 640));
        customerReportPage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                customerReportPageComponentHidden(evt);
            }
        });

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Job number", "Date received", "Date completed", "Total payable", "Invoice status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(jTable6);
        if (jTable6.getColumnModel().getColumnCount() > 0) {
            jTable6.getColumnModel().getColumn(0).setResizable(false);
            jTable6.getColumnModel().getColumn(1).setResizable(false);
            jTable6.getColumnModel().getColumn(2).setResizable(false);
            jTable6.getColumnModel().getColumn(3).setResizable(false);
            jTable6.getColumnModel().getColumn(4).setResizable(false);
        }

        cPLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        cPLabel.setForeground(new java.awt.Color(255, 255, 255));

        reportBackButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        reportBackButton2.setText("Back");
        reportBackButton2.setMaximumSize(new java.awt.Dimension(175, 45));
        reportBackButton2.setMinimumSize(new java.awt.Dimension(175, 45));
        reportBackButton2.setPreferredSize(new java.awt.Dimension(175, 45));
        reportBackButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportBackButton2ActionPerformed(evt);
            }
        });

        printButton3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        printButton3.setText("Print");
        printButton3.setMaximumSize(new java.awt.Dimension(175, 45));
        printButton3.setMinimumSize(new java.awt.Dimension(175, 45));
        printButton3.setPreferredSize(new java.awt.Dimension(175, 45));
        printButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout customerReportPageLayout = new javax.swing.GroupLayout(customerReportPage);
        customerReportPage.setLayout(customerReportPageLayout);
        customerReportPageLayout.setHorizontalGroup(
            customerReportPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerReportPageLayout.createSequentialGroup()
                .addContainerGap(450, Short.MAX_VALUE)
                .addComponent(cPLabel)
                .addContainerGap(450, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerReportPageLayout.createSequentialGroup()
                .addGroup(customerReportPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(customerReportPageLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(customerReportPageLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(printButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reportBackButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(99, 99, 99))
        );
        customerReportPageLayout.setVerticalGroup(
            customerReportPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerReportPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customerReportPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reportBackButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cPLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );

        cardPanel1.add(customerReportPage, "customerReport");

        searchInvoice.setMaximumSize(new java.awt.Dimension(900, 700));
        searchInvoice.setMinimumSize(new java.awt.Dimension(900, 700));

        searchInvoicejPanel1.setBackground(new java.awt.Color(61, 96, 146));
        searchInvoicejPanel1.setMaximumSize(new java.awt.Dimension(900, 700));
        searchInvoicejPanel1.setPreferredSize(new java.awt.Dimension(900, 700));

        invoicejTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice No.", "Job Job No.", "Total Payable", "Date Issued", "Invoice Status", "Invoice Location"
            }
        ));
        jScrollPane10.setViewportView(invoicejTable1);

        selectSelectedInvoicejButton1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        selectSelectedInvoicejButton1.setText("Select");
        selectSelectedInvoicejButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectSelectedInvoicejButton1ActionPerformed(evt);
            }
        });

        cancelInvoiceSeletionjButton1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        cancelInvoiceSeletionjButton1.setText("Cancel");
        cancelInvoiceSeletionjButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelInvoiceSeletionjButton1ActionPerformed(evt);
            }
        });

        searchInvoiceByInvoiceNojTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        searchInvoiceByInvoiceNojTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        searchInvoiceByInvoiceNojTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        searchInvoiceByInvoiceNojTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInvoiceByInvoiceNojTextFieldActionPerformed(evt);
            }
        });
        searchInvoiceByInvoiceNojTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchInvoiceByInvoiceNojTextFieldKeyReleased(evt);
            }
        });

        searchInvoiceByJobNumberjTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        searchInvoiceByJobNumberjTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        searchInvoiceByJobNumberjTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        searchInvoiceByJobNumberjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInvoiceByJobNumberjTextFieldActionPerformed(evt);
            }
        });
        searchInvoiceByJobNumberjTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchInvoiceByJobNumberjTextFieldKeyReleased(evt);
            }
        });

        searchInvoiceByInvoiceNojLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        searchInvoiceByInvoiceNojLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchInvoiceByInvoiceNojLabel.setText("Invoice Number:");
        searchInvoiceByInvoiceNojLabel.setMaximumSize(new java.awt.Dimension(124, 29));
        searchInvoiceByInvoiceNojLabel.setMinimumSize(new java.awt.Dimension(124, 29));
        searchInvoiceByInvoiceNojLabel.setPreferredSize(new java.awt.Dimension(124, 29));

        searchInvoiceByJobNumberjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        searchInvoiceByJobNumberjLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchInvoiceByJobNumberjLabel.setText("Job Number:");

        javax.swing.GroupLayout searchInvoicejPanel1Layout = new javax.swing.GroupLayout(searchInvoicejPanel1);
        searchInvoicejPanel1.setLayout(searchInvoicejPanel1Layout);
        searchInvoicejPanel1Layout.setHorizontalGroup(
            searchInvoicejPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchInvoicejPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cancelInvoiceSeletionjButton1)
                .addGap(18, 18, 18)
                .addComponent(selectSelectedInvoicejButton1)
                .addGap(69, 69, 69))
            .addGroup(searchInvoicejPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(searchInvoicejPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(searchInvoicejPanel1Layout.createSequentialGroup()
                        .addComponent(searchInvoiceByInvoiceNojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchInvoiceByInvoiceNojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchInvoiceByJobNumberjLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchInvoiceByJobNumberjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 50, Short.MAX_VALUE))
        );
        searchInvoicejPanel1Layout.setVerticalGroup(
            searchInvoicejPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchInvoicejPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchInvoicejPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchInvoiceByInvoiceNojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchInvoiceByInvoiceNojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchInvoiceByJobNumberjLabel)
                    .addComponent(searchInvoiceByJobNumberjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchInvoicejPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelInvoiceSeletionjButton1)
                    .addComponent(selectSelectedInvoicejButton1))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout searchInvoiceLayout = new javax.swing.GroupLayout(searchInvoice);
        searchInvoice.setLayout(searchInvoiceLayout);
        searchInvoiceLayout.setHorizontalGroup(
            searchInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(searchInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(searchInvoiceLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(searchInvoicejPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        searchInvoiceLayout.setVerticalGroup(
            searchInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(searchInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(searchInvoiceLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(searchInvoicejPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        cardPanel1.add(searchInvoice, "searchInvoicePage");

        AutoBackupConfigjPanel.setBackground(new java.awt.Color(61, 96, 146));
        AutoBackupConfigjPanel.setMaximumSize(new java.awt.Dimension(900, 700));
        AutoBackupConfigjPanel.setMinimumSize(new java.awt.Dimension(900, 700));

        currentAutoBackupModeDatajTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        currentAutoBackupModeDatajTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        currentAutoBackupModeDatajTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        currentAutoBackupModeDatajTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentAutoBackupModeDatajTextFieldActionPerformed(evt);
            }
        });

        backupLocationjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        backupLocationjLabel.setForeground(new java.awt.Color(255, 255, 255));
        backupLocationjLabel.setText("Backup Location:");

        backupFrequencyjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        backupFrequencyjLabel.setForeground(new java.awt.Color(255, 255, 255));
        backupFrequencyjLabel.setText("Backup Frequency:");

        currentConfigjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        currentConfigjLabel.setForeground(new java.awt.Color(255, 255, 255));
        currentConfigjLabel.setText("Current configeration:");

        backupModejComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manual", "Reminder", "Auto" }));
        backupModejComboBox.setMaximumSize(new java.awt.Dimension(250, 42));
        backupModejComboBox.setMinimumSize(new java.awt.Dimension(250, 42));
        backupModejComboBox.setPreferredSize(new java.awt.Dimension(250, 42));

        backupFrequencyjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Weekly", "Monthly" }));
        backupFrequencyjComboBox.setMaximumSize(new java.awt.Dimension(250, 42));
        backupFrequencyjComboBox.setMinimumSize(new java.awt.Dimension(250, 42));
        backupFrequencyjComboBox.setPreferredSize(new java.awt.Dimension(250, 42));

        selectAutoBackupLocationjButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectAutoBackupLocationjButton.setText("Select Location");
        selectAutoBackupLocationjButton.setMaximumSize(new java.awt.Dimension(159, 37));
        selectAutoBackupLocationjButton.setMinimumSize(new java.awt.Dimension(159, 37));
        selectAutoBackupLocationjButton.setPreferredSize(new java.awt.Dimension(159, 37));
        selectAutoBackupLocationjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAutoBackupLocationjButtonActionPerformed(evt);
            }
        });

        confirmAutoBackupConfigjButton.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        confirmAutoBackupConfigjButton.setText("Confirm");
        confirmAutoBackupConfigjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmAutoBackupConfigjButtonActionPerformed(evt);
            }
        });

        backupModejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        backupModejLabel.setForeground(new java.awt.Color(255, 255, 255));
        backupModejLabel.setText("Backup Mode:");

        currentBackupModejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        currentBackupModejLabel.setForeground(new java.awt.Color(255, 255, 255));
        currentBackupModejLabel.setText("Backup Mode:");

        currentBackupFrequencyjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        currentBackupFrequencyjLabel.setForeground(new java.awt.Color(255, 255, 255));
        currentBackupFrequencyjLabel.setText("Backup Frequency:");

        currentBackupLocationjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        currentBackupLocationjLabel.setForeground(new java.awt.Color(255, 255, 255));
        currentBackupLocationjLabel.setText("Backup Location:");

        currentAutoBackupFrequencyDatajTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        currentAutoBackupFrequencyDatajTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        currentAutoBackupFrequencyDatajTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        currentAutoBackupFrequencyDatajTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentAutoBackupFrequencyDatajTextFieldActionPerformed(evt);
            }
        });

        autoBackupLocationjTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        autoBackupLocationjTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        autoBackupLocationjTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        autoBackupLocationjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoBackupLocationjTextFieldActionPerformed(evt);
            }
        });

        currentAutoBackupLocationDatajTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        currentAutoBackupLocationDatajTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        currentAutoBackupLocationDatajTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        currentAutoBackupLocationDatajTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentAutoBackupLocationDatajTextFieldActionPerformed(evt);
            }
        });

        changeConfigjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        changeConfigjLabel.setForeground(new java.awt.Color(255, 255, 255));
        changeConfigjLabel.setText("Change configeration:");

        javax.swing.GroupLayout AutoBackupConfigjPanelLayout = new javax.swing.GroupLayout(AutoBackupConfigjPanel);
        AutoBackupConfigjPanel.setLayout(AutoBackupConfigjPanelLayout);
        AutoBackupConfigjPanelLayout.setHorizontalGroup(
            AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AutoBackupConfigjPanelLayout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(currentBackupFrequencyjLabel)
                    .addComponent(currentBackupLocationjLabel)
                    .addComponent(currentBackupModejLabel)
                    .addComponent(backupFrequencyjLabel)
                    .addComponent(backupLocationjLabel)
                    .addComponent(backupModejLabel))
                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AutoBackupConfigjPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(selectAutoBackupLocationjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(AutoBackupConfigjPanelLayout.createSequentialGroup()
                        .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AutoBackupConfigjPanelLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(currentAutoBackupModeDatajTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                                    .addComponent(currentAutoBackupFrequencyDatajTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(currentAutoBackupLocationDatajTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(AutoBackupConfigjPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(backupModejComboBox, 0, 369, Short.MAX_VALUE)
                                    .addComponent(backupFrequencyjComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(autoBackupLocationjTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 90, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AutoBackupConfigjPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AutoBackupConfigjPanelLayout.createSequentialGroup()
                        .addComponent(confirmAutoBackupConfigjButton)
                        .addGap(118, 118, 118))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AutoBackupConfigjPanelLayout.createSequentialGroup()
                        .addComponent(currentConfigjLabel)
                        .addGap(317, 317, 317))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AutoBackupConfigjPanelLayout.createSequentialGroup()
                        .addComponent(changeConfigjLabel)
                        .addGap(317, 317, 317))))
        );
        AutoBackupConfigjPanelLayout.setVerticalGroup(
            AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AutoBackupConfigjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AutoBackupConfigjPanelLayout.createSequentialGroup()
                        .addComponent(currentConfigjLabel)
                        .addGap(30, 30, 30)
                        .addComponent(currentBackupModejLabel))
                    .addComponent(currentAutoBackupModeDatajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentBackupFrequencyjLabel)
                    .addComponent(currentAutoBackupFrequencyDatajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentBackupLocationjLabel)
                    .addComponent(currentAutoBackupLocationDatajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(changeConfigjLabel)
                .addGap(50, 50, 50)
                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backupModejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backupModejLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backupFrequencyjLabel)
                    .addComponent(backupFrequencyjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backupLocationjLabel)
                    .addComponent(autoBackupLocationjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectAutoBackupLocationjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(confirmAutoBackupConfigjButton)
                .addContainerGap())
        );

        TotalLatePayjTextField.setEditable(false);
        TotalLatePayjTextField.setEditable(false);
        TotalLatePayjTextField.setEditable(false);
        TotalLatePayjTextField.setEditable(false);

        cardPanel1.add(AutoBackupConfigjPanel, "AutoBackupConfig");

        SelectDiscountPlan.setBackground(new java.awt.Color(61, 96, 146));
        SelectDiscountPlan.setMaximumSize(new java.awt.Dimension(900, 700));
        SelectDiscountPlan.setMinimumSize(new java.awt.Dimension(900, 700));

        discountPlanTypejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        discountPlanTypejLabel.setForeground(new java.awt.Color(255, 255, 255));
        discountPlanTypejLabel.setText("Discount plan type:");

        discountPlanTypejComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a discount type", "Fixed", "Variable", "Flexible" }));
        discountPlanTypejComboBox.setMaximumSize(new java.awt.Dimension(250, 42));
        discountPlanTypejComboBox.setMinimumSize(new java.awt.Dimension(250, 42));
        discountPlanTypejComboBox.setPreferredSize(new java.awt.Dimension(250, 42));
        discountPlanTypejComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountPlanTypejComboBoxActionPerformed(evt);
            }
        });

        discountCanceljButton.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        discountCanceljButton.setText("Cancel");
        discountCanceljButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountCanceljButtonActionPerformed(evt);
            }
        });

        applyDiscountjButton.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        applyDiscountjButton.setText("Apply");
        applyDiscountjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyDiscountjButtonActionPerformed(evt);
            }
        });

        DiscountTypeInformation.setBackground(new java.awt.Color(34, 54, 81));
        DiscountTypeInformation.setMaximumSize(new java.awt.Dimension(900, 500));
        DiscountTypeInformation.setMinimumSize(new java.awt.Dimension(900, 500));

        FixedDiscountType.setBackground(new java.awt.Color(34, 54, 81));
        FixedDiscountType.setMaximumSize(new java.awt.Dimension(900, 500));
        FixedDiscountType.setMinimumSize(new java.awt.Dimension(900, 500));

        fixedDiscountRatejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        fixedDiscountRatejLabel.setForeground(new java.awt.Color(255, 255, 255));
        fixedDiscountRatejLabel.setText("Discount rate:");

        fixedPercentageSymbol.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        fixedPercentageSymbol.setForeground(new java.awt.Color(255, 255, 255));
        fixedPercentageSymbol.setText("%");

        fixedDiscountRatejTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        fixedDiscountRatejTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        fixedDiscountRatejTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        fixedDiscountRatejTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fixedDiscountRatejTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FixedDiscountTypeLayout = new javax.swing.GroupLayout(FixedDiscountType);
        FixedDiscountType.setLayout(FixedDiscountTypeLayout);
        FixedDiscountTypeLayout.setHorizontalGroup(
            FixedDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FixedDiscountTypeLayout.createSequentialGroup()
                .addGap(291, 291, 291)
                .addComponent(fixedDiscountRatejLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fixedDiscountRatejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fixedPercentageSymbol)
                .addContainerGap(291, Short.MAX_VALUE))
        );
        FixedDiscountTypeLayout.setVerticalGroup(
            FixedDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FixedDiscountTypeLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(FixedDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fixedDiscountRatejLabel)
                    .addComponent(fixedPercentageSymbol)
                    .addComponent(fixedDiscountRatejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(422, Short.MAX_VALUE))
        );

        TotalLatePayjTextField.setEditable(false);

        VariableDiscountType.setBackground(new java.awt.Color(34, 54, 81));
        VariableDiscountType.setMaximumSize(new java.awt.Dimension(900, 500));
        VariableDiscountType.setPreferredSize(new java.awt.Dimension(900, 500));

        JobsjPanel.setBackground(new java.awt.Color(34, 54, 81));
        JobsjPanel.setMaximumSize(new java.awt.Dimension(888, 504));

        JobsjScrollPane.setMaximumSize(new java.awt.Dimension(700, 404));
        JobsjScrollPane.setMinimumSize(new java.awt.Dimension(700, 404));
        JobsjScrollPane.setPreferredSize(new java.awt.Dimension(700, 404));

        JobsjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Job No", "Customer Account No", "Status", "Is Collected"
            }
        ));
        JobsjScrollPane.setViewportView(JobsjTable);

        javax.swing.GroupLayout JobsjPanelLayout = new javax.swing.GroupLayout(JobsjPanel);
        JobsjPanel.setLayout(JobsjPanelLayout);
        JobsjPanelLayout.setHorizontalGroup(
            JobsjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 899, Short.MAX_VALUE)
            .addGroup(JobsjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(JobsjPanelLayout.createSequentialGroup()
                    .addGap(78, 78, 78)
                    .addComponent(JobsjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(102, Short.MAX_VALUE)))
        );
        JobsjPanelLayout.setVerticalGroup(
            JobsjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 516, Short.MAX_VALUE)
            .addGroup(JobsjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(JobsjPanelLayout.createSequentialGroup()
                    .addGap(48, 48, 48)
                    .addComponent(JobsjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(64, Short.MAX_VALUE)))
        );

        StandardJobsjPanel.setBackground(new java.awt.Color(34, 54, 81));
        StandardJobsjPanel.setMaximumSize(new java.awt.Dimension(888, 504));
        StandardJobsjPanel.setMinimumSize(new java.awt.Dimension(888, 504));

        StandardJobsjScrollPane.setMaximumSize(new java.awt.Dimension(700, 404));
        StandardJobsjScrollPane.setMinimumSize(new java.awt.Dimension(700, 404));
        StandardJobsjScrollPane.setPreferredSize(new java.awt.Dimension(700, 404));

        StandardJobsjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Standard Job No", "Standard Job Code", "Status"
            }
        ));
        StandardJobsjScrollPane.setViewportView(StandardJobsjTable);

        javax.swing.GroupLayout StandardJobsjPanelLayout = new javax.swing.GroupLayout(StandardJobsjPanel);
        StandardJobsjPanel.setLayout(StandardJobsjPanelLayout);
        StandardJobsjPanelLayout.setHorizontalGroup(
            StandardJobsjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StandardJobsjPanelLayout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(StandardJobsjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        StandardJobsjPanelLayout.setVerticalGroup(
            StandardJobsjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StandardJobsjPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(StandardJobsjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        TasksjPanel.setBackground(new java.awt.Color(34, 54, 81));
        TasksjPanel.setMaximumSize(new java.awt.Dimension(888, 504));
        TasksjPanel.setPreferredSize(new java.awt.Dimension(888, 504));

        TasksjScrollPane.setMaximumSize(new java.awt.Dimension(700, 404));
        TasksjScrollPane.setMinimumSize(new java.awt.Dimension(700, 404));
        TasksjScrollPane.setPreferredSize(new java.awt.Dimension(700, 404));

        TaskjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Task ID", "Task Description", "Price", "Discount"
            }
        ));
        TasksjScrollPane.setViewportView(TaskjTable);

        javax.swing.GroupLayout TasksjPanelLayout = new javax.swing.GroupLayout(TasksjPanel);
        TasksjPanel.setLayout(TasksjPanelLayout);
        TasksjPanelLayout.setHorizontalGroup(
            TasksjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TasksjPanelLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(TasksjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        TasksjPanelLayout.setVerticalGroup(
            TasksjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TasksjPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(TasksjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout VariableDiscountTypeLayout = new javax.swing.GroupLayout(VariableDiscountType);
        VariableDiscountType.setLayout(VariableDiscountTypeLayout);
        VariableDiscountTypeLayout.setHorizontalGroup(
            VariableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(VariableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(VariableDiscountTypeLayout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(JobsjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(4, 4, 4)))
            .addGroup(VariableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(VariableDiscountTypeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(StandardJobsjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(VariableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(VariableDiscountTypeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(TasksjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        VariableDiscountTypeLayout.setVerticalGroup(
            VariableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(VariableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(VariableDiscountTypeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(JobsjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(VariableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(VariableDiscountTypeLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(StandardJobsjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(12, Short.MAX_VALUE)))
            .addGroup(VariableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(VariableDiscountTypeLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(TasksjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(12, Short.MAX_VALUE)))
        );

        JobsjPanel.setVisible(false);
        StandardJobsjPanel.setVisible(false);

        FlexiableDiscountType.setBackground(new java.awt.Color(34, 54, 81));
        FlexiableDiscountType.setMaximumSize(new java.awt.Dimension(900, 500));
        FlexiableDiscountType.setMinimumSize(new java.awt.Dimension(900, 500));

        boundjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        boundjLabel.setForeground(new java.awt.Color(255, 255, 255));
        boundjLabel.setText("Add bound:");

        upperBoundjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        upperBoundjLabel.setForeground(new java.awt.Color(255, 255, 255));
        upperBoundjLabel.setText("Upper bound:");

        lowerBoundjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lowerBoundjLabel.setForeground(new java.awt.Color(255, 255, 255));
        lowerBoundjLabel.setText("Lower bound:");

        flexibleDiscountRatejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        flexibleDiscountRatejLabel.setForeground(new java.awt.Color(255, 255, 255));
        flexibleDiscountRatejLabel.setText("Discount rate:");

        flexiablePercentageSymbol.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        flexiablePercentageSymbol.setForeground(new java.awt.Color(255, 255, 255));
        flexiablePercentageSymbol.setText("%");

        upperBoundjTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        upperBoundjTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        upperBoundjTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        upperBoundjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upperBoundjTextFieldActionPerformed(evt);
            }
        });

        lowerBoundjTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        lowerBoundjTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        lowerBoundjTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        lowerBoundjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lowerBoundjTextFieldActionPerformed(evt);
            }
        });

        flexibleDiscountRatejTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        flexibleDiscountRatejTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        flexibleDiscountRatejTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        flexibleDiscountRatejTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flexibleDiscountRatejTextFieldActionPerformed(evt);
            }
        });

        jScrollPane11.setViewportView(boundsjList);

        removeFlexibleBoundjButton.setText("Remove");
        removeFlexibleBoundjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFlexibleBoundjButtonActionPerformed(evt);
            }
        });

        addFlexibleBoundjButton.setText("Add");
        addFlexibleBoundjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFlexibleBoundjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FlexiableDiscountTypeLayout = new javax.swing.GroupLayout(FlexiableDiscountType);
        FlexiableDiscountType.setLayout(FlexiableDiscountTypeLayout);
        FlexiableDiscountTypeLayout.setHorizontalGroup(
            FlexiableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FlexiableDiscountTypeLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(boundjLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FlexiableDiscountTypeLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(FlexiableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(flexibleDiscountRatejLabel)
                    .addComponent(lowerBoundjLabel)
                    .addComponent(upperBoundjLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FlexiableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lowerBoundjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(upperBoundjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FlexiableDiscountTypeLayout.createSequentialGroup()
                        .addGroup(FlexiableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addFlexibleBoundjButton)
                            .addComponent(flexibleDiscountRatejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flexiablePercentageSymbol)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addGroup(FlexiableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeFlexibleBoundjButton)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );
        FlexiableDiscountTypeLayout.setVerticalGroup(
            FlexiableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FlexiableDiscountTypeLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(FlexiableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FlexiableDiscountTypeLayout.createSequentialGroup()
                        .addComponent(boundjLabel)
                        .addGap(68, 68, 68)
                        .addGroup(FlexiableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(upperBoundjLabel)
                            .addComponent(upperBoundjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(FlexiableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lowerBoundjLabel)
                            .addComponent(lowerBoundjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(FlexiableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(flexibleDiscountRatejLabel)
                            .addComponent(flexiablePercentageSymbol)
                            .addComponent(flexibleDiscountRatejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(FlexiableDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeFlexibleBoundjButton)
                    .addComponent(addFlexibleBoundjButton))
                .addContainerGap(150, Short.MAX_VALUE))
        );

        TotalLatePayjTextField.setEditable(false);
        TotalLatePayjTextField.setEditable(false);
        TotalLatePayjTextField.setEditable(false);

        javax.swing.GroupLayout DiscountTypeInformationLayout = new javax.swing.GroupLayout(DiscountTypeInformation);
        DiscountTypeInformation.setLayout(DiscountTypeInformationLayout);
        DiscountTypeInformationLayout.setHorizontalGroup(
            DiscountTypeInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(DiscountTypeInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DiscountTypeInformationLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(FixedDiscountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(DiscountTypeInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DiscountTypeInformationLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(VariableDiscountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(DiscountTypeInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DiscountTypeInformationLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(FlexiableDiscountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        DiscountTypeInformationLayout.setVerticalGroup(
            DiscountTypeInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(DiscountTypeInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DiscountTypeInformationLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(FixedDiscountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(DiscountTypeInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DiscountTypeInformationLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(VariableDiscountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(DiscountTypeInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DiscountTypeInformationLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(FlexiableDiscountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        FixedDiscountType.setVisible(false);
        VariableDiscountType.setVisible(false);
        FlexiableDiscountType.setVisible(false);

        javax.swing.GroupLayout SelectDiscountPlanLayout = new javax.swing.GroupLayout(SelectDiscountPlan);
        SelectDiscountPlan.setLayout(SelectDiscountPlanLayout);
        SelectDiscountPlanLayout.setHorizontalGroup(
            SelectDiscountPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SelectDiscountPlanLayout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addGroup(SelectDiscountPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(discountPlanTypejComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(discountPlanTypejLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SelectDiscountPlanLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(DiscountTypeInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SelectDiscountPlanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(discountCanceljButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(applyDiscountjButton)
                .addGap(31, 31, 31))
        );
        SelectDiscountPlanLayout.setVerticalGroup(
            SelectDiscountPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SelectDiscountPlanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(discountPlanTypejLabel)
                .addGap(18, 18, 18)
                .addComponent(discountPlanTypejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DiscountTypeInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(SelectDiscountPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(discountCanceljButton)
                    .addComponent(applyDiscountjButton))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        DiscountTypeInformation.setVisible(true);

        cardPanel1.add(SelectDiscountPlan, "DiscountPlan");

        SelectCustomer.setBackground(new java.awt.Color(61, 96, 146));
        SelectCustomer.setMaximumSize(new java.awt.Dimension(900, 700));
        SelectCustomer.setMinimumSize(new java.awt.Dimension(900, 700));

        customersjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "AccountNo", "Account Holder Name", "FirstName", "LastName"
            }
        ));
        customersjScrollPane.setViewportView(customersjTable);

        selectCustomerjButton.setText("Select");
        selectCustomerjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCustomerjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SelectCustomerLayout = new javax.swing.GroupLayout(SelectCustomer);
        SelectCustomer.setLayout(SelectCustomerLayout);
        SelectCustomerLayout.setHorizontalGroup(
            SelectCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SelectCustomerLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(SelectCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(selectCustomerjButton)
                    .addComponent(customersjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 848, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        SelectCustomerLayout.setVerticalGroup(
            SelectCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SelectCustomerLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(customersjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(selectCustomerjButton)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        cardPanel1.add(SelectCustomer, "SelectCustomer");

        ViewCustomerDetail.setBackground(new java.awt.Color(61, 96, 146));
        ViewCustomerDetail.setMaximumSize(new java.awt.Dimension(900, 700));
        ViewCustomerDetail.setMinimumSize(new java.awt.Dimension(900, 700));

        CustomerInfo.setBackground(new java.awt.Color(255, 255, 255));

        customerAccHolderNamejLabel.setText("jLabel1");

        customerPrefixjLabel.setText("jLabel2");

        customerFirstNamejLabel.setText("jLabel3");

        customerLastNamejLabel.setText("jLabel4");

        jLabel5.setText("Status:");

        jLabel6.setText("Type:");

        jLabel7.setText("In Default:");

        jLabel8.setText("Discount:");

        customerStatusjTextField.setText("Unknown");

        customerTypejTextField.setText("jTextField2");

        cutomerInDefaultjTextField.setText("jTextField3");

        customerDiscountjTextField.setText("jTextField4");

        customerNumberjLabel.setText("jLabel1");

        customerRegistationDatejLabel.setText("jLabel1");

        registrationDatejLabel.setText("Registrated on:");

        javax.swing.GroupLayout CustomerInfoLayout = new javax.swing.GroupLayout(CustomerInfo);
        CustomerInfo.setLayout(CustomerInfoLayout);
        CustomerInfoLayout.setHorizontalGroup(
            CustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CustomerInfoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(CustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerAccHolderNamejLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(customerNumberjLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CustomerInfoLayout.createSequentialGroup()
                        .addComponent(registrationDatejLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(customerRegistationDatejLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CustomerInfoLayout.createSequentialGroup()
                        .addComponent(customerPrefixjLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customerFirstNamejLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customerLastNamejLabel)))
                .addGap(50, 50, 50)
                .addGroup(CustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(CustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(customerTypejTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(customerDiscountjTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cutomerInDefaultjTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerStatusjTextField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CustomerInfoLayout.setVerticalGroup(
            CustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerInfoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(CustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(customerStatusjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerAccHolderNamejLabel))
                .addGap(18, 18, 18)
                .addGroup(CustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(customerPrefixjLabel)
                        .addComponent(customerFirstNamejLabel)
                        .addComponent(customerLastNamejLabel))
                    .addGroup(CustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(customerTypejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cutomerInDefaultjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerNumberjLabel))
                .addGap(17, 17, 17)
                .addGroup(CustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(customerRegistationDatejLabel)
                        .addComponent(registrationDatejLabel))
                    .addGroup(CustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(customerDiscountjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        CustomerAction.setBackground(new java.awt.Color(255, 255, 255));

        customerActionjLabel.setText("Actions");

        assignDiscountPlanjButton.setText("Assign Discount Plan");
        assignDiscountPlanjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignDiscountPlanjButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Update account status");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Delete Account");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("View Invoices");

        javax.swing.GroupLayout CustomerActionLayout = new javax.swing.GroupLayout(CustomerAction);
        CustomerAction.setLayout(CustomerActionLayout);
        CustomerActionLayout.setHorizontalGroup(
            CustomerActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerActionLayout.createSequentialGroup()
                .addGroup(CustomerActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CustomerActionLayout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(customerActionjLabel))
                    .addGroup(CustomerActionLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(CustomerActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(assignDiscountPlanjButton)))
                    .addGroup(CustomerActionLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addGroup(CustomerActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3)
                            .addComponent(jButton2))))
                .addContainerGap(215, Short.MAX_VALUE))
        );
        CustomerActionLayout.setVerticalGroup(
            CustomerActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerActionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(customerActionjLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assignDiscountPlanjButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        OtherCustomerInfo.setBackground(new java.awt.Color(255, 255, 255));

        customerStreetNamejTextField.setText("jTextField1");

        customerPostcodejTextField.setText("jTextField2");

        customerCityjTextField.setText("jTextField3");

        customerBuildingNojTextField.setText("jTextField4");

        javax.swing.GroupLayout OtherCustomerInfoLayout = new javax.swing.GroupLayout(OtherCustomerInfo);
        OtherCustomerInfo.setLayout(OtherCustomerInfoLayout);
        OtherCustomerInfoLayout.setHorizontalGroup(
            OtherCustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OtherCustomerInfoLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(OtherCustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerBuildingNojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerCityjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerPostcodejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerStreetNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        OtherCustomerInfoLayout.setVerticalGroup(
            OtherCustomerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OtherCustomerInfoLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(customerStreetNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(customerPostcodejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(customerCityjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(customerBuildingNojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewCustomerDetailLayout = new javax.swing.GroupLayout(ViewCustomerDetail);
        ViewCustomerDetail.setLayout(ViewCustomerDetailLayout);
        ViewCustomerDetailLayout.setHorizontalGroup(
            ViewCustomerDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewCustomerDetailLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(ViewCustomerDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CustomerInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ViewCustomerDetailLayout.createSequentialGroup()
                        .addComponent(CustomerAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(OtherCustomerInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        ViewCustomerDetailLayout.setVerticalGroup(
            ViewCustomerDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewCustomerDetailLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(CustomerInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ViewCustomerDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(OtherCustomerInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CustomerAction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        cardPanel1.add(ViewCustomerDetail, "ViewCustomerDetail");

        acceptPayment.setMaximumSize(new java.awt.Dimension(900, 700));
        acceptPayment.setMinimumSize(new java.awt.Dimension(900, 700));
        acceptPayment.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                acceptPaymentComponentHidden(evt);
            }
        });

        acceptLatePaymentjPanel.setBackground(new java.awt.Color(61, 96, 146));
        acceptLatePaymentjPanel.setMaximumSize(new java.awt.Dimension(900, 700));
        acceptLatePaymentjPanel.setMinimumSize(new java.awt.Dimension(900, 700));

        expiryDatejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        expiryDatejLabel.setForeground(new java.awt.Color(255, 255, 255));
        expiryDatejLabel.setText("Expiry date:");

        last4DigitjTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        last4DigitjTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        last4DigitjTextField.setPreferredSize(new java.awt.Dimension(250, 42));

        selectInvoicejButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        selectInvoicejButton.setText("Select Invoice(s)");
        selectInvoicejButton.setMaximumSize(new java.awt.Dimension(159, 37));
        selectInvoicejButton.setMinimumSize(new java.awt.Dimension(159, 37));
        selectInvoicejButton.setPreferredSize(new java.awt.Dimension(159, 37));
        selectInvoicejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectInvoicejButtonActionPerformed(evt);
            }
        });

        paymentTypejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        paymentTypejLabel.setForeground(new java.awt.Color(255, 255, 255));
        paymentTypejLabel.setText("Payment Type:");

        expiryDatejTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        expiryDatejTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        expiryDatejTextField.setPreferredSize(new java.awt.Dimension(250, 42));

        paymentTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Card", "Cash" }));
        paymentTypeComboBox.setMaximumSize(new java.awt.Dimension(250, 42));
        paymentTypeComboBox.setMinimumSize(new java.awt.Dimension(250, 42));
        paymentTypeComboBox.setPreferredSize(new java.awt.Dimension(250, 42));
        paymentTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentTypeComboBoxActionPerformed(evt);
            }
        });

        TotalLatePayjTextField.setText("£");
        TotalLatePayjTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        TotalLatePayjTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        TotalLatePayjTextField.setPreferredSize(new java.awt.Dimension(250, 42));

        cardTypejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        cardTypejLabel.setForeground(new java.awt.Color(255, 255, 255));
        cardTypejLabel.setText("Card type:");

        totaljLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        totaljLabel.setForeground(new java.awt.Color(255, 255, 255));
        totaljLabel.setText("Total:");

        cardTypejComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MasterCard", "Visa", "American Express" }));
        cardTypejComboBox.setMaximumSize(new java.awt.Dimension(250, 42));
        cardTypejComboBox.setMinimumSize(new java.awt.Dimension(250, 42));
        cardTypejComboBox.setPreferredSize(new java.awt.Dimension(250, 42));

        last4DigitjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        last4DigitjLabel.setForeground(new java.awt.Color(255, 255, 255));
        last4DigitjLabel.setText("Last 4 digits:");

        invoicejScrollPane.setViewportView(invoicejList);

        latePaymentSubmitjButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        latePaymentSubmitjButton.setText("Submit");
        latePaymentSubmitjButton.setMaximumSize(new java.awt.Dimension(163, 37));
        latePaymentSubmitjButton.setMinimumSize(new java.awt.Dimension(163, 37));
        latePaymentSubmitjButton.setPreferredSize(new java.awt.Dimension(163, 37));
        latePaymentSubmitjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                latePaymentSubmitjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout acceptLatePaymentjPanelLayout = new javax.swing.GroupLayout(acceptLatePaymentjPanel);
        acceptLatePaymentjPanel.setLayout(acceptLatePaymentjPanelLayout);
        acceptLatePaymentjPanelLayout.setHorizontalGroup(
            acceptLatePaymentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acceptLatePaymentjPanelLayout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addGroup(acceptLatePaymentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(expiryDatejLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totaljLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(paymentTypejLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cardTypejLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(selectInvoicejButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(last4DigitjLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(acceptLatePaymentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(acceptLatePaymentjPanelLayout.createSequentialGroup()
                        .addComponent(last4DigitjTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(150, 150, 150))
                    .addGroup(acceptLatePaymentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(acceptLatePaymentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(TotalLatePayjTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(paymentTypeComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cardTypejComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(expiryDatejTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(invoicejScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(153, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acceptLatePaymentjPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(latePaymentSubmitjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
        );
        acceptLatePaymentjPanelLayout.setVerticalGroup(
            acceptLatePaymentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(acceptLatePaymentjPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(acceptLatePaymentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(invoicejScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectInvoicejButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(acceptLatePaymentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acceptLatePaymentjPanelLayout.createSequentialGroup()
                        .addGroup(acceptLatePaymentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(totaljLabel)
                            .addComponent(TotalLatePayjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(acceptLatePaymentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(paymentTypejLabel)
                            .addComponent(paymentTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(cardTypejLabel))
                    .addComponent(cardTypejComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(acceptLatePaymentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(last4DigitjLabel)
                    .addComponent(last4DigitjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(acceptLatePaymentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(expiryDatejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expiryDatejLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(latePaymentSubmitjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172))
        );

        TotalLatePayjTextField.setEditable(false);

        javax.swing.GroupLayout acceptPaymentLayout = new javax.swing.GroupLayout(acceptPayment);
        acceptPayment.setLayout(acceptPaymentLayout);
        acceptPaymentLayout.setHorizontalGroup(
            acceptPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(acceptPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(acceptPaymentLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(acceptLatePaymentjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        acceptPaymentLayout.setVerticalGroup(
            acceptPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(acceptPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(acceptPaymentLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(acceptLatePaymentjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        cardPanel1.add(acceptPayment, "acceptPayment");

        technicianPage.setBackground(new java.awt.Color(61, 96, 146));
        technicianPage.setMaximumSize(new java.awt.Dimension(900, 640));
        technicianPage.setMinimumSize(new java.awt.Dimension(900, 640));

        jobEnquiryTechnician.setText("Job Enquiry");
        jobEnquiryTechnician.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jobEnquiryTechnicianActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout technicianPageLayout = new javax.swing.GroupLayout(technicianPage);
        technicianPage.setLayout(technicianPageLayout);
        technicianPageLayout.setHorizontalGroup(
            technicianPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(technicianPageLayout.createSequentialGroup()
                .addGap(343, 343, 343)
                .addComponent(jobEnquiryTechnician, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(361, Short.MAX_VALUE))
        );
        technicianPageLayout.setVerticalGroup(
            technicianPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(technicianPageLayout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(jobEnquiryTechnician, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(299, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout technicianHomePageLayout = new javax.swing.GroupLayout(technicianHomePage);
        technicianHomePage.setLayout(technicianHomePageLayout);
        technicianHomePageLayout.setHorizontalGroup(
            technicianHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, technicianHomePageLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(technicianPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        technicianHomePageLayout.setVerticalGroup(
            technicianHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(technicianHomePageLayout.createSequentialGroup()
                .addComponent(technicianPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cardPanel1.add(technicianHomePage, "technicianHomePage");

        taskPage.setBackground(new java.awt.Color(61, 96, 146));

        manageTasksButton.setText("Manage Tasks");
        manageTasksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageTasksButtonActionPerformed(evt);
            }
        });

        createTaskButton.setText("Create Task");
        createTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTaskButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout taskPageLayout = new javax.swing.GroupLayout(taskPage);
        taskPage.setLayout(taskPageLayout);
        taskPageLayout.setHorizontalGroup(
            taskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskPageLayout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(manageTasksButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(createTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(252, Short.MAX_VALUE))
        );
        taskPageLayout.setVerticalGroup(
            taskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskPageLayout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addGroup(taskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageTasksButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(284, Short.MAX_VALUE))
        );

        cardPanel1.add(taskPage, "taskPage");

        receptionHomePage.setBackground(new java.awt.Color(61, 96, 146));
        receptionHomePage.setMaximumSize(new java.awt.Dimension(900, 640));
        receptionHomePage.setMinimumSize(new java.awt.Dimension(900, 640));

        jobReceptionist.setText("Job");
        jobReceptionist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jobReceptionistActionPerformed(evt);
            }
        });

        acceptPaymentReceptionist.setText("Accept Payment");
        acceptPaymentReceptionist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptPaymentReceptionistActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout receptionHomePageLayout = new javax.swing.GroupLayout(receptionHomePage);
        receptionHomePage.setLayout(receptionHomePageLayout);
        receptionHomePageLayout.setHorizontalGroup(
            receptionHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receptionHomePageLayout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jobReceptionist, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125)
                .addComponent(acceptPaymentReceptionist, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(206, Short.MAX_VALUE))
        );
        receptionHomePageLayout.setVerticalGroup(
            receptionHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receptionHomePageLayout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addGroup(receptionHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jobReceptionist, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceptPaymentReceptionist, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(283, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout receptionistHomePageLayout = new javax.swing.GroupLayout(receptionistHomePage);
        receptionistHomePage.setLayout(receptionistHomePageLayout);
        receptionistHomePageLayout.setHorizontalGroup(
            receptionistHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, receptionistHomePageLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(receptionHomePage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        receptionistHomePageLayout.setVerticalGroup(
            receptionistHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receptionistHomePageLayout.createSequentialGroup()
                .addComponent(receptionHomePage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cardPanel1.add(receptionistHomePage, "receptionistHomePage");

        editTaskPage.setBackground(new java.awt.Color(61, 96, 146));

        newDescriptionLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        newDescriptionLabel.setForeground(new java.awt.Color(255, 255, 255));
        newDescriptionLabel.setText("New Description");

        newDescriptionLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        newDescriptionLabel1.setForeground(new java.awt.Color(255, 255, 255));
        newDescriptionLabel1.setText("New Department");

        newDescriptionLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        newDescriptionLabel2.setForeground(new java.awt.Color(255, 255, 255));
        newDescriptionLabel2.setText("New Price");

        newDescriptionLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        newDescriptionLabel3.setForeground(new java.awt.Color(255, 255, 255));
        newDescriptionLabel3.setText("New Shelf Slot");

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        departmentComboBox.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        departmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Copy Room", "Development Area", "Dark Room", "Finishing Room", "Packaging Department", "Printing Room", " " }));

        taskIDLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        taskIDLabel.setForeground(new java.awt.Color(255, 255, 255));
        taskIDLabel.setText("Task ID");

        javax.swing.GroupLayout editTaskPageLayout = new javax.swing.GroupLayout(editTaskPage);
        editTaskPage.setLayout(editTaskPageLayout);
        editTaskPageLayout.setHorizontalGroup(
            editTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTaskPageLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(editTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editTaskPageLayout.createSequentialGroup()
                        .addGroup(editTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newDescriptionLabel3)
                            .addComponent(newDescriptionLabel1)
                            .addComponent(newDescriptionLabel2))
                        .addGroup(editTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editTaskPageLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                                .addGroup(editTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(shelfSlotText, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                                    .addComponent(priceText)))
                            .addGroup(editTaskPageLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(departmentComboBox, 0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editTaskPageLayout.createSequentialGroup()
                        .addGroup(editTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(taskIDLabel)
                            .addComponent(newDescriptionLabel))
                        .addGap(82, 82, 82)
                        .addGroup(editTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(descriptionText, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                            .addComponent(taskIDText))))
                .addContainerGap(193, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editTaskPageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );
        editTaskPageLayout.setVerticalGroup(
            editTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTaskPageLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(editTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(taskIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(taskIDText))
                .addGap(18, 18, 18)
                .addGroup(editTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(descriptionText)
                    .addComponent(newDescriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(editTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newDescriptionLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(departmentComboBox))
                .addGap(27, 27, 27)
                .addGroup(editTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(shelfSlotText)
                    .addComponent(newDescriptionLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(editTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newDescriptionLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priceText))
                .addGap(86, 86, 86)
                .addGroup(editTaskPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91))
        );

        cardPanel1.add(editTaskPage, "editTaskPage");

        manageTasksPage.setBackground(new java.awt.Color(61, 96, 146));

        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        taskTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Task ID", "Description", "Location", "Shelf Slot"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane12.setViewportView(taskTable);

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton1.setText("Delete");
        deleteButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout manageTasksPageLayout = new javax.swing.GroupLayout(manageTasksPage);
        manageTasksPage.setLayout(manageTasksPageLayout);
        manageTasksPageLayout.setHorizontalGroup(
            manageTasksPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageTasksPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(manageTasksPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                    .addGroup(manageTasksPageLayout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        manageTasksPageLayout.setVerticalGroup(
            manageTasksPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manageTasksPageLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(manageTasksPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(79, 79, 79))
        );

        cardPanel1.add(manageTasksPage, "manageTasksPage");

        officeManagerCustomerPage.setBackground(new java.awt.Color(61, 96, 146));

        searchCustomerBtn.setText("Search Customer");
        searchCustomerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCustomerBtnActionPerformed(evt);
            }
        });

        reminderLettersButton.setText("Reminder letters");
        reminderLettersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reminderLettersButtonActionPerformed(evt);
            }
        });

        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout officeManagerCustomerPageLayout = new javax.swing.GroupLayout(officeManagerCustomerPage);
        officeManagerCustomerPage.setLayout(officeManagerCustomerPageLayout);
        officeManagerCustomerPageLayout.setHorizontalGroup(
            officeManagerCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, officeManagerCustomerPageLayout.createSequentialGroup()
                .addContainerGap(215, Short.MAX_VALUE)
                .addComponent(reminderLettersButton)
                .addGap(156, 156, 156)
                .addComponent(searchCustomerBtn)
                .addGap(227, 227, 227))
            .addGroup(officeManagerCustomerPageLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        officeManagerCustomerPageLayout.setVerticalGroup(
            officeManagerCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(officeManagerCustomerPageLayout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addGroup(officeManagerCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchCustomerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reminderLettersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        cardPanel1.add(officeManagerCustomerPage, "officeManagerCustomerPage");

        reminderLettersTablePage.setBackground(new java.awt.Color(61, 96, 146));

        reminderLettersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer name", "Invoice number", "Date", "Total amount"
            }
        ));
        jScrollPane13.setViewportView(reminderLettersTable);

        backCustomerPageButton.setText("Back");
        backCustomerPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backCustomerPageButtonActionPerformed(evt);
            }
        });

        viewReminderLetterButton.setText("Create PDF");
        viewReminderLetterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReminderLetterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reminderLettersTablePageLayout = new javax.swing.GroupLayout(reminderLettersTablePage);
        reminderLettersTablePage.setLayout(reminderLettersTablePageLayout);
        reminderLettersTablePageLayout.setHorizontalGroup(
            reminderLettersTablePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reminderLettersTablePageLayout.createSequentialGroup()
                .addGroup(reminderLettersTablePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(reminderLettersTablePageLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backCustomerPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(viewReminderLetterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, reminderLettersTablePageLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        reminderLettersTablePageLayout.setVerticalGroup(
            reminderLettersTablePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reminderLettersTablePageLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(reminderLettersTablePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(backCustomerPageButton, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(viewReminderLetterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        cardPanel1.add(reminderLettersTablePage, "reminderLettersTablePage");

        taskSearchResultsJobEnquiryPage.setBackground(new java.awt.Color(61, 96, 146));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Standard Job Code: ");

        standardJobCodeLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        standardJobCodeLabel.setForeground(new java.awt.Color(255, 255, 255));
        standardJobCodeLabel.setText("(Code) ");

        taskResultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Task ID", "Description", "Location", "Shelf slot", "Status"
            }
        ));
        jScrollPane14.setViewportView(taskResultsTable);

        updateTaskButton.setText("Update");
        updateTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTaskButtonActionPerformed(evt);
            }
        });

        taskEnquiryBackButton.setText("Back");
        taskEnquiryBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taskEnquiryBackButtonActionPerformed(evt);
            }
        });

        standardJobIndexLabel.setBackground(new java.awt.Color(61, 96, 146));
        standardJobIndexLabel.setForeground(new java.awt.Color(61, 96, 146));
        standardJobIndexLabel.setText("Hidden");

        javax.swing.GroupLayout taskSearchResultsJobEnquiryPageLayout = new javax.swing.GroupLayout(taskSearchResultsJobEnquiryPage);
        taskSearchResultsJobEnquiryPage.setLayout(taskSearchResultsJobEnquiryPageLayout);
        taskSearchResultsJobEnquiryPageLayout.setHorizontalGroup(
            taskSearchResultsJobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskSearchResultsJobEnquiryPageLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(taskSearchResultsJobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(taskSearchResultsJobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(taskSearchResultsJobEnquiryPageLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(standardJobCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(standardJobIndexLabel)))
                    .addGroup(taskSearchResultsJobEnquiryPageLayout.createSequentialGroup()
                        .addComponent(taskEnquiryBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateTaskButton)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        taskSearchResultsJobEnquiryPageLayout.setVerticalGroup(
            taskSearchResultsJobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskSearchResultsJobEnquiryPageLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(taskSearchResultsJobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(standardJobCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(standardJobIndexLabel))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addGroup(taskSearchResultsJobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(taskEnquiryBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        cardPanel1.add(taskSearchResultsJobEnquiryPage, "taskSearchResultsJobEnquiryPage");

        searchJobPage.setBackground(new java.awt.Color(61, 96, 146));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Customer Number:");

        searchJobButton.setText("Search Job");
        searchJobButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJobButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Job Number:");

        searchCustomerButton1.setText("Search Customer");
        searchCustomerButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCustomerButton1ActionPerformed(evt);
            }
        });

        backRecpHPButton.setText("Back");
        backRecpHPButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backRecpHPButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchJobPageLayout = new javax.swing.GroupLayout(searchJobPage);
        searchJobPage.setLayout(searchJobPageLayout);
        searchJobPageLayout.setHorizontalGroup(
            searchJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchJobPageLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(searchJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchJobPageLayout.createSequentialGroup()
                        .addGroup(searchJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(searchJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jobNumberTextField)
                            .addComponent(customerNumberText, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                        .addGap(52, 52, 52)
                        .addGroup(searchJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchJobButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchCustomerButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(backRecpHPButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        searchJobPageLayout.setVerticalGroup(
            searchJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchJobPageLayout.createSequentialGroup()
                .addGroup(searchJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchJobPageLayout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jobNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchJobPageLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(searchJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(searchJobButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(57, 57, 57)
                .addGroup(searchJobPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(customerNumberText)
                    .addComponent(searchCustomerButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                .addComponent(backRecpHPButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        cardPanel1.add(searchJobPage, "searchJobPage");

        jobEnquiryPage.setBackground(new java.awt.Color(61, 96, 146));

        jobStatusLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jobStatusLabel.setForeground(new java.awt.Color(255, 255, 255));
        jobStatusLabel.setText("Job Status: ");

        jobNumberText.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        searchJobNumberJobEnquiryButton.setText("Search");
        searchJobNumberJobEnquiryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJobNumberJobEnquiryButtonActionPerformed(evt);
            }
        });

        jobNumberLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jobNumberLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jobNumberLabel1.setText("Job Number:");

        jobPriorityLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jobPriorityLabel.setForeground(new java.awt.Color(255, 255, 255));
        jobPriorityLabel.setText("Job Priority:");

        jobPriorityLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jobPriorityLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jobPriorityLabel2.setText("Collected:");

        cancelJobEnquiryButton.setText("Cancel");
        cancelJobEnquiryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelJobEnquiryButtonActionPerformed(evt);
            }
        });

        searchJobEnquiryButton.setText("Search");
        searchJobEnquiryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJobEnquiryButtonActionPerformed(evt);
            }
        });

        jobStatusComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jobStatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "In progress", "Completed", "Not started" }));

        jobCollectedComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jobCollectedComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Yes" }));

        jobPriorityComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jobPriorityComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "normal", "urgent", "stipulated", " " }));

        javax.swing.GroupLayout jobEnquiryPageLayout = new javax.swing.GroupLayout(jobEnquiryPage);
        jobEnquiryPage.setLayout(jobEnquiryPageLayout);
        jobEnquiryPageLayout.setHorizontalGroup(
            jobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jobEnquiryPageLayout.createSequentialGroup()
                .addGroup(jobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jobEnquiryPageLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(jobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jobEnquiryPageLayout.createSequentialGroup()
                                .addGroup(jobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jobPriorityLabel2)
                                    .addComponent(jobPriorityLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jobCollectedComboBox, 0, 183, Short.MAX_VALUE)
                                    .addComponent(jobPriorityComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jobEnquiryPageLayout.createSequentialGroup()
                                .addGroup(jobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jobEnquiryPageLayout.createSequentialGroup()
                                        .addComponent(jobNumberLabel1)
                                        .addGap(40, 40, 40))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jobEnquiryPageLayout.createSequentialGroup()
                                        .addComponent(jobStatusLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jobNumberText, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jobStatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(searchJobNumberJobEnquiryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jobEnquiryPageLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelJobEnquiryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addComponent(searchJobEnquiryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        jobEnquiryPageLayout.setVerticalGroup(
            jobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jobEnquiryPageLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(jobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jobNumberLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jobNumberText, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jobEnquiryPageLayout.createSequentialGroup()
                        .addComponent(searchJobNumberJobEnquiryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(64, 64, 64)
                .addGroup(jobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jobStatusLabel)
                    .addComponent(jobStatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jobPriorityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jobEnquiryPageLayout.createSequentialGroup()
                        .addComponent(jobPriorityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jobPriorityLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jobEnquiryPageLayout.createSequentialGroup()
                        .addComponent(jobCollectedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addGap(115, 115, 115)
                .addGroup(jobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelJobEnquiryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchJobEnquiryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        cardPanel1.add(jobEnquiryPage, "jobEnquiryPage");

        jobEnquirySearchResultsPage.setBackground(new java.awt.Color(61, 96, 146));

        jobEnquiryTableResults.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Job Number", "Issued By", "Date recorded", "Deadline", "Status"
            }
        ));
        jScrollPane15.setViewportView(jobEnquiryTableResults);

        viewJobEnquiryButton.setText("View");
        viewJobEnquiryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewJobEnquiryButtonActionPerformed(evt);
            }
        });

        backJobEnquiryButton.setText("Back");
        backJobEnquiryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJobEnquiryButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jobEnquirySearchResultsPageLayout = new javax.swing.GroupLayout(jobEnquirySearchResultsPage);
        jobEnquirySearchResultsPage.setLayout(jobEnquirySearchResultsPageLayout);
        jobEnquirySearchResultsPageLayout.setHorizontalGroup(
            jobEnquirySearchResultsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jobEnquirySearchResultsPageLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jobEnquirySearchResultsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jobEnquirySearchResultsPageLayout.createSequentialGroup()
                        .addComponent(backJobEnquiryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(viewJobEnquiryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jobEnquirySearchResultsPageLayout.setVerticalGroup(
            jobEnquirySearchResultsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jobEnquirySearchResultsPageLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jobEnquirySearchResultsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewJobEnquiryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backJobEnquiryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        cardPanel1.add(jobEnquirySearchResultsPage, "jobEnquirySearchResultsPage");

        jobSearchResultsPage.setBackground(new java.awt.Color(61, 96, 146));

        backButon.setText("Back");
        backButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButonActionPerformed(evt);
            }
        });

        jobSearchResultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Job no", "Issued by", "Deadline", "Status", "Collected"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane16.setViewportView(jobSearchResultsTable);

        collectJobButton.setText("Collect");
        collectJobButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collectJobButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jobSearchResultsPageLayout = new javax.swing.GroupLayout(jobSearchResultsPage);
        jobSearchResultsPage.setLayout(jobSearchResultsPageLayout);
        jobSearchResultsPageLayout.setHorizontalGroup(
            jobSearchResultsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jobSearchResultsPageLayout.createSequentialGroup()
                .addContainerGap(553, Short.MAX_VALUE)
                .addComponent(collectJobButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(backButon, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(jobSearchResultsPageLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jobSearchResultsPageLayout.setVerticalGroup(
            jobSearchResultsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jobSearchResultsPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jobSearchResultsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(backButon, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(collectJobButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(90, 90, 90))
        );

        cardPanel1.add(jobSearchResultsPage, "jobSearchResultsPage");

        standardJobSearchResultsJobEnquiryPage.setBackground(new java.awt.Color(61, 96, 146));

        standardJobResults.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        standardJobResults.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Description", "Status"
            }
        ));
        jScrollPane17.setViewportView(standardJobResults);

        viewStandardJobButton.setText("View");
        viewStandardJobButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewStandardJobButtonActionPerformed(evt);
            }
        });

        backJobSearchResultsButton.setText("Back");
        backJobSearchResultsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJobSearchResultsButtonActionPerformed(evt);
            }
        });

        jobNumberLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jobNumberLabel.setForeground(new java.awt.Color(255, 255, 255));
        jobNumberLabel.setText("(JN)");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Job Number: ");

        jobIndexLabel.setBackground(new java.awt.Color(61, 96, 146));
        jobIndexLabel.setForeground(new java.awt.Color(61, 96, 146));
        jobIndexLabel.setText("jLabel4");

        javax.swing.GroupLayout standardJobSearchResultsJobEnquiryPageLayout = new javax.swing.GroupLayout(standardJobSearchResultsJobEnquiryPage);
        standardJobSearchResultsJobEnquiryPage.setLayout(standardJobSearchResultsJobEnquiryPageLayout);
        standardJobSearchResultsJobEnquiryPageLayout.setHorizontalGroup(
            standardJobSearchResultsJobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(standardJobSearchResultsJobEnquiryPageLayout.createSequentialGroup()
                .addGroup(standardJobSearchResultsJobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(standardJobSearchResultsJobEnquiryPageLayout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(jobNumberLabel)
                        .addGap(134, 134, 134)
                        .addComponent(jobIndexLabel))
                    .addGroup(standardJobSearchResultsJobEnquiryPageLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(standardJobSearchResultsJobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(standardJobSearchResultsJobEnquiryPageLayout.createSequentialGroup()
                                .addComponent(backJobSearchResultsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(viewStandardJobButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(standardJobSearchResultsJobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(standardJobSearchResultsJobEnquiryPageLayout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(jLabel9)
                    .addContainerGap(639, Short.MAX_VALUE)))
        );
        standardJobSearchResultsJobEnquiryPageLayout.setVerticalGroup(
            standardJobSearchResultsJobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(standardJobSearchResultsJobEnquiryPageLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(standardJobSearchResultsJobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jobNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jobIndexLabel))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(standardJobSearchResultsJobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewStandardJobButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backJobSearchResultsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(standardJobSearchResultsJobEnquiryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(standardJobSearchResultsJobEnquiryPageLayout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(554, Short.MAX_VALUE)))
        );

        cardPanel1.add(standardJobSearchResultsJobEnquiryPage, "standardJobSearchResultsJobEnquiryPage");

        cardPanel2.setBackground(new java.awt.Color(204, 255, 204));
        cardPanel2.setMaximumSize(new java.awt.Dimension(900, 60));
        cardPanel2.setPreferredSize(new java.awt.Dimension(900, 60));
        cardPanel2.setLayout(new java.awt.CardLayout());

        welcomeBar.setBackground(new java.awt.Color(33, 53, 80));
        welcomeBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        backButton.setBackground(new java.awt.Color(40, 64, 97));
        backButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("Back");
        backButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        backButton.setOpaque(true);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        welcomePageLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        welcomePageLabel.setForeground(new java.awt.Color(255, 255, 255));
        welcomePageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout welcomeBarLayout = new javax.swing.GroupLayout(welcomeBar);
        welcomeBar.setLayout(welcomeBarLayout);
        welcomeBarLayout.setHorizontalGroup(
            welcomeBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomeBarLayout.createSequentialGroup()
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 372, Short.MAX_VALUE)
                .addComponent(welcomePageLabel)
                .addContainerGap(421, Short.MAX_VALUE))
        );
        welcomeBarLayout.setVerticalGroup(
            welcomeBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomeBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addComponent(welcomePageLabel))
        );

        cardPanel2.add(welcomeBar, "welcomeBar");

        homeBar.setBackground(new java.awt.Color(33, 53, 80));
        homeBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        homeBar.setMaximumSize(new java.awt.Dimension(900, 60));
        homeBar.setMinimumSize(new java.awt.Dimension(900, 60));

        homeButton.setBackground(new java.awt.Color(40, 64, 97));
        homeButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        homeButton.setForeground(new java.awt.Color(255, 255, 255));
        homeButton.setText("Home");
        homeButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        homeButton.setMaximumSize(new java.awt.Dimension(103, 56));
        homeButton.setMinimumSize(new java.awt.Dimension(103, 56));
        homeButton.setOpaque(true);
        homeButton.setPreferredSize(new java.awt.Dimension(103, 56));
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });

        logOutButton.setBackground(new java.awt.Color(40, 64, 97));
        logOutButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        logOutButton.setForeground(new java.awt.Color(255, 255, 255));
        logOutButton.setText("Log out");
        logOutButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logOutButton.setMaximumSize(new java.awt.Dimension(103, 56));
        logOutButton.setMinimumSize(new java.awt.Dimension(103, 56));
        logOutButton.setOpaque(true);
        logOutButton.setPreferredSize(new java.awt.Dimension(103, 56));
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        pageLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        pageLabel.setForeground(new java.awt.Color(255, 255, 255));
        pageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout homeBarLayout = new javax.swing.GroupLayout(homeBar);
        homeBar.setLayout(homeBarLayout);
        homeBarLayout.setHorizontalGroup(
            homeBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeBarLayout.createSequentialGroup()
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 345, Short.MAX_VALUE)
                .addComponent(pageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 345, Short.MAX_VALUE)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        homeBarLayout.setVerticalGroup(
            homeBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(pageLabel))
        );

        cardPanel2.add(homeBar, "homeBar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cardPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(900, 700));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        String role = null;
        String userID = userIDField.getText();
        String password = passwordField.getText();

        //Check fields are not empty
        if (userID.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "Please insert data");
        } else {
            loggedInUser = controller.login(userID, password);
            if (loggedInUser != null) {

                role = loggedInUser.getRole();

                switch (role) {
                    case "Shift Manager":
                        System.out.println("Shift Manager");
                        usersMenuPageButton.setVisible(false);
                        manageCustomersMenuPageButton.setVisible(false);
                        break;
                    case "Technician":
                        System.out.println("Technician");
                        usersMenuPageButton.setVisible(false);
                        manageCustomersMenuPageButton.setVisible(false);
                        tasksMenuPageButton.setVisible(false);
                        settingsMenuPageButton.setVisible(false);
                        standardJobsMenuPageButton.setVisible(false);
                        reportsMenuPageButton.setVisible(false);
                        acceptPaymentPageButton.setVisible(false);
                        collectJobPageButton.setVisible(false);
                        acceptJobPageButton.setVisible(false);
                        break;
                    case "Receptionist":
                        System.out.println("Receptionist");
                        usersMenuPageButton.setVisible(false);
                        manageCustomersMenuPageButton.setVisible(false);
                        tasksMenuPageButton.setVisible(false);
                        settingsMenuPageButton.setVisible(false);
                        standardJobsMenuPageButton.setVisible(false);
                        reportsMenuPageButton.setVisible(false);
                        break;
                }

                card1.show(cardPanel1, "homePage");
                card2.show(cardPanel2, "homeBar");
                pageLabel.setText("Welcome, " + role + "!");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid User details");
            }

        }
        System.out.println(role);
    }//GEN-LAST:event_loginButtonActionPerformed

    private void loginPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginPageButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "login");
        backButton.setVisible(true);
        welcomePageLabel.setVisible(true);
        welcomePageLabel.setText("Login page");
    }//GEN-LAST:event_loginPageButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "welcome");
        backButton.setVisible(false);
        welcomePageLabel.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void RestorePageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RestorePageButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "restore");
        backButton.setVisible(true);
        welcomePageLabel.setVisible(true);
        welcomePageLabel.setText("Restore page");
    }//GEN-LAST:event_RestorePageButtonActionPerformed

    private void RestoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RestoreButtonActionPerformed
        // TODO add your handling code here:
        String[] restoreCmd = new String[]{"C:/Program Files/MySQL/MySQL Server 5.7/bin/mysql.exe", "--user=root", "--password=password", "-e", "source " + fileChosenField.getText()};

        if (fileChosenField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a file");
        } else {
            try {
                Runtime runtime = Runtime.getRuntime();
                //Process p = runtime.exec("C:/Program Files/MySQL/MySQL Server 5.7/bin/mysql.exe -uroot -ppassword bapers_data < \"" + fileChosenField.getText() + "\"");
                Process p = runtime.exec(restoreCmd);

                int processComplete = p.waitFor();
                if (processComplete == 0) {
                    JOptionPane.showMessageDialog(this, "Restore done");
                    fileChosenField.setText(null);
                    backButton.doClick();
                } else {
                    JOptionPane.showMessageDialog(this, "Restore failed");
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_RestoreButtonActionPerformed

    private void chooseFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFileButtonActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("sql file", "sql"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(this);

        try {
            String directory = chooser.getSelectedFile().getPath();
            directory = directory.replace('\\', '/');
            fileChosenField.setText(directory);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_chooseFileButtonActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "homePage");
        pageLabel.setText("Backup page");
        homeButton.setVisible(false);
        if (tblModel != null) {
            tblModel.setRowCount(0);
        }
        pageLabel.setText("Welcome, " + loggedInUser.getRole() + "!");
    }//GEN-LAST:event_homeButtonActionPerformed

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "welcome");
        card2.show(cardPanel2, "welcomeBar");
        for (Component c : homePage.getComponents()) {
            c.setVisible(true);
        }
        collectJobPageButton.setVisible(true);
        acceptJobPageButton.setVisible(true);
        backButton.setVisible(false);
        managerjPanel.setVisible(false);
        welcomePageLabel.setVisible(false);

        
        this.deleteJobTableInformation();
        controller.clearJob();
        this.deleteStandardJobTableInformation();
        controller.clearStandardJobList();
        this.deleteTaskEnquiryTableInformation();
        controller.clearTaskList();
        this.resetJobEnquiryTables();
        System.out.println("Logging out");

    }//GEN-LAST:event_logOutButtonActionPerformed

    private void backupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backupButtonActionPerformed
        // TODO add your handling code here:
        if (locationChosenField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a directory");
        } else {
            try {
                Runtime runtime = Runtime.getRuntime();
                String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                date = date.replace(' ', '_');
                date = date.replace(':', '-');
                String filename = "BAPERS_" + date + ".sql";
                Process p = runtime.exec("C:/Program Files/MySQL/MySQL Server 5.7/bin/mysqldump.exe -uroot -ppassword -B bapers_data -r \"" + locationChosenField.getText() + "\\" + filename + "\"");

                int processComplete = p.waitFor();
                if (processComplete == 0) {
                    JOptionPane.showMessageDialog(this, "Backup '" + filename + "' created");
                    locationChosenField.setText(null);
                    logOutButton.doClick();
                } else {
                    JOptionPane.showMessageDialog(this, "Backup failed");
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }//GEN-LAST:event_backupButtonActionPerformed

    private void chooseLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseLocationButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(this);

        try {
            String directory = chooser.getSelectedFile().getPath();
            directory = directory.replace('\\', '/');
            locationChosenField.setText(directory);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_chooseLocationButtonActionPerformed

    private void searchUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchUserButtonActionPerformed
        // TODO add your handling code here:
        String userNumber = UserNumberField.getText();
        String firstName = UserFirstnameField.getText();
        String lastName = UserLastnameField.getText();
        boolean valid = true;

        //regex needs to be recapped
        if (!userNumber.isEmpty()) {
            if (!Pattern.matches("(\\d)+", userNumber)) {
                JOptionPane.showMessageDialog(this, "User number: Numbers only");
                valid = false;
            } else if (userNumber.length() > 5) {
                JOptionPane.showMessageDialog(this, "User number: Cannot be longer than five digits");
                valid = false;
            }
        }
        if (!firstName.isEmpty()) {
            if (!Pattern.matches("(\\D)+", firstName)) {
                JOptionPane.showMessageDialog(this, "User firstname: letters only");
                valid = false;
            } else if (firstName.length() > 35) {
                JOptionPane.showMessageDialog(this, "User firstname: Cannot be longer than 35 characters");
                valid = false;
            }
        }
        if (!lastName.isEmpty()) {
            if (!Pattern.matches("(\\D)+", lastName)) {
                JOptionPane.showMessageDialog(this, "User lastname: letters only");
                valid = false;
            } else if (lastName.length() > 35) {
                JOptionPane.showMessageDialog(this, "User lastname: Cannot be longer than 35 characters");
                valid = false;
            }
        }

        if (valid) {
            ArrayList<UserDetails> users = controller.findUser(userNumber, firstName, lastName, (String) UserRoleSearchDrop.getSelectedItem());

            tblModel = (DefaultTableModel) userResultsTable.getModel();
            Object[] row = new Object[5];
            for (int i = 0; i < users.size(); i++) {
                row[0] = users.get(i).getAccount_no();
                row[1] = users.get(i).getFirstname();
                row[2] = users.get(i).getLastname();
                row[3] = users.get(i).getRole();
                row[4] = users.get(i).getDatetime();
                tblModel.insertRow(i, row);
            }
            card1.show(cardPanel1, "userResults");
            //card2.show(cardPanel2, "homeBar");
            pageLabel.setText("User results page");

            userResultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            //WORK FROM HERE. MAKE RESULTS PAGE, AND POPULATE IT WITH THE ARRAYLIST ABOVE
        }

    }//GEN-LAST:event_searchUserButtonActionPerformed

    private void UserRoleSearchDropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserRoleSearchDropActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserRoleSearchDropActionPerformed

    private void changeRoleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeRoleButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = userResultsTable.getSelectedRow();
        if (selectedRow != -1) {
            String[] choices = {"Office Manager", "Shift Manager", "Receptionist", "Technician"};
            String usersRole = (String) userResultsTable.getValueAt(selectedRow, 3);
            String selectedRole = (String) JOptionPane.showInputDialog(this, "Select a new role:", "Update user role", JOptionPane.QUESTION_MESSAGE, null, choices, choices[controller.getRoleID(usersRole) - 1]);
            if (selectedRole != null) {
                String outcome;
                int newRoleid = controller.getRoleID(selectedRole);
                if (controller.updateUserRole((int) userResultsTable.getValueAt(selectedRow, 0), newRoleid)) {
                    outcome = "Success";
                    tblModel.setValueAt(selectedRole, userResultsTable.getRowSorter().convertRowIndexToModel(userResultsTable.getSelectedRow()), 3);
                } else {
                    outcome = "Fail";
                }
                JOptionPane.showMessageDialog(this, outcome);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update");
        }
    }//GEN-LAST:event_changeRoleButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = userResultsTable.getSelectedRow(); // index 0 here
        if (selectedRow != -1) {
            int userId = (int) userResultsTable.getValueAt(selectedRow, 0);
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you would like to delete user with id " + userId + "?");

            if (response == 0) {
                String outcome;
                if (controller.deleteUser(userId)) {
                    outcome = "Success";
                    //we need the selected elements position relative to the model before the sort
                    tblModel.removeRow(userResultsTable.getRowSorter().convertRowIndexToModel(userResultsTable.getSelectedRow()));
                } else {
                    outcome = "Fail";
                }
                JOptionPane.showMessageDialog(this, outcome);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void searchAgainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAgainButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "userSearch");
        pageLabel.setText("User search page");
        tblModel.setRowCount(0);
    }//GEN-LAST:event_searchAgainButtonActionPerformed

    private void userFirstNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userFirstNameFieldActionPerformed

    }//GEN-LAST:event_userFirstNameFieldActionPerformed

    private void NewRepeatPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewRepeatPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewRepeatPasswordFieldActionPerformed

    private void NewPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPasswordFieldActionPerformed

    private void userRoleComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userRoleComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userRoleComboBoxActionPerformed

    private void createUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUserButtonActionPerformed
        //Initialise values
        boolean valid = true;
        String firstName = userFirstNameField.getText();
        String surname = userLastNameField.getText();
        String password = NewPasswordField.getText();
        String role = (String) userRoleComboBox.getSelectedItem();
        String username = usernameField.getText();
        int roleID = 0;

        //usernameField
        //Check first name field
        if (userFirstNameField.getText().length() > 10) {
            JOptionPane.showMessageDialog(this, "Name cannot be longer than 10 characters");
            valid = false;
            //Check fields are not empty
        }
        if (firstName.equals("") || surname.equals("") || password.equals("") || username.equals("")) {
            JOptionPane.showMessageDialog(this, "Please insert data");
            valid = false;
            //Check passwords match
        }
        if (!NewPasswordField.getText().equals(NewRepeatPasswordField.getText())) {
            JOptionPane.showMessageDialog(this, "Passwords do not match");
            //Insert pop up error
            valid = false;
            //Will only execute method in controller if all preconditions are met
        }
        if (controller.checkIfUsernameExists(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists");
            valid = false;
        }
        //Get RoleID
        roleID = controller.getRoleID(role);

        //Create technician
        if (role.equals("Technician") && valid) {
            Object[] possibilities = {"Copy Room", "Development area", "Packing Departments", "Finishing Room"};
            String technicianRoom = (String) JOptionPane.showInputDialog(null, "Select room", "Select Room", JOptionPane.PLAIN_MESSAGE, null, possibilities, "Copy Room");

            if (controller.createUser(firstName, surname, password, roleID, username)) {
                controller.createTechnician(controller.getUserID(password), controller.getDepartmentCode(technicianRoom));
                JOptionPane.showMessageDialog(this, "User created with id: " + controller.getUserID(password));
                homeButton.doClick();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create technician");
            }
        }

        //Create user other than technician
        if (!(role.equals("Technician"))) {
            if (controller.createUser(firstName, surname, password, roleID, username) && valid) {
                JOptionPane.showMessageDialog(this, "User created with id: " + controller.getUserID(password));
                homeButton.doClick();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create user");
            }
        }

    }//GEN-LAST:event_createUserButtonActionPerformed

    private void durationNewTaskDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_durationNewTaskDDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_durationNewTaskDDActionPerformed

    private void createNewTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewTaskButtonActionPerformed
        // TODO add your handling code here:

        //Initialise values to get the data from the GUI buttons
        String description = descriptionNewTaskField.getText();
        String price = priceNewTaskField.getText();
        int duration_min = Integer.parseInt((String) durationNewTaskMinsDD.getSelectedItem());
        int duration_hours = Integer.parseInt((String) durationNewTaskDD.getSelectedItem());
        String Department_department_code = (String) departmentNewTaskDD.getSelectedItem();
        int shelf_slot = Integer.parseInt((String) shelfSlotTaskDD.getSelectedItem());

        duration_min += duration_hours * 60;

        //Check fields are not empty in the GUI, then pop up will show if no data is inserted
        if (description.equals("") || !Pattern.matches("(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$", price)) {
            JOptionPane.showMessageDialog(null, "Please insert data");
        } else {
            if (controller.createNewTask(description, duration_min, Double.parseDouble(price), Department_department_code, shelf_slot)) {
                JOptionPane.showMessageDialog(null, "Task created");
                homeButton.doClick();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to create Task");
            }
        }
    }//GEN-LAST:event_createNewTaskButtonActionPerformed

    private void shelfSlotTaskDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shelfSlotTaskDDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shelfSlotTaskDDActionPerformed

    private void departmentNewTaskDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentNewTaskDDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_departmentNewTaskDDActionPerformed

    private void durationNewTaskMinsDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_durationNewTaskMinsDDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_durationNewTaskMinsDDActionPerformed

    private void tasksMenuPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tasksMenuPageButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "taskHomePage");
        homeButton.setVisible(true);
    }//GEN-LAST:event_tasksMenuPageButtonActionPerformed

    private void acceptPaymentPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptPaymentPageButtonActionPerformed
        // TODO add your handling code here:
        currentPage = "acceptPayment";
        card1.show(cardPanel1, "acceptPayment");
        homeButton.setVisible(true);
        pageLabel.setText("Accept payment page");
    }//GEN-LAST:event_acceptPaymentPageButtonActionPerformed

    private void jobMenuPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jobMenuPageButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "jobHomePage");
        homeButton.setVisible(true);
    }//GEN-LAST:event_jobMenuPageButtonActionPerformed

    private void reportsMenuPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsMenuPageButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "reportHomePage");
        homeButton.setVisible(true);
    }//GEN-LAST:event_reportsMenuPageButtonActionPerformed

    private void usersMenuPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersMenuPageButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "userHomePage");
        homeButton.setVisible(true);
    }//GEN-LAST:event_usersMenuPageButtonActionPerformed

    private void standardJobsMenuPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_standardJobsMenuPageButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "standardJobHomePage");
        homeButton.setVisible(true);
    }//GEN-LAST:event_standardJobsMenuPageButtonActionPerformed

    private void settingsMenuPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsMenuPageButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "settingsHomePage");
        homeButton.setVisible(true);
    }//GEN-LAST:event_settingsMenuPageButtonActionPerformed

    private void createUserPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUserPageButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "createUser");
        pageLabel.setText("User create page");
    }//GEN-LAST:event_createUserPageButtonActionPerformed

    private void backupPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backupPageButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "backup");
        pageLabel.setText("Backup data page");
    }//GEN-LAST:event_backupPageButtonActionPerformed

    private void manageUsersPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageUsersPageButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "userSearch");
        pageLabel.setText("Search User page");
        changeRoleButton.setVisible(true);
        deleteButton.setVisible(true);
        selectUserButton.setVisible(false);
    }//GEN-LAST:event_manageUsersPageButtonActionPerformed

    private void prefixjComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prefixjComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prefixjComboBoxActionPerformed


    private void createCustomerjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createCustomerjButtonActionPerformed
        // TODO add your handling code here:
        //NEED TO VALIDATE INPUT SIZE, AND POST CODE, PHONE NUMBER, ETC.  SEE CUSTOMER SEARCH FOR REGEX
//        for (Component c : createCustomerPage.getComponents()) {
//            if (emptyCheck(c))
//            {
//                break;
//            }
//        }

        // TODO add your handling code here:
        String cFirstName = firstNameField.getText();
        String cLastName = surnameField.getText();
        String accountHName = accountHolderNamejTextField.getText();
        String streetName = streetNameField.getText();
        String postCode = postCodeField.getText();
        String city = cityField.getText();
        String phone = phoneNumberField.getText();
        int prefixSelected = prefixjComboBox.getSelectedIndex();
        String buildingNo = buildingNumberField.getText();

        //regex needs to be recapped
        if (!Pattern.matches("(\\D){2,35}$", cFirstName)) {
            JOptionPane.showMessageDialog(this, "Please a firstname no longer than 35 characters");
        } else if (!Pattern.matches("(\\D){2,35}$", cLastName)) {
            JOptionPane.showMessageDialog(this, "Please a lastname no longer than 35 characters");
        } else if (accountHName.isEmpty() || accountHName.length() > 80) {
            JOptionPane.showMessageDialog(this, "Please enter an account holders name no longer than 80 characters");
        } else if (!Pattern.matches("(\\D){2,80}$", streetName)) {
            JOptionPane.showMessageDialog(this, "Please an account holders name no longer than 80 characters");
        } else if (!Pattern.matches("([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z]))))\\s?[0-9][A-Za-z]{2})", postCode)) {
            JOptionPane.showMessageDialog(this, "Poscode: Invalid format");
        } else if (!Pattern.matches("(\\D){2,60}$", city)) {
            JOptionPane.showMessageDialog(this, "City: letters only");
        } else if (!Pattern.matches("^(((\\+44\\s?\\d{4}|\\(?0\\d{4}\\)?)\\s?\\d{3}\\s?\\d{3})|((\\+44\\s?\\d{3}|\\(?0\\d{3}\\)?)\\s?\\d{3}\\s?\\d{4})|((\\+44\\s?\\d{2}|\\(?0\\d{2}\\)?)\\s?\\d{4}\\s?\\d{4}))(\\s?\\#(\\d{4}|\\d{3}))?$", phone)) {
            JOptionPane.showMessageDialog(this, "Phone: Invalid format");
        } else if (prefixSelected == 0) {
            JOptionPane.showMessageDialog(this, "Please select a name prefix");
        } else if (!Pattern.matches("(\\d){0,4}$", buildingNo)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid building number");
        } else {
            if (controller.createCustomerAccount(cFirstName, cLastName, accountHName, streetName, postCode, city, phone, (String) prefixjComboBox.getSelectedItem(), buildingNo)) {
                CustomerDetails customer = controller.getLastCustomer();
                customerInfoField.setText("Contact name: " + customer.getAccountHolderName() + ", ID: " + customer.getAccountNo());
                acceptJobPageButton.doClick();
            }
        }

    }//GEN-LAST:event_createCustomerjButtonActionPerformed

//    private boolean emptyCheck(Component c) {
    //        Boolean condition = false;
    //        
    //        if (c instanceof JTextField) {
    //            condition = ((JTextField) c).getText().isEmpty();
    //        } else if (c instanceof JComboBox) {
    //            condition = ((JComboBox) c).getSelectedIndex() == 0;
    //        }
    //
    //        if (condition) {
    //            JOptionPane.showMessageDialog(this, c.getName() + " cannot be empty");
    //            return true;
    //        }
    //        
    //        return false;
    //    }
    private void cancelCreationjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelCreationjButtonActionPerformed
        // TODO add your handling code here:
        // sets the fields that the user inserts to to null
        // returns user back to the accept job page
        currentPage = "acceptJob";
        acceptJobPageButton.doClick();

    }//GEN-LAST:event_cancelCreationjButtonActionPerformed

    private void firstNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameFieldActionPerformed

    private void buildingNumberFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildingNumberFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buildingNumberFieldActionPerformed

    private void cancelCustomerFJobjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelCustomerFJobjButtonActionPerformed
        // TODO add your handling code here:
        if (previousPage.equals("createReport")) {
            createReportPageButton.doClick();
        } else {
            acceptJobPageButton.doClick();
        }
    }//GEN-LAST:event_cancelCustomerFJobjButtonActionPerformed

    private void cityField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cityField1ActionPerformed

    private void addMaterialButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMaterialButtonActionPerformed
        // TODO add your handling code here:
        // checks to see if the user has entereds the correct format for materials
        // if so, it will add the inputted material into the arraylist
        // then update a new model which will be used to display the list of
        // materials.
        String material = materialsjTextField.getText();

        if ((!material.isEmpty()) && material.matches("^[a-zA-Z0-9]+[a-z,A-Z,0-9,\\s]*")) {

            materials.add(new Material(material));
            materialsjTextField.setText("");

            list1.addElement(materials.get(materials.size() - 1).getMaterialDescription());

            materialList.setModel(list1);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a material to be added.");
        }
    }//GEN-LAST:event_addMaterialButtonActionPerformed

    private void addJobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJobButtonActionPerformed
        // TODO add your handling code here:
        //selectStdJob.getSelectedItem();
        // adds a selected standard job to a list and arraylist

        int stdJobIndex = stdJobDD.getSelectedIndex();

        if (stdJobIndex != 0) {

            selectedStdJobs.add(stdJobs.get(stdJobIndex - 1));

            list2.addElement(selectedStdJobs.get(selectedStdJobs.size() - 1).getJobDescription());
            standardJobList.setModel(list2);
            StandardJob newstdJob = selectedStdJobs.get(selectedStdJobs.size() - 1);
            double total = Double.parseDouble(jobTotalField.getText()) + newstdJob.getPrice();
            jobTotalField.setText(String.format("%.2f", total));
            stdJobDD.setSelectedIndex(0);
            updateCompletionTimeSelection();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a standard job to be added.");
        }
    }//GEN-LAST:event_addJobButtonActionPerformed

    private void updateCompletionTimeSelection() {
        int totalDuration = 0;

        for (StandardJob s : selectedStdJobs) {
            totalDuration += s.getDurationMin();
        }

        System.out.println(totalDuration);

        List<String> duration = new ArrayList<>();
        duration.add("Select a time");

        for (int i = 165; i > totalDuration; i -= 15) {
            int hours = i / 60;
            int mins = i - (hours * 60);
            duration.add(hours + " hours " + mins + " mins");
        }

        durations = new String[duration.size()];
        durations = duration.toArray(durations);

        if (selectPriority.getSelectedIndex() == 3) {
            completionTimeDD.setModel(new javax.swing.DefaultComboBoxModel<>(durations));
        }
    }

    private void stdJobDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stdJobDDActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_stdJobDDActionPerformed

    private void selectPriorityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectPriorityActionPerformed
        // TODO add your handling code here:
        // if the selected list is Stipulated then the extra field needed to calculate
        // Stipulated are shown.
        String selectedItem = ((String) selectPriority.getSelectedItem());

        switch (selectedItem) {
            case "Stipulated":
                stipulatedFields.setVisible(true);
                completionTimeDD.setEnabled(true);
                completionTimeDD.setEditable(false);
                completionTimeDD.setSelectedIndex(0);
                completionTimeDD.setModel(new javax.swing.DefaultComboBoxModel<>(durations));

                break;
            case "Urgent":
                stipulatedFields.setVisible(true);
                completionTimeDD.setEnabled(false);
                completionTimeDD.setEditable(true);
                surchargejTextField.setText(100 + " %");
                completionTimeDD.setSelectedItem("3 hours 0 mins");

                break;
            default:
                completionTimeDD.setEditable(true);
                completionTimeDD.setSelectedItem("24 hours 0 mins");
                surchargejTextField.setText(0 + " %");
                stipulatedFields.setVisible(false);
                break;
        }

    }//GEN-LAST:event_selectPriorityActionPerformed

    private void searchCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCustomerButtonActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        previousPage = currentPage;
        currentPage = "searchCustomer";
        card1.show(cardPanel1, currentPage);
        pageLabel.setText("Search Customer page");
        if (loggedInUser.getRole().equals("Office Manager")) {
            managerjPanel.setVisible(true);
        }
    }//GEN-LAST:event_searchCustomerButtonActionPerformed

    private void createCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createCustomerButtonActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        previousPage = currentPage;
        currentPage = "createCustomer";
        card1.show(cardPanel1, currentPage);
        pageLabel.setText("Create Customer page");
    }//GEN-LAST:event_createCustomerButtonActionPerformed

    private void materialListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_materialListValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_materialListValueChanged

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // TODO add your handling code here:
        if (customerInfoField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a customer.");
        } else if (materials.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add material.");
        } else if (selectedStdJobs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add a standard job.");
        } else if (selectPriority.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a priority level.");
        } else if (selectPriority.getSelectedIndex() == 3 && completionTimeDD.getModel().getSize() == 1) {
            JOptionPane.showMessageDialog(this, "You cannot stipulate a deadline for this job");
        } else if (selectPriority.getSelectedIndex() == 3 && completionTimeDD.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a completion time");
        } else {
            String surcharge = (String) surchargejTextField.getText().replaceAll("[\\D]", "");
            double total = Double.parseDouble(jobTotalField.getText()) * ((Integer.parseInt(surcharge) / 100.0) + 1);
            int response = JOptionPane.showConfirmDialog(this, "Total : £" + String.format("%.2f", total) + " (including surcharge and VAT). Is the customer happy to proceed?");
            if (response == 0) {
                String[] parts = customerInfoField.getText().split("\\:");
                if (controller.acceptJob(parts[2], materials, selectedStdJobs, total, loggedInUser, specialInstructionjTextField.getText(), (String) completionTimeDD.getSelectedItem(), surcharge, (String) selectPriority.getSelectedItem())) {
                    JOptionPane.showMessageDialog(this, "Job created");
                    homeButton.doClick();
                }
            }

        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void materialsjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialsjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_materialsjTextFieldActionPerformed

    private void selectSelectedInvoicejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectSelectedInvoicejButtonActionPerformed
        // TODO add your handling code here:
        // gets the selected position from the invoice table
        int row = invoicejTable.getSelectedRow();
        int columnCount = invoicejTable.getColumnCount();

        Invoice invoice;
        Object[] obj = new Object[6];

        // gets all the row information from the selected invoice in the table
        // and places it in the array
        for (int i = 0; i < columnCount; ++i) {
            obj[i] = invoicejTable.getValueAt(row, i);
        }

        // variables getting the values and parsing them so that they are at the right type
        // to create a new invoice
        int invoiceNo = Integer.parseInt(obj[0].toString());
        int jobJobNo = Integer.parseInt(obj[1].toString());
        double totalPayable = Double.parseDouble(obj[2].toString());
        Date dateIssued = new Date(obj[3].toString());
        String invoiceStatus = obj[4].toString();
        String invoiceLocation = obj[5].toString();

        invoice = new Invoice(invoiceNo, jobJobNo, totalPayable, dateIssued, invoiceStatus, invoiceLocation);

        selectedInvoices.add(invoice); // adds the invoice to the arraylist

        // grabs all elements from arraylist and adds to a model object
        for (int i = 0; i < selectedInvoices.size(); ++i) {
            list1.addElement(selectedInvoices.get(i).getInvoiceLocation());
        }

        invoicejList.setModel(list1); // sets the model from the t typed model object

        TotalLatePayjTextField.setText(Double.toString(calculateTotal()));

        m.setRowCount(0); // clears the table since it will take the old values and
        // re-display when re-entered into the search invoice

        card1.show(cardPanel1, "acceptLatePayment");
        card2.show(cardPanel2, "acceptLatePaymentBar");
    }//GEN-LAST:event_selectSelectedInvoicejButtonActionPerformed

    public double calculateTotal() {
        //calculates the total amount needed to be paid based on the number and type of
        //invoices being selected.
        double totalResult = 0;
        for (int i = 0; i < selectedInvoices.size(); ++i) {
            totalResult += selectedInvoices.get(i).getTotalPayable();
        }
        return totalResult;
    }
    private void cancelInvoiceSeletionjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelInvoiceSeletionjButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "acceptLatePayment");
        card2.show(cardPanel2, "acceptLatePaymentBar");
    }//GEN-LAST:event_cancelInvoiceSeletionjButtonActionPerformed

    private void acceptJobPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptJobPageButtonActionPerformed
        // TODO add your handling code here:
        currentPage = "acceptJob";
        card1.show(cardPanel1, currentPage);
        pageLabel.setText("Accept Job page");
    }//GEN-LAST:event_acceptJobPageButtonActionPerformed

    private void createTaskPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTaskPageButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "createTask");
        pageLabel.setText("Create task page");
    }//GEN-LAST:event_createTaskPageButtonActionPerformed

    private void descriptionFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptionFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descriptionFieldActionPerformed

    private void codeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codeFieldActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        int taskIndex = selectATaskBox.getSelectedIndex();

        if (taskIndex != 0) {

            selectedTasks.add(tasks.get(taskIndex - 1));

            list2.addElement(selectedTasks.get(selectedTasks.size() - 1).getDescription());
            Task newTask = selectedTasks.get(selectedTasks.size() - 1);
            double total = Double.parseDouble(stdJobTotalField.getText()) + newTask.getPrice();
            stdJobTotalField.setText(String.format("%.2f", total));
            standardJobList1.setModel(list2);
            selectATaskBox.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to be added.");
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        //Initialise values
        String code = codeField.getText();
        String job_description = descriptionField.getText();
        String price = stdJobTotalField.getText();

        //Check fields are not empty
        if (job_description.equals("")) {
            JOptionPane.showMessageDialog(this, "Please insert data");
        } //check code field is not bigger than 6 characters
        else if (!Pattern.matches("\\b[a-zA-Z]{1,3}\\d{2,3}\\b", code)) {
            JOptionPane.showMessageDialog(this, "Code must be no longer than 6 characters. Must consist of letters followed by minimum 2 numbers.");
        } //check description field is not bigger than 45 characters
        else if (descriptionField.getText().length() > 45) {
            JOptionPane.showMessageDialog(this, "Description cannot be longer than 45 characters");
        } else if (selectedTasks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select tasks to be added.");
        } else {
            if (controller.createStandardJob(code, job_description, Double.parseDouble(price), selectedTasks)) {
                JOptionPane.showMessageDialog(this, "Standard Job created");
                homeButton.doClick();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create Standard Job");
            }
        }
    }//GEN-LAST:event_createButtonActionPerformed

    private void createSJobPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createSJobPageButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "createStandardJob");
        pageLabel.setText("Create Standard Job page");
    }//GEN-LAST:event_createSJobPageButtonActionPerformed

    private void searchAgainButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAgainButton1ActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "searchCustomer");
        pageLabel.setText("Customer search page");
    }//GEN-LAST:event_searchAgainButton1ActionPerformed

    private void selectCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCustomerButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = customerResultsTable.getSelectedRow(); // index 0 here
        if (selectedRow != -1) {
            String selectedCustomer = "Contact name: " + ((String) customerResultsTable.getValueAt(selectedRow, 1)) + ", ID: " + ((int) customerResultsTable.getValueAt(selectedRow, 0));
            if (previousPage.equals("createReport")) {
                createReportPageButton.doClick();
                infoField.setText(selectedCustomer);
            } else {
                acceptJobPageButton.doClick();
                customerInfoField.setText(selectedCustomer);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a customer");
        }
    }//GEN-LAST:event_selectCustomerButtonActionPerformed

    private void searchCustomerFJobjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCustomerFJobjButtonActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        String customerNumber = custAccountNoField.getText();
        String cFirstName = custFirstnameField.getText();
        String cLastName = custLastnameField.getText();
        String accountHName = custAccountHNameField.getText();
        String streetName = streetNameField1.getText();
        String postCode = postCodeField1.getText();
        String city = cityField1.getText();
        String phone = phoneField1.getText();
        String isValued = (String) customerTypeDD.getSelectedItem();
        String isSuspended = (String) accountStatusDD.getSelectedItem();
        String inDefault = (String) inDefaultDD.getSelectedItem();
        String regDate = customerRegDateField.getDateFormatString();
        boolean valid = true;

        //regex needs to be recapped
        if (!customerNumber.isEmpty()) {
            if (!Pattern.matches("(\\d)+", customerNumber)) {
                JOptionPane.showMessageDialog(this, "Customer number: Numbers only");
                valid = false;
            } else if (customerNumber.length() > 5) {
                JOptionPane.showMessageDialog(this, "Customer number: Cannot be longer than five digits");
                valid = false;
            }
        }
        if (!cFirstName.isEmpty()) {
            if (!Pattern.matches("(\\D)+", cFirstName)) {
                JOptionPane.showMessageDialog(this, "Customer contact firstname: letters only");
                valid = false;
            } else if (cFirstName.length() > 35) {
                JOptionPane.showMessageDialog(this, "Customer contact firstname: Cannot be longer than 35 characters");
                valid = false;
            }
        }
        if (!cLastName.isEmpty()) {
            if (!Pattern.matches("(\\D)+", cLastName)) {
                JOptionPane.showMessageDialog(this, "Customer contact lastname: letters only");
                valid = false;
            } else if (cLastName.length() > 35) {
                JOptionPane.showMessageDialog(this, "User contact lastname: Cannot be longer than 35 characters");
                valid = false;
            }
        }
        if (!accountHName.isEmpty() && accountHName.length() > 80) {
            JOptionPane.showMessageDialog(this, "Account holders name: Cannot be longer than 80 characters");
            valid = false;
        }
        if (!streetName.isEmpty()) {
            if (!Pattern.matches("(\\D)+", streetName)) {
                JOptionPane.showMessageDialog(this, "Customer contact lastname: letters only");
                valid = false;
            } else if (streetName.length() > 80) {
                JOptionPane.showMessageDialog(this, "Street name: Cannot be longer than 80 characters");
                valid = false;
            }
        }
        if (!postCode.isEmpty()) {
            if (!Pattern.matches("([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z]))))\\s?[0-9][A-Za-z]{2})", postCode)) {
                JOptionPane.showMessageDialog(this, "Poscode: Invalid format");
                valid = false;
            }
        }

        if (!city.isEmpty()) {
            if (!Pattern.matches("(\\D)+", city)) {
                JOptionPane.showMessageDialog(this, "City: letters only");
                valid = false;
            } else if (city.length() > 60) {
                JOptionPane.showMessageDialog(this, "City: Cannot be longer than 60 characters");
                valid = false;
            }
        }

        if (!phone.isEmpty()) {
            if (!Pattern.matches("^(((\\+44\\s?\\d{4}|\\(?0\\d{4}\\)?)\\s?\\d{3}\\s?\\d{3})|((\\+44\\s?\\d{3}|\\(?0\\d{3}\\)?)\\s?\\d{3}\\s?\\d{4})|((\\+44\\s?\\d{2}|\\(?0\\d{2}\\)?)\\s?\\d{4}\\s?\\d{4}))(\\s?\\#(\\d{4}|\\d{3}))?$", phone)) {
                JOptionPane.showMessageDialog(this, "Phone: Invalid format");
                valid = false;
            }
        }
        if (valid) {
            ArrayList<CustomerDetails> customers = controller.findCustomer(customerNumber, cFirstName, cLastName, accountHName, streetName, postCode, city, phone, isValued, isSuspended, inDefault, regDate);

            tblModel = (DefaultTableModel) customerResultsTable.getModel();
            Object[] row = new Object[5];
            for (int i = 0; i < customers.size(); i++) {
                row[0] = customers.get(i).getAccountNo();
                row[1] = customers.get(i).getAccountHolderName();
                System.out.println(row[1]);
                row[2] = customers.get(i).getPrefix() + " " + customers.get(i).getFirstName() + " " + customers.get(i).getLastName();
                row[3] = customers.get(i).getCustomerType();
                row[4] = customers.get(i).getCustomerStatus();
                tblModel.insertRow(i, row);
            }
            card1.show(cardPanel1, "customerResults");
            //card2.show(cardPanel2, "homeBar");
            pageLabel.setText("Customer results page");

            customerResultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            //WORK FROM HERE. MAKE RESULTS PAGE, AND POPULATE IT WITH THE ARRAYLIST ABOVE
        }

    }//GEN-LAST:event_searchCustomerFJobjButtonActionPerformed

    private void loginPageComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_loginPageComponentHidden
        // TODO add your handling code here:
        resetComponents(loginPage);
    }//GEN-LAST:event_loginPageComponentHidden

    private void standardJobListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_standardJobListValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_standardJobListValueChanged

    private void removeJobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeJobButtonActionPerformed
//        // TODO add your handling code here:
        int index = standardJobList.getSelectedIndex();
        if (index != -1) {
            list2.remove(index);
            standardJobList.setModel(list2);
            double total = Double.parseDouble(jobTotalField.getText()) - selectedStdJobs.get(index).getPrice();
            jobTotalField.setText(String.format("%.2f", total));
            selectedStdJobs.remove(index);

            updateCompletionTimeSelection();
        } else {
            JOptionPane.showMessageDialog(this, "You need to select a standard job to remove.");
        }
    }//GEN-LAST:event_removeJobButtonActionPerformed

    private void removeMaterialButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMaterialButtonActionPerformed
        // TODO add your handling code here:
        int index = materialList.getSelectedIndex();
        if (index != -1) {
            list1.remove(index);
            materialList.setModel(list1);
        } else {
            JOptionPane.showMessageDialog(this, "You need to select a material to remove.");
        }
    }//GEN-LAST:event_removeMaterialButtonActionPerformed

    private void acceptJobPageComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_acceptJobPageComponentShown
        // TODO add your handling code here:
        if (jobTotalField.getText().equals("")) {
            jobTotalField.setText("0");
        }
        stdJobs = controller.getStandardJobs();

        String[] stdJobList = new String[stdJobs.size() + 1];

        stdJobList[0] = "Select a standard job.";
        for (int i = 0; i < stdJobs.size(); i++) {
            stdJobList[i + 1] = stdJobs.get(i).getJobDescription();
        }

        stdJobDD.setModel(new javax.swing.DefaultComboBoxModel<>(stdJobList));
    }//GEN-LAST:event_acceptJobPageComponentShown

    private void completionTimeDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completionTimeDDActionPerformed
        // TODO add your handling code here:
        if (selectPriority.getSelectedIndex() == 3) {
            int surcharge = (completionTimeDD.getSelectedIndex() * 5) + 100;
            surchargejTextField.setText(Integer.toString(surcharge) + " %");
        }
    }//GEN-LAST:event_completionTimeDDActionPerformed

    private void customerResultsPageComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_customerResultsPageComponentHidden
        // TODO add your handling code here:
        resetComponents(customerResultsPage);
        if (tblModel != null) {
            tblModel.setRowCount(0);
        }
    }//GEN-LAST:event_customerResultsPageComponentHidden

    private void acceptJobPageComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_acceptJobPageComponentHidden
        // TODO add your handling code here:
        System.out.println("hidden");
        if (!currentPage.equals("searchCustomer") && !currentPage.equals("createCustomer")) {
            resetComponents(acceptJobPage);
            list1.clear();
            materials.clear();
            list2.clear();
            selectedStdJobs.clear();
        }
    }//GEN-LAST:event_acceptJobPageComponentHidden

    private void searchCustomerPageComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_searchCustomerPageComponentHidden
        // TODO add your handling code here:
        resetComponents(receptionistjPanel);
        resetComponents(managerjPanel);
    }//GEN-LAST:event_searchCustomerPageComponentHidden

    private void createCustomerPageComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_createCustomerPageComponentHidden
        // TODO add your handling code here:
        resetComponents(createCustomerPage);
    }//GEN-LAST:event_createCustomerPageComponentHidden

    private void standardJobListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_standardJobListPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_standardJobListPropertyChange

    private void createStandardJobPageComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_createStandardJobPageComponentShown
        // TODO add your handling code here:
        if (stdJobTotalField.getText().equals("")) {
            stdJobTotalField.setText("0");
        }

        tasks = controller.getTasks();

        String[] taskList = new String[tasks.size() + 1];

        taskList[0] = "Select a task.";
        for (int i = 0; i < tasks.size(); i++) {
            taskList[i + 1] = tasks.get(i).getDescription();
        }

        selectATaskBox.setModel(new javax.swing.DefaultComboBoxModel<>(taskList));
    }//GEN-LAST:event_createStandardJobPageComponentShown

    private void standardJobList1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_standardJobList1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_standardJobList1PropertyChange

    private void standardJobList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_standardJobList1ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_standardJobList1ValueChanged

    private void removeTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTaskButtonActionPerformed
        // TODO add your handling code here:
        int index = standardJobList1.getSelectedIndex();
        if (index != -1) {
            list2.remove(index);
            standardJobList1.setModel(list2);
            double total = Double.parseDouble(stdJobTotalField.getText()) - selectedTasks.get(index).getPrice();
            stdJobTotalField.setText(String.format("%.2f", total));
            selectedTasks.remove(index);
        } else {
            JOptionPane.showMessageDialog(this, "You need to select a task to remove.");
        }
    }//GEN-LAST:event_removeTaskButtonActionPerformed

    private void createStandardJobPageComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_createStandardJobPageComponentHidden
        // TODO add your handling code here:
        resetComponents(createStandardJobPage);
        selectedTasks.clear();
        list2.clear();
    }//GEN-LAST:event_createStandardJobPageComponentHidden

    private void createTaskPageComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_createTaskPageComponentHidden
        // TODO add your handling code here:
        resetComponents(createTaskPage);
    }//GEN-LAST:event_createTaskPageComponentHidden

    private void createUserPageComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_createUserPageComponentHidden
        // TODO add your handling code here:
        resetComponents(createUserPage);
    }//GEN-LAST:event_createUserPageComponentHidden

    private void reportTypeDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportTypeDDActionPerformed
        // TODO add your handling code here:
        int selected = reportTypeDD.getSelectedIndex();

        switch (selected) {
            case 3:
                searchPanel.setVisible(true);
                infoField.setText("Select customer...");
                break;
            case 1:
                searchPanel.setVisible(true);
                infoField.setText("Select user... (optional)");
                break;
            default:
                searchPanel.setVisible(false);
                break;
        }
    }//GEN-LAST:event_reportTypeDDActionPerformed

    private void createReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createReportButtonActionPerformed
        // TODO add your handling code here:
        int reportIndex = reportTypeDD.getSelectedIndex();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String info = infoField.getText();

        if (reportIndex == 0) {
            JOptionPane.showMessageDialog(this, "Please select a report type");
        } else if (reportStartPeriod.getDate() == null || reportEndPeriod.getDate() == null || !reportEndPeriod.getDate().after(reportStartPeriod.getDate())) {
            JOptionPane.showMessageDialog(this, "Please enter a valid report peroid");
        } else if (reportIndex == 3 && info.equals("Select customer...")) {
            JOptionPane.showMessageDialog(this, "Please select a customer");
        } else {
            String startDate = sdf.format(reportStartPeriod.getDate());
            String finishDate = sdf.format(reportEndPeriod.getDate());
            String[] reportPeriod = new String[]{startDate, finishDate};
            ArrayList<Object[][]> objects = controller.createReport(reportIndex, reportPeriod, info);
            Object[][] o;

            if ((objects.isEmpty()) || (objects.get(0) == null)) {
                JOptionPane.showMessageDialog(this, "No data found!");
            } else {
                switch (reportIndex) {
                    case 1:
                        tblModel = (DefaultTableModel) jTable5.getModel();
                        o = objects.get(0);
                        for (int x = 0; x < o.length; x++) {
                            tblModel.insertRow(x, o[x]);
                        }
                        iPLabel.setText("Period : " + startDate + " - " + finishDate);
                        currentPage = "individualReport";
                        pageLabel.setText("Individual Performance Report");
                        break;
                    case 2:
                        String shift = "";
                        int sumCR = 0;
                        int sumDA = 0;
                        int sumFR = 0;
                        int sumPD = 0;
                        for (int i = 0; i < objects.size(); i++) {
                            o = objects.get(i);
                            if (o != null) {
                                switch (i) {
                                    case 0:
                                        shift = "Day Shift 1";
                                        tblModel = (DefaultTableModel) jTable1.getModel();
                                        break;
                                    case 1:
                                        shift = "Day Shift 2";
                                        tblModel = (DefaultTableModel) jTable2.getModel();
                                        break;
                                    case 2:
                                        shift = "Night Shift 1";
                                        tblModel = (DefaultTableModel) jTable3.getModel();
                                        break;
                                    default:
                                        break;
                                }

                                for (int x = 0; x < o.length; x++) {
                                    tblModel.insertRow(x, o[x]);

                                }

                                tblModel = (DefaultTableModel) jTable4.getModel();
                                tblModel.addRow(new Object[]{shift, o[o.length - 1][1], o[o.length - 1][2], o[o.length - 1][3], o[o.length - 1][4]});

                                if (!((Integer) o[o.length - 1][1] == null)) {
                                    sumCR += (Integer) o[o.length - 1][1];
                                    sumDA += (Integer) o[o.length - 1][2];
                                    sumFR += (Integer) o[o.length - 1][3];
                                    sumPD += (Integer) o[o.length - 1][4];
                                }
                            }
                        }

                        tblModel = (DefaultTableModel) jTable4.getModel();
                        tblModel.addRow(new Object[]{"Total", sumCR, sumDA, sumFR, sumPD});
                        periodLabel.setText("For period : (" + startDate + " - " + finishDate + ")");
                        summaryReportLabel.setText("Period : " + startDate + " - " + finishDate);
                        pageLabel.setText("Summary Performance Report");
                        currentPage = "summaryReport";
                        break;
                    case 3:
                        tblModel = (DefaultTableModel) jTable6.getModel();
                        o = objects.get(0);
                        for (int x = 0; x < o.length; x++) {
                            tblModel.insertRow(x, o[x]);
                        }
                        cPLabel.setText("Period : " + startDate + " - " + finishDate);
                        currentPage = "customerReport";
                        pageLabel.setText("Customer Report");
                        break;
                    default:
                        break;
                }

                card1.show(cardPanel1, currentPage);
            }
        }
    }//GEN-LAST:event_createReportButtonActionPerformed

    private void createReportPageComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_createReportPageComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_createReportPageComponentHidden

    private void createReportPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createReportPageButtonActionPerformed
        // TODO add your handling code here:
        currentPage = "createReport";
        card1.show(cardPanel1, currentPage);
        pageLabel.setText("Create report page");
    }//GEN-LAST:event_createReportPageButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        int selected = reportTypeDD.getSelectedIndex();

        previousPage = currentPage;

        switch (selected) {
            case 3:
                currentPage = "searchCustomer";
                card1.show(cardPanel1, currentPage);
                pageLabel.setText("Search Customer page");
                if (loggedInUser.getRole().equals("Office Manager")) {
                    managerjPanel.setVisible(true);
                }
                break;
            case 1:
                currentPage = "userSearch";
                changeRoleButton.setVisible(false);
                deleteButton.setVisible(false);
                selectUserButton.setVisible(true);
                card1.show(cardPanel1, currentPage);
                pageLabel.setText("Search User page");

                break;
            default:
                break;
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void selectUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectUserButtonActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        int selectedRow = userResultsTable.getSelectedRow(); // index 0 here
        if (selectedRow != -1) {
            String selectedUser = "User name: " + ((String) userResultsTable.getValueAt(selectedRow, 1)) + " " + ((String) userResultsTable.getValueAt(selectedRow, 2)) + ", ID: " + ((int) userResultsTable.getValueAt(selectedRow, 0));
            if (previousPage.equals("createReport")) {
                createReportPageButton.doClick();
                infoField.setText(selectedUser);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user");
        }
    }//GEN-LAST:event_selectUserButtonActionPerformed

    private void userResultsTableComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_userResultsTableComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_userResultsTableComponentHidden

    private void userResultsPageComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_userResultsPageComponentHidden
        // TODO add your handling code here:
        if (tblModel != null) {
            tblModel.setRowCount(0);
        }
    }//GEN-LAST:event_userResultsPageComponentHidden

    private void searchUserButtonComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_searchUserButtonComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_searchUserButtonComponentHidden

    private void customerResultsTableComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_customerResultsTableComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_customerResultsTableComponentMoved

    private void cancelSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelSearchButtonActionPerformed
        // TODO add your handling code here:
        if (previousPage.equals("createReport")) {
            createReportPageButton.doClick();
        } else {
            usersMenuPageButton.doClick();
        }
    }//GEN-LAST:event_cancelSearchButtonActionPerformed

    private void summaryReportPageDataComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_summaryReportPageDataComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_summaryReportPageDataComponentHidden

    private void reportBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBackButtonActionPerformed
        // TODO add your handling code here:
        createReportPageButton.doClick();
    }//GEN-LAST:event_reportBackButtonActionPerformed

    private void printButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButton1ActionPerformed
        // TODO add your handling code here:       
        printComponenet(summaryReportPage);
    }//GEN-LAST:event_printButton1ActionPerformed

    public void printComponenet(Component component) {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(" Print Component ");

        pj.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                component.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        if (pj.printDialog() == false) {
            return;
        }

        try {
            pj.print();
        } catch (PrinterException ex) {
            // handle exception
        }
    }
    private void summaryReportPageComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_summaryReportPageComponentHidden
        // TODO add your handling code here:
        if (tblModel != null) {
            tblModel.setRowCount(0);
        }
    }//GEN-LAST:event_summaryReportPageComponentHidden

    private void reportBackButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBackButton1ActionPerformed
        // TODO add your handling code here:
        createReportPageButton.doClick();
    }//GEN-LAST:event_reportBackButton1ActionPerformed

    private void printButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButton2ActionPerformed
        // TODO add your handling code here:
        printComponenet(individualReportPage);
    }//GEN-LAST:event_printButton2ActionPerformed

    private void individualReportPageComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_individualReportPageComponentHidden
        // TODO add your handling code here:
        if (tblModel != null) {
            tblModel.setRowCount(0);
        }
    }//GEN-LAST:event_individualReportPageComponentHidden

    private void reportBackButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBackButton2ActionPerformed
        // TODO add your handling code here:
        createReportPageButton.doClick();
    }//GEN-LAST:event_reportBackButton2ActionPerformed

    private void printButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButton3ActionPerformed
        // TODO add your handling code here:
        printComponenet(customerReportPage);
    }//GEN-LAST:event_printButton3ActionPerformed

    private void customerReportPageComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_customerReportPageComponentHidden
        // TODO add your handling code here:
        if (tblModel != null) {
            tblModel.setRowCount(0);
        }
    }//GEN-LAST:event_customerReportPageComponentHidden

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void selectSelectedInvoicejButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectSelectedInvoicejButton1ActionPerformed
        // TODO add your handling code here:
        if (invoicejTable.getSelectedRow() >= 0) {
            t.clear();
            // gets the selected position from the invoice table
            final int row = invoicejTable.getSelectedRow();
            final int columnCount = invoicejTable.getColumnCount();

            Object[] obj = new Object[6];

            // gets all the row information from the selected invoice in the table
            // and places it in the array
            for (int i = 0; i < columnCount; ++i) {
                obj[i] = invoicejTable.getValueAt(row, i);
            }

            // variables for invoice
            final int invoiceNo = Integer.parseInt(obj[0].toString());
            final int jobJobNo = Integer.parseInt(obj[1].toString());
            double totalPayable = Double.parseDouble(obj[2].toString());
            final Date dateIssued = (Date) obj[3];
            final String invoiceStatus = obj[4].toString();
            final String invoiceLocation = obj[5].toString();

            invoice = new Invoice(invoiceNo, jobJobNo, totalPayable, dateIssued, invoiceStatus, invoiceLocation);

            // sets the informaiton needed in the acceptlatepayment page
            t.addElement("Invoice number:" + invoice.getInvoiceNo());
            t.addElement("Job number: " + invoice.getJobJobNo());
            invoicejList.setModel(t); // sets the model from the t typed model object
            TotalLatePayjTextField.setText("£ " + Double.toString(invoice.getTotalPayable()));
            invoiceModelTable.removeRow(invoicejTable.getSelectedRow());

            card1.show(cardPanel1, "acceptPayment");
            pageLabel.setText("Accept payment page");
        } else {
            JOptionPane.showMessageDialog(this, "You need to select a record");
        }
    }//GEN-LAST:event_selectSelectedInvoicejButton1ActionPerformed

    private void cancelInvoiceSeletionjButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelInvoiceSeletionjButton1ActionPerformed
        // TODO add your handling code here:
        previousPage = currentPage;
        currentPage = "acceptPayment";

        searchInvoiceByInvoiceNojTextField.setText("");
        searchInvoiceByJobNumberjTextField.setText("");
        invoiceModelTable.setRowCount(0);

        card1.show(cardPanel1, "acceptPayment");
        pageLabel.setText("Accept payment page");
    }//GEN-LAST:event_cancelInvoiceSeletionjButton1ActionPerformed

    private void searchInvoiceByInvoiceNojTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInvoiceByInvoiceNojTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInvoiceByInvoiceNojTextFieldActionPerformed

    private void searchInvoiceByInvoiceNojTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchInvoiceByInvoiceNojTextFieldKeyReleased
        // TODO add your handling code here:
        final String inputText = searchInvoiceByInvoiceNojTextField.getText();
        final DefaultTableModel table = (DefaultTableModel) invoicejTable.getModel();
        final TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(table);
        invoicejTable.setRowSorter(tr);
        if (inputText.length() != 0) {
            tr.setRowFilter(RowFilter.regexFilter(inputText, 0));
        } else {
            tr.removeRowSorterListener(invoicejTable);
        }
    }//GEN-LAST:event_searchInvoiceByInvoiceNojTextFieldKeyReleased

    private void searchInvoiceByJobNumberjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInvoiceByJobNumberjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInvoiceByJobNumberjTextFieldActionPerformed

    private void searchInvoiceByJobNumberjTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchInvoiceByJobNumberjTextFieldKeyReleased
        // TODO add your handling code here:
        final String inputText = searchInvoiceByJobNumberjTextField.getText();
        final DefaultTableModel table = (DefaultTableModel) invoicejTable.getModel();
        final TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(table);
        invoicejTable.setRowSorter(tr);
        if (inputText.length() != 0) {
            tr.setRowFilter(RowFilter.regexFilter(inputText, 1));
        } else {
            tr.removeRowSorterListener(invoicejTable);
        }
    }//GEN-LAST:event_searchInvoiceByJobNumberjTextFieldKeyReleased

    private void currentAutoBackupModeDatajTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentAutoBackupModeDatajTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentAutoBackupModeDatajTextFieldActionPerformed

    private void selectAutoBackupLocationjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAutoBackupLocationjButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(this);

        try {
            String directory = chooser.getSelectedFile().getPath();
            directory = directory.replace('\\', '/');
            autoBackupLocationjTextField.setText(directory);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_selectAutoBackupLocationjButtonActionPerformed

    private void confirmAutoBackupConfigjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmAutoBackupConfigjButtonActionPerformed
        // TODO add your handling code here:
        if (!autoBackupLocationjTextField.getText().equals("")) {
            final String mode = backupModejComboBox.getSelectedItem().toString();
            final String frequency = backupFrequencyjComboBox.getSelectedItem().toString();
            final String location = autoBackupLocationjTextField.getText();

            final AutoBackupConfig config = new AutoBackupConfig(mode, frequency, location);

            controller.setAutoBackupConfig(config);
            autoBackupLocationjTextField.setText("");
            JOptionPane.showMessageDialog(this, "You have updated the automatic backup configuration");
            card1.show(cardPanel1, "homePage");
            pageLabel.setText("Welcome, " + loggedInUser.getRole() + "!");
        } else {
            JOptionPane.showMessageDialog(this, "You need to pick a location");
        }
    }//GEN-LAST:event_confirmAutoBackupConfigjButtonActionPerformed

    private void currentAutoBackupFrequencyDatajTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentAutoBackupFrequencyDatajTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentAutoBackupFrequencyDatajTextFieldActionPerformed

    private void autoBackupLocationjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoBackupLocationjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_autoBackupLocationjTextFieldActionPerformed

    private void currentAutoBackupLocationDatajTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentAutoBackupLocationDatajTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentAutoBackupLocationDatajTextFieldActionPerformed

    private void discountPlanTypejComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountPlanTypejComboBoxActionPerformed
        // TODO add your handling code here:
        if (discountPlanTypejComboBox.getSelectedItem().equals("Fixed")) {
            FixedDiscountType.setVisible(true);
            VariableDiscountType.setVisible(false);
            FlexiableDiscountType.setVisible(false);
        } else if (discountPlanTypejComboBox.getSelectedItem().equals("Variable")) {
            displayAllTask();
            VariableDiscountType.setVisible(true);
            FixedDiscountType.setVisible(false);
            FlexiableDiscountType.setVisible(false);
        } else if (discountPlanTypejComboBox.getSelectedItem().equals("Flexible")) {
            FlexiableDiscountType.setVisible(true);
            VariableDiscountType.setVisible(false);
            FixedDiscountType.setVisible(false);
        } else {
            FixedDiscountType.setVisible(false);
            VariableDiscountType.setVisible(false);
            FlexiableDiscountType.setVisible(false);
        }
    }//GEN-LAST:event_discountPlanTypejComboBoxActionPerformed

    public void displayAllTask() {
        taskModelTable = (DefaultTableModel) TaskjTable.getModel();
        taskModelTable.setRowCount(0);

        final ArrayList<Task> taskList = controller.getAllTasks();
        final int SIZE = taskList.size();

        Object rowData[] = new Object[3];
        for (int i = 0; i < SIZE; ++i) {
            rowData[0] = taskList.get(i).getTaskId();
            rowData[1] = taskList.get(i).getDescription();
            rowData[2] = taskList.get(i).getPrice();
            //adds the array type object to the table by adding it to the model
            taskModelTable.addRow(rowData);
        }
    }

    private void discountCanceljButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountCanceljButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountCanceljButtonActionPerformed

    private void applyDiscountjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyDiscountjButtonActionPerformed
        // TODO add your handling code here:
        final String discountType = discountPlanTypejComboBox.getSelectedItem().toString();
        final CustomerDetails customer = selectedCustomer;

        switch (discountType) {
            case "Fixed":
                System.out.println("Fixed");
                final int fixedDiscountPercentage = Integer.parseInt(fixedDiscountRatejTextField.getText());
                controller.applyDiscountPlan(customer, loggedInUser, discountType);
                controller.applyFixedDiscountRate(fixedDiscountPercentage, customer);
                break;

            case "Variable":
                System.out.println("Variable");

                break;

            case "Flexible":
                System.out.println("Flexible");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_applyDiscountjButtonActionPerformed

    private void fixedDiscountRatejTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fixedDiscountRatejTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fixedDiscountRatejTextFieldActionPerformed

    private void upperBoundjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upperBoundjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_upperBoundjTextFieldActionPerformed

    private void lowerBoundjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lowerBoundjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lowerBoundjTextFieldActionPerformed

    private void flexibleDiscountRatejTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flexibleDiscountRatejTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_flexibleDiscountRatejTextFieldActionPerformed

    private void removeFlexibleBoundjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFlexibleBoundjButtonActionPerformed
        // TODO add your handling code here:
        boundsjList.remove(boundsjList.getSelectedIndex());
    }//GEN-LAST:event_removeFlexibleBoundjButtonActionPerformed

    private void addFlexibleBoundjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFlexibleBoundjButtonActionPerformed
        // TODO add your handling code here:
        upperBoundjTextField.getText();
        lowerBoundjTextField.getText();
        flexibleDiscountRatejTextField.getText();

    }//GEN-LAST:event_addFlexibleBoundjButtonActionPerformed

    private void selectCustomerjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCustomerjButtonActionPerformed
        // TODO add your handling code here:
        final String getCustomerNo = customersjTable.getValueAt(customersjTable.getSelectedRow(), 0).toString();
        final int customerNo = Integer.parseInt(getCustomerNo);
        final CustomerDetails[] customerList = controller.getAllCustomers();
        for (int i = 0; i < customerList.length; ++i) {
            if (customerNo == customerList[i].getAccountNo()) {
                selectedCustomer = customerList[i];
                break;
            }
        }

        try {
            updateCustomerInfo(selectedCustomer);

        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        card1.show(cardPanel1, "ViewCustomerDetail");
        pageLabel.setText("Customer: " + selectedCustomer.getAccountHolderName());
    }//GEN-LAST:event_selectCustomerjButtonActionPerformed

    public void updateCustomerInfo(CustomerDetails selectedCustomer) throws SQLException {
        CustomerDetails refreshedCustomerDetailList = controller.getSpecificCustomer(selectedCustomer);
        if (refreshedCustomerDetailList != null) {
            customerAccHolderNamejLabel.setText(refreshedCustomerDetailList.getAccountHolderName());
            customerPrefixjLabel.setText(refreshedCustomerDetailList.getPrefix());
            customerFirstNamejLabel.setText(refreshedCustomerDetailList.getFirstName());
            customerLastNamejLabel.setText(refreshedCustomerDetailList.getLastName());
            customerNumberjLabel.setText(refreshedCustomerDetailList.getPhoneNumber());
            customerRegistationDatejLabel.setText(refreshedCustomerDetailList.getRegDate().toString());

            if (refreshedCustomerDetailList.getIsSuspended() == true) {
                customerStatusjTextField.setText("Suspended");
            } else {
                customerStatusjTextField.setText("Not Suspended");
            }

            if (refreshedCustomerDetailList.getIsValued() == true) {
                customerTypejTextField.setText("Valued");
            } else {
                customerTypejTextField.setText("Standard");
            }

            if (refreshedCustomerDetailList.getInDefault() == true) {
                cutomerInDefaultjTextField.setText("true");
            } else {
                cutomerInDefaultjTextField.setText("false");
            }

            final boolean hasDiscount = controller.checkCustomerHasDiscountPlan(refreshedCustomerDetailList);
            if (hasDiscount) {
                final String discountType = controller.getCustomerDiscountType(refreshedCustomerDetailList);
                customerDiscountjTextField.setText(discountType);
            } else {
                customerDiscountjTextField.setText("None");
            }

            customerStreetNamejTextField.setText(refreshedCustomerDetailList.getStreetName());
            customerPostcodejTextField.setText(refreshedCustomerDetailList.getPostCode());
            customerCityjTextField.setText(refreshedCustomerDetailList.getCity());
            customerBuildingNojTextField.setText(Integer.toString(refreshedCustomerDetailList.getBuildingNo()));
        }
    }

    private void assignDiscountPlanjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignDiscountPlanjButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "DiscountPlan");
        card2.show(cardPanel2, "homePageR");
    }//GEN-LAST:event_assignDiscountPlanjButtonActionPerformed

    private void selectInvoicejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectInvoicejButtonActionPerformed
        // TODO add your handling code here:
        previousPage = currentPage;
        currentPage = "searchInvoicePage";

        // updates the tables for selecting invoices
        invoiceModelTable = (DefaultTableModel) invoicejTable.getModel(); // grabs the current model
        invoiceModelTable.setRowCount(0);
        ArrayList<Invoice> invoicesList;
        try {
            // initialse invoicesList arraylist with data from controller
            invoicesList = controller.getInvoices();

            Object rowData[] = new Object[6];
            for (int i = 0; i < invoicesList.size(); ++i) {
                rowData[0] = invoicesList.get(i).getInvoiceNo();
                rowData[1] = invoicesList.get(i).getJobJobNo();
                rowData[2] = invoicesList.get(i).getTotalPayable();
                rowData[3] = invoicesList.get(i).getDateIssued();
                rowData[4] = invoicesList.get(i).getInvoiceStatus().toString().toLowerCase();
                rowData[5] = invoicesList.get(i).getInvoiceLocation();
                //adds the array type object to the table by adding it to the model
                invoiceModelTable.addRow(rowData);
            }

            // change page
            card1.show(cardPanel1, "searchInvoicePage");
            pageLabel.setText("Search Invoice page");

        } catch (ParseException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_selectInvoicejButtonActionPerformed

    private void paymentTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentTypeComboBoxActionPerformed
        // TODO add your handling code here:
        // if the field that has been selected is card then the label will display
        // extra information needed to record card payment. Otherwise the fields
        // will not show.
        if (paymentTypeComboBox.getSelectedItem().toString().equals("Card")) {
            // shows the labels for card payment
            cardTypejLabel.setVisible(true);
            expiryDatejLabel.setVisible(true);
            last4DigitjLabel.setVisible(true);

            // shows the text field for card payment
            cardTypejComboBox.setVisible(true);
            expiryDatejTextField.setVisible(true);
            last4DigitjTextField.setVisible(true);
        } else {
            // hides the labels for card payment
            cardTypejLabel.setVisible(false);
            expiryDatejLabel.setVisible(false);
            last4DigitjLabel.setVisible(false);

            // hides the text field for card payment
            cardTypejComboBox.setVisible(false);
            expiryDatejTextField.setVisible(false);
            last4DigitjTextField.setVisible(false);
        }
    }//GEN-LAST:event_paymentTypeComboBoxActionPerformed

    private void latePaymentSubmitjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_latePaymentSubmitjButtonActionPerformed
        // TODO add your handling code here:
        if (invoicejList.getModel().getSize() != 0) { // first check to see if there is a invoice selected

            final String paymentNo = "null";
            final String[] paymentToken = TotalLatePayjTextField.getText().split("\\s");
            final double total = Double.parseDouble(paymentToken[1]);
            final String paymentType = paymentTypeComboBox.getSelectedItem().toString();
            final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            final String paymentDate = dateFormat.format(new Date());
            final int invoiceNumber = invoice.getInvoiceNo();

            switch (paymentTypeComboBox.getSelectedItem().toString()) {
                case "Card":
                    if (// checks to see if format for the card info is entered correctly
                            expiryDatejTextField.getText().matches("[0-9]{4}[/]{1}[0-9]{2}[/]{1}[0-9]{2}")
                            && last4DigitjTextField.getText().matches("[0-9]{4}")) {
                        // card information
                        final String cardType = cardTypejComboBox.getSelectedItem().toString();
                        final String cardDetailsLast4digits = last4DigitjTextField.getText();
                        final String cardDetailsExpiryDate = expiryDatejTextField.getText();

                        final Card card = new Card(cardDetailsLast4digits, cardType, cardDetailsExpiryDate);

                        final Payment paymentRecord = new PaymentCard(
                                paymentNo,
                                total,
                                paymentType,
                                paymentDate,
                                invoiceNumber,
                                card.getCardType(),
                                card.getLast4Digits(),
                                card.getExpiryDate()
                        );

                        controller.recordPayment(paymentRecord, paymentType, invoice, card);
                        //System.out.println("payment info attained");

                        // clears the model and the total
                        TotalLatePayjTextField.setText("");
                        t.clear();

                        // clears the data for card detials
                        expiryDatejTextField.setText("");
                        last4DigitjTextField.setText("");

                        JOptionPane.showMessageDialog(this, "Card payment successful");
                    }
                    break;
                case "Cash":
                    final Payment paymentRecord = new PaymentCash(
                            paymentNo,
                            total,
                            paymentType,
                            paymentDate,
                            invoiceNumber
                    );

                    controller.recordPayment(paymentRecord, paymentType, invoice);

                    // clears the model and the total
                    TotalLatePayjTextField.setText("");
                    t.clear();

                    // clears the data for card detials
                    expiryDatejTextField.setText("");
                    last4DigitjTextField.setText("");

                    JOptionPane.showMessageDialog(this, "Cash payment successful");
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "No payment type was selected");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Cannot make payment");
        }
    }//GEN-LAST:event_latePaymentSubmitjButtonActionPerformed

    private void backupSettingsPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backupSettingsPageButtonActionPerformed
        // TODO add your handling code here:
        if (controller.checkAutoBackupConfigExist() == true) {
            currentAutoBackupModeDatajTextField.setText(controller.getAutoBackupConfigData().getBackupMode());
            currentAutoBackupFrequencyDatajTextField.setText(controller.getAutoBackupConfigData().getBackupFrequency());
            currentAutoBackupLocationDatajTextField.setText(controller.getAutoBackupConfigData().getBackupLocation());

            autoBackupLocationjTextField.setText(configData.getBackupLocation());
        } else {
            currentAutoBackupModeDatajTextField.setText("None");
            currentAutoBackupFrequencyDatajTextField.setText("None");
            currentAutoBackupLocationDatajTextField.setText("None");
        }
        card1.show(cardPanel1, "AutoBackupConfig");
        pageLabel.setText("Backup settings page");
    }//GEN-LAST:event_backupSettingsPageButtonActionPerformed

    private void manageCustomersMenuPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageCustomersMenuPageButtonActionPerformed
        // TODO add your handling code here:
        customerModelTable = (DefaultTableModel) customersjTable.getModel();
        customerModelTable.setRowCount(0);

        final CustomerDetails[] customerList = controller.getAllCustomers();

        Object rowData[] = new Object[4];
        for (int i = 0; i < customerList.length; ++i) {
            rowData[0] = customerList[i].getAccountNo();
            rowData[1] = customerList[i].getAccountHolderName();
            rowData[2] = customerList[i].getFirstName();
            rowData[3] = customerList[i].getLastName();
            //adds the array type object to the table by adding it to the model
            customerModelTable.addRow(rowData);
        }
        card1.show(cardPanel1, "SelectCustomer");
        homeButton.setVisible(true);
        pageLabel.setText("Manage customer page");
    }//GEN-LAST:event_manageCustomersMenuPageButtonActionPerformed

    private void acceptPaymentComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_acceptPaymentComponentHidden
        // TODO add your handling code here:

        if (!currentPage.equals("searchInvoicePage")) {
            resetComponents(acceptPayment);
            t.clear();
        }
    }//GEN-LAST:event_acceptPaymentComponentHidden

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (selectedCustomer.getInDefault() && selectedCustomer.getIsSuspended()) {
            controller.reactivateDefaultAccount(selectedCustomer, 0);
        }
        try {
            updateCustomerInfo(selectedCustomer);

        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        controller.deleteAccount(selectedCustomer);
        selectedCustomer = null;
        JOptionPane.showMessageDialog(this, "You have deleted customer account");
        card1.show(cardPanel1, "homePage");
        pageLabel.setText("Welcome, " + loggedInUser.getRole() + "!");
    }//GEN-LAST:event_jButton2ActionPerformed

    /*
    
    Joseph
     */

    private void jobEnquiryTechnicianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jobEnquiryTechnicianActionPerformed
        card1.show(cardPanel1, "jobEnquiryPage");
    }//GEN-LAST:event_jobEnquiryTechnicianActionPerformed

    private void manageTasksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageTasksButtonActionPerformed
        //Get task information from controller class
        updateTaskTable();
        card1.show(cardPanel1, "manageTasksPage");

    }//GEN-LAST:event_manageTasksButtonActionPerformed

    private void createTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTaskButtonActionPerformed
        card1.show(cardPanel1, "createNewTask");
    }//GEN-LAST:event_createTaskButtonActionPerformed

    private void jobReceptionistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jobReceptionistActionPerformed
        card1.show(cardPanel1, "searchJobPage");
    }//GEN-LAST:event_jobReceptionistActionPerformed

    private void acceptPaymentReceptionistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptPaymentReceptionistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acceptPaymentReceptionistActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        card1.show(cardPanel1, "manageTasksPage");
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        //Initialise values:
        boolean valid = true;
        String taskID = taskIDText.getText();
        String description = descriptionText.getText();
        String department = (String) departmentComboBox.getSelectedItem();
        String shelfSlot = (shelfSlotText.getText());
        String price = (priceText.getText());
        //String is used for price and shelfSlot but will be converted to int/double when checking values below

        //Preconditions:
        if (description.equals("") || department.equals("")) {
            valid = false;
            JOptionPane.showMessageDialog(null, "Please insert data");
        }
        //Check shelf slot value is numeric
        try {
            Integer.parseInt(shelfSlot);
        } catch (NumberFormatException e) {
            valid = false;
            JOptionPane.showMessageDialog(null, "Enter numeric values for shelfSlot");
        }
        //Check if price value is numeric
        try {
            Double.parseDouble(price);
        } catch (NumberFormatException e) {
            valid = false;
            JOptionPane.showMessageDialog(null, "Enter numeric values for price");
        }
        //Check if task is numeric
        try {
            Integer.parseInt(price);
        } catch (NumberFormatException e) {
            valid = false;
            JOptionPane.showMessageDialog(null, "Enter numeric values for Task ID");
        }

        //Save new values for task
        if (valid) {

            if (controller.doesTaskExist(taskID)) {
                if (controller.updateTask(Integer.parseInt(taskID), description, Integer.parseInt(shelfSlot), Double.parseDouble(price), department)) {
                    JOptionPane.showMessageDialog(null, "Failed to update task");
                } else {
                    JOptionPane.showMessageDialog(null, "Task Updated");
                    //Update Task table
                    deleteTaskTableInformation();
                    updateTaskTable();
                    card1.show(cardPanel1, "manageTasksPage");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Task does not exist");
            }
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.deleteTaskTableInformation();
        card1.show(cardPanel1, "taskPage");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed

        //Get row index
        if ((taskTable.getSelectedRow() < 0)) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        } else {
            //Set task id
            taskIDText.setText(String.valueOf(taskTable.getModel().getValueAt(taskTable.getSelectedRow(), 0)));
            card1.show(cardPanel1, "editTaskPage");
        }


    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButton1ActionPerformed

        int taskID = -1;

        if ((taskTable.getSelectedRow() >= 0)) {
            //Get task id from table
            taskID = (Integer) taskTable.getModel().getValueAt(taskTable.getSelectedRow(), 0);
            //Delete task from table

            if (!controller.deleteTask(taskID)) {
                //Update new Task table
                deleteTaskTableInformation();
                updateTaskTable();
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row");
            }
        }
    }//GEN-LAST:event_deleteButton1ActionPerformed

    private void searchCustomerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCustomerBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchCustomerBtnActionPerformed

    private void reminderLettersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reminderLettersButtonActionPerformed
        //  this.updateReminderLettersTable();
        card1.show(cardPanel1, "reminderLettersTablePage");
        //Update reminder letters table
    }//GEN-LAST:event_reminderLettersButtonActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        card1.show(cardPanel1, "officeManagerHomePage");
        card2.show(cardPanel2, "homePageOMBar");
    }//GEN-LAST:event_backBtnActionPerformed

    private void backCustomerPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backCustomerPageButtonActionPerformed
        this.deleteReminderLettersTableInformation();
        card1.show(cardPanel1, "officeManagerCustomerPage");
    }//GEN-LAST:event_backCustomerPageButtonActionPerformed

    private void viewReminderLetterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReminderLetterButtonActionPerformed
        // TODO add your handling code here:
          if (reminderLettersTable.getSelectedRow() >= 0) {

            try {
                Document document = new Document();
                try {
                    PdfWriter.getInstance(document, new FileOutputStream("ReminderLetter.pdf"));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                document.open();
                document.add(new Paragraph("Invoice reminder"));
                document.add(new Paragraph("  "));
                PdfPTable pdfTable = new PdfPTable(reminderLettersTable.getColumnCount());
                //adding table headers
                for (int i = 0; i < reminderLettersTable.getColumnCount(); i++) {
                    pdfTable.addCell(reminderLettersTable.getColumnName(i));
                }

                //Convert date to string
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                //Add rows to table
                String date = dateFormat.format(controller.getInvoices().get(reminderLettersTable.getSelectedRow()).getDate());
                pdfTable.addCell(controller.getInvoices().get(reminderLettersTable.getSelectedRow()).getCustomerName());
                pdfTable.addCell(String.valueOf(controller.getInvoices().get(reminderLettersTable.getSelectedRow()).getInvoiceNumber()));
                pdfTable.addCell(date);
                pdfTable.addCell(String.valueOf(controller.getInvoices().get(reminderLettersTable.getSelectedRow()).getAmountDue()));

                document.add(pdfTable);
                document.add(new Paragraph("According to our records, "
                    + "it appears that we have not yet received payment of the above invoice,"
                    + "for photographic work done in our laboratory. "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("We would appreciate payment at your earliest convenience."));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("If you have already sent a payment to us recently, please accept our apologies. "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Yours sincereley,"));
            document.add(new Paragraph("   G. Lancaster "));
            document.close();
            JOptionPane.showMessageDialog(null, "PDF Sucessfully created");
        } catch (DocumentException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }//GEN-LAST:event_viewReminderLetterButtonActionPerformed

    private void resetJobEnquiryTables() {
        //Update task table
        this.deleteTaskEnquiryTableInformation();
        this.updateTaskEnquiryTable(standardJobCodeLabel.getText());
        //Update standard job table
        this.deleteStandardJobTableInformation();
        this.updateStandardJobTable();
        //Update job table
        this.deleteJobEnquiryTable();
        this.updateJobEnquiryTable();
    }
    private void updateTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateTaskButtonActionPerformed
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String currentTime = sdf.format(d);

        int taskID = -1;
        String standardJobStatusBeforeUpdatingTask = String.valueOf(standardJobResults.getModel().getValueAt(Integer.parseInt(standardJobIndexLabel.getText()), 2));
        String jobStatusBeforeUpdatingStandardJob = String.valueOf(jobEnquiryTableResults.getModel().getValueAt(Integer.parseInt(jobIndexLabel.getText()), 4));

        //Get row index and check row has values
        if ((taskResultsTable.getSelectedRow() >= 0) && taskResultsTable.getModel().getValueAt(taskResultsTable.getSelectedRow(), 0) != null) {
            taskID = (Integer) (taskResultsTable.getModel().getValueAt(taskResultsTable.getSelectedRow(), 0));
            if (controller.doesTechnicianHaveAccess(loggedInUser.getAccount_no(), standardJobCodeLabel.getText(), Integer.parseInt(jobNumberLabel.getText()), taskID)) {

                Object[] possibilities = {"Completed", "In progress", "Not started"};
                String taskStatus = (String) JOptionPane.showInputDialog(null, "Update Task ID: " + taskID, "Task Update", JOptionPane.PLAIN_MESSAGE, null, possibilities, "In progress");

                controller.updateTaskStatusInDatabase(taskStatus, taskResultsTable.getSelectedRow(), currentTime);
                controller.setTechnicianWhoCompletedTask(loggedInUser.getAccount_no(), standardJobCodeLabel.getText(), Integer.parseInt(jobNumberLabel.getText()), taskID);

            } else {
                JOptionPane.showMessageDialog(null, "You do not have access to change this task");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }

        //If standard job has been changed from not started to in progress then update the start time of that standard job
        if (controller.checkIfStandardJobIsInProgress(standardJobCodeLabel.getText(), Integer.parseInt(jobNumberLabel.getText()))
                && standardJobStatusBeforeUpdatingTask.equals("Not started")) {

            //Mark standard job as in progress and record the time it started
            controller.updateStandardJobStatus(Integer.parseInt(jobNumberLabel.getText()), Integer.parseInt(standardJobIndexLabel.getText()), "In progress", currentTime);
            JOptionPane.showMessageDialog(null, "Standard job is now in progress, the start time for " + standardJobCodeLabel.getText() + " is " + currentTime);
            //If job has been changed from not started to in progress then update the start time of that job
            if (controller.checkIfJobIsInProgress(Integer.parseInt(jobNumberLabel.getText())) && jobStatusBeforeUpdatingStandardJob.equals("Not started")) {
                //Mark job as in progress and record the time it started
                controller.updateJobStatus("In progress", Integer.parseInt(jobIndexLabel.getText()), currentTime);
                JOptionPane.showMessageDialog(null, "Job is now in progress, the start time for " + jobNumberLabel.getText() + " is " + currentTime);
            }

        }

        //Check if all tasks have been completed
        if (controller.checkIfAllTasksAreCompleted(standardJobCodeLabel.getText(), Integer.parseInt(jobNumberLabel.getText()))) {
            //Update standard job finish time and status
            controller.updateStandardJobStatus(Integer.parseInt(jobNumberLabel.getText()), Integer.parseInt(standardJobIndexLabel.getText()), "Completed", currentTime);
            //Set technician who completed standardjob
            JOptionPane.showMessageDialog(null, "All tasks have been completed, the finish time for " + standardJobCodeLabel.getText() + " is " + currentTime);

            //Check if all standard jobs have been completed
            if (controller.checkIfAllStandardJobsAreCompleted(Integer.parseInt(jobNumberLabel.getText()))) {
                //Update job status
                controller.updateJobStatus("Completed", Integer.parseInt(jobIndexLabel.getText()), currentTime);
                JOptionPane.showMessageDialog(null, "Job has now been completed, the finish time for " + jobNumberLabel.getText() + " is " + currentTime);
            }
        } else {
            //If standard job is in progress and all tasks are not completed then mark standard job as in progress 
            if (controller.checkIfStandardJobIsInProgress(standardJobCodeLabel.getText(), Integer.parseInt(jobNumberLabel.getText()))) {
                controller.overrideStandardJobStatus(Integer.parseInt(jobNumberLabel.getText()), Integer.parseInt(standardJobIndexLabel.getText()), "In progress");
                //Mark job as in progress
                controller.overrideJobStatus("In progress", Integer.parseInt(jobIndexLabel.getText()));
            }
        }

        //Change standard job from in progress to not started if all tasks are listed as not started
        if (standardJobStatusBeforeUpdatingTask.equals("In progress")
                && !(controller.checkIfStandardJobIsInProgress(standardJobCodeLabel.getText(), Integer.parseInt(jobNumberLabel.getText())))) {
            controller.overrideStandardJobStatus(Integer.parseInt(jobNumberLabel.getText()), Integer.parseInt(standardJobIndexLabel.getText()), "Not started");
        }
        //Change standard job from completed to not started if all tasks are listed as not started
        if (standardJobStatusBeforeUpdatingTask.equals("Completed")
                && !(controller.checkIfStandardJobIsInProgress(standardJobCodeLabel.getText(), Integer.parseInt(jobNumberLabel.getText())))) {
            controller.overrideStandardJobStatus(Integer.parseInt(jobNumberLabel.getText()), Integer.parseInt(standardJobIndexLabel.getText()), "Not started");
        }

        //Change standard job from completed to not started if all tasks are listed as not started
        if (jobStatusBeforeUpdatingStandardJob.equals("Completed")
                && !(controller.checkIfJobIsInProgress(Integer.parseInt(jobNumberLabel.getText())))) {
            controller.overrideJobStatus("Not started", Integer.parseInt(jobIndexLabel.getText()));
        }
        //Change standard job from completed to not started if all tasks are listed as not started
        if (jobStatusBeforeUpdatingStandardJob.equals("In progress")
                && !(controller.checkIfJobIsInProgress(Integer.parseInt(jobNumberLabel.getText())))) {
            controller.overrideJobStatus("Not started", Integer.parseInt(jobIndexLabel.getText()));
        }

        //Reset task/standardjob/job tables
        this.resetJobEnquiryTables();

    }//GEN-LAST:event_updateTaskButtonActionPerformed

    private void taskEnquiryBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taskEnquiryBackButtonActionPerformed
        this.deleteTaskEnquiryTableInformation();
        controller.clearTaskList();
        card1.show(cardPanel1, "standardJobSearchResultsJobEnquiryPage");
    }//GEN-LAST:event_taskEnquiryBackButtonActionPerformed

    private void searchJobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJobButtonActionPerformed
        boolean valid = true;
        String jobNumber = jobNumberTextField.getText();

        if (jobNumber.equals("")) {
            valid = false;
            JOptionPane.showMessageDialog(null, "Enter a job number");
        }

        if (valid) {
            if (controller.doesJobExist(jobNumber, true)) {
                //Get job information from controller
                controller.searchAllJobsUnderCriteria(Integer.parseInt(jobNumber));
                //Update table
                updateCollectJobTable();
                //Show job enquiry results page
                card1.show(cardPanel1, "jobSearchResultsPage");
            } else {
                JOptionPane.showMessageDialog(null, "Job number does not exist");
            }
        }
    }//GEN-LAST:event_searchJobButtonActionPerformed

    private void searchCustomerButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCustomerButton1ActionPerformed
        boolean valid = true;
        String customerNumber = customerNumberText.getText();

        if (customerNumber.equals("")) {
            valid = false;
            JOptionPane.showMessageDialog(null, "Enter a customer number");
        }
        if (valid) {
            if (controller.doesJobExist(customerNumber, false)) {
                controller.addJob(customerNumber);
                updateCollectJobTable();
                card1.show(cardPanel1, "jobSearchResultsPage");
            } else {
                JOptionPane.showMessageDialog(null, "Customer number does not exist");
            }

        }
    }//GEN-LAST:event_searchCustomerButton1ActionPerformed

    private void backRecpHPButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backRecpHPButtonActionPerformed
        card1.show(cardPanel1, "receptionistHomePage");
    }//GEN-LAST:event_backRecpHPButtonActionPerformed

    private void searchJobNumberJobEnquiryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJobNumberJobEnquiryButtonActionPerformed
        boolean valid = true;
        String jobNumber = jobNumberText.getText();

        if (jobNumber.equals("")) {
            valid = false;
            JOptionPane.showMessageDialog(null, "Enter a job number");
        }
        if (valid) {
            if (controller.doesJobExist(jobNumber, true)) {
                controller.searchAllJobsUnderCriteria(Integer.parseInt(jobNumber));
                //update table
                this.updateJobEnquiryTable();
                card1.show(cardPanel1, "jobEnquirySearchResultsPage");

            } else {
                JOptionPane.showMessageDialog(null, "Job number does not exist");
            }

        }
    }//GEN-LAST:event_searchJobNumberJobEnquiryButtonActionPerformed

    private void searchJobEnquiryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJobEnquiryButtonActionPerformed
        String status = (String) jobStatusComboBox.getSelectedItem();
        String priority = (String) jobPriorityComboBox.getSelectedItem();
        String collected = (String) jobCollectedComboBox.getSelectedItem();

        boolean valid = true;

        //Check if user wants to search for a collected job in progress
        if (collected.equals("Yes") && status.equals("In progress")) {
            JOptionPane.showMessageDialog(null, "Invalid search criteria");
            valid = false;
        }

        int collectedValue;
        if (collected.equals("No")) {
            collectedValue = 0;
        } else {
            collectedValue = 1;
        }

        if (valid) {
            if (controller.doesJobUnderJobCriteriaExist(status, priority, collectedValue)) {
                controller.getListOfJobNumbers(status, priority, collectedValue);
                this.updateJobEnquiryTable();
                card1.show(cardPanel1, "jobEnquirySearchResultsPage");
            } else {
                JOptionPane.showMessageDialog(null, "No jobs exists");
            }
        }
    }//GEN-LAST:event_searchJobEnquiryButtonActionPerformed

    private void viewJobEnquiryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewJobEnquiryButtonActionPerformed

        int jobNumber = -1;
        //Get row index
        if ((jobEnquiryTableResults.getSelectedRow() >= 0)) {
            //Check row has values
            if (jobEnquiryTableResults.getModel().getValueAt(jobEnquiryTableResults.getSelectedRow(), 0) != null) {

                //Get job Number from table
                jobNumber = (Integer) jobEnquiryTableResults.getModel().getValueAt(jobEnquiryTableResults.getSelectedRow(), 0);
                jobNumberLabel.setText(Integer.toString(jobNumber));
                jobIndexLabel.setText(String.valueOf(jobEnquiryTableResults.getSelectedRow()));

                //Get standard jobs from job number
                controller.getStandardJobsFromJobNumber(jobNumber);

                //Update standard job table
                this.updateStandardJobTable();
                //Show standard job search results
                card1.show(cardPanel1, "standardJobSearchResultsJobEnquiryPage");
            } else {
                JOptionPane.showMessageDialog(null, "Job is still in progress");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }

    }//GEN-LAST:event_viewJobEnquiryButtonActionPerformed

    private void backJobEnquiryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJobEnquiryButtonActionPerformed
        controller.clearJob();
        controller.clearStandardJobTasks();
        this.deleteJobEnquiryTable();
        card1.show(cardPanel1, "jobEnquiryPage");
    }//GEN-LAST:event_backJobEnquiryButtonActionPerformed

    private void backButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButonActionPerformed
        this.deleteJobTableInformation();
        controller.clearJob();
        card1.show(cardPanel1, "searchJobPage");
    }//GEN-LAST:event_backButonActionPerformed

    private void collectJobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collectJobButtonActionPerformed

        int jobNumber = -1;
        //Get row index
        if ((jobSearchResultsTable.getSelectedRow() < 0)) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        } else {
            //Check row has values and job is completed and not collected
            if ((jobSearchResultsTable.getModel().getValueAt(jobSearchResultsTable.getSelectedRow(), 3).equals("Completed"))
                    && (jobSearchResultsTable.getModel().getValueAt(jobSearchResultsTable.getSelectedRow(), 4).toString().equals("false"))) {

                //Get job Number from table
                jobNumber = (Integer) jobSearchResultsTable.getModel().getValueAt(jobSearchResultsTable.getSelectedRow(), 0);

                //Check if customer is valued
                if (controller.isCustomerValued(jobNumber)) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to pay later?");
                    if (dialogResult == JOptionPane.NO_OPTION) {
                        //Parse to payment page with invoice number
                        System.out.println("Payment page... invoice number is " + controller.getInvoiceNumber(jobNumber) + ", job number is: " + jobNumber);
                        //Insert code for job payment here ...
                    }
                } else {
                    System.out.println("Payment page... invoice number is " + controller.getInvoiceNumber(jobNumber) + ", job number is: " + jobNumber);
                    //Insert code for job payment here ...
                }
            } else {
                JOptionPane.showMessageDialog(null, "Job cannot be collected");
            }
        }

    }//GEN-LAST:event_collectJobButtonActionPerformed

    private void viewStandardJobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewStandardJobButtonActionPerformed

        String standardJobCode = "";
        //Get row index
        if ((standardJobResults.getSelectedRow() >= 0)) {
            //Check row has values
            if (standardJobResults.getModel().getValueAt(standardJobResults.getSelectedRow(), 0) != null) {

                //Get job Number from table
                standardJobCode = (String) standardJobResults.getModel().getValueAt(standardJobResults.getSelectedRow(), 0);
                //Set title text
                standardJobCodeLabel.setText(standardJobCode);

                //Get task information
                controller.getTasksFromStandardJobCode(standardJobCode, Integer.parseInt(jobNumberLabel.getText()));
                //Update standard job table
                this.updateTaskEnquiryTable(standardJobCode);
                //Show standard job search results
                card1.show(cardPanel1, "taskSearchResultsJobEnquiryPage");

                //Set standard Job index
                int standardJobIndex = standardJobResults.getSelectedRow();
                standardJobIndexLabel.setText(Integer.toString(standardJobIndex));
            } else {
                JOptionPane.showMessageDialog(null, "Job is still in progress");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }

    }//GEN-LAST:event_viewStandardJobButtonActionPerformed

    private void backJobSearchResultsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJobSearchResultsButtonActionPerformed
        this.deleteStandardJobTableInformation();
        controller.clearStandardJobList();
        card1.show(cardPanel1, "jobEnquirySearchResultsPage");
    }//GEN-LAST:event_backJobSearchResultsButtonActionPerformed

    private void manageTasksPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageTasksPageButtonActionPerformed
        updateTaskTable();
        card1.show(cardPanel1, "manageTasksPage");
    }//GEN-LAST:event_manageTasksPageButtonActionPerformed

    private void collectJobPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collectJobPageButtonActionPerformed
        updateCollectJobTable();
        card1.show(cardPanel1, "searchJobPage");
    }//GEN-LAST:event_collectJobPageButtonActionPerformed

    private void jobEnquiryPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jobEnquiryPageButtonActionPerformed
        card1.show(cardPanel1, "jobEnquiryPage");
    }//GEN-LAST:event_jobEnquiryPageButtonActionPerformed

    private void cancelJobEnquiryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelJobEnquiryButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelJobEnquiryButtonActionPerformed

    private void deleteTaskTableInformation() {
        DefaultTableModel taskTableModel = (DefaultTableModel) taskTable.getModel();
        taskTableModel.setRowCount(0);
    }

    private void deleteJobTableInformation() {
        DefaultTableModel jobTableModel = (DefaultTableModel) jobSearchResultsTable.getModel();
        jobTableModel.setRowCount(0);
    }

    private void deleteJobEnquiryTable() {
        DefaultTableModel jobTableModel = (DefaultTableModel) jobEnquiryTableResults.getModel();
        jobTableModel.setRowCount(0);
    }

    private void deleteStandardJobTableInformation() {
        DefaultTableModel standardJobTableModel = (DefaultTableModel) standardJobResults.getModel();
        standardJobTableModel.setRowCount(0);
    }

    private void deleteTaskEnquiryTableInformation() {
        DefaultTableModel taskTableModel = (DefaultTableModel) taskResultsTable.getModel();
        taskTableModel.setRowCount(0);
    }

    private void deleteReminderLettersTableInformation() {
        DefaultTableModel reminderLettersTableModel = (DefaultTableModel) reminderLettersTable.getModel();
        reminderLettersTableModel.setRowCount(0);
    }

    private void updateTaskEnquiryTable(String standardJobCode) {
        DefaultTableModel taskTableModel = (DefaultTableModel) taskResultsTable.getModel();
        //taskTableModel.setRowCount(tasks.size());

        //Insert task information into table
        Object[] row = new Object[5];
        for (int i = 0; i < controller.getTaskList().size(); i++) {
            row[0] = controller.getTaskList().get(i).getTaskID();
            row[1] = controller.getTaskList().get(i).getDescription();
            row[2] = controller.getTaskList().get(i).getDepartmentCode();
            row[3] = controller.getTaskList().get(i).getShelfSlot();
            row[4] = controller.getTaskList().get(i).getStatus();
            //taskTableModel.insertRow(0, row);
            taskTableModel.addRow(row);
        }
    }

    /*private void updateReminderLettersTable() {
        DefaultTableModel reminderLettersTableModel = (DefaultTableModel) reminderLettersTable.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < controller.getInvoices().size(); i++) {
            row[0] = controller.getInvoices().get(i).getCustomerName();
            row[1] = controller.getInvoices().get(i).getInvoiceNumber();
            row[2] = controller.getInvoices().get(i).getDate();
            row[3] = controller.getInvoices().get(i).getAmountDue();
            reminderLettersTableModel.addRow(row);
        }
    }*/
    private void updateStandardJobTable() {

        DefaultTableModel standardJobTableModel = (DefaultTableModel) standardJobResults.getModel();
        Object[] row = new Object[3];
        for (int i = 0; i < controller.getStandardJobList().size(); i++) {
            row[0] = controller.getStandardJobList().get(i).getCode();
            row[1] = controller.getStandardJobList().get(i).getDescription();
            row[2] = controller.getStandardJobList().get(i).getStatus();
            standardJobTableModel.addRow(row);
        }
    }

    private void updateCollectJobTable() {
        //Get job information from controller class

        //ArrayList<JobDetails> jobs = controller.getJob();
        DefaultTableModel jobTableModel = (DefaultTableModel) jobSearchResultsTable.getModel();
        //jobTableModel.setRowCount(controller.getJob().size());
        //Set table
        Object[] row = new Object[5];
        for (int i = 0; i < controller.getJob().size(); i++) {
            row[0] = controller.getJob().get(i).getJobNumber();
            row[1] = controller.getJob().get(i).getIssuedBy();
            row[2] = controller.getJob().get(i).getDeadline();
            row[3] = controller.getJob().get(i).getStatus();
            row[4] = controller.getJob().get(i).isIsCollected();

            jobTableModel.addRow(row);
        }
    }

    private void updateJobEnquiryTable() {

        DefaultTableModel jobEnquiryTableModel = (DefaultTableModel) jobEnquiryTableResults.getModel();
        //Set table
        //jobEnquiryTableModel.setRowCount(controller.getJob().size());
        Object[] row = new Object[5];
        for (int i = 0; i < controller.getJob().size(); i++) {
            row[0] = controller.getJob().get(i).getJobNumber();
            row[1] = controller.getJob().get(i).getIssuedBy();
            row[2] = controller.getJob().get(i).getDateReceived();
            row[3] = controller.getJob().get(i).getDeadline();
            row[4] = controller.getJob().get(i).getStatus();
            jobEnquiryTableModel.addRow(row);
        }
    }

    private void updateTaskTable() {
        //Get task information from controller class
        ArrayList<Task> tasks = controller.getTasksArrayList();
        DefaultTableModel taskTableModel = (DefaultTableModel) taskTable.getModel();

        //Set table
        Object[] row = new Object[4];
        for (int i = 0; i < tasks.size(); i++) {
            row[0] = tasks.get(i).getTaskID();
            row[1] = tasks.get(i).getDescription();
            row[2] = tasks.get(i).getDepartmentCode();
            row[3] = tasks.get(i).getShelfSlot();
            taskTableModel.addRow(row);
        }

    }

    private void resetComponents(JPanel panel) {
        for (Component c : panel.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainFrame(controller).setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AutoBackupConfigjPanel;
    private javax.swing.JLabel BAPERSLabel;
    private javax.swing.JLabel BackupDataLabel;
    private javax.swing.JPanel CustomerAction;
    private javax.swing.JPanel CustomerInfo;
    private javax.swing.JPanel DiscountTypeInformation;
    private javax.swing.JPanel FixedDiscountType;
    private javax.swing.JPanel FlexiableDiscountType;
    private javax.swing.JPanel JobsjPanel;
    private javax.swing.JScrollPane JobsjScrollPane;
    private javax.swing.JTable JobsjTable;
    private javax.swing.JLabel LastnamejLabel;
    private javax.swing.JPasswordField NewPasswordField;
    private javax.swing.JPasswordField NewRepeatPasswordField;
    private javax.swing.JPanel OtherCustomerInfo;
    private javax.swing.JLabel ReenterPasswordLabel;
    private javax.swing.JButton RestoreButton;
    private javax.swing.JButton RestorePageButton;
    private javax.swing.JLabel RoleLabel;
    private javax.swing.JPanel SelectCustomer;
    private javax.swing.JPanel SelectDiscountPlan;
    private javax.swing.JPanel StandardJobsjPanel;
    private javax.swing.JScrollPane StandardJobsjScrollPane;
    private javax.swing.JTable StandardJobsjTable;
    private javax.swing.JTable TaskjTable;
    private javax.swing.JPanel TasksjPanel;
    private javax.swing.JScrollPane TasksjScrollPane;
    private javax.swing.JTextField TotalLatePayjTextField;
    private javax.swing.JTextField UserFirstnameField;
    private javax.swing.JTextField UserLastnameField;
    private javax.swing.JTextField UserNumberField;
    private javax.swing.JComboBox<String> UserRoleSearchDrop;
    private javax.swing.JPanel VariableDiscountType;
    private javax.swing.JPanel ViewCustomerDetail;
    private javax.swing.JPanel acceptJobPage;
    private javax.swing.JButton acceptJobPageButton;
    private javax.swing.JPanel acceptLatePaymentjPanel;
    private javax.swing.JPanel acceptPayment;
    private javax.swing.JButton acceptPaymentPageButton;
    private javax.swing.JButton acceptPaymentReceptionist;
    private javax.swing.JLabel accountHolderNameLabel;
    private javax.swing.JTextField accountHolderNamejTextField;
    private javax.swing.JComboBox<String> accountStatusDD;
    private javax.swing.JLabel accountStatusjLabel;
    private javax.swing.JButton addButton;
    private javax.swing.JButton addFlexibleBoundjButton;
    private javax.swing.JButton addJobButton;
    private javax.swing.JButton addMaterialButton;
    private javax.swing.JButton applyDiscountjButton;
    private javax.swing.JButton assignDiscountPlanjButton;
    private javax.swing.JTextField autoBackupLocationjTextField;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton backButon;
    private javax.swing.JButton backButton;
    private javax.swing.JButton backCustomerPageButton;
    private javax.swing.JButton backJobEnquiryButton;
    private javax.swing.JButton backJobSearchResultsButton;
    private javax.swing.JButton backRecpHPButton;
    private javax.swing.JButton backupButton;
    private javax.swing.JLabel backupDestinationLabel;
    private javax.swing.JComboBox<String> backupFrequencyjComboBox;
    private javax.swing.JLabel backupFrequencyjLabel;
    private javax.swing.JLabel backupLocationjLabel;
    private javax.swing.JComboBox<String> backupModejComboBox;
    private javax.swing.JLabel backupModejLabel;
    private javax.swing.JPanel backupPage;
    private javax.swing.JButton backupPageButton;
    private javax.swing.JButton backupSettingsPageButton;
    private javax.swing.JLabel boundjLabel;
    private javax.swing.JList<String> boundsjList;
    private javax.swing.JTextField buildingNumberField;
    private javax.swing.JLabel cPLabel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton cancelCreationjButton;
    private javax.swing.JButton cancelCustomerFJobjButton;
    private javax.swing.JButton cancelInvoiceSeletionjButton;
    private javax.swing.JButton cancelInvoiceSeletionjButton1;
    private javax.swing.JButton cancelJobEnquiryButton;
    private javax.swing.JButton cancelSearchButton;
    private javax.swing.JPanel cardPanel1;
    private javax.swing.JPanel cardPanel2;
    private javax.swing.JComboBox<String> cardTypejComboBox;
    private javax.swing.JLabel cardTypejLabel;
    private javax.swing.JLabel changeConfigjLabel;
    private javax.swing.JButton changeRoleButton;
    private javax.swing.JButton chooseFileButton;
    private javax.swing.JButton chooseLocationButton;
    private javax.swing.JTextField cityField;
    private javax.swing.JTextField cityField1;
    private javax.swing.JLabel citySjLabel;
    private javax.swing.JLabel cityjLabel;
    private javax.swing.JTextField codeField;
    private javax.swing.JLabel codeLabel;
    private javax.swing.JButton collectJobButton;
    private javax.swing.JButton collectJobPageButton;
    private javax.swing.JComboBox<String> completionTimeDD;
    private javax.swing.JButton confirmAutoBackupConfigjButton;
    private javax.swing.JButton createButton;
    private javax.swing.JButton createCustomerButton;
    private javax.swing.JPanel createCustomerPage;
    private javax.swing.JButton createCustomerjButton;
    private javax.swing.JButton createNewTaskButton;
    private javax.swing.JButton createReportButton;
    private javax.swing.JPanel createReportPage;
    private javax.swing.JButton createReportPageButton;
    private javax.swing.JButton createSJobPageButton;
    private javax.swing.JPanel createStandardJobPage;
    private javax.swing.JButton createTaskButton;
    private javax.swing.JPanel createTaskPage;
    private javax.swing.JButton createTaskPageButton;
    private javax.swing.JButton createUserButton;
    private javax.swing.JPanel createUserPage;
    private javax.swing.JButton createUserPageButton;
    private javax.swing.JTextField currentAutoBackupFrequencyDatajTextField;
    private javax.swing.JTextField currentAutoBackupLocationDatajTextField;
    private javax.swing.JTextField currentAutoBackupModeDatajTextField;
    private javax.swing.JLabel currentBackupFrequencyjLabel;
    private javax.swing.JLabel currentBackupLocationjLabel;
    private javax.swing.JLabel currentBackupModejLabel;
    private javax.swing.JLabel currentConfigjLabel;
    private javax.swing.JTextField custAccountHNameField;
    private javax.swing.JTextField custAccountNoField;
    private javax.swing.JTextField custFirstnameField;
    private javax.swing.JTextField custLastnameField;
    private javax.swing.JLabel customerAccHolderNamejLabel;
    private javax.swing.JLabel customerActionjLabel;
    private javax.swing.JTextField customerBuildingNojTextField;
    private javax.swing.JTextField customerCityjTextField;
    private javax.swing.JTextField customerDiscountjTextField;
    private javax.swing.JLabel customerFirstNamejLabel;
    private javax.swing.JTextField customerInfoField;
    private javax.swing.JLabel customerLastNamejLabel;
    private javax.swing.JTextField customerNumberText;
    private javax.swing.JLabel customerNumberjLabel;
    private javax.swing.JTextField customerPostcodejTextField;
    private javax.swing.JLabel customerPrefixjLabel;
    private com.toedter.calendar.JDateChooser customerRegDateField;
    private javax.swing.JLabel customerRegistationDatejLabel;
    private javax.swing.JPanel customerReportPage;
    private javax.swing.JPanel customerResultsPage;
    private javax.swing.JTable customerResultsTable;
    private javax.swing.JTextField customerStatusjTextField;
    private javax.swing.JTextField customerStreetNamejTextField;
    private javax.swing.JComboBox<String> customerTypeDD;
    private javax.swing.JLabel customerTypeSjLabel;
    private javax.swing.JTextField customerTypejTextField;
    private javax.swing.JScrollPane customersjScrollPane;
    private javax.swing.JTable customersjTable;
    private javax.swing.JTextField cutomerInDefaultjTextField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton deleteButton1;
    private javax.swing.JComboBox<String> departmentComboBox;
    private javax.swing.JComboBox<String> departmentNewTaskDD;
    private javax.swing.JLabel departmentNewTaskLabel;
    private javax.swing.JLabel descLabel;
    private javax.swing.JTextField descriptionField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextField descriptionNewTaskField;
    private javax.swing.JTextField descriptionText;
    private javax.swing.JButton discountCanceljButton;
    private javax.swing.JComboBox<String> discountPlanTypejComboBox;
    private javax.swing.JLabel discountPlanTypejLabel;
    private javax.swing.JLabel durationLabel;
    private javax.swing.JComboBox<String> durationNewTaskDD;
    private javax.swing.JComboBox<String> durationNewTaskMinsDD;
    private javax.swing.JButton editButton;
    private javax.swing.JPanel editTaskPage;
    private javax.swing.JLabel expiryDatejLabel;
    private javax.swing.JTextField expiryDatejTextField;
    private javax.swing.JTextField fileChosenField;
    private javax.swing.JLabel findUserLabel;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JLabel firstNamejLabel;
    private javax.swing.JLabel firstnameLabel;
    private javax.swing.JLabel fixedDiscountRatejLabel;
    private javax.swing.JTextField fixedDiscountRatejTextField;
    private javax.swing.JLabel fixedPercentageSymbol;
    private javax.swing.JLabel flexiablePercentageSymbol;
    private javax.swing.JLabel flexibleDiscountRatejLabel;
    private javax.swing.JTextField flexibleDiscountRatejTextField;
    private javax.swing.JPanel homeBar;
    private javax.swing.JButton homeButton;
    private javax.swing.JPanel homePage;
    private javax.swing.JLabel hoursLabel;
    private javax.swing.JLabel iPLabel;
    private javax.swing.JComboBox<String> inDefaultDD;
    private javax.swing.JLabel inDefaultSjLabel;
    private javax.swing.JPanel individualReportPage;
    private javax.swing.JTextField infoField;
    private javax.swing.JList<String> invoicejList;
    private javax.swing.JScrollPane invoicejScrollPane;
    private javax.swing.JTable invoicejTable;
    private javax.swing.JTable invoicejTable1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JComboBox<String> jobCollectedComboBox;
    private javax.swing.JPanel jobEnquiryPage;
    private javax.swing.JButton jobEnquiryPageButton;
    private javax.swing.JPanel jobEnquirySearchResultsPage;
    private javax.swing.JTable jobEnquiryTableResults;
    private javax.swing.JButton jobEnquiryTechnician;
    private javax.swing.JPanel jobHomePage;
    private javax.swing.JLabel jobIndexLabel;
    private javax.swing.JButton jobMenuPageButton;
    private javax.swing.JLabel jobNumberLabel;
    private javax.swing.JLabel jobNumberLabel1;
    private javax.swing.JTextField jobNumberText;
    private javax.swing.JTextField jobNumberTextField;
    private javax.swing.JComboBox<String> jobPriorityComboBox;
    private javax.swing.JLabel jobPriorityLabel;
    private javax.swing.JLabel jobPriorityLabel2;
    private javax.swing.JButton jobReceptionist;
    private javax.swing.JPanel jobSearchResultsPage;
    private javax.swing.JTable jobSearchResultsTable;
    private javax.swing.JComboBox<String> jobStatusComboBox;
    private javax.swing.JLabel jobStatusLabel;
    private javax.swing.JTextField jobTotalField;
    private javax.swing.JLabel last4DigitjLabel;
    private javax.swing.JTextField last4DigitjTextField;
    private javax.swing.JLabel lastnameLabel;
    private javax.swing.JButton latePaymentSubmitjButton;
    private javax.swing.JTextField locationChosenField;
    private javax.swing.JButton logOutButton;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPanel loginPage;
    private javax.swing.JButton loginPageButton;
    private javax.swing.JLabel lowerBoundjLabel;
    private javax.swing.JTextField lowerBoundjTextField;
    private javax.swing.JButton manageCustomersMenuPageButton;
    private javax.swing.JButton manageReportsPageButton;
    private javax.swing.JButton manageSJobPageButton;
    private javax.swing.JButton manageTasksButton;
    private javax.swing.JPanel manageTasksPage;
    private javax.swing.JButton manageTasksPageButton;
    private javax.swing.JButton manageUsersPageButton;
    private javax.swing.JPanel managerjPanel;
    private javax.swing.JList<String> materialList;
    private javax.swing.JLabel materialSubmittedLabel;
    private javax.swing.JScrollPane materialsjScrollPane;
    private javax.swing.JScrollPane materialsjScrollPane1;
    private javax.swing.JScrollPane materialsjScrollPane2;
    private javax.swing.JTextField materialsjTextField;
    private javax.swing.JLabel minutesLabel;
    private javax.swing.JLabel newDescriptionLabel;
    private javax.swing.JLabel newDescriptionLabel1;
    private javax.swing.JLabel newDescriptionLabel2;
    private javax.swing.JLabel newDescriptionLabel3;
    private javax.swing.JLabel newTaskLabel1;
    private javax.swing.JLabel newUserLabel;
    private javax.swing.JLabel newUserLabel1;
    private javax.swing.JPanel officeManagerCustomerPage;
    private javax.swing.JLabel pageLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JComboBox<String> paymentTypeComboBox;
    private javax.swing.JLabel paymentTypejLabel;
    private javax.swing.JLabel periodFromLabel;
    private javax.swing.JLabel periodLabel;
    private javax.swing.JLabel periodToLabel;
    private javax.swing.JTextField phoneField1;
    private javax.swing.JTextField phoneNumberField;
    private javax.swing.JLabel phoneNumberjLabel;
    private javax.swing.JLabel phoneNumberjLabel1;
    private javax.swing.JLabel phoneSjLabel;
    private javax.swing.JTextField postCodeField;
    private javax.swing.JTextField postCodeField1;
    private javax.swing.JLabel postCodejLabel;
    private javax.swing.JLabel postcodeSjLabel;
    private javax.swing.JComboBox<String> prefixjComboBox;
    private javax.swing.JLabel prefixjLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceNewTaskField;
    private javax.swing.JTextField priceText;
    private javax.swing.JButton printButton1;
    private javax.swing.JButton printButton2;
    private javax.swing.JButton printButton3;
    private javax.swing.JPanel receptionHomePage;
    private javax.swing.JPanel receptionistHomePage;
    private javax.swing.JPanel receptionistjPanel;
    private javax.swing.JLabel registrationDateSjLabel;
    private javax.swing.JLabel registrationDatejLabel;
    private javax.swing.JButton reminderLettersButton;
    private javax.swing.JTable reminderLettersTable;
    private javax.swing.JPanel reminderLettersTablePage;
    private javax.swing.JButton removeFlexibleBoundjButton;
    private javax.swing.JButton removeJobButton;
    private javax.swing.JButton removeMaterialButton;
    private javax.swing.JButton removeTaskButton;
    private javax.swing.JButton reportBackButton;
    private javax.swing.JButton reportBackButton1;
    private javax.swing.JButton reportBackButton2;
    private com.toedter.calendar.JDateChooser reportEndPeriod;
    private javax.swing.JPanel reportHomePage;
    private javax.swing.JLabel reportPeriodLabel;
    private javax.swing.JButton reportSettingsPageButton;
    private com.toedter.calendar.JDateChooser reportStartPeriod;
    private javax.swing.JComboBox<String> reportTypeDD;
    private javax.swing.JLabel reportTypeLabel;
    private javax.swing.JButton reportsMenuPageButton;
    private javax.swing.JLabel restoreLabel;
    private javax.swing.JPanel restorePage;
    private javax.swing.JButton restoreSettingsPageButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel searchAccountHolderNamejLabel;
    private javax.swing.JButton searchAgainButton;
    private javax.swing.JButton searchAgainButton1;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel searchContactFirstNamejLabel;
    private javax.swing.JLabel searchContactSurnamejLabel;
    private javax.swing.JLabel searchCustomerAccountNojLabel;
    private javax.swing.JButton searchCustomerBtn;
    private javax.swing.JButton searchCustomerButton;
    private javax.swing.JButton searchCustomerButton1;
    private javax.swing.JButton searchCustomerFJobjButton;
    private javax.swing.JPanel searchCustomerPage;
    private javax.swing.JPanel searchCustomerjPanel;
    private javax.swing.JPanel searchInvoice;
    private javax.swing.JLabel searchInvoiceByInvoiceNojLabel;
    private javax.swing.JTextField searchInvoiceByInvoiceNojTextField;
    private javax.swing.JLabel searchInvoiceByJobNumberjLabel;
    private javax.swing.JTextField searchInvoiceByJobNumberjTextField;
    private javax.swing.JPanel searchInvoicePage;
    private javax.swing.JPanel searchInvoicejPanel;
    private javax.swing.JPanel searchInvoicejPanel1;
    private javax.swing.JButton searchJobButton;
    private javax.swing.JButton searchJobEnquiryButton;
    private javax.swing.JButton searchJobNumberJobEnquiryButton;
    private javax.swing.JPanel searchJobPage;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JButton searchUserButton;
    private javax.swing.JComboBox<String> selectATaskBox;
    private javax.swing.JButton selectAutoBackupLocationjButton;
    private javax.swing.JButton selectCustomerButton;
    private javax.swing.JButton selectCustomerjButton;
    private javax.swing.JButton selectInvoicejButton;
    private javax.swing.JComboBox<String> selectPriority;
    private javax.swing.JButton selectSelectedInvoicejButton;
    private javax.swing.JButton selectSelectedInvoicejButton1;
    private javax.swing.JButton selectUserButton;
    private javax.swing.JPanel settingsHomePage;
    private javax.swing.JButton settingsMenuPageButton;
    private javax.swing.JLabel shelfSlotNewTaskLabel;
    private javax.swing.JComboBox<String> shelfSlotTaskDD;
    private javax.swing.JTextField shelfSlotText;
    private javax.swing.JLabel shift1Label;
    private javax.swing.JLabel shift2Label;
    private javax.swing.JLabel shift3Label;
    private javax.swing.JTextField specialInstructionjTextField;
    private javax.swing.JLabel specialInstructionsLabel;
    private javax.swing.JLabel standardJobCodeLabel;
    private javax.swing.JPanel standardJobHomePage;
    private javax.swing.JLabel standardJobIndexLabel;
    private javax.swing.JList<String> standardJobList;
    private javax.swing.JList<String> standardJobList1;
    private javax.swing.JTable standardJobResults;
    private javax.swing.JPanel standardJobSearchResultsJobEnquiryPage;
    private javax.swing.JButton standardJobsMenuPageButton;
    private javax.swing.JComboBox<String> stdJobDD;
    private javax.swing.JLabel stdJobLabel;
    private javax.swing.JTextField stdJobTotalField;
    private javax.swing.JPanel stipulatedFields;
    private javax.swing.JTextField streetNameField;
    private javax.swing.JTextField streetNameField1;
    private javax.swing.JLabel streetNameSjLabel;
    private javax.swing.JLabel streetNamejLabel;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel summaryReportLabel;
    private javax.swing.JScrollPane summaryReportPage;
    private javax.swing.JPanel summaryReportPageData;
    private javax.swing.JLabel surchargeLabel;
    private javax.swing.JTextField surchargejTextField;
    private javax.swing.JTextField surnameField;
    private javax.swing.JButton taskEnquiryBackButton;
    private javax.swing.JPanel taskHomePage;
    private javax.swing.JLabel taskIDLabel;
    private javax.swing.JTextField taskIDText;
    private javax.swing.JPanel taskPage;
    private javax.swing.JTable taskResultsTable;
    private javax.swing.JPanel taskSearchResultsJobEnquiryPage;
    private javax.swing.JTable taskTable;
    private javax.swing.JButton tasksMenuPageButton;
    private javax.swing.JPanel technicianHomePage;
    private javax.swing.JPanel technicianPage;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JLabel totalLabel1;
    private javax.swing.JLabel totaljLabel;
    private javax.swing.JButton updateTaskButton;
    private javax.swing.JLabel upperBoundjLabel;
    private javax.swing.JTextField upperBoundjTextField;
    private javax.swing.JTextField userFirstNameField;
    private javax.swing.JLabel userFirstnameLabel;
    private javax.swing.JPanel userHomePage;
    private javax.swing.JTextField userIDField;
    private javax.swing.JTextField userLastNameField;
    private javax.swing.JLabel userLastnameLabel;
    private javax.swing.JLabel userLastnameLabel1;
    private javax.swing.JLabel userNumberLabel;
    private javax.swing.JPanel userResultsPage;
    private javax.swing.JTable userResultsTable;
    private javax.swing.JComboBox<String> userRoleComboBox;
    private javax.swing.JPanel userSearchPage;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JButton usersMenuPageButton;
    private javax.swing.JButton viewJobEnquiryButton;
    private javax.swing.JButton viewReminderLetterButton;
    private javax.swing.JButton viewStandardJobButton;
    private javax.swing.JPanel welcomeBar;
    private javax.swing.JPanel welcomePage;
    private javax.swing.JLabel welcomePageLabel;
    // End of variables declaration//GEN-END:variables
}
