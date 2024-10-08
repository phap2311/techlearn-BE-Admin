package com.techzen.techlearn.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted = false")
@Table(name = "tbl_user")
public class UserEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_user_roles",
            joinColumns=@JoinColumn(name="user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role", referencedColumnName = "id")
    )
    Set<Role> roles= new HashSet<>();

    @Column(name = "avatar")
    String avatar;

    @Column(name = "email")
    String email;

}
