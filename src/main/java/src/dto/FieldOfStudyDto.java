package src.dto;

import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class FieldOfStudyDto implements Serializable {

    private int id;
    private String name;
    private String type;
}