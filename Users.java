/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiniProject;

/**
 *
 * @author kingm
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Users{
    JPanel p1;
    Users(){
         JLabel l= new JLabel("Developed by Milan Karki. Hope you enjoy it.");
        JFrame f=new JFrame("TicTacToe");
        p1=new JPanel();
        JLabel l1=new JLabel("Enter Player 1=>");
        JLabel l2=new JLabel("Enter Player 2=>");
        JTextField t1=new JTextField(" Player 1 ");
        t1.setBackground(Color.BLACK);
        t1.setForeground(Color.WHITE);
        JTextField t2=new JTextField(" Player 2 ");
        t2.setBackground(Color.BLACK);
        t2.setForeground(Color.WHITE);
        JButton b=new JButton("  Play TicTacToe Game  ");
        JButton b2=new JButton("  Exit the Game  ");
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(b);
        p1.add(b2);
        ActionListener ac;
        ac = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String v1=t1.getText();
                String v2=t2.getText();
                f.dispose();
		new TicTacToe(v1,v2);
            }
        };
        ActionListener ac2;
        ac2 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        b.addActionListener(ac);
        b2.addActionListener(ac2);
        JTabbedPane tp=new JTabbedPane();
        tp.setBounds(10,10,250,250);
        tp.addTab("Home",p1);
        tp.add("About",l);
        f.add(tp);
        f.setSize(300,310);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        new Users();
    }
}
