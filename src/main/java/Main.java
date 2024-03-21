import dataBase.dbOperations;
import dbFamework.dbFramework;

public class Main {
    public static void main(String[] args) {
        dbOperations.createTable();
        dbOperations.insertStudent();
        dbOperations.selectStudent(2);
        dbOperations.updateStudent("Alexey", 15);
        System.out.println();
        System.out.println();
        System.out.println();
        dbOperations.selectStudent(0);
        dbOperations.deleteStudent(4);
        dbOperations.deleteStudent(3);
        dbOperations.deleteStudent(2);
        System.out.println();
        System.out.println();
        dbOperations.selectStudent(0);


    }
}
