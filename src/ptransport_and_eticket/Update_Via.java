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

public class Update_Via extends JFrame{
    private Container c;
    private JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7;
    private JTextField tf1, tf2, tf3, tf4;
    private JPasswordField pf;
    private JPanel pn1, pn2, pn3;
    private JButton btn1, btn2, btn3, btn4, btn5;
    private Font f1, f2, f3, f4, f5;
    private JTable table;
    private JScrollPane scroll;
    private DefaultTableModel model;
    
    String header[] = new String[] {"ViaID","RouteID", "Source","Place", "Cost_for_AC",
      "Cost_for_NONAC" };
    
    String source, destination, via, id, src, dest, r_id;
    Object[] row = new Object[6];
    String viaid, routeid, place, c_ac, c_nac;
    int c_acc, c_noac;
    
    Update_Via()
    {
        setBounds(200, 50, 1600, 900);
        setTitle("Public Transport & E-Ticketing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       try{
            BufferedReader bfr1 = new BufferedReader(new FileReader("admin/source.text"));
            src = bfr1.readLine();
            bfr1.close();
            
            BufferedReader bfr2 = new BufferedReader(new FileReader("admin/destination.text"));
            dest = bfr2.readLine();
            bfr2.close();
            
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
        
        JLabel lb2 = new JLabel("Route_Table");
        lb2.setFont(f2);
        lb2.setBounds(1100, 220, 400, 100);
        pn1.add(lb2);
        
        JPanel pn4 = new JPanel();
        pn4.setBounds(800, 150, 800, 750);
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
        scroll.setBounds(840, 300, 700, 400);
        pn1.add(scroll);
        
       table.addMouseListener(new MouseAdapter(){
           
           public void mouseClicked(MouseEvent me){
               
               int noOfRow = table.getSelectedRow();
               
               viaid=model.getValueAt(noOfRow, 0).toString();
               routeid=model.getValueAt(noOfRow, 1).toString();
               place=model.getValueAt(noOfRow, 2).toString();
               c_ac=model.getValueAt(noOfRow, 3).toString();
               c_nac=model.getValueAt(noOfRow, 4).toString();
               
                 tf1.setText(place);
                 tf2.setText(c_ac);
                 tf3.setText(c_nac);
                 //tf4.setText(viaid);
          
                 
           }
       });

        JPanel pn3 = new JPanel();
        pn3.setBounds(0, 150, 800, 750);
        pn3.setBackground(Color.DARK_GRAY);
        pn3.setLayout(null);
        
        lb6 = new JLabel("From "+src+" to "+dest);
        lb6.setFont(f2);
        lb6.setBounds(100, 200, 400, 100);
        lb6.setForeground(Color.lightGray);
        pn1.add(lb6);
        
        
        lb3 = new JLabel("Place");
        lb3.setFont(f2);
        lb3.setBounds(100, 350, 100, 100);
        lb3.setForeground(Color.lightGray);
        pn1.add(lb3);
        
        tf1 = new JTextField();
        tf1.setBounds(360, 370, 250, 50);
        tf1.setFont(f2);
        tf1.setBackground(Color.lightGray);
        pn1.add(tf1);
        
        lb4 = new JLabel("CostForAC");
        lb4.setFont(f2);
        lb4.setBounds(100, 450, 200, 100);
        lb4.setForeground(Color.lightGray);
        pn1.add(lb4);
        
        tf2 = new JTextField();
        tf2.setBounds(360, 470, 250, 50);
        tf2.setFont(f2);
        tf2.setBackground(Color.lightGray);
        pn1.add(tf2);
        
        lb5 = new JLabel("CostForNONAC");
        lb5.setFont(f2);
        lb5.setBounds(100, 550, 250, 100);
        lb5.setForeground(Color.lightGray);
        pn1.add(lb5);
        
        tf3 = new JTextField();
        tf3.setBounds(360, 570, 250, 50);
        tf3.setFont(f2);
        tf3.setBackground(Color.lightGray);
        pn1.add(tf3);
        
        btn5 = new JButton("Next");
        btn5.setBounds(480, 770, 130, 50);
        btn5.setBackground(Color.BLACK);
        btn5.setForeground(Color.WHITE);
        btn5.setFont(f2);
        pn1.add(btn5);
        
        btn5.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                Update_Bus bs = new Update_Bus();
                bs.setVisible(true);
            }
        
        
        });
        
        btn3 = new JButton("Update");
        btn3.setBounds(480, 670, 130, 50);
        btn3.setBackground(Color.BLACK);
        btn3.setForeground(Color.WHITE);
        btn3.setFont(f2);
        pn1.add(btn3);
        
        btn3.addActionListener(new ActionListener(){

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
                    
                    String sql1 = "Update Via Set Place=?, CostForBusinessClass="+costac+",CostForGeneral="+ costnac+" WHERE ViaId="+viaid;
                     
                    
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
        
        });
        
        btn4 = new JButton("Add");
        btn4.setBounds(160, 670, 130, 50);
        btn4.setBackground(Color.BLACK);
        btn4.setForeground(Color.WHITE);
        btn4.setFont(f2);
        pn1.add(btn4);
        
        btn4.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
         
                if(!tf1.getText().isEmpty() && !tf2.getText().isEmpty() && !tf3.getText().isEmpty()){
                   
                    int costac=Integer.parseInt(tf2.getText());
                    int costnac=Integer.parseInt(tf3.getText());
                   
                 table.removeAll();
                 model.setRowCount(0);
               
                ArrayList<rts_via> routesList = new ArrayList<>();
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    
                    
                    String sql1 = "Insert into Via(RouteID, Source, Place, CostForBusinessClass, CostForGeneral) Values (?,?,?,?,?)";
                    
                    PreparedStatement pst = con.prepareStatement(sql1);
                    
                    
                   
                    pst.setInt(1, Integer.parseInt(r_id));
                    pst.setString(2, src);
                    pst.setString(3, tf1.getText());
                    pst.setInt(4, costac);
                    pst.setInt(5, costnac);
                    
                    pst.executeUpdate();
   
                    JOptionPane.showMessageDialog(null, "Added successfully");
                    
                    String sql = "SELECT * FROM Via where RouteID='"+r_id+"'";
                    
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                    rts_via routs;
                    
                    while(rs.next()){
                        routs = new rts_via(rs.getInt("ViaID"),rs.getInt("RouteID"),rs.getString("Source"),rs.getString("Place"),rs.getInt("CostForBusinessClass"),rs.getInt("CostForGeneral"));
                        routesList.add(routs);
                    }
                    
                    for(int i=0; i<routesList.size(); i++)
                    {
                        row[0]=routesList.get(i).getViaID();
                        row[1]=routesList.get(i).getRouteID();
                        row[2]=routesList.get(i).getSource();
                        row[3]=routesList.get(i).getPlace();
                        row[4]=routesList.get(i).getCost_for_AC();
                        row[5]=routesList.get(i).getCost_for_NONAC();
            
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
        btn5.setBounds(320, 670, 130, 50);
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
                
            }
        
        });

        
        JLabel lb1 = new JLabel("Route Via Update");
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
                
                dispose();
                AdminActivity adm = new AdminActivity();
                adm.setVisible(true);
                
            }
        
        });
        
        
        pn1.add(pn2);
        pn1.add(pn3);
        pn1.add(pn4);
        this.add(pn1);
        
    }
    
    public ArrayList<rts_via> routeList(){
        ArrayList<rts_via> routesList = new ArrayList<>();
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    
                    System.out.print(r_id);
                    
                    String sql = "SELECT * FROM Via where RouteID='"+r_id+"'";
                    
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                    rts_via routs;
                    
                    while(rs.next()){
                        routs = new rts_via(rs.getInt("ViaID"),rs.getInt("RouteID"),rs.getString("Source"),rs.getString("Place"),rs.getInt("CostForBusinessClass"),rs.getInt("CostForGeneral"));
                        routesList.add(routs);
                    }
                    
                    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return routesList;
    }
    
    public void showUser(){
        ArrayList<rts_via> list=routeList();
        
        
        for(int i=0; i<list.size(); i++){
            row[0]=list.get(i).getViaID();
            row[1]=list.get(i).getRouteID();
            row[2]=list.get(i).getSource();
            row[3]=list.get(i).getPlace();
            row[4]=list.get(i).getCost_for_AC();
            row[5]=list.get(i).getCost_for_NONAC();
            
            model.addRow(row);
        }
    }
    
    
   public static void main(String [] args)
    {
        Update_Via route_via = new Update_Via();
        route_via.setVisible(true);
    }
  }
