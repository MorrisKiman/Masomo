package Updaters;

import Database_Links.DB_API;
import Database_Links.Kommon_Kweries;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student_Update extends JPanel{
    private JLabel jcomp1;
    private JButton btn_search;
    private JTextField txt_searchAdmin;
    private JLabel jcomp4;
    private JComboBox cbx_select;
    private JTextField jcomp6;
    private JLabel jcomp7;
    private JLabel jcomp8;
    private JLabel jcomp9;
    private JLabel jcomp10;
    private JLabel lbl_adminNo;
    private JTextField txt_FIrstName;
    private JTextField txt_OtherNaames;
    private JLabel jcomp14;
    private JComboBox cbx_Classes;
    private JTextField jcomp16;
    private JLabel jcomp17;
    private JButton btn_SaveDets;
    private JList list_electives;
    private JList list_mandatories;
    private JLabel jcomp21;
    private JLabel jcomp22;
    private JComboBox cbx_subTypes;
    private JList list_NewSubs;
    private JLabel jcomp25;
    private JButton btn_saveSubjects;
    private JLabel jcomp27;
    private JComboBox cbx_Gender;

    Kommon_Kweries comQueries = new Kommon_Kweries();

    public static void main (String[]args){
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Student_Update());
        frame.pack();
        frame.setLocationRelativeTo(null);//sets the location of the JFrame to the center of the screen
        frame.setVisible (true);

    }//end of main method

    //lets build the UI
    private Student_Update(){
        //construct preComponents
        String[] jcomp5Items = {"Edit Details", "Edit Subjects"};
        String[] cbx_ClassesItems = comQueries.getCLasses();
        String[] list_mandatoriesItems = {};
        String[] cbx_subTypesItems = {"Electives", "Optionals"};
        String[] list_electivesItems = {};
        String[] list_NewSubsItems = {"Item 1", "Item 2", "Item 3"};
        String[] cbx_GenderItems = {"M", "F"};

        //construct components
        jcomp1 = new JLabel ("Edit Student Details");
        btn_search = new JButton ("Search");
        txt_searchAdmin = new JTextField (5);
        jcomp4 = new JLabel ("Select Action:");
        cbx_select = new JComboBox (jcomp5Items);
        jcomp6 = new JTextField (5);
        jcomp7 = new JLabel ("Student Details");
        jcomp8 = new JLabel ("First Name:");
        jcomp9 = new JLabel ("Other Names:");
        jcomp10 = new JLabel ("Admission Number:");
        lbl_adminNo = new JLabel ("|-x-x-x-x-|");
        txt_FIrstName = new JTextField (5);
        txt_OtherNaames = new JTextField (5);
        jcomp14 = new JLabel ("Class:");
        cbx_Classes = new JComboBox (cbx_ClassesItems);
        jcomp16 = new JTextField (5);
        jcomp17 = new JLabel ("Subjects taken by the Student");
        btn_SaveDets = new JButton ("Save Changes");
        list_electives = new JList (list_electivesItems);
        list_mandatories = new JList (list_mandatoriesItems);
        jcomp21 = new JLabel ("Mandatory");
        jcomp22 = new JLabel ("Current Subjects");
        cbx_subTypes = new JComboBox (cbx_subTypesItems);
        list_NewSubs = new JList (list_NewSubsItems);
        jcomp25 = new JLabel ("List Subjects:");
        btn_saveSubjects = new JButton ("Save Subject Changes");
        jcomp27 = new JLabel ("Gender:");
        cbx_Gender = new JComboBox (cbx_GenderItems);

        DefaultListModel listModel = new DefaultListModel();
        DefaultListModel modelMandatory = new DefaultListModel();
        DefaultListModel newSubModel = new DefaultListModel();
        list_electives.setModel(listModel);
        list_mandatories.setModel(modelMandatory);
        list_NewSubs.setModel(newSubModel);
        //list_NewSubs.setModel();

        //set components properties
        jcomp6.setEnabled (false);
        jcomp16.setEnabled (false);
        btn_SaveDets.setEnabled (false);
        btn_saveSubjects.setEnabled(false);
        list_electives.setToolTipText ("select a subjects to delete it");
        list_mandatories.setToolTipText ("These are mandatory subjects. They can't be changed");
        cbx_subTypes.setToolTipText ("Select the type of subjects you want to add");
        list_NewSubs.setToolTipText ("Select a subject to add");
        setBackground(Color.gray);

        //adjust size and set layout
        setPreferredSize (new Dimension(666, 531));
        setLayout (null);

        //add components
        add (jcomp1);
        add (btn_search);
        add (txt_searchAdmin);
        add (jcomp4);
        add (cbx_select);
        add (jcomp6);
        add (jcomp7);
        add (jcomp8);
        add (jcomp9);
        add (jcomp10);
        add (lbl_adminNo);
        add (txt_FIrstName);
        add (txt_OtherNaames);
        add (jcomp14);
        add (cbx_Classes);
        add (jcomp16);
        add (jcomp17);
        add (btn_SaveDets);
        add (list_electives);
        add (list_mandatories);
        add (jcomp21);
        add (jcomp22);
        add (cbx_subTypes);
        add (list_NewSubs);
        add (jcomp25);
        add (btn_saveSubjects);
        add (jcomp27);
        add (cbx_Gender);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (250, 0, 145, 30);
        btn_search.setBounds (318, 35, 100, 25);
        txt_searchAdmin.setBounds (422, 35, 120, 25);
        jcomp4.setBounds (10, 35, 100, 25);
        cbx_select.setBounds (105, 35, 139, 25);
        jcomp6.setBounds (0, 70, 680, 6);
        jcomp7.setBounds (245, 76, 112, 25);
        jcomp8.setBounds (240, 100, 82, 25);
        jcomp9.setBounds (5, 130, 100, 25);
        jcomp10.setBounds (10, 100, 141, 25);
        lbl_adminNo.setBounds (145, 100, 100, 25);
        txt_FIrstName.setBounds (320, 100, 168, 25);
        txt_OtherNaames.setBounds (100, 130, 217, 25);
        jcomp14.setBounds (320, 130, 48, 25);
        cbx_Classes.setBounds (360, 130, 98, 25);
        jcomp16.setBounds (0, 189, 681, 6);
        jcomp17.setBounds (221, 195, 218, 25);
        btn_SaveDets.setBounds (285, 160, 135, 25);
        list_electives.setBounds (210, 265, 156, 185);
        list_mandatories.setBounds (27, 265, 156, 185);
        jcomp21.setBounds (57, 230, 85, 25);
        jcomp22.setBounds (224, 230, 119, 25);
        cbx_subTypes.setBounds (480, 230, 105, 25);
        list_NewSubs.setBounds (395, 265, 156, 185);
        jcomp25.setBounds (386, 230, 100, 25);
        btn_saveSubjects.setBounds (180, 475, 244, 27);
        jcomp27.setBounds (500, 100, 58, 25);
        cbx_Gender.setBounds (555, 100, 100, 25);

        //let the fun begin..

        //1. Activate the save button for the action you want to take
        cbx_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(cbx_select.getSelectedIndex()==0){
                    btn_SaveDets.setEnabled(true);
                    btn_saveSubjects.setEnabled(false);

                } else if (cbx_select.getSelectedIndex()==1){
                    btn_saveSubjects.setEnabled(true);
                    btn_SaveDets.setEnabled(false);

                }//end of if statement
            }
        });//end of selection combo action

        //2. Search for the student that we have added..
        btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //empty the list boxes
                //list_electives.setModel(null);
                modelMandatory.clear();
                listModel.clear();
                newSubModel.clear();
                lbl_adminNo.setText("|-x-x-x-x-|");
                txt_FIrstName.setText("");
                txt_OtherNaames.setText("");

                if (txt_searchAdmin.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "Please enter the Admission number of\nthe student whose details\nyou wish to edit");

                } else{
                    int start = getStudentDetails(Integer.parseInt(txt_searchAdmin.getText()));
                    String [][] theSubjects = comQueries.getSubjects(txt_searchAdmin.getText());

                    System.out.println("The length of the array table is===> "+theSubjects.length);
                    String [] toMandatoryList = new String[theSubjects.length];

                    for (int i = 0; i < theSubjects.length; i++){

                    }
                    //empty the list boxes
                    //list_electives.setModel(null);
                    modelMandatory.clear();
                    listModel.clear();
                    newSubModel.clear();


                    //get the subjects and load them into the list boxes
                    System.out.println("\n\n");
                    for (int i = 0; i < theSubjects.length; i++){
                        System.out.println("List Length "+theSubjects.length);
                        System.out.println("Column Length "+theSubjects[i].length);

                        for (int r = 0; r < theSubjects[i].length; r++){

                            System.out.println(i+" <=Col_index Row_Index=> "+r+" Content=> "+theSubjects[i][r]);

                        }
                        System.out.println("\n");
                    }//end for

                    //loading the searched student's subjects into the lists on the UI
                    String [] aalSbj = new String[theSubjects[0].length];
                    int counter1 = 0;
                    int counter2 = 0;

                    for (int r = 0; r < theSubjects[0].length; r++){


                        if (theSubjects[2][r].contains("1")){
                            modelMandatory.add(counter1,theSubjects[0][r]); //list of mandatory subjects
                            counter1++;
                        } else {
                            listModel.add(counter2, theSubjects[0][r]); //list of optional subjects
                            counter2++;
                        }

                        //(theSubjects[0][r]);

                    }//end for
                    //list_electives = new JList();

                    if(start == 0){
                        JOptionPane.showMessageDialog(null, "Something went wrong");

                    } else if (start == 2){
                        JOptionPane.showMessageDialog(null, "The student was not found!\nPlease recheck the admission number\nand try again.");

                    }//end if
                }
            }
        });//end of searching for student

        //After loading subjects done by students to the lists, lets decide what to do with them

        //1. Remove the subject selected
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {


                    String selectedItem = (String) list_electives.getSelectedValue();
                    int selectedinx = list_electives.getSelectedIndex();
                    System.out.println("Selected Item = "+ selectedItem + "\nSelected Index = "+ selectedinx);

                    JOptionPane.showMessageDialog(null, "Are you sure you want to remove \n"
                            +selectedItem+ " from "+txt_FIrstName.getText()+"'s\n" +
                            "subjects list?");

                }
            }
        };//mouse listener thingy

        list_electives.addMouseListener(mouseListener);


        //end of optionals list clicking

        //2. On item change on the combobox, we should see a change in the subjects
        cbx_subTypes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String [][] byImportance = new String[2][];
                if (cbx_subTypes.getSelectedIndex()==0){
                    String importance = "3";
                    comQueries.getSpecSubjects(importance);
                   // byImportance = new String[2][comQueries.getSpecSubjects(importance)[].size()];

                } else if (cbx_subTypes.getSelectedIndex()==1){
                    String importance = "2";
                    //fill the JList with elective subjects
                    comQueries.getSpecSubjects(importance);
                }
            }
        });
        //end of getting the elective/optional subjects

    }//end of UI creation

    //handling all the SQL stuff

    //1. Gimme the basic derails of the searched student
    private int getStudentDetails (int admino){
        int ret = 0;
        String query = "select * from Student where AdminNo = ?";

        try {
            Connection kon = DB_API.getKonnection();
            PreparedStatement stat = kon.prepareStatement(query);

            stat.setString(1, String.valueOf(admino));

            //run dis ting
            ResultSet rs = stat.executeQuery();
            if(rs.next()){
                lbl_adminNo.setText(rs.getString("AdminNo"));
                txt_FIrstName.setText(rs.getString("Fname"));
                txt_OtherNaames.setText(rs.getString("Onames"));
                System.out.print("\t|| "+rs.getString("House")+"\n");
                String er = comQueries.getCLasses(rs.getString("Class_ID"));
                System.out.println("The Class Is==>" +er);



                ret = 1;

            } else {
                ret = 2;
            }//end if
        } catch (SQLException ex){

            ex.getSQLState();
            ex.printStackTrace();

        }//end of Try catch

        return ret;
    }//end of getting the student's details

    //2. Add or delete a subject from the student's list of subjects.
    private boolean subjectChange(String changeType, String subj_ID){
        String query1 = "delete this subject from the list of subjects of this guy";
        String query2 = "add this subject to the list of subjects of this guy";
        boolean successful = false;

        return successful;

    }//end of editing the subjects details


}//end class