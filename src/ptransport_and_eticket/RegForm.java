
package ptransport_and_eticket;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegForm extends JFrame {
    
    private JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7;
    private JTextField tf1, tf2, tf3, tf4, tf5;
    private JPasswordField pf;
    private JPanel pn1, pn2, pn3;
    private JButton btn1, btn2;
    private Font f1, f2, f3, f4;
    String text = "<html><u>Are you new? Register now</u></html>";
    String admin = "<html><u>Admin</u></html>", st;
    int x=0;
    
    RegForm()
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
        
        f1 = new Font("Arial", Font.BOLD, 40);
        f2 = new Font("Arial", Font.BOLD, 24);
        f3 = new Font("Arial", Font.BOLD, 19);
        f4 = new Font("Arial", Font.BOLD, 26);
        
        Color clr = new Color(44, 62, 80);
        
        JPanel pn1 = new JPanel();
        pn1.setBounds(0,0, 1600, 900);
        pn1.setBackground(clr);
        pn1.setLayout(null);
       
        
        JPanel pn2 = new JPanel();
        pn2.setBounds(0, 0, 1600, 100);
        pn2.setBackground(Color.ORANGE);
        pn2.setLayout(null);
        
        JLabel lb1 = new JLabel("Registration Form");
        lb1.setFont(f1);
        lb1.setBounds(650, 10, 500, 80);
        pn2.add(lb1);
        
        JLabel lb2 = new JLabel("Username");
        lb2.setFont(f2);
        lb2.setBounds(500, 130+100, 200, 100);
        lb2.setForeground(Color.lightGray);
        pn1.add(lb2);
        
        JTextField tf1 = new JTextField();
        tf1.setBounds(700, 160+90, 300, 50);
        tf1.setFont(f2);
        tf1.setBackground(Color.lightGray);
        pn1.add(tf1);
        
        JLabel lb3 = new JLabel("Password");
        lb3.setFont(f2);
        lb3.setBounds(500, 220+100, 200, 100);
        lb3.setForeground(Color.lightGray);
        pn1.add(lb3);
        
        JPasswordField pf = new JPasswordField();
        pf.setBounds(700, 250+90, 300, 50);
        pf.setFont(f2);
        pf.setBackground(Color.lightGray);
        pn1.add(pf);
        
        JLabel lb4 = new JLabel("Age");
        lb4.setFont(f2);
        lb4.setBounds(500, 310+100, 200, 100);
        lb4.setForeground(Color.lightGray);
        pn1.add(lb4);
        
        tf2 = new JTextField();
        tf2.setBounds(700, 340+90, 300, 50);
        tf2.setFont(f2);
        tf2.setBackground(Color.lightGray);
        pn1.add(tf2);
        
        JLabel lb5 = new JLabel("Contact No.");
        lb5.setFont(f2);
        lb5.setBounds(500, 400+100, 200, 100);
        lb5.setForeground(Color.lightGray);
        pn1.add(lb5);
        
        tf3 = new JTextField();
        tf3.setBounds(700, 430+90, 300, 50);
        tf3.setFont(f2);
        tf3.setBackground(Color.lightGray);
        pn1.add(tf3);
        
        btn1 = new JButton("Register");
        btn1.setFont(f2);
        btn1.setBounds(700, 600+40, 140, 60);
        btn1.setBackground(Color.darkGray);
        btn1.setForeground(Color.white);
        pn1.add(btn1);
        
        btn1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(tf1.getText().isEmpty() || pf.getText().isEmpty() || tf2.getText().isEmpty()
                            || tf3.getText().isEmpty()){
                        
                        JOptionPane.showMessageDialog(null, "Registration failed !!");
                    }
                else if(pf.getPassword().length<8){
                    JOptionPane.showMessageDialog(null, "Password required minimum 8 characters");
                }
                else if(tf3.getText().length()!=11){
                    JOptionPane.showMessageDialog(null, "Invalid Contact No");
                }
                else{
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    
                    String sql = "Insert Into Passenger Values (?,?,?,?)";
                    
                    PreparedStatement pst = con.prepareStatement(sql);
                    
                    
                    pst.setString(1,tf1.getText());
                    pst.setString(2,new String(pf.getPassword()));
                    pst.setString(3,tf2.getText());
                    pst.setString(4,tf3.getText());
                    
                    st=tf3.getText();
                    
                    System.out.println(st.charAt(0));
                    System.out.println(st.charAt(1));
                    
                    pst.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Successfully registered");
                    
                    tf1.setText("");
                    pf.setText("");
                    tf2.setText("");
                    tf3.setText("");
                    
                    /*dispose();
                    userLogin userLog = new userLogin();
                    userLog.setVisible(true);
                    
                    con.close();*/
                    }
                    catch(Exception e)
                    {
                        if(st.charAt(0)!='0' || st.charAt(1)!='1'){
                            JOptionPane.showMessageDialog(null, "Invalid Contact No!!");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Username already exists!!");
                        }
                        
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
                userLog userLog = new userLog();
                userLog.setVisible(true);
                
            }
        
        });
        
        
        pn1.add(pn2);
        this.add(pn1);
        
    }
    
    /*public static void main(String [] args)
    {
        RegForm reg = new RegForm();
        reg.setVisible(true);
    }*/

    
}
