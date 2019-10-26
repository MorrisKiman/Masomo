package Database_Links;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Kommon_Kweries {
    static String []classID;

    public String[] getCLasses() {
        String query = "select  Class_ID, Stream, Grad_Year from Classes where Available = 1 order by Grad_Year desc";
        String query2 = "select count(Stream) from Classes where Available = 1";
        int i = 0;//for initializing the array size of our class names holder
        int j = 0;//for checking the subjects we have
        String[] class_names = new String[i];


        //go into the database to seek the data we want
        try {
            Connection kon = DB_API.getKonnection();
            PreparedStatement stat = kon.prepareStatement(query);
            PreparedStatement stat2 = kon.prepareStatement(query2);

            //run the query
            ResultSet rs = stat.executeQuery();
            ResultSet rs2 = stat2.executeQuery();

            if (rs2.next() == true) {
                i = Integer.parseInt(rs2.getString("count(Stream)"));
            }

            class_names = new String[i];
            classID = new String[i];
            int counter = 0;

            while (rs.next() == true) {
                //get the values from the db
                String strl = rs.getString("Stream");
                String claID = rs.getString("Class_ID");
                int frm = 4 - (Integer.parseInt(rs.getString("Grad_Year")) - 2010);//calculate the form
                String frm_ = String.valueOf(frm);//convert the form back to a string
                String to_ComboBox = frm_ + " " + strl;//concat to get a result like "4 South"

                //put it into our array
                class_names[counter] = to_ComboBox;
                classID[counter] = claID;
                //System.out.println("ClassID==> "+classID[counter]+"  Class===> "+class_names[counter]);
                counter++;

            }
            kon.close();

        } catch (SQLException ex) {

            System.out.println(ex);
            ex.printStackTrace();

        }//end of Try... Catch


        return class_names;
    }//end of getting all classes

    //get specific class
    public String getCLasses(String classID) {
        String class_names = "";
        String query = "select  Class_ID, Stream, Grad_Year from Classes where Class_ID = ? order by Grad_Year desc";

        //go into the database to seek the data we want
        try {
            Connection kon = DB_API.getKonnection();
            PreparedStatement stat = kon.prepareStatement(query);
            stat.setString(1, classID);

            //run the query
            ResultSet rs = stat.executeQuery();

            while (rs.next() == true) {
                //get the values from the db
                String strl = rs.getString("Stream");
                String claID = rs.getString("Class_ID");
                int frm = 4 - (Integer.parseInt(rs.getString("Grad_Year")) - 2010);//calculate the form
                String frm_ = String.valueOf(frm);//convert the form back to a string
                class_names = frm_ + " " + strl;//concat to get a result like "4 South"

            }
            kon.close();

        } catch (SQLException ex) {

            System.out.println(ex);
            ex.printStackTrace();

        }//end of Try... Catch


        return class_names;
    }//end of getting specific class

    //3. get a student's subjects
    public  String[][] getSubjects(String adminNo){
        int nO = 0;
        String[][] myList = new String[0][];

        String query = "select StudentID, SubjID, Active, Subjects.Sub_Name, Subjects.Importance\n" +
                "       from StudentSubjects inner join Subjects on\n" +
                "       Subjects.Sub_ID = StudentSubjects.SubjID where StudentSubjects.StudentID = ?";
        String query2 = "select count(*) from StudentSubjects where StudentID = ?";
        try{
            Connection kon = DB_API.getKonnection();
            PreparedStatement stat = kon.prepareStatement(query);
            PreparedStatement stat2 = kon.prepareStatement(query2);

            stat.setString(1, adminNo);
            stat2.setString(1, adminNo);

            //execute query
            ResultSet rs = stat.executeQuery();
            ResultSet rs2 = stat2.executeQuery();

            //1. get the number of student our student is doing to set our list's array size
            if(rs2.next()==true){
                nO = rs2.getInt("count(*)");
                System.out.println("The number of subjects is===> "+ nO);
            }//end if

            myList = new String[3][nO];
            int counter = 0;
            int counter1 = 0;

            //2. start statcking up
            while (rs.next()==true ){
                myList[counter][counter1] = rs.getString("Sub_Name");
                myList[counter+1][counter1] = rs.getString("SubjID");
                myList[counter+2][counter1] = rs.getString("Importance");

                counter1++;
                counter = 0;

                }//end while

            for (int i=0; i< myList.length; i++){
                for(int r = 0; r < myList[i].length; r++){
                    System.out.println(i+" <=Col_index Row_Index=> "+r+" Content=> "+myList[i][r]);
                }
                System.out.println("\nNext..");
            }


        }catch (SQLException ex){
            System.out.println("Noma bro==>\n"+ex);
            ex.printStackTrace();
        }//end try catch
        
        return myList;

    }//end of getting subjects


}//end class