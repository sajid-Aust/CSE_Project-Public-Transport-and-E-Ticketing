package ptransport_and_eticket;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class Seats extends JFrame{
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
    String via = "<html><u>Via of selected route</u></html>", pass, b_id, pid;
     String header[] = new String[] { "SeatNo","BusID", "RowNo",
      "ColumnNo", "Availiability"};
     int cnt;
 
    BufferedWriter bfrw;
    
    Seats()
    {
        setBounds(200, 50, 1600, 900);
        setTitle("Public Transport & E-Ticketing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try{
            BufferedReader bfrr = new BufferedReader(new FileReader("Passenger/busid.text"));
            b_id = bfrr.readLine();
            bfrr.close();
            
            BufferedReader bfrr1 = new BufferedReader(new FileReader("Passenger/pid.text"));
            pid = bfrr1.readLine();
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
        
        JLabel lb1 = new JLabel("Available Seats of Selected Bus");
        lb1.setFont(f1);
        lb1.setBounds(550, 30, 800, 100);
        pn2.add(lb1);
        
        JLabel lb4 = new JLabel("Select your seat(From availiable seats)");
        lb4.setFont(f5);
        lb4.setBounds(500+100, 170, 550, 100);
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
        table.setRowHeight(40);
        
        scroll = new JScrollPane(table);
        scroll.setBounds(170, 265, 1250, 400);
        pn1.add(scroll);
        
        btn1 = new JButton("Proceed");
        btn1.setFont(f2);
        btn1.setBounds(730, 700, 140, 60);
        btn1.setBackground(Color.darkGray);
        btn1.setForeground(Color.white);
        pn1.add(btn1);
        
        btn1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if(cnt>=4){
                     JOptionPane.showMessageDialog(null, "4 seats have been selected already!!");
                     
                }
                else if(cnt==3){
                    int indexs[]=table.getSelectedRows();
                    
                    if(indexs.length>1){
                    JOptionPane.showMessageDialog(null, "3 seats already selected, more than 4 seat slection can't be possible!!");
                    
                    }
                    else if(indexs.length==0){
                    JOptionPane.showMessageDialog(null, "Select a seat!!");
                    }
                    else{
                
                      String j;
                  
                    for(int i=0; i<indexs.length; i++){
                    try{
                        
                    bfrw = new BufferedWriter(new FileWriter("Passenger/seatno"+i+".text"));
                    j=model.getValueAt(indexs[i], 0).toString();
                    //System.out.print(j+"\n");
                    bfrw.write(j);
                    bfrw.close();
                    
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                   
                   
                }
                  dispose(); 
                  Ticket tkt = new Ticket(indexs.length);
                  tkt.setVisible(true);
                }
                }
                else if(cnt==2){
                    int indexs[]=table.getSelectedRows();
                    
                    if(indexs.length>2){
                    JOptionPane.showMessageDialog(null, "2 seats already selected, more than 4 seat slection can't be possible!!");
                     
                    }
                    else if(indexs.length==0){
                    JOptionPane.showMessageDialog(null, "Select a seat!!");
                    }
                    else{
                
                      String j;
                  
                    for(int i=0; i<indexs.length; i++){
                    try{
                        
                    bfrw = new BufferedWriter(new FileWriter("Passenger/seatno"+i+".text"));
                    j=model.getValueAt(indexs[i], 0).toString();
                    bfrw.write(j);
                    bfrw.close();
                    
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                   
                   
                }
                  dispose(); 
                  Ticket tkt = new Ticket(indexs.length);
                  tkt.setVisible(true);
                }
                
                }
                else if(cnt==1){
                    int indexs[]=table.getSelectedRows();
                    
                    if(indexs.length>3){
                    JOptionPane.showMessageDialog(null, "1 seat already selected, more than 4 seat slection can't be possible!!");
                     
                    }
                    else if(indexs.length==0){
                    JOptionPane.showMessageDialog(null, "Select a seat!!");
                    }
                    else{
                
                      String j;
                  
                    for(int i=0; i<indexs.length; i++){
                    try{
                        
                    bfrw = new BufferedWriter(new FileWriter("Passenger/seatno"+i+".text"));
                    j=model.getValueAt(indexs[i], 0).toString();
                    bfrw.write(j);
                    bfrw.close();
                    
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                   
                   
                }
                  dispose(); 
                  Ticket tkt = new Ticket(indexs.length);
                  tkt.setVisible(true);
                }
                }
                else{
                    int indexs[]=table.getSelectedRows();
                
                if(indexs.length>4){
                    JOptionPane.showMessageDialog(null, "More than 4 seat slection can't be possible!!");
                    
                }
                else if(indexs.length==0){
                    JOptionPane.showMessageDialog(null, "Select a seat!!");
                }
                else{
                
                  String j;
                  
                  for(int i=0; i<indexs.length; i++){
                    try{
                        
                    bfrw = new BufferedWriter(new FileWriter("Passenger/seatno"+i+".text"));
                    j=model.getValueAt(indexs[i], 0).toString();
                    System.out.print(j+"\n"+"seatno"+i+".text");
                    bfrw.write(j);
                    bfrw.close();
                    
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                   
                   
                }
                  dispose(); 
                  Ticket tkt = new Ticket(indexs.length);
                  tkt.setVisible(true);
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
                Bus_Info bsInfo = new Bus_Info();
                bsInfo.setVisible(true);
                
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
    public ArrayList<rts_seat> SeatList(){
        ArrayList<rts_seat> seatList = new ArrayList<>();
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    
                    String sql = "SELECT * FROM Seat WHERE BusID='"+b_id+"' and Availiability='yes'";
                    
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                    rts_seat stlist;
                    
                    while(rs.next()){
                        stlist = new rts_seat(rs.getInt("SeatNo"),rs.getInt("BusID"),rs.getInt("RowNo"),rs.getInt("ColumnNo"),
                                  rs.getString("Availiability"));
                        seatList.add(stlist);
                    }
                    
                    String sql1 = "Select count(BookingID) as cnt from Booking_Detail where PassengerID='"+pid+"'";
                    
                    ResultSet rs1 = st.executeQuery(sql1);
                    
                    while(rs1.next()){
                        cnt=rs1.getInt("cnt");
                    }
                    
                    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return seatList;
    }
    
    public void showUser(){
        ArrayList<rts_seat> list=SeatList();
        
        Object[] row = new Object[5];
        for(int i=0; i<list.size(); i++){
            row[0]=list.get(i).getSeatNo();
            row[1]=list.get(i).getBusID();
            row[2]=list.get(i).getRowNo();
            row[3]=list.get(i).getColumnNo();
            row[4]=list.get(i).getAvailiability();
            
            model.addRow(row);
        }
    }
    
   
    
    /*public static void main(String [] args)
    {
        Seats seats = new Seats();
        seats.setVisible(true);
    }*/

}
