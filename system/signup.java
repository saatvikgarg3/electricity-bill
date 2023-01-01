
package electricity.billing.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.sql.*;

public class signup  extends JFrame implements ActionListener{
    JButton create,back;
    Choice accounttype;
    JTextField meter,username,name,password;
    signup(){
        
        setBounds(450,150,700,400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JPanel panel=new JPanel();
        panel.setBounds(30,30,650,300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),2),
                "Create-Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(172,216,230)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34,139,34));
        add(panel);
        
        JLabel heading=new JLabel("Create-account as");
        heading.setBounds(100,50,140,20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(heading);
        
        accounttype=new Choice();
        accounttype.add("Admin");
        accounttype.add("Customer");
        accounttype.setBounds(260,50,150,20);
//        accounttype.addFocusListener(this);
        panel.add(accounttype);
        
        JLabel lblmeter=new JLabel("Meter-No:");
        lblmeter.setBounds(100,90,140,20);
        lblmeter.setForeground(Color.GRAY);
        lblmeter.setFont(new Font("Tahoma",Font.BOLD,14));
        lblmeter.setVisible(false);
        panel.add(lblmeter);
        
        meter=new JTextField();
        meter.setBounds(260,90,150,20);
        meter.setVisible(false);
        panel.add(meter);
           
        JLabel lblusername=new JLabel("Username");
        lblusername.setBounds(100,130,140,20);
        lblusername.setForeground(Color.GRAY);
        lblusername.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblusername);
        
        username=new JTextField();
        username.setBounds(260,130,150,20);
        panel.add(username);
        
        JLabel lblname=new JLabel("name");
        lblname.setBounds(100,170,140,20);
        lblname.setForeground(Color.GRAY);
        lblname.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblname);
        
        name=new JTextField();
        name.setBounds(260,170,150,20);
        panel.add(name);
        
         meter.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    conn c=new conn();
                    ResultSet rs=c.s.executeQuery("select* from login where meter_no= '"+meter.getText()+"'");
                    while(rs.next()){
                        name.setText(rs.getString("name"));
                    }
                    
                }catch(Exception t){
                t.printStackTrace();
            }
            }
        });
        
        JLabel lblpassword=new JLabel("password");
        lblpassword.setBounds(100,210,140,20);
        lblpassword.setForeground(Color.GRAY);
        lblpassword.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblpassword);
        
        password=new JTextField();
        password.setBounds(260,210,150,20);
        panel.add(password);
        
        accounttype.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user=accounttype.getSelectedItem();
                if(user.equals("Customer")){
                    lblmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                }
                else{
                    lblmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
            
        });
        
        create=new JButton("Create");
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.setBounds(140,260,120,25);
        create.addActionListener(this);
        panel.add(create);
        
        back=new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(300,260,120,25);
        panel.add(back);
        back.addActionListener(this);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2=i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(415,30,250,250);
        panel.add(image);
        
        setVisible(true);
    }
    public static void main(String []args){
        new signup();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==create){
            String atype=accounttype.getSelectedItem();
            String susername= username.getText();
            String sname=name.getText();
            String spassword=password.getText();
            String smeter=meter.getText();
            //in sabh values ko table mein store bhi krana pdega
            //toh table bnani pdegi in mysql so go in mysql and create table uske badh query hit karo
            
            
            //my sql ko hit marni hai 
            try{
                //eastablish the connection with my sql
                conn c=new conn();
                //query hit krne se pehle query bnani bhi toh pdegi 
                String query=null;
                if(accounttype.equals("Admin")){
                      query="insert into login values('"+smeter+"', '"+susername+"', '"+sname+"', '"+spassword+"', '"+atype+"')";
               }
                else{
                    query="update login set username='"+susername+"',password='"+spassword+"',user= '"+atype+"' where meter_no='"+smeter+"'";
                }
                c.s.executeUpdate(query);
                //pop up dikhayenge agar query exceute hojayegi jani create hojayegi profile in database
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                setVisible(false);
                new login();
                
                
            }catch(Exception x){
                x.printStackTrace();
            }
        }
        else if(e.getSource()==back){
            setVisible(false);
            new login();
        }
    }
}
