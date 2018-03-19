/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.gui;

import bapers.controller.Controller;
import java.awt.CardLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author CameronE
 */
public class MainFrame extends javax.swing.JFrame {

    private CardLayout card1;
    private CardLayout card2;
    private Controller controller;

    /**
     * Creates new form MainFrame
     */
    public MainFrame(Controller controller) {
        this.controller = controller;
        initComponents();
        card1 = (CardLayout) cardPanel1.getLayout();
        card2 = (CardLayout) cardPanel2.getLayout();
    }

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
        loginPageButton = new javax.swing.JButton();
        RestorePageButton = new javax.swing.JButton();
        tempButton = new javax.swing.JButton();
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
        cardPanel2 = new javax.swing.JPanel();
        welcomeBar1 = new javax.swing.JPanel();
        userHomePagePanel = new javax.swing.JPanel();
        logOutButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        welcomeBar2 = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(900, 700));
        setMinimumSize(new java.awt.Dimension(900, 700));
        setPreferredSize(new java.awt.Dimension(900, 700));
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

        tempButton.setText("tempButton");
        tempButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tempButtonActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, welcomePageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tempButton)
                .addGap(131, 131, 131))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(tempButton)
                .addGap(37, 37, 37))
        );

        cardPanel1.add(welcomePage, "welcome");

        loginPage.setBackground(new java.awt.Color(61, 96, 146));
        loginPage.setMaximumSize(new java.awt.Dimension(900, 640));
        loginPage.setMinimumSize(new java.awt.Dimension(900, 640));
        loginPage.setPreferredSize(new java.awt.Dimension(900, 640));

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

        createUserPage.setBackground(new java.awt.Color(61, 96, 146));
        createUserPage.setMaximumSize(new java.awt.Dimension(900, 640));
        createUserPage.setMinimumSize(new java.awt.Dimension(900, 640));
        createUserPage.setPreferredSize(new java.awt.Dimension(900, 640));

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
        userRoleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Technician", "Shift Manager", "Office Manager", "Receptionist", " " }));
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

        javax.swing.GroupLayout createUserPageLayout = new javax.swing.GroupLayout(createUserPage);
        createUserPage.setLayout(createUserPageLayout);
        createUserPageLayout.setHorizontalGroup(
            createUserPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createUserPageLayout.createSequentialGroup()
                .addGap(204, 204, 204)
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
                    .addComponent(NewRepeatPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(204, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createUserPageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(createUserPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createUserPageLayout.createSequentialGroup()
                        .addComponent(createUserButton)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createUserPageLayout.createSequentialGroup()
                        .addComponent(newUserLabel)
                        .addGap(362, 362, 362))))
        );
        createUserPageLayout.setVerticalGroup(
            createUserPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createUserPageLayout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(newUserLabel)
                .addGap(18, 18, 18)
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
                .addGap(59, 59, 59)
                .addComponent(createUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        cardPanel1.add(createUserPage, "createUser");

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
            .addGap(0, 56, Short.MAX_VALUE)
        );

        cardPanel2.add(welcomeBar1, "welcomeBar1");

        userHomePagePanel.setBackground(new java.awt.Color(33, 53, 80));

        logOutButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        logOutButton.setText("Log Out");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        userLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        userLabel.setForeground(new java.awt.Color(255, 255, 255));
        userLabel.setText("User");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Welcome");

        javax.swing.GroupLayout userHomePagePanelLayout = new javax.swing.GroupLayout(userHomePagePanel);
        userHomePagePanel.setLayout(userHomePagePanelLayout);
        userHomePagePanelLayout.setHorizontalGroup(
            userHomePagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userHomePagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(201, 201, 201)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        userHomePagePanelLayout.setVerticalGroup(
            userHomePagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logOutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(userHomePagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardPanel2.add(userHomePagePanel, "userHomePagePanel");

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
            .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        cardPanel2.add(welcomeBar2, "welcomeBar2");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        int roleID = -1;
        boolean valid = true;
        String userID = userIDField.getText();
        String password = passwordField.getText();

        //Check fields are not empty
        if (userID.equals("") || password.equals("")) {
            valid = false;
            JOptionPane.showMessageDialog(null, "Please insert data");
        }

        if (valid) {
            roleID = controller.login(userID,password);
        }

        switch (roleID) {
            case 1:
                System.out.println("Technician homepage");
                //Insert code to show pages here:
                //card1.show(cardPanel1, "restore");
                //card2.show(cardPanel2, "welcomeBar2");
                break;
            case 2:
                System.out.println("Office Manager homepage");
                break;
            case 3:
                System.out.println("Shift Manager homepage");
                break;
            case 4:
                System.out.println("Receptionist homepage");
                break;
            case 0:
                JOptionPane.showMessageDialog(null, "Invalid User details");
        }

    }//GEN-LAST:event_loginButtonActionPerformed

    private void loginPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginPageButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "login");
        card2.show(cardPanel2, "welcomeBar2");
    }//GEN-LAST:event_loginPageButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "welcome");
        card2.show(cardPanel2, "welcomeBar1");
    }//GEN-LAST:event_backButtonActionPerformed

    private void RestorePageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RestorePageButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "restore");
        card2.show(cardPanel2, "welcomeBar2");
    }//GEN-LAST:event_RestorePageButtonActionPerformed

    private void RestoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RestoreButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RestoreButtonActionPerformed

    private void chooseFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFileButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chooseFileButtonActionPerformed

    private void tempButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tempButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "createUser");
        card2.show(cardPanel2, "welcomeBar2");
    }//GEN-LAST:event_tempButtonActionPerformed

    private void NewRepeatPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewRepeatPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewRepeatPasswordFieldActionPerformed

    private void NewPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPasswordFieldActionPerformed

    private void createUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUserButtonActionPerformed
        boolean valid = true;
        //Initialise values
        String firstName = userFirstNameField.getText();
        String surname = userLastNameField.getText();
        String password = NewPasswordField.getText();
        String role = (String) userRoleComboBox.getSelectedItem();
        int roleID = 0;

        //Check first name field
        if (userFirstNameField.getText().length() > 10) {
            JOptionPane.showMessageDialog(null, "Name cannot be longer than 10 characters");
            valid = false;
        }

        //Check fields are not empty
        if (firstName.equals("") || surname.equals("") || password.equals("")) {
            valid = false;
            JOptionPane.showMessageDialog(null, "Please insert data");
        }

        //Check passwords match
        if (!NewPasswordField.getText().equals(NewRepeatPasswordField.getText())) {
            valid = false;
            JOptionPane.showMessageDialog(null, "Passwords do not match");
            //Insert pop up error
        }

        //Get RoleID
        roleID = controller.getRole(role);
 
        //Will only execute method in controller if all preconditions are met
        if (valid) {
            if (controller.createUser(firstName, surname, password, roleID)) {
                JOptionPane.showMessageDialog(null, "User created");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to create user");
            }
        }
        
           
    }//GEN-LAST:event_createUserButtonActionPerformed

    private void userRoleComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userRoleComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userRoleComboBoxActionPerformed

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        card1.show(cardPanel1, "welcome");
        card2.show(cardPanel2, "welcomeBar1");
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void userFirstNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userFirstNameFieldActionPerformed

    }//GEN-LAST:event_userFirstNameFieldActionPerformed

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
    private javax.swing.JLabel BAPERSLabel;
    private javax.swing.JPasswordField NewPasswordField;
    private javax.swing.JPasswordField NewRepeatPasswordField;
    private javax.swing.JLabel ReenterPasswordLabel;
    private javax.swing.JButton RestoreButton;
    private javax.swing.JButton RestorePageButton;
    private javax.swing.JLabel RoleLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel cardPanel1;
    private javax.swing.JPanel cardPanel2;
    private javax.swing.JButton chooseFileButton;
    private javax.swing.JButton createUserButton;
    private javax.swing.JPanel createUserPage;
    private javax.swing.JTextField fileChosenField;
    private javax.swing.JLabel firstnameLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lastnameLabel;
    private javax.swing.JButton logOutButton;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPanel loginPage;
    private javax.swing.JButton loginPageButton;
    private javax.swing.JLabel newUserLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel restoreLabel;
    private javax.swing.JPanel restorePage;
    private javax.swing.JButton tempButton;
    private javax.swing.JTextField userFirstNameField;
    private javax.swing.JPanel userHomePagePanel;
    private javax.swing.JTextField userIDField;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField userLastNameField;
    private javax.swing.JComboBox<String> userRoleComboBox;
    private javax.swing.JPanel welcomeBar1;
    private javax.swing.JPanel welcomeBar2;
    private javax.swing.JPanel welcomePage;
    // End of variables declaration//GEN-END:variables
}
