package ptransport_and_eticket;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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

public class Ticket extends JFrame{
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
    String header[] = new String[] { "TicketNo","SeatNo","BusID", "Fair"};
   
    BufferedWriter bfrw;
    BufferedReader bfrd;
    
    String pid, src, dest, date, time, company, sql, sql3, sql_delt;
    
    
    int total_fair, total_ticket;
    Connection con;
     PreparedStatement pst;
     //Statement st1;
     int bck=0;
    
    Ticket(int len)
    {
        setBounds(200, 50, 1600, 900);
        setTitle("Public Transport & E-Ticketing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try{
                        bfrd = new BufferedReader(new FileReader("Passenger/pid.text"));
                        pid = bfrd.readLine();
                        bfrd.close();
                        
                        bfrd = new BufferedReader(new FileReader("Passenger/source.text"));
                        src = bfrd.readLine();
                        bfrd.close();
                        
                        bfrd = new BufferedReader(new FileReader("Passenger/destination.text"));
                        dest = bfrd.readLine();
                        bfrd.close();
                        
                        bfrd = new BufferedReader(new FileReader("Passenger/date.text"));
                        date = bfrd.readLine();
                        bfrd.close();
                        
                        bfrd = new BufferedReader(new FileReader("Passenger/time.text"));
                        time = bfrd.readLine();
                        bfrd.close();
                        
                        bfrd = new BufferedReader(new FileReader("Passenger/buscompany.text"));
                        company = bfrd.readLine();
                        bfrd.close();
                        
                        
                        
                    }catch(Exception e){
                        
                    }
        
       
        
        
        initComponents0();
        showUser(len);
        
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
        
        JLabel lb1 = new JLabel("Tickets of your Selected Bus");
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
        table.setEnabled(false);
        
        scroll = new JScrollPane(table);
        scroll.setBounds(170, 285, 1250, 260);
        pn1.add(scroll);
        
        lb5 = new JLabel();
        lb5.setFont(f5);
        lb5.setBounds(990, 520, 700, 100);
        lb5.setForeground(Color.lightGray);
        pn1.add(lb5);
        
        
        btn1 = new JButton("Confirm");
        btn1.setFont(f2);
        btn1.setBounds(730, 650, 140, 60);
        btn1.setBackground(Color.darkGray);
        btn1.setForeground(Color.white);
        pn1.add(btn1);
        
        btn1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                int choice=JOptionPane.showConfirmDialog(null, "Are you want to confirm?", "Confirmation", JOptionPane.YES_NO_OPTION);
                
                if(choice==JOptionPane.YES_OPTION){
                    dispose();
                    Booking_Status bks = new Booking_Status();
                    bks.setVisible(true);
                }
                else{
                   deleteuser();
                   dispose();
                   Seats seat = new Seats();
                   seat.setVisible(true);
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
                deleteuser();  
                dispose();
                Seats seat = new Seats();
                seat.setVisible(true);
                
            }
        
        });
        
        btn3 = new JButton("Exit");
        btn3.setBounds(1410, 765, 130, 50);
        btn3.setBackground(Color.LIGHT_GRAY);
        btn3.setForeground(Color.black);
        btn3.setFont(f2);
        pn1.add(btn3);
        
        
        pn1.add(pn2);
        this.add(pn1);
        
    }
    
    
    public void showUser(int ln){
        String st1=null, st2=null, st3=null, st4=null;
        String sql1="", sql2="";
        try{
            if(ln==1){
            File f = new File("Passenger/seatno0.text"); 
            bfrd = new BufferedReader(new FileReader(f));
            st1 = bfrd.readLine();
            f.delete();
            bfrd.close();
            }
            else if(ln==2){
             File f = new File("Passenger/seatno0.text"); 
            bfrd = new BufferedReader(new FileReader(f));
            st1 = bfrd.readLine();
            f.delete();
            bfrd.close();
                
            File f1 = new File("Passenger/seatno1.text");
            bfrd = new BufferedReader(new FileReader(f1));
            st2 = bfrd.readLine();
            f1.delete();
            bfrd.close();
            }
            else if(ln==3){
            File f = new File("Passenger/seatno0.text"); 
            bfrd = new BufferedReader(new FileReader(f));
            st1 = bfrd.readLine();
            //System.out.print(st1+"\n");
            f.delete();
            bfrd.close();
                
            File f1 = new File("Passenger/seatno1.text");
            bfrd = new BufferedReader(new FileReader(f1));
            st2 = bfrd.readLine();
            //System.out.print(st2+"\n");
            f1.delete();
            bfrd.close();
                
            File f2 = new File("Passenger/seatno2.text");
            bfrd = new BufferedReader(new FileReader(f2));
            st3 = bfrd.readLine();
            //System.out.print(st3+"\n"+"lksjf");
            f2.delete();
            bfrd.close();
            }
            
            else if(ln==4){
            File f = new File("Passenger/seatno0.text"); 
            bfrd = new BufferedReader(new FileReader(f));
            st1 = bfrd.readLine();
            f.delete();
            bfrd.close();
                
            File f1 = new File("Passenger/seatno1.text");
            bfrd = new BufferedReader(new FileReader(f1));
            st2 = bfrd.readLine();
            f1.delete();
            bfrd.close();
                
            File f2 = new File("Passenger/seatno2.text");
            bfrd = new BufferedReader(new FileReader(f2));
            st3 = bfrd.readLine();
            f2.delete();
            bfrd.close();
                
            File f3 = new File("Passenger/seatno3.text");
            bfrd = new BufferedReader(new FileReader("Passenger/seatno3.text"));
            st4 = bfrd.readLine();
            
            f3.delete();
            bfrd.close();
            }
            else{
                
            }
            
             int seat1;
             int seat2;
             int seat3;
             int seat4;
            
             
            
            if(st2==null){
                
                seat1=Integer.parseInt(st1);
                sql = "SELECT * FROM Ticket WHERE SeatNo in('"+seat1+"')";
                sql1 = "SELECT sum(fair) as tot_fair FROM Ticket WHERE SeatNo in('"+seat1+"')";
                sql2 = "Select count(ticketno) as tot_ticket FROM Ticket WHERE SeatNo in('"+seat1+"')";
               
            }
            else if(st3==null){
                seat1=Integer.parseInt(st1);
                seat2=Integer.parseInt(st2);
                sql = "SELECT * FROM Ticket WHERE SeatNo in ('"+seat1+"','"+seat2+"')";
                sql1 = "SELECT sum(fair) as tot_fair FROM Ticket WHERE SeatNo in ('"+seat1+"','"+seat2+"')";
                sql2 = "SELECT count(ticketno) as tot_ticket FROM Ticket WHERE SeatNo in ('"+seat1+"','"+seat2+"')";
                
            }
            else if(st4==null){
                seat1=Integer.parseInt(st1);
                seat2=Integer.parseInt(st2);
                seat3=Integer.parseInt(st3);
                sql = "SELECT * FROM Ticket WHERE SeatNo in ('"+seat1+"','"+seat2+"','"+seat3+"')";
                sql1 = "SELECT sum(fair) as tot_fair FROM Ticket WHERE SeatNo in ('"+seat1+"','"+seat2+"','"+seat3+"')";
                sql2 = "SELECT count(ticketno) as tot_ticket FROM Ticket WHERE SeatNo in ('"+seat1+"','"+seat2+"','"+seat3+"')";
         
               
            }
            else{
                seat1=Integer.parseInt(st1);
                seat2=Integer.parseInt(st2);
                seat3=Integer.parseInt(st3);
                seat4=Integer.parseInt(st4);
                sql = "SELECT * FROM Ticket WHERE SeatNo in ('"+seat1+"','"+seat2+"','"+seat3+"','"+seat4+"')";
                sql1 = "SELECT sum(fair) as tot_fair FROM Ticket WHERE SeatNo in ('"+seat1+"','"+seat2+"','"+seat3+"','"+seat4+"')";
                sql2 = "SELECT count(ticketno) as tot_ticket FROM Ticket WHERE SeatNo in ('"+seat1+"','"+seat2+"','"+seat3+"','"+seat4+"')";
              
            }   
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                     con = DriverManager.getConnection(url);
                    
                    String sql3, sql4, sql5, sql6;
                  
                    
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                    
                    
                    rts_seat stlist;
                    Object[] row = new Object[4];
                    int lp=0, i=0, k=0;
                    String[] sqli = new String[4];
                     
                    while(rs.next()){
                       
                        row[0]=rs.getInt("TicketNo");
                        row[1]=rs.getInt("SeatNo");
                        row[2]=rs.getInt("BusID");
                        row[3]=rs.getInt("Fair");
                       
                        
                        model.addRow(row);
                        
                        
                        sqli[i]="Insert into Booking_Detail values ('"+pid+"','"+row[0]+"','"+row[1]+"','"+row[2]+"','"+row[3]+"'"
                                   + ",'"+src+"','"+dest+"','"+date+"','"+time+"','"+company+"','Not yet')";
                      
                       
                        i++;
                        
                    }
                    
                    for(int j=0; j<i; j++){
                            st.executeUpdate(sqli[j]);
                        }
                       
                    
                    
                    ResultSet rs1 = st.executeQuery(sql2);
                    
                    while(rs1.next()){
                        total_ticket = rs1.getInt("tot_ticket");
                    }
                    
                    lb4.setText("Total Tickets of your booked seats: "+total_ticket);
                    
                    ResultSet rs2 = st.executeQuery(sql1);
                    
                    while(rs2.next()){
                        total_fair = rs2.getInt("tot_fair");
                    }
                    
                    lb5.setText("Total fair:  "+total_fair+" taka"); 
                    
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
        return;
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
    
   
    
    /*public static void main(String [] args)
    {
        Ticket tkt = new Ticket(3);
        tkt.setVisible(true);
    }*/
}
