package app.dashboard;

import app.Beranda;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import utils.*;

public class Pengguna extends javax.swing.JFrame {

    String id_user = null;
    Random random = new Random();

    public Pengguna() throws SQLException {
        initComponents();
        addUserPanel.setVisible(false);
        editUserPanel.setVisible(false);
        Model.table(tabelPengguna);
        showDataTable();

        DocumentListener dl = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFieldState();
            }

            protected void updateFieldState() {
                DefaultTableModel model = new DefaultTableModel();

                model.addColumn("Id");
                model.addColumn("Nama");
                model.addColumn("Username");
                model.addColumn("Password");
                model.addColumn("Gender");
                model.addColumn("Role");


                try {
                    String query = "SELECT * FROM users WHERE nama_user LIKE '%" + searchItem.getText() + "%'";
                    ResultSet res = Database.queryResultSet(query);
                    while (res.next()) {
                        model.addRow(new Object[]{res.getString("id_user"), res.getString("nama_user"), res.getString("username"),
                            res.getString("password_user"), res.getString("gender"), res.getString("role")});
                    }
                    tabelPengguna.setModel(model);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }
        };

        searchItem.getDocument().addDocumentListener(dl);
    }

    void showDataTable() throws SQLException {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Id");
        model.addColumn("Nama");
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Gender");
        model.addColumn("Role");

        String sql = "SELECT * FROM users";
        ResultSet res = Database.queryResultSet(sql);
        try {
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_user"), res.getString("nama_user"), res.getString("username"),
                    res.getString("password_user"), res.getString("gender"), res.getString("role")});
            }
            tabelPengguna.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }

    void showDataEditPanel() {
        String sql = "SELECT * FROM users WHERE id_user='" + id_user + "'";
        try {
            ResultSet res = Database.queryResultSet(sql);
            if (res.next()) {
                idPenggunaEdit.setText(id_user);
                namaUserEdit.setText(res.getString("nama_user"));
                usernameUserEdit.setText(res.getString("username"));
                passwordUserEdit.setText(res.getString("password_user"));
                if (res.getString("gender").equals("Laki-laki")) {
                    genderUserCBox.setSelectedItem("Laki-laki");
                } else {
                    genderUserCBox.setSelectedItem("Perempuan");
                }
                if (res.getString("role").equals("Administrator")) {
                    roleUserCBox.setSelectedItem("Administrator");
                } else {
                    roleUserCBox.setSelectedItem("Pegawai");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        editUserPanel = new javax.swing.JPanel();
        idPenggunaEdit = new javax.swing.JTextField();
        passwordUserEdit = new javax.swing.JTextField();
        namaUserEdit = new javax.swing.JTextField();
        usernameUserEdit = new javax.swing.JTextField();
        editDataBtn = new javax.swing.JPanel();
        genderUserCBox = new javax.swing.JComboBox<>();
        roleUserCBox = new javax.swing.JComboBox<>();
        backEditBtn = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        addUserPanel = new javax.swing.JPanel();
        idPengguna = new javax.swing.JTextField();
        passwordUser = new javax.swing.JTextField();
        namaUser = new javax.swing.JTextField();
        usernameUser = new javax.swing.JTextField();
        addUserBtn = new javax.swing.JPanel();
        genderUser = new javax.swing.JComboBox<>();
        roleUser = new javax.swing.JComboBox<>();
        backAddBtn = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        addBtn = new javax.swing.JPanel();
        editBtn = new javax.swing.JPanel();
        deleteBtn = new javax.swing.JPanel();
        dashboardBtn = new javax.swing.JPanel();
        barangBtn = new javax.swing.JPanel();
        pengeluaranBtn = new javax.swing.JPanel();
        laporanBtn = new javax.swing.JPanel();
        transBeliBtn = new javax.swing.JPanel();
        transJualBtn = new javax.swing.JPanel();
        kategoriBtn = new javax.swing.JPanel();
        pemasokBtn = new javax.swing.JPanel();
        homeBtn = new javax.swing.JPanel();
        searchItem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPengguna = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TUBES_PBO - Dashboard Barang");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editUserPanel.setBackground(new java.awt.Color(241, 245, 249));
        editUserPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idPenggunaEdit.setEditable(false);
        idPenggunaEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        idPenggunaEdit.setBorder(null);
        idPenggunaEdit.setOpaque(false);
        editUserPanel.add(idPenggunaEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 448, 290, 20));

        passwordUserEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        passwordUserEdit.setBorder(null);
        passwordUserEdit.setOpaque(false);
        editUserPanel.add(passwordUserEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 516, 280, 20));

        namaUserEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        namaUserEdit.setBorder(null);
        namaUserEdit.setOpaque(false);
        editUserPanel.add(namaUserEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 448, 280, 20));

        usernameUserEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        usernameUserEdit.setBorder(null);
        usernameUserEdit.setOpaque(false);
        usernameUserEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameUserEditActionPerformed(evt);
            }
        });
        editUserPanel.add(usernameUserEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 290, 30));

        editDataBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editDataBtn.setOpaque(false);
        editDataBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editDataBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout editDataBtnLayout = new javax.swing.GroupLayout(editDataBtn);
        editDataBtn.setLayout(editDataBtnLayout);
        editDataBtnLayout.setHorizontalGroup(
            editDataBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        editDataBtnLayout.setVerticalGroup(
            editDataBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        editUserPanel.add(editDataBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 630, 70, 30));

        genderUserCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kategori", "Laki-laki", "Perempuan" }));
        editUserPanel.add(genderUserCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 580, 300, 30));

        roleUserCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kategori", "Administrator", "Pegawai" }));
        editUserPanel.add(roleUserCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 580, 300, 30));

        backEditBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backEditBtn.setOpaque(false);
        backEditBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backEditBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backEditBtnLayout = new javax.swing.GroupLayout(backEditBtn);
        backEditBtn.setLayout(backEditBtnLayout);
        backEditBtnLayout.setHorizontalGroup(
            backEditBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        backEditBtnLayout.setVerticalGroup(
            backEditBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        editUserPanel.add(backEditBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 20, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ubah_data_pengguna_panel.png"))); // NOI18N
        editUserPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(editUserPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 750, 700));

        addUserPanel.setBackground(new java.awt.Color(241, 245, 249));
        addUserPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idPengguna.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        idPengguna.setBorder(null);
        idPengguna.setOpaque(false);
        addUserPanel.add(idPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 280, 20));

        passwordUser.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        passwordUser.setBorder(null);
        passwordUser.setOpaque(false);
        addUserPanel.add(passwordUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 518, 290, 20));

        namaUser.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        namaUser.setBorder(null);
        namaUser.setOpaque(false);
        addUserPanel.add(namaUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 450, 280, 20));

        usernameUser.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        usernameUser.setBorder(null);
        usernameUser.setOpaque(false);
        addUserPanel.add(usernameUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 518, 290, 20));

        addUserBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addUserBtn.setOpaque(false);
        addUserBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addUserBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout addUserBtnLayout = new javax.swing.GroupLayout(addUserBtn);
        addUserBtn.setLayout(addUserBtnLayout);
        addUserBtnLayout.setHorizontalGroup(
            addUserBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        addUserBtnLayout.setVerticalGroup(
            addUserBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        addUserPanel.add(addUserBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 633, 100, 30));

        genderUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kategori", "Laki-laki", "Perempuan" }));
        addUserPanel.add(genderUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 586, 300, 30));

        roleUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kategori", "Administrator", "Pegawai" }));
        addUserPanel.add(roleUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 586, 300, 30));

        backAddBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backAddBtn.setOpaque(false);
        backAddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backAddBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backAddBtnLayout = new javax.swing.GroupLayout(backAddBtn);
        backAddBtn.setLayout(backAddBtnLayout);
        backAddBtnLayout.setHorizontalGroup(
            backAddBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        backAddBtnLayout.setVerticalGroup(
            backAddBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        addUserPanel.add(backAddBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 20, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah_data_pengguna_panel.png"))); // NOI18N
        addUserPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(addUserPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 750, 700));

        addBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBtn.setOpaque(false);
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout addBtnLayout = new javax.swing.GroupLayout(addBtn);
        addBtn.setLayout(addBtnLayout);
        addBtnLayout.setHorizontalGroup(
            addBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        addBtnLayout.setVerticalGroup(
            addBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 90, 80, 20));

        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editBtn.setOpaque(false);
        editBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout editBtnLayout = new javax.swing.GroupLayout(editBtn);
        editBtn.setLayout(editBtnLayout);
        editBtnLayout.setHorizontalGroup(
            editBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        editBtnLayout.setVerticalGroup(
            editBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 90, 60, 20));

        deleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtn.setOpaque(false);
        deleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout deleteBtnLayout = new javax.swing.GroupLayout(deleteBtn);
        deleteBtn.setLayout(deleteBtnLayout);
        deleteBtnLayout.setHorizontalGroup(
            deleteBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        deleteBtnLayout.setVerticalGroup(
            deleteBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, 60, 20));

        dashboardBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardBtn.setOpaque(false);
        dashboardBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout dashboardBtnLayout = new javax.swing.GroupLayout(dashboardBtn);
        dashboardBtn.setLayout(dashboardBtnLayout);
        dashboardBtnLayout.setHorizontalGroup(
            dashboardBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        dashboardBtnLayout.setVerticalGroup(
            dashboardBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(dashboardBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 190, 40));

        barangBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        barangBtn.setOpaque(false);
        barangBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                barangBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout barangBtnLayout = new javax.swing.GroupLayout(barangBtn);
        barangBtn.setLayout(barangBtnLayout);
        barangBtnLayout.setHorizontalGroup(
            barangBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        barangBtnLayout.setVerticalGroup(
            barangBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(barangBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 40));

        pengeluaranBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pengeluaranBtn.setOpaque(false);
        pengeluaranBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pengeluaranBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pengeluaranBtnLayout = new javax.swing.GroupLayout(pengeluaranBtn);
        pengeluaranBtn.setLayout(pengeluaranBtnLayout);
        pengeluaranBtnLayout.setHorizontalGroup(
            pengeluaranBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        pengeluaranBtnLayout.setVerticalGroup(
            pengeluaranBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(pengeluaranBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, -1, 30));

        laporanBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        laporanBtn.setOpaque(false);
        laporanBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laporanBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout laporanBtnLayout = new javax.swing.GroupLayout(laporanBtn);
        laporanBtn.setLayout(laporanBtnLayout);
        laporanBtnLayout.setHorizontalGroup(
            laporanBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        laporanBtnLayout.setVerticalGroup(
            laporanBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(laporanBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, -1, 40));

        transBeliBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        transBeliBtn.setOpaque(false);
        transBeliBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transBeliBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout transBeliBtnLayout = new javax.swing.GroupLayout(transBeliBtn);
        transBeliBtn.setLayout(transBeliBtnLayout);
        transBeliBtnLayout.setHorizontalGroup(
            transBeliBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        transBeliBtnLayout.setVerticalGroup(
            transBeliBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(transBeliBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, 40));

        transJualBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        transJualBtn.setOpaque(false);
        transJualBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transJualBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout transJualBtnLayout = new javax.swing.GroupLayout(transJualBtn);
        transJualBtn.setLayout(transJualBtnLayout);
        transJualBtnLayout.setHorizontalGroup(
            transJualBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        transJualBtnLayout.setVerticalGroup(
            transJualBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(transJualBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, 40));

        kategoriBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        kategoriBtn.setOpaque(false);
        kategoriBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kategoriBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout kategoriBtnLayout = new javax.swing.GroupLayout(kategoriBtn);
        kategoriBtn.setLayout(kategoriBtnLayout);
        kategoriBtnLayout.setHorizontalGroup(
            kategoriBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        kategoriBtnLayout.setVerticalGroup(
            kategoriBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(kategoriBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, 40));

        pemasokBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pemasokBtn.setOpaque(false);
        pemasokBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pemasokBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pemasokBtnLayout = new javax.swing.GroupLayout(pemasokBtn);
        pemasokBtn.setLayout(pemasokBtnLayout);
        pemasokBtnLayout.setHorizontalGroup(
            pemasokBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        pemasokBtnLayout.setVerticalGroup(
            pemasokBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(pemasokBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, 30));

        homeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        homeBtn.setOpaque(false);
        homeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout homeBtnLayout = new javax.swing.GroupLayout(homeBtn);
        homeBtn.setLayout(homeBtnLayout);
        homeBtnLayout.setHorizontalGroup(
            homeBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        homeBtnLayout.setVerticalGroup(
            homeBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, -1, 40));

        searchItem.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        searchItem.setForeground(new java.awt.Color(48, 45, 65));
        searchItem.setBorder(null);
        searchItem.setOpaque(false);
        jPanel1.add(searchItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 180, 20));

        tabelPengguna.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        tabelPengguna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelPengguna.setSelectionBackground(new java.awt.Color(229, 229, 229));
        tabelPengguna.setSelectionForeground(new java.awt.Color(30, 30, 46));
        tabelPengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPenggunaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPengguna);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 570, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dashboard_pengguna_page.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeBtnMouseClicked
        this.setVisible(false);
        new Beranda().setVisible(true);
    }//GEN-LAST:event_homeBtnMouseClicked

    private void deleteBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteBtnMouseClicked
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data?");
        if (confirm == 0) {
            try {
                String query = "DELETE FROM users WHERE id_user='" + id_user + "'";
                Database.queryExecute(query);
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                showDataTable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_deleteBtnMouseClicked

    private void editBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseClicked
        if (id_user == null) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih data pada baris tabel untuk memulai mengubah data!");
        } else {
            editUserPanel.setVisible(true);
            tabelPengguna.setVisible(false);
            showDataEditPanel();
        }
    }//GEN-LAST:event_editBtnMouseClicked

    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        addUserPanel.setVisible(true);
        idPengguna.setText("USR" + random.nextInt(99999));
        tabelPengguna.setVisible(false);
    }//GEN-LAST:event_addBtnMouseClicked

    private void tabelPenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPenggunaMouseClicked
        int baris = tabelPengguna.rowAtPoint(evt.getPoint());
        id_user = tabelPengguna.getValueAt(baris, 0).toString();
    }//GEN-LAST:event_tabelPenggunaMouseClicked

    private void editDataBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editDataBtnMouseClicked
        try {
            String query = "UPDATE users SET nama_user='" + namaUserEdit.getText() + "', password_user='" + passwordUserEdit.getText() + "',"
                    + "username='" + usernameUserEdit.getText() + "' WHERE id_user='" + id_user + "'";
            Database.queryExecute(query);
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
            namaUserEdit.setText(null);
            passwordUserEdit.setText(null);
            usernameUserEdit.setText(null);
            editUserPanel.setVisible(false);
            tabelPengguna.setVisible(true);
            showDataTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_editDataBtnMouseClicked

    private void backEditBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backEditBtnMouseClicked
        editUserPanel.setVisible(false);
        tabelPengguna.setVisible(true);
        idPenggunaEdit.setText(null);
        namaUserEdit.setText(null);
        usernameUserEdit.setText(null);
        passwordUserEdit.setText(null);
    }//GEN-LAST:event_backEditBtnMouseClicked

    private void barangBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barangBtnMouseClicked
        this.setVisible(false);
        new Barang().setVisible(true);
    }//GEN-LAST:event_barangBtnMouseClicked

    private void addUserBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addUserBtnMouseClicked
        String role = roleUser.getSelectedItem().toString();
        String gender = genderUser.getSelectedItem().toString();
        try {
            String sql = "INSERT INTO users (id_user, nama_user, username, password_user, gender, role) "
                    + "VALUES ('" + idPengguna.getText() + "', '" + namaUser.getText() + "', '" + usernameUser.getText() + "', '"
                    + passwordUser.getText() + "', '" + gender + "', '" + role + "')";
            Database.queryExecute(sql);
            addUserPanel.setVisible(false);
            namaUser.setText(null);
            usernameUser.setText(null);
            passwordUser.setText(null);
            showDataTable();
            tabelPengguna.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_addUserBtnMouseClicked

    private void backAddBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backAddBtnMouseClicked
        addUserPanel.setVisible(false);
        tabelPengguna.setVisible(true);
        idPengguna.setText(null);
        namaUser.setText(null);
        usernameUser.setText(null);
        passwordUser.setText(null);
    }//GEN-LAST:event_backAddBtnMouseClicked

    private void pemasokBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pemasokBtnMouseClicked
        this.setVisible(false);
        new Suplier().setVisible(true);
    }//GEN-LAST:event_pemasokBtnMouseClicked

    private void dashboardBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardBtnMouseClicked
        this.setVisible(false);
        new Dashboard().setVisible(true);
    }//GEN-LAST:event_dashboardBtnMouseClicked

    private void kategoriBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kategoriBtnMouseClicked
        this.setVisible(false);
        new Kategori().setVisible(true);
    }//GEN-LAST:event_kategoriBtnMouseClicked

    private void transJualBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transJualBtnMouseClicked
       this.setVisible(false);
       new TransaksiJual().setVisible(true);
    }//GEN-LAST:event_transJualBtnMouseClicked

    private void transBeliBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transBeliBtnMouseClicked
        this.setVisible(false);
        try {
            new TransaksiBeli().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Pengguna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_transBeliBtnMouseClicked

    private void laporanBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanBtnMouseClicked
        this.setVisible(false);
        new Laporan().setVisible(true);
    }//GEN-LAST:event_laporanBtnMouseClicked

    private void pengeluaranBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengeluaranBtnMouseClicked
        this.setVisible(false);
        try {
            new Pengeluaran().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Pengguna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pengeluaranBtnMouseClicked

    private void usernameUserEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameUserEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameUserEditActionPerformed

    public static void main(String args[]) {
        try {
            FlatArcIJTheme.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Pengguna().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Pengguna.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addBtn;
    private javax.swing.JPanel addUserBtn;
    private javax.swing.JPanel addUserPanel;
    private javax.swing.JPanel backAddBtn;
    private javax.swing.JPanel backEditBtn;
    private javax.swing.JPanel barangBtn;
    private javax.swing.JPanel dashboardBtn;
    private javax.swing.JPanel deleteBtn;
    private javax.swing.JPanel editBtn;
    private javax.swing.JPanel editDataBtn;
    private javax.swing.JPanel editUserPanel;
    private javax.swing.JComboBox<String> genderUser;
    private javax.swing.JComboBox<String> genderUserCBox;
    private javax.swing.JPanel homeBtn;
    private javax.swing.JTextField idPengguna;
    private javax.swing.JTextField idPenggunaEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel kategoriBtn;
    private javax.swing.JPanel laporanBtn;
    private javax.swing.JTextField namaUser;
    private javax.swing.JTextField namaUserEdit;
    private javax.swing.JTextField passwordUser;
    private javax.swing.JTextField passwordUserEdit;
    private javax.swing.JPanel pemasokBtn;
    private javax.swing.JPanel pengeluaranBtn;
    private javax.swing.JComboBox<String> roleUser;
    private javax.swing.JComboBox<String> roleUserCBox;
    private javax.swing.JTextField searchItem;
    private javax.swing.JTable tabelPengguna;
    private javax.swing.JPanel transBeliBtn;
    private javax.swing.JPanel transJualBtn;
    private javax.swing.JTextField usernameUser;
    private javax.swing.JTextField usernameUserEdit;
    // End of variables declaration//GEN-END:variables
}
