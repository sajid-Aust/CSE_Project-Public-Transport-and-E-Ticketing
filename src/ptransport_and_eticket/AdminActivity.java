package ptransport_and_eticket;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminActivity extends JFrame{
    private Container c;
    private JLabel lb1, lb2, lb3, lb4, lb5;
    private JTextField tf1;
    private JPasswordField pf;
    private JPanel pn1, pn2, pn3;
    private JButton btn1, btn2, btn3, btn4;
    private Font f1, f2, f3, f4;
    String admin="<html><u>Update Admin Info</u></html>";
    
    AdminActivity()
    {
        setBounds(200, 50, 1600, 900);
        setTitle("Public Transport & E-Ticketing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initComponents();
    }
    
    void initComponents()
    {
        
        f1 = new Font("Arial", Font.BOLD, 44);
        f2 = new Font("Arial", Font.BOLD, 24);
        f3 = new Font("Arial", Font.BOLD, 19);
        f4 = new Font("Arial", Font.BOLD, 26);
        
        Color clr = new Color(44, 62, 80);
        
        JPanel pn1 = new JPanel();
        pn1.setBounds(0,0, 1600, 900);
        pn1.setBackground(clr);
        pn1.setLayout(null);
       
        
        JPanel pn2 = new JPanel();
        pn2.setBounds(0, 0, 1600, 150);
        pn2.setBackground(Color.ORANGE);
        pn2.setLayout(null);
        
        JLabel lb1 = new JLabel("Admin Activity");
        lb1.setFont(f1);
        lb1.setBounds(650, 30, 500, 100);
        pn2.add(lb1);
        
        btn1 = new JButton("Booking Info");
        btn1.setFont(f2);
        btn1.setBounds(660, 330, 290, 60);
        btn1.setBackground(Color.darkGray);
        btn1.setForeground(Color.white);
        pn1.add(btn1);
        
        btn1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                Booking_status2 bks = new Booking_status2();
                bks.setVisible(true);
            }
        
        
        });
        
        btn2 = new JButton("Update Info");
        btn2.setFont(f2);
        btn2.setBounds(660, 500, 290, 60);
        btn2.setBackground(Color.darkGray);
        btn2.setForeground(Color.white);
        pn1.add(btn2);
        
        btn2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                dispose();
                Update_Route update = new Update_Route();
                update.setVisible(true);
                
            }
        
        });
        
        
        /*btn3 = new JButton("Add Info");
        btn3.setFont(f2);
        btn3.setBounds(660, 550, 290, 60);
        btn3.setBackground(Color.darkGray);
        btn3.setForeground(Color.white);
        pn1.add(btn3);*/
        
        
        
        btn4 = new JButton("Back");
        btn4.setBounds(45, 765, 130, 50);
        btn4.setBackground(Color.LIGHT_GRAY);
        btn4.setForeground(Color.black);
        btn4.setFont(f2);
        pn1.add(btn4);
        
        btn4.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                AdminLogin adm = new AdminLogin();
                adm.setVisible(true);
            }
        
        });
        
        
        
        lb5 = new JLabel();
        lb5.setText(admin);
        lb5.setFont(f4);
        lb5.setBounds(1320, 160, 270, 50);
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
        
        
        
        pn1.add(pn2);
        this.add(pn1);
        
    }
    public static void main(String [] args)
    {
        AdminActivity AdminLog = new AdminActivity();
        AdminLog.setVisible(true);
    }
    
}
