package org.ictlab.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "MESSAGE")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_seq")
    @SequenceGenerator(name = "message_seq", sequenceName = "message_seq", allocationSize = 1)
    private Long id;

    @Column(name = "STATE")
    @NotNull
    private String state;

    @Column(name = "MSG")
    @NotNull
    private String msg;

    @Column(name = "SENDDATE")
    @NotNull
    private LocalDateTime localDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public State getState() {
        return State.fromValue(state);
    }

    public void setState(State state) {
        this.state = state.toValue();
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
            Objects.equals(state, message.state) &&
            Objects.equals(msg, message.msg) &&
            Objects.equals(localDateTime, message.localDateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, state, msg, localDateTime);
    }

    @Override
    public String toString() {
        return "Message{" +
            "id=" + id +
            ", state='" + state + '\'' +
            ", msg='" + msg + '\'' +
            ", localDateTime=" + localDateTime +
            '}';
    }
}
