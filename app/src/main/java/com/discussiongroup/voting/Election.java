package com.discussiongroup.voting;

public class Election {
    private int electionId;
    private String electionName;
    private Integer startTimestamp, endTimestamp;

    public Election(int elId, String elName, Integer start, Integer end) {
        this.electionId = elId;
        this.electionName = elName;
        this.startTimestamp = start;
        this.endTimestamp = end;
    }


    public int getElectionId() {
        return electionId;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }

    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public Integer getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Integer startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Integer getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Integer endTimestamp) {
        this.endTimestamp = endTimestamp;
    }
}
