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
import javax.swing.text.AbstractDocument;
import utils.*;

public class Barang extends javax.swing.JFrame {

    String id_barang = null;
    Random ramdom = new Random();
    String id_kategori = null;
    String fromPanel = null;

    public Barang() {
        initComponents();
        addItemPanel.setVisible(false);
        editItemPanel.setVisible(false);
        pilihKategoriPanel.setVisible(false);
        Model.table(tabelBarang);
        Model.table(tabelKategori);
        showDataTable();
        
        ((AbstractDocument) barcode.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        ((AbstractDocument) barcodeEdit.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        ((AbstractDocument) hargaEdit.getDocument()).setDocumentFilter(new CustomDocumentFilter());

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

                model.addColumn("Id");
                model.addColumn("Barcode");
                model.addColumn("Nama Barang");
                model.addColumn("Id Kategori");
                model.addColumn("Harga Jual");
                model.addColumn("Harga Beli");
                model.addColumn("Stok");
                model.addColumn("Tanggal");

                try {
                    String query = "SELECT * FROM barang WHERE CONCAT(nama_barang, barcode) LIKE '%" + searchItem.getText() + "%'";
                    ResultSet res = Database.queryResultSet(query);
                    while (res.next()) {
                        model.addRow(new Object[]{res.getString("id_barang"), res.getString("barcode"), res.getString("nama_barang"),
                            res.getString("id_kategori"), res.getString("harga_jual"), res.getString("harga_beli"), res.getString("qty"),
                            res.getString("tanggal")});
                    }
                    tabelBarang.setModel(model);
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
        model.addColumn("Barcode");
        model.addColumn("Nama Barang");
        model.addColumn("Id Kategori");
        model.addColumn("Harga Grosir");
        model.addColumn("Harga Beli");
        model.addColumn("Stok");
        model.addColumn("Tanggal");

        String sql = "SELECT * FROM barang";
        try {
            ResultSet res = Database.queryResultSet(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_barang"), res.getString("barcode"), res.getString("nama_barang"),
                    res.getString("id_kategori"), res.getString("harga_jual"), res.getString("harga_beli"), res.getString("qty"),
                    res.getString("tanggal")});
            }
            tabelBarang.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }

    void showDataEditPanel() {
        String sql = "SELECT * FROM barang WHERE id_barang='" + id_barang + "'";
        try {
            ResultSet res = Database.queryResultSet(sql);
            if (res.next()) {
                idBarangEdit.setText(id_barang);
                namaBarangEdit.setText(res.getString("nama_barang"));
                barcodeEdit.setText(res.getString("barcode"));
                hargaEdit.setText(res.getString("harga_jual"));
                kategoriEdit.setText(res.getString("id_kategori"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    void showDataKategori() {
        DefaultTableModel tk = new DefaultTableModel();
        tk.addColumn("No");
        tk.addColumn("Id Kategori");
        tk.addColumn("Nama Kategori");
        int no = 1;
        try {
            String query = "SELECT * FROM kategori";
            ResultSet result = Database.queryResultSet(query);
            while (result.next()) {
                tk.addRow(new Object[]{no++, result.getString("id_kategori"), result.getString("nama_kategori")});
            }
            tabelKategori.setModel(tk);
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pilihKategoriPanel = new javax.swing.JPanel();
        pilihPilihBtn = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelKategori = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        addItemPanel = new javax.swing.JPanel();
        pilihKategori = new javax.swing.JPanel();
        kategori = new javax.swing.JTextField();
        idBarang = new javax.swing.JTextField();
        namaBarang = new javax.swing.JTextField();
        barcode = new javax.swing.JTextField();
        tambahBtn = new javax.swing.JPanel();
        backBtn = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        editItemPanel = new javax.swing.JPanel();
        kategoriEdit = new javax.swing.JTextField();
        pilihKategoriEdit = new javax.swing.JPanel();
        idBarangEdit = new javax.swing.JTextField();
        hargaEdit = new javax.swing.JTextField();
        barcodeEdit = new javax.swing.JTextField();
        namaBarangEdit = new javax.swing.JTextField();
        editDataBtn = new javax.swing.JPanel();
        backEditBtn = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        addBtn = new javax.swing.JPanel();
        editBtn = new javax.swing.JPanel();
        deleteBtn = new javax.swing.JPanel();
        dashboardBtn = new javax.swing.JPanel();
        penggunaBtn = new javax.swing.JPanel();
        pengeluaranBtn = new javax.swing.JPanel();
        laporanBtn = new javax.swing.JPanel();
        transBeliBtn = new javax.swing.JPanel();
        transJualBtn = new javax.swing.JPanel();
        kategoriBtn = new javax.swing.JPanel();
        pemasokBtn = new javax.swing.JPanel();
        homeBtn = new javax.swing.JPanel();
        searchItem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TUBES_PBO- Dashboard Barang");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pilihKategoriPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pilihPilihBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pilihPilihBtn.setOpaque(false);
        pilihPilihBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pilihPilihBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pilihPilihBtnLayout = new javax.swing.GroupLayout(pilihPilihBtn);
        pilihPilihBtn.setLayout(pilihPilihBtnLayout);
        pilihPilihBtnLayout.setHorizontalGroup(
            pilihPilihBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        pilihPilihBtnLayout.setVerticalGroup(
            pilihPilihBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        pilihKategoriPanel.add(pilihPilihBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 520, 70, 20));

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
        jScrollPane2.setViewportView(tabelKategori);

        pilihKategoriPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 440, 250));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dashboard_barang_pilih_kategori_panel.png"))); // NOI18N
        pilihKategoriPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, -1, -1));

        jPanel1.add(pilihKategoriPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-250, 0, 1250, 700));

        addItemPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pilihKategori.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pilihKategori.setOpaque(false);
        pilihKategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pilihKategoriMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pilihKategoriLayout = new javax.swing.GroupLayout(pilihKategori);
        pilihKategori.setLayout(pilihKategoriLayout);
        pilihKategoriLayout.setHorizontalGroup(
            pilihKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        pilihKategoriLayout.setVerticalGroup(
            pilihKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        addItemPanel.add(pilihKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 580, 90, 30));

        kategori.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        kategori.setForeground(new java.awt.Color(56, 57, 71));
        kategori.setBorder(null);
        kategori.setOpaque(false);
        addItemPanel.add(kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 580, 180, 30));

        idBarang.setEditable(false);
        idBarang.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        idBarang.setForeground(new java.awt.Color(56, 57, 71));
        idBarang.setBorder(null);
        idBarang.setOpaque(false);
        addItemPanel.add(idBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 518, 280, 20));

        namaBarang.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        namaBarang.setForeground(new java.awt.Color(56, 57, 71));
        namaBarang.setBorder(null);
        namaBarang.setOpaque(false);
        addItemPanel.add(namaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 518, 280, 20));

        barcode.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        barcode.setForeground(new java.awt.Color(56, 57, 71));
        barcode.setBorder(null);
        barcode.setOpaque(false);
        addItemPanel.add(barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 586, 280, 20));

        tambahBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tambahBtn.setOpaque(false);
        tambahBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tambahBtnLayout = new javax.swing.GroupLayout(tambahBtn);
        tambahBtn.setLayout(tambahBtnLayout);
        tambahBtnLayout.setHorizontalGroup(
            tambahBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        tambahBtnLayout.setVerticalGroup(
            tambahBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        addItemPanel.add(tambahBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 631, -1, 30));

        backBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backBtn.setOpaque(false);
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backBtnLayout = new javax.swing.GroupLayout(backBtn);
        backBtn.setLayout(backBtnLayout);
        backBtnLayout.setHorizontalGroup(
            backBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        backBtnLayout.setVerticalGroup(
            backBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        addItemPanel.add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 20, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah_data_barang_panel.png"))); // NOI18N
        addItemPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(addItemPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 750, 700));

        editItemPanel.setBackground(new java.awt.Color(241, 245, 249));
        editItemPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kategoriEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        kategoriEdit.setForeground(new java.awt.Color(56, 57, 71));
        kategoriEdit.setBorder(null);
        kategoriEdit.setOpaque(false);
        editItemPanel.add(kategoriEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 580, 180, 30));

        pilihKategoriEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pilihKategoriEdit.setOpaque(false);
        pilihKategoriEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pilihKategoriEditMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pilihKategoriEditLayout = new javax.swing.GroupLayout(pilihKategoriEdit);
        pilihKategoriEdit.setLayout(pilihKategoriEditLayout);
        pilihKategoriEditLayout.setHorizontalGroup(
            pilihKategoriEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pilihKategoriEditLayout.setVerticalGroup(
            pilihKategoriEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        editItemPanel.add(pilihKategoriEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 580, -1, 30));

        idBarangEdit.setEditable(false);
        idBarangEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        idBarangEdit.setForeground(new java.awt.Color(56, 57, 71));
        idBarangEdit.setBorder(null);
        idBarangEdit.setOpaque(false);
        editItemPanel.add(idBarangEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 518, 180, 20));

        hargaEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        hargaEdit.setForeground(new java.awt.Color(56, 57, 71));
        hargaEdit.setBorder(null);
        hargaEdit.setOpaque(false);
        editItemPanel.add(hargaEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 586, 280, 20));

        barcodeEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        barcodeEdit.setForeground(new java.awt.Color(56, 57, 71));
        barcodeEdit.setBorder(null);
        barcodeEdit.setOpaque(false);
        editItemPanel.add(barcodeEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 518, 190, 20));

        namaBarangEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        namaBarangEdit.setForeground(new java.awt.Color(56, 57, 71));
        namaBarangEdit.setBorder(null);
        namaBarangEdit.setOpaque(false);
        editItemPanel.add(namaBarangEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 518, 180, 20));

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

