/*
    author @Morris_Keymoney
 */
package Inserters;

import Database_Links.DB_API;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Insert_Exams extends JPanel {
    private JLabel jcomp1;
    private JComboBox act_Select;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JLabel jcomp5;
    private JLabel jcomp6;
    private JComboBox cbx_term;
    private JTextField txt_Yr;
    private JComboBox cbx_exTitle;
    private JButton btn_CreateExam;
    private JLabel jcomp11;
    private JLabel jcomp12;
    private JLabel jcomp13;
    private JLabel jcomp14;
    private JLabel jcomp15;
    private JTextField txt_TrNames;
    private JTextField txt_UName;
    private JPasswordField txt_Pwd;
    private JComboBox cbx_UserLev;
    private JButton btn_AddTr;
    private JLabel jcomp21;
    private JLabel jcomp22;
    private JLabel jcomp23;
    private JTextField txt_DeptName;
    private JComboBox cbx_SelTr;
    private JLabel jcomp26;
    private JLabel alredyHOD;
    private JButton btn_AddDept;
    private JLabel jcomp29;
    private JLabel jcomp30;
    private JTextField txt_SubjectName;
    private JComboBox cbx_Dept;
    private JButton btn_AddSubj;
    private JTextField jcomp34;
    private JTextField jcomp35;
    private JTextField jcomp36;
    private JComboBox cbx_SubjRRtn;
    private JLabel jcomp38;
    private String[] teacherIDs;
    private String[] dept_IDs;

    public static void main(String [] args){

        JFrame frame = new JFrame ("masomo_Bora_create_exam");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Insert_Exams());
        frame.pack();
        frame.setResizable(false);
        frame.setVisible (true);

    }//end of main

    public Insert_Exams() {
        //construct preComponents
        String[] act_SelectItems = {"Create Exam", "Add Teacher", "Add Department", "Add Subject"};
        String[] cbx_termItems = {"Term 1", "Term 2", "Term 3"};
        String[] cbx_exTitleItems = {"Opener", "Mid Term", "End Term"};
        String[] cbx_UserLevItems = {"Dean", "Dept. Head", "Teacher"};
        String[] cbx_SelTrItems = teachers_4_HOD_cbx();//{"Item 1", "Item 2", "Item 3"};
        String[] cbx_DeptItems = dept_names_cbx();//{"Item 1", "Item 2", "Item 3"};
        String[] cbx_SubjRRtnItems = {"Core", "Substitute", "Optional"};

        //construct components
        jcomp1 = new JLabel ("Select Action:");
        act_Select = new JComboBox (act_SelectItems);
        jcomp3 = new JLabel ("Create Exam");
        jcomp4 = new JLabel ("School Term:");
        jcomp5 = new JLabel ("Year:");
        jcomp6 = new JLabel ("Exam Titile:");
        cbx_term = new JComboBox (cbx_termItems);
        txt_Yr = new JTextField (5);
        cbx_exTitle = new JComboBox (cbx_exTitleItems);
        btn_CreateExam = new JButton ("Create Exam");
        jcomp11 = new JLabel ("Add Teacher");
        jcomp12 = new JLabel ("Full Names:");
        jcomp13 = new JLabel ("User Level:");
        jcomp14 = new JLabel ("UserName:");
        jcomp15 = new JLabel ("Password:");
        txt_TrNames = new JTextField (5);
        txt_UName = new JTextField (5);
        txt_Pwd = new JPasswordField (5);
        cbx_UserLev = new JComboBox (cbx_UserLevItems);
        btn_AddTr = new JButton ("Add Teacher");
        jcomp21 = new JLabel ("Add Department");
        jcomp22 = new JLabel ("Name:");
        jcomp23 = new JLabel ("HOD:");
        txt_DeptName = new JTextField (5);
        cbx_SelTr = new JComboBox (cbx_SelTrItems);
        jcomp26 = new JLabel ("Add Subject");
        alredyHOD = new JLabel ("");
        btn_AddDept = new JButton ("Add Department");
        jcomp29 = new JLabel ("Subject Name:");
        jcomp30 = new JLabel ("Department:");
        txt_SubjectName = new JTextField (5);
        cbx_Dept = new JComboBox (cbx_DeptItems);
        btn_AddSubj = new JButton ("Add Subject");
        jcomp34 = new JTextField (5);
        jcomp35 = new JTextField (5);
        jcomp36 = new JTextField (5);
        jcomp38 = new JLabel ("Subject Rating:");
        cbx_SubjRRtn = new JComboBox (cbx_SubjRRtnItems);


        //set components properties
        act_Select.setToolTipText ("To activate an action, select an option.");
        cbx_term.setEnabled (false);
        txt_Yr.setEnabled (false);
        cbx_exTitle.setEnabled (false);
        btn_CreateExam.setEnabled (false);
        txt_TrNames.setEnabled (false);
        txt_UName.setEnabled (false);
        txt_UName.setToolTipText ("Enter a username for the teacher");
        txt_Pwd.setEnabled (false);
        txt_Pwd.setToolTipText ("Enter a password for the teacher. It can be changed later.");
        cbx_UserLev.setEnabled (false);
        btn_AddTr.setEnabled (false);
        jcomp22.setToolTipText ("Enter a name for the new Department");
        txt_DeptName.setEnabled (false);
        cbx_SelTr.setEnabled (false);
        cbx_SelTr.setToolTipText ("Select a teacher You want as HOD");
        btn_AddDept.setEnabled (false);
        txt_SubjectName.setEnabled (false);
        txt_SubjectName.setToolTipText ("Name the subject");
        cbx_Dept.setEnabled (false);
        cbx_Dept.setToolTipText ("select a Department for this suject");
        btn_AddSubj.setEnabled (false);
        jcomp34.setEnabled (false);
        jcomp35.setEnabled (false);
        jcomp36.setEnabled (false);
        cbx_SubjRRtn.setEnabled(false);
        cbx_SubjRRtn.setToolTipText("Select Core if it is a mandatory subject for all students\n" +
                "Substitute if it is a must pick but mandatory in the begining or\n" +
                "Optional if it is of a pool of optional electives");


        //adjust size and set layout
        setPreferredSize (new Dimension(556, 456));
        setLayout (null);

        //add components
        add (jcomp1);
        add (act_Select);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (cbx_term);
        add (txt_Yr);
        add (cbx_exTitle);
        add (btn_CreateExam);
        add (jcomp11);
        add (jcomp12);
        add (jcomp13);
        add (jcomp14);
        add (jcomp15);
        add (txt_TrNames);
        add (txt_UName);
        add (txt_Pwd);
        add (cbx_UserLev);
        add (btn_AddTr);
        add (jcomp21);
        add (jcomp22);
        add (jcomp23);
        add (txt_DeptName);
        add (cbx_SelTr);
        add (jcomp26);
        add (alredyHOD);
        add (btn_AddDept);
        add (jcomp29);
        add (jcomp30);
        add (txt_SubjectName);
        add (cbx_Dept);
        add (btn_AddSubj);
        add (jcomp34);
        add (jcomp35);
        add (jcomp36);
        add (jcomp38);
        add (cbx_SubjRRtn);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (105, 5, 100, 30);
        act_Select.setBounds (200, 5, 150, 30);
        jcomp3.setBounds (30, 60, 135, 25);
        jcomp4.setBounds (5, 90, 100, 25);
        jcomp5.setBounds (5, 121, 100, 25);
        jcomp6.setBounds (5, 150, 100, 25);
        cbx_term.setBounds (95, 90, 100, 25);
        txt_Yr.setBounds (95, 120, 100, 25);
        cbx_exTitle.setBounds (95, 150, 100, 25);
        btn_CreateExam.setBounds (20, 210, 205, 35);
        jcomp11.setBounds (345, 60, 100, 25);
        jcomp12.setBounds (264, 90, 100, 25);
        jcomp13.setBounds (268, 120, 100, 25);
        jcomp14.setBounds (269, 150, 100, 25);
        jcomp15.setBounds (273, 179, 100, 25);
        txt_TrNames.setBounds (345, 90, 209, 25);
        txt_UName.setBounds (345, 150, 100, 25);
        txt_Pwd.setBounds (345, 180, 100, 25);
        cbx_UserLev.setBounds (345, 120, 144, 25);
        btn_AddTr.setBounds (315, 210, 205, 35);
        jcomp21.setBounds (35, 268, 134, 25);
        jcomp22.setBounds (5, 304, 46, 25);
        jcomp23.setBounds (10, 342, 39, 25);
        txt_DeptName.setBounds (51, 305, 149, 25);
        cbx_SelTr.setBounds (51, 342, 209, 25);
        jcomp26.setBounds (340, 265, 100, 25);
        alredyHOD.setBounds (5, 375, 269, 25);
        btn_AddDept.setBounds (25, 405, 205, 25);
        jcomp29.setBounds (290, 305, 100, 25);
        jcomp30.setBounds (299, 342, 92, 25);
        txt_SubjectName.setBounds (390, 305, 165, 25);
        cbx_Dept.setBounds (390, 342, 147, 25);
        btn_AddSubj.setBounds (327, 405, 205, 25);
        jcomp34.setBounds (270, 265, 10, 195);
        jcomp35.setBounds (249, 35, 5, 225);
        jcomp36.setBounds (0, 259, 561, 6);
        cbx_SubjRRtn.setBounds(403, 375, 135, 25);
        jcomp38.setBounds (290, 375, 145, 25);

        //and the fun begins
        //1. Activate/deactivate the section we want to use
        act_Select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                de_activator(act_Select.getSelectedItem().toString());
                System.out.println("Selected Index is===> "+act_Select.getSelectedIndex());

            }
        });//end of activation

        //2. Create a new exam
        btn_CreateExam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(txt_Yr.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "Please Enter the Year");
                } else {
                    if (newExam(cbx_term.getSelectedItem().toString(), cbx_exTitle.getSelectedItem().toString(), txt_Yr.getText())==1){
                        JOptionPane.showMessageDialog(null, "Exam has been Created");
                        txt_Yr.setText("");
                        txt_Yr.setEnabled(false);
                        cbx_term.setEnabled(false);
                        cbx_exTitle.setEnabled(false);
                        btn_CreateExam.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "An error occured while trying to create the exam." +
                                "\nPlease try again");
                    }
                }
            }
        });//end of creating an exam

        //3. Add a teacher
        btn_AddTr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //check inputs
                System.out.println("Majina===> "+txt_TrNames.getText());
                System.out.println("userlevel Index===> "+(String.valueOf(cbx_UserLev.getSelectedIndex() + 1)));
                System.out.println("Username===> "+txt_UName.getText());
                System.out.println("password===> "+txt_Pwd.getPassword().toString());



                if (txt_TrNames.getText().length()==0| txt_UName.getText().length()==0| txt_Pwd.getPassword().toString().length()==0){
                    JOptionPane.showMessageDialog(null, "Please fill all Text Fields\n" +
                            "before proceeding.");
                } else {
                    if(newTeacher(txt_TrNames.getText(), String.valueOf(cbx_UserLev.getSelectedIndex() + 1), txt_UName.getText(), String.valueOf(txt_Pwd.getPassword()))==1){

                        JOptionPane.showMessageDialog(null, "Teacher Added Successfully");

                        txt_TrNames.setText("");
                        txt_UName.setText("");
                        txt_Pwd.setText("");

                        txt_TrNames.setEnabled(false);
                        cbx_UserLev.setEnabled(false);
                        txt_UName.setEnabled(false);
                        txt_Pwd.setEnabled(false);
                        btn_AddTr.setEnabled(false);

                    }else{
                        JOptionPane.showMessageDialog(null, "There was a problem while trying\nto add the new teacher.\nPlease try again.");
                    }
                }//end if
            }
        });//end of add teacher

        //4. Add a Dept
        btn_AddDept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String tID="";
                String comparisoner ="";
                int counter = 0;//for looping through

                //get the Teacher's ID
                while (!cbx_SelTr.getSelectedItem().toString().equals(comparisoner)){
                    comparisoner = teachers_4_HOD_cbx()[counter];
                    tID = teacherIDs[counter];
                    //System.out.println("==> "+comparisoner+" ==> "+tID);
                    counter++;
                }//end while

                if(txt_DeptName.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "Please give the Department a name.");
                } else {

                    if (newDept(txt_DeptName.getText(), tID) == 1){

                        JOptionPane.showMessageDialog(null, "The new Department has been created.");
                        txt_DeptName.setText("");
                        txt_DeptName.setEnabled(false);
                        cbx_SelTr.setEnabled(false);
                        btn_AddDept.setEnabled(false);

                    } else {

                        JOptionPane.showMessageDialog(null, "An error occurred while creating a new Department.");

                    }//end if

                }//end if
            }
        });//end of adding dept

        //5. add a subject
        btn_AddSubj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String comparison ="";
                String depID ="";
                int counter=0;

                while (!cbx_Dept.getSelectedItem().toString().equals(comparison)){
                    comparison = dept_names_cbx()[counter];
                    depID = dept_IDs[counter];
                    //System.out.println("Department==> "+comparison + "\tDEpt ID===> "+depID);
                    counter++;
                }//end of while

                if (txt_SubjectName.getText().length() == 0){
                    JOptionPane.showMessageDialog(null, "Please give the subject a name and\ncorresponding Department.");
                } else{

                    if (newSubject(txt_SubjectName.getText(), depID, String.valueOf(cbx_SubjRRtn.getSelectedIndex()+1)) == 1){
                        JOptionPane.showMessageDialog(null, "New subject has been added");

                        txt_SubjectName.setText("");
//                        txt_SubjectName.setEnabled(false);
//                        cbx_Dept.setEnabled(falseun);
//                        cbx_SubjRRtn.setEnabled(false);
//                        btn_AddSubj.setEnabled(false);

                    }else{
                        JOptionPane.showMessageDialog(null, "Adding new subject failed!\nPlease try agin.");
                    }//end if else
                }//end of if... else

            }
        });//end of adding subject

    }//end of UI

    //now for the UI activation/deactivation
    private void de_activator(String activate){

        if (activate.equals("Create Exam")) {

            //add exam
            txt_Yr.setEnabled(true);
            cbx_term.setEnabled(true);
            cbx_exTitle.setEnabled(true);
            btn_CreateExam.setEnabled(true);

            //add Teacher
            txt_TrNames.setEnabled(false);
            cbx_UserLev.setEnabled(false);
            txt_UName.setEnabled(false);
            txt_Pwd.setEnabled(false);
            btn_AddTr.setEnabled(false);

            //add Dept
            txt_DeptName.setEnabled(false);
            cbx_SelTr.setEnabled(false);
            btn_AddDept.setEnabled(false);

            //add Subject
            txt_SubjectName.setEnabled(false);
            cbx_Dept.setEnabled(false);
            btn_AddSubj.setEnabled(false);

        } else if (activate.equals("Add Teacher")) {

            //add exam
            txt_Yr.setEnabled(false);
            cbx_term.setEnabled(false);
            cbx_exTitle.setEnabled(false);
            btn_CreateExam.setEnabled(false);

            //add Teacher
            txt_TrNames.setEnabled(true);
            cbx_UserLev.setEnabled(true);
            txt_UName.setEnabled(true);
            txt_Pwd.setEnabled(true);
            btn_AddTr.setEnabled(true);

            //add Dept
            txt_DeptName.setEnabled(false);
            cbx_SelTr.setEnabled(false);
            btn_AddDept.setEnabled(false);

            //add Subject
            txt_SubjectName.setEnabled(false);
            cbx_Dept.setEnabled(false);
            btn_AddSubj.setEnabled(false);
        } else if (activate.equals("Add Department")) {

            //add exam
            txt_Yr.setEnabled(false);
            cbx_term.setEnabled(false);
            cbx_exTitle.setEnabled(false);
            btn_CreateExam.setEnabled(false);

            //add Teacher
            txt_TrNames.setEnabled(false);
            cbx_UserLev.setEnabled(false);
            txt_UName.setEnabled(false);
            txt_Pwd.setEnabled(false);
            btn_AddTr.setEnabled(false);

            //add Dept
            txt_DeptName.setEnabled(true);
            cbx_SelTr.setEnabled(true);
            btn_AddDept.setEnabled(true);

            //add Subject
            txt_SubjectName.setEnabled(false);
            cbx_Dept.setEnabled(false);
            btn_AddSubj.setEnabled(false);
        }else if (activate.equals("Add Subject")) {

            //add exam
            txt_Yr.setEnabled(false);
            cbx_term.setEnabled(false);
            cbx_exTitle.setEnabled(false);
            btn_CreateExam.setEnabled(false);

            //add Teacher
            txt_TrNames.setEnabled(false);
            cbx_UserLev.setEnabled(false);
            txt_UName.setEnabled(false);
            txt_Pwd.setEnabled(false);
            btn_AddTr.setEnabled(false);

            //add Dept
            txt_DeptName.setEnabled(false);
            cbx_SelTr.setEnabled(false);
            btn_AddDept.setEnabled(false);

            //add Subject
            txt_SubjectName.setEnabled(true);
            cbx_Dept.setEnabled(true);
            cbx_SubjRRtn.setEnabled(true);
            btn_AddSubj.setEnabled(true);
        }//end if

    }//end of activating/deactivating the

    //1. Create an Exam
    private int newExam(String scTerm, String exTitle, String year){
        //Set up some variables
        int created = 0;
        String query = "Insert into Exams (School_Term, Exam_Title, Year) values (?,?,?)";

        //begin insert
        try{
            Connection kon = DB_API.getKonnection();
            PreparedStatement stat = kon.prepareStatement(query);

            //give our query some values
            stat.setString(1, scTerm);
            stat.setString(2, exTitle);
            stat.setString(3, year);

            created = stat.executeUpdate();

            //check if the update was successful
            if(created == 1){
                System.out.println("Yaay!!!");
            }else{
                created = 0;
                System.out.println("Ni kubaya brathe!!!");
            }//end if

        } catch (SQLException ex){
            System.out.println("Short version is==>\n"+ex+"\n Long version is below==>\n");
            ex.printStackTrace();
        }

        return created;
    }//end of creating an exam

    //add a teacher
    private int newTeacher(String full_Names, String user_Level, String userName, String password){
        int created = 0;
        String query = "Insert into Teachers (Full_Names, User_Level, UserName, Password) values (?,?,?,?)";

        try{
            Connection kon = DB_API.getKonnection();
            PreparedStatement stat = kon.prepareStatement(query);

            stat.setString(1, full_Names);
            stat.setString(2, user_Level);
            stat.setString(3, userName);
            stat.setString(4, password);

            created = stat.executeUpdate();

            if(created == 1){
                System.out.println("Yaay.. Teacher added");
            } else {
                System.out.println("A problem ocured");
                created = 0;
            }
        } catch (SQLException ex){
            System.out.println("Noma iko Hapa===> "+ex);
            ex.getErrorCode();
            ex.printStackTrace();
        }
        return created;
    }//end of adding teacher

    //add a department
    private int newDept(String department_Name, String department_Head){
        int created = 0;
        String query = "Insert into Departments (Dept_Name, Dept_Head) values(?,?)";
        String query2 = "update Teachers set User_Level = 2 where TID = ?";

        try {
            Connection kon = DB_API.getKonnection();
            PreparedStatement stat = kon.prepareStatement(query);
            PreparedStatement stat2 = kon.prepareStatement(query2);

            stat.setString(1, department_Name);
            stat.setString(2, department_Head);
            stat2.setString(1, department_Head);

            created = stat.executeUpdate() & stat2.executeUpdate();

            if(created == 1){
                System.out.println("Yaay.. Saved!");
            } else{
                created = 0;
            }//end if
        } catch (SQLException ex){
            ex.printStackTrace();
            ex.printStackTrace();
        }//end try... catch

        return created;
    }//end of adding a dept

    private int newSubject(String suName, String dept, String importance){
        int created = 0;
        String query = "insert into Subjects (Sub_Name, Department, Importance) values (?,?,?)";

        try{
            Connection kon = DB_API.getKonnection();
            PreparedStatement stat = kon.prepareStatement(query);

            stat.setString(1, suName);
            stat.setString(2, dept);
            stat.setString(3, importance);

            created = stat.executeUpdate();
            if(created == 1){
                System.out.println("Yaay!! Save success");
            } else {
                System.out.println("oops! Something went wrong");
                created = 0;
            }//end if

        } catch (SQLException ex){
            ex.printStackTrace();
            ex.getErrorCode();
        }//end of try catch

        return created;
    }//end of adding a subject

    //fill the teacher's combo for selecting a HOD
    private String[] teachers_4_HOD_cbx(){
        int arSize = 0;
       String [] names = new String[arSize];
       String query = "select TID, Full_Names from Teachers where User_Level = 3";
       String query2 = "select count(Full_Names) from Teachers where User_Level = 3";

       try {
           Connection kon = DB_API.getKonnection();
           PreparedStatement stat = kon.prepareStatement(query);
           PreparedStatement stat2 = kon.prepareStatement(query2);

           ResultSet rs = stat.executeQuery();
           ResultSet rs2 = stat2.executeQuery();
           int counter = 0;

           //set the array size
           if (rs2.next() == true){
               arSize = Integer.parseInt(rs2.getString("count(Full_Names)"));
           }//end if

           names = new String[arSize];
           teacherIDs = new String[arSize];

           while (rs.next() == true){
               names[counter] = rs.getString("Full_Names");
               teacherIDs[counter] = rs.getString("TID");
               //System.out.println(counter+". Names===> "+names[counter]+"\t=ID ===>"+teacherIDs[counter]);
               counter++;
           }//end while
       } catch (SQLException ex){
           ex.getErrorCode();
           ex.printStackTrace();

       }//end of Try... catch
       return names;
    }//end of teachers_4_HOD_cbx

    //fill the teacher's combo for selecting a HOD
    private String[] dept_names_cbx(){
        int arSize = 0;
        String [] dept_names = new String[arSize];
        String query = "select Dept_ID, Dept_Name from Departments order by Dept_ID asc";
        String query2 = "select count(Dept_Name) from Departments";

        try {
            Connection kon = DB_API.getKonnection();
            PreparedStatement stat = kon.prepareStatement(query);
            PreparedStatement stat2 = kon.prepareStatement(query2);

            ResultSet rs = stat.executeQuery();
            ResultSet rs2 = stat2.executeQuery();
            int counter = 0;

            //set the array size
            if (rs2.next() == true){
                arSize = Integer.parseInt(rs2.getString("count(Dept_Name)"));
            }//end if

            dept_names = new String[arSize];
            dept_IDs = new String[arSize];

            while (rs.next() == true){
                dept_names[counter] = rs.getString("Dept_Name");
                dept_IDs[counter] = rs.getString("Dept_ID");
                //System.out.println(counter+". Names===> "+dept_names[counter]+"\t=ID ===>"+dept_IDs[counter]);
                counter++;
            }//end while
        } catch (SQLException ex){
            ex.getErrorCode();
            ex.printStackTrace();

        }//end of Try... catch
        return dept_names;
    }//end of teachers_4_HOD_cbx

}//end of class
