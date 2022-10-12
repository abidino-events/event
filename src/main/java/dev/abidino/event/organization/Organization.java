package dev.abidino.event.organization;

import dev.abidino.event.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orgName;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    @ManyToMany
    private List<User> adminList = new ArrayList<>();
    @ManyToMany
    private List<User> memberList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public List<User> getAdminList() {
        return adminList;
    }

    public List<User> getMemberList() {
        return memberList;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
