package Ontap.models;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class Student {
    private String ID, Name, Image, Address, Notes;
    private double GPA;

    public String getName() {
        return Name;
    }

    public String getImage() {
        return Image;
    }

    public String getAddress() {
        return Address;
    }

    public String getNotes() {
        return Notes;
    }

    /**
     * Default Constructor
     */
    public Student(){

    }

    /**
     * Constructor with many parameter
     * @param ID
     * @param name
     * @param image
     * @param address
     * @param notes
     * @param GPA
     */
    public Student(String ID, String name, String image, String address, String notes, double GPA) {
        this.ID = ID;
        Name = name;
        Image = image;
        Address = address;
        Notes = notes;
        this.GPA = GPA;
    }

    public Student(String csvString){
        String[] strArray = csvString.split(",");
        ID = strArray[0];
        Name = strArray[1];
        GPA = Double.valueOf(strArray[2]).doubleValue();
        Image = strArray[3];
        Address = strArray[4];
        Notes = strArray[5];
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Press information from keyboard to data for student
     */
    public void setInforStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Fill Student's Information");
        System.out.print("1. ID : ");
        ID = sc.nextLine();

        System.out.print("2. Name : ");
        Name = sc.nextLine();

        System.out.print("3. GPA : ");
        GPA = sc.nextDouble();

        sc.nextLine();

        System.out.print("4. Image : ");
        Image = sc.nextLine();

        System.out.print("5. Address : ");
        Address = sc.nextLine();

        System.out.print("6. Notes : ");
        Notes = sc.nextLine();
    }

    /**
     * Print information of student to console
     */
    public void showInforStudent(){
        System.out.println("----------------------------");
        System.out.println("ID : " + ID);
        System.out.println("Name : " + Name);
        System.out.println("GPA : " + GPA);
        System.out.println("Image : " + Image);
        System.out.println("Address : " + Address);
        System.out.println("Notes : " + Notes);
        System.out.println("----------------------------");
    }

    /**
     * Update information for student
     */

    public void updateInforStudent(String ID, String name, String image, String address, String notes, double GPA) {
        this.ID = ID;
        Name = name;
        Image = image;
        Address = address;
        Notes = notes;
        this.GPA = GPA;
    }

    /**
     *
     * @param bw
     * @throws IOException
     */

    public void writeInforToFileTxt(BufferedWriter bw) throws IOException {
        bw.write(ID);
        bw.newLine();
        bw.write(Name);
        bw.newLine();
        bw.write(String.valueOf(GPA));
        bw.newLine();
        bw.write(Image);
        bw.newLine();
        bw.write(Address);
        bw.newLine();
        bw.write(Notes);
        bw.newLine();
        bw.newLine();
    }

    /**
     * Convert information to CSV String
     * @return
     */
    public String convertToCSVString(){
        String str = ID + "," + Name + "," + String.valueOf(GPA) + "," + Image
                + "," + Address + "," + Notes;

        return str;
    }


    /**
     * Get GPA
     * @return GPA value
     */
    public double getGPA() {
        return GPA;
    }

    public void readFile(String fileName) throws IOException {

        System.out.println(getClass().getClassLoader().getResource(fileName));
    }
}