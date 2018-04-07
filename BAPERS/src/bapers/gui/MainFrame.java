/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.gui;

import bapers.acct.*;
import bapers.controller.Controller;
import java.awt.CardLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author CameronE
 */
public class MainFrame extends javax.swing.JFrame {
    Calendar date = Calendar.getInstance();
    Timer time = new Timer();
    AutoBackupConfig configdata;
    
    int paymentNo = 0;
    
    // Lists for the tables and list 
    List<Material> materials = new ArrayList<>();
    List<StandardJob> stdJobs = new ArrayList<>();
    List<Invoice> selectedInvoices = new ArrayList<>();
    Invoice invoice;
    
    // list models that are used to for the scroll
    DefaultListModel t = new DefaultListModel();
    DefaultListModel t2 = new DefaultListModel();
    DefaultTableModel m; 
    
    //materials counter
    int mCount = 0;
    
    private final CardLayout card1;
    private final CardLayout card2;
    private final Controller controller;
    
    /**
     * Creates new form MainFrame
     * @param controller
     */
    public MainFrame(Controller controller) {
        
        this.controller = controller;
        initComponents();
        card1 = (CardLayout) cardPanel1.getLayout();
        card2 = (CardLayout) cardPanel2.getLayout();
        initAutoBackup();
    }
    
    public final void initAutoBackup() {
        final boolean countAutoConfigData = controller.checkAutoBackupConfigExist();
        
        if (countAutoConfigData != false) {
            this.configdata = controller.getAutoBackupConfigData();
            autoBackupMode(configdata);
        } else {
            System.out.println("No auto update");
        }
    }
    
    public final void autoBackupMode(AutoBackupConfig configdata) {
        switch (configdata.getBackupMode()) {
            case "auto" :
                System.out.println("Auto");
                autoBackupFrequency(configdata);
                break;
            case "reminder" :
                System.out.println("Reminder");
                break;
            case "manual" :
                System.out.println("Manual");
                break;
            default : break;
        }
    }
    
