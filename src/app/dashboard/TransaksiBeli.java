package app.dashboard;

import app.Beranda;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import utils.*;

public class TransaksiBeli extends javax.swing.JFrame {

    String id_pemasokan = "";
    String id_barang = "";
    String id_supplier = "";
    String nama_barang = "";
    String insertDetailTransaksiQuery = "INSERT INTO detail_pemasokan VALUES ";
    int no = 1;
    int totalHargaBeli = 0;
    int barisTabelPemasokan = 0;
    ArrayList<String> idBarangList = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DefaultTableModel modelTransBeli = new DefaultTableModel();

    public TransaksiBeli() throws SQLException {
        initComponents();
        addPemasokanPanel.setVisible(false);
        pilihSupplierPanel.setVisible(false);
        pilihBarangPanel.setVisible(false);
        tabelSupplier.setVisible(false);
        tabelBarang.setVisible(false);
        Model.table(tabelTransBeli);
        Model.table(tabelPemasokan);
        Model.table(tabelBarang);
        Model.table(tabelSupplier);

        modelTransBeli.addColumn("Id Barang");
        modelTransBeli.addColumn("Nama Barang");
        modelTransBeli.addColumn("Harga Beli");
        modelTransBeli.addColumn("Harga Jual");
        modelTransBeli.addColumn("qty");

        tabelPemasokan.setModel(modelTransBeli);

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

                model.addColumn("Id Pemasokan");
                model.addColumn("Tanggal");
                model.addColumn("Nama Suplier");
                model.addColumn("Total Harga");

                try {
                    String query = "SELECT * FROM pemasokan p JOIN supplier s ON p.id_supplier=s.id_supplier WHERE id_pemasokan LIKE '%" + searchItem.getText() + "%'";
                    ResultSet res = Database.queryResultSet(query);
                    while (res.next()) {
                        model.addRow(new Object[]{res.getString("p.id_pemasokan"), res.getString("p.tanggal"), res.getString("s.nama_supplier"),
                            res.getString("p.total_harga_beli")});
                    }
                    tabelTransBeli.setModel(model);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }
        };

        DocumentListener dl2 = new DocumentListener() {

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

                model.addColumn("No");
                model.addColumn("Id Barang");
                model.addColumn("Nama Barang");
                
                int no = 1;
                
                try {
                    String query = "SELECT * FROM barang WHERE CONCAT(id_barang, nama_barang) LIKE '%" + searchBarPilihBarang.getText() + "%'";
                    ResultSet result = Database.queryResultSet(query);
                    while (result.next()) {
                        model.addRow(new Object[]{no++, result.getString("id_barang"), result.getString("nama_barang")});
                    }
                    tabelBarang.setModel(model);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }
        };

        searchItem.getDocument().addDocumentListener(dl);
        searchBarPilihBarang.getDocument().addDocumentListener(dl2);

