package ptransport_and_eticket;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class Booking_status2 extends JFrame {
    private Container c;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private JLabel lb1, lb2, lb3, lb4, lb5, lb6;
    private JTextField tf1, tf2;
    private JPasswordField pf;
    private JPanel pn1, pn2, pn3;
    private JButton btn1, btn2, btn3, btn4, btn5;
    private Font f1, f2, f3, f4, f5;
    String text = "<html><u>Are you new? Register now</u></html>";
    String via = "<html><u>Via of selected route</u></html>", pass, b_id;
    String header[] = new String[] {"BookingID","PassengerID","TicketNo","SeatNo","BusID","CompanyName" ,"Fair","Source",
                                     "Destination","DepartureDate","DepartureTime","Payment"};
   
    BufferedWriter bfrw;
    BufferedReader bfrd;
    
    String pid, src, dest, date, time, company, sql, sql3, sql_delt, sqli;
    
    
    int total_fair, total_ticket, index=-1;
    Connection con;
     PreparedStatement pst;
     int bck=0;
    
    Booking_status2()
    {
        setBounds(200, 50, 1600, 900);
        setTitle("Public Transport & E-Ticketing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try{
            BufferedReader bfrr = new BufferedReader(new FileReader("Passenger/pid.text"));
            pid = bfrr.readLine();
            bfrr.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        initComponents0();
        showUser();
        
    }
    
    
    
    void initComponents0()
    {
        
        
        f1 = new Font("Arial", Font.BOLD, 44);
        f2 = new Font("Arial", Font.BOLD, 24);
        f3 = new Font("Arial", Font.BOLD, 19);
        f4 = new Font("Arial", Font.BOLD, 26);
        f5 = new Font("Arial", Font.BOLD, 28);
        
        Color clr = new Color(44, 62, 80);
        
        JPanel pn1 = new JPanel();
        pn1.setBounds(0,0, 1600, 900);
        pn1.setBackground(clr);
        pn1.setLayout(null);
       
        
        JPanel pn2 = new JPanel();
        pn2.setBounds(0, 0, 1600, 150);
        pn2.setBackground(Color.ORANGE);
        pn2.setLayout(null);
        
        JLabel lb1 = new JLabel("Booking Status");
        lb1.setFont(f1);
        lb1.setBounds(650, 30, 800, 100);
        pn2.add(lb1);
        
        lb4 = new JLabel();
        lb4.setFont(f5);
        lb4.setBounds(550, 190, 700, 100);
        lb4.setForeground(Color.lightGray);
        pn1.add(lb4);
        
        lb2 = new JLabel("Booking ID:  ");
        lb2.setFont(f2);
        lb2.setBounds(920, 180, 200, 50);
        lb2.setForeground(Color.white);
        pn1.add(lb2);
        
        tf1 = new JTextField();
        tf1.setFont(f2);
        tf1.setBounds(1100, 180, 200, 50);
        tf1.setBackground(Color.BLACK);
        tf1.setForeground(Color.white);
        pn1.add(tf1);
        
        btn3 = new JButton("Search");
        btn3.setFont(f2);
        btn3.setBounds(1380, 180, 130, 50);
        btn3.setBackground(Color.YELLOW);
        btn3.setForeground(Color.black);
        pn1.add(btn3);
        
        btn3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(tf1.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "Insert a BookingID!!");
                else{
                   
                    table.removeAll();
                    model.setRowCount(0);
                    
                    String id = tf1.getText();
                    sqli = "Select * from Booking_Detail where BookingID='"+id+"'";
                    
                    executeQuery();
                }
            }
        
        
        });
        
        lb6 = new JLabel("DepartureTime:  ");
        lb6.setFont(f2);
        lb6.setBounds(120, 180, 200, 50);
        lb6.setForeground(Color.white);
        pn1.add(lb6);
        
        tf2 = new JTextField();
        tf2.setFont(f2);
        tf2.setBounds(340, 180, 200, 50);
        tf2.setBackground(Color.BLACK);
        tf2.setForeground(Color.white);
        pn1.add(tf2);
        
        btn4 = new JButton("Remove");
        btn4.setFont(f2);
        btn4.setBounds(600, 180, 130, 50);
        btn4.setBackground(Color.YELLOW);
        btn4.setForeground(Color.black);
        pn1.add(btn4);
        
        btn4.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(tf1.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "Insert DepartureTime!!");
                else{
                   
                    table.removeAll();
                    model.setRowCount(0);
                    
                    String id = tf1.getText();
                    sqli = "Delete from Booking_Detail where DepartureTime='"+tf2.getText()+"'";
                    
                    executeQuery();
                }
            }
        
        
        });
        
        
       
        table = new JTable(){

        public boolean isCellEditable(int row, int column) {                
                return false;               
        };
    };
        model = new DefaultTableModel();
        model.setColumnIdentifiers(header);
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.setFont(f3);
        table.setSelectionBackground(new Color(237, 217, 31));
        table.setBackground(new Color(230, 202, 101));
        table.setRowHeight(60);

        
        scroll = new JScrollPane(table);
        scroll.setBounds(50, 325, 1470, 260);
        pn1.add(scroll);
        
        lb5 = new JLabel();
        lb5.setFont(f5);
        lb5.setBounds(990, 520, 700, 100);
        lb5.setForeground(Color.lightGray);
        pn1.add(lb5);
        
        
        btn1 = new JButton("Update");
        btn1.setFont(f2);
        btn1.setBounds(530, 650, 140, 60);
        btn1.setBackground(Color.darkGray);
        btn1.setForeground(Color.white);
        pn1.add(btn1);
        
        btn1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
             
                   index = table.getSelectedRow()+1;
              
              
                  if(index==0)
                   JOptionPane.showMessageDialog(null, "Select a record!!");
                   
                  else{
                   
                     
                    updateuser(index-1);
                    dispose();
                    Booking_status2 bksd = new Booking_status2();
                    bksd.setVisible(true);
                   }
            }
            
        
        });
        
        btn5 = new JButton("Remove All");
        btn5.setFont(f2);
        btn5.setBounds(860, 650, 240, 60);
        btn5.setBackground(Color.darkGray);
        btn5.setForeground(Color.white);
        pn1.add(btn5);
        
        btn5.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure to remove all?", "Confirmation", JOptionPane.YES_NO_OPTION);
                
                if(choice==JOptionPane.YES_OPTION){
                    deleteAll();
                    
                    dispose();
                    Booking_status2 bkst = new Booking_status2();
                    bkst.setVisible(true);
                }
                else{
                    
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
                   AdminActivity us = new  AdminActivity ();
                   us.setVisible(true);
                
            }
        
        });
        
        
        
        
        pn1.add(pn2);
        this.add(pn1);
        
    }
    
    
    public void showUser(){
       
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                     con = DriverManager.getConnection(url);
                    
                    String sql3, sql4, sql5, sql6;
                  
                    sql="Select * from Booking_Detail";
                    
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                    
                    
                    rts_seat stlist;
                    Object[] row = new Object[12];
                    int lp=0;
                    String[] sqli=new String[4];
                    int i=0, k=0;
                    
                    while(rs.next()){
                       
                        row[0]=rs.getInt("BookingID");
                        row[1]=rs.getInt("PassengerID");
                        row[2]=rs.getInt("TicketNo");
                        row[3]=rs.getInt("SeatNo");
                        row[4]=rs.getInt("BusID");
                        row[6]=rs.getInt("Fair");
                        row[7]=rs.getString("Source");
                        row[8]=rs.getString("Destination");
                        row[9]=rs.getString("DepartureDate");
                        row[10]=rs.getString("DepartureTime");
                        row[5]=rs.getString("CompanyName");
                        row[11]=rs.getString("Payment");
                        
                        
                        model.addRow(row);
                    }
                    
                    
                    
                   
                    
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void updateuser(int us){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                     con = DriverManager.getConnection(url);
                    
                    String sql3, sql4, sql5, sql6;
                  
                    //String sqld = "Delete From Booking_Detail where BookingID='"+model.getValueAt(us, 0).toString()+"'";
                    String sqqll = "Update Booking_Detail set Payment = 'Done' where BookingID='"+model.getValueAt(us, 0).toString()+"'";
                    Statement st = con.createStatement();
                    st.executeUpdate(sqqll);
                    
        }
        catch(Exception e){
            
        }
    }
    
    public void executeQuery(){
         try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                     con = DriverManager.getConnection(url);
                    
                    String sql3, sql4, sql5, sql6;
                  
                    //String sqld = "Delete From Booking_Detail where PassengerID='"+model.getValueAt(us, 1).toString()+"'";
                    Statement st = con.createStatement();
                    ResultSet rs2 = st.executeQuery(sqli);
                    
                    Object[] row = new Object[12];
                    int lp=0;
                    String[] sqli=new String[4];
                    int i=0, k=0;
                    
                    while(rs2.next()){
                       
                        row[0]=rs2.getInt("BookingID");
                        row[1]=rs2.getInt("PassengerID");
                        row[2]=rs2.getInt("TicketNo");
                        row[3]=rs2.getInt("SeatNo");
                        row[4]=rs2.getInt("BusID");
                        row[10]=rs2.getInt("Fair");
                        row[6]=rs2.getString("Source");
                        row[7]=rs2.getString("Destination");
                        row[8]=rs2.getString("DepartureDate");
                        row[9]=rs2.getString("DepartureTime");
                        row[5]=rs2.getString("CompanyName");
                        row[11]=rs2.getString("Payment");
                        
                        /*sqli[i]="Update Seat set Availiability='No' where SeatNo='"+row[3]+"' and BusID='"+row[4]+"'";
                        k++;
                        i++;*/
                        model.addRow(row);
                    }
                    
                    
        }
        catch(Exception e){
            
        }
    }
    
    public void deleteAll(){
         try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                     con = DriverManager.getConnection(url);
                    
                    String sql3, sql4, sql5, sql6;
                  
                    String sq = "Delete From Booking_Detail";
                    Statement st = con.createStatement();
                    st.executeQuery(sq);
                    
                    String sq2 = "Update Seat set Availiability='yes'";
                    st.executeUpdate(sq2);
                    
        }
        catch(Exception e){
            
        }
    }
   
    
   public static void main(String [] args)
    {
        Booking_status2 bks = new Booking_status2();
        bks.setVisible(true);
    }
}
