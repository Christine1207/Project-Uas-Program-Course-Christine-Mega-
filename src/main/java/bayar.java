import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class bayar extends JFrame {
    JLabel label;

    public bayar (){
        setTitle("Jadwal & Harga Course");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        String column[]={"PROGRAM", "DAYS", "TIME", "CATEGORY", "PRICE"};
        String row[][]={
                {"Regular A", "Senin & Rabu",  "08.00 - 10.00", "Weekdays", "150k"},
                {"Regular B", "Selasa & Kamis", "13.00 - 15.00", "Weekdays", "150k"},
                {"Regular C", "Rabu & Jum'at", "15.00 - 17.00", "Weekdays", "150k"},
                {"Private", "Sabtu & Minggu", "17.00 - 19.00", "Weekend", "175k"},};
        JTable table = new JTable(row,column);
        table.setFillsViewportHeight(true);
        table.setEnabled(false);
        setLayout(new GridLayout(4, 1));
        JScrollPane scroll = new JScrollPane(table);
        add(scroll);

        add(new datapilihan());
        add(new button());

        setVisible(true);
    }
}


class datapilihan extends JPanel {
    JLabel label,harga;
    JComboBox comboBox;

    static JComboBox<String> cmbbayar = new JComboBox<>(new String[] {"Cash", "Ovo", "Debit", "Gopay"});

    public datapilihan(){
        setLayout(new GridLayout(3, 1));

        harga = new JLabel("Rp.");
        add(new JLabel("Program yang dipilih: "));
        String data[]={"Regular A", "Regular B", "Regular C", "Private"};
        comboBox =new JComboBox(data);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboBox.getSelectedItem().equals("Regular A")){
                    harga.setText("Rp. 150.000");
                }
                if(comboBox.getSelectedItem().equals("Regular B")){
                    harga.setText("Rp. 150.000");
                }
                if(comboBox.getSelectedItem().equals("Regular C")){
                    harga.setText("Rp. 150.000");
                }
                if(comboBox.getSelectedItem().equals("Private A")){
                    harga.setText("Rp. 175.000");
                }
            }
        });

        add(comboBox);

        add(new JLabel("Total Harga: "));
        add(harga);
        setVisible(true);

        add(new JLabel("Pilih Metode Pembayaran"));
        add(cmbbayar);

        Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Pembayaran");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD));

        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 5, 10,5), titledBorder));
    }
}

class button extends  JPanel {
    JButton btnPay = new JButton("Pay");
    JButton btnCancel = new JButton("Cancel");

    public button() {
        setLayout(new GridLayout(1, 2));
        add(btnCancel);
        add(btnPay);

        btnCancel.setBackground(Color.PINK);
        btnPay.setBackground(Color.PINK);


        btnPay.addActionListener(new ButtonPayListener());
        btnCancel.addActionListener(new ButtonCancelListener());

    }

    class ButtonPayListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,"Terimakasih. Anda Telah Membayar");
        }
    }

    class ButtonCancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new daftar();
        }
    }
}