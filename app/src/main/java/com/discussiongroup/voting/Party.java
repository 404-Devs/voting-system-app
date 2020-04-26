
package com.discussiongroup.voting;


public class Party {

    int partyLogo;
    String partySlogan;
    String partyName;

    public Party(int partyLogo, String partySlogan, String partyName) {
        this.partyLogo = partyLogo;
        this.partySlogan = partySlogan;
        this.partyName = partyName;
    }

    public int getPartyLogo() {
        return partyLogo;
    }

    public void setPartyLogo(int partyLogo) {
        this.partyLogo = partyLogo;
    }

    public String getPartySlogan() {
        return partySlogan;
    }

    public void setPartySlogan(String partySlogan) {
        this.partySlogan = partySlogan;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }


}

