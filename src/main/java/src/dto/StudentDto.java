package src.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentDto extends PersonDto implements Serializable {

    private int indexNo;
    private int semester;

    public StudentDto(int id, String fName, String sName, String email, int indexNo, int semester) {
        super(id, fName, sName, email);
        this.indexNo = indexNo;
        this.semester = semester;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student(");
        sb.append("fName='").append(fName).append('\'');
        sb.append(", sName='").append(sName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", indexNo=").append(indexNo);
        sb.append(", semester=").append(semester);
        sb.append(')');
        return sb.toString();
    }
}