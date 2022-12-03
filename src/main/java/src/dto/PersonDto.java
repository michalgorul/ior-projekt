package src.dto;

import lombok.*;

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