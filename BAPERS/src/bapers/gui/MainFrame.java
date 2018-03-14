/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.gui;

import bapers.controller.Controller;
import java.awt.CardLayout;
import javax.swing.JFileChooser;

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

        jFileChooser1 = new javax.swing.JFileChooser();
        cardPanel1 = new javax.swing.JPanel();
        welcomePage = new javax.swing.JPanel();
        BAPERSLabel = new javax.swing.JLabel();
        loginPageButton = new javax.swing.JButton();
        RestorePageButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
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
        backupPage = new javax.swing.JPanel();
        BackupDataLabel = new javax.swing.JLabel();
        backupButton = new javax.swing.JButton();
        chooseLocationButton = new javax.swing.JButton();
        locationChosenField = new javax.swing.JTextField();
        backupDestinationLabel = new javax.swing.JLabel();
        cardPanel2 = new javax.swing.JPanel();
        welcomeBar1 = new javax.swing.JPanel();
        welcomeBar2 = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        homeBar2 = new javax.swing.JPanel();
        homeButton = new javax.swing.JButton();
        logOutButton = new javax.swing.JButton();
        pageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 700));
        setPreferredSize(new java.awt.Dimension(900, 700));
        setResizable(false);

        cardPanel1.setBackground(new java.awt.Color(255, 204, 204));
        cardPanel1.setMaximumSize(new java.awt.Dimension(900, 640));
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
                .addGap(75, 75, 75)
                .addGroup(welcomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(BAPERSLabel)
                    .addComponent(loginPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RestorePageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, welcomePageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(208, 208, 208))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(122, 122, 122))
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

        userSearchPage.setBackground(new java.awt.Color(61, 96, 146));
        userSearchPage.setMaximumSize(new java.awt.Dimension(900, 640));
        userSearchPage.setMinimumSize(new java.awt.Dimension(900, 640));

        javax.swing.GroupLayout userSearchPageLayout = new javax.swing.GroupLayout(userSearchPage);
        userSearchPage.setLayout(userSearchPageLayout);
        userSearchPageLayout.setHorizontalGroup(
            userSearchPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        userSearchPageLayout.setVerticalGroup(
            userSearchPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
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

        cardPanel2.setBackground(new java.awt.Color(204, 255, 204));
        cardPanel2.setMaximumSize(new java.awt.Dimension(900, 60));
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

        homeBar2.setBackground(new java.awt.Color(33, 53, 80));
        homeBar2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        homeBar2.setMaximumSize(new java.awt.Dimension(900, 60));
        homeBar2.setMinimumSize(new java.awt.Dimension(900, 60));

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

        javax.swing.GroupLayout homeBar2Layout = new javax.swing.GroupLayout(homeBar2);
        homeBar2.setLayout(homeBar2Layout);
        homeBar2Layout.setHorizontalGroup(
            homeBar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeBar2Layout.createSequentialGroup()
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 345, Short.MAX_VALUE)
                .addComponent(pageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 345, Short.MAX_VALUE)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        homeBar2Layout.setVerticalGroup(
            homeBar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeBar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(pageLabel))
        );

        cardPanel2.add(homeBar2, "homeBar2");

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

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
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

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_homeButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "backup");
        card2.show(cardPanel2, "homeBar2");
        pageLabel.setText("Backup page");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        // TODO add your handling code here:
        card1.show(cardPanel1, "welcome");
        card2.show(cardPanel2, "welcomeBar1");
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void backupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backupButtonActionPerformed
        // TODO add your handling code here:
        Process p = null;
        try {
            Runtime runtime = Runtime.getRuntime();
           // String[] cmd = {"cmd.exe", "/c", "C:/Program Files/MySQL/MySQL Server 5.7/bin/mysqldump -u root -ppassword bapers_data -r C:\\db.sql"};
            p = runtime.exec("C:/Program Files/MySQL/MySQL Server 5.7/bin/mysqldump -u root -ppassword bapers_data -r C:\\db.sql");

            int processComplete = p.waitFor();
            if (processComplete == 0) {
                System.out.println("backup completed");
            } else {
                System.out.println("backup failed");
                System.out.println(p.getErrorStream());

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_backupButtonActionPerformed

    private void chooseLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseLocationButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(this);
        String directory = chooser.getSelectedFile().getPath();
        directory = directory.replace('\\', '/');

        locationChosenField.setText(directory);
    }//GEN-LAST:event_chooseLocationButtonActionPerformed

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
    private javax.swing.JLabel BackupDataLabel;
    private javax.swing.JButton RestoreButton;
    private javax.swing.JButton RestorePageButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton backupButton;
    private javax.swing.JLabel backupDestinationLabel;
    private javax.swing.JPanel backupPage;
    private javax.swing.JPanel cardPanel1;
    private javax.swing.JPanel cardPanel2;
    private javax.swing.JButton chooseFileButton;
    private javax.swing.JButton chooseLocationButton;
    private javax.swing.JTextField fileChosenField;
    private javax.swing.JPanel homeBar2;
    private javax.swing.JButton homeButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JTextField locationChosenField;
    private javax.swing.JButton logOutButton;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPanel loginPage;
    private javax.swing.JButton loginPageButton;
    private javax.swing.JLabel pageLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel restoreLabel;
    private javax.swing.JPanel restorePage;
    private javax.swing.JTextField userIDField;
    private javax.swing.JPanel userSearchPage;
    private javax.swing.JPanel welcomeBar1;
    private javax.swing.JPanel welcomeBar2;
    private javax.swing.JPanel welcomePage;
    // End of variables declaration//GEN-END:variables
}
