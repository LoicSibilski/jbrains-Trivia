package com.trivia.Refacto;

public class Player {
    private String name;
    private Integer place;
    private Integer coins;
    private boolean inPenaltyBox;
    private boolean isGettingOutOfPenaltyBox;

    public Player() {
    }

    public Player(String name, Integer place, Integer coins, boolean inPenaltyBox, boolean isGettingOutOfPenaltyBox) {
        this.name = name;
        this.place = place;
        this.coins = coins;
        this.inPenaltyBox = inPenaltyBox;
        this.isGettingOutOfPenaltyBox = isGettingOutOfPenaltyBox;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlace() {
        return this.place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getPurse() {
        return this.coins;
    }

    public void setPurse(Integer coins) {
        this.coins = coins;
    }

    public boolean isInPenaltyBox() {
        return this.inPenaltyBox;
    }

    public boolean getInPenaltyBox() {
        return this.inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public boolean isIsGettingOutOfPenaltyBox() {
        return this.isGettingOutOfPenaltyBox;
    }

    public boolean getIsGettingOutOfPenaltyBox() {
        return this.isGettingOutOfPenaltyBox;
    }

    public void setIsGettingOutOfPenaltyBox(boolean isGettingOutOfPenaltyBox) {
        this.isGettingOutOfPenaltyBox = isGettingOutOfPenaltyBox;
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", place='" + getPlace() + "'" +
                ", coins='" + getPurse() + "'" +
                ", inPenaltyBox='" + isInPenaltyBox() + "'" +
                ", isGettingOutOfPenaltyBox='" + isIsGettingOutOfPenaltyBox() + "'" +
                "}";
    }

    public void moveForward(int roll){
        this.place += roll;
        if (this.place > 11){
            this.place -= 12;
        }
        System.out.println(this.name + "'s new location is " + place);
    }

    public boolean didPlayerWin(){
        return this.coins == 6;
    }

    public void addCoin(){
        this.coins++;
        System.out.println(this.name + " now has " + this.coins + " Gold Coins.");
    }

}
