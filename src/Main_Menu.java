/*
    author @Morris_Keymoney
 */


import Database_Links.Cookie;
import Inserters.Insert_Exams;
import Inserters.Insert_Marks;
import Inserters.Insert_Student;
import Updaters.Student_Update;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Menu extends JPanel {
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JLabel lbl_LGid;
    private JButton btn_addStudent;
    private JButton btn_EditStudent;
    private JButton btn_EntExam;
    private JButton btn_GenReports;
    private JButton btn_createExam;
    private JButton btn_ViewAwards;

    public static void main(String [] args){
        JFrame frame = new JFrame ("Main Menu");
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add (new Main_Menu());
        frame.pack();
        frame.setLocationRelativeTo(null);//sets the location of the JFrame to the center of the screen
        frame.setResizable(false);
        frame.setVisible (true);

    }//end main

    //create the UI components
    public Main_Menu() {
        //construct components
        jcomp1 = new JLabel ("Main Menu");
        jcomp2 = new JLabel ("Logged in as");
        lbl_LGid = new JLabel (Cookie.username);
        btn_addStudent = new JButton ("Add Student");
        btn_EditStudent = new JButton ("Edit Student Details");
        btn_EntExam = new JButton ("Enter Marks");
        btn_GenReports = new JButton ("Generate Reports");
        btn_createExam = new JButton ("Create Exam");
        btn_ViewAwards = new JButton ("View Awards");

        //adjust size and set layout
        setPreferredSize (new Dimension (430, 211));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (lbl_LGid);
        add (btn_addStudent);
        add (btn_EditStudent);
        add (btn_EntExam);
        add (btn_GenReports);
        add (btn_createExam);
        add (btn_ViewAwards);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (170, 0, 77, 25);
        jcomp2.setBounds (5, 45, 100, 25);
        lbl_LGid.setBounds (95, 45, 210, 25);
        btn_addStudent.setBounds (14, 90, 191, 30);
        btn_EditStudent.setBounds (220, 127, 191, 30);
        btn_EntExam.setBounds (14, 163, 191, 30);
        btn_GenReports.setBounds (220, 90, 191, 30);
        btn_createExam.setBounds (14, 127, 191, 30);
        btn_ViewAwards.setBounds (220, 163, 191, 30);

        //Class routing

        btn_addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Insert_Student.main(new String[]{});
            }
        });//end of btn

        btn_createExam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Insert_Exams.main(new String[]{});
            }
        });//end of btn

        btn_EditStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Student_Update.main(new String[]{});
            }
        });//end of btn

        btn_EntExam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Insert_Marks.main(new String[]{});
                JOptionPane.showMessageDialog(null, "Currently Unavailable");
            }
        });//end of btn

        btn_GenReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Currently Unavailable");
            }
        });//en of btn

        btn_ViewAwards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Currently Unavailable");
            }
        });//end of btn
    }

}//end class
