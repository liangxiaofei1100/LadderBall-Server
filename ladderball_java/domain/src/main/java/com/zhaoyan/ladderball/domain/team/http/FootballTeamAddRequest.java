package com.zhaoyan.ladderball.domain.team.http;

import com.zhaoyan.ladderball.domain.common.http.Request;

public class FootballTeamAddRequest extends Request{

    /**
     * 创建者的微信id
     */
    public String weiXinId;

    public String name;

    public String logo;

    public String address;

    public boolean isInvitationCodeEnabled;

    public String invitationCode;

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
