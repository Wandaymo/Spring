package br.com.wandaymo.awesome.model;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    public static List<Student> studentList;
    
    static {
        studentResitory();
    }
    
    public Student(int id, String name) {
        this(name);
        this.id = id;
    }
    
    public Student(String name) {
        this.name = name;
    }

    public Student(){
    }
    
    private static void studentResitory() {
        studentList = new ArrayList<>(asList(new Student(1, "Wandaymo"), new Student(2, "Radynalva")));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static List<Student> getStudentList() {
        return studentList;
    }

    public static void setStudentList(List<Student> studentList) {
        Student.studentList = studentList;
    }
    
    
}
