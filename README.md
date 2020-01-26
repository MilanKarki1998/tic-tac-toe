# tic-tac-toe
//User Code to enter players
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Users{
    JPanel p1;
    Users(){
         JLabel l= new JLabel("Developed by Milan Karki. I hope yo will enjoy it.");
        JFrame f=new JFrame("TicTacToe");
        p1=new JPanel();
        JLabel l1=new JLabel("      Enter Player 1=>  ");
        JLabel l2=new JLabel("      Enter Player 2=>  ");
        JTextField t1=new JTextField("Player 1 ");
        t1.setBackground(Color.BLACK);
        t1.setForeground(Color.WHITE);
        JTextField t2=new JTextField("Player 2 ");
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
        tp.setBounds(10,10,525,250);
        tp.addTab("Home",p1);
        tp.add("About",l);
        f.add(tp);
        f.setSize(560,310);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        new Users();
    }
}



//Main game code
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe{
    JFrame f;
    JLabel t,t2,t3,t4,t5;
    JLabel t6=new JLabel("Nobody Wins");
    JButton buttons[] = new JButton[9]; 
    int alternate = 0;//if this number is a even, then put a X. If it's odd, then put an O
    public TicTacToe(String v1,String v2){
        Font font1 = new Font("SansSerif", Font.BOLD, 20);
        t=new JLabel(v1+"'s turns");
        t.setBackground(Color.GRAY);
        t.setFont(font1);
        t2=new JLabel(v2+ "'s turns");
        t3=new JLabel(v1+"'s turns");
        t4=new JLabel(v1+" is winner");
        t5=new JLabel(v2+" is winner");
        JButton b=new JButton("Reset");
        b.setBackground(Color.BLACK);
        b.setForeground(Color.RED);
        JButton b2=new JButton("Exit");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.RED);
        b.addActionListener(new ResetOperation());
        b2.addActionListener(new ExitOperation());
        f = new JFrame("Tic-Tac-Toe");
        f.setLayout(new GridLayout(4,4));
        initializebuttons();
        f.add(b);
        f.add(t);
        f.add(b2);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,500);
        f.setVisible(true);
    }
    public void initializebuttons(){
        for(int i = 0; i <= 8; i++){
            buttons[i] = new JButton();
            buttons[i].setText("");
            buttons[i].addActionListener(new buttonListener());
            f.add(buttons[i]); //adds this button to JPanel (note: no need for JPanel.add(...)
        }                        //because this whole class is a JPanel already           
    }
    public void resetButtons(){
        t.setText(t3.getText());
        for(int i=0;i<=8;i++){
            buttons[i].setEnabled(true);
            buttons[i].setText("");
        }
        alternate=0;
    }
    private class ResetOperation implements ActionListener{
    @Override
        public void actionPerformed(ActionEvent e) {
            resetButtons();
        }
    }
    private class ExitOperation implements ActionListener{
    @Override
        public void actionPerformed(ActionEvent e) {
            f.dispose();
            new Users();
        }
    }
