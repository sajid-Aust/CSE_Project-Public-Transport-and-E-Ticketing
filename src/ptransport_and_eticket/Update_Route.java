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

public class Update_Route extends JFrame{
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
    
    String header[] = new String[] { "RouteID", "Source", "Destination",
      "NumberOfPlacesVia" };
    String text = "<html><u>Change Via_Table</u></html>";
    String source, destination, via, id;
    Object[] row = new Object[4];
    
    Update_Route()
    {
        setBounds(200, 50, 1600, 900);
        setTitle("Public Transport & E-Ticketing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
               
               id=model.getValueAt(noOfRow, 0).toString();
               source=model.getValueAt(noOfRow, 1).toString();
               destination=model.getValueAt(noOfRow, 2).toString();
               via=model.getValueAt(noOfRow, 3).toString();
               
                 tf1.setText(source);
                 tf2.setText(destination);
                 tf3.setText(via);
                 
           }
       });

        JPanel pn3 = new JPanel();
        pn3.setBounds(0, 150, 800, 750);
        pn3.setBackground(Color.DARK_GRAY);
        pn3.setLayout(null);
        
        
        
        lb3 = new JLabel("Source");
        lb3.setFont(f2);
        lb3.setBounds(100, 280, 100, 100);
        lb3.setForeground(Color.lightGray);
        pn1.add(lb3);
        
        tf1 = new JTextField();
        tf1.setBounds(360, 290, 250, 50);
        tf1.setFont(f2);
        tf1.setBackground(Color.lightGray);
        pn1.add(tf1);
        
        lb4 = new JLabel("Destination");
        lb4.setFont(f2);
        lb4.setBounds(100, 370, 200, 100);
        lb4.setForeground(Color.lightGray);
        pn1.add(lb4);
        
        tf2 = new JTextField();
        tf2.setBounds(360, 380, 250, 50);
        tf2.setFont(f2);
        tf2.setBackground(Color.lightGray);
        pn1.add(tf2);
        
        lb5 = new JLabel("NumberOfPlacesVia");
        lb5.setFont(f2);
        lb5.setBounds(100, 460, 250, 100);
        lb5.setForeground(Color.lightGray);
        pn1.add(lb5);
        
        tf3 = new JTextField();
        tf3.setBounds(360, 470, 250, 50);
        tf3.setFont(f2);
        tf3.setBackground(Color.lightGray);
        pn1.add(tf3);
        
        btn3 = new JButton("Update");
        btn3.setBounds(520, 620, 130, 50);
        btn3.setBackground(Color.BLACK);
        btn3.setForeground(Color.WHITE);
        btn3.setFont(f2);
        pn1.add(btn3);
        
        btn3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
         
                if(!tf1.getText().isEmpty() && !tf2.getText().isEmpty() && !tf3.getText().isEmpty() 
                        ){
                    int viano=Integer.parseInt(tf3.getText());
                    int rid=Integer.parseInt(id);
                 
                 table.removeAll();
                 model.setRowCount(0);
               
                ArrayList<rts> routesList = new ArrayList<>();
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    
                    String sql1 = "Update Route Set Source=?, Destination=?, NumberOfPlacesVia="+viano+"where RouteID="+rid;
                    
                    PreparedStatement pst = con.prepareStatement(sql1);
                    
                    pst.setString(1, tf1.getText());
                    pst.setString(2, tf2.getText());
                    
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Updated successfully");
                    
                    
                    
                    String sql = "SELECT * FROM Route";
                    
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                    rts routs;
                    
                    while(rs.next()){
                        routs = new rts(rs.getInt("RouteID"),rs.getString("Source"),rs.getString("Destination"),rs.getInt("NumberOfPlacesVia"));
                        routesList.add(routs);
                    }
                    
                    for(int i=0; i<routesList.size(); i++){
                        row[0]=routesList.get(i).getRouteID();
                        row[1]=routesList.get(i).getSource();
                        row[2]=routesList.get(i).getDestination();
                        row[3]=routesList.get(i).getNumberOfPlacesVia();
            
                        model.addRow(row);
                    }
                    
                    String sql11 = "SELECT RouteID FROM Route where Source='"+tf1.getText()+"' and Destination='"+tf2.getText()+"'";
                    ResultSet rs11 = st.executeQuery(sql11);
                    String rid1=null;
                    
                    while(rs11.next()){
                        rid1 = rs11.getString("RouteID");
                    }
                    
                    
                     try{
                        BufferedWriter bfrw1 = new BufferedWriter(new FileWriter("Admin/source.text"));
                        bfrw1.write(tf1.getText());
                        bfrw1.close();
                        
                        BufferedWriter bfrw2 = new BufferedWriter(new FileWriter("Admin/destination.text"));
                        bfrw2.write(tf2.getText());
                        bfrw2.close();
                        
                        BufferedWriter bfrw3 = new BufferedWriter(new FileWriter("Admin/rid.text"));
                        bfrw3.write(rid1);
                        bfrw3.close();
                    }
                    
                    catch(Exception e){
                        e.printStackTrace();
                    }
                     
                     
                    dispose();
                    Update_Via via = new Update_Via();
                    via.setVisible(true);
            
                    
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
        btn4.setBounds(180, 620, 130, 50);
        btn4.setBackground(Color.BLACK);
        btn4.setForeground(Color.WHITE);
        btn4.setFont(f2);
        pn1.add(btn4);
        
        btn4.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
         
                if(!tf1.getText().isEmpty() && !tf2.getText().isEmpty() && !tf3.getText().isEmpty()
                       && tf4.getText().isEmpty() ){
                    int viano=Integer.parseInt(tf3.getText());
                 
                 table.removeAll();
                 model.setRowCount(0);
               
                ArrayList<rts> routesList = new ArrayList<>();
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    
                    String sql1 = "Insert into Route Values (?,?,?)";
                    
                    PreparedStatement pst = con.prepareStatement(sql1);
                    
                    pst.setString(1, tf1.getText());
                    pst.setString(2, tf2.getText());
                    pst.setInt(3, viano);
                    
                    pst.executeUpdate();
   
                    JOptionPane.showMessageDialog(null, "Added successfully");
                    
                    String sql = "SELECT * FROM Route";
                    
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                    rts routs;
                    
                    while(rs.next()){
                        routs = new rts(rs.getInt("RouteID"),rs.getString("Source"),rs.getString("Destination"),rs.getInt("NumberOfPlacesVia"));
                        routesList.add(routs);
                    }
                    
                    for(int i=0; i<routesList.size(); i++){
                        row[0]=routesList.get(i).getRouteID();
                        row[1]=routesList.get(i).getSource();
                        row[2]=routesList.get(i).getDestination();
                        row[3]=routesList.get(i).getNumberOfPlacesVia();
            
                        model.addRow(row);
                    }
                    
                    String sql11 = "SELECT RouteID FROM Route where Source='"+tf1.getText()+"' and Destination='"+tf2.getText()+"'";
                    ResultSet rs11 = st.executeQuery(sql11);
                    String rid=null;
                    
                    while(rs11.next()){
                        rid = rs11.getString("RouteID");
                    }
                    
                    try{
                        BufferedWriter bfrw1 = new BufferedWriter(new FileWriter("Admin/source.text"));
                        bfrw1.write(tf1.getText());
                        bfrw1.close();
                        
                        BufferedWriter bfrw2 = new BufferedWriter(new FileWriter("Admin/destination.text"));
                        bfrw2.write(tf2.getText());
                        bfrw2.close();
                        
                        BufferedWriter bfrw3 = new BufferedWriter(new FileWriter("Admin/rid.text"));
                        bfrw3.write(rid);
                        bfrw3.close();
                    }
                    
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    
                    dispose();
                    Update_Via via = new Update_Via();
                    via.setVisible(true);
            
                    
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
        btn5.setBounds(350, 620, 130, 50);
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
            }
        
        });

        
        /*lb6 = new JLabel();
        lb6.setText(text);
        lb6.setFont(f5);
        lb6.setBounds(295, 690, 400, 100);
        lb6.setForeground(Color.YELLOW);
        pn1.add(lb6);
        
        lb6.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent me) {
                
            }

            @Override
            public void mousePressed(MouseEvent me) {
                
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                  try{
                       BufferedWriter bfr1 = new BufferedWriter(new FileWriter("admin/routeID.txt")); 
                       BufferedWriter bfr2 = new BufferedWriter(new FileWriter("admin/viano.txt"));
                       
                       
                       
                       bfr1.write(tf4.getText());
                       bfr2.write(tf3.getText());
                       
                       bfr1.close();
                       bfr2.close();
                       
                    }
                    catch(Exception e){
                        
                    }

                  if(tf4.getText().isEmpty()){
                      JOptionPane.showMessageDialog(null, "Select one route");
                  }
                  else{
                  dispose();
                  Update_Via updv = new Update_Via();
                  updv.setVisible(true);
                  }
                //showUser();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                
            }

            @Override
            public void mouseExited(MouseEvent me) {
                
            }
        
        });*/
        

        
        JLabel lb1 = new JLabel("Route Update");
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
                //PTransport_and_Eticket home = new PTransport_and_Eticket();
                //home.setVisible(true);
                AdminActivity adm = new AdminActivity();
                adm.setVisible(true);
                
            }
        
        });
        
        
        pn1.add(pn2);
        pn1.add(pn3);
        pn1.add(pn4);
        this.add(pn1);
        
    }
    
    public ArrayList<rts> routeList(){
        ArrayList<rts> routesList = new ArrayList<>();
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    
                    String sql = "SELECT * FROM Route";
                    
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                    rts routs;
                    
                    while(rs.next()){
                        routs = new rts(rs.getInt("RouteID"),rs.getString("Source"),rs.getString("Destination"),rs.getInt("NumberOfPlacesVia"));
                        routesList.add(routs);
                    }
                    
                    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return routesList;
    }
    
    public void showUser(){
        ArrayList<rts> list=routeList();
        
        
        for(int i=0; i<list.size(); i++){
            row[0]=list.get(i).getRouteID();
            row[1]=list.get(i).getSource();
            row[2]=list.get(i).getDestination();
            row[3]=list.get(i).getNumberOfPlacesVia();
            
            model.addRow(row);
        }
    }
    
    
    public static void main(String [] args)
    {
        Update_Route route_update = new Update_Route();
        route_update.setVisible(true);
    }
  
}
