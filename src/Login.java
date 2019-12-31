/*
    author @Morris_Keymoney
 */
import Database_Links.DB_API;
import Database_Links.Cookie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;


public class Login extends JPanel {
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JTextField userName;
    private JPasswordField password;
    private JButton lgBTN;

    public static void main (String [] args){
        JFrame frame = new JFrame ("Masomo Bora Management System Login");
        frame.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add (new Login());
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);//sets the location of the JFrame to the center of the screen
        frame.setVisible (true);
    }//end of main

    //the interface stuff
    public Login() {
        //construct components
        jcomp1 = new JLabel ("Masomo Bora School Management System");
        jcomp2 = new JLabel ("User Login");
        jcomp3 = new JLabel ("User Name");
        jcomp4 = new JLabel ("Password");
        userName = new JTextField (7);
        password = new JPasswordField (5);
        lgBTN = new JButton ("Login");

        //adjust size and set layout
        setPreferredSize (new Dimension(383, 210));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (userName);
        add (password);
        add (lgBTN);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (55, 0, 275, 30);
        jcomp2.setBounds (140, 35, 75, 25);
        jcomp3.setBounds (5, 69, 100, 25);
        jcomp4.setBounds (5, 117, 100, 25);
        userName.setBounds (85, 69, 285, 25);
        password.setBounds (85, 117, 285, 25);
        lgBTN.setBounds (135, 170, 100, 25);

        //now we give life to our components

        //1. the login button
        lgBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String pwd = String.valueOf(password.getPassword());
                if (login_Seq(userName.getText(), pwd) == 1){
                    System.out.println("Chonjo brathe");
                    setBackground(Color.green);
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    setBackground(Color.WHITE);
                    userName.setText("");
                    password.setText("");

                    //opem the new UI
                    Main_Menu.main(new String[]{});

                }else{
                    System.out.println("UUUUIIIIIII!!!!!!");
                    setBackground(Color.red);
                    JOptionPane.showMessageDialog(null, "Login Failed!");
                    setBackground(Color.LIGHT_GRAY);
                    userName.setText("");
                    password.setText("");
                }

            }
        });
    }//end of UI creation

    private int login_Seq(String username, String password){
        int lgStat = 0;
        String query = "Select * from Teachers where UserName = ? and Password= ?";

        try {
            Connection kon = DB_API.getKonnection();
            PreparedStatement stat = kon.prepareStatement(query);

            stat.setString(1, username);
            stat.setString(2, password);
            System.out.println("The UserName====> "+username);
            System.out.println("The Password====> "+password);
            //execute the query
            ResultSet rs = stat.executeQuery();

            //check for validity
            if(rs.next() == false){
                //message box to deny entry
                System.out.println("No way Bruh");
            } else {
                //messagebox to confirm its true
                System.out.println("Cool Bruh");
                Cookie.username = rs.getString("Full_Names");
                lgStat = 1;
            }//end if

        } catch (SQLException ex){
            System.out.println("Braeeetheeeee! Kumethoka!!!\n"+ex);
        }

        return lgStat;
    }//end of logging in method



}//end class