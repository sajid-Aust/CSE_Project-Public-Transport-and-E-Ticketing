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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class userLog extends JFrame {
    
    private Container c;
    private JLabel lb1, lb2, lb3, lb4, lb5;
    private JTextField tf1;
    private JPasswordField pf;
    private JPanel pn1, pn2, pn3;
    private JButton btn1, btn2;
    private Font f1, f2, f3, f4;
    String text = "<html><u>Are you new? Register now</u></html>";
    String admin = "<html><u>Admin</u></html>", pass, uname;
    int pid;
    
    userLog()
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
        
        JLabel lb1 = new JLabel("Login Form");
        lb1.setFont(f1);
        lb1.setBounds(650, 30, 500, 100);
        pn2.add(lb1);
        
        JLabel lb2 = new JLabel("Username");
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
        
        btn1 = new JButton("Proceed");
        btn1.setFont(f2);
        btn1.setBounds(700, 570, 140, 60);
        btn1.setBackground(Color.darkGray);
        btn1.setForeground(Color.white);
        pn1.add(btn1);
        
        btn1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(tf1.getText().isEmpty() || pf.getText().isEmpty()){
                        
                        JOptionPane.showMessageDialog(null, "Login failed !!");
                    }
                
                else{
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    
                    String username=tf1.getText();
                    
                    String sql = "SELECT PassengerID,Username,Password FROM Passenger WHERE Username='"+username+"'";
                    
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                   while(rs.next()){
                        
                        pass=rs.getString("Password");
                        uname=rs.getString("Username");
                        pid = rs.getInt("PassengerID");
                   }
    
                    if(pass.equals(new String(pf.getPassword()))){
                        JOptionPane.showMessageDialog(null, "Log in Successful");
                        
                        try{
                    BufferedWriter bfr = new BufferedWriter(new FileWriter("Passenger/pid.text"));
              
                    bfr.write(Integer.toString(pid));
                    bfr.close();
                    
                    
                   }
                   catch(Exception e){
                       e.printStackTrace();
                   }
                        
                        dispose();
                        Route_Select rt_select = new Route_Select();
                        rt_select.setVisible(true);
                    
                        con.close();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Wrong combination of username & password!!");
                    }
                    
                    tf1.setText("");
                    pf.setText("");
                    
                    }
                    catch(Exception e)
                    {
                       JOptionPane.showMessageDialog(null, "Login failed");
                    }
                }
            }
            
        
        });
        
        JLabel lb4 = new JLabel();
        lb4.setText(text);
        lb4.setFont(f3);
        lb4.setBounds(650, 600, 400, 100);
        lb4.setForeground(Color.lightGray);
        pn1.add(lb4);
        
        lb4.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent me) {
                
            }

            @Override
            public void mousePressed(MouseEvent me) {
                
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                dispose();
                RegForm reg = new RegForm();
                reg.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                
            }

            @Override
            public void mouseExited(MouseEvent me) {
                
            }
        
        });
        
        JLabel lb5 = new JLabel();
        lb5.setText(admin);
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
                PTransport_and_Eticket home = new PTransport_and_Eticket();
                home.setVisible(true);
                
            }
        
        });
        
        
        pn1.add(pn2);
        this.add(pn1);
        
    }
    
   public static void main(String [] args)
    {
        userLog userLog = new userLog();
        userLog.setVisible(true);
    }

    
}
