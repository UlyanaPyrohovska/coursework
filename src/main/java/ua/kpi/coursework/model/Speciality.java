package ua.kpi.coursework.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Setter
@ToString
@RequiredArgsConstructor
@Getter
@Entity
@Table(name = "specialities")
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "client_specialities", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Unemployed> clients;

    @OneToMany(mappedBy = "speciality", cascade = CascadeType.ALL)
    List<Vacancy> vacancies;
}