        ((AbstractDocument) qty.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        ((AbstractDocument) hargaBeli.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        ((AbstractDocument) hargaJual.getDocument()).setDocumentFilter(new CustomDocumentFilter());
    }

    void showDataTable() throws SQLException {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Id Pemasokan");
        model.addColumn("Tanggal");
        model.addColumn("Nama Suplier");
        model.addColumn("Total Harga");

        int no = 1;

        String sql = "SELECT * FROM pemasokan p JOIN supplier s ON p.id_supplier=s.id_supplier";
        ResultSet res = Database.queryResultSet(sql);
        try {
            while (res.next()) {
                model.addRow(new Object[]{res.getString("p.id_pemasokan"), res.getString("p.tanggal"), res.getString("s.nama_supplier"),
                    res.getString("p.total_harga_beli")});
            }
            tabelTransBeli.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }

    void showPilihSupplierPanel() {
        addPemasokanPanel.setVisible(false);
        pilihSupplierPanel.setVisible(true);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Id Supplier");
        model.addColumn("Nama Supplier");
        tabelSupplier.setVisible(true);
        int no = 1;
        try {
            String query = "SELECT * FROM supplier";
            ResultSet result = Database.queryResultSet(query);
            while (result.next()) {
                model.addRow(new Object[]{no++, result.getString("id_supplier"), result.getString("nama_supplier")});
            }
            tabelSupplier.setModel(model);
        } catch (SQLException e) {
        }
    }

    void showPilihBarangPanel() {
        addPemasokanPanel.setVisible(false);
        pilihBarangPanel.setVisible(true);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Id Barang");
        model.addColumn("Nama Barang");
        tabelBarang.setVisible(true);
        int no = 1;
        try {
            String query = "SELECT * FROM barang";
            ResultSet result = Database.queryResultSet(query);
            while (result.next()) {
                model.addRow(new Object[]{no++, result.getString("id_barang"), result.getString("nama_barang")});
            }
            tabelBarang.setModel(model);
        } catch (SQLException e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pilihBarangPanel = new javax.swing.JPanel();
        pilihPilihBarangBtn = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();
        searchBarPilihBarang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pilihSupplierPanel = new javax.swing.JPanel();
        pilihPilihSupplierBtn = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelSupplier = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        addPemasokanPanel = new javax.swing.JPanel();
        tambahPemasokan = new javax.swing.JPanel();
        hapus = new javax.swing.JPanel();
        tambah = new javax.swing.JPanel();
        pilihBarang = new javax.swing.JPanel();
        pilihSupplier = new javax.swing.JPanel();
        hargaJual = new javax.swing.JTextField();
        hargaBeli = new javax.swing.JTextField();
        qty = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPemasokan = new javax.swing.JTable();
        tanggal = new com.toedter.calendar.JDateChooser();
        idBarang = new javax.swing.JTextField();
        idSupplier = new javax.swing.JTextField();
        idPemasokan = new javax.swing.JTextField();
        backAddBtn = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        addBtn = new javax.swing.JPanel();
        deleteBtn = new javax.swing.JPanel();
        dashboardBtn = new javax.swing.JPanel();
        barangBtn = new javax.swing.JPanel();
        pengeluaranBtn = new javax.swing.JPanel();
        laporanBtn = new javax.swing.JPanel();
        penggunaBtn = new javax.swing.JPanel();
        transJualBtn = new javax.swing.JPanel();
        kategoriBtn = new javax.swing.JPanel();
        pemasokBtn = new javax.swing.JPanel();
        homeBtn = new javax.swing.JPanel();
        searchItem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTransBeli = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TUBES_PBO - Dashboard Barang");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pilihBarangPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pilihPilihBarangBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pilihPilihBarangBtn.setOpaque(false);
        pilihPilihBarangBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pilihPilihBarangBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pilihPilihBarangBtnLayout = new javax.swing.GroupLayout(pilihPilihBarangBtn);
        pilihPilihBarangBtn.setLayout(pilihPilihBarangBtnLayout);
        pilihPilihBarangBtnLayout.setHorizontalGroup(
            pilihPilihBarangBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        pilihPilihBarangBtnLayout.setVerticalGroup(
            pilihPilihBarangBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        pilihBarangPanel.add(pilihPilihBarangBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 520, 70, 20));

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
        tabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBarangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelBarang);

        pilihBarangPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 280, 440, 210));

        searchBarPilihBarang.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        searchBarPilihBarang.setForeground(new java.awt.Color(48, 45, 65));
        searchBarPilihBarang.setBorder(null);
        searchBarPilihBarang.setOpaque(false);
        pilihBarangPanel.add(searchBarPilihBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 192, 410, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dashboard_transaksi_beli_pilih_barang_panel.png"))); // NOI18N
        pilihBarangPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(pilihBarangPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 700));

        pilihSupplierPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pilihPilihSupplierBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pilihPilihSupplierBtn.setOpaque(false);
        pilihPilihSupplierBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pilihPilihSupplierBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pilihPilihSupplierBtnLayout = new javax.swing.GroupLayout(pilihPilihSupplierBtn);
        pilihPilihSupplierBtn.setLayout(pilihPilihSupplierBtnLayout);
        pilihPilihSupplierBtnLayout.setHorizontalGroup(
            pilihPilihSupplierBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        pilihPilihSupplierBtnLayout.setVerticalGroup(
            pilihPilihSupplierBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        pilihSupplierPanel.add(pilihPilihSupplierBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 520, 70, 20));

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
        tabelSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelSupplierMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelSupplier);

        pilihSupplierPanel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 240, 440, 250));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dashboard_transaksi_beli_pilih_supplier_panel.png"))); // NOI18N
        pilihSupplierPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(pilihSupplierPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 700));

        addPemasokanPanel.setBackground(new java.awt.Color(241, 245, 249));
        addPemasokanPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tambahPemasokan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tambahPemasokan.setOpaque(false);
        tambahPemasokan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahPemasokanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tambahPemasokanLayout = new javax.swing.GroupLayout(tambahPemasokan);
        tambahPemasokan.setLayout(tambahPemasokanLayout);
        tambahPemasokanLayout.setHorizontalGroup(
            tambahPemasokanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        tambahPemasokanLayout.setVerticalGroup(
            tambahPemasokanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        addPemasokanPanel.add(tambahPemasokan, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 585, 190, 30));

        hapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hapus.setOpaque(false);
        hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hapusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout hapusLayout = new javax.swing.GroupLayout(hapus);
        hapus.setLayout(hapusLayout);
        hapusLayout.setHorizontalGroup(
            hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        hapusLayout.setVerticalGroup(
            hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        addPemasokanPanel.add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 465, 90, 30));

        tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tambah.setOpaque(false);
        tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tambahLayout = new javax.swing.GroupLayout(tambah);
        tambah.setLayout(tambahLayout);
        tambahLayout.setHorizontalGroup(
            tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        tambahLayout.setVerticalGroup(
            tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        addPemasokanPanel.add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 465, 90, 30));

        pilihBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pilihBarang.setOpaque(false);
        pilihBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pilihBarangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pilihBarangLayout = new javax.swing.GroupLayout(pilihBarang);
        pilihBarang.setLayout(pilihBarangLayout);
        pilihBarangLayout.setHorizontalGroup(
            pilihBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pilihBarangLayout.setVerticalGroup(
            pilihBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        addPemasokanPanel.add(pilihBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 217, -1, 30));

        pilihSupplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pilihSupplier.setOpaque(false);
        pilihSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pilihSupplierMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pilihSupplierLayout = new javax.swing.GroupLayout(pilihSupplier);
        pilihSupplier.setLayout(pilihSupplierLayout);
        pilihSupplierLayout.setHorizontalGroup(
            pilihSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pilihSupplierLayout.setVerticalGroup(
            pilihSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        addPemasokanPanel.add(pilihSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, -1, 30));

        hargaJual.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        hargaJual.setBorder(null);
        hargaJual.setOpaque(false);
        addPemasokanPanel.add(hargaJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 420, 190, 30));

        hargaBeli.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        hargaBeli.setBorder(null);
        hargaBeli.setOpaque(false);
        addPemasokanPanel.add(hargaBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 352, 190, 30));

        qty.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        qty.setBorder(null);
        qty.setOpaque(false);
        addPemasokanPanel.add(qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 285, 190, 30));

        tabelPemasokan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPemasokan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPemasokanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelPemasokan);

        addPemasokanPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 410, 290));

        tanggal.setDateFormatString("yyyy-MM-dd");
        addPemasokanPanel.add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 215, 250, 30));

        idBarang.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        idBarang.setBorder(null);
        idBarang.setOpaque(false);
        addPemasokanPanel.add(idBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 217, 220, 30));

        idSupplier.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        idSupplier.setBorder(null);
        idSupplier.setOpaque(false);
        addPemasokanPanel.add(idSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 147, 220, 30));

        idPemasokan.setEditable(false);
        idPemasokan.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        idPemasokan.setBorder(null);
        idPemasokan.setOpaque(false);
        addPemasokanPanel.add(idPemasokan, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 147, 230, 30));

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

        addPemasokanPanel.add(backAddBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 20, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah_data_pemasok_panel.png"))); // NOI18N
        addPemasokanPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(addPemasokanPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 750, 700));

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

        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, 70, 20));

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
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(laporanBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, -1, 40));

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
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, -1, 40));

        searchItem.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        searchItem.setForeground(new java.awt.Color(48, 45, 65));
        searchItem.setBorder(null);
        searchItem.setOpaque(false);
        jPanel1.add(searchItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 180, 20));

        tabelTransBeli.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        tabelTransBeli.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelTransBeli.setSelectionBackground(new java.awt.Color(229, 229, 229));
        tabelTransBeli.setSelectionForeground(new java.awt.Color(30, 30, 46));
        tabelTransBeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelTransBeliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelTransBeli);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 227, 0, 380));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dashboard_transaksi_beli_page.png"))); // NOI18N
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
                String query = "DELETE FROM detail_pemasokan WHERE id_pemasokan='" + id_pemasokan + "'";
                String query2 = "DELETE FROM pemasokan WHERE id_pemasokan='" + id_pemasokan + "'";
                Database.queryExecute(query);
                Database.queryExecute(query2);
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                showDataTable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_deleteBtnMouseClicked

    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        addPemasokanPanel.setVisible(true);
        idPemasokan.setText("PMSK" + Attr.getDateNow("yyyyMMdd") + Attr.generateRandomNumber(999));
        tabelTransBeli.setVisible(false);
    }//GEN-LAST:event_addBtnMouseClicked

    private void tabelTransBeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTransBeliMouseClicked
        int baris = tabelTransBeli.rowAtPoint(evt.getPoint());
        id_pemasokan = tabelTransBeli.getValueAt(baris, 0).toString();
    }//GEN-LAST:event_tabelTransBeliMouseClicked

    private void barangBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barangBtnMouseClicked
        this.setVisible(false);
        new Barang().setVisible(true);
    }//GEN-LAST:event_barangBtnMouseClicked

    private void backAddBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backAddBtnMouseClicked
        addPemasokanPanel.setVisible(false);
        tabelPemasokan.setVisible(false);
        tabelTransBeli.setVisible(true);
        idPemasokan.setText(null);
        idBarang.setText(null);
        qty.setText("0");
        hargaBeli.setText("0");
        hargaJual.setText("0");
        int rowsToRemove = modelTransBeli.getRowCount();
        for (int i = rowsToRemove - 1; i >= 0; i--) {
            modelTransBeli.removeRow(i);
        }
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

    private void penggunaBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penggunaBtnMouseClicked
        this.setVisible(false);
        try {
            new Pengguna().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiBeli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_penggunaBtnMouseClicked

    private void laporanBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanBtnMouseClicked
        this.setVisible(false);
        new Laporan().setVisible(true);
    }//GEN-LAST:event_laporanBtnMouseClicked

    private void pengeluaranBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengeluaranBtnMouseClicked
        this.setVisible(false);
        try {
            new Pengeluaran().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiBeli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pengeluaranBtnMouseClicked

    private void pilihPilihSupplierBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihPilihSupplierBtnMouseClicked
        pilihSupplierPanel.setVisible(false);
        tabelSupplier.setVisible(false);
        addPemasokanPanel.setVisible(true);
        idSupplier.setText(id_supplier);
    }//GEN-LAST:event_pilihPilihSupplierBtnMouseClicked

    private void tabelSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelSupplierMouseClicked
        int baris = tabelSupplier.rowAtPoint(evt.getPoint());
        id_supplier = tabelSupplier.getValueAt(baris, 1).toString();

    }//GEN-LAST:event_tabelSupplierMouseClicked

    private void pilihSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihSupplierMouseClicked
        showPilihSupplierPanel();
    }//GEN-LAST:event_pilihSupplierMouseClicked

    private void pilihBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihBarangMouseClicked
        showPilihBarangPanel();
    }//GEN-LAST:event_pilihBarangMouseClicked

    private void tabelBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseClicked
        int baris = tabelBarang.rowAtPoint(evt.getPoint());
        id_barang = tabelBarang.getValueAt(baris, 1).toString();
        nama_barang = tabelBarang.getValueAt(baris, 2).toString();
    }//GEN-LAST:event_tabelBarangMouseClicked

    private void pilihPilihBarangBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihPilihBarangBtnMouseClicked
        pilihBarangPanel.setVisible(false);
        tabelBarang.setVisible(false);
        addPemasokanPanel.setVisible(true);
        idBarang.setText(id_barang);
    }//GEN-LAST:event_pilihPilihBarangBtnMouseClicked

    private void tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahMouseClicked
        if (idSupplier.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan masukan id supplier terlebih dahulu!");
        } else if (idBarang.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan masukan id barang terlebih dahulu!");
        } else if (qty.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan masukan kuantitas terlebih dahulu!");
        } else if (hargaBeli.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan masukan harga beli terlebih dahulu!");
        } else if (hargaJual.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan masukan harga jual terlebih dahulu!");
        } else {
            boolean result = idBarangList.contains(idBarang.getText());
            if (!result) {
                idBarangList.add(idBarang.getText());
                totalHargaBeli += (Integer.parseInt(hargaBeli.getText()) * Integer.parseInt(qty.getText()));
                modelTransBeli.addRow(new Object[]{idBarang.getText(), nama_barang, hargaBeli.getText(), hargaJual.getText(), qty.getText()});
                tabelBarang.setModel(modelTransBeli);
            }
        }
    }//GEN-LAST:event_tambahMouseClicked

    private void hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusMouseClicked
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data?");
        String idBarangText = tabelPemasokan.getValueAt(barisTabelPemasokan, 0).toString();
        String hargaBeliBarang = tabelPemasokan.getValueAt(barisTabelPemasokan, 2).toString();
        String qtyBarang = tabelPemasokan.getValueAt(barisTabelPemasokan, 4).toString();
        if (confirm == 0) {
            modelTransBeli.removeRow(barisTabelPemasokan);
            no--;
            totalHargaBeli -= (Integer.parseInt(hargaBeliBarang) * Integer.parseInt(qtyBarang));
            idBarangList.remove(idBarangText);
        }
    }//GEN-LAST:event_hapusMouseClicked

    private void tabelPemasokanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPemasokanMouseClicked
        barisTabelPemasokan = tabelPemasokan.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_tabelPemasokanMouseClicked

    private void tambahPemasokanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahPemasokanMouseClicked
        for (int i = 0; i < modelTransBeli.getDataVector().size(); i++) {
            String idBarang = modelTransBeli.getValueAt(i, 0).toString();
            String qty = modelTransBeli.getValueAt(i, 4).toString();
            String hargaBeliBarang = modelTransBeli.getValueAt(i, 2).toString();
            String hargaJualBarang = modelTransBeli.getValueAt(i, 3).toString();
            if (i != 0) {
                insertDetailTransaksiQuery += ", ";
            }
            insertDetailTransaksiQuery += "(" + qty + ", '" + idPemasokan.getText() + "', '" + idBarang + "', " + hargaBeliBarang + ", " + hargaJualBarang + ")";
        }
        try {
            String query = "SELECT * FROM pemasokan WHERE id_pemasokan='" + idPemasokan.getText() + "'";
            Connection conn = (Connection) Database.getConnection();
            ResultSet res = Database.queryResultSet(query);
            try {
                conn.setAutoCommit(false);
                if (!res.next()) {
                    String query2 = "INSERT INTO pemasokan (id_pemasokan, tanggal, id_supplier) VALUES ('" + idPemasokan.getText() + "', '" + Attr.formatDate(tanggal.getDate()) + "', '" + idSupplier.getText() + "')";
                    Database.queryExecute(query2);
                }
                Database.queryExecute(insertDetailTransaksiQuery);
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
            }
        } catch (Exception e) {
        }
        addPemasokanPanel.setVisible(false);
        idSupplier.setText(null);
        idBarang.setText(null);
        qty.setText("0");
        hargaBeli.setText("0");
        hargaJual.setText("0");
        tabelTransBeli.setVisible(true);
        try {
            showDataTable();
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiBeli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tambahPemasokanMouseClicked

    public static void main(String args[]) {
        try {
            FlatArcIJTheme.setup();
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TransaksiBeli().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiBeli.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addBtn;
    private javax.swing.JPanel addPemasokanPanel;
    private javax.swing.JPanel backAddBtn;
    private javax.swing.JPanel barangBtn;
    private javax.swing.JPanel dashboardBtn;
    private javax.swing.JPanel deleteBtn;
    private javax.swing.JPanel hapus;
    private javax.swing.JTextField hargaBeli;
    private javax.swing.JTextField hargaJual;
    private javax.swing.JPanel homeBtn;
    private javax.swing.JTextField idBarang;
    private javax.swing.JTextField idPemasokan;
    private javax.swing.JTextField idSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel kategoriBtn;
    private javax.swing.JPanel laporanBtn;
    private javax.swing.JPanel pemasokBtn;
    private javax.swing.JPanel pengeluaranBtn;
    private javax.swing.JPanel penggunaBtn;
    private javax.swing.JPanel pilihBarang;
    private javax.swing.JPanel pilihBarangPanel;
    private javax.swing.JPanel pilihPilihBarangBtn;
    private javax.swing.JPanel pilihPilihSupplierBtn;
    private javax.swing.JPanel pilihSupplier;
    private javax.swing.JPanel pilihSupplierPanel;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField searchBarPilihBarang;
    private javax.swing.JTextField searchItem;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JTable tabelPemasokan;
    private javax.swing.JTable tabelSupplier;
    private javax.swing.JTable tabelTransBeli;
    private javax.swing.JPanel tambah;
    private javax.swing.JPanel tambahPemasokan;
    private com.toedter.calendar.JDateChooser tanggal;
    private javax.swing.JPanel transJualBtn;
    // End of variables declaration//GEN-END:variables
}
