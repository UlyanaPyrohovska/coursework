package ua.kpi.coursework.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @ManyToMany
            (cascade = CascadeType.MERGE, mappedBy = "positions", fetch = FetchType.LAZY)
    private List<Resume> resumes;

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    List<Vacancy> vacancies;
}
