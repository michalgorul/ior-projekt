package src.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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
        return "Student(" + "fName='" + fName + '\'' +
                ", sName='" + sName + '\'' +
                ", email='" + email + '\'' +
                ", indexNo=" + indexNo +
                ", semester=" + semester +
                ')';
    }
}