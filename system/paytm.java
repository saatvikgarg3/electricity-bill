package electricity.billing.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class paytm extends JFrame implements ActionListener{
    String meter;
    JButton back;
    paytm(String meter){
        this.meter=meter;
        
        JEditorPane j=new JEditorPane();
        j.setEditable(false);
        
        try{
            j.setPage("https://paytm.com/online-payments");
        }catch(Exception e){
            j.setContentType("text/html");
            j.setText("<html>could not load<html>");
        }
        
        JScrollPane pane=new JScrollPane(j);
        add(pane);
        
        back=new JButton("Back");
        back.setBounds(640,20,80,30);
        back.addActionListener(this);
        j.add(back);
        
        setSize(800,600);
        setLocation(400,150);
        setVisible(true);
    }
    public static void main(String []args){
        new paytm("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         this.setVisible(false);
         new paybill(meter);
    }
}
