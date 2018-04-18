/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.discountplan;

/**
 *
 * @author CameronE
 */
public class DiscountBand {

    double lowerBound;
    double upperBound;
    float discountRate;
    int bandNo;

    public DiscountBand(double lowerBound, double upperBound, float discountRate) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.discountRate = discountRate;
        int bandNo = 0;
    }

    public int getBandNo() {
        return bandNo;
    }

    public void setBandNo(int bandNo) {
        this.bandNo = bandNo;
    }

    public DiscountBand(double bound, float discountRate, boolean isUpperBound) {
        if (isUpperBound) {
            this.upperBound = bound;
        } else {
            this.lowerBound = bound;
        }
        this.discountRate = discountRate;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(double upperBound) {
        this.upperBound = upperBound;
    }

    public float getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(float discountRate) {
        this.discountRate = discountRate;
    }

}
