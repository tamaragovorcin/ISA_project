package com.isaproject.isaproject.Model.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;



    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PersonUser personUser;

    public ConfirmationToken() {
    }

    public ConfirmationToken(PersonUser user) {
        this.personUser = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public long getTokenid() {
        return tokenid;
    }

    public void setTokenid(long tokenid) {
        this.tokenid = tokenid;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public PersonUser getPersonUser() {
        return personUser;
    }

    public void setPersonUser(PersonUser personUser) {
        this.personUser = personUser;
    }
}
