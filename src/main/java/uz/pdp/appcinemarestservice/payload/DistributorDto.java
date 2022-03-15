package uz.pdp.appcinemarestservice.payload;
// Nurkulov Nodirbek 3/15/2022  6:36 PM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DistributorDto {

    @NotNull(message = "FullName can't be empty!!!")
    private String fullName;

    @NotNull(message = "Bio can't be empty!!!")
    private String bio;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime updatedAt;
}
