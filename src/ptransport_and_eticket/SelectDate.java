package ptransport_and_eticket;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SelectDate extends JFrame {
    private Container c;
    private JLabel lb1, lb2, lb3, lb4, lb5;
    private JTextField tf1, tf2;
    private JPasswordField pf;
    private JPanel pn1, pn2, pn3;
    private JButton btn1, btn2;
    private Font f1, f2, f3, f4, f5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    String text = "<html><u>Are you new? Register now</u></html>";
    String profile = "<html><u>Profile</u></html>", pass;
    
    SelectDate()
    {
        setBounds(200, 50, 1600, 900);
        setTitle("Public Transport & E-Ticketing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initComponents0();
    }
    
    
    void initComponents0()
    {
        
        
        f1 = new Font("Arial", Font.BOLD, 44);
        f2 = new Font("Arial", Font.BOLD, 24);
        f3 = new Font("Arial", Font.BOLD, 19);
        f4 = new Font("Arial", Font.BOLD, 26);
        f5 = new Font("Arial", Font.BOLD, 21);
        
        Color clr = new Color(44, 62, 80);
        
        JPanel pn1 = new JPanel();
        pn1.setBounds(0,0, 1600, 900);
        pn1.setBackground(clr);
        pn1.setLayout(null);
       
        
        JPanel pn2 = new JPanel();
        pn2.setBounds(0, 0, 1600, 150);
        pn2.setBackground(Color.ORANGE);
        pn2.setLayout(null);
        
        JLabel lb1 = new JLabel("Date Selection");
        lb1.setFont(f1);
        lb1.setBounds(650, 30, 500, 100);
        pn2.add(lb1);
        
        
        //jDateChooser2 = new com.toedter.calendar.JDateChooser();
        JLabel lb2 = new JLabel("Select your date");
        lb2.setFont(f4);
        lb2.setBounds(680, 320, 200, 100);
        lb2.setForeground(Color.lightGray);
        pn1.add(lb2);
        
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser1.setBounds(580, 400, 400, 80);
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setFont(f5);
        pn1.add(jDateChooser1);
        
        
        
        btn1 = new JButton("Proceed");
        btn1.setFont(f2);
        btn1.setBounds(710, 550, 140, 60);
        btn1.setBackground(Color.darkGray);
        btn1.setForeground(Color.white);
        pn1.add(btn1);
        
        btn1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String dte = df.format(jDateChooser1.getDate());
        
                 
               
                   try{
                    BufferedWriter bfr1 = new BufferedWriter(new FileWriter("Passenger/date.text"));
                    bfr1.write(dte);
                    bfr1.close();
                    
                   }
                   catch(Exception e){
                       e.printStackTrace();
                   }
                    
                    dispose();
                    Bus_Info bus = new Bus_Info();
                    bus.setVisible(true);
                }
                
               
        
            
        
        });
        
        JLabel lb5 = new JLabel();
        lb5.setText(profile);
        lb5.setFont(f4);
        lb5.setBounds(1460, 160, 130, 50);
        lb5.setForeground(Color.YELLOW);
        pn1.add(lb5);
        
        lb5.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent me) {
                
            }

            @Override
            public void mousePressed(MouseEvent me) {
                
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                dispose();
                AdminLogin adml = new AdminLogin();
                adml.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                
            }

            @Override
            public void mouseExited(MouseEvent me) {
                
            }
        
        });
        
        btn2 = new JButton("Back");
        btn2.setBounds(45, 765, 130, 50);
        btn2.setBackground(Color.LIGHT_GRAY);
        btn2.setForeground(Color.black);
        btn2.setFont(f2);
        pn1.add(btn2);
        
        btn2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                dispose();
                userLog userlog = new userLog();
                userlog.setVisible(true);
                
            }
        
        });
        
        
        pn1.add(pn2);
        this.add(pn1);
        
    }
    /*public static void main(String [] args)
    {
        SelectDate date_select = new SelectDate();
        date_select.setVisible(true);
    }*/

}