        editItemPanel.add(editDataBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 632, 70, 30));

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

        editItemPanel.add(backEditBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 20, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ubah_data_barang_panel.png"))); // NOI18N
        editItemPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(editItemPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 750, 700));

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

        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 90, 80, 20));

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
            .addGap(0, 70, Short.MAX_VALUE)
        );
        editBtnLayout.setVerticalGroup(
            editBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 90, 70, 20));

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
            .addGap(0, 70, Short.MAX_VALUE)
        );
        deleteBtnLayout.setVerticalGroup(
            deleteBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, -1, 20));

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
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(pengeluaranBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, -1, -1));

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

        jPanel1.add(laporanBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, -1, -1));

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
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(transJualBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));

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
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(kategoriBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, 30));

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

        jPanel1.add(pemasokBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, 40));

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
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 630, -1, -1));

        searchItem.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        searchItem.setForeground(new java.awt.Color(48, 45, 65));
        searchItem.setBorder(null);
        searchItem.setOpaque(false);
        jPanel1.add(searchItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 180, 20));

        tabelBarang.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelBarang.setSelectionBackground(new java.awt.Color(229, 229, 229));
        tabelBarang.setSelectionForeground(new java.awt.Color(30, 30, 46));
        tabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBarang);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, 570, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dashboard_barang_page.png"))); // NOI18N
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
                String query = "DELETE FROM barang WHERE id_barang='" + id_barang + "'";
                Database.queryExecute(query);
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                showDataTable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_deleteBtnMouseClicked

    private void editBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseClicked
        if (id_barang == null) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih data pada baris tabel untuk memulai mengubah data!");
        } else {
            editItemPanel.setVisible(true);
            tabelBarang.setVisible(false);
            showDataEditPanel();
        }
    }//GEN-LAST:event_editBtnMouseClicked

    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        addItemPanel.setVisible(true);
        idBarang.setText("BR" + ramdom.nextInt(999999));
        tabelBarang.setVisible(false);
    }//GEN-LAST:event_addBtnMouseClicked

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        addItemPanel.setVisible(false);
        tabelBarang.setVisible(true);
        namaBarang.setText(null);
        barcode.setText(null);
    }//GEN-LAST:event_backBtnMouseClicked

    private void tambahBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahBtnMouseClicked
        try {
            String sql = "INSERT INTO barang (id_barang, barcode, nama_barang, id_kategori, tanggal) "
                    + "VALUES ('BR" + Attr.generateRandomNumber(999999) + "', '" + barcode.getText() + "', '" + namaBarang.getText() + "', '"
                    + 12 + "', '" + Attr.getDateNow("yyyy-MM-dd") + "')";
            Database.queryExecute(sql);
            addItemPanel.setVisible(false);
            idBarang.setText(null);
            namaBarang.setText(null);
            barcode.setText(null);
            showDataTable();
            tabelBarang.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_tambahBtnMouseClicked

    private void tabelBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseClicked
        int baris = tabelBarang.rowAtPoint(evt.getPoint());
        id_barang = tabelBarang.getValueAt(baris, 0).toString();
    }//GEN-LAST:event_tabelBarangMouseClicked

    private void editDataBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editDataBtnMouseClicked
        try {
            String query = "UPDATE barang SET nama_barang='" + namaBarangEdit.getText() + "', barcode='" + barcodeEdit.getText() + "',"
                    + "harga_jual=" + hargaEdit.getText() + " WHERE id_barang='" + id_barang + "'";
            Database.queryExecute(query);
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
            idBarangEdit.setText(null);
            namaBarangEdit.setText(null);
            barcodeEdit.setText(null);
            hargaEdit.setText(null);
            editItemPanel.setVisible(false);
            tabelBarang.setVisible(true);
            showDataTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_editDataBtnMouseClicked

    private void backEditBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backEditBtnMouseClicked
        editItemPanel.setVisible(false);
        tabelBarang.setVisible(true);
        idBarangEdit.setText(null);
        namaBarangEdit.setText(null);
        barcodeEdit.setText(null);
        hargaEdit.setText(null);
    }//GEN-LAST:event_backEditBtnMouseClicked

    private void penggunaBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penggunaBtnMouseClicked
        this.setVisible(false);
        try {
            new Pengguna().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_penggunaBtnMouseClicked

    private void pemasokBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pemasokBtnMouseClicked
        this.setVisible(false);
        new Suplier().setVisible(true);
    }//GEN-LAST:event_pemasokBtnMouseClicked

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
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pengeluaranBtnMouseClicked

    private void dashboardBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardBtnMouseClicked
        this.setVisible(false);
        new Dashboard().setVisible(true);
    }//GEN-LAST:event_dashboardBtnMouseClicked

    private void pilihKategoriEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihKategoriEditMouseClicked
        pilihKategoriPanel.setVisible(true);
        tabelKategori.setVisible(true);
        fromPanel = "Edit";
        showDataKategori();
        editItemPanel.setVisible(false);
    }//GEN-LAST:event_pilihKategoriEditMouseClicked

    private void pilihKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihKategoriMouseClicked
        pilihKategoriPanel.setVisible(true);
        tabelKategori.setVisible(true);
        fromPanel = "Tambah";
        showDataKategori();
        addItemPanel.setVisible(false);
    }//GEN-LAST:event_pilihKategoriMouseClicked

    private void tabelKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKategoriMouseClicked
        int baris = tabelKategori.rowAtPoint(evt.getPoint());
        id_kategori = tabelKategori.getValueAt(baris, 1).toString();
    }//GEN-LAST:event_tabelKategoriMouseClicked

    private void pilihPilihBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihPilihBtnMouseClicked
        if (fromPanel.equals("Tambah")) {
            kategori.setText(id_kategori);
            addItemPanel.setVisible(true);
        } else if (fromPanel.equals("Edit")) {
            kategoriEdit.setText(id_kategori);
            editItemPanel.setVisible(true);
        }
        pilihKategoriPanel.setVisible(false);
        tabelKategori.setVisible(false);
    }//GEN-LAST:event_pilihPilihBtnMouseClicked

    public static void main(String args[]) {
        try {
            FlatArcIJTheme.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addBtn;
    private javax.swing.JPanel addItemPanel;
    private javax.swing.JPanel backBtn;
    private javax.swing.JPanel backEditBtn;
    private javax.swing.JTextField barcode;
    private javax.swing.JTextField barcodeEdit;
    private javax.swing.JPanel dashboardBtn;
    private javax.swing.JPanel deleteBtn;
    private javax.swing.JPanel editBtn;
    private javax.swing.JPanel editDataBtn;
    private javax.swing.JPanel editItemPanel;
    private javax.swing.JTextField hargaEdit;
    private javax.swing.JPanel homeBtn;
    private javax.swing.JTextField idBarang;
    private javax.swing.JTextField idBarangEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextField kategori;
    private javax.swing.JPanel kategoriBtn;
    private javax.swing.JTextField kategoriEdit;
    private javax.swing.JPanel laporanBtn;
    private javax.swing.JTextField namaBarang;
    private javax.swing.JTextField namaBarangEdit;
    private javax.swing.JPanel pemasokBtn;
    private javax.swing.JPanel pengeluaranBtn;
    private javax.swing.JPanel penggunaBtn;
    private javax.swing.JPanel pilihKategori;
    private javax.swing.JPanel pilihKategoriEdit;
    private javax.swing.JPanel pilihKategoriPanel;
    private javax.swing.JPanel pilihPilihBtn;
    private javax.swing.JTextField searchItem;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JTable tabelKategori;
    private javax.swing.JPanel tambahBtn;
    private javax.swing.JPanel transBeliBtn;
    private javax.swing.JPanel transJualBtn;
    // End of variables declaration//GEN-END:variables
}
