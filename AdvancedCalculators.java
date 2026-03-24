import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdvancedCalculators extends JFrame implements ActionListener {

    JTextField display;
    double num1=0, num2=0, memory=0;
    String op="";

    String[] keys={
            "MC","MR","M+","M-","C",
            "7","8","9","/","sqrt",
            "4","5","6","*","log",
            "1","2","3","-","x^y",
            "0",".","=","+","Back",
            "sin","cos","tan","x^2","%"
    };

    JButton[] b=new JButton[30];

    public AdvancedCalculators(){

        setTitle("Advanced Calculator");
        setSize(420,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        display=new JTextField();
        display.setFont(new Font("Arial",Font.BOLD,24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        add(display,BorderLayout.NORTH);

        JPanel p=new JPanel(new GridLayout(6,5,5,5));
        p.setBackground(Color.DARK_GRAY);

        for(int i=0;i<keys.length;i++){
            b[i]=new JButton(keys[i]);
            b[i].setFont(new Font("Arial",Font.BOLD,14));
            b[i].setBackground(new Color(50,50,50));
            b[i].setForeground(Color.WHITE);
            b[i].addActionListener(this);
            p.add(b[i]);
        }

        add(p);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String s=e.getActionCommand();

        try{
            if("0123456789.".contains(s)){
                display.setText(display.getText()+s);
            }

            else if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")||s.equals("x^y")){
                num1=Double.parseDouble(display.getText());
                op=s;
                display.setText("");
            }

            else if(s.equals("=")){
                num2=Double.parseDouble(display.getText());
                switch(op){
                    case "+":display.setText(""+(num1+num2));break;
                    case "-":display.setText(""+(num1-num2));break;
                    case "*":display.setText(""+(num1*num2));break;
                    case "/":display.setText(""+(num1/num2));break;
                    case "x^y":display.setText(""+Math.pow(num1,num2));break;
                }
            }

            else if(s.equals("sqrt")) display.setText(""+Math.sqrt(Double.parseDouble(display.getText())));
            else if(s.equals("log")) display.setText(""+Math.log10(Double.parseDouble(display.getText())));
            else if(s.equals("sin")) display.setText(""+Math.sin(Math.toRadians(Double.parseDouble(display.getText()))));
            else if(s.equals("cos")) display.setText(""+Math.cos(Math.toRadians(Double.parseDouble(display.getText()))));
            else if(s.equals("tan")) display.setText(""+Math.tan(Math.toRadians(Double.parseDouble(display.getText()))));
            else if(s.equals("x^2")){
                double n=Double.parseDouble(display.getText());
                display.setText(""+(n*n));
            }
            else if(s.equals("%")) display.setText(""+Double.parseDouble(display.getText())/100);

            // Memory
            else if(s.equals("M+")) memory+=Double.parseDouble(display.getText());
            else if(s.equals("M-")) memory-=Double.parseDouble(display.getText());
            else if(s.equals("MR")) display.setText(""+memory);
            else if(s.equals("MC")) memory=0;

            else if(s.equals("C")) display.setText("");
            else if(s.equals("Back")){
                String t=display.getText();
                display.setText(t.substring(0,t.length()-1));
            }
        }
        catch(Exception ex){
            display.setText("Error");
        }
    }

    public static void main(String[] args){
        new AdvancedCalculators();
    }
}