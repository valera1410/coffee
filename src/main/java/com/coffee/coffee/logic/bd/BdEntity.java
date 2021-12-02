package com.coffee.coffee.logic.bd;

import javax.persistence.*;

@Entity
@Table(name="History")
public class BdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "leftWater")
    protected int leftWater;

    @Column(name = "leftMilk")
    protected int leftMilk;

    @Column(name = "leftGrain")
    protected int leftGrain;

    public int getLeftMilk() {
        return leftMilk;
    }

    public void setLeftMilk(int leftMilk) {
        this.leftMilk = leftMilk;
    }

    public int getLeftWater() {
        return leftWater;
    }

    public void setLeftWater(int leftWater) {
        this.leftWater = leftWater;
    }

    public int getLeftGrain() {
        return leftGrain;
    }

    public void setLeftGrain(int leftGrain) {
        this.leftGrain = leftGrain;
    }

    public String getWhatBrewed() {
        return whatBrewed;
    }

    public void setWhatBrewed(String whatBrewed) {
        this.whatBrewed = whatBrewed;
    }

    protected String whatBrewed;
}
