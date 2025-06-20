package Application.ModelClass;

import javafx.beans.property.SimpleStringProperty;

public class Students {
    SimpleStringProperty studentId;
    SimpleStringProperty name;
    SimpleStringProperty dateOfBirth;

    public Students(String studentId, String name, String dateOfBirth) {
        this.studentId = new SimpleStringProperty(studentId);
        this.name = new SimpleStringProperty(name);
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
    }

    public String getStudentId() {
        return studentId.get();
    }
    public void setStudentId(String studentId) {
        this.studentId = new SimpleStringProperty(studentId);
    }

    public String getName() {
        return name.get();
    }  
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
    }
}
