package src.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


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