    public void autoBackupFrequency(AutoBackupConfig configdata) {
        switch (configdata.getBackupFrequency()) {
            case "weekly": 
                System.out.println("weekly");
//                actDate.get
                //time.scheduleAtFixedRate(new AutoBackup(configdata, date), date.getTime(), TimeUnit.DAYS.toMillis(7));
                break;
            case "monthly": 
                System.out.println("monthly");
                //scheduleEveryMonth();
                break;
            default : break;
        }
    }
    
//    public void scheduleEveryMonth() {
//        calendar.set(Calendar.MONTH, Calendar.JANUARY);
//        calendar.set(Calendar.HOUR_OF_DAY, 12);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        time.scheduleAtFixedRate(new AutoBackup(configdata), calendar.getTime(), TimeUnit.DAYS.toMillis(365));
//        
//        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
//        calendar.set(Calendar.HOUR_OF_DAY, 12);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        time.scheduleAtFixedRate(new AutoBackup(configdata), calendar.getTime(), TimeUnit.DAYS.toMillis(365));
//        
//        calendar.set(Calendar.MONTH, Calendar.MARCH);
//        calendar.set(Calendar.HOUR_OF_DAY, 12);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        time.scheduleAtFixedRate(new AutoBackup(configdata), calendar.getTime(), TimeUnit.DAYS.toMillis(365));
//        
//        calendar.set(Calendar.MONTH, Calendar.APRIL);
//        calendar.set(Calendar.HOUR_OF_DAY, 12);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        time.scheduleAtFixedRate(new AutoBackup(configdata), calendar.getTime(), TimeUnit.DAYS.toMillis(365));
//        
//        calendar.set(Calendar.MONTH, Calendar.MAY);
//        calendar.set(Calendar.HOUR_OF_DAY, 12);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        time.scheduleAtFixedRate(new AutoBackup(configdata), calendar.getTime(), TimeUnit.DAYS.toMillis(365));
//        
//        calendar.set(Calendar.MONTH, Calendar.JUNE);
//        calendar.set(Calendar.HOUR_OF_DAY, 12);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        time.scheduleAtFixedRate(new AutoBackup(configdata), calendar.getTime(), TimeUnit.DAYS.toMillis(365));
//        
//        calendar.set(Calendar.MONTH, Calendar.JULY);
//        calendar.set(Calendar.HOUR_OF_DAY, 12);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        time.scheduleAtFixedRate(new AutoBackup(configdata), calendar.getTime(), TimeUnit.DAYS.toMillis(365));
//        
//        calendar.set(Calendar.MONTH, Calendar.AUGUST);
//        calendar.set(Calendar.HOUR_OF_DAY, 12);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        time.scheduleAtFixedRate(new AutoBackup(configdata), calendar.getTime(), TimeUnit.DAYS.toMillis(365));
//        
//        calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
//        calendar.set(Calendar.HOUR_OF_DAY, 12);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        time.scheduleAtFixedRate(new AutoBackup(configdata), calendar.getTime(), TimeUnit.DAYS.toMillis(365));
//        
//        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
//        calendar.set(Calendar.HOUR_OF_DAY, 12);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        time.scheduleAtFixedRate(new AutoBackup(configdata), calendar.getTime(), TimeUnit.DAYS.toMillis(365));
//        
//        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
//        calendar.set(Calendar.HOUR_OF_DAY, 12);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        time.scheduleAtFixedRate(new AutoBackup(configdata), calendar.getTime(), TimeUnit.DAYS.toMillis(365));
//        
//        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
//        calendar.set(Calendar.HOUR_OF_DAY, 12);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        time.scheduleAtFixedRate(new AutoBackup(configdata), calendar.getTime(), TimeUnit.DAYS.toMillis(365));  
//    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardPanel1 = new javax.swing.JPanel();
        welcomePage = new javax.swing.JPanel();
        BAPERSLabel = new javax.swing.JLabel();
        homePageR = new javax.swing.JButton();
        autoBackConfig = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        receptionistHomePage = new javax.swing.JPanel();
        receptionHomePage = new javax.swing.JPanel();
        jobReceptionist = new javax.swing.JButton();
        acceptPaymentReceptionist = new javax.swing.JButton();
        acceptJob = new javax.swing.JPanel();
        acceptJobjPanel = new javax.swing.JPanel();
        addMaterialButton = new javax.swing.JButton();
        addJobButton = new javax.swing.JButton();
        totalLabel = new javax.swing.JLabel();
        removeButton = new javax.swing.JButton();
        selectStdJob = new javax.swing.JComboBox<>();
        selectPriority = new javax.swing.JComboBox<>();
        searchCustomerButton = new javax.swing.JButton();
        createCustomerButton = new javax.swing.JButton();
        specialInstructionsLabel = new javax.swing.JLabel();
        materialsjScrollPane = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        customerInfojTextField = new javax.swing.JTextField();
        stdJobsjScrollPane1 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        materialSubmittedLabel = new javax.swing.JLabel();
        cancelAcceptJobButton = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();
        totalAmountLabel = new javax.swing.JLabel();
        materialsjTextField = new javax.swing.JTextField();
        surchargejTextField = new javax.swing.JTextField();
        percentageLabel = new javax.swing.JLabel();
        surchargeLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        specialInstructionjTextField = new javax.swing.JTextField();
        createCustomer = new javax.swing.JPanel();
        createCustomerjPanel = new javax.swing.JPanel();
        accountHolderNojLabel = new javax.swing.JLabel();
        prefixjLabel = new javax.swing.JLabel();
        firstNamejLabel = new javax.swing.JLabel();
        surnamejLabel = new javax.swing.JLabel();
        phoneNumberjLabel = new javax.swing.JLabel();
        buildingNumberjLabel = new javax.swing.JLabel();
        postCodejLabel = new javax.swing.JLabel();
        streetNamejLabel = new javax.swing.JLabel();
        prefixjComboBox = new javax.swing.JComboBox<>();
        createCustomerjButton = new javax.swing.JButton();
        cancelCreationjButton = new javax.swing.JButton();
        accountHolderNojTextField = new javax.swing.JTextField();
        postCodeField = new javax.swing.JTextField();
        surnameField = new javax.swing.JTextField();
        phoneNumberField = new javax.swing.JTextField();
        firstNameField = new javax.swing.JTextField();
        buildingNumberField = new javax.swing.JTextField();
        streetNameField = new javax.swing.JTextField();
        cityField = new javax.swing.JTextField();
        cityjLabel = new javax.swing.JLabel();
        accountHolderNamejLabel = new javax.swing.JLabel();
        accountHolderNamejTextField = new javax.swing.JTextField();
        searchCustomer = new javax.swing.JPanel();
        searchCustomerjPanel = new javax.swing.JPanel();
        receptionistjPanel = new javax.swing.JPanel();
        searchCustomerAccountNojLabel = new javax.swing.JLabel();
        searchContactFirstNamejLabel = new javax.swing.JLabel();
        searchContactSurnamejLabel = new javax.swing.JLabel();
        searchAccountHolderNamejLabel = new javax.swing.JLabel();
        searchCustomerAccountNojTextField = new javax.swing.JTextField();
        searchContactFirstNamejTextField = new javax.swing.JTextField();
        searchContactSurnamejTextField = new javax.swing.JTextField();
        searchAccountHolderNamejTextField = new javax.swing.JTextField();
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
        streetNamejTextField = new javax.swing.JTextField();
        postCodejTextField = new javax.swing.JTextField();
        cityjTextField = new javax.swing.JTextField();
        phonejTextField = new javax.swing.JTextField();
        customerTypejComboBox = new javax.swing.JComboBox<>();
        discountStatusjComboBox = new javax.swing.JComboBox<>();
        inDefaultjComboBox = new javax.swing.JComboBox<>();
        registrationDatejComboBox = new javax.swing.JComboBox<>();
        isManagerjToggleButton = new javax.swing.JToggleButton();
        acceptLatePayment = new javax.swing.JPanel();
        acceptLatePaymentjPanel = new javax.swing.JPanel();
        expiryDatejLabel = new javax.swing.JLabel();
        last4DigitjTextField = new javax.swing.JTextField();
        selectInvoicejButton = new javax.swing.JButton();
        paymentTypejLabel = new javax.swing.JLabel();
        cancelLatePaymentjButton = new javax.swing.JButton();
        expiryDatejTextField = new javax.swing.JTextField();
        paymentTypeComboBox = new javax.swing.JComboBox<>();
        submitLatePaymentjButton = new javax.swing.JButton();
        TotalLatePayjTextField = new javax.swing.JTextField();
        cardTypejLabel = new javax.swing.JLabel();
        totaljLabel = new javax.swing.JLabel();
        cardTypejComboBox = new javax.swing.JComboBox<>();
        last4DigitjLabel = new javax.swing.JLabel();
        invoicejScrollPane = new javax.swing.JScrollPane();
        invoicejList = new javax.swing.JList<>();
        searchInvoice = new javax.swing.JPanel();
        searchInvoicejPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        invoicejTable = new javax.swing.JTable();
        selectSelectedInvoicejButton = new javax.swing.JButton();
        cancelInvoiceSeletionjButton = new javax.swing.JButton();
        searchInvoiceByInvoiceNojTextField = new javax.swing.JTextField();
        searchInvoiceByJobNumberjTextField = new javax.swing.JTextField();
        searchInvoiceByInvoiceNojLabel = new javax.swing.JLabel();
        searchInvoiceByJobNumberjLabel = new javax.swing.JLabel();
        AutoBackupConfigjPanel = new javax.swing.JPanel();
        autoBackupLocationjTextField = new javax.swing.JTextField();
        backupLocationjLabel = new javax.swing.JLabel();
        backupFrequencyjLabel = new javax.swing.JLabel();
        backupModejLabel = new javax.swing.JLabel();
        backupModejComboBox = new javax.swing.JComboBox<>();
        backupFrequencyjComboBox = new javax.swing.JComboBox<>();
        selectAutoBackupLocationjButton = new javax.swing.JButton();
        cancelAutoBackupConfigjButton = new javax.swing.JButton();
        confirmAutoBackupConfigjButton = new javax.swing.JButton();
        Backup = new javax.swing.JPanel();
        cardPanel2 = new javax.swing.JPanel();
        welcomeBar1 = new javax.swing.JPanel();
        welcomeBar2 = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        homePageOMBar = new javax.swing.JPanel();
        welcomeBar3 = new javax.swing.JPanel();
        officeManagerText = new javax.swing.JLabel();
        officeManagerWelcomeText = new javax.swing.JLabel();
        logOutButton = new javax.swing.JButton();
        homePagesSMBar = new javax.swing.JPanel();
        welcomeBar4 = new javax.swing.JPanel();
        officeManagerText1 = new javax.swing.JLabel();
        officeManagerWelcomeText1 = new javax.swing.JLabel();
        logOutButton1 = new javax.swing.JButton();
        homePageTBar = new javax.swing.JPanel();
        homePageTechnician = new javax.swing.JPanel();
        officeManagerText2 = new javax.swing.JLabel();
        officeManagerWelcomeText2 = new javax.swing.JLabel();
        logOutButton2 = new javax.swing.JButton();
        homePageRBar = new javax.swing.JPanel();
        homePageReceptionist = new javax.swing.JPanel();
        officeManagerText3 = new javax.swing.JLabel();
        officeManagerWelcomeText3 = new javax.swing.JLabel();
        logOutButton3 = new javax.swing.JButton();
        acceptJobMBar = new javax.swing.JPanel();
        acceptJobLabel = new javax.swing.JLabel();
        acceptLatePaymentMBar = new javax.swing.JPanel();
        acceptLatePaymentLabel = new javax.swing.JLabel();
        searchCustomerMBar = new javax.swing.JPanel();
        searchCustomerLabel = new javax.swing.JLabel();
        createCustomerMBar = new javax.swing.JPanel();
        createCustomerLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 700));
        setResizable(false);

        cardPanel1.setBackground(new java.awt.Color(255, 204, 204));
        cardPanel1.setPreferredSize(new java.awt.Dimension(900, 640));
        cardPanel1.setLayout(new java.awt.CardLayout());

        welcomePage.setBackground(new java.awt.Color(61, 96, 146));
        welcomePage.setMaximumSize(new java.awt.Dimension(900, 640));
        welcomePage.setMinimumSize(new java.awt.Dimension(900, 640));
        welcomePage.setPreferredSize(new java.awt.Dimension(900, 640));

        BAPERSLabel.setFont(new java.awt.Font("Tahoma", 1, 90)); // NOI18N
        BAPERSLabel.setForeground(new java.awt.Color(255, 255, 255));
        BAPERSLabel.setText("B   A   P   E   R   S");

        homePageR.setText("homePageR");
        homePageR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homePageRActionPerformed(evt);
            }
        });

        autoBackConfig.setText("autoBackupConfig");
        autoBackConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoBackConfigActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout welcomePageLayout = new javax.swing.GroupLayout(welcomePage);
        welcomePage.setLayout(welcomePageLayout);
        welcomePageLayout.setHorizontalGroup(
            welcomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePageLayout.createSequentialGroup()
                .addGroup(welcomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(welcomePageLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(BAPERSLabel))
                    .addGroup(welcomePageLayout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(autoBackConfig)
                        .addGap(36, 36, 36)
                        .addComponent(homePageR)))
                .addGap(75, 75, 75))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, welcomePageLayout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(95, 95, 95))
        );
        welcomePageLayout.setVerticalGroup(
            welcomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePageLayout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(BAPERSLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(60, 60, 60)
                .addGroup(welcomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(homePageR)
                    .addComponent(autoBackConfig))
                .addGap(114, 114, 114))
        );

        cardPanel1.add(welcomePage, "welcome");

        receptionistHomePage.setMaximumSize(new java.awt.Dimension(900, 700));
        receptionistHomePage.setMinimumSize(new java.awt.Dimension(900, 700));
        receptionistHomePage.setPreferredSize(new java.awt.Dimension(900, 700));

        receptionHomePage.setBackground(new java.awt.Color(61, 96, 146));
        receptionHomePage.setMaximumSize(new java.awt.Dimension(900, 700));
        receptionHomePage.setMinimumSize(new java.awt.Dimension(900, 700));
        receptionHomePage.setPreferredSize(new java.awt.Dimension(900, 700));

        jobReceptionist.setText("Accept Job");
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
                .addGap(218, 218, 218)
                .addComponent(jobReceptionist, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(acceptPaymentReceptionist, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );
        receptionHomePageLayout.setVerticalGroup(
            receptionHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, receptionHomePageLayout.createSequentialGroup()
                .addContainerGap(284, Short.MAX_VALUE)
                .addGroup(receptionHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jobReceptionist, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceptPaymentReceptionist, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(284, 284, 284))
        );

        jobReceptionist.getAccessibleContext().setAccessibleName("AcceptJob");
        jobReceptionist.getAccessibleContext().setAccessibleDescription("");

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

        acceptJob.setBackground(new java.awt.Color(61, 96, 146));
        acceptJob.setMaximumSize(new java.awt.Dimension(900, 700));
        acceptJob.setMinimumSize(new java.awt.Dimension(900, 700));
        acceptJob.setPreferredSize(new java.awt.Dimension(900, 700));

        acceptJobjPanel.setBackground(new java.awt.Color(61, 96, 146));
        acceptJobjPanel.setMaximumSize(new java.awt.Dimension(900, 700));
        acceptJobjPanel.setMinimumSize(new java.awt.Dimension(900, 700));
        acceptJobjPanel.setPreferredSize(new java.awt.Dimension(900, 700));

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
        totalLabel.setText("Total:");

        removeButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        removeButton.setText("Remove Job");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        selectStdJob.setMaximumSize(new java.awt.Dimension(250, 42));
        selectStdJob.setMinimumSize(new java.awt.Dimension(250, 42));
        selectStdJob.setPreferredSize(new java.awt.Dimension(250, 42));
        selectStdJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectStdJobActionPerformed(evt);
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

        jList1.setMaximumSize(new java.awt.Dimension(85, 507));
        jList1.setMinimumSize(new java.awt.Dimension(85, 507));
        jList1.setPreferredSize(new java.awt.Dimension(85, 507));
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        materialsjScrollPane.setViewportView(jList1);

        customerInfojTextField.setText("No Customer Selected");
        customerInfojTextField.setMaximumSize(new java.awt.Dimension(308, 42));
        customerInfojTextField.setMinimumSize(new java.awt.Dimension(308, 42));
        customerInfojTextField.setPreferredSize(new java.awt.Dimension(308, 42));

        jList3.setMaximumSize(new java.awt.Dimension(85, 507));
        jList3.setMinimumSize(new java.awt.Dimension(85, 507));
        jList3.setPreferredSize(new java.awt.Dimension(85, 507));
        stdJobsjScrollPane1.setViewportView(jList3);

        materialSubmittedLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        materialSubmittedLabel.setForeground(new java.awt.Color(255, 255, 255));
        materialSubmittedLabel.setText("Materials Submitted:");

        cancelAcceptJobButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cancelAcceptJobButton.setText("Cancel");
        cancelAcceptJobButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelAcceptJobButtonActionPerformed(evt);
            }
        });

        submitButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        totalAmountLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        totalAmountLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalAmountLabel.setText("£0.00");

        materialsjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialsjTextFieldActionPerformed(evt);
            }
        });

        surchargejTextField.setText("100");

        percentageLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        percentageLabel.setForeground(new java.awt.Color(255, 255, 255));
        percentageLabel.setText("%");

        surchargeLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        surchargeLabel.setForeground(new java.awt.Color(255, 255, 255));
        surchargeLabel.setText("Surcharge:");
        surchargeLabel.setName(""); // NOI18N

        jScrollPane3.setBorder(null);

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(61, 96, 146));
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        javax.swing.GroupLayout acceptJobjPanelLayout = new javax.swing.GroupLayout(acceptJobjPanel);
        acceptJobjPanel.setLayout(acceptJobjPanelLayout);
        acceptJobjPanelLayout.setHorizontalGroup(
            acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acceptJobjPanelLayout.createSequentialGroup()
                .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(acceptJobjPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(acceptJobjPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(acceptJobjPanelLayout.createSequentialGroup()
                                .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(selectStdJob, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addJobButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(selectPriority, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(materialSubmittedLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addMaterialButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(specialInstructionsLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(stdJobsjScrollPane1)
                                    .addComponent(materialsjScrollPane)
                                    .addComponent(materialsjTextField)
                                    .addGroup(acceptJobjPanelLayout.createSequentialGroup()
                                        .addComponent(removeButton)
                                        .addGap(36, 36, 36)
                                        .addComponent(surchargeLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(surchargejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(percentageLabel)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(specialInstructionjTextField)))
                            .addGroup(acceptJobjPanelLayout.createSequentialGroup()
                                .addGap(0, 18, Short.MAX_VALUE)
                                .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(acceptJobjPanelLayout.createSequentialGroup()
                                        .addComponent(searchCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(createCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(customerInfojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(acceptJobjPanelLayout.createSequentialGroup()
                                        .addComponent(cancelAcceptJobButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, acceptJobjPanelLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(45, 45, 45))
        );
        acceptJobjPanelLayout.setVerticalGroup(
            acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(acceptJobjPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(acceptJobjPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(createCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(customerInfojTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(materialsjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(materialSubmittedLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(acceptJobjPanelLayout.createSequentialGroup()
                        .addComponent(addMaterialButton)
                        .addGap(10, 10, 10))
                    .addComponent(materialsjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(acceptJobjPanelLayout.createSequentialGroup()
                        .addComponent(selectStdJob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addJobButton))
                    .addComponent(stdJobsjScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(surchargeLabel))
                    .addComponent(selectPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(acceptJobjPanelLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(percentageLabel)
                            .addComponent(surchargejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(specialInstructionsLabel)
                    .addComponent(specialInstructionjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(acceptJobjPanelLayout.createSequentialGroup()
                        .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalAmountLabel))
                        .addGap(31, 31, 31)
                        .addGroup(acceptJobjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelAcceptJobButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(134, Short.MAX_VALUE))
        );

        customerInfojTextField.setEditable(false);
        surchargejTextField.setVisible(false);
        surchargejTextField.getAccessibleContext().setAccessibleName("");
        percentageLabel.setVisible(false);
        percentageLabel.getAccessibleContext().setAccessibleName("");
        surchargeLabel.setVisible(false);
        surchargeLabel.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout acceptJobLayout = new javax.swing.GroupLayout(acceptJob);
        acceptJob.setLayout(acceptJobLayout);
        acceptJobLayout.setHorizontalGroup(
            acceptJobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(acceptJobjPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        acceptJobLayout.setVerticalGroup(
            acceptJobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acceptJobLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(acceptJobjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cardPanel1.add(acceptJob, "acceptJobPage");
        acceptJob.getAccessibleContext().setAccessibleName("");

        createCustomer.setMaximumSize(new java.awt.Dimension(900, 700));
        createCustomer.setMinimumSize(new java.awt.Dimension(900, 700));

        createCustomerjPanel.setBackground(new java.awt.Color(61, 96, 146));
        createCustomerjPanel.setMaximumSize(new java.awt.Dimension(900, 700));
        createCustomerjPanel.setMinimumSize(new java.awt.Dimension(900, 700));
        createCustomerjPanel.setPreferredSize(new java.awt.Dimension(900, 700));

        accountHolderNojLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        accountHolderNojLabel.setForeground(new java.awt.Color(255, 255, 255));
        accountHolderNojLabel.setText("Account Holder No:");
        accountHolderNojLabel.setMaximumSize(new java.awt.Dimension(128, 29));
        accountHolderNojLabel.setMinimumSize(new java.awt.Dimension(128, 29));
        accountHolderNojLabel.setPreferredSize(new java.awt.Dimension(128, 29));

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

        surnamejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        surnamejLabel.setForeground(new java.awt.Color(255, 255, 255));
        surnamejLabel.setText("Surname:");
        surnamejLabel.setMaximumSize(new java.awt.Dimension(128, 29));
        surnamejLabel.setMinimumSize(new java.awt.Dimension(128, 29));
        surnamejLabel.setPreferredSize(new java.awt.Dimension(128, 29));

        phoneNumberjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        phoneNumberjLabel.setForeground(new java.awt.Color(255, 255, 255));
        phoneNumberjLabel.setText("Phone Number:");
        phoneNumberjLabel.setMaximumSize(new java.awt.Dimension(128, 29));
        phoneNumberjLabel.setMinimumSize(new java.awt.Dimension(128, 29));
        phoneNumberjLabel.setPreferredSize(new java.awt.Dimension(128, 29));

        buildingNumberjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        buildingNumberjLabel.setForeground(new java.awt.Color(240, 240, 240));
        buildingNumberjLabel.setText("Building Number:");
        buildingNumberjLabel.setMaximumSize(new java.awt.Dimension(128, 29));
        buildingNumberjLabel.setMinimumSize(new java.awt.Dimension(128, 29));
        buildingNumberjLabel.setPreferredSize(new java.awt.Dimension(128, 29));

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
        prefixjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mr", "Mrs", "Miss" }));
        prefixjComboBox.setMaximumSize(new java.awt.Dimension(250, 42));
        prefixjComboBox.setMinimumSize(new java.awt.Dimension(250, 42));
        prefixjComboBox.setPreferredSize(new java.awt.Dimension(250, 42));
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

        accountHolderNojTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        accountHolderNojTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        accountHolderNojTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        accountHolderNojTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        accountHolderNojTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountHolderNojTextFieldActionPerformed(evt);
            }
        });

        postCodeField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        postCodeField.setMaximumSize(new java.awt.Dimension(250, 42));
        postCodeField.setMinimumSize(new java.awt.Dimension(250, 42));
        postCodeField.setPreferredSize(new java.awt.Dimension(250, 42));

        surnameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        surnameField.setMaximumSize(new java.awt.Dimension(250, 42));
        surnameField.setMinimumSize(new java.awt.Dimension(250, 42));
        surnameField.setPreferredSize(new java.awt.Dimension(250, 42));

        phoneNumberField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        phoneNumberField.setMaximumSize(new java.awt.Dimension(250, 42));
        phoneNumberField.setMinimumSize(new java.awt.Dimension(250, 42));
        phoneNumberField.setPreferredSize(new java.awt.Dimension(250, 42));

        firstNameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        firstNameField.setMaximumSize(new java.awt.Dimension(250, 42));
        firstNameField.setMinimumSize(new java.awt.Dimension(250, 42));
        firstNameField.setPreferredSize(new java.awt.Dimension(250, 42));
        firstNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameFieldActionPerformed(evt);
            }
        });

        buildingNumberField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buildingNumberField.setMaximumSize(new java.awt.Dimension(250, 42));
        buildingNumberField.setMinimumSize(new java.awt.Dimension(250, 42));
        buildingNumberField.setPreferredSize(new java.awt.Dimension(250, 42));
        buildingNumberField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildingNumberFieldActionPerformed(evt);
            }
        });

        streetNameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        streetNameField.setMaximumSize(new java.awt.Dimension(250, 42));
        streetNameField.setMinimumSize(new java.awt.Dimension(250, 42));
        streetNameField.setPreferredSize(new java.awt.Dimension(250, 42));

        cityField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cityField.setMaximumSize(new java.awt.Dimension(250, 42));
        cityField.setMinimumSize(new java.awt.Dimension(250, 42));
        cityField.setPreferredSize(new java.awt.Dimension(250, 42));

        cityjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        cityjLabel.setForeground(new java.awt.Color(255, 255, 255));
        cityjLabel.setText("City:");

        accountHolderNamejLabel.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        accountHolderNamejLabel.setForeground(new java.awt.Color(238, 238, 238));
        accountHolderNamejLabel.setText("Account Holder Name:");

        accountHolderNamejTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        accountHolderNamejTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        accountHolderNamejTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        accountHolderNamejTextField.setSize(new java.awt.Dimension(250, 42));
        accountHolderNamejTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountHolderNamejTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout createCustomerjPanelLayout = new javax.swing.GroupLayout(createCustomerjPanel);
        createCustomerjPanel.setLayout(createCustomerjPanelLayout);
        createCustomerjPanelLayout.setHorizontalGroup(
            createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createCustomerjPanelLayout.createSequentialGroup()
                .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createCustomerjPanelLayout.createSequentialGroup()
                        .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(createCustomerjPanelLayout.createSequentialGroup()
                                .addGap(222, 222, 222)
                                .addComponent(prefixjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(accountHolderNamejLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accountHolderNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(createCustomerjPanelLayout.createSequentialGroup()
                        .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(createCustomerjPanelLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(accountHolderNojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createCustomerjPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phoneNumberjLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(surnamejLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(firstNamejLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(accountHolderNojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prefixjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(surnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(createCustomerjPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createCustomerjPanelLayout.createSequentialGroup()
                        .addGap(542, 542, 542)
                        .addComponent(cancelCreationjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(createCustomerjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(createCustomerjPanelLayout.createSequentialGroup()
                        .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, createCustomerjPanelLayout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(postCodejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(postCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, createCustomerjPanelLayout.createSequentialGroup()
                                .addComponent(buildingNumberjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buildingNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addComponent(streetNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(streetNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(createCustomerjPanelLayout.createSequentialGroup()
                        .addGap(509, 509, 509)
                        .addComponent(cityjLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        createCustomerjPanelLayout.setVerticalGroup(
            createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createCustomerjPanelLayout.createSequentialGroup()
                .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createCustomerjPanelLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(accountHolderNojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createCustomerjPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(accountHolderNojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accountHolderNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountHolderNamejLabel))
                .addGap(9, 9, 9)
                .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prefixjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prefixjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(surnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(surnamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(phoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumberjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buildingNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buildingNumberjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(streetNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(streetNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createCustomerjPanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(postCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(postCodejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(createCustomerjPanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cityjLabel))))
                .addGap(36, 36, 36)
                .addGroup(createCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createCustomerjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelCreationjButton))
                .addContainerGap(223, Short.MAX_VALUE))
        );

        prefixjComboBox.getAccessibleContext().setAccessibleName("");
        accountHolderNojTextField.getAccessibleContext().setAccessibleName("");
        postCodeField.getAccessibleContext().setAccessibleName("");
        surnameField.getAccessibleContext().setAccessibleName("");
        phoneNumberField.getAccessibleContext().setAccessibleName("");
        firstNameField.getAccessibleContext().setAccessibleName("");
        buildingNumberField.getAccessibleContext().setAccessibleName("");
        streetNameField.getAccessibleContext().setAccessibleName("");
        cityField.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout createCustomerLayout = new javax.swing.GroupLayout(createCustomer);
        createCustomer.setLayout(createCustomerLayout);
        createCustomerLayout.setHorizontalGroup(
            createCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(createCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(createCustomerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(createCustomerjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        createCustomerLayout.setVerticalGroup(
            createCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(createCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(createCustomerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(createCustomerjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        cardPanel1.add(createCustomer, "createCustomerPanel");

        searchCustomerjPanel.setBackground(new java.awt.Color(61, 96, 146));
        searchCustomerjPanel.setMaximumSize(new java.awt.Dimension(900, 700));
        searchCustomerjPanel.setMinimumSize(new java.awt.Dimension(900, 700));
        searchCustomerjPanel.setPreferredSize(new java.awt.Dimension(900, 700));

        receptionistjPanel.setBackground(new java.awt.Color(34, 54, 81));
        receptionistjPanel.setMaximumSize(new java.awt.Dimension(900, 250));
        receptionistjPanel.setMinimumSize(new java.awt.Dimension(900, 250));
        receptionistjPanel.setPreferredSize(new java.awt.Dimension(900, 250));

        searchCustomerAccountNojLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        searchCustomerAccountNojLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchCustomerAccountNojLabel.setText("Customer account number:");

        searchContactFirstNamejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        searchContactFirstNamejLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchContactFirstNamejLabel.setText("Contact first name:");

        searchContactSurnamejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        searchContactSurnamejLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchContactSurnamejLabel.setText("Contact surname:");

        searchAccountHolderNamejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        searchAccountHolderNamejLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchAccountHolderNamejLabel.setText("Account Holder Name:");

        searchCustomerAccountNojTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        searchCustomerAccountNojTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        searchCustomerAccountNojTextField.setPreferredSize(new java.awt.Dimension(250, 42));

        searchContactFirstNamejTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        searchContactFirstNamejTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        searchContactFirstNamejTextField.setPreferredSize(new java.awt.Dimension(250, 42));

        searchContactSurnamejTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        searchContactSurnamejTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        searchContactSurnamejTextField.setPreferredSize(new java.awt.Dimension(250, 42));

        searchAccountHolderNamejTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        searchAccountHolderNamejTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        searchAccountHolderNamejTextField.setPreferredSize(new java.awt.Dimension(250, 42));

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
                    .addComponent(searchCustomerAccountNojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchContactFirstNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchContactSurnamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchAccountHolderNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        receptionistjPanelLayout.setVerticalGroup(
            receptionistjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receptionistjPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(receptionistjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchCustomerAccountNojLabel)
                    .addComponent(searchCustomerAccountNojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receptionistjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchContactFirstNamejLabel)
                    .addComponent(searchContactFirstNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receptionistjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchContactSurnamejLabel)
                    .addComponent(searchContactSurnamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receptionistjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchAccountHolderNamejLabel)
                    .addComponent(searchAccountHolderNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        searchCustomerFJobjButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        searchCustomerFJobjButton.setText("Search");
        searchCustomerFJobjButton.setMaximumSize(new java.awt.Dimension(163, 37));
        searchCustomerFJobjButton.setMinimumSize(new java.awt.Dimension(163, 37));
        searchCustomerFJobjButton.setPreferredSize(new java.awt.Dimension(163, 37));

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
        managerjPanel.setPreferredSize(new java.awt.Dimension(900, 250));
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

        streetNamejTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        streetNamejTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        streetNamejTextField.setPreferredSize(new java.awt.Dimension(250, 42));

        postCodejTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        postCodejTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        postCodejTextField.setPreferredSize(new java.awt.Dimension(250, 42));

        cityjTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        cityjTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        cityjTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        cityjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityjTextFieldActionPerformed(evt);
            }
        });

        phonejTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        phonejTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        phonejTextField.setPreferredSize(new java.awt.Dimension(250, 42));

        customerTypejComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Valued", "Normal" }));
        customerTypejComboBox.setMaximumSize(new java.awt.Dimension(200, 42));
        customerTypejComboBox.setMinimumSize(new java.awt.Dimension(200, 42));
        customerTypejComboBox.setPreferredSize(new java.awt.Dimension(200, 42));

        discountStatusjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        discountStatusjComboBox.setMaximumSize(new java.awt.Dimension(200, 42));
        discountStatusjComboBox.setMinimumSize(new java.awt.Dimension(200, 42));
        discountStatusjComboBox.setPreferredSize(new java.awt.Dimension(200, 42));

        inDefaultjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "True", "False" }));
        inDefaultjComboBox.setMaximumSize(new java.awt.Dimension(200, 42));
        inDefaultjComboBox.setMinimumSize(new java.awt.Dimension(200, 42));
        inDefaultjComboBox.setPreferredSize(new java.awt.Dimension(200, 42));

        registrationDatejComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        registrationDatejComboBox.setMaximumSize(new java.awt.Dimension(200, 42));
        registrationDatejComboBox.setMinimumSize(new java.awt.Dimension(200, 42));
        registrationDatejComboBox.setPreferredSize(new java.awt.Dimension(200, 42));

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
                            .addComponent(cityjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phonejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managerjPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managerjPanelLayout.createSequentialGroup()
                                .addComponent(streetNameSjLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(streetNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managerjPanelLayout.createSequentialGroup()
                                .addComponent(postcodeSjLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(postCodejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20)
                .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerTypeSjLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(accountStatusjLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(inDefaultSjLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(registrationDateSjLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(customerTypejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discountStatusjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inDefaultjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registrationDatejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        managerjPanelLayout.setVerticalGroup(
            managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managerjPanelLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(managerjPanelLayout.createSequentialGroup()
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(customerTypeSjLabel)
                            .addComponent(customerTypejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(streetNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managerjPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(streetNameSjLabel)
                        .addGap(17, 17, 17)))
                .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(managerjPanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(discountStatusjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(accountStatusjLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inDefaultSjLabel)
                            .addComponent(inDefaultjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registrationDateSjLabel)
                            .addComponent(registrationDatejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(managerjPanelLayout.createSequentialGroup()
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(postCodejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(postcodeSjLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cityjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(citySjLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(managerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(phonejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneSjLabel))))
                .addContainerGap())
        );

        isManagerjToggleButton.setText("Test");
        isManagerjToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isManagerjToggleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchCustomerjPanelLayout = new javax.swing.GroupLayout(searchCustomerjPanel);
        searchCustomerjPanel.setLayout(searchCustomerjPanelLayout);
        searchCustomerjPanelLayout.setHorizontalGroup(
            searchCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(receptionistjPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchCustomerjPanelLayout.createSequentialGroup()
                .addComponent(managerjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchCustomerjPanelLayout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(isManagerjToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelCustomerFJobjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchCustomerFJobjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        searchCustomerjPanelLayout.setVerticalGroup(
            searchCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchCustomerjPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(receptionistjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(managerjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(searchCustomerjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchCustomerFJobjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelCustomerFJobjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isManagerjToggleButton))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout searchCustomerLayout = new javax.swing.GroupLayout(searchCustomer);
        searchCustomer.setLayout(searchCustomerLayout);
        searchCustomerLayout.setHorizontalGroup(
            searchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(searchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(searchCustomerLayout.createSequentialGroup()
                    .addComponent(searchCustomerjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        searchCustomerLayout.setVerticalGroup(
            searchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(searchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(searchCustomerLayout.createSequentialGroup()
                    .addComponent(searchCustomerjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        cardPanel1.add(searchCustomer, "searchCustomerPanel");

        acceptLatePayment.setMaximumSize(new java.awt.Dimension(900, 700));
        acceptLatePayment.setMinimumSize(new java.awt.Dimension(900, 700));
        acceptLatePayment.setPreferredSize(new java.awt.Dimension(900, 700));

        acceptLatePaymentjPanel.setBackground(new java.awt.Color(61, 96, 146));
        acceptLatePaymentjPanel.setMaximumSize(new java.awt.Dimension(900, 700));
        acceptLatePaymentjPanel.setMinimumSize(new java.awt.Dimension(900, 700));
        acceptLatePaymentjPanel.setPreferredSize(new java.awt.Dimension(900, 700));

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

        cancelLatePaymentjButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cancelLatePaymentjButton.setText("Cancel");
        cancelLatePaymentjButton.setMaximumSize(new java.awt.Dimension(159, 37));
        cancelLatePaymentjButton.setMinimumSize(new java.awt.Dimension(159, 37));
        cancelLatePaymentjButton.setPreferredSize(new java.awt.Dimension(159, 37));
        cancelLatePaymentjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelLatePaymentjButtonActionPerformed(evt);
            }
        });

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

        submitLatePaymentjButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        submitLatePaymentjButton.setText("Submit");
        submitLatePaymentjButton.setMaximumSize(new java.awt.Dimension(159, 37));
        submitLatePaymentjButton.setMinimumSize(new java.awt.Dimension(159, 37));
        submitLatePaymentjButton.setPreferredSize(new java.awt.Dimension(159, 37));
        submitLatePaymentjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitLatePaymentjButtonActionPerformed(evt);
            }
        });

        TotalLatePayjTextField.setText("£");
        TotalLatePayjTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        TotalLatePayjTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        TotalLatePayjTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        TotalLatePayjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalLatePayjTextFieldActionPerformed(evt);
            }
        });

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
                        .addGap(165, 165, 165))
                    .addGroup(acceptLatePaymentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(acceptLatePaymentjPanelLayout.createSequentialGroup()
                            .addGroup(acceptLatePaymentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cancelLatePaymentjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TotalLatePayjTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(paymentTypeComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cardTypejComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(expiryDatejTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(submitLatePaymentjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(invoicejScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(138, Short.MAX_VALUE))
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
                .addGap(80, 80, 80)
                .addGroup(acceptLatePaymentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitLatePaymentjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelLatePaymentjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(159, Short.MAX_VALUE))
        );

        TotalLatePayjTextField.setEditable(false);

        javax.swing.GroupLayout acceptLatePaymentLayout = new javax.swing.GroupLayout(acceptLatePayment);
        acceptLatePayment.setLayout(acceptLatePaymentLayout);
        acceptLatePaymentLayout.setHorizontalGroup(
            acceptLatePaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(acceptLatePaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(acceptLatePaymentLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(acceptLatePaymentjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        acceptLatePaymentLayout.setVerticalGroup(
            acceptLatePaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(acceptLatePaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(acceptLatePaymentLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(acceptLatePaymentjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        cardPanel1.add(acceptLatePayment, "acceptLatePayment");

        searchInvoice.setMaximumSize(new java.awt.Dimension(900, 700));
        searchInvoice.setMinimumSize(new java.awt.Dimension(900, 700));
        searchInvoice.setPreferredSize(new java.awt.Dimension(900, 700));
        searchInvoice.setSize(new java.awt.Dimension(900, 700));

        searchInvoicejPanel.setBackground(new java.awt.Color(61, 96, 146));
        searchInvoicejPanel.setMaximumSize(new java.awt.Dimension(900, 700));
        searchInvoicejPanel.setPreferredSize(new java.awt.Dimension(900, 700));
        searchInvoicejPanel.setSize(new java.awt.Dimension(900, 700));

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

        searchInvoiceByInvoiceNojTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        searchInvoiceByInvoiceNojTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        searchInvoiceByInvoiceNojTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        searchInvoiceByInvoiceNojTextField.setSize(new java.awt.Dimension(250, 42));
        searchInvoiceByInvoiceNojTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInvoiceByInvoiceNojTextFieldActionPerformed(evt);
            }
        });
        searchInvoiceByInvoiceNojTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchInvoiceByInvoiceNojTextFieldKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchInvoiceByInvoiceNojTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchInvoiceByInvoiceNojTextFieldKeyReleased(evt);
            }
        });

        searchInvoiceByJobNumberjTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        searchInvoiceByJobNumberjTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        searchInvoiceByJobNumberjTextField.setPreferredSize(new java.awt.Dimension(250, 42));
        searchInvoiceByJobNumberjTextField.setSize(new java.awt.Dimension(250, 42));
        searchInvoiceByJobNumberjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInvoiceByJobNumberjTextFieldActionPerformed(evt);
            }
        });
        searchInvoiceByJobNumberjTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchInvoiceByJobNumberjTextFieldKeyTyped(evt);
            }
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
        searchInvoiceByInvoiceNojLabel.setSize(new java.awt.Dimension(124, 29));

        searchInvoiceByJobNumberjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        searchInvoiceByJobNumberjLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchInvoiceByJobNumberjLabel.setText("Job Number:");

        javax.swing.GroupLayout searchInvoicejPanelLayout = new javax.swing.GroupLayout(searchInvoicejPanel);
        searchInvoicejPanel.setLayout(searchInvoicejPanelLayout);
        searchInvoicejPanelLayout.setHorizontalGroup(
            searchInvoicejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchInvoicejPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cancelInvoiceSeletionjButton)
                .addGap(18, 18, 18)
                .addComponent(selectSelectedInvoicejButton)
                .addGap(69, 69, 69))
            .addGroup(searchInvoicejPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(searchInvoicejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(searchInvoicejPanelLayout.createSequentialGroup()
                        .addComponent(searchInvoiceByInvoiceNojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchInvoiceByInvoiceNojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchInvoiceByJobNumberjLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchInvoiceByJobNumberjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 50, Short.MAX_VALUE))
        );
        searchInvoicejPanelLayout.setVerticalGroup(
            searchInvoicejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchInvoicejPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchInvoicejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchInvoiceByInvoiceNojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchInvoiceByInvoiceNojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchInvoiceByJobNumberjLabel)
                    .addComponent(searchInvoiceByJobNumberjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchInvoicejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelInvoiceSeletionjButton)
                    .addComponent(selectSelectedInvoicejButton))
                .addContainerGap(176, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout searchInvoiceLayout = new javax.swing.GroupLayout(searchInvoice);
        searchInvoice.setLayout(searchInvoiceLayout);
        searchInvoiceLayout.setHorizontalGroup(
            searchInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(searchInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(searchInvoiceLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(searchInvoicejPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        searchInvoiceLayout.setVerticalGroup(
            searchInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(searchInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(searchInvoiceLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(searchInvoicejPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        searchInvoicejPanel.getAccessibleContext().setAccessibleName("");

        cardPanel1.add(searchInvoice, "searchInvoicePage");

        AutoBackupConfigjPanel.setBackground(new java.awt.Color(61, 96, 146));
        AutoBackupConfigjPanel.setMaximumSize(new java.awt.Dimension(900, 700));
        AutoBackupConfigjPanel.setMinimumSize(new java.awt.Dimension(900, 700));
        AutoBackupConfigjPanel.setPreferredSize(new java.awt.Dimension(900, 700));
        AutoBackupConfigjPanel.setSize(new java.awt.Dimension(900, 700));

        autoBackupLocationjTextField.setMaximumSize(new java.awt.Dimension(250, 42));
        autoBackupLocationjTextField.setMinimumSize(new java.awt.Dimension(250, 42));
        autoBackupLocationjTextField.setPreferredSize(new java.awt.Dimension(250, 42));

        backupLocationjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        backupLocationjLabel.setForeground(new java.awt.Color(255, 255, 255));
        backupLocationjLabel.setText("Backup Location:");

        backupFrequencyjLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        backupFrequencyjLabel.setForeground(new java.awt.Color(255, 255, 255));
        backupFrequencyjLabel.setText("Backup Frequency:");

        backupModejLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        backupModejLabel.setForeground(new java.awt.Color(255, 255, 255));
        backupModejLabel.setText("Backup Mode:");

        backupModejComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manual", "Reminder", "Auto" }));
        backupModejComboBox.setMaximumSize(new java.awt.Dimension(250, 42));
        backupModejComboBox.setMinimumSize(new java.awt.Dimension(250, 42));
        backupModejComboBox.setPreferredSize(new java.awt.Dimension(250, 42));

        backupFrequencyjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hourly", "Every 12 hours", "Daily", "Weekly", "Monthly", "Every 3 months", "Every 6 months" }));
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

        cancelAutoBackupConfigjButton.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        cancelAutoBackupConfigjButton.setText("Cancel");
        cancelAutoBackupConfigjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelAutoBackupConfigjButtonActionPerformed(evt);
            }
        });

        confirmAutoBackupConfigjButton.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        confirmAutoBackupConfigjButton.setText("Confirm");
        confirmAutoBackupConfigjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmAutoBackupConfigjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AutoBackupConfigjPanelLayout = new javax.swing.GroupLayout(AutoBackupConfigjPanel);
        AutoBackupConfigjPanel.setLayout(AutoBackupConfigjPanelLayout);
        AutoBackupConfigjPanelLayout.setHorizontalGroup(
            AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AutoBackupConfigjPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelAutoBackupConfigjButton)
                .addGap(18, 18, 18)
                .addComponent(confirmAutoBackupConfigjButton)
                .addGap(114, 114, 114))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AutoBackupConfigjPanelLayout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backupFrequencyjLabel)
                    .addComponent(backupLocationjLabel)
                    .addComponent(backupModejLabel))
                .addGap(18, 18, 18)
                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backupModejComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backupFrequencyjComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(autoBackupLocationjTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AutoBackupConfigjPanelLayout.createSequentialGroup()
                        .addComponent(selectAutoBackupLocationjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(203, 203, 203))
        );
        AutoBackupConfigjPanelLayout.setVerticalGroup(
            AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AutoBackupConfigjPanelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backupModejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backupModejLabel))
                .addGap(18, 18, 18)
                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backupFrequencyjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backupFrequencyjLabel))
                .addGap(18, 18, 18)
                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backupLocationjLabel)
                    .addComponent(autoBackupLocationjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectAutoBackupLocationjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(AutoBackupConfigjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelAutoBackupConfigjButton)
                    .addComponent(confirmAutoBackupConfigjButton))
                .addContainerGap(366, Short.MAX_VALUE))
        );

        TotalLatePayjTextField.setEditable(false);

        cardPanel1.add(AutoBackupConfigjPanel, "AutoBackupConfig");

        javax.swing.GroupLayout BackupLayout = new javax.swing.GroupLayout(Backup);
        Backup.setLayout(BackupLayout);
        BackupLayout.setHorizontalGroup(
            BackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        BackupLayout.setVerticalGroup(
            BackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );

        cardPanel1.add(Backup, "Backup");

        cardPanel2.setBackground(new java.awt.Color(204, 255, 204));
        cardPanel2.setPreferredSize(new java.awt.Dimension(900, 60));
        cardPanel2.setLayout(new java.awt.CardLayout());

        welcomeBar1.setBackground(new java.awt.Color(33, 53, 80));
        welcomeBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        welcomeBar1.setMaximumSize(new java.awt.Dimension(900, 60));
        welcomeBar1.setMinimumSize(new java.awt.Dimension(900, 60));

        javax.swing.GroupLayout welcomeBar1Layout = new javax.swing.GroupLayout(welcomeBar1);
        welcomeBar1.setLayout(welcomeBar1Layout);
        welcomeBar1Layout.setHorizontalGroup(
            welcomeBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 896, Short.MAX_VALUE)
        );
        welcomeBar1Layout.setVerticalGroup(
            welcomeBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

        cardPanel2.add(welcomeBar1, "welcomeBar1");

        welcomeBar2.setBackground(new java.awt.Color(33, 53, 80));
        welcomeBar2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        javax.swing.GroupLayout welcomeBar2Layout = new javax.swing.GroupLayout(welcomeBar2);
        welcomeBar2.setLayout(welcomeBar2Layout);
        welcomeBar2Layout.setHorizontalGroup(
            welcomeBar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomeBar2Layout.createSequentialGroup()
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 793, Short.MAX_VALUE))
        );
        welcomeBar2Layout.setVerticalGroup(
            welcomeBar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );

        cardPanel2.add(welcomeBar2, "welcomeBar2");

        welcomeBar3.setBackground(new java.awt.Color(33, 53, 80));
        welcomeBar3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        officeManagerText.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        officeManagerText.setForeground(new java.awt.Color(255, 255, 255));
        officeManagerText.setText("Office Manager");

        officeManagerWelcomeText.setFont(new java.awt.Font("Lucida Grande", 1, 26)); // NOI18N
        officeManagerWelcomeText.setForeground(new java.awt.Color(255, 255, 255));
        officeManagerWelcomeText.setText("Welcome, User");

        logOutButton.setBackground(new java.awt.Color(40, 64, 97));
        logOutButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        logOutButton.setForeground(new java.awt.Color(255, 255, 255));
        logOutButton.setText("Log out");
        logOutButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logOutButton.setOpaque(true);
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout welcomeBar3Layout = new javax.swing.GroupLayout(welcomeBar3);
        welcomeBar3.setLayout(welcomeBar3Layout);
        welcomeBar3Layout.setHorizontalGroup(
            welcomeBar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomeBar3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(officeManagerText, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146)
                .addComponent(officeManagerWelcomeText, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        welcomeBar3Layout.setVerticalGroup(
            welcomeBar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, welcomeBar3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(welcomeBar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(officeManagerText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(officeManagerWelcomeText, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(logOutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout homePageOMBarLayout = new javax.swing.GroupLayout(homePageOMBar);
        homePageOMBar.setLayout(homePageOMBarLayout);
        homePageOMBarLayout.setHorizontalGroup(
            homePageOMBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePageOMBarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(welcomeBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        homePageOMBarLayout.setVerticalGroup(
            homePageOMBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePageOMBarLayout.createSequentialGroup()
                .addComponent(welcomeBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cardPanel2.add(homePageOMBar, "homePageOMBar");

        welcomeBar4.setBackground(new java.awt.Color(33, 53, 80));
        welcomeBar4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        officeManagerText1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        officeManagerText1.setForeground(new java.awt.Color(255, 255, 255));
        officeManagerText1.setText("Shift Manager");

        officeManagerWelcomeText1.setFont(new java.awt.Font("Lucida Grande", 1, 26)); // NOI18N
        officeManagerWelcomeText1.setForeground(new java.awt.Color(255, 255, 255));
        officeManagerWelcomeText1.setText("Welcome, User");

        logOutButton1.setBackground(new java.awt.Color(40, 64, 97));
        logOutButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        logOutButton1.setForeground(new java.awt.Color(255, 255, 255));
        logOutButton1.setText("Log out");
        logOutButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logOutButton1.setOpaque(true);
        logOutButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout welcomeBar4Layout = new javax.swing.GroupLayout(welcomeBar4);
        welcomeBar4.setLayout(welcomeBar4Layout);
        welcomeBar4Layout.setHorizontalGroup(
            welcomeBar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomeBar4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(officeManagerText1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146)
                .addComponent(officeManagerWelcomeText1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addComponent(logOutButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        welcomeBar4Layout.setVerticalGroup(
            welcomeBar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, welcomeBar4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(welcomeBar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(officeManagerText1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(officeManagerWelcomeText1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(logOutButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout homePagesSMBarLayout = new javax.swing.GroupLayout(homePagesSMBar);
        homePagesSMBar.setLayout(homePagesSMBarLayout);
        homePagesSMBarLayout.setHorizontalGroup(
            homePagesSMBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePagesSMBarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(welcomeBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        homePagesSMBarLayout.setVerticalGroup(
            homePagesSMBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePagesSMBarLayout.createSequentialGroup()
                .addComponent(welcomeBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cardPanel2.add(homePagesSMBar, "homePageSMBar");

        homePageTBar.setBackground(new java.awt.Color(33, 53, 80));
        homePageTBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        homePageTBar.setMaximumSize(new java.awt.Dimension(900, 60));
        homePageTBar.setMinimumSize(new java.awt.Dimension(900, 60));

        homePageTechnician.setBackground(new java.awt.Color(33, 53, 80));
        homePageTechnician.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        officeManagerText2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        officeManagerText2.setForeground(new java.awt.Color(255, 255, 255));
        officeManagerText2.setText("Technician");

        officeManagerWelcomeText2.setFont(new java.awt.Font("Lucida Grande", 1, 26)); // NOI18N
        officeManagerWelcomeText2.setForeground(new java.awt.Color(255, 255, 255));
        officeManagerWelcomeText2.setText("Welcome, User");

        logOutButton2.setBackground(new java.awt.Color(40, 64, 97));
        logOutButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        logOutButton2.setForeground(new java.awt.Color(255, 255, 255));
        logOutButton2.setText("Log out");
        logOutButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logOutButton2.setOpaque(true);
        logOutButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout homePageTechnicianLayout = new javax.swing.GroupLayout(homePageTechnician);
        homePageTechnician.setLayout(homePageTechnicianLayout);
        homePageTechnicianLayout.setHorizontalGroup(
            homePageTechnicianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePageTechnicianLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(officeManagerText2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146)
                .addComponent(officeManagerWelcomeText2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addComponent(logOutButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        homePageTechnicianLayout.setVerticalGroup(
            homePageTechnicianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePageTechnicianLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homePageTechnicianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(officeManagerText2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(officeManagerWelcomeText2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(logOutButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout homePageTBarLayout = new javax.swing.GroupLayout(homePageTBar);
        homePageTBar.setLayout(homePageTBarLayout);
        homePageTBarLayout.setHorizontalGroup(
            homePageTBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePageTBarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(homePageTechnician, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        homePageTBarLayout.setVerticalGroup(
            homePageTBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePageTBarLayout.createSequentialGroup()
                .addComponent(homePageTechnician, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cardPanel2.add(homePageTBar, "homePageT");

        homePageRBar.setBackground(new java.awt.Color(33, 53, 80));
        homePageRBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        homePageRBar.setMaximumSize(new java.awt.Dimension(900, 60));
        homePageRBar.setMinimumSize(new java.awt.Dimension(900, 60));

        homePageReceptionist.setBackground(new java.awt.Color(33, 53, 80));
        homePageReceptionist.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        officeManagerText3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        officeManagerText3.setForeground(new java.awt.Color(255, 255, 255));
        officeManagerText3.setText("Receptionist");

        officeManagerWelcomeText3.setFont(new java.awt.Font("Lucida Grande", 1, 26)); // NOI18N
        officeManagerWelcomeText3.setForeground(new java.awt.Color(255, 255, 255));
        officeManagerWelcomeText3.setText("Welcome, User");

        logOutButton3.setBackground(new java.awt.Color(40, 64, 97));
        logOutButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        logOutButton3.setForeground(new java.awt.Color(255, 255, 255));
        logOutButton3.setText("Log out");
        logOutButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logOutButton3.setOpaque(true);
        logOutButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout homePageReceptionistLayout = new javax.swing.GroupLayout(homePageReceptionist);
        homePageReceptionist.setLayout(homePageReceptionistLayout);
        homePageReceptionistLayout.setHorizontalGroup(
            homePageReceptionistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePageReceptionistLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(officeManagerText3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146)
                .addComponent(officeManagerWelcomeText3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addComponent(logOutButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        homePageReceptionistLayout.setVerticalGroup(
            homePageReceptionistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePageReceptionistLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homePageReceptionistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(officeManagerText3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(officeManagerWelcomeText3, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(logOutButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout homePageRBarLayout = new javax.swing.GroupLayout(homePageRBar);
        homePageRBar.setLayout(homePageRBarLayout);
        homePageRBarLayout.setHorizontalGroup(
            homePageRBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePageRBarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(homePageReceptionist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        homePageRBarLayout.setVerticalGroup(
            homePageRBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePageRBarLayout.createSequentialGroup()
                .addComponent(homePageReceptionist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cardPanel2.add(homePageRBar, "homePageR");

        acceptJobMBar.setBackground(new java.awt.Color(33, 53, 80));
        acceptJobMBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        acceptJobMBar.setMaximumSize(new java.awt.Dimension(900, 60));
        acceptJobMBar.setMinimumSize(new java.awt.Dimension(900, 60));
        acceptJobMBar.setPreferredSize(new java.awt.Dimension(900, 60));

        acceptJobLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        acceptJobLabel.setForeground(new java.awt.Color(255, 255, 255));
        acceptJobLabel.setText("Acccept Job");

        javax.swing.GroupLayout acceptJobMBarLayout = new javax.swing.GroupLayout(acceptJobMBar);
        acceptJobMBar.setLayout(acceptJobMBarLayout);
        acceptJobMBarLayout.setHorizontalGroup(
            acceptJobMBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(acceptJobMBarLayout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(acceptJobLabel)
                .addContainerGap(341, Short.MAX_VALUE))
        );
        acceptJobMBarLayout.setVerticalGroup(
            acceptJobMBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acceptJobMBarLayout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(acceptJobLabel))
        );

        cardPanel2.add(acceptJobMBar, "acceptJobBar");

        acceptLatePaymentMBar.setBackground(new java.awt.Color(33, 53, 80));
        acceptLatePaymentMBar.setMaximumSize(new java.awt.Dimension(900, 60));
        acceptLatePaymentMBar.setMinimumSize(new java.awt.Dimension(900, 60));
        acceptLatePaymentMBar.setPreferredSize(new java.awt.Dimension(900, 60));

        acceptLatePaymentLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        acceptLatePaymentLabel.setForeground(new java.awt.Color(255, 255, 255));
        acceptLatePaymentLabel.setText("Acccept Late Payment");

        javax.swing.GroupLayout acceptLatePaymentMBarLayout = new javax.swing.GroupLayout(acceptLatePaymentMBar);
        acceptLatePaymentMBar.setLayout(acceptLatePaymentMBarLayout);
        acceptLatePaymentMBarLayout.setHorizontalGroup(
            acceptLatePaymentMBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acceptLatePaymentMBarLayout.createSequentialGroup()
                .addContainerGap(249, Short.MAX_VALUE)
                .addComponent(acceptLatePaymentLabel)
                .addGap(249, 249, 249))
        );
        acceptLatePaymentMBarLayout.setVerticalGroup(
            acceptLatePaymentMBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acceptLatePaymentMBarLayout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addComponent(acceptLatePaymentLabel))
        );

        cardPanel2.add(acceptLatePaymentMBar, "acceptLatePaymentBar");

        searchCustomerMBar.setBackground(new java.awt.Color(33, 53, 80));
        searchCustomerMBar.setMaximumSize(new java.awt.Dimension(900, 60));
        searchCustomerMBar.setMinimumSize(new java.awt.Dimension(900, 60));
        searchCustomerMBar.setPreferredSize(new java.awt.Dimension(900, 60));

        searchCustomerLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        searchCustomerLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchCustomerLabel.setText("Search Customer");

        javax.swing.GroupLayout searchCustomerMBarLayout = new javax.swing.GroupLayout(searchCustomerMBar);
        searchCustomerMBar.setLayout(searchCustomerMBarLayout);
        searchCustomerMBarLayout.setHorizontalGroup(
            searchCustomerMBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchCustomerMBarLayout.createSequentialGroup()
                .addContainerGap(296, Short.MAX_VALUE)
                .addComponent(searchCustomerLabel)
                .addGap(295, 295, 295))
        );
        searchCustomerMBarLayout.setVerticalGroup(
            searchCustomerMBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchCustomerMBarLayout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addComponent(searchCustomerLabel))
        );

        cardPanel2.add(searchCustomerMBar, "searchCustomerBar");

        createCustomerMBar.setBackground(new java.awt.Color(33, 53, 80));
        createCustomerMBar.setMaximumSize(new java.awt.Dimension(900, 60));
        createCustomerMBar.setMinimumSize(new java.awt.Dimension(900, 60));

        createCustomerLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        createCustomerLabel.setForeground(new java.awt.Color(255, 255, 255));
        createCustomerLabel.setText("Create Customer");

        javax.swing.GroupLayout createCustomerMBarLayout = new javax.swing.GroupLayout(createCustomerMBar);
        createCustomerMBar.setLayout(createCustomerMBarLayout);
        createCustomerMBarLayout.setHorizontalGroup(
            createCustomerMBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createCustomerMBarLayout.createSequentialGroup()
                .addContainerGap(347, Short.MAX_VALUE)
                .addComponent(createCustomerLabel)
                .addGap(249, 249, 249))
        );
        createCustomerMBarLayout.setVerticalGroup(
            createCustomerMBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createCustomerMBarLayout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addComponent(createCustomerLabel))
        );

        cardPanel2.add(createCustomerMBar, "createCustomerBar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cardPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cardPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "welcome");
        card2.show(cardPanel2, "welcomeBar1");
    }//GEN-LAST:event_backButtonActionPerformed

    private void acceptPaymentReceptionistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptPaymentReceptionistActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "acceptLatePayment");
        card2.show(cardPanel2, "acceptLatePaymentBar");
    }//GEN-LAST:event_acceptPaymentReceptionistActionPerformed

    private void homePageRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homePageRActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "receptionistHomePage");
        card2.show(cardPanel2, "homePageR");
    }//GEN-LAST:event_homePageRActionPerformed

    private void logOutButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButton3ActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "welcome");
        card2.show(cardPanel2, "welcomeBar1");
    }//GEN-LAST:event_logOutButton3ActionPerformed

    private void logOutButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButton2ActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "welcome");
        card2.show(cardPanel2, "welcomeBar2");
    }//GEN-LAST:event_logOutButton2ActionPerformed

    private void logOutButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButton1ActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "welcome");
        card2.show(cardPanel2, "welcomeBar1");
    }//GEN-LAST:event_logOutButton1ActionPerformed

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "welcome");
        card2.show(cardPanel2, "welcomeBar2");
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void jobReceptionistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jobReceptionistActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "acceptJobPage");
        card2.show(cardPanel2, "acceptJobBar");
        // refreshes the list of standard jobs avaliable from the database
        selectStdJob.setModel(new javax.swing.DefaultComboBoxModel<>(controller.getStandardJobs()));
    }//GEN-LAST:event_jobReceptionistActionPerformed

    private void addMaterialButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMaterialButtonActionPerformed
        // TODO add your handling code here:
        // checks to see if the user has entereds the correct format for materials
        // if so, it will add the inputted material into the arraylist 
        // then update a new model which will be used to display the list of
        // materials.
        if (materialsjTextField.getText().matches("[a-z,A-Z]([a-z,A-Z,\\s])+[a-z,A-Z]")) {
            ++mCount;
            String material = materialsjTextField.getText();
            materials.add(new Material(mCount,material));
//            System.out.println(material);
            materialsjTextField.setText("");
            
            DefaultListModel t = new DefaultListModel();
            
            for (int i = 0; i < materials.size(); ++i) {
                t.addElement(materials.get(i).getMaterialDescription());
            }
            
            jList1.setModel(t);
            if (!jTextArea2.getText().equals(""))
                jTextArea2.setText("");
        } else if (materialsjTextField.getText().matches("\\s+")) {
            System.out.println("You need to enter something other than just white space");
            jTextArea2.setText("You need to enter something other than just white space");
            materialsjTextField.setText("");
        } else {
            System.out.println("You need to enter something");
            jTextArea2.setText("You need to enter something");
        }
    }//GEN-LAST:event_addMaterialButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here
//        jList3.getModel().getElementAt(jList3.getSelectedIndex());
        // checks to see if a row is selected in a list, if it is
        // it will remove the selected row from the list and the array of standard jobs
        if (jList3.isSelectedIndex(jList3.getSelectedIndex())) {
            stdJobs.remove(jList3.getSelectedIndex());
            t2.remove(jList3.getSelectedIndex());
        } else {
            System.out.println("You need to select a standard job to delete");
        }
    }//GEN-LAST:event_removeButtonActionPerformed

    private void searchCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCustomerButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "searchCustomerPanel");
        card2.show(cardPanel2, "searchCustomerBar");
    }//GEN-LAST:event_searchCustomerButtonActionPerformed

    private void createCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createCustomerButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "createCustomerPanel");
        card2.show(cardPanel2, "createCustomerBar");
    }//GEN-LAST:event_createCustomerButtonActionPerformed

    private void addJobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJobButtonActionPerformed
        // TODO add your handling code here:
        //selectStdJob.getSelectedItem();
        // adds a selected standard job to a list and arraylist
        double total = 0;
        t2.clear();
        stdJobs.add(new StandardJob("12","Test",21));
        selectStdJob.getSelectedItem();
            
        for (int i = 0; i < stdJobs.size(); ++i) {
            t2.addElement(stdJobs.get(i).getJobDescription());
            total += stdJobs.get(i).getPrice(); // takse the price value from the arraylist to be totaled
        }
            
        jList3.setModel(t2); // updates the list with new values
        totalAmountLabel.setText("£" + total);
    }//GEN-LAST:event_addJobButtonActionPerformed

    private void cancelAcceptJobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelAcceptJobButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "receptionistHomePage");
        card2.show(cardPanel2, "homePageR");
        materials.clear();
        mCount = 0;
        t2.clear();
    }//GEN-LAST:event_cancelAcceptJobButtonActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitButtonActionPerformed

    private void materialsjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialsjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_materialsjTextFieldActionPerformed

    private void accountHolderNojTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountHolderNojTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accountHolderNojTextFieldActionPerformed

    private void prefixjComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prefixjComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prefixjComboBoxActionPerformed

    private void firstNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameFieldActionPerformed

    private void cancelLatePaymentjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelLatePaymentjButtonActionPerformed
        // TODO add your handling code here:
        // invoice informaiton and total 
        TotalLatePayjTextField.setText("");
        t.clear();
        
        // clears the data for card detials
        expiryDatejTextField.setText("");
        last4DigitjTextField.setText("");
        
        card1.show(cardPanel1, "receptionistHomePage");
        card2.show(cardPanel2, "homePageR");
    }//GEN-LAST:event_cancelLatePaymentjButtonActionPerformed

    private void cancelCreationjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelCreationjButtonActionPerformed
        // TODO add your handling code here:
        // sets the fields that the user inserts to to null
        accountHolderNojTextField.setText("");
        accountHolderNamejTextField.setText("");
        postCodeField.setText("");
        surnameField.setText("");
        phoneNumberField.setText("");
        firstNameField.setText("");
        buildingNumberField.setText("");
        streetNameField.setText("");
        cityField.setText("");
        
        // returns user back to the accept job page
        card1.show(cardPanel1, "acceptJobPage");
        card2.show(cardPanel2, "acceptJobBar");
    }//GEN-LAST:event_cancelCreationjButtonActionPerformed

    private void createCustomerjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createCustomerjButtonActionPerformed
        // TODO add your handling code here: 
        if (isAllEnteredCorrectly()) { // if this method turns true account will be created based on inserted information 
            Customer customerAccount;
            // variables holding information for customer account creation
            String accountHolderNo = accountHolderNojTextField.getText();
            String accountHolderName = accountHolderNamejTextField.getText();
            String prefix = prefixjComboBox.getSelectedItem().toString();
            String firstName = firstNameField.getText();
            String surname = surnameField.getText();
            String streetName = streetNameField.getText();
            String postCode = postCodeField.getText();
            String city = cityField.getText();
            String phoneNumber = phoneNumberField.getText();
            String buildingNo = buildingNumberField.getText();

            // assigns the variables to the class
            customerAccount = new Customer(accountHolderNo, accountHolderName, prefix, firstName, surname, streetName, postCode, city, phoneNumber, buildingNo);
            controller.createCustomerAccount(customerAccount);

            // sets the fields that the customer input back to null
            accountHolderNojTextField.setText("");
            accountHolderNamejTextField.setText("");
            postCodeField.setText("");
            surnameField.setText("");
            phoneNumberField.setText("");
            firstNameField.setText("");
            buildingNumberField.setText("");
            streetNameField.setText("");
            cityField.setText("");

            // test to check if the code manages to complie and reach this part of the flow
            System.out.println("Account created");
            //System.out.println(customerAccount.getRegistrationDate());
        } else {
            System.out.println("Missing values or invalid information, need to enter before creating customer account");
        }
    }//GEN-LAST:event_createCustomerjButtonActionPerformed

    private boolean isAllEnteredCorrectly() {
        // returns true of the fields that the user is required to enter is not false and if they are the right format
        return
        !accountHolderNojTextField.getText().equals("") && accountHolderNojTextField.getText().matches("([0-9])+") &&
        !accountHolderNamejTextField.getText().equals("") &&
        !firstNameField.getText().equals("") &&
        !surnameField.getText().equals("") &&
        !streetNameField.getText().equals("") &&
        !postCodeField.getText().equals("") &&
        !cityField.getText().equals("") &&
        !phoneNumberField.getText().equals("") && phoneNumberField.getText().matches("[0][0-9]{9}")&&
        !buildingNumberField.getText().equals("") && buildingNumberField.getText().matches("[0-9]{2}");
    }
    
    private void buildingNumberFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildingNumberFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buildingNumberFieldActionPerformed

    private void cancelCustomerFJobjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelCustomerFJobjButtonActionPerformed
        // TODO add your handling code here: 
        // clears receptionist search field when cancel button is entered 
        searchCustomerAccountNojTextField.setText("");
        searchContactFirstNamejTextField.setText("");
        searchContactSurnamejTextField.setText("");
        searchAccountHolderNamejTextField.setText("");
        
        // clears office manager search fields
        streetNamejTextField.setText("");
        postCodejTextField.setText("");
        cityjTextField.setText("");
        phonejTextField.setText("");
                
        card1.show(cardPanel1, "acceptJobPage");
        card2.show(cardPanel2, "acceptJobBar");
    }//GEN-LAST:event_cancelCustomerFJobjButtonActionPerformed

    private void cityjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cityjTextFieldActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jList1ValueChanged

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

    private void selectPriorityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectPriorityActionPerformed
        // TODO add your handling code here:
        // if the selected list is Stipulated then the extra field needed to calculate
        // Stipulated are shown.
        if (selectPriority.getSelectedItem().toString().equals("Stipulated")) {
            surchargeLabel.setVisible(true);
            surchargejTextField.setVisible(true);
            percentageLabel.setVisible(true);
        } else {
            surchargeLabel.setVisible(false);
            surchargejTextField.setVisible(false);
            percentageLabel.setVisible(false);
        }
    }//GEN-LAST:event_selectPriorityActionPerformed
    
    private void selectStdJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectStdJobActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_selectStdJobActionPerformed

    private void isManagerjToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isManagerjToggleButtonActionPerformed
        // TODO add your handling code here:
        // toggles between showing the office manager search and not showing  based on a button being selected
        if (isManagerjToggleButton.isSelected())
            managerjPanel.setVisible(true);
        else
            managerjPanel.setVisible(false);
    }//GEN-LAST:event_isManagerjToggleButtonActionPerformed

    private void submitLatePaymentjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitLatePaymentjButtonActionPerformed
        // TODO add your handling code here:
        
        if (invoicejList.getModel().getSize() != 0) { // first check to see if there is a invoice selected
            switch (paymentTypeComboBox.getSelectedItem().toString()) {
                case "Card":
                    System.out.println("card");
                    // if card is selected for payment type
                    if (// checks to see if format for the card info is entered correctly
                            expiryDatejTextField.getText().matches("[0-9]{4}[/]{1}[0-9]{2}[/]{1}[0-9]{2}")
                            && last4DigitjTextField.getText().matches("[0-9]{4}")
                            ) {
                try {
                    // grabs infor for card payment
                    ++paymentNo;
                    String[] paymentToken = TotalLatePayjTextField.getText().split("\\s");
                    final double total = Double.parseDouble(paymentToken[1]);
                    final String paymentType = paymentTypeComboBox.getSelectedItem().toString();
                    
                    
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String currentDate = new Date().toString();
                    Date paymentDate = dateFormat.parse(currentDate);
                    
                    final int invoiceNumber = invoice.getInvoiceNo();
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
                    
                    controller.recordPayment(paymentRecord, paymentTypeComboBox.getSelectedItem().toString(), invoice, card);
                    
                    //System.out.println("payment info attained");
                    
                    // clears the model and the total
                    TotalLatePayjTextField.setText("");
                    t.clear();
                    
                    // clears the data for card detials
                    expiryDatejTextField.setText("");
                    last4DigitjTextField.setText("");
                } catch (ParseException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }   
                    break;
                case "Cash":
                    // if card is selected for payment type
                    // grabs info for cash payment
//                    ++paymentNo;
//                    String[] paymentToken = TotalLatePayjTextField.getText().split("\\s");
//                    final double total = Double.parseDouble(paymentToken[1]);
//                    final String paymentType = paymentTypeComboBox.getSelectedItem().toString();
//                    final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
////                    Date paymentDate = dateFormat.parse(dateFormat.format(new Date()));
//                    final int invoiceNumber = invoice.getInvoiceNo();
//                    
//                    final Payment paymentRecord = new PaymentCash(
//                            paymentNo,
//                            total,
//                            paymentType,
//                            //paymentDate,
//                            invoiceNumber
//                    );
//                    
//                    controller.recordPayment(paymentRecord, paymentTypeComboBox.getSelectedItem().toString(), invoice);
//                    // clears the model and the total 
//                    TotalLatePayjTextField.setText("");
//                    t.clear();
//                    // clears the data for card detials
//                    expiryDatejTextField.setText("");
//                    last4DigitjTextField.setText("");
                    break;
                default:
                    System.out.println("Have not chosen a payment type");
                    break;
            }
        } else {
            System.out.println("Cannot make payment");
        }
    }//GEN-LAST:event_submitLatePaymentjButtonActionPerformed

    private void selectInvoicejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectInvoicejButtonActionPerformed
        // TODO add your handling code here:
        // updates the tables for selecting invoices
        m = (DefaultTableModel) invoicejTable.getModel(); // grabs the current model
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
                m.addRow(rowData);
            }
            
            // change page
            card1.show(cardPanel1, "searchInvoicePage");
            card2.show(cardPanel2, "acceptLatePaymentBar"); 
        } catch (ParseException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_selectInvoicejButtonActionPerformed

    private void cancelInvoiceSeletionjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelInvoiceSeletionjButtonActionPerformed
        // TODO add your handling code here:
        searchInvoiceByInvoiceNojTextField.setText("");
        searchInvoiceByJobNumberjTextField.setText("");
        m.setRowCount(0);
        card1.show(cardPanel1, "acceptLatePayment");
        card2.show(cardPanel2, "acceptLatePaymentBar");
    }//GEN-LAST:event_cancelInvoiceSeletionjButtonActionPerformed

    private void selectSelectedInvoicejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectSelectedInvoicejButtonActionPerformed
        // TODO add your handling code here:  
        if (invoicejTable.getSelectedRow() >= 0) {
            t.clear();
            // gets the selected position from the invoice table
            final int row = invoicejTable.getSelectedRow();
            final int columnCount = invoicejTable.getColumnCount();

            Object[] obj = new Object[6];

            // gets all the row information from the selected invoice in the table
            // and places it in the array 
            for (int i = 0; i < columnCount; ++i)
                obj[i] = invoicejTable.getValueAt(row, i);
            
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
            m.setRowCount(0);
            
            card1.show(cardPanel1, "acceptLatePayment");
            card2.show(cardPanel2, "acceptLatePaymentBar");
        } else {
            System.out.println("You need to select a record");
        }  
    }//GEN-LAST:event_selectSelectedInvoicejButtonActionPerformed

    private void searchInvoiceByInvoiceNojTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInvoiceByInvoiceNojTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInvoiceByInvoiceNojTextFieldActionPerformed

    private void accountHolderNamejTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountHolderNamejTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accountHolderNamejTextFieldActionPerformed

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

    public void filterInvoiceTable(String inputText) {
        //List<RowFilter<>> filters = new ArrayList<>(2);
    }
    
    private void searchInvoiceByJobNumberjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInvoiceByJobNumberjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInvoiceByJobNumberjTextFieldActionPerformed

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

    private void autoBackConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoBackConfigActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "AutoBackupConfig");
        card2.show(cardPanel2, "acceptLatePaymentBar");
    }//GEN-LAST:event_autoBackConfigActionPerformed

    private void cancelAutoBackupConfigjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelAutoBackupConfigjButtonActionPerformed
        // TODO add your handling code here:
        autoBackupLocationjTextField.setText("");
        card1.show(cardPanel1, "welcome");
        card2.show(cardPanel2, "welcomeBar1");
    }//GEN-LAST:event_cancelAutoBackupConfigjButtonActionPerformed

    private void confirmAutoBackupConfigjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmAutoBackupConfigjButtonActionPerformed
        // TODO add your handling code here:
        if (!autoBackupLocationjTextField.getText().equals("")) {
            final String mode = backupModejComboBox.getSelectedItem().toString();
            final String frequency = backupFrequencyjComboBox.getSelectedItem().toString();
            final String location = autoBackupLocationjTextField.getText();
            
            final AutoBackupConfig config = new AutoBackupConfig(mode, frequency, location);

            controller.setAutoBackupConfig(config);
            autoBackupLocationjTextField.setText("");
        } else {
            System.out.println("You need to pick a location");
        }
    }//GEN-LAST:event_confirmAutoBackupConfigjButtonActionPerformed

    private void TotalLatePayjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalLatePayjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalLatePayjTextFieldActionPerformed
    
    private void searchInvoiceByInvoiceNojTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchInvoiceByInvoiceNojTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInvoiceByInvoiceNojTextFieldKeyTyped

    private void searchInvoiceByJobNumberjTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchInvoiceByJobNumberjTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInvoiceByJobNumberjTextFieldKeyTyped

    private void searchInvoiceByInvoiceNojTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchInvoiceByInvoiceNojTextFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInvoiceByInvoiceNojTextFieldKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//        System.out.println(date.get(Calendar.DAY_OF_WEEK));
//        System.out.println(date.get(Calendar.HOUR));
//        System.out.println(date.get(Calendar.MINUTE));
//        System.out.println(date.get(Calendar.SECOND));
    }//GEN-LAST:event_jButton1ActionPerformed
    
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JPanel Backup;
    private javax.swing.JTextField TotalLatePayjTextField;
    private javax.swing.JPanel acceptJob;
    private javax.swing.JLabel acceptJobLabel;
    private javax.swing.JPanel acceptJobMBar;
    private javax.swing.JPanel acceptJobjPanel;
    private javax.swing.JPanel acceptLatePayment;
    private javax.swing.JLabel acceptLatePaymentLabel;
    private javax.swing.JPanel acceptLatePaymentMBar;
    private javax.swing.JPanel acceptLatePaymentjPanel;
    private javax.swing.JButton acceptPaymentReceptionist;
    private javax.swing.JLabel accountHolderNamejLabel;
    private javax.swing.JTextField accountHolderNamejTextField;
    private javax.swing.JLabel accountHolderNojLabel;
    private javax.swing.JTextField accountHolderNojTextField;
    private javax.swing.JLabel accountStatusjLabel;
    private javax.swing.JButton addJobButton;
    private javax.swing.JButton addMaterialButton;
    private javax.swing.JButton autoBackConfig;
    private javax.swing.JTextField autoBackupLocationjTextField;
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> backupFrequencyjComboBox;
    private javax.swing.JLabel backupFrequencyjLabel;
    private javax.swing.JLabel backupLocationjLabel;
    private javax.swing.JComboBox<String> backupModejComboBox;
    private javax.swing.JLabel backupModejLabel;
    private javax.swing.JTextField buildingNumberField;
    private javax.swing.JLabel buildingNumberjLabel;
    private javax.swing.JButton cancelAcceptJobButton;
    private javax.swing.JButton cancelAutoBackupConfigjButton;
    private javax.swing.JButton cancelCreationjButton;
    private javax.swing.JButton cancelCustomerFJobjButton;
    private javax.swing.JButton cancelInvoiceSeletionjButton;
    private javax.swing.JButton cancelLatePaymentjButton;
    private javax.swing.JPanel cardPanel1;
    private javax.swing.JPanel cardPanel2;
    private javax.swing.JComboBox<String> cardTypejComboBox;
    private javax.swing.JLabel cardTypejLabel;
    private javax.swing.JTextField cityField;
    private javax.swing.JLabel citySjLabel;
    private javax.swing.JLabel cityjLabel;
    private javax.swing.JTextField cityjTextField;
    private javax.swing.JButton confirmAutoBackupConfigjButton;
    private javax.swing.JPanel createCustomer;
    private javax.swing.JButton createCustomerButton;
    private javax.swing.JLabel createCustomerLabel;
    private javax.swing.JPanel createCustomerMBar;
    private javax.swing.JButton createCustomerjButton;
    private javax.swing.JPanel createCustomerjPanel;
    private javax.swing.JTextField customerInfojTextField;
    private javax.swing.JLabel customerTypeSjLabel;
    private javax.swing.JComboBox<String> customerTypejComboBox;
    private javax.swing.JComboBox<String> discountStatusjComboBox;
    private javax.swing.JLabel expiryDatejLabel;
    private javax.swing.JTextField expiryDatejTextField;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JLabel firstNamejLabel;
    private javax.swing.JPanel homePageOMBar;
    private javax.swing.JButton homePageR;
    private javax.swing.JPanel homePageRBar;
    private javax.swing.JPanel homePageReceptionist;
    private javax.swing.JPanel homePageTBar;
    private javax.swing.JPanel homePageTechnician;
    private javax.swing.JPanel homePagesSMBar;
    private javax.swing.JLabel inDefaultSjLabel;
    private javax.swing.JComboBox<String> inDefaultjComboBox;
    private javax.swing.JList<String> invoicejList;
    private javax.swing.JScrollPane invoicejScrollPane;
    private javax.swing.JTable invoicejTable;
    private javax.swing.JToggleButton isManagerjToggleButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JButton jobReceptionist;
    private javax.swing.JLabel last4DigitjLabel;
    private javax.swing.JTextField last4DigitjTextField;
    private javax.swing.JButton logOutButton;
    private javax.swing.JButton logOutButton1;
    private javax.swing.JButton logOutButton2;
    private javax.swing.JButton logOutButton3;
    private javax.swing.JPanel managerjPanel;
    private javax.swing.JLabel materialSubmittedLabel;
    private javax.swing.JScrollPane materialsjScrollPane;
    private javax.swing.JTextField materialsjTextField;
    private javax.swing.JLabel officeManagerText;
    private javax.swing.JLabel officeManagerText1;
    private javax.swing.JLabel officeManagerText2;
    private javax.swing.JLabel officeManagerText3;
    private javax.swing.JLabel officeManagerWelcomeText;
    private javax.swing.JLabel officeManagerWelcomeText1;
    private javax.swing.JLabel officeManagerWelcomeText2;
    private javax.swing.JLabel officeManagerWelcomeText3;
    private javax.swing.JComboBox<String> paymentTypeComboBox;
    private javax.swing.JLabel paymentTypejLabel;
    private javax.swing.JLabel percentageLabel;
    private javax.swing.JTextField phoneNumberField;
    private javax.swing.JLabel phoneNumberjLabel;
    private javax.swing.JLabel phoneSjLabel;
    private javax.swing.JTextField phonejTextField;
    private javax.swing.JTextField postCodeField;
    private javax.swing.JLabel postCodejLabel;
    private javax.swing.JTextField postCodejTextField;
    private javax.swing.JLabel postcodeSjLabel;
    private javax.swing.JComboBox<String> prefixjComboBox;
    private javax.swing.JLabel prefixjLabel;
    private javax.swing.JPanel receptionHomePage;
    private javax.swing.JPanel receptionistHomePage;
    private javax.swing.JPanel receptionistjPanel;
    private javax.swing.JLabel registrationDateSjLabel;
    private javax.swing.JComboBox<String> registrationDatejComboBox;
    private javax.swing.JButton removeButton;
    private javax.swing.JLabel searchAccountHolderNamejLabel;
    private javax.swing.JTextField searchAccountHolderNamejTextField;
    private javax.swing.JLabel searchContactFirstNamejLabel;
    private javax.swing.JTextField searchContactFirstNamejTextField;
    private javax.swing.JLabel searchContactSurnamejLabel;
    private javax.swing.JTextField searchContactSurnamejTextField;
    private javax.swing.JPanel searchCustomer;
    private javax.swing.JLabel searchCustomerAccountNojLabel;
    private javax.swing.JTextField searchCustomerAccountNojTextField;
    private javax.swing.JButton searchCustomerButton;
    private javax.swing.JButton searchCustomerFJobjButton;
    private javax.swing.JLabel searchCustomerLabel;
    private javax.swing.JPanel searchCustomerMBar;
    private javax.swing.JPanel searchCustomerjPanel;
    private javax.swing.JPanel searchInvoice;
    private javax.swing.JLabel searchInvoiceByInvoiceNojLabel;
    private javax.swing.JTextField searchInvoiceByInvoiceNojTextField;
    private javax.swing.JLabel searchInvoiceByJobNumberjLabel;
    private javax.swing.JTextField searchInvoiceByJobNumberjTextField;
    private javax.swing.JPanel searchInvoicejPanel;
    private javax.swing.JButton selectAutoBackupLocationjButton;
    private javax.swing.JButton selectInvoicejButton;
    private javax.swing.JComboBox<String> selectPriority;
    private javax.swing.JButton selectSelectedInvoicejButton;
    private javax.swing.JComboBox<String> selectStdJob;
    private javax.swing.JTextField specialInstructionjTextField;
    private javax.swing.JLabel specialInstructionsLabel;
    private javax.swing.JScrollPane stdJobsjScrollPane1;
    private javax.swing.JTextField streetNameField;
    private javax.swing.JLabel streetNameSjLabel;
    private javax.swing.JLabel streetNamejLabel;
    private javax.swing.JTextField streetNamejTextField;
    private javax.swing.JButton submitButton;
    private javax.swing.JButton submitLatePaymentjButton;
    private javax.swing.JLabel surchargeLabel;
    private javax.swing.JTextField surchargejTextField;
    private javax.swing.JTextField surnameField;
    private javax.swing.JLabel surnamejLabel;
    private javax.swing.JLabel totalAmountLabel;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JLabel totaljLabel;
    private javax.swing.JPanel welcomeBar1;
    private javax.swing.JPanel welcomeBar2;
    private javax.swing.JPanel welcomeBar3;
    private javax.swing.JPanel welcomeBar4;
    private javax.swing.JPanel welcomePage;
    // End of variables declaration//GEN-END:variables
}
