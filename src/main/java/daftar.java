import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class daftar extends JFrame{

    public daftar(){
        setTitle("English & Chinese Course");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new DaftarEditPanel());
        add(new ButtonDaftarPanel());
        add(new DataDaftarPanel());

        setVisible(true);
    }
}

class DataDaftarPanel extends JPanel{
    static JTable table = new JTable();

    public DataDaftarPanel(){
        database db = new database();
        db.show();

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setEnabled(false);

        setLayout(new GridLayout(1, 1));
        setBorder(new EmptyBorder(10, 10, 5, 10));

        add(scrollPane);
    }
}

class DaftarEditPanel extends JPanel {
    static JTextField txtnama = new JTextField();
    static JTextField txtalamat = new JTextField();
    static JTextField txtno_telepon = new JTextField();
    static JTextField txtemail = new JTextField();

    static JComboBox<String> cmbjenis_kelamin = new JComboBox<>(new String[] {"Laki-Laki", "Perempuan"});
    static JComboBox<String> cmbpendidikan = new JComboBox<>(new String[]{
            "SD",
            "SMP",
            "SMA"
    });
    static JComboBox<String> cmbpilihan = new JComboBox<String>(new String[] {"Inggris", "Mandarin"});

    public DaftarEditPanel(){
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Nama"));
        add(txtnama);

        add(new JLabel("Alamat"));
        add(txtalamat);

        add(new JLabel("No Telepon"));
        add(txtno_telepon);

        add(new JLabel("Email"));
        add(txtemail);

        add(new JLabel("Jenis Kelamin"));
        add(cmbjenis_kelamin);

        add(new JLabel("Pendidikan"));
        add(cmbpendidikan);

        add(new JLabel("Pilihan"));
        add(cmbpilihan);

        // border
        Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Daftar");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));

        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 10,10), titledBorder));
    }
}

class ButtonDaftarPanel extends  JPanel {
    JButton btnAdd = new JButton("Add");
    JButton btnDelete = new JButton("Delete");
    JButton btnSave = new JButton("Save");

    public ButtonDaftarPanel (){
        final database db = new database();
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.insert();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.delete();
            }
        });

        setLayout(new GridLayout(1, 3));
        add(btnAdd);
        add(btnDelete);
        add(btnSave);

        btnAdd.setBackground(Color.PINK);
        btnDelete.setBackground(Color.PINK);
        btnSave.setBackground(Color.PINK);

        btnSave.addActionListener(new ButtonSaveListener());
    }

    class ButtonSaveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new bayar();
        }
    }
}

