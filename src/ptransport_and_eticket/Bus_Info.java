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

public class Bus_Info extends JFrame{
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
    String via = "<html><u>Via of selected route</u></html>", pass;
     String header[] = new String[] { "BusID", "CompanyName",
      "Class", "TotalSeats","FairPerSeat", "DepartureTime"};
     String date, bus_id=null, time=null, bus_company=null;
    BufferedWriter bfrw;
    
    Bus_Info()
    {
        setBounds(200, 50, 1600, 900);
        setTitle("Public Transport & E-Ticketing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try{
            BufferedReader bfrr1 = new BufferedReader(new FileReader("Passenger/date.text"));
            date = bfrr1.readLine();
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
        
        JLabel lb1 = new JLabel("Bus_Info");
        lb1.setFont(f1);
        lb1.setBounds(700, 30, 500, 100);
        pn2.add(lb1);
        
        JLabel lb4 = new JLabel("Select your bus");
        lb4.setFont(f5);
        lb4.setBounds(500+180, 250, 300, 100);
        lb4.setForeground(Color.lightGray);
        pn1.add(lb4);
        
        
       
        table = new JTable(){
        //private static final long serialVersionUID = 1L;

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
        scroll.setBounds(170, 320, 1250, 250);
        pn1.add(scroll);
        
        table.addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent me){
               
               int noOfRow = table.getSelectedRow();
               
               bus_id=model.getValueAt(noOfRow, 0).toString();
               time=model.getValueAt(noOfRow, 5).toString();
               bus_company=model.getValueAt(noOfRow, 1).toString();
               
               try{
                  bfrw = new BufferedWriter(new FileWriter("Passenger/busid.text")); 
                  bfrw.write(bus_id);
                  bfrw.close();
                  
                  bfrw = new BufferedWriter(new FileWriter("Passenger/time.text")); 
                  bfrw.write(time);
                  bfrw.close();
                  
                  bfrw = new BufferedWriter(new FileWriter("Passenger/buscompany.text")); 
                  bfrw.write(bus_company);
                  bfrw.close();
               }catch(Exception e){
                   e.printStackTrace();
               }
           }
        
        });
        
        
        
        btn1 = new JButton("Proceed");
        btn1.setFont(f2);
        btn1.setBounds(710, 620, 140, 60);
        btn1.setBackground(Color.darkGray);
        btn1.setForeground(Color.white);
        pn1.add(btn1);
        
        btn1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(bus_id==null){
                        
                        JOptionPane.showMessageDialog(null, "Select a bus");
                    }
                
                else{
                    dispose();
                    Seats st = new Seats();
                    st.setVisible(true);
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
                RouteVia rtv = new RouteVia();
                rtv.setVisible(true);
                
            }
        
        });
        
        btn3 = new JButton("Exit");
        btn3.setBounds(1410, 765, 130, 50);
        btn3.setBackground(Color.LIGHT_GRAY);
        btn3.setForeground(Color.black);
        btn3.setFont(f2);
        pn1.add(btn3);
        
        btn3.addActionListener(new ActionListener(){

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
    
    public ArrayList<rts_bus> BusList(){
        ArrayList<rts_bus> busList = new ArrayList<>();
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    
                    String sql = "SELECT * FROM Bus_Info WHERE DepartureDate='"+date+"'";
                    
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                    rts_bus bs;
                    
                    while(rs.next()){
                        bs = new rts_bus(rs.getInt("BusID"),rs.getString("CompanyName"),rs.getString("Class"),rs.getInt("TotalSeats"),
                                rs.getInt("FairPerSeat"),rs.getString("DepartureTime"));
                        busList.add(bs);
                    }
                    
                    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return busList;
    }
    
    public void showUser(){
        ArrayList<rts_bus> list=BusList();
        
        Object[] row = new Object[6];
        for(int i=0; i<list.size(); i++){
            
            row[0]=list.get(i).getBusID();
            row[1]=list.get(i).getCompanyName();
            row[2]=list.get(i).getclass();
            row[3]=list.get(i).getTotalSeats();
            row[4]=list.get(i).getFairPerSeat();
            row[5]=list.get(i).getDepartureTime();
            
            model.addRow(row);
        }
    }
    
    
    public static void main(String [] args)
    {
        Bus_Info buses = new Bus_Info();
        buses.setVisible(true);
    }

}
