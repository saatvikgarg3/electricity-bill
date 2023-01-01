
package electricity.billing.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import java.sql.*;

public class paybill extends JFrame implements ActionListener{
    Choice cmonth;
    JButton pay,back;
    String meter;
    paybill(String meter){
        this.meter=meter;
        setLayout(null);
        setBounds(300,100,900,600);
        
        JLabel heading=new JLabel("Electricity Bill");
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        heading.setBounds(120,5,400,30);
        add(heading);
      
        JLabel lblmeternumber=new JLabel("Meter Number");
//        lblmeternumber.setFont(new Font("Tahoma",Font.BOLD,24));
        lblmeternumber.setBounds(35,80,200,20);
        add(lblmeternumber);
        
        JLabel meternumber=new JLabel("");
//        lblmeternumber.setFont(new Font("Tahoma",Font.BOLD,24));
        meternumber.setBounds(300,80,200,20);
        add(meternumber);
        
        JLabel lblname=new JLabel("Name");
//        lblmeternumber.setFont(new Font("Tahoma",Font.BOLD,24));
        lblname.setBounds(35,140,200,20);
        add(lblname);
        
        JLabel labelname=new JLabel("");
//        lblmeternumber.setFont(new Font("Tahoma",Font.BOLD,24));
        labelname.setBounds(300,140,200,20);
        add(labelname);
        
         JLabel lblmonth=new JLabel("Month");
//        lblmeternumber.setFont(new Font("Tahoma",Font.BOLD,24));
        lblmonth.setBounds(35,200,200,20);
        add(lblmonth);
        
        cmonth=new Choice();
        cmonth.setBounds(300,200,200,20);
        cmonth.add("January");
        cmonth.add("Feburary");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);
        
        JLabel lblunits=new JLabel("Units");
//        lblmeternumber.setFont(new Font("Tahoma",Font.BOLD,24));
        lblunits.setBounds(35,260,200,20);
        add(lblunits);
        
        JLabel labelunits=new JLabel("");
//        lblmeternumber.setFont(new Font("Tahoma",Font.BOLD,24));
        labelunits.setBounds(300,260,200,20);
        add(labelunits);
        
        JLabel lbltotalbill=new JLabel("Total Bill");
//        lblmeternumber.setFont(new Font("Tahoma",Font.BOLD,24));
        lbltotalbill.setBounds(35,320,200,20);
        add(lbltotalbill);
        
        JLabel labeltotalbill=new JLabel("");
//        lblmeternumber.setFont(new Font("Tahoma",Font.BOLD,24));
        labeltotalbill.setBounds(300,320,200,20);
        add(labeltotalbill);
        
        JLabel lblstatus=new JLabel("Status");
//        lblmeternumber.setFont(new Font("Tahoma",Font.BOLD,24));
        lblstatus.setBounds(35,380,200,20);
        add(lblstatus);
        
        JLabel labelstatus=new JLabel("");
//        lblmeternumber.setFont(new Font("Tahoma",Font.BOLD,24));
        labelstatus.setBounds(300,380,200,20);
        labelstatus.setForeground(Color.RED);
        add(labelstatus);
        
        try{
            conn c=new conn();
            ResultSet rs=c.s.executeQuery("Select * from customer where meter_no='"+meter+"'");
            while(rs.next()){
                meternumber.setText(meter);
                labelname.setText(rs.getString("name"));          
            }
            
            rs=c.s.executeQuery("Select * from bill where meter_no='"+meter+"' and month='"+cmonth.getSelectedItem()+"'");
            while(rs.next()){
                labelunits.setText(rs.getString("units"));
                labeltotalbill.setText(rs.getString("totalbill"));  
                labelstatus.setText(rs.getString("status"));  
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        cmonth.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
               try{
            conn c=new conn();
           
            ResultSet rs=c.s.executeQuery("Select * from bill where meter_no='"+meter+"' and month='"+cmonth.getSelectedItem()+"'");
            if(!rs.next()){
                 labelunits.setText("");
                labeltotalbill.setText("");  
                labelstatus.setText("");
            }
            while(rs.next()){
                labelunits.setText(rs.getString("units"));
                labeltotalbill.setText(rs.getString("totalbill"));  
                labelstatus.setText(rs.getString("status"));  
            }
            
        }catch(SQLException e2){
            e2.printStackTrace();
        }
            }
        
        });
        
        pay=new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100,460,100,25);
        pay.addActionListener(this);
        add(pay);
        
        back=new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230,460,100,25);
        back.addActionListener(this);
        add(back);
        
        getContentPane().setBackground(Color.WHITE);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2=i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(400,120,600,300);
        add(image);
        
        setVisible(true);
    }
    public static void main(String[]args){
        new paybill("");
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==pay){
            try{
                conn c=new conn();
                c.s.executeUpdate("update bill set status='Paid' where meter_no='"+meter+"' and month='"+cmonth.getSelectedItem()+"'");
            }catch(Exception f){
                f.printStackTrace();
            }
            setVisible(false);
            new paytm(meter);
        }
        else{
            setVisible(false);
        }
    }
}
