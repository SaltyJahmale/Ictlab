package org.ictlab.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column(name = "ROOM")
    @NotNull
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "RESERVATION_ROOM",
        joinColumns = {@JoinColumn(name = "RESERVATION_ID", referencedColumnName = "ID")},
        inverseJoinColumns = {@JoinColumn(name = "ROOM_ID", referencedColumnName = "ID")})
    @JsonIgnoreProperties("reservations")
    private List<Room> rooms;

    @JsonIgnoreProperties("reservations")
    @ManyToMany(mappedBy = "reservations")
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
