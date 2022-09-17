package app.dashboard;

import app.Beranda;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import utils.*;

public class Pengeluaran extends javax.swing.JFrame {

    String id_pengeluaran = null;
    String id_pengguna = "";
    int barisTabelPemasokan = 0;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DefaultTableModel modelPengeluaran = new DefaultTableModel();

    public Pengeluaran() throws SQLException {
        initComponents();
        addPengeluaranPanel.setVisible(false);
        editPengeluaranPanel.setVisible(false);
        pilihPenggunaTambahPanel.setVisible(false);
        pilihPenggunaUbahPanel.setVisible(false);
        tabelPengguna.setVisible(false);
        Model.table(tabelPengeluaran);
        Model.table(tabelPenggunaEdit);
        Model.table(tabelPengguna);

        modelPengeluaran.addColumn("Id Pengeluaran");
        modelPengeluaran.addColumn("Nama Pengeluaran");
        modelPengeluaran.addColumn("Nominal");
        modelPengeluaran.addColumn("Tanggal");
        modelPengeluaran.addColumn("Pengguna");

        tabelPengeluaran.setModel(modelPengeluaran);

        showDataTable();
        queryHitungPengeluaranHariIni();

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

                model.addColumn("Id Pengeluaran");
                model.addColumn("Nama Pengeluaran");
                model.addColumn("Nominal");
                model.addColumn("Tanggal");
                model.addColumn("Pengguna");

                try {
                    String query = "SELECT * FROM pengeluaran p JOIN users u ON p.id_user=u.id_user WHERE CONCAT(id_pengeluaran, nama_pengeluaran) LIKE '%" + searchItem.getText() + "%'";
                    ResultSet res = Database.queryResultSet(query);
                    while (res.next()) {
                        model.addRow(new Object[]{res.getString("p.id_pengeluaran"), res.getString("p.nama_pengeluaran"), res.getString("p.nominal"),
                            res.getString("p.tanggal"), res.getString("u.nama_user")});
                    }
                    tabelPengeluaran.setModel(model);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }
        };

        searchItem.getDocument().addDocumentListener(dl);
        searchBarPilihPenggunaTambah.getDocument().addDocumentListener(Attr.searchUsersDL(tabelPengguna, searchBarPilihPenggunaTambah));
        searchBarPilihPenggunaUbah.getDocument().addDocumentListener(Attr.searchUsersDL(tabelPenggunaEdit, searchBarPilihPenggunaUbah));

        ((AbstractDocument) nominal.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        ((AbstractDocument) nominalEdit.getDocument()).setDocumentFilter(new CustomDocumentFilter());
    }

