/*
    author @Morris_Keymoney
 */


import Inserters.Insert_Exams;
import Inserters.Insert_Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Menu extends JPanel {
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JButton btn_add_STD;
    private JButton btn_ent_marks;
    private JButton btn_View_Awards;
    private JLabel jcomp6;
    private JLabel jcomp7;
    private JButton btn_StudentActions;
    private JButton btn_Create_Exam;
    private JButton btn_Generate_Reports;

    public static void main(String [] args){
        JFrame frame = new JFrame ("Main Menu");
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add (new Main_Menu());
        frame.pack();
        frame.setVisible (true);

    }//end main

    //create the UI components
    public Main_Menu() {
        //construct components
        jcomp1 = new JLabel ("Masomo Bora Management System");
        jcomp2 = new JLabel ("Main Menu");
        btn_add_STD = new JButton ("Add Student");
        btn_ent_marks = new JButton ("Enter Marks");
        btn_View_Awards = new JButton ("View Awards");
        jcomp6 = new JLabel ("Logged in as:");
        jcomp7 = new JLabel ("super Admin");
        btn_StudentActions = new JButton ("Student Actions");
        btn_Create_Exam = new JButton ("Create Exam");
        btn_Generate_Reports = new JButton ("Generate Reports");

        //adjust size and set layout
        setPreferredSize (new Dimension(325, 265));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (btn_add_STD);
        add (btn_ent_marks);
        add (btn_View_Awards);
        add (jcomp6);
        add (jcomp7);
        add (btn_StudentActions);
        add (btn_Create_Exam);
        add (btn_Generate_Reports);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (70, 0, 235, 25);
        jcomp2.setBounds (134, 14, 70, 25);
        btn_add_STD.setBounds (15, 110, 130, 30);
        btn_ent_marks.setBounds (15, 155, 130, 30);
        btn_View_Awards.setBounds (185, 155, 130, 30);
        jcomp6.setBounds (6, 35, 80, 35);
        jcomp7.setBounds (83, 37, 238, 30);
        btn_StudentActions.setBounds (185, 200, 130, 30);
        btn_Create_Exam.setBounds (185, 110, 130, 30);
        btn_Generate_Reports.setBounds (15, 200, 134, 30);

        //lets make stuff interesting....
        btn_add_STD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //opem the new UI
                Insert_Student.main(new String[]{});
            }
        });//end of adding student btn

        btn_Create_Exam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Insert_Exams.main(new String[]{});
            }
        });//end of adding exam etc....


    }//end of UI

}//end class