// when a button is clicked, it generates an ActionEvent. Thus, each button needs an ActionListener. When it is clicked, it goes to this listener class that I have created and goes to the actionPerformed method. There (and in this class), we decide what we want to do.
    private class buttonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) { 
            JButton buttonClicked = (JButton)e.getSource(); //get the particular button that was clicked
            if(alternate%2 == 0){
                t.setText(t2.getText());
                buttonClicked.setText("X");
                buttonClicked.setEnabled(false);
            }
            else{
                t.setText(t3.getText());
                buttonClicked.setText("O");
                buttonClicked.setEnabled(false);
            }
            if(checkForWin() == true){
                if(buttonClicked.getText()=="X"){
                    int input=JOptionPane.showConfirmDialog(null,t4.getText()+" Do you want to play again",null, JOptionPane.YES_NO_OPTION);
                    OptionPaneOperation(input);
                }else if(buttonClicked.getText()=="O"){
                    int input=JOptionPane.showConfirmDialog(null,t5.getText()+" Do you want to play again",null, JOptionPane.YES_NO_OPTION);
                   OptionPaneOperation(input);
                }
            }
            if(alternate==8){
                int input=JOptionPane.showConfirmDialog(null,t6.getText()+" Do you want to play again",null, JOptionPane.YES_NO_OPTION);
                OptionPaneOperation(input);
            }
            alternate++;
        }
        public void OptionPaneOperation(int input){
            if(input == JOptionPane.YES_OPTION){
                t.setText(t3.getText());
                for(int i=0;i<=8;i++){
                    buttons[i].setEnabled(true);
                    buttons[i].setText("");
                }
                alternate=-1;
            }if(input == JOptionPane.NO_OPTION){
                f.dispose();
                new Users();
            }
        }
        public boolean checkForWin(){
            //horizontal win check
            if(buttons[0].getText()==buttons[1].getText()&& buttons[1].getText()==buttons[2].getText()&&buttons[0].getText()!=("")&&buttons[1].getText()!=("")&&buttons[2].getText()!=("")){
               buttons[0].setBackground(Color.RED);
               buttons[1].setBackground(Color.RED);
               buttons[2].setBackground(Color.RED);
                return true;
            }else if(buttons[3].getText()==buttons[4].getText()&& buttons[4].getText()==buttons[5].getText()&&buttons[3].getText()!=("")&&buttons[4].getText()!=("")&&buttons[5].getText()!=("")){
                buttons[3].setBackground(Color.RED);
               buttons[4].setBackground(Color.RED);
               buttons[5].setBackground(Color.RED);
                return true;
            }else if(buttons[6].getText()==buttons[7].getText()&& buttons[7].getText()==buttons[8].getText()&&buttons[6].getText()!=("")&&buttons[7].getText()!=("")&&buttons[8].getText()!=("")){
                buttons[6].setBackground(Color.RED);
               buttons[7].setBackground(Color.RED);
               buttons[8].setBackground(Color.RED);
                return true;
            }
            //vertical win check
            else if(buttons[0].getText()==buttons[3].getText()&& buttons[3].getText()==buttons[6].getText()&&buttons[0].getText()!=("")&&buttons[3].getText()!=("")&&buttons[6].getText()!=("")){
                buttons[0].setBackground(Color.RED);
               buttons[3].setBackground(Color.RED);
               buttons[6].setBackground(Color.RED);
                return true;
            }else if(buttons[1].getText()==buttons[4].getText()&& buttons[4].getText()==buttons[7].getText()&&buttons[1].getText()!=("")&&buttons[4].getText()!=("")&&buttons[7].getText()!=("")){
                buttons[4].setBackground(Color.RED);
               buttons[1].setBackground(Color.RED);
               buttons[7].setBackground(Color.RED);
                return true;
            }else if(buttons[2].getText()==buttons[5].getText()&& buttons[5].getText()==buttons[8].getText()&&buttons[2].getText()!=("")&&buttons[5].getText()!=("")&&buttons[8].getText()!=("")){
                buttons[8].setBackground(Color.RED);
               buttons[5].setBackground(Color.RED);
               buttons[2].setBackground(Color.RED);
                return true;
            }
            //diagonal win check
            else if(buttons[0].getText()==buttons[4].getText()&& buttons[4].getText()==buttons[8].getText()&&buttons[0].getText()!=("")&&buttons[4].getText()!=("")&&buttons[8].getText()!=("")){
                buttons[0].setBackground(Color.RED);
               buttons[4].setBackground(Color.RED);
               buttons[8].setBackground(Color.RED);
                return true;
            }else if(buttons[2].getText()==buttons[4].getText()&& buttons[4].getText()==buttons[6].getText()&&buttons[2].getText()!=("")&&buttons[4].getText()!=("")&&buttons[6].getText()!=("")){
                buttons[4].setBackground(Color.RED);
               buttons[6].setBackground(Color.RED);
               buttons[2].setBackground(Color.RED);
                return true;
            }
            return false;
        } 
    }
}
