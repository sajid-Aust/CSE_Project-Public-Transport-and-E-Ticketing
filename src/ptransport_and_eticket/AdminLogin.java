
package ptransport_and_eticket;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class AdminLogin extends JFrame {
     private Container c;
    private JLabel lb1, lb2, lb3, lb4, lb5;
    private JTextField tf1;
    private JPasswordField pf;
    private JPanel pn1, pn2, pn3;
    private JButton btn1, btn2;
    private Font f1, f2, f3, f4;
    
    AdminLogin()
    {
        setBounds(200, 50, 1600, 900);
        setTitle("Public Transport & E-Ticketing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initComponents();
    }
    
    void initComponents()
    {
        //c = this.getContentPane();
        //c.setLayout(null);
        
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
        
        JLabel lb1 = new JLabel("Admin Login");
        lb1.setFont(f1);
        lb1.setBounds(650, 30, 500, 100);
        pn2.add(lb1);
        
        JLabel lb2 = new JLabel("Admin name");
        lb2.setFont(f2);
        lb2.setBounds(500, 330, 200, 100);
        lb2.setForeground(Color.lightGray);
        pn1.add(lb2);
        
        JTextField tf1 = new JTextField();
        tf1.setBounds(700, 360, 300, 50);
        tf1.setFont(f2);
        tf1.setBackground(Color.lightGray);
        pn1.add(tf1);
        
        JLabel lb3 = new JLabel("Password");
        lb3.setFont(f2);
        lb3.setBounds(500, 420, 200, 100);
        lb3.setForeground(Color.lightGray);
        pn1.add(lb3);
        
        JPasswordField pf = new JPasswordField();
        pf.setBounds(700, 450, 300, 50);
        pf.setFont(f2);
        pf.setBackground(Color.lightGray);
        pn1.add(pf);
        
        btn1 = new JButton("Log In");
        btn1.setFont(f2);
        btn1.setBounds(700, 570, 140, 60);
        btn1.setBackground(Color.darkGray);
        btn1.setForeground(Color.white);
        pn1.add(btn1);
        
        btn1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(tf1.getText().isEmpty() || pf.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Insert admin name & password!!");
                }
                    
                
                else
                {
                    
                    try{
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                        String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                        Connection con = DriverManager.getConnection(url);
                    
                        String username=null, pass=null;
                        String sql = "Select * from admin";
                        
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        
                        while(rs.next()){
                            username = rs.getString("AdminName");
                            pass = rs.getString("AdminPassword");
                        }
                        
                        if(username.equals(tf1.getText()) && pass.equals(pf.getText())){
                            JOptionPane.showMessageDialog(null, "Logged in successfully");
                            
                            dispose();
                            AdminActivity admt = new AdminActivity();
                            admt.setVisible(true);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Wrong combination of name & password");
                        }
                    }
                    catch(Exception e){
                        
                    }
                }
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
                PTransport_and_Eticket home = new PTransport_and_Eticket();
                home.setVisible(true);
                
            }
        
        });
        
        
        pn1.add(pn2);
        this.add(pn1);
        
    }
    
    public static void main(String [] args)
    {
        AdminLogin AdminLog = new AdminLogin();
        AdminLog.setVisible(true);
    }

}
