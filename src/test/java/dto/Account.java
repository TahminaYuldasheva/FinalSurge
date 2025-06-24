package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Account {
    String firstName;
    String lastName;
    String emailAddress;
    String timeZone;
    String password;
    String reTypePassword;
}
