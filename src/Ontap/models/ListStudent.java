package Ontap.models;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

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
            return "New student's ID (" + newStudent.getID() + ") is not unique !!";

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
        double GPA = Double.valueOf(data[2]).doubleValue();
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
    public void viewStudent(){
        for (int i = 0; i < dataStudent.size(); i++)
            dataStudent.get(i).showInforStudent();
    }

    public int getOrderOneStudent(String ID){
        for (int i = 0; i < dataStudent.size(); i++)
            if (dataStudent.get(i).getID().equals(ID))
                return i;

        return -1;
    }

    /**
     * Delete one student on list
     */
    public void deleteStudent(){
        String idDel = "";
        boolean isFirst = true;
        int orderNumber;
        while (true) {
            Scanner sc = new Scanner(System.in);
            viewStudent();
            if (!isFirst)
                System.out.println("ID " + idDel + " is not exists !! Choose again");

            System.out.print("Press ID student you want to remove : ");
            idDel = sc.nextLine();

            orderNumber = getOrderOneStudent(idDel);
            if (orderNumber != -1)
                break;

            isFirst = false;
        }

        System.out.println("Delete Successfully student with ID " + idDel);

        dataStudent.remove(orderNumber);
    }

    /**
     * Write data student to file TXT
     * @param fileName
     * @throws IOException
     */
    public void writeStudentToFileTXT(String fileName) throws IOException {

        PrintStream ps = new PrintStream(fileName);
        for (int i = 0; i < dataStudent.size(); i++)
            dataStudent.get(i).writeInforToFileTxt(ps);

        ps.close();
    }

    /**
     * Write student to file CSV
     * @param fileName
     * @throws FileNotFoundException
     */
    public void writeStudentToFileCSV(String fileName) throws FileNotFoundException {

        PrintStream ps = new PrintStream(fileName);
        ps.println("ID,Name,GPA,image,address,notes");
        for (int i = 0; i < dataStudent.size(); i++)
            ps.println(dataStudent.get(i).convertToCSVString());
        ps.close();
    }

    /**
     * Import student from file txt
     * @param fileName
     * @throws IOException
     */
    public void importStudentFromFileTXT(String fileName) throws IOException {
        dataStudent.clear();
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
        BufferedReader br = null;

        if (in == null) {
            fileName = "file/" + fileName;
            br = new BufferedReader(new FileReader(fileName));
        }
        else
            br = new BufferedReader(new InputStreamReader(in));

        while (true){
            String id = br.readLine();
            if (null == id)
                break;

            String name = br.readLine();
            double gpa = Double.valueOf(br.readLine()).doubleValue();
            String image = br.readLine();
            String address = br.readLine();
            String Notes = br.readLine();
            br.readLine();

            Student newStudent = new Student(id, name, image, address, Notes, gpa);

            dataStudent.add(newStudent);
        }
        br.close();
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
    boolean isUpdatedDataValid(int order){
        boolean isValid = true;
        for (int i = 0; i < dataStudent.size(); i++)
            if (i != order && dataStudent.get(i).getID().equals(dataStudent.get(order).getID())){
                isValid = false;
                System.out.println("Updated Student's ID (" + dataStudent.get(order).getID() + ") is not unique");
                break;
            }

        if (dataStudent.get(order).getGPA() > 4.0 || dataStudent.get(order).getGPA() < 0){
            isValid = false;
            System.out.println("Updated student's GPA (" + dataStudent.get(order).getGPA() +
                    " is out of range (0 -> 4)");
        }

        return isValid;
    }

    /**
     * Update student
     */
    public void updateStudent() {
        Scanner sc = new Scanner(System.in);

        String idUpdate = null;

        System.out.println("There are list of student : ");
        viewStudent();
        System.out.print("Press Student's ID who you want to update : ");
        idUpdate = sc.nextLine();

        int order = getOrderOneStudent(idUpdate);
        if (order == -1)
            System.out.println("ID You press (" + idUpdate + ") is not valid");
        else {
            boolean isSuccess = dataStudent.get(order).updateInforStudent();
            if (!isUpdatedDataValid(order)) {
                System.out.println("Updated data is not valid !! ");
            }
            else if (isSuccess)
                System.out.println("Successfully update data");
        }


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