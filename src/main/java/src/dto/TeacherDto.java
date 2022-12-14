package src.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class TeacherDto extends PersonDto implements Serializable {
    private String title;
}