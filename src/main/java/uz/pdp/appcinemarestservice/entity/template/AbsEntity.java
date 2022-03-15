package uz.pdp.appcinemarestservice.entity.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

// Nurkulov Nodirbek 3/14/2022  10:37 PM
@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public abstract class AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime updatedAt;
}
