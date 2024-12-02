package ma.yassine.agencyauthservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String agencyname;
    @Column(unique = true)
    private String email;
    private String fullName;
    private String adresse;
    @Column(unique = true)
    private String phone;
    private String recoveryEmail;
    private boolean isSeller = false;
    private boolean isAdmin = false;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime lastLogin;


}
