package ptransport_and_eticket;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Route_Select extends JFrame{
    private Container c;
    private JLabel lb1, lb2, lb3, lb4, lb5;
    private JTextField tf1, tf2;
    private JPasswordField pf;
    private JPanel pn1, pn2, pn3;
    private JButton btn1, btn2;
    private Font f1, f2, f3, f4;
    private JComboBox cb1, cb2;
    String text = "<html><u>Are you new? Register now</u></html>";
    String profile = "<html><u>My Booking Status</u></html>", pass, source="", destination="";
    
    Route_Select()
    {
        setBounds(200, 50, 1600, 900);
        setTitle("Public Transport & E-Ticketing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        initComponents0();
        Places();
    }
    
    
    void initComponents0()
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
        
        JLabel lb1 = new JLabel("Route_Selection");
        lb1.setFont(f1);
        lb1.setBounds(650, 30, 500, 100);
        pn2.add(lb1);
        
        

        
        JLabel lb2 = new JLabel("Source");
        lb2.setFont(f2);
        lb2.setBounds(500, 330+25, 200, 100);
        lb2.setForeground(Color.lightGray);
        pn1.add(lb2);
        
        cb1 = new JComboBox();
        cb1.setBounds(700, 360+25, 300, 50);
        cb1.setFont(f2);
        cb1.setBackground(Color.lightGray);
        cb1.setEditable(true);
        cb1.addItem("");
        pn1.add(cb1);
        
        cb1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                source = cb1.getSelectedItem().toString();
            }
        
        });
        
        JLabel lb3 = new JLabel("Destination");
        lb3.setFont(f2);
        lb3.setBounds(500, 420+25, 200, 100);
        lb3.setForeground(Color.lightGray);
        pn1.add(lb3);
        
        cb2 = new JComboBox();
        cb2.setBounds(700, 450+25, 300, 50);
        cb2.setFont(f2);
        cb2.setBackground(Color.lightGray);
        cb2.setEditable(true);
        cb2.addItem("");
        pn1.add(cb2);
        
         cb2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                destination = cb2.getSelectedItem().toString();
            }
        
        });
        
        btn1 = new JButton("Proceed");
        btn1.setFont(f2);
        btn1.setBounds(700, 570+30, 140, 60);
        btn1.setBackground(Color.darkGray);
        btn1.setForeground(Color.white);
        pn1.add(btn1);
        
        btn1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(source.isEmpty() || destination.isEmpty()){
                        
                        JOptionPane.showMessageDialog(null, "Insert route info!!");
                    }
                
                else{
                   try{
                    BufferedWriter bfr1 = new BufferedWriter(new FileWriter("Passenger/source.text"));
                    bfr1.write(source);
                    bfr1.close();
                    
                    BufferedWriter bfr2 = new BufferedWriter(new FileWriter("Passenger/destination.text"));
                    bfr2.write(destination);
                    bfr2.close();
                    
                    
                   }
                   catch(Exception e){
                       e.printStackTrace();
                   }
                    
                    dispose();
                    Routes rts = new Routes();
                    rts.setVisible(true);
                }
            }
        
        });
        
        JLabel lb5 = new JLabel();
        lb5.setText(profile);
        lb5.setFont(f4);
        lb5.setBounds(1360, 160, 230, 50);
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
                Booking_Status bks = new Booking_Status();
                bks.setVisible(true);
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
    public void Places(){
       
        
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    
                    String sql = "SELECT * FROM Places";
                    
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                    
                    
                    while(rs.next()){
                        cb1.addItem(rs.getString("PlaceName"));
                        cb2.addItem(rs.getString("PlaceName"));
                    }
                    
                    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
   
    
    /*public static void main(String [] args)
    {
        Route_Select rt_select = new Route_Select();
        rt_select.setVisible(true);
    }*/

}
