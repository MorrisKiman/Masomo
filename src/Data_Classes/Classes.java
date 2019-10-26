package Data_Classes;

public class Classes {
    private String class_ID;
    private String stream;
    private String grad_Year;
    private String glass_Teacher;
    private String available;

    public Classes(String class_ID, String stream, String grad_Year, String glass_Teacher, String available) {
        this.class_ID = class_ID;
        this.stream = stream;
        this.grad_Year = grad_Year;
        this.glass_Teacher = glass_Teacher;
        this.available = available;
    }//end of full constructor

    public Classes() {

    }//end of empty constructor

    //setters

    public void setClass_ID(String class_ID) {
        this.class_ID = class_ID;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public void setGrad_Year(String grad_Year) {
        this.grad_Year = grad_Year;
    }

    public void setGlass_Teacher(String glass_Teacher) {
        this.glass_Teacher = glass_Teacher;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    //getters

    public String getClass_ID() {
        return class_ID;
    }

    public String getStream() {
        return stream;
    }

    public String getGrad_Year() {
        return grad_Year;
    }

    public String getGlass_Teacher() {
        return glass_Teacher;
    }

    public String getAvailable() {
        return available;
    }

    //any methods will go here
}//end of class
