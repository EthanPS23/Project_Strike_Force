package sample.Model;

import java.util.Objects;

public class Class1 {

    /*
     * Title:  table object for bookingDetails tab
     * Date: April 05/2019
     * Author: James Cockriell
     */

    private String ClassId;
    private String ClassName;

    public Class1(String classId, String className) {
        ClassId = classId;
        ClassName = className;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String classId) {
        ClassId = classId;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    @Override
    public String toString() {
        return ClassName;
    }

    /*@Override
    public boolean equals(Object o) {
        String otherClassId="";
        if (o instanceof Class1){
            otherClassId = ((Class1)o).ClassId;
        } else if (o instanceof String){
            otherClassId = (String)o;
        } else {
            return false;
        }
        return true;
        *//*if (this == o) return true;
        if (!(o instanceof Class1)) return false;
        Class1 class1 = (Class1) o;
        return getClassId().equals(class1.getClassId()) &&
                getClassName().equals(class1.getClassName());*//*
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ClassId != null ? ClassId.hashCode() : 0);
        return hash;
        //return Objects.hash(getClassId(), getClassName());
    }*/
}
