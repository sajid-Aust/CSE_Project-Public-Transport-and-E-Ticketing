
package ptransport_and_eticket;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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


public class RouteVia extends JFrame{
    private Container c;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private JLabel lb1, lb2, lb3, lb4, lb5, lb6;
    private JTextField tf1, tf2;
    private JPasswordField pf;
    private JPanel pn1, pn2, pn3;
    private JButton btn1, btn2, btn3;
    private Font f1, f2, f3, f4, f5;
    
    String text = "<html><u>Are you new? Register now</u></html>";
    String via = "<html><u>Via of selected route</u></html>", pass;
    String header[] = new String[] { "ViaID","RouteID", "Source","Via_Place",  "Fair_for_BusinessClass", "Fair_for_General" };
    
    int t_business, t_general;

    
    RouteVia()
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
        
        JLabel lb1 = new JLabel("Via Routes");
        lb1.setFont(f1);
        lb1.setBounds(700, 30, 500, 100);
        pn2.add(lb1);
        
        JLabel lb4 = new JLabel("Via routes of your selected route");
        lb4.setFont(f5);
        lb4.setBounds(500+120, 155, 500, 100);
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
       // table.setSelectionBackground(new Color(237, 217, 31));
        table.setEnabled(false);
        table.setBackground(new Color(230, 202, 101));
        table.setRowHeight(40);
        
        scroll = new JScrollPane(table);
        scroll.setBounds(130, 245, 1330, 300);
        pn1.add(scroll);
        
        
        lb5 = new JLabel();
        lb5.setFont(f5);
        lb5.setBounds(840, 520, 300, 100);
        lb5.setForeground(new Color(67, 250, 107));
        pn1.add(lb5);
        
        
        lb6 = new JLabel();
        lb6.setFont(f5);
        lb6.setBounds(1100, 520, 300, 100);
        lb6.setForeground(new Color(120, 232, 95));
        pn1.add(lb6);
        
        btn1 = new JButton("Proceed");
        btn1.setFont(f2);
        btn1.setBounds(730, 650, 140, 60);
        btn1.setBackground(Color.darkGray);
        btn1.setForeground(Color.white);
        pn1.add(btn1);
        
        btn1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                    dispose();
                    SelectDate sd = new SelectDate();
                    sd.setVisible(true);
                
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
                Routes routes = new Routes();
                routes.setVisible(true);
                
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
    
    public ArrayList<rts_via> routeList(){
        ArrayList<rts_via> routesviaList = new ArrayList<>();
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=TransportationSystem;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    
                    String sql = "SELECT * FROM Via";
                    String sql1 = "SELECT SUM(CostForBusinessClass) as SumB FROM Via";
                    String sql2 = "SELECT SUM(CostForGeneral) as SumG FROM Via";
                    
                    Statement st = con.createStatement();
                    
                    ResultSet rs = st.executeQuery(sql);
                   
                    rts_via routs;
                    
                    while(rs.next()){
                       routs = new rts_via(rs.getInt("ViaID"),rs.getInt("RouteID"),rs.getString("Source"),rs.getString("Place"),rs.getInt("CostForBusinessClass"),rs.getInt("CostForGeneral"));
                        routesviaList .add(routs);
                    }
                    
                    ResultSet rs1 = st.executeQuery(sql1);
                    
                   while(rs1.next()){
                      t_business = rs1.getInt("SumB");
                     
                   }
                   
                   ResultSet rs2 = st.executeQuery(sql2);
                   
                   while(rs2.next()){
                        t_general = rs2.getInt("SumG");
                   }
                   
                   System.out.print(t_business+"\n");
                   System.out.print(t_general);
                    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return routesviaList;
    }
    
    public void showUser(){
        ArrayList<rts_via> list=routeList();
        
        Object[] row = new Object[8];
         for(int i=0; i<list.size(); i++){
             
                        row[0]=list.get(i).getViaID();
                        row[1]=list.get(i).getRouteID();
                        row[2]=list.get(i).getSource();
                        row[3]=list.get(i).getPlace();
                        row[4]=list.get(i).getCost_for_AC();
                        row[5]=list.get(i).getCost_for_NONAC();
            
                        model.addRow(row);
                    }
         //lb5.setText("Total: "+t_business);
         //lb6.setText("Total: "+t_general);
    }
    
    
   
    
    /*public static void main(String [] args)
    {
        RouteVia via = new RouteVia();
        via.setVisible(true);
    }*/

    
}
