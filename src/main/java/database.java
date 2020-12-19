import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class database {
    private Connection db;
    static DefaultTableModel dtm;

    public database() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/employee";
            String user = "root";
            String pass = "";

            db = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show() {
        DaftarEditPanel.cmbjenis_kelamin.addItem("");
        DaftarEditPanel.cmbpendidikan.addItem("");
        DaftarEditPanel.cmbpilihan.addItem("");

        try {
            Object[] column = {"Nama", "Alamat", "No Telepon", "E-mail", "Jenis Kelamin", "Pendidikan", "Pilihan"};
            dtm = new DefaultTableModel(null, column);
            DataDaftarPanel.table.setModel(dtm);
            try {
                String sql = "SELECT * FROM daftar";
                Statement st = db.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    Object[] row = {
                            rs.getString("Nama"),
                            rs.getString("Alamat"),
                            rs.getInt("No_Telepon"),
                            rs.getString("EMail"),
                            rs.getString("Jenis_Kelamin"),
                            rs.getString("Pendidikan"),
                            rs.getString("Pilihan"),
                    };
                    dtm.addRow(row);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insert() {
        String nama = DaftarEditPanel.txtnama.getText();
        String alamat = DaftarEditPanel.txtalamat.getText();
        int no_telepon = Integer.parseInt(DaftarEditPanel.txtno_telepon.getText());
        String email = DaftarEditPanel.txtemail.getText();
        String jenis_kelamin = DaftarEditPanel.cmbjenis_kelamin.getSelectedItem().toString();
        String pendidikan = DaftarEditPanel.cmbpendidikan.getSelectedItem().toString();
        String pilihan = DaftarEditPanel.cmbpilihan.getSelectedItem().toString();

        try {
            String sql = "INSERT INTO daftar VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = db.prepareStatement(sql);
            ps.setString(1, nama);
            ps.setString(2, alamat);
            ps.setInt(3, no_telepon);
            ps.setString(4, email);
            ps.setString(5, jenis_kelamin);
            ps.setString(6, pendidikan);
            ps.setString(7, pilihan);
            ps.execute();
            show();
            JOptionPane.showMessageDialog(null, "Success");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed");
        }
    }

    public void delete(){
        try{
            String sql = "DELETE FROM daftar WHERE nama = '"+ DaftarEditPanel.txtnama.getText()+"'";
            PreparedStatement stmt = db.prepareStatement(sql);

            stmt.execute();
            show();
            JOptionPane.showMessageDialog(null,"Delete Success");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Delete Fail");
        }
    }
}
