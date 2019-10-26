/*
    author @Morris_Keymoney
 */

package Inserters;

import Database_Links.DB_API;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.awt.Color.white;
import static java.awt.Transparency.OPAQUE;

public class Insert_Student extends JPanel {
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JLabel jcomp5;
    private JTextField fName;
    private JTextField oNames;
    private JTextField DOA;
    private JComboBox Daro;
    private JButton verify_BTN;
    private JButton save_STD;
    private JLabel jcomp12;
    private JLabel jcomp13;
    private JComboBox house_;
    private JComboBox cbx_gender;
    private JLabel jcomp6;

    String [] classID;
    String [] subjID;

    public static void main(String [] args){
        JFrame frame = new JFrame ("Masomo Bora - Enroll A student");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Insert_Student());
        frame.pack();
        frame.setResizable(false);
        frame.setVisible (true);
    }//end main

    //create the UI now
    public Insert_Student() {
        //construct preComponents
        String[] DaroItems = getCLasses();//{"1 North", "1 South", "2 North", "2South", "3 North", "3South"};

        String[] houses = {"Red", "Green", "Blue", "Yellow", "Orange"};
        String[] cbx_genderItems = {"M", "F"};

        //just checking if my class IDs were loaded
        for (int i = 0; i<DaroItems.length; i++){
            System.out.println("Class Id==> " + classID[i]);
        }//end for loop
        //yup, the IDs are fine

        //construct components
        jcomp1 = new JLabel ("Masomo Bora Management System");
        jcomp2 = new JLabel ("First Name:");
        jcomp3 = new JLabel ("Other Names:");
        jcomp4 = new JLabel ("Admission Date:");
        jcomp5 = new JLabel ("Class:");
        jcomp6 = new JLabel ("Gender");
        fName = new JTextField (5);
        oNames = new JTextField (5);
        DOA = new JTextField (5);
        Daro = new JComboBox (DaroItems);
        verify_BTN = new JButton ("Verify");
        save_STD = new JButton ("Enroll Student");
        jcomp12 = new JLabel ("Add Student");
        jcomp13 = new JLabel ("House:");
        house_ = new JComboBox (houses);
        cbx_gender = new JComboBox (cbx_genderItems);

        //set components properties
        fName.setToolTipText ("Enter the student's first name");
        oNames.setToolTipText ("Enter the student's other names");
        DOA.setToolTipText ("give the admission date");
        Daro.setToolTipText ("Select a class to admit the student");
        jcomp1.setBackground(Color.red);
        //adjust size and set layout
        setPreferredSize (new Dimension(362, 322));
        setBackground(Color.ORANGE);
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (fName);
        add (oNames);
        add (DOA);
        add (Daro);
        add (verify_BTN);
        add (save_STD);
        add (jcomp12);
        add (jcomp13);
        add (house_);
        add (jcomp6);
        add (cbx_gender);

        save_STD.setEnabled(false);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (70, 0, 250, 30);
        jcomp2.setBounds (25, 75, 150, 25);
        jcomp3.setBounds (25, 110, 100, 25);
        jcomp4.setBounds (10, 145, 150, 25);
        jcomp5.setBounds (70, 179, 100, 25);
        fName.setBounds (125, 75, 195, 25);
        oNames.setBounds (125, 110, 195, 25);
        DOA.setBounds (125, 145, 130, 25);
        Daro.setBounds (125, 175, 115, 25);
        verify_BTN.setBounds (25, 291, 100, 25);
        save_STD.setBounds (191, 291, 145, 25);
        jcomp12.setBounds (130, 25, 150, 30);
        jcomp13.setBounds (73, 211, 100, 25);
        house_.setBounds (125, 210, 100, 25);
        jcomp6.setBounds(70, 240, 59, 25);
        cbx_gender.setBounds(125, 240, 100, 25);

        //let the fun begin...

        //1. verify that the fields are all ok.
        verify_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String firstNames = fName.getText();
                String otherNames = oNames.getText();
                String classAdmitted = Daro.getSelectedItem().toString();

                if(firstNames.length()==0| otherNames.length()==0| classAdmitted.length()==0){

                    JOptionPane.showMessageDialog(null, "Some Data is missing from the input!\n" +
                            "Please check all input fields and ensure\nthey are all filled.");
                } else {

                    verify_BTN.setEnabled(false);
                    save_STD.setEnabled(true);

                }//end if statement


            }
        });//end of verify button

        //2. Save the student in the database

        save_STD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String firstNames = fName.getText();
                String otherNames = oNames.getText();
                String classAdmitted = Daro.getSelectedItem().toString();
                String dateAdin = DOA.getText();
                String house = house_.getSelectedItem().toString();

                if(firstNames.length()==0| house.length()==0| otherNames.length()==0| classAdmitted.length()==0| dateAdin.length()==0){

                    JOptionPane.showMessageDialog(null, "Some Data is missing from the input!\n" +
                            "Please check all input fields and ensure\nthey are all filled.");
                    verify_BTN.setEnabled(true);
                    save_STD.setEnabled(false);

                } else {
                    //first, remember that we only want the class ID, not the class name in the combo box soooo...
                    String classNames = "";
                    String classIdent = "";
                    int counter = 0;
                    while (!classAdmitted.equals(classNames)){//check for the class we selected in the array plus its corresponding class index in the other global array

                        classNames = getCLasses()[counter];
                        classIdent = classID[counter];
                        System.out.println( classAdmitted+" <=====> "+ getCLasses()[counter]+" | | "
                                +classIdent +" <=====> "+ classID[counter]);
                        counter++;

                    }//end while
                    //System.out.println("Yaay! Index is==> "+classIdent + " for "+  classAdmitted);

                    int ndani = addStudent(firstNames,otherNames, dateAdin, classIdent, house, cbx_gender.getSelectedItem().toString());//if the result is 1, the data was entered succeffully.

                    //verify if the insert was successful
                    if (ndani == 1){
                        JOptionPane.showMessageDialog(null, "Student has been added\n SUCCESSFULY!");
                        fName.setText("");
                        oNames.setText("");
                        //DOA.setText("");
                        String [] sBJ = subjects(classAdmitted);
                        int counter2 = subjects(classAdmitted).length;
                        String latestStudent = getRecentStudent();

                        for (int i = 0; i<counter2; i++){
                            autoAddSubject(latestStudent, sBJ[i]);
                        }//end forloop
                        JOptionPane.showMessageDialog(null, "All default subjects added!");
                    } else {
                        JOptionPane.showMessageDialog(null, "An error while saving has occurred.\n" +
                                "1. Check your connection to the database\n" +
                                "2. Contact your DB Admin or\n" +
                                "3. Try saving again");
                    }//end if

                    //switch the enabled and disabled buttons
                    verify_BTN.setEnabled(true);
                    save_STD.setEnabled(false);


                }//end if statement
            }
        });//end of save button

    }//end of the interface creator

    //other methods will go here

    //1. Load Available Classes into the combo box
    private String[] getCLasses(){
        String query = "select  Class_ID, Stream, Grad_Year from Classes where Available = 1 order by Grad_Year desc";
        String query2 = "select count(Stream) from Classes where Available = 1";
        int i = 0;//for initializing the array size of our class names holder
        int j = 0;//for checking the subjects we have
        String [] class_names = new String[i];


        //go into the database to seek the data we want
        try{
            Connection kon = DB_API.getKonnection();
            PreparedStatement stat = kon.prepareStatement(query);
            PreparedStatement stat2 = kon.prepareStatement(query2);

            //run the query
            ResultSet rs = stat.executeQuery();
            ResultSet rs2 = stat2.executeQuery();

            if(rs2.next() == true){
                i = Integer.parseInt(rs2.getString("count(Stream)"));
            }

            class_names = new String[i];
            classID = new String[i];
            int counter = 0;

            while (rs.next() == true){
                //get the values from the db
                String strl = rs.getString("Stream");
                String claID = rs.getString("Class_ID");
                int frm = 4-(Integer.parseInt(rs.getString("Grad_Year")) - 2010);//calculate the form
                String frm_ = String.valueOf(frm);//convert the form back to a string
                String to_ComboBox = frm_+ " " +strl;//concat to get a result like "4 South"

                //put it into our array
                class_names[counter] = to_ComboBox;
                classID[counter] = claID;
                //System.out.println("ClassID==> "+classID[counter]+"  Class===> "+class_names[counter]);
                counter++;

            }
            kon.close();

        } catch (SQLException ex){

            System.out.println(ex);
            ex.printStackTrace();

        }//end of Try... Catch


        return class_names;
        //return new String[0];
    }//end of getting the various classes available...

    //2. Save the details to the database
    private int addStudent(String firstname, String otherNames, String dateAdmitted, String classIndex, String hSe, String gender){
        int ret = 0;//it will tell us if the save was a success, by default it is not
        String query = "Insert into Student (Fname, Onames, Date_Admitted, Class_ID, House, Gender) values (?,?,?,?,?,?)";

        try{
            //set up the connection to the database using the connector class we created
            Connection kon = DB_API.getKonnection();
            PreparedStatement stat = kon.prepareStatement(query);

            //give the query its parameters
            stat.setString(1, firstname);
            stat.setString(2, otherNames);
            stat.setString(3, dateAdmitted);
            stat.setString(4, classIndex);
            stat.setString(5, hSe);
            stat.setString(6, gender);

            //run the query
            int i = stat.executeUpdate();
            if (i == 1){
                ret = 1;
                System.out.println("Save success");
            } else{
                ret = 0;
                System.out.println("Save FAILED");
            }
            kon.close();

        } catch (SQLException ex){
            System.out.println("Ni kubaya bro===>\n"+ex);
        }//end try... catch


        return ret;
    }//end of addStudent

    //get the most recently entered student
    private String getRecentStudent(){
        String adminNo = "";
        String query = "Select max(AdminNo) from Student";

        try {
            Connection kon = DB_API.getKonnection();
            PreparedStatement stat = kon.prepareStatement(query);

            ResultSet rs = stat.executeQuery();

            if(rs.next() == true){
                adminNo = rs.getString("max(AdminNo)");
                System.out.println(adminNo + " is the admin number we wanted");
            }
            kon.close();//close connection

        } catch (SQLException ex){
            ex.getErrorCode();
            ex.printStackTrace();
        }//end try... catch

        return adminNo;

    }//End of the get student IDs

    //Get the subjects
    private String [] subjects(String form){
        int arSize = 0;
        String [] subjectID = new String[arSize];
        String query = "";
        String query2 ="";

        if (form.contains("1") | form.contains("2")){
            query = "select Sub_ID, Importance from Subjects where Importance between 1 and 2";// order by Importance asc";
            query2 ="select count(Sub_ID) from Subjects where Importance between 1 and 2";
        } else if (form.contains("3") | form.contains("4")){
            query = "select Sub_ID, Importance from Subjects where Importance = 1";
            query2 = "select count(Sub_ID) from Subjects where Importance = 1";
        }//end if

        try{
            Connection kon = DB_API.getKonnection();
            PreparedStatement stat = kon.prepareStatement(query);
            PreparedStatement stat2 = kon.prepareStatement(query2);

            ResultSet rs = stat.executeQuery();
            ResultSet rs2 = stat2.executeQuery();

            while(rs2.next()){
                arSize = Integer.parseInt(rs2.getString("count(Sub_ID)"));
            }
            subjectID = new String[arSize];//set the size of our String array
            int counter = 0;//our counter

            while (rs.next()){
                subjectID[counter] = rs.getString("Sub_ID");
                System.out.println("The subject ID ===>"+ subjectID[counter]);
                counter++;
            }//end while

            //close connection
            kon.close();

        }catch (SQLException ex){
            ex.printStackTrace();
            ex.getSQLState();}


        return subjectID;
    }

    //Auto Add Some Subjects
    private int autoAddSubject(String adminNo, String subjID){
        int ndani = 0;
        String query = "insert into StudentSubjects (StudentID, SubjID, Active) values (?,?,?)";

        try{
            Connection kon = DB_API.getKonnection();
            PreparedStatement stat = kon.prepareStatement(query);

            stat.setString(1, adminNo);
            stat.setString(2, subjID);
            stat.setString(3, "1");

            ndani = stat.executeUpdate();

            if (ndani == 1){
                System.out.println("Student and Subject added!");
            } else {
                System.out.println("An error occured adding this one!");
            }//end if

            kon.close();



        }catch (SQLException ex){
            ex.printStackTrace();
            ex.getSQLState();
        }

        return ndani;
    }//end of subject Autoadd


}//end of class
