package ua.kpi.coursework.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Unemployed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "User's first name cannot be empty.")
    @Column(name = "first_name")
    private String first_name;

    @NotEmpty(message = "User's last name cannot be empty.")
    @Column(name = "last_name")
    private String last_name;

    @Min(value = 10, message = "Invalid number")
    @NotEmpty(message = "User's phone cannot be empty.")
    @Column(name = "phone")
    private String phone;

    @NotEmpty(message = "User's email cannot be empty.")
    @Email(message = "Invalid email")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "User's address cannot be empty.")
    @Column(name = "address")
    private String address;

    @OneToOne(mappedBy = "unemployed", cascade = CascadeType.ALL)
    private Resume resume;

    @ManyToMany
    @JoinTable(name="specialities_unemployed",
            joinColumns={@JoinColumn(name="unemployed_id")},
            inverseJoinColumns={@JoinColumn(name="speciality_id")})
    private List<Speciality> client_specialities;
}
