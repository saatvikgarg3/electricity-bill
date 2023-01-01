
package electricity.billing.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class meterinfo extends JFrame implements ActionListener{
    JTextField tfname,tfaddress,tfstate,tfcity,tfemail,tfphone;
    JButton next,cancel;
    JLabel lblmeter;
    Choice meterlocation,metertype,phasecode,billtype;
    String meternumber;
    meterinfo(String meterno){
        this.meternumber=meterno;
        setSize(700,500);
        setLocation(400,200);
        
        JPanel p=new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
        JLabel heading=new JLabel("Meter information");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        p.add(heading);
        
        JLabel lblname=new JLabel("Meter No.");
        lblname.setBounds(100,80,100,20);
//        lblname.setFont(new Font("Tahoma",Font.PLAIN,15));
        p.add(lblname);
        
        JLabel lblmeternumber=new JLabel(meternumber);
        lblmeternumber.setBounds(240,80,100,20);
//        lblname.setFont(new Font("Tahoma",Font.PLAIN,15));
        p.add(lblmeternumber);
        
        JLabel lblmeterno=new JLabel("Meter Location");
        lblmeterno.setBounds(100,120,100,20);
//        lblmeterno.setFont(new Font("Tahoma",Font.PLAIN,15));
        p.add(lblmeterno);
        
        meterlocation=new Choice();
        meterlocation.add("outside");
        meterlocation.add("inside");
        meterlocation.setBounds(240,120,200,20);
        p.add(meterlocation);
        
        JLabel lbladdress=new JLabel("Meter Type");
        lbladdress.setBounds(100,160,100,20);
//        lblname.setFont(new Font("Tahoma",Font.PLAIN,15));
        p.add(lbladdress);
        
        metertype=new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(240,160,200,20);
        p.add(metertype);
        
        JLabel lblcity=new JLabel("Phase Code");
        lblcity.setBounds(100,200,100,20);
//        lblname.setFont(new Font("Tahoma",Font.PLAIN,15));
        p.add(lblcity);
        
        phasecode=new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        phasecode.setBounds(240,200,200,20);
        p.add(phasecode);
        
        JLabel lblstate=new JLabel("Bill Type");
        lblstate.setBounds(100,240,100,20);
//        lblname.setFont(new Font("Tahoma",Font.PLAIN,15));
        p.add(lblstate);
        
        billtype=new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        billtype.setBounds(240,240,200,20);
        p.add(billtype);
        
        JLabel lblemail=new JLabel("Days");
        lblemail.setBounds(100,280,100,20);
//        lblname.setFont(new Font("Tahoma",Font.PLAIN,15));
        p.add(lblemail);
        
        JLabel lblemails=new JLabel("30 Days");
        lblemails.setBounds(240,280,100,20);
//        lblname.setFont(new Font("Tahoma",Font.PLAIN,15));
        p.add(lblemails);
        
 
        JLabel lblphone=new JLabel("Note");
        lblphone.setBounds(100,320,100,20);
//        lblname.setFont(new Font("Tahoma",Font.PLAIN,15));
        p.add(lblphone);
        
        JLabel lblphones=new JLabel("By Default Bill is calculated for 30 days only");
        lblphones.setBounds(240,320,500,20);
//        lblname.setFont(new Font("Tahoma",Font.PLAIN,15));
        p.add(lblphones);
        
        next=new JButton("Submit");
        next.setBounds(220,390,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);
        
        setLayout(new BorderLayout());
        
        add(p,"Center");
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2=i1.getImage().getScaledInstance(150, 300,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        add(image,"West");
        
        setVisible(true);
    }
    public static void main(String[] args){
        new meterinfo("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==next){
            String meter=meternumber;
            String location=meterlocation.getSelectedItem();
            String type=metertype.getSelectedItem();
            String code=phasecode.getSelectedItem();
            String typebill=billtype.getSelectedItem();
            String days="30";
           
            String query="insert into meter_info values('"+meter+"', '"+location+"', '"+type+"', '"+code+"', '"+typebill+"', '"+days+"')";
            
            try{
                conn c=new conn();
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null,"Meter Information added successfully");
                setVisible(false);
                
            }catch(Exception ae){
                ae.printStackTrace();
            }
        }
        else{
            setVisible(false);
        }
    }
}

