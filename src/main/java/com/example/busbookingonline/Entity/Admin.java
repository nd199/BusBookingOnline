package com.example.busbookingonline.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Admin {
    @Id
    private Long AdminId;

    private String AdminName;
    private String Email;
    private String Password;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;

}
