package ca.sheridancollege.hoangjam.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;

    @NonNull
    private String email;
    @NonNull
    private String encryptedPassword;
    private Boolean enabled;
}
