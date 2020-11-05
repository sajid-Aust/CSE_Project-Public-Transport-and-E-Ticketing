package ptransport_and_eticket;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PTransport_and_Eticket extends JFrame {
    
    private Container c;
    private JLabel background, lb1, lb2;
    private JButton btn1, btn2;
    private Font f1, f2;

    PTransport_and_Eticket()
    {
        setBounds(200, 50, 1600, 900);
        setTitle("Public Transport & E-Ticketing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initComponents();
    }
    
    public void initComponents()
    {
        c = this.getContentPane();
        c.setLayout(null);
        
        f1 = new Font("Arial", Font.BOLD, 44);
        f2 = new Font("Arial", Font.BOLD, 24);
        
        ImageIcon img = new ImageIcon("E:\\image\\trans1.jpg");
        background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 1600, 900);
        
        Color clr1 = new Color(255, 155, 5);
        Color clr2 = new Color(255, 222, 5);
        
        lb1 = new JLabel("Public Transport and E-Ticketing");
        lb1.setForeground(clr1);
        lb1.setBounds(430, 220, 800, 250);
        lb1.setFont(f1);
        background.add(lb1);
        
        lb2 = new JLabel("System");
        lb2.setForeground(clr1);
        lb2.setBounds(680, 290, 800, 250);
        lb2.setFont(f1);
        background.add(lb2);
        
        btn1 = new JButton("Next");
        btn1.setBounds(1410, 765, 130, 50);
        btn1.setBackground(Color.LIGHT_GRAY);
        btn1.setForeground(Color.black);
        btn1.setFont(f2);
        background.add(btn1);
        
        btn1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                dispose();
                userLog user = new userLog();
                user.setVisible(true);
                
            }
        
        });
        
        btn2 = new JButton("About");
        btn2.setBounds(45, 765, 130, 50);
        btn2.setBackground(Color.LIGHT_GRAY);
        btn2.setForeground(Color.black);
        btn2.setFont(f2);
        background.add(btn2);
        
        c.add(background);
        
    }
    
   public static void main(String[] args) {
        
        PTransport_and_Eticket ptrans = new PTransport_and_Eticket();
        ptrans.setVisible(true);
        
    }
    
}
