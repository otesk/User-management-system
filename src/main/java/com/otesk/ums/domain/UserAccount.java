package com.otesk.ums.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Java class representing data about a user account.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_account")
public class UserAccount {

    /**
     * Stores user account id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Stores user account username.
     */
    @Column(unique = true)
    private String username;

    /**
     * Stores user account password.
     */
    private String password;

    /**
     * Stores user account first name.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Stores user account last name.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Stores user account role.
     */
    @Enumerated(value = EnumType.STRING)
    private Role role;

    /**
     * Stores user account creation date.
     */
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    /**
     * Stores user account status.
     */
    @Enumerated(value = EnumType.STRING)
    private Status status;
}
