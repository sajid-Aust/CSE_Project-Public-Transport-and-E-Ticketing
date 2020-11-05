package ptransport_and_eticket;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Update_Bus extends JFrame{
    private Container c;
    private JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8;
    private JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7;
    private JPasswordField pf;
    private JPanel pn1, pn2, pn3;
    private JButton btn1, btn2, btn3, btn4, btn5;
    private Font f1, f2, f3, f4, f5;
    private JTable table;
    private JScrollPane scroll;
    private DefaultTableModel model;
    
     String header[] = new String[] { "BusID", "CompanyName",
      "Class", "TotalSeats","FairPerSeat", "DepartureDate","DepartureTime"};
    
    String source, destination, via, id, src, dest, r_id, busid;
    Object[] row = new Object[7];
    String viaid, routeid, place, c_ac, c_nac;
    int c_acc, c_noac;
    
    Update_Bus()
    {
        setBounds(200, 50, 1600, 900);
        setTitle("Public Transport & E-Ticketing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       try{
            //BufferedReader bfr1 = new BufferedReader(new FileReader("admin/source.text"));
            //src = bfr1.readLine();
            //bfr1.close();
            
            /*BufferedReader bfr2 = new BufferedReader(new FileReader("admin/destination.text"));
            dest = bfr2.readLine();
            bfr2.close();*/
            
            BufferedReader bfr3 = new BufferedReader(new FileReader("admin/rid.text"));
            r_id = bfr3.readLine();
            bfr3.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        initComponents();
        showUser();
        
    }
    
    void initComponents()
    {
        f1 = new Font("Arial", Font.BOLD, 44);
        f2 = new Font("Arial", Font.BOLD, 24);
        f3 = new Font("Arial", Font.BOLD, 19);
        f4 = new Font("Arial", Font.BOLD, 26);
        f5 = new Font("Arial", Font.BOLD, 21);
        
        Color clr = new Color(44, 62, 80);
        
        JPanel pn1 = new JPanel();
        pn1.setBounds(0,0, 1600, 900);
        pn1.setBackground(clr);
        pn1.setLayout(null);
       
        
        JPanel pn2 = new JPanel();
        pn2.setBounds(0, 0, 1600, 150);
        pn2.setBackground(Color.ORANGE);
        pn2.setLayout(null);
        
        JLabel lb2 = new JLabel("Bus_Table");
        lb2.setFont(f2);
        lb2.setBounds(1100, 220, 400, 100);
        pn1.add(lb2);
        
        JPanel pn4 = new JPanel();
        pn4.setBounds(500, 150, 1300, 750);
        pn4.setBackground(Color.LIGHT_GRAY);
        pn4.setLayout(null);
        
        table = new JTable(){

        public boolean isCellEditable(int row, int column) {                
                return false;               
        };
    };
        model = new DefaultTableModel();
        model.setColumnIdentifiers(header);
        table.setModel(model);
        table.setFont(f3);
        table.setSelectionBackground(new Color(237, 217, 31));
        table.setBackground(new Color(230, 202, 101));
        table.setRowHeight(40);
        
        scroll = new JScrollPane(table);
        scroll.setBounds(510, 300, 1100, 400);
        pn1.add(scroll);
        
       table.addMouseListener(new MouseAdapter(){
           
           public void mouseClicked(MouseEvent me){
               
               int noOfRow = table.getSelectedRow();
               
               busid=model.getValueAt(noOfRow, 0).toString();
               /*routeid=model.getValueAt(noOfRow, 1).toString();
               place=model.getValueAt(noOfRow, 2).toString();
               c_ac=model.getValueAt(noOfRow, 3).toString();
               c_nac=model.getValueAt(noOfRow, 4).toString();*/
               
                /* tf1.setText(place);
                 tf2.setText(c_ac);
                 tf3.setText(c_nac);
                 tf4.setText(viaid);*/
               
               try{
                   BufferedWriter bfrw = new BufferedWriter(new FileWriter("admin/busid.text"));
                   bfrw.write(busid);
                   bfrw.close();
               }
               catch(Exception e){
                   
               }
          
                 
           }
       });

        JPanel pn3 = new JPanel();
        pn3.setBounds(0, 150, 500, 750);
        pn3.setBackground(Color.DARK_GRAY);
        pn3.setLayout(null);
        
        
        lb3 = new JLabel("Company");
        lb3.setFont(f2);
        lb3.setBounds(50, 140, 200, 100);
        lb3.setForeground(Color.lightGray);
        pn1.add(lb3);
        
        tf1 = new JTextField();
        tf1.setBounds(250, 160, 200, 50);
        tf1.setFont(f2);
        tf1.setBackground(Color.lightGray);
        pn1.add(tf1);
        
        lb4 = new JLabel("Class");
        lb4.setFont(f2);
        lb4.setBounds(50, 210, 200, 100);
        lb4.setForeground(Color.lightGray);
        pn1.add(lb4);
        
        tf2 = new JTextField();
        tf2.setBounds(250, 230, 200, 50);
        tf2.setFont(f2);
        tf2.setBackground(Color.lightGray);
        pn1.add(tf2);
        
        lb5 = new JLabel("Total Seat");
        lb5.setFont(f2);
        lb5.setBounds(50, 280, 200, 100);
        lb5.setForeground(Color.lightGray);
        pn1.add(lb5);
        
        tf3 = new JTextField();
        tf3.setBounds(250, 300, 200, 50);
        tf3.setFont(f2);
        tf3.setBackground(Color.lightGray);
        pn1.add(tf3);
        
        lb6 = new JLabel("Fair");
        lb6.setFont(f2);
        lb6.setBounds(50, 350, 200, 100);
        lb6.setForeground(Color.lightGray);
        pn1.add(lb6);
        
        tf4 = new JTextField();
        tf4.setBounds(250, 370, 200, 50);
        tf4.setFont(f2);
        tf4.setBackground(Color.lightGray);
        pn1.add(tf4);
        
        lb7 = new JLabel("Dept. Date");
        lb7.setFont(f2);
        lb7.setBounds(50, 420, 200, 100);
        lb7.setForeground(Color.lightGray);
        pn1.add(lb7);
        
        tf5 = new JTextField();
        tf5.setBounds(250, 440, 200, 50);
        tf5.setFont(f2);
        tf5.setBackground(Color.lightGray);
        pn1.add(tf5);
        
        lb8 = new JLabel("Dept. Time");
        lb8.setFont(f2);
        lb8.setBounds(50, 490, 200, 100);
        lb8.setForeground(Color.lightGray);
        pn1.add(lb8);
        
        tf6 = new JTextField();
        tf6.setBounds(250, 510, 200, 50);
        tf6.setFont(f2);
        tf6.setBackground(Color.lightGray);
        pn1.add(tf6);
        

        
        btn3 = new JButton("Update");
        btn3.setBounds(370, 670, 130, 50);
        btn3.setBackground(Color.BLACK);
        btn3.setForeground(Color.WHITE);
        btn3.setFont(f2);
        pn1.add(btn3);
        
        /*btn3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
         
                if(!tf1.getText().isEmpty() && !tf2.getText().isEmpty() && !tf3.getText().isEmpty() 
                        ){
                   
              
                    int costac=Integer.parseInt(tf2.getText());
                    int costnac=Integer.parseInt(tf3.getText());
                 
                 table.removeAll();
                 model.setRowCount(0);
               
                ArrayList<rts_via> routesList = new ArrayList<>();
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    
                   String sql1 = "Update Via Set Place=?, CostForBusinessClass="+costac+",CostForGeneral="+ costnac+" WHERE ViaId="+viano;
                     
                    
                    PreparedStatement pst = con.prepareStatement(sql1);
                    
       
                    pst.setString(1, tf1.getText());
                    
                    int a=pst.executeUpdate();
                    System.out.print(a);
                    System.out.print(sql1);
                    JOptionPane.showMessageDialog(null, "Updated successfully");
                    
                    
                    
                    String sql = "SELECT * FROM Via where RouteID='"+r_id+"'";
                    
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                    rts_via routs;
                    
                   while(rs.next()){
                       routs = new rts_via(rs.getInt("ViaID"),rs.getInt("RouteID"),rs.getString("Source"),rs.getString("Place"),rs.getInt("CostForBusinessClass"),rs.getInt("CostForGeneral"));
                        routesList.add(routs);
                    }
                    
                    for(int i=0; i<routesList.size(); i++){
                        row[0]=routesList.get(i).getViaID();
                        row[1]=routesList.get(i).getRouteID();
                        row[2]=routesList.get(i).getPlace();
                        row[3]=routesList.get(i).getCost_for_AC();
                        row[4]=routesList.get(i).getCost_for_NONAC();
            
                         model.addRow(row);
                    }
            
                    
        }
        catch(Exception e){
            e.printStackTrace();
        }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Updation unnsuccessful");    
                }
                
               
                
            }
        
        });*/
        
        btn4 = new JButton("Add");
        btn4.setBounds(30, 670, 130, 50);
        btn4.setBackground(Color.BLACK);
        btn4.setForeground(Color.WHITE);
        btn4.setFont(f2);
        pn1.add(btn4);
        
        btn4.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
         
                if(!tf1.getText().isEmpty() && !tf2.getText().isEmpty() && !tf3.getText().isEmpty()
                      && !tf1.getText().isEmpty() && !tf2.getText().isEmpty() && !tf3.getText().isEmpty() ){
                   
                    int total_seat=Integer.parseInt(tf3.getText());
                    int fair=Integer.parseInt(tf4.getText());
                   
                 table.removeAll();
                 model.setRowCount(0);
               
                ArrayList<rtss_bus> busList = new ArrayList<>();
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    
                    
                    String sql1 = "Insert into Bus_Info(CompanyName, Class, TotalSeats, RouteID,Fairperseat, DepartureDate, DepartureTime) Values (?,?,?,?,?,?,?)";
                    
                    PreparedStatement pst = con.prepareStatement(sql1);
                    
                    
                   
                    pst.setString(1, tf1.getText());
                    pst.setString(2, tf2.getText());
                    pst.setInt(3, total_seat);
                    pst.setInt(4, Integer.parseInt(r_id));
                    pst.setInt(5, fair);
                    pst.setString(6, tf5.getText());
                    pst.setString(7, tf6.getText());
                    
                    pst.executeUpdate();
   
                    JOptionPane.showMessageDialog(null, "Added successfully");
                    
                    String sql = "SELECT * FROM Bus_Info where RouteID='"+r_id+"'";
                    
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                    rtss_bus bs;
                    
                   
                    while(rs.next()){
                        bs = new rtss_bus(rs.getInt("BusID"),rs.getString("CompanyName"),rs.getString("Class"),rs.getInt("TotalSeats"),
                                rs.getInt("FairPerSeat"),rs.getString("DepartureDate"),rs.getString("DepartureTime"));
                        busList.add(bs);
                    }
                    
                    for(int i=0; i<busList.size(); i++)
                    {
                         row[0]=busList.get(i).getBusID();
                         row[1]=busList.get(i).getCompanyName();
                         row[2]=busList.get(i).getclass();
                         row[3]=busList.get(i).getTotalSeats();
                         row[4]=busList.get(i).getFairPerSeat();
                         row[5]=busList.get(i).getDepartureDate();
                         row[6]=busList.get(i).getDepartureTime();
            
                         model.addRow(row);
                    }
            
                    
        }
        catch(Exception e){
            e.printStackTrace();
        }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Insertion unsuccessful");    
                }
                
               
                
            }
        
        });
        
        btn5 = new JButton("Clear");
        btn5.setBounds(200, 670, 130, 50);
        btn5.setBackground(Color.BLACK);
        btn5.setForeground(Color.WHITE);
        btn5.setFont(f2);
        pn1.add(btn5);
        
        btn5.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                tf5.setText("");
                tf6.setText("");
               
            }
        
        });

        
        JLabel lb1 = new JLabel("Bus Update");
        lb1.setFont(f1);
        lb1.setBounds(650, 30, 500, 100);
        pn2.add(lb1);
        
        btn2 = new JButton("Back");
        btn2.setBounds(45, 785, 130, 50);
        btn2.setBackground(Color.LIGHT_GRAY);
        btn2.setForeground(Color.black);
        btn2.setFont(f2);
        pn1.add(btn2);
        
        btn2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                //dispose();
                AdminActivity adm = new AdminActivity();
                adm.setVisible(true);
                
            }
        
        });
        
        
        pn1.add(pn2);
        pn1.add(pn3);
        pn1.add(pn4);
        this.add(pn1);
        
    }
    
    public ArrayList<rtss_bus> BusList(){
        ArrayList<rtss_bus> busList = new ArrayList<>();
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    
                    String sql = "SELECT * FROM Bus_Info where RouteID='"+10+"'" ;
                    
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                    rtss_bus bs;
                    
                    while(rs.next()){
                        bs = new rtss_bus(rs.getInt("BusID"),rs.getString("CompanyName"),rs.getString("Class"),rs.getInt("TotalSeats"),
                                rs.getInt("FairPerSeat"),rs.getString("DepartureDate"),rs.getString("DepartureTime"));
                        busList.add(bs);
                    }
                    
                    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return busList;
    }
    
    public void showUser(){
        ArrayList<rtss_bus> list=BusList();
        
        //Object[] row = new Object[7];
        for(int i=0; i<list.size(); i++){
            
            row[0]=list.get(i).getBusID();
            row[1]=list.get(i).getCompanyName();
            row[2]=list.get(i).getclass();
            row[3]=list.get(i).getTotalSeats();
            row[4]=list.get(i).getFairPerSeat();
            row[5]=list.get(i).getDepartureDate();
            row[6]=list.get(i).getDepartureTime();
            
            model.addRow(row);
        }
    }
    
   public static void main(String [] args)
    {
        Update_Bus bus = new Update_Bus();
        bus.setVisible(true);
    }
  }
