package com.zhaoyan.ladderball.domain.team.db;

import javax.persistence.*;

@Entity(name = "footballteam")
public class FootballTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name = "name")
    public String name;

    @Column(name = "logo")
    public String logo;

    @Column(name = "address")
    public String address;

    @Column(name = "invitation_code_enable")
    public boolean isInvitationCodeEnabled;

    @Column(name = "invitation_code")
    public String invitationCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getIsInvitationCodeEnabled() {
        return isInvitationCodeEnabled;
    }

    public void setIsInvitationCodeEnabled(boolean isInvitationCodeEnabled) {
        this.isInvitationCodeEnabled = isInvitationCodeEnabled;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
