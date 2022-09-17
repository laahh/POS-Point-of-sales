package utils;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date; //untuk memperoleh current time & date
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class Attr {

    public static Color black1 = new Color(30, 30, 46);
    public static Color black2 = new Color(48, 45, 65);
    public static Color blue = new Color(88, 0, 232);
    public static Color white = new Color(217, 224, 238);

    public static Font quicksandPlain(int fontSize) {
        return new Font("Quicksand", Font.PLAIN, fontSize);
    }

    public static Font quicksandMedium(int fontSize) {
        return new Font("Quicksand Medium", Font.PLAIN, fontSize);
    }

    public static Font quicksandBold(int fontSize) {
        return new Font("Quicksand", Font.BOLD, fontSize);
    }

    public static String getDateNow(String dateFormat) {
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        Date now = new Date();
        return formatter.format(now);
    }

    public static String formatDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public static int generateRandomNumber(int num) {
        Random random = new Random();
        return random.nextInt(num);
    }

    public static String kursIndo(String nominal) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(Double.parseDouble(nominal));
    }

    public static DocumentListener jumlahDL(JTextField harga, JTextField jumlah, JTextField totalHarga) {
        DocumentListener dl = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                kalikanHarga();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                kalikanHarga();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                kalikanHarga();
            }

            void kalikanHarga() {
                int th;
                if (jumlah.getText().equals("")) {
                    th = 0 * Integer.parseInt(harga.getText());
                } else {
                    th = Integer.parseInt(jumlah.getText()) * Integer.parseInt(harga.getText());
                }
                totalHarga.setText(Integer.toString(th));
            }
        };

        return dl;
    }

    public static DocumentListener DL(JTextField harga, JTextField jumlah, JTextField totalHarga) {
        DocumentListener dl = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                kalikanHarga();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                kalikanHarga();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                kalikanHarga();
            }

            void kalikanHarga() {
                int th;
                if (jumlah.getText().equals("")) {
                    th = 0 * Integer.parseInt(harga.getText());
                } else {
                    th = Integer.parseInt(jumlah.getText()) * Integer.parseInt(harga.getText());
                }
                totalHarga.setText(Integer.toString(th));
            }
        };

        return dl;
    }

    public static DocumentListener searchUsersDL(JTable table, JTextField textField) {
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

                model.addColumn("Id Pengguna");
                model.addColumn("Nama Pengguna");

                try {
                    String query = "SELECT * FROM users WHERE CONCAT(id_user, nama_user) LIKE '%" + textField.getText() + "%'";
                    ResultSet result = Database.queryResultSet(query);
                    while (result.next()) {
                        model.addRow(new Object[]{result.getString("id_user"), result.getString("nama_user")});
                    }
                    table.setModel(model);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }
        };

        return dl;
    }
    
    public static void showDataAnalisa(String func, JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("ID Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Terjual");
        try {
            int no = 1;
            String query = "SELECT dt.id_barang id_barang, b.nama_barang nama_barang, SUM(dt.qty) barang_laku FROM transaksi t JOIN detail_transaksi dt ON t.id_transaksi=dt.id_transaksi JOIN barang b ON dt.id_barang=b.id_barang WHERE " + func + "(t.tanggal)=" + func + "(CURDATE()) GROUP BY dt.id_barang ORDER BY barang_laku DESC";
            ResultSet result = Database.queryResultSet(query);
            while(result.next()) {
                model.addRow(new Object[] {no++, result.getString("id_barang"), result.getString("nama_barang"), result.getString("barang_laku")});
            }
            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
    }
    
    public static int hitungOmset(JLabel tf, String func) {
        int omset = 0;
        try {
            String query = "SELECT SUM(total_harga) total_harga FROM transaksi WHERE " + func + "(tanggal)=" + func + "(CURDATE())";
            ResultSet result = Database.queryResultSet(query);
            if (result.next()) {
                tf.setText(Attr.kursIndo(result.getString(1)));
                omset = result.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return omset;
    }
}
