package ua.kpi.coursework.model;

import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Desired salary cannot be null")
    @Column(name = "desired_salary")
    private Long desired_salary;

    @Column(name = "date_submit")
    private Date date_submit;

    @OneToOne
    @JoinColumn(name = "unemployed_id")
    private Unemployed unemployed;

    @OneToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    @ManyToMany
    @JoinTable(name="position_resumes",
            joinColumns={@JoinColumn(name="resume_id")},
            inverseJoinColumns={@JoinColumn(name="position_id")})
    private List<Position> positions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Resume resume = (Resume) o;
        return id != null && Objects.equals(id, resume.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
