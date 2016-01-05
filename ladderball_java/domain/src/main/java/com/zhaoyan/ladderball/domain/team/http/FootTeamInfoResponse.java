package com.zhaoyan.ladderball.domain.team.http;

import com.zhaoyan.ladderball.domain.common.http.Response;


public class FootTeamInfoResponse extends Response{
    public TeamInfo teamInfo;

    public static class TeamInfo {
        public long id;
        public String name;
        public String logo;
        public String address;
        public boolean isInvitationCodeEnabled;
        public String invitationCode;

        public int playerNumber;
        public String captain;
        public boolean isIAmCaptain;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getPlayerNumber() {
            return playerNumber;
        }

        public void setPlayerNumber(int playerNumber) {
            this.playerNumber = playerNumber;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
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
}
