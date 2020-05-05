package com.discussiongroup.voting;


class Party {
    int id, electionId, chairmanId, treasurerId, sec_genId;
    String name, logo, slogan;

    Party(int partyId, int election_id, String name, String logo, String slogan, int chairman_id, int treasurer_id, int sec_gen_id) {
        id = partyId;
        electionId = election_id;
        this.name = name;
        this.logo = logo;
        this.slogan = slogan;
        this.chairmanId = chairman_id;
        this.treasurerId = treasurer_id;
        this.sec_genId = sec_gen_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getElectionId() {
        return electionId;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
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

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getChairman() {
        return chairmanId;
    }

    public void setChairman(int chairmanId) {
        this.chairmanId = chairmanId;
    }

    public int getTreasurer() {
        return treasurerId;
    }

    public void setTreasurer(int treasurerId) {
        this.treasurerId = treasurerId;
    }

    public int getSec_gen() {
        return sec_genId;
    }

    public void setSec_gen(int sec_genId) {
        this.sec_genId = sec_genId;
    }
}
