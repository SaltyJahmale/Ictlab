package org.ictlab.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "ROOM")
    @NotNull
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
        name = "SCHOOLSCHEDULE_ROOM",
        joinColumns = {@JoinColumn(name = "SCHOOLSCHEDULE_ID", referencedColumnName = "ID")},
        inverseJoinColumns = {@JoinColumn(name = "ROOM_ID", referencedColumnName = "ID")})
    @JsonIgnoreProperties("schoolSchedules")
    private List<Room> rooms;

    @Column(name = "CLASS")
    @NotNull
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
        name = "SCHOOLSCHEDULE_CLASS",
        joinColumns = {@JoinColumn(name = "SCHOOLSCHEDULE_ID", referencedColumnName = "ID")},
        inverseJoinColumns = {@JoinColumn(name = "CLASS_ID", referencedColumnName = "ID")})
    @JsonIgnoreProperties("schoolSchedules")
    private List<Group> groups;

    @Column(name = "TEACHER")
    @NotNull
    @ElementCollection
    private List<String> teacher;

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

    public List<String> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<String> teacher) {
        this.teacher = teacher;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
