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

public class Booking_Status extends JFrame{
    private Container c;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private JLabel lb1, lb2, lb3, lb4, lb5;
    private JTextField tf1, tf2;
    private JPasswordField pf;
    private JPanel pn1, pn2, pn3;
    private JButton btn1, btn2, btn3;
    private Font f1, f2, f3, f4, f5;
    String text = "<html><u>Are you new? Register now</u></html>";
    String via = "<html><u>Via of selected route</u></html>", pass, b_id;
    String header[] = new String[] {"BookingID","PassengerID","TicketNo","SeatNo","BusID","CompanyName","Fair","Source",
                                     "Destination","DepartureDate","DepartureTime","Payment"};
   
    BufferedWriter bfrw;
    BufferedReader bfrd;
    
    String pid, src, dest, date, time, company, sql, sql3, sql_delt;
    
    
    int total_fair, total_ticket;
    Connection con;
     PreparedStatement pst;
     int bck=0, count;
     String sqll, sqlll;
    
    Booking_Status()
    {
        setBounds(200, 50, 1600, 900);
        setTitle("Public Transport & E-Ticketing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try{
            BufferedReader bfrr = new BufferedReader(new FileReader("Passenger/pid.text"));
            pid = bfrr.readLine();
            bfrr.close();
            
            BufferedReader bfrr1 = new BufferedReader(new FileReader("Passenger/busid.text"));
            b_id = bfrr1.readLine();
            bfrr1.close();
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
        
        JLabel lb1 = new JLabel("My booking Status");
        lb1.setFont(f1);
        lb1.setBounds(550, 30, 800, 100);
        pn2.add(lb1);
        
        lb4 = new JLabel();
        lb4.setFont(f5);
        lb4.setBounds(550, 190, 700, 100);
        lb4.setForeground(Color.lightGray);
        pn1.add(lb4);
        
        
       
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
        scroll.setBounds(50, 285, 1470, 260);
        pn1.add(scroll);
        
        lb5 = new JLabel();
        lb5.setFont(f5);
        lb5.setBounds(990, 520, 700, 100);
        lb5.setForeground(Color.lightGray);
        pn1.add(lb5);
        
        
        btn1 = new JButton("Cancel Reservation");
        btn1.setFont(f2);
        btn1.setBounds(630, 650, 280, 60);
        btn1.setBackground(Color.darkGray);
        btn1.setForeground(Color.white);
        pn1.add(btn1);
        
        btn1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                
                
                if(count==0){
                    JOptionPane.showMessageDialog(null, "There is no new booking record of your");
                    
                     dispose();
                     Booking_Status us = new Booking_Status();
                     us.setVisible(true);
                }
                else if(count==1){
                    
                    int indexs[]=table.getSelectedRows();
                    
                    if(indexs.length==0){
                         JOptionPane.showMessageDialog(null, "Select your reservation");
                    }
                    else{
                    deleteuser();
                    
                     dispose();
                     Booking_Status us = new Booking_Status();
                     us.setVisible(true);
                 }
                }
                
                else if(count==2){
                    int index=-1;
                    index=table.getSelectedRow();
                    int indexs[]=table.getSelectedRows();
                    
                    if(indexs.length==0 || index==-1){
                         JOptionPane.showMessageDialog(null, "Select your reservation");
                    }
                    
                    if(indexs.length==1){
                        String seat_no = model.getValueAt(index,3).toString();
                        
                        sqll = "Delete From Booking_Detail where SeatNo='"+seat_no+"' and PassengerID='"+pid+"'";
                        sqlll = "Update Seat set Availiability='yes' where SeatNo='"+seat_no+"' and BusID='"+b_id+"'";
                        
                        executeQuery();
                        updateData();
                        
                         dispose();
                         Booking_Status us = new Booking_Status();
                         us.setVisible(true);
                    }
                    else if(indexs.length==2){
                        deleteuser();
                        updateData();
                        
                         dispose();
                         Booking_Status us = new Booking_Status();
                         us.setVisible(true);
                    }
                }
                
                else if(count==3){
                     int indexs[]=table.getSelectedRows();
                     
                     if(indexs.length==0){
                         JOptionPane.showMessageDialog(null, "Select your reservation");
                    }
                     
                     if(indexs.length==1){
                         
                         int index=table.getSelectedRow();
                         String seat_no = model.getValueAt(index,3).toString();
                        
                        sqll = "Delete From Booking_Detail where SeatNo='"+seat_no+"' and PassengerID='"+pid+"'";
                        sqlll = "Update Seat set Availiability='yes' where SeatNo='"+seat_no+"' and BusID='"+b_id+"'";
                        
                        executeQuery();
                        updateData();
                        
                         dispose();
                         Booking_Status us = new Booking_Status();
                         us.setVisible(true);
                     }
                     
                     else if(indexs.length==2){
                         
                         for(int k=0; k<indexs.length; k++){
                             String seat_no = model.getValueAt(indexs[k],3).toString();
                             
                              sqll = "Delete From Booking_Detail where SeatNo='"+seat_no+"' and PassengerID='"+pid+"'";
                              sqlll = "Update Seat set Availiability='yes' where SeatNo='"+seat_no+"' and BusID='"+b_id+"'";
                              
                              executeQuery();
                              updateData();
                              
                               dispose();
                               Booking_Status us = new Booking_Status();
                               us.setVisible(true);
                         }
                     }
                     else if(indexs.length==3){
                        deleteuser();
                        updateData();
                        
                         dispose();
                         Booking_Status us = new Booking_Status();
                         us.setVisible(true);
                   }
                }
                
                else if(count==4){
                     int indexs[]=table.getSelectedRows();
                     
                     if(indexs.length==0){
                         JOptionPane.showMessageDialog(null, "Select your reservation");
                    }
                     
                     if(indexs.length==1){
                         
                         int index=table.getSelectedRow();
                         String seat_no = model.getValueAt(index,3).toString();
                        
                        sqll = "Delete From Booking_Detail where SeatNo='"+seat_no+"' and PassengerID='"+pid+"'";
                        sqlll = "Update Seat set Availiability='yes' where SeatNo='"+seat_no+"' and BusID='"+b_id+"'";
                        
                        executeQuery();
                        updateData();
                        
                          dispose();
                          Booking_Status us = new Booking_Status();
                          us.setVisible(true);
                     }
                     else if(indexs.length==2){
                         for(int k=0; k<indexs.length; k++){
                             String seat_no = model.getValueAt(indexs[k],3).toString();
                             
                              sqll = "Delete From Booking_Detail where SeatNo='"+seat_no+"' and PassengerID='"+pid+"'";
                              sqlll = "Update Seat set Availiability='yes' where SeatNo='"+seat_no+"' and BusID='"+b_id+"'";
                              
                              executeQuery();
                              updateData();
                              
                               dispose();
                               Booking_Status us = new Booking_Status();
                               us.setVisible(true);
                         }
                     }
                     else if(indexs.length==3){
                         for(int k=0; k<indexs.length; k++){
                             String seat_no = model.getValueAt(indexs[k],3).toString();
                             
                              sqll = "Delete From Booking_Detail where SeatNo='"+seat_no+"' and PassengerID='"+pid+"'";
                              sqlll = "Update Seat set Availiability='yes' where SeatNo='"+seat_no+"' and BusID='"+b_id+"'";
                              
                              executeQuery();
                              updateData();
                              
                                dispose();
                                Booking_Status us = new Booking_Status();
                                us.setVisible(true);
                         }
                   }
                     else if(indexs.length==4){
                        deleteuser();
                        updateData();
                        
                          dispose();
                          Booking_Status us = new Booking_Status();
                          us.setVisible(true);
                    }
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
                   Route_Select us = new Route_Select();
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
                  
                    sql="Select * from Booking_Detail where PassengerID='"+pid+"'";
                    
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
                       
                        
                        sqli[i]="Update Seat set Availiability='No' where SeatNo='"+row[3]+"' and BusID='"+row[4]+"'";
                        k++;
                        i++;
                        model.addRow(row);
                    }
                    
                    try{
                    for(int j=0; j<k; j++){
                        st.executeUpdate(sqli[j]);
                    }
                    
                    }catch(Exception e){
                        
                    }
                    
                    String sqli1 = "Select count(PassengerID) as cnt from Booking_Detail where PassengerID='"+pid+"'";
                    
                    ResultSet rs1 = st.executeQuery(sqli1);
                    
                   while(rs1.next()){
                       count = rs1.getInt("cnt");
                   }
                    
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void deleteuser(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                     con = DriverManager.getConnection(url);
                    
                    String sql3, sql4, sql5, sql6;
                  
                    String sqld = "Delete From Booking_Detail where PassengerID='"+pid+"'";
                    Statement st = con.createStatement();
                    st.executeQuery(sqld);
                    
        }
        catch(Exception e){
            
        }
    }
    
   public void executeQuery(){
       try{
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                     con = DriverManager.getConnection(url);
                    
                    Statement st = con.createStatement();
              
                    st.executeQuery(sqll);
       }
       catch(Exception e){
           
       }
   }
   
    public void updateData(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                     con = DriverManager.getConnection(url);
                    
                    String sql3, sql4, sql5, sql6;
                  
                    
                    
                    Statement st = con.createStatement();
                    st.executeUpdate(sqlll);
                    
                    
        }
        catch(Exception e){
            e.printStackTrace();
        }
                    
    }
    
    public static void main(String [] args)
    {
        Booking_Status bks = new Booking_Status();
        bks.setVisible(true);
    }
}
