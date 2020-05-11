package com.discussiongroup.voting;


class Party {
    private int votes, id, electionId;
    private String name, logo, slogan, chairman, treasurer, sec_gen;

    Party(int partyId, int election_id, String name, String logo, String slogan, String chairman, String treasurer, String sec_gen, int votes) {
        id = partyId;
        electionId = election_id;
        this.name = name;
        this.logo = logo;
        this.slogan = slogan;
        this.chairman = chairman;
        this.treasurer = treasurer;
        this.sec_gen = sec_gen;
        this.votes = votes;
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

    String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public void setTreasurer(String treasurer) {
        this.treasurer = treasurer;
    }

    public void setSec_gen(String sec_gen) {
        this.sec_gen = sec_gen;
    }

    String getChairman() {
        return chairman;
    }

    String getTreasurer() {
        return treasurer;
    }

    String getSec_gen() {
        return sec_gen;
    }

    int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
