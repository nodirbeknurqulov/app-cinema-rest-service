package uz.pdp.appcinemarestservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Nurkulov Nodirbek 4/4/2022  8:51 AM
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MailDto {
    private String to;
    private String message;
    private String subject;
    private String name;

    public MailDto(String to, String message, String subject) {
        this.to = to;
        this.message = message;
        this.subject = subject;
    }
}