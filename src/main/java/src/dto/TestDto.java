package src.dto;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class TestDto {

    private int id;
    private Date date;
    private int grade;

    public static void printTestTuple(Tuple tuple) {
        Date date = null;
        Integer grade = null;
        String fName = "";
        String sName = "";
        String subjectName = "";
        try {
            date = (Date) tuple.get(0);
            grade = (Integer) tuple.get(1);
            fName = (String) tuple.get(2);
            sName = (String) tuple.get(3);
            subjectName = (String) tuple.get(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("\tTest(date=%s, grade=%d, studentFName=%s, studentSName=%s, subjectName=%s)\n",
                date, grade, fName, sName, subjectName);
    }
}