package Data_Classes;

public class ExamCreation {
    private String exam_ID;
    private String schoolTerm;
    private String examTite;
    private String year;

    //constructors


    public ExamCreation() {
    }//end of empty constructor

    public ExamCreation(String exam_ID, String schoolTerm, String examTite, String year) {
        this.exam_ID = exam_ID;
        this.schoolTerm = schoolTerm;
        this.examTite = examTite;
        this.year = year;
    }//end of constructor

    //setters and getters

    //1. Setters

    public void setExam_ID(String exam_ID) {
        this.exam_ID = exam_ID;
    }

    public void setSchoolTerm(String schoolTerm) {
        this.schoolTerm = schoolTerm;
    }

    public void setExamTite(String examTite) {
        this.examTite = examTite;
    }

    public void setYear(String year) {
        this.year = year;
    }

    //2. Getters


    public String getExam_ID() {
        return exam_ID;
    }

    public String getSchoolTerm() {
        return schoolTerm;
    }

    public String getExamTite() {
        return examTite;
    }

    public String getYear() {
        return year;
    }
}//end class
