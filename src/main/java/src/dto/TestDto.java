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
}