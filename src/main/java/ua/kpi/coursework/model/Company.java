package ua.kpi.coursework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "Name cannot be null")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Company's email cannot be empty.")
    @Email(message = "Invalid email")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Address cannot be null")
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    List<Vacancy> vacancies;
}
