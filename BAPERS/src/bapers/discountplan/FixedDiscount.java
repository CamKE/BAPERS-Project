/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.discountplan;

import bapers.job.Job;

/**
 *
 * @author CameronE
 */
public class FixedDiscount extends Discount {

    float discountRate;

    public FixedDiscount(int accountNo, float discountRate) {
        super(accountNo, "Fixed");
        this.discountRate = discountRate;
    }

    @Override
    protected void buildQuery() {
        sb.append("INSERT INTO fixeddiscount VALUES(").append(discountRate).append(",").append(accountNo).append(")");
    }

    @Override
    public double applyDiscount(Job job) {
        double total = job.calculateTotal();
        total *= (1 - (discountRate / 100.0));

        return total;
    }

}
