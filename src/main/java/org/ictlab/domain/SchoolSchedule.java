package org.ictlab.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "SCHOOLSCHEDULE")
public class SchoolSchedule {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schoolschedule_seq")
    @SequenceGenerator(name = "schoolschedule_seq", sequenceName = "schoolschedule_seq", allocationSize = 1)
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

    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(
        name = "SCHOOLSCHEDULE_ROOM",
        joinColumns = {@JoinColumn(name = "SCHOOLSCHEDULE_ID", referencedColumnName = "ID")},
        inverseJoinColumns = {@JoinColumn(name = "ROOM_ID", referencedColumnName = "ID")})
    private List<Room> rooms;

    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(
        name = "SCHOOLSCHEDULE_APPUSER",
        joinColumns = {@JoinColumn(name = "SCHOOLSCHEDULE_ID", referencedColumnName = "ID")},
        inverseJoinColumns = {@JoinColumn(name = "APPUSER_ID", referencedColumnName = "ID")})
    private List<User> users;

    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(
        name = "SCHOOLSCHEDULE_TEACHER",
        joinColumns = {@JoinColumn(name = "SCHOOLSCHEDULE_ID", referencedColumnName = "ID")},
        inverseJoinColumns = {@JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")})
    private List<Teacher> teachers;

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

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolSchedule that = (SchoolSchedule) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(start, that.start) &&
            Objects.equals(end, that.end) &&
            Objects.equals(rooms, that.rooms) &&
            Objects.equals(users, that.users) &&
            Objects.equals(teachers, that.teachers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, start, end, rooms, users, teachers);
    }

    @Override
    public String toString() {
        return "SchoolSchedule{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", start=" + start +
            ", end=" + end +
            ", rooms=" + rooms +
            ", users=" + users +
            ", teachers=" + teachers +
            '}';
    }
}
