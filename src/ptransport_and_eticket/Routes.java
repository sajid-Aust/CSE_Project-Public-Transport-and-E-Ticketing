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

public class Routes extends JFrame {
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
     String header[] = new String[] { "RouteID", "Source", "Destination",
      "NumberOfPlacesVia" };
     String source, destination, r_id=null;
    

    
    Routes()
    {
        setBounds(200, 50, 1600, 900);
        setTitle("Public Transport & E-Ticketing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
      
        
        
        
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
        
        JLabel lb1 = new JLabel("Routes of Dhaka City");
        lb1.setFont(f1);
        lb1.setBounds(650, 30, 500, 100);
        pn2.add(lb1);
        
        JLabel lb4 = new JLabel("Select your route");
        lb4.setFont(f5);
        lb4.setBounds(500+180, 270, 300, 100);
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
        table.setFont(f3);
        table.setSelectionBackground(new Color(237, 217, 31));
        table.setBackground(new Color(230, 202, 101));
        table.setRowHeight(40);
        
        scroll = new JScrollPane(table);
        scroll.setBounds(170, 365, 1250, 250);
        pn1.add(scroll);
        
        table.addMouseListener(new MouseAdapter(){
           
           public void mouseClicked(MouseEvent me){
               
               int noOfRow = table.getSelectedRow();
               
               r_id=model.getValueAt(noOfRow, 0).toString();
               
               try{
                  BufferedWriter bfrw = new BufferedWriter(new FileWriter("Passenger/rid.text")); 
                  bfrw.write(r_id);
                  bfrw.close();
               }catch(Exception e){
                   e.printStackTrace();
               }
           }
       });

        
        btn1 = new JButton("Proceed");
        btn1.setFont(f2);
        btn1.setBounds(730, 650, 140, 60);
        btn1.setBackground(Color.darkGray);
        btn1.setForeground(Color.white);
        pn1.add(btn1);
        
        btn1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(r_id==null){
                     JOptionPane.showMessageDialog(null, "Select a route!!");
                }
                else{
                    dispose();
                    RouteVia rt_via = new RouteVia();
                    rt_via.setVisible(true);
                }
            }
        
        });
        
        /*JLabel lb5 = new JLabel();
        lb5.setText(via);
        lb5.setFont(f4);
        lb5.setBounds(1300, 160, 290, 50);
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
                
                
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                
            }

            @Override
            public void mouseExited(MouseEvent me) {
                
            }
        
        });*/
        
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
                Route_Select rtss = new Route_Select();
                rtss.setVisible(true);
                
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
                userLog usl  = new userLog();
                usl.setVisible(true);
                
            }
        
        });
        
        
        pn1.add(pn2);
        this.add(pn1);
        
    }
    
   
    public ArrayList<rts> routeList(){
        ArrayList<rts> routesList = new ArrayList<>();
        
        try{
            BufferedReader bfrr1 = new BufferedReader(new FileReader("D:\\Java Programming\\PTransport_and_Eticket\\Passenger\\source.text"));
            source = bfrr1.readLine();
            System.out.print(source+"\n");
            bfrr1.close();
            
            BufferedReader bfrr2 = new BufferedReader(new FileReader("D:\\Java Programming\\PTransport_and_Eticket\\Passenger\\destination.text"));
            destination = bfrr2.readLine();
            System.out.print(destination);
            bfrr2.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    
                    String sql = "SELECT * FROM Route where Source='"+source+"' and Destination='"+destination+"'";
                    
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
        
        Object[] row = new Object[4];
        for(int i=0; i<list.size(); i++){
            row[0]=list.get(i).getRouteID();
            row[1]=list.get(i).getSource();
            row[2]=list.get(i).getDestination();
            row[3]=list.get(i).getNumberOfPlacesVia();
            
            model.addRow(row);
        }
    }
    
    /*public static void main(String [] args)
    {
        Routes routes = new Routes();
        routes.setVisible(true);
    }*/

    
}
