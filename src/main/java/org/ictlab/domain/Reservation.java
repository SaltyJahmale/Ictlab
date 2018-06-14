package org.ictlab.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq")
    @SequenceGenerator(name = "reservation_seq", sequenceName = "reservation_seq", allocationSize = 1)
    private Long id;

    @Column(name = "TITLE")
    @NotNull
    private String title;

    @Column(name = "STARTDATE")
    @NotNull
    private LocalDateTime start;

    @Column(name = "ENDDATE")
    @NotNull
    private LocalDateTime end;

    @ManyToMany
    @JoinTable(
        name = "RESERVATION_APPUSER",
        joinColumns = {@JoinColumn(name = "RESERVATION_ID", referencedColumnName = "ID")},
        inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")})
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Reservation{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", start=" + start +
            ", end=" + end +
            ", users=" + users +
            '}';
    }
}
