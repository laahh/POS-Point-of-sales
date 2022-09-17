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

public class Suplier extends javax.swing.JFrame {

    String id_suplier = null;
    Random random = new Random();

    public Suplier() {
        initComponents();
        addPemasokPanel.setVisible(false);
        editPemasokPanel.setVisible(false);
        Model.table(tabelSupplier);
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
                model.addColumn("No. Hp");
                model.addColumn("Alamat");

                try {
                    String query = "SELECT * FROM supplier WHERE nama_supplier LIKE '%" + searchItem.getText() + "%'";
            ResultSet res = Database.queryResultSet(query);
                    while (res.next()) {
                        model.addRow(new Object[]{res.getString("id_supplier"), res.getString("nama_supplier"), res.getString("no_hp_supplier"),
                            res.getString("alamat_supplier")});
                    }
                    tabelSupplier.setModel(model);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }
        };

        searchItem.getDocument().addDocumentListener(dl);
    }

    void showDataTable() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Id");
        model.addColumn("Nama");
        model.addColumn("No. Hp");
        model.addColumn("Alamat");

        String sql = "SELECT * FROM supplier";
        try {
            ResultSet res = Database.queryResultSet(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_supplier"), res.getString("nama_supplier"), res.getString("no_hp_supplier"),
                    res.getString("alamat_supplier")});
            }
            tabelSupplier.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }

    void showDataEditPanel() {
        String sql = "SELECT * FROM supplier WHERE id_supplier='" + id_suplier + "'";
        try {
            ResultSet res = Database.queryResultSet(sql);
            if (res.next()) {
                idSuplierEdit.setText(id_suplier);
                namaSuplierEdit.setText(res.getString("nama_supplier"));
                alamatEdit.setText(res.getString("alamat_supplier"));
                noTelpEdit.setText(res.getString("no_hp_supplier"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        editPemasokPanel = new javax.swing.JPanel();
        idSuplierEdit = new javax.swing.JTextField();
        namaSuplierEdit = new javax.swing.JTextField();
        noTelpEdit = new javax.swing.JTextField();
        editDataBtn = new javax.swing.JPanel();
        backEditBtn = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        alamatEdit = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        addPemasokPanel = new javax.swing.JPanel();
        idSuplier = new javax.swing.JTextField();
        namaSuplier = new javax.swing.JTextField();
        noTelp = new javax.swing.JTextField();
        addPemasokBtn = new javax.swing.JPanel();
        backAddBtn = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        alamat = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        addBtn = new javax.swing.JPanel();
        editBtn = new javax.swing.JPanel();
        deleteBtn = new javax.swing.JPanel();
        dashboardBtn = new javax.swing.JPanel();
        barangName = new javax.swing.JPanel();
        pengeluaranBtn = new javax.swing.JPanel();
        laporanBtn = new javax.swing.JPanel();
        transBeliBtn = new javax.swing.JPanel();
        transJualBtn = new javax.swing.JPanel();
        kategoriBtn = new javax.swing.JPanel();
        penggunaBtn = new javax.swing.JPanel();
        homeBtn = new javax.swing.JPanel();
        searchItem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelSupplier = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TUBES_PBO - Dashboard Barang");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editPemasokPanel.setBackground(new java.awt.Color(241, 245, 249));
        editPemasokPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idSuplierEdit.setEditable(false);
        idSuplierEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        idSuplierEdit.setBorder(null);
        idSuplierEdit.setOpaque(false);
        editPemasokPanel.add(idSuplierEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 459, 180, 20));

        namaSuplierEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        namaSuplierEdit.setBorder(null);
        namaSuplierEdit.setOpaque(false);
        editPemasokPanel.add(namaSuplierEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 458, 180, 20));

        noTelpEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        noTelpEdit.setBorder(null);
        noTelpEdit.setOpaque(false);
        editPemasokPanel.add(noTelpEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 459, 190, 20));

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

        editPemasokPanel.add(editDataBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 630, 70, 30));

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

        editPemasokPanel.add(backEditBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 20, 20));

        alamatEdit.setColumns(20);
        alamatEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        alamatEdit.setRows(5);
        alamatEdit.setWrapStyleWord(true);
        alamatEdit.setBorder(null);
        alamatEdit.setOpaque(false);
        jScrollPane2.setViewportView(alamatEdit);

        editPemasokPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, 640, 70));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ubah_data_suplier_panel.png"))); // NOI18N
        editPemasokPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(editPemasokPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 750, 700));

        addPemasokPanel.setBackground(new java.awt.Color(241, 245, 249));
        addPemasokPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idSuplier.setEditable(false);
        idSuplier.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        idSuplier.setBorder(null);
        idSuplier.setOpaque(false);
        addPemasokPanel.add(idSuplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 457, 180, 20));

        namaSuplier.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        namaSuplier.setBorder(null);
        namaSuplier.setOpaque(false);
        addPemasokPanel.add(namaSuplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 457, 180, 20));

        noTelp.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        noTelp.setBorder(null);
        noTelp.setOpaque(false);
        addPemasokPanel.add(noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 457, 190, 20));

        addPemasokBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addPemasokBtn.setOpaque(false);
        addPemasokBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPemasokBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout addPemasokBtnLayout = new javax.swing.GroupLayout(addPemasokBtn);
        addPemasokBtn.setLayout(addPemasokBtnLayout);
        addPemasokBtnLayout.setHorizontalGroup(
            addPemasokBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        addPemasokBtnLayout.setVerticalGroup(
            addPemasokBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        addPemasokPanel.add(addPemasokBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 632, 100, 30));

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

        addPemasokPanel.add(backAddBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 20, 20));

        alamat.setColumns(20);
        alamat.setFont(new java.awt.Font("Quicksand", 0, 13)); // NOI18N
        alamat.setRows(5);
        alamat.setBorder(null);
        alamat.setOpaque(false);
        jScrollPane3.setViewportView(alamat);

        addPemasokPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, 640, 70));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah_data_suplier_panel.png"))); // NOI18N
        addPemasokPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(addPemasokPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 750, 700));

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

        barangName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        barangName.setOpaque(false);
        barangName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                barangNameMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout barangNameLayout = new javax.swing.GroupLayout(barangName);
        barangName.setLayout(barangNameLayout);
        barangNameLayout.setHorizontalGroup(
            barangNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        barangNameLayout.setVerticalGroup(
            barangNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(barangName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 40));

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
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(pengeluaranBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, -1, 40));

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

        jPanel1.add(laporanBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, -1, 30));

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
        searchItem.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                searchItemInputMethodTextChanged(evt);
            }
        });
        jPanel1.add(searchItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 180, 20));

        tabelSupplier.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        tabelSupplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelSupplier.setSelectionBackground(new java.awt.Color(229, 229, 229));
        tabelSupplier.setSelectionForeground(new java.awt.Color(30, 30, 46));
        tabelSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelSupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelSupplier);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 570, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dashboard_suplier_page.png"))); // NOI18N
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
                String query = "DELETE FROM supplier WHERE id_supplier='" + id_suplier + "'";
                Database.queryExecute(query);
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                showDataTable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_deleteBtnMouseClicked

    private void editBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseClicked
        if (id_suplier == null) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih data pada baris tabel untuk memulai mengubah data!");
        } else {
            editPemasokPanel.setVisible(true);
            tabelSupplier.setVisible(false);
            showDataEditPanel();
        }
    }//GEN-LAST:event_editBtnMouseClicked

    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        addPemasokPanel.setVisible(true);
        idSuplier.setText("SP" + random.nextInt(999999));
        tabelSupplier.setVisible(false);
    }//GEN-LAST:event_addBtnMouseClicked

    private void tabelSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelSupplierMouseClicked
        int baris = tabelSupplier.rowAtPoint(evt.getPoint());
        id_suplier = tabelSupplier.getValueAt(baris, 0).toString();
    }//GEN-LAST:event_tabelSupplierMouseClicked

    private void editDataBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editDataBtnMouseClicked
        try {
            String query = "UPDATE supplier SET nama_supplier='" + namaSuplierEdit.getText() + "', alamat_supplier='" + alamatEdit.getText() + "',"
                    + "no_hp_supplier='" + noTelpEdit.getText() + "' WHERE id_supplier='" + id_suplier + "'";
            Database.queryExecute(query);
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
            namaSuplierEdit.setText(null);
            noTelpEdit.setText(null);
            alamatEdit.setText(null);
            editPemasokPanel.setVisible(false);
            tabelSupplier.setVisible(true);
            showDataTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_editDataBtnMouseClicked

    private void backEditBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backEditBtnMouseClicked
        editPemasokPanel.setVisible(false);
        tabelSupplier.setVisible(true);
        namaSuplierEdit.setText(null);
        noTelpEdit.setText(null);
        alamatEdit.setText(null);
        id_suplier = null;
    }//GEN-LAST:event_backEditBtnMouseClicked

    private void searchItemInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_searchItemInputMethodTextChanged
        // TODO add your handling code here:
        System.out.println("hello");
    }//GEN-LAST:event_searchItemInputMethodTextChanged

    private void barangNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barangNameMouseClicked
        this.setVisible(false);
        new Barang().setVisible(true);
    }//GEN-LAST:event_barangNameMouseClicked

    private void addPemasokBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPemasokBtnMouseClicked
        try {
            String sql = "INSERT INTO supplier (id_supplier, nama_supplier, alamat_supplier, no_hp_supplier) "
                    + "VALUES ('" + idSuplier.getText() + "', '" + namaSuplier.getText() + "', '" + alamat.getText() + "', '"
                    + noTelp.getText() + "')";
            Database.queryExecute(sql);
            addPemasokPanel.setVisible(false);
            showDataTable();
            tabelSupplier.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_addPemasokBtnMouseClicked

    private void backAddBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backAddBtnMouseClicked
        addPemasokPanel.setVisible(false);
        tabelSupplier.setVisible(true);
        namaSuplier.setText(null);
        alamat.setText(null);
        noTelp.setText(null);
    }//GEN-LAST:event_backAddBtnMouseClicked

    private void penggunaBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penggunaBtnMouseClicked
        this.setVisible(false);
        try {
            new Pengguna().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Suplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_penggunaBtnMouseClicked

    private void kategoriBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kategoriBtnMouseClicked
        this.setVisible(false);
        new Kategori().setVisible(true);
    }//GEN-LAST:event_kategoriBtnMouseClicked

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
            Logger.getLogger(Suplier.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Suplier.class.getName()).log(Level.SEVERE, null, ex);
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
                new Suplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addBtn;
    private javax.swing.JPanel addPemasokBtn;
    private javax.swing.JPanel addPemasokPanel;
    private javax.swing.JTextArea alamat;
    private javax.swing.JTextArea alamatEdit;
    private javax.swing.JPanel backAddBtn;
    private javax.swing.JPanel backEditBtn;
    private javax.swing.JPanel barangName;
    private javax.swing.JPanel dashboardBtn;
    private javax.swing.JPanel deleteBtn;
    private javax.swing.JPanel editBtn;
    private javax.swing.JPanel editDataBtn;
    private javax.swing.JPanel editPemasokPanel;
    private javax.swing.JPanel homeBtn;
    private javax.swing.JTextField idSuplier;
    private javax.swing.JTextField idSuplierEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel kategoriBtn;
    private javax.swing.JPanel laporanBtn;
    private javax.swing.JTextField namaSuplier;
    private javax.swing.JTextField namaSuplierEdit;
    private javax.swing.JTextField noTelp;
    private javax.swing.JTextField noTelpEdit;
    private javax.swing.JPanel pengeluaranBtn;
    private javax.swing.JPanel penggunaBtn;
    private javax.swing.JTextField searchItem;
    private javax.swing.JTable tabelSupplier;
    private javax.swing.JPanel transBeliBtn;
    private javax.swing.JPanel transJualBtn;
    // End of variables declaration//GEN-END:variables
}