    void showDataTable() throws SQLException {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Id Pengeluaran");
        model.addColumn("Nama Pengeluaran");
        model.addColumn("Nominal");
        model.addColumn("Tanggal");
        model.addColumn("Pengguna");

        int no = 1;

        String sql = "SELECT * FROM pengeluaran p JOIN users u ON p.id_user=u.id_user";
        ResultSet res = Database.queryResultSet(sql);
        try {
            while (res.next()) {
                model.addRow(new Object[]{res.getString("p.id_pengeluaran"), res.getString("p.nama_pengeluaran"), res.getString("p.nominal"),
                    res.getString("p.tanggal"), res.getString("u.nama_user")});
            }
            tabelPengeluaran.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }

    void showPilihPenggunaPanel() {
        addPengeluaranPanel.setVisible(false);
        pilihPenggunaTambahPanel.setVisible(true);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Pengguna");
        model.addColumn("Nama Pengguna");
        tabelPengguna.setVisible(true);
        try {
            String query = "SELECT * FROM users";
            ResultSet result = Database.queryResultSet(query);
            while (result.next()) {
                model.addRow(new Object[]{result.getString("id_user"), result.getString("nama_user")});
            }
            tabelPengguna.setModel(model);
        } catch (SQLException e) {
        }
    }

    void showPilihPenggunaEditPanel() {
        addPengeluaranPanel.setVisible(false);
        pilihPenggunaUbahPanel.setVisible(true);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Pengguna");
        model.addColumn("Nama Pengguna");
        tabelPenggunaEdit.setVisible(true);
        try {
            String query = "SELECT * FROM barang";
            ResultSet result = Database.queryResultSet(query);
            while (result.next()) {
                model.addRow(new Object[]{result.getString("id_barang"), result.getString("nama_barang")});
            }
            tabelPenggunaEdit.setModel(model);
        } catch (SQLException e) {
        }
    }

    void showDataEditPanel() throws ParseException {
        String sql = "SELECT * FROM pengeluaran WHERE id_pengeluaran='" + id_pengeluaran + "'";
        try {
            ResultSet res = Database.queryResultSet(sql);
            if (res.next()) {
                idPengeluaranEdit.setText(id_pengeluaran);
                namaPengeluaranEdit.setText(res.getString("nama_pengeluaran"));
                nominalEdit.setText(res.getString("nominal"));
                idPenggunaEdit.setText(res.getString("id_user"));
                tanggalEdit.setDate(dateFormat.parse(res.getString("tanggal")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    void queryHitungPengeluaranHariIni() {
        try {
            String query = "SELECT SUM(nominal) FROM pengeluaran WHERE MONTH(tanggal)=MONTH('" + Attr.getDateNow("yyyy-MM-dd") + "')";
            ResultSet result = Database.queryResultSet(query);
            if (result.next()) {
                pengeluaranHariIni.setText(Attr.kursIndo(result.getString(1)));
            }
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pilihPenggunaUbahPanel = new javax.swing.JPanel();
        pilihPenggunaUbahBtn = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelPenggunaEdit = new javax.swing.JTable();
        searchBarPilihPenggunaUbah = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pilihPenggunaTambahPanel = new javax.swing.JPanel();
        pilihPenggunaTambahBtn = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelPengguna = new javax.swing.JTable();
        searchBarPilihPenggunaTambah = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        addPengeluaranPanel = new javax.swing.JPanel();
        backBtn = new javax.swing.JPanel();
        tambahPengeluaranBtn = new javax.swing.JPanel();
        pilihBtn = new javax.swing.JPanel();
        idPengguna = new javax.swing.JTextField();
        nominal = new javax.swing.JTextField();
        namaPengeluaran = new javax.swing.JTextField();
        idPengeluaran = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        editPengeluaranPanel = new javax.swing.JPanel();
        backBtnEdit = new javax.swing.JPanel();
        ubahPengeluaranBtn = new javax.swing.JPanel();
        pilihBtnEdit = new javax.swing.JPanel();
        idPenggunaEdit = new javax.swing.JTextField();
        nominalEdit = new javax.swing.JTextField();
        namaPengeluaranEdit = new javax.swing.JTextField();
        idPengeluaranEdit = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        pengeluaranHariIni = new javax.swing.JLabel();
        addBtn = new javax.swing.JPanel();
        editBtn = new javax.swing.JPanel();
        deleteBtn = new javax.swing.JPanel();
        dashboardBtn = new javax.swing.JPanel();
        barangBtn = new javax.swing.JPanel();
        pengeluaranBtn = new javax.swing.JPanel();
        laporanBtn = new javax.swing.JPanel();
        penggunaBtn = new javax.swing.JPanel();
        transBeliBtn = new javax.swing.JPanel();
        transJualBtn = new javax.swing.JPanel();
        kategoriBtn = new javax.swing.JPanel();
        pemasokBtn = new javax.swing.JPanel();
        homeBtn = new javax.swing.JPanel();
        searchItem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPengeluaran = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TUBES_PBO - Dashboard Barang");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pilihPenggunaUbahPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pilihPenggunaUbahBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pilihPenggunaUbahBtn.setOpaque(false);
        pilihPenggunaUbahBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pilihPenggunaUbahBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pilihPenggunaUbahBtnLayout = new javax.swing.GroupLayout(pilihPenggunaUbahBtn);
        pilihPenggunaUbahBtn.setLayout(pilihPenggunaUbahBtnLayout);
        pilihPenggunaUbahBtnLayout.setHorizontalGroup(
            pilihPenggunaUbahBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        pilihPenggunaUbahBtnLayout.setVerticalGroup(
            pilihPenggunaUbahBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        pilihPenggunaUbahPanel.add(pilihPenggunaUbahBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 520, 70, 20));

        tabelPenggunaEdit.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPenggunaEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPenggunaEditMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabelPenggunaEdit);

        pilihPenggunaUbahPanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 280, 440, 210));

        searchBarPilihPenggunaUbah.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        searchBarPilihPenggunaUbah.setForeground(new java.awt.Color(48, 45, 65));
        searchBarPilihPenggunaUbah.setBorder(null);
        searchBarPilihPenggunaUbah.setOpaque(false);
        pilihPenggunaUbahPanel.add(searchBarPilihPenggunaUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 192, 410, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pilih_pengguna_ubah_data_pengeluaran.png"))); // NOI18N
        pilihPenggunaUbahPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(pilihPenggunaUbahPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 700));

        pilihPenggunaTambahPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pilihPenggunaTambahBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pilihPenggunaTambahBtn.setOpaque(false);
        pilihPenggunaTambahBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pilihPenggunaTambahBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pilihPenggunaTambahBtnLayout = new javax.swing.GroupLayout(pilihPenggunaTambahBtn);
        pilihPenggunaTambahBtn.setLayout(pilihPenggunaTambahBtnLayout);
        pilihPenggunaTambahBtnLayout.setHorizontalGroup(
            pilihPenggunaTambahBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        pilihPenggunaTambahBtnLayout.setVerticalGroup(
            pilihPenggunaTambahBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        pilihPenggunaTambahPanel.add(pilihPenggunaTambahBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 520, 70, 20));

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
        tabelPengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPenggunaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelPengguna);

        pilihPenggunaTambahPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 280, 440, 210));

        searchBarPilihPenggunaTambah.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        searchBarPilihPenggunaTambah.setForeground(new java.awt.Color(48, 45, 65));
        searchBarPilihPenggunaTambah.setBorder(null);
        searchBarPilihPenggunaTambah.setOpaque(false);
        pilihPenggunaTambahPanel.add(searchBarPilihPenggunaTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 192, 410, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pilih_pengguna_tambah_data_pengeluaran.png"))); // NOI18N
        pilihPenggunaTambahPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(pilihPenggunaTambahPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 700));

        addPengeluaranPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        addPengeluaranPanel.add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 20, 20));

        tambahPengeluaranBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tambahPengeluaranBtn.setOpaque(false);
        tambahPengeluaranBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahPengeluaranBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tambahPengeluaranBtnLayout = new javax.swing.GroupLayout(tambahPengeluaranBtn);
        tambahPengeluaranBtn.setLayout(tambahPengeluaranBtnLayout);
        tambahPengeluaranBtnLayout.setHorizontalGroup(
            tambahPengeluaranBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        tambahPengeluaranBtnLayout.setVerticalGroup(
            tambahPengeluaranBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        addPengeluaranPanel.add(tambahPengeluaranBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 620, -1, 40));

        pilihBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pilihBtn.setOpaque(false);
        pilihBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pilihBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pilihBtnLayout = new javax.swing.GroupLayout(pilihBtn);
        pilihBtn.setLayout(pilihBtnLayout);
        pilihBtnLayout.setHorizontalGroup(
            pilihBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pilihBtnLayout.setVerticalGroup(
            pilihBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        addPengeluaranPanel.add(pilihBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 560, -1, 30));

        idPengguna.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        idPengguna.setForeground(new java.awt.Color(56, 57, 71));
        idPengguna.setBorder(null);
        idPengguna.setOpaque(false);
        addPengeluaranPanel.add(idPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 230, 30));

        nominal.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        nominal.setForeground(new java.awt.Color(56, 57, 71));
        nominal.setBorder(null);
        nominal.setOpaque(false);
        addPengeluaranPanel.add(nominal, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 490, 180, 30));

        namaPengeluaran.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        namaPengeluaran.setForeground(new java.awt.Color(56, 57, 71));
        namaPengeluaran.setBorder(null);
        namaPengeluaran.setOpaque(false);
        addPengeluaranPanel.add(namaPengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 490, 180, 30));

        idPengeluaran.setEditable(false);
        idPengeluaran.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        idPengeluaran.setForeground(new java.awt.Color(56, 57, 71));
        idPengeluaran.setBorder(null);
        idPengeluaran.setOpaque(false);
        addPengeluaranPanel.add(idPengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 180, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah_data_pengeluaran_panel.png"))); // NOI18N
        addPengeluaranPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(addPengeluaranPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 750, 700));

        editPengeluaranPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backBtnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backBtnEdit.setOpaque(false);
        backBtnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnEditMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backBtnEditLayout = new javax.swing.GroupLayout(backBtnEdit);
        backBtnEdit.setLayout(backBtnEditLayout);
        backBtnEditLayout.setHorizontalGroup(
            backBtnEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        backBtnEditLayout.setVerticalGroup(
            backBtnEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        editPengeluaranPanel.add(backBtnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 20, 20));

        ubahPengeluaranBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ubahPengeluaranBtn.setOpaque(false);
        ubahPengeluaranBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ubahPengeluaranBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ubahPengeluaranBtnLayout = new javax.swing.GroupLayout(ubahPengeluaranBtn);
        ubahPengeluaranBtn.setLayout(ubahPengeluaranBtnLayout);
        ubahPengeluaranBtnLayout.setHorizontalGroup(
            ubahPengeluaranBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        ubahPengeluaranBtnLayout.setVerticalGroup(
            ubahPengeluaranBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        editPengeluaranPanel.add(ubahPengeluaranBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 620, -1, 40));

        pilihBtnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pilihBtnEdit.setOpaque(false);
        pilihBtnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pilihBtnEditMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pilihBtnEditLayout = new javax.swing.GroupLayout(pilihBtnEdit);
        pilihBtnEdit.setLayout(pilihBtnEditLayout);
        pilihBtnEditLayout.setHorizontalGroup(
            pilihBtnEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pilihBtnEditLayout.setVerticalGroup(
            pilihBtnEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        editPengeluaranPanel.add(pilihBtnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 560, -1, 30));

        idPenggunaEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        idPenggunaEdit.setForeground(new java.awt.Color(56, 57, 71));
        idPenggunaEdit.setBorder(null);
        idPenggunaEdit.setOpaque(false);
        editPengeluaranPanel.add(idPenggunaEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 230, 30));

        nominalEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        nominalEdit.setForeground(new java.awt.Color(56, 57, 71));
        nominalEdit.setBorder(null);
        nominalEdit.setOpaque(false);
        editPengeluaranPanel.add(nominalEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 490, 180, 30));

        namaPengeluaranEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        namaPengeluaranEdit.setForeground(new java.awt.Color(56, 57, 71));
        namaPengeluaranEdit.setBorder(null);
        namaPengeluaranEdit.setOpaque(false);
        editPengeluaranPanel.add(namaPengeluaranEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 490, 180, 30));

        idPengeluaranEdit.setEditable(false);
        idPengeluaranEdit.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        idPengeluaranEdit.setForeground(new java.awt.Color(56, 57, 71));
        idPengeluaranEdit.setBorder(null);
        idPengeluaranEdit.setOpaque(false);
        editPengeluaranPanel.add(idPengeluaranEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 180, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ubah_data_pengeluaran_panel.png"))); // NOI18N
        editPengeluaranPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, -1));

