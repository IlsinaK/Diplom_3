package api;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserDataLombok {
    private String email;
    private String password;
    private String name;

    public UserDataLombok(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}


