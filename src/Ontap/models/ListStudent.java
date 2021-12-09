package Ontap.models;

import Ontap.utils.DBSinhVien;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ListStudent {
    private ArrayList<Student> dataStudent;


    /**
     * Constructor with parameter
     * @param dataStudent
     */
    public ListStudent(ArrayList<Student> dataStudent) {
        this.dataStudent = dataStudent;
    }

    /**
     * Default constructor
     */
    public ListStudent(){
        dataStudent = new ArrayList<Student>();
    }

    public String isNewDataValid(Student newStudent){
        boolean isValid = true;
        // Check Identical ID
        for (int i = 0; i < dataStudent.size(); i++)
            if (dataStudent.get(i).getID().equals(newStudent.getID()))
                isValid = false;

        if (!isValid)
            return "New student's ID (" + newStudent.getID() + ") is Exists !!";

        //Check range of GPA
        if (newStudent.getGPA() > 4 || newStudent.getGPA() < 0)
            return "New student's GPA (" + newStudent.getGPA() + ") is out of range (0 -> 4)";

        return "";
    }



    /**
     * Add new student to list
     */
    public String addStudent(String[] data){
        String ID = data[0];
        String Name = data[1];
        double GPA = 0;
        try {
            GPA = Double.valueOf(data[2]).doubleValue();
        }
        catch (NumberFormatException e)
        {
            return "GPA is not number";
        }


        String Address = data[3];
        String Img = data[4];
        String Note = data[5];

        Student newStudent = new Student(ID, Name,  Img,  Address,  Note,  GPA);
        if (isNewDataValid(newStudent).equals(""))
        {
            dataStudent.add(newStudent);
            return "";
        }

        return isNewDataValid(newStudent);
    }

    /**
     * View all student on list
     */

    public int getOrderOneStudent(String ID){
        for (int i = 0; i < dataStudent.size(); i++)
            if (dataStudent.get(i).getID().equals(ID))
                return i;

        return -1;
    }

    /**
     * Delete one student on list
     */
    public void deleteStudent(String ID){
        int orderNumber = getOrderOneStudent(ID);
        dataStudent.remove(orderNumber);
    }

    /**
     *
     * @param file
     * @throws IOException
     */
    public void writeStudentToFileTXT(File file) throws IOException {

        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file),"UTF-8"));

        for (int i = 0; i < dataStudent.size(); i++)
            dataStudent.get(i).writeInforToFileTxt(bw);

        bw.close();
    }

    /**
     *
     * @param file
     * @throws IOException
     */
    public void writeStudentToFileCSV(File file) throws IOException {

        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file),"UTF-8"));



        bw.write("ID,Name,GPA,image,address,notes");
        bw.newLine();

        for (int i = 0; i < dataStudent.size(); i++) {
            bw.write(dataStudent.get(i).convertToCSVString());
            bw.newLine();
        }

        bw.close();

    }


    /**
     * Add new student to file
     * @param fileName
     * @throws IOException
     */
    public void importStudentFromFileCSV(String fileName) throws IOException {
        dataStudent.clear();

        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
        BufferedReader br = null;

        if (in == null) {
            fileName = "file/" + fileName;
            br = new BufferedReader(new FileReader(fileName));
        }
        else
            br = new BufferedReader(new InputStreamReader(in));
        br.readLine();

        while (true){
            String csvStr = br.readLine();
            if (csvStr == null)
                break;

            Student newStudent = new Student(csvStr);

            dataStudent.add(newStudent);
        }
        br.close();
    }

    /**
     * Check the valid data of updated
     * @param order
     * @return
     */
    public String isUpdatedDataValid(int order, String updateID, double GPA){
        boolean isValid = true;
        for (int i = 0; i < dataStudent.size(); i++)
            if (i != order && dataStudent.get(i).getID().equals(updateID)){
                isValid = false;
                return "Updated Student's ID (" + updateID + ") is Exist !!";
            }

        if (GPA > 4.0 || GPA < 0){
            isValid = false;
            return "Updated student's GPA (" + GPA + " is out of range (0 -> 4)";
        }

        return "";
    }

    /**
     * Update student
     */
    public String updateStudent(String[] data, String oldID) {
        String ID = data[0];
        String Name = data[1];
        double GPA = 0;
        try {
            GPA = Double.valueOf(data[2]).doubleValue();
        }
        catch (NumberFormatException e)
        {
            return "GPA is not number";
        }


        String Address = data[3];
        String Img = data[4];
        String Note = data[5];

        int order = getOrderOneStudent(oldID);
        String str = isUpdatedDataValid(order, ID, GPA);
        if (str.equals(""))
        {
            dataStudent.get(order).updateInforStudent(ID , Name, Img, Address, Note, GPA);
            return "";
        }

        return str;

    }

    /**
     * Check data is empty ?
     * @return true/false
     */
    public boolean isEmpty(){
        if (dataStudent.size() == 0)
            return true;

        return false;
    }

    public void exportDataToDatabase(DBSinhVien dbSinhVien) {
        for (Student student : dataStudent){
            String ID = student.getID();
            String Name = student.getName();
            double GPA = student.getGPA();
            String Address = student.getAddress();
            String Note = student.getNotes();
            String Img = student.getImage();

            dbSinhVien.insertData(ID, Name, GPA, Address, Note, Img);
        }
    }

    public void resetData(){
        dataStudent = new ArrayList<Student>();
        System.out.println("Reset Data");
    }
}

class compareAscendingID implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        int IDo1 = Integer.valueOf(o1.getID()).intValue();
        int IDo2 = Integer.valueOf(o2.getID()).intValue();

        if (IDo1 > IDo2)
            return 1;
        else if (IDo1 == IDo2)
            return 0;
        else
            return -1;
    }
}

class compareAscendingGPA implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        Double GPAo1 = o1.getGPA();
        Double GPAo2 = o2.getGPA();
        if (GPAo1 > GPAo2)
            return 1;
        else if (GPAo1 == GPAo2)
            return 0;
        else
            return -1;
    }
}