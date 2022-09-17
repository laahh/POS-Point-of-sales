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

public class Kategori extends javax.swing.JFrame {

    String id_kategori = null;
    Random random = new Random();

    public Kategori() {
        initComponents();
        addKategoriPanel.setVisible(false);
        editKategoriPanel.setVisible(false);
        Model.table(tabelKategori);
        showDataTable();

        DocumentListener dl = new DocumentListener() {
             //Polimorfisme   
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

                model.addColumn("Id Kategori");
                model.addColumn("Nama Kategori");

                try {
                    String query = "SELECT * FROM kategori WHERE nama_kategori LIKE '%" + searchItem.getText() + "%'";
                    ResultSet res = Database.queryResultSet(query);
                    while (res.next()) {
                        model.addRow(new Object[]{res.getString("id_kategori"), res.getString("nama_kategori")});
                    }
                    tabelKategori.setModel(model);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }
        };

        searchItem.getDocument().addDocumentListener(dl);
    }

    void showDataTable() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Id Kategori");
        model.addColumn("Nama Kategori");

        String sql = "SELECT * FROM kategori";
        try {
            ResultSet res = Database.queryResultSet(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_kategori"), res.getString("nama_kategori")});
            }
            tabelKategori.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }

    void showDataEditPanel() {
        String sql = "SELECT * FROM kategori WHERE id_kategori='" + id_kategori + "'";
        try {
            
            ResultSet res = Database.queryResultSet(sql);
            if (res.next()) {
                idKategoriEdit.setText(id_kategori);
                namaKategoriEdit.setText(res.getString("nama_kategori"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addKategoriPanel = new javax.swing.JPanel();
        idKategori = new javax.swing.JTextField();
        namaKategori = new javax.swing.JTextField();
        addKategoriBtn = new javax.swing.JPanel();
        backAddBtn = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        editKategoriPanel = new javax.swing.JPanel();
        idKategoriEdit = new javax.swing.JTextField();
        namaKategoriEdit = new javax.swing.JTextField();
        editKategoriBtn = new javax.swing.JPanel();
        backEditBtn = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        addBtn = new javax.swing.JPanel();
        editBtn = new javax.swing.JPanel();
        deleteBtn = new javax.swing.JPanel();
        dashboardBtn = new javax.swing.JPanel();
        barangBtn = new javax.swing.JPanel();
        penggunaBtn = new javax.swing.JPanel();
        pengeluaranBtn = new javax.swing.JPanel();
        laporanBtn = new javax.swing.JPanel();
        transBeliBtn = new javax.swing.JPanel();
        transJualBtn = new javax.swing.JPanel();
        pemasokBtn = new javax.swing.JPanel();
        homeBtn = new javax.swing.JPanel();
        searchItem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKategori = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TUBES_PBO- Dashboard Barang");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addKategoriPanel.setBackground(new java.awt.Color(241, 245, 249));
        addKategoriPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idKategori.setEditable(false);
        idKategori.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        idKategori.setBorder(null);
        idKategori.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        idKategori.setOpaque(false);
        addKategoriPanel.add(idKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 472, 290, 20));

        namaKategori.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        namaKategori.setBorder(null);
        namaKategori.setOpaque(false);
        addKategoriPanel.add(namaKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 472, 290, 20));

        addKategoriBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addKategoriBtn.setOpaque(false);
        addKategoriBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addKategoriBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout addKategoriBtnLayout = new javax.swing.GroupLayout(addKategoriBtn);
        addKategoriBtn.setLayout(addKategoriBtnLayout);
        addKategoriBtnLayout.setHorizontalGroup(
            addKategoriBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        addKategoriBtnLayout.setVerticalGroup(
            addKategoriBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        addKategoriPanel.add(addKategoriBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 518, 110, 30));

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

        addKategoriPanel.add(backAddBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 20, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah_data_kategori_panel.png"))); // NOI18N
        addKategoriPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 760, -1));

        jPanel1.add(addKategoriPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 750, 700));

        editKategoriPanel.setBackground(new java.awt.Color(241, 245, 249));
        editKategoriPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idKategoriEdit.setEditable(false);
        idKategoriEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        idKategoriEdit.setBorder(null);
        idKategoriEdit.setOpaque(false);
        editKategoriPanel.add(idKategoriEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 472, 280, 20));

        namaKategoriEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        namaKategoriEdit.setBorder(null);
        namaKategoriEdit.setOpaque(false);
        editKategoriPanel.add(namaKategoriEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 472, 280, 20));

        editKategoriBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editKategoriBtn.setOpaque(false);
        editKategoriBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editKategoriBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout editKategoriBtnLayout = new javax.swing.GroupLayout(editKategoriBtn);
        editKategoriBtn.setLayout(editKategoriBtnLayout);
        editKategoriBtnLayout.setHorizontalGroup(
            editKategoriBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        editKategoriBtnLayout.setVerticalGroup(
            editKategoriBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        editKategoriPanel.add(editKategoriBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 519, 70, 30));

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

        editKategoriPanel.add(backEditBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 20, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ubah_data_kategori_panel.png"))); // NOI18N
        editKategoriPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(editKategoriPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 750, 700));

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

        penggunaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        penggunaBtn.setOpaque(false);
        penggunaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                penggunaBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout penggunaBtnLayout = new javax.swing.GroupLayout(penggunaBtn);
        penggunaBtn.setLayout(penggunaBtnLayout);
        penggunaBtnLayout.setHorizontalGroup(
            penggunaBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        penggunaBtnLayout.setVerticalGroup(
            penggunaBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(penggunaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, 30));

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

        jPanel1.add(pengeluaranBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 190, 30));

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
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(laporanBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 190, 30));

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

