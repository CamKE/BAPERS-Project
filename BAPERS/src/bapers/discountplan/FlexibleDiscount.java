/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.discountplan;

import bapers.database.DBImpl;
import bapers.job.Job;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author CameronE
 */
public class FlexibleDiscount extends Discount {

    List<DiscountBand> bands;
    DiscountBand currentBand;

    public FlexibleDiscount(int accountNo, List<DiscountBand> bands) {
        super(accountNo, "Flexible");
        this.bands = bands;
        currentBand = null;
    }

    private void checkBound(double bound) {
        if (bound == 0) {
            sb.append("NULL");
        } else {
            sb.append(bound);
        }
    }

    @Override
    protected void buildQuery() {
        sb.append("INSERT INTO band(upper_bound, lower_bound, discount_rate, DiscountPlan_Customer_account_no) VALUES");
        for (DiscountBand b : bands) {
            sb.append("(");
            checkBound(b.upperBound);
            sb.append(",");
            checkBound(b.lowerBound);
            sb.append(",").append(b.discountRate).append(",").append(accountNo).append("),");
        }
        sb.deleteCharAt(sb.length() - 1);
    }

    @Override
    public double applyDiscount(Job job) {
        double total = job.calculateTotal();

        if (currentBand != null) {
            total *= (1 - (currentBand.getDiscountRate() / 100.0));
        }

        return total;
    }

    public DiscountBand getCurrentBand() {
        return currentBand;
    }

    public void setCurrentBand(int currentBandNo) {
        for (DiscountBand b : bands) {
            if (b.getBandNo() == currentBandNo) {
                currentBand = b;
                break;
            }
        }
    }

}
