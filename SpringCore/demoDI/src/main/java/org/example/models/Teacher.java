package org.example.models;

public class Teacher {

    private Student student;

    public Teacher(){
        student = new Student("Mai Van Dang", 10f);
    }

    public Teacher(Student student) {
        this.student =student;
    }

    public Student getStudent(){
        return student;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public float getStudentMark(){
        return student.getMark();
    }

    @Override
    public String toString(){
        return "Teacher{" +
                "student=" + student +
                " }";
    }
}