        jPanel1.add(transBeliBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 190, 40));

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

        jPanel1.add(transJualBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 190, 40));

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
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(pemasokBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 190, 40));

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

        tabelKategori.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        tabelKategori.setForeground(new java.awt.Color(30, 30, 46));
        tabelKategori.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelKategori.setSelectionBackground(new java.awt.Color(229, 229, 229));
        tabelKategori.setSelectionForeground(new java.awt.Color(30, 30, 46));
        tabelKategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKategoriMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelKategori);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 570, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dashboard_kategori_page.png"))); // NOI18N
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
                String query = "DELETE FROM kategori WHERE id_kategori='" + id_kategori + "'";
                Database.queryExecute(query);
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                showDataTable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_deleteBtnMouseClicked

    private void editBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseClicked
        if (id_kategori == null) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih data pada baris tabel untuk memulai mengubah data!");
        } else {
            editKategoriPanel.setVisible(true);
            tabelKategori.setVisible(false);
            showDataEditPanel();
        }
    }//GEN-LAST:event_editBtnMouseClicked

    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        addKategoriPanel.setVisible(true);
        idKategori.setText("KT" + random.nextInt(999999));
        tabelKategori.setVisible(false);
    }//GEN-LAST:event_addBtnMouseClicked

    private void tabelKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKategoriMouseClicked
        int baris = tabelKategori.rowAtPoint(evt.getPoint());
        id_kategori = tabelKategori.getValueAt(baris, 0).toString();
    }//GEN-LAST:event_tabelKategoriMouseClicked

    private void editKategoriBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editKategoriBtnMouseClicked
        try {
            String query = "UPDATE kategori SET nama_kategori='" + namaKategoriEdit.getText() + "' WHERE id_kategori='" + id_kategori + "'";
            Database.queryExecute(query);
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
            idKategoriEdit.setText(null);
            namaKategoriEdit.setText(null);
            editKategoriPanel.setVisible(false);
            tabelKategori.setVisible(true);
            showDataTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_editKategoriBtnMouseClicked

    private void backEditBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backEditBtnMouseClicked
        editKategoriPanel.setVisible(false);
        tabelKategori.setVisible(true);
        idKategoriEdit.setText(null);
        namaKategoriEdit.setText(null);
        id_kategori = null;
    }//GEN-LAST:event_backEditBtnMouseClicked

    private void barangBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barangBtnMouseClicked
        this.setVisible(false);
        new Barang().setVisible(true);
    }//GEN-LAST:event_barangBtnMouseClicked

    private void addKategoriBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addKategoriBtnMouseClicked
        try {
            String sql = "INSERT INTO kategori (id_kategori, nama_kategori) "
                    + "VALUES ('" + idKategori.getText() + "', '" + namaKategori.getText() + "')";
            Database.queryExecute(sql);
            addKategoriPanel.setVisible(false);
            showDataTable();
            tabelKategori.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_addKategoriBtnMouseClicked

    private void backAddBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backAddBtnMouseClicked
        addKategoriPanel.setVisible(false);
        tabelKategori.setVisible(true);
        idKategori.setText(null);
        namaKategori.setText(null);
    }//GEN-LAST:event_backAddBtnMouseClicked

    private void penggunaBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penggunaBtnMouseClicked
        this.setVisible(false);
        try {
            new Pengguna().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Kategori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_penggunaBtnMouseClicked

    private void pemasokBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pemasokBtnMouseClicked
        this.setVisible(false);
        new Suplier().setVisible(true);
    }//GEN-LAST:event_pemasokBtnMouseClicked

    private void dashboardBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardBtnMouseClicked
        this.setVisible(false);
        new Dashboard().setVisible(true);
    }//GEN-LAST:event_dashboardBtnMouseClicked

    private void transJualBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transJualBtnMouseClicked
        this.setVisible(false);
        new TransaksiJual().setVisible(true);
    }//GEN-LAST:event_transJualBtnMouseClicked

    private void transBeliBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transBeliBtnMouseClicked
        this.setVisible(false);
        try {
            new TransaksiBeli().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Kategori.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Kategori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pengeluaranBtnMouseClicked

    public static void main(String args[]) {
        try {
            FlatArcIJTheme.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kategori().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addBtn;
    private javax.swing.JPanel addKategoriBtn;
    private javax.swing.JPanel addKategoriPanel;
    private javax.swing.JPanel backAddBtn;
    private javax.swing.JPanel backEditBtn;
    private javax.swing.JPanel barangBtn;
    private javax.swing.JPanel dashboardBtn;
    private javax.swing.JPanel deleteBtn;
    private javax.swing.JPanel editBtn;
    private javax.swing.JPanel editKategoriBtn;
    private javax.swing.JPanel editKategoriPanel;
    private javax.swing.JPanel homeBtn;
    private javax.swing.JTextField idKategori;
    private javax.swing.JTextField idKategoriEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel laporanBtn;
    private javax.swing.JTextField namaKategori;
    private javax.swing.JTextField namaKategoriEdit;
    private javax.swing.JPanel pemasokBtn;
    private javax.swing.JPanel pengeluaranBtn;
    private javax.swing.JPanel penggunaBtn;
    private javax.swing.JTextField searchItem;
    private javax.swing.JTable tabelKategori;
    private javax.swing.JPanel transBeliBtn;
    private javax.swing.JPanel transJualBtn;
    // End of variables declaration//GEN-END:variables
}
