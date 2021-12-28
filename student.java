package learning.management.system;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class student extends User {

    private String name;
    private String password;
    private int ID;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    public void view(JTextArea jt, String path) throws FileNotFoundException {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                jt.append(data + "\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public int Enter(String CourseName, String path) throws FileNotFoundException, IOException {

        int returnValue = 0;
        File myobj = new File(path);
        Scanner myReader = new Scanner(myobj);

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            if (data.equals(CourseName)) {
                returnValue = 1;
                break;
            }
        }
        myReader.close();
        return returnValue;
    }

    public void dropCourse(String lineToRemove, int userId) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("Courses\\" + userId + "\\Courses.txt"))) {
            String allData = "";
            int userExists = 0;
            String line;
            int x = 0;
            while ((line = br.readLine()) != null) {
                if (line.equals(String.valueOf(lineToRemove))) {
                    userExists = 1;
                } else {
                    if (x == 0) {
                        allData = allData + line;
                        x++;
                    } else {
                        allData = allData + "\n" + line;
                    }
                }
            }
            FileWriter writer = new FileWriter("Courses\\" + userId + "\\Courses.txt", false);
            writer.write(allData);
            writer.close();
        }
    }

    static void deleteFolder(File file) {
        for (File subFile : file.listFiles()) {
            if (subFile.isDirectory()) {
                deleteFolder(subFile);
            } else {
                subFile.delete();
            }
        }
        file.delete();
    }

    public void Regeister(String s, int userId) throws IOException {
        int returnValue = 0;
        try {
            File myObj = new File("Courses/"+userId+"/Courses.txt");//available
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.equals(s)) {
                    returnValue = 1;
                    break;
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        if (returnValue != 1) {
            FileWriter fw = new FileWriter("Courses/"+userId+"/Courses.txt", true);//courses
            BufferedWriter bw = new BufferedWriter(fw);
            File f = new File("Courses/"+userId+"/Courses.txt");//courses
            if (f.length() == 0) {
                bw.write(s);

            } else {
                bw.write("\n" + s);

            }

            //bw.newLine();
            bw.close();
        }

    }

    public int EnterCourse(String s,int userId) {
        int returnValue = 0;
        int CourseRegeistered = 0;
        try {
            File myObj = new File("Courses/"+userId+"/Courses.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.equals(s)) {
                    CourseRegeistered = 1;
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        if (CourseRegeistered == 1){
            try {
            File myObj = new File("LecturersAndCourses.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String course = data.split(",")[1];
                int id = Integer.valueOf(data.split(",")[0]);
                if (course.equals(s)) {
                    returnValue = id;
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        }
        return returnValue;
    }
}
