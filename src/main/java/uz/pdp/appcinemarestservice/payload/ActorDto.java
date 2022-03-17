package uz.pdp.appcinemarestservice.payload;
// Nurkulov Nodirbek 3/17/2022  10:00 AM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActorDto {

    private String fullName;

    private String bio;

    private String fileOriginalName;//pdp.jpg, inn.pdf

    private long size;//2048 KB

    private String contentType;// application/pdf || image/png
}
