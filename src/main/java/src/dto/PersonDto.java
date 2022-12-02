package src.dto;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PersonDto {

    private int id;
    protected String fName;
    protected String sName;
    protected String email;
}