        jPanel1.add(editPengeluaranPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 750, 700));

        pengeluaranHariIni.setFont(new java.awt.Font("Quicksand", 1, 28)); // NOI18N
        pengeluaranHariIni.setForeground(new java.awt.Color(255, 255, 255));
        pengeluaranHariIni.setText("Rp. 0,00");
        jPanel1.add(pengeluaranHariIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 310, 50));

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
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 200, 80, 30));

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
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 200, 60, 30));

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
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 200, 70, 30));

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

        jPanel1.add(transBeliBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 442, -1, 40));

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
        jPanel1.add(searchItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 204, 180, 20));

        tabelPengeluaran.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        tabelPengeluaran.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPengeluaran.setSelectionBackground(new java.awt.Color(229, 229, 229));
        tabelPengeluaran.setSelectionForeground(new java.awt.Color(30, 30, 46));
        tabelPengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPengeluaranMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPengeluaran);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 777, 10, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dashboard_pengeluaran_page.png"))); // NOI18N
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
                String query = "DELETE FROM pengeluaran WHERE id_pengeluaran='" + id_pengeluaran + "'";
                Database.queryExecute(query);
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                showDataTable();
                queryHitungPengeluaranHariIni();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_deleteBtnMouseClicked

    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        addPengeluaranPanel.setVisible(true);
        idPengeluaran.setText("PGN" + Attr.getDateNow("yyyyMMdd") + Attr.generateRandomNumber(9999));
        tabelPengeluaran.setVisible(false);
    }//GEN-LAST:event_addBtnMouseClicked

    private void tabelPengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPengeluaranMouseClicked
        int baris = tabelPengeluaran.rowAtPoint(evt.getPoint());
        id_pengeluaran = tabelPengeluaran.getValueAt(baris, 0).toString();
    }//GEN-LAST:event_tabelPengeluaranMouseClicked

    private void barangBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barangBtnMouseClicked
        this.setVisible(false);
        new Barang().setVisible(true);
    }//GEN-LAST:event_barangBtnMouseClicked

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
            Logger.getLogger(Pengeluaran.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Pengeluaran.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pengeluaranBtnMouseClicked

    private void tabelPenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPenggunaMouseClicked
        int baris = tabelPengguna.rowAtPoint(evt.getPoint());
        id_pengguna = tabelPengguna.getValueAt(baris, 0).toString();
    }//GEN-LAST:event_tabelPenggunaMouseClicked

    private void pilihPenggunaTambahBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihPenggunaTambahBtnMouseClicked
        pilihPenggunaTambahPanel.setVisible(false);
        tabelPengguna.setVisible(false);
        addPengeluaranPanel.setVisible(true);
        idPengguna.setText(id_pengguna);
    }//GEN-LAST:event_pilihPenggunaTambahBtnMouseClicked

    private void pilihPenggunaUbahBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihPenggunaUbahBtnMouseClicked
        pilihPenggunaUbahPanel.setVisible(false);
        tabelPenggunaEdit.setVisible(false);
        addPengeluaranPanel.setVisible(true);
        idPenggunaEdit.setText(id_pengguna);
    }//GEN-LAST:event_pilihPenggunaUbahBtnMouseClicked

    private void tabelPenggunaEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPenggunaEditMouseClicked
        int baris = tabelPengguna.rowAtPoint(evt.getPoint());
        id_pengguna = tabelPengguna.getValueAt(baris, 0).toString();
    }//GEN-LAST:event_tabelPenggunaEditMouseClicked

    private void editBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseClicked
        if (id_pengeluaran == null) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih data pada baris tabel untuk memulai mengubah data!");
        } else {
            editPengeluaranPanel.setVisible(true);
            tabelPengeluaran.setVisible(false);
            try {
                showDataEditPanel();
            } catch (ParseException ex) {
                Logger.getLogger(Pengeluaran.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_editBtnMouseClicked

    private void pilihBtnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihBtnEditMouseClicked
        showPilihPenggunaEditPanel();
    }//GEN-LAST:event_pilihBtnEditMouseClicked

    private void ubahPengeluaranBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ubahPengeluaranBtnMouseClicked
        try {
            String query = "UPDATE pengeluaran SET nama_pengeluaran='" + namaPengeluaranEdit.getText() + "', nominal='" + nominalEdit.getText() + "',"
                    + " id_user='" + idPenggunaEdit.getText() + "', tanggal='" + dateFormat.format(tanggalEdit.getDate()) + "' WHERE id_pengeluaran='" + idPengeluaranEdit.getText() + "'";
            Database.queryExecute(query);
            editPengeluaranPanel.setVisible(false);
            tabelPengeluaran.setVisible(true);
            showDataTable();
            queryHitungPengeluaranHariIni();
            idPengeluaranEdit.setText(null);
            namaPengeluaranEdit.setText(null);
            nominalEdit.setText("0");
            idPenggunaEdit.setText(null);
            tanggalEdit.setDate(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada saat mengubah data pengeluaran. Pesan error : " + e.getMessage());
        }
    }//GEN-LAST:event_ubahPengeluaranBtnMouseClicked

    private void pilihBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihBtnMouseClicked
        showPilihPenggunaPanel();
    }//GEN-LAST:event_pilihBtnMouseClicked

    private void tambahPengeluaranBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahPengeluaranBtnMouseClicked
        try {
            String query = "INSERT INTO pengeluaran VALUES('" + idPengeluaran.getText() + "', '" + namaPengeluaran.getText() + "', " + nominal.getText() + ","
                    + "'" + dateFormat.format(tanggal.getDate()) + "', '" + idPengguna.getText() + "')";
            Database.queryExecute(query);
            addPengeluaranPanel.setVisible(false);
            tabelPengeluaran.setVisible(true);
            idPengeluaran.setText(null);
            namaPengeluaran.setText(null);
            nominal.setText("0");
            idPengguna.setText(null);
            tanggal.setDate(null);
            showDataTable();
            queryHitungPengeluaranHariIni();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada saat menambah data pengeluaran. Pesan error : " + e.getMessage());
        }
    }//GEN-LAST:event_tambahPengeluaranBtnMouseClicked

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        addPengeluaranPanel.setVisible(false);
        tabelPengeluaran.setVisible(true);
        idPengeluaran.setText(null);
        namaPengeluaran.setText(null);
        nominal.setText("0");
        idPengguna.setText(null);
        tanggal.setDate(null);
    }//GEN-LAST:event_backBtnMouseClicked

    private void backBtnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnEditMouseClicked
        editPengeluaranPanel.setVisible(false);
        tabelPengeluaran.setVisible(true);
    }//GEN-LAST:event_backBtnEditMouseClicked

    private void transBeliBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transBeliBtnMouseClicked
        this.setVisible(false);
        try {
            new TransaksiBeli().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Pengeluaran.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_transBeliBtnMouseClicked

    public static void main(String args[]) {
        try {
            FlatArcIJTheme.setup();
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Pengeluaran().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Pengeluaran.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addBtn;
    private javax.swing.JPanel addPengeluaranPanel;
    private javax.swing.JPanel backBtn;
    private javax.swing.JPanel backBtnEdit;
    private javax.swing.JPanel barangBtn;
    private javax.swing.JPanel dashboardBtn;
    private javax.swing.JPanel deleteBtn;
    private javax.swing.JPanel editBtn;
    private javax.swing.JPanel editPengeluaranPanel;
    private javax.swing.JPanel homeBtn;
    private javax.swing.JTextField idPengeluaran;
    private javax.swing.JTextField idPengeluaranEdit;
    private javax.swing.JTextField idPengguna;
    private javax.swing.JTextField idPenggunaEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel kategoriBtn;
    private javax.swing.JPanel laporanBtn;
    private javax.swing.JTextField namaPengeluaran;
    private javax.swing.JTextField namaPengeluaranEdit;
    private javax.swing.JTextField nominal;
    private javax.swing.JTextField nominalEdit;
    private javax.swing.JPanel pemasokBtn;
    private javax.swing.JPanel pengeluaranBtn;
    private javax.swing.JLabel pengeluaranHariIni;
    private javax.swing.JPanel penggunaBtn;
    private javax.swing.JPanel pilihBtn;
    private javax.swing.JPanel pilihBtnEdit;
    private javax.swing.JPanel pilihPenggunaTambahBtn;
    private javax.swing.JPanel pilihPenggunaTambahPanel;
    private javax.swing.JPanel pilihPenggunaUbahBtn;
    private javax.swing.JPanel pilihPenggunaUbahPanel;
    private javax.swing.JTextField searchBarPilihPenggunaTambah;
    private javax.swing.JTextField searchBarPilihPenggunaUbah;
    private javax.swing.JTextField searchItem;
    private javax.swing.JTable tabelPengeluaran;
    private javax.swing.JTable tabelPengguna;
    private javax.swing.JTable tabelPenggunaEdit;
    private javax.swing.JPanel tambahPengeluaranBtn;
    private javax.swing.JPanel transBeliBtn;
    private javax.swing.JPanel transJualBtn;
    private javax.swing.JPanel ubahPengeluaranBtn;
    // End of variables declaration//GEN-END:variables
}
