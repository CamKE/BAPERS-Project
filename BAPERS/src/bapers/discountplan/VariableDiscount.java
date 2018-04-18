/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.discountplan;

import bapers.job.Job;
import bapers.job.StandardJob;
import bapers.job.Task;
import java.util.List;

/**
 *
 * @author CameronE
 */
public class VariableDiscount extends Discount {

    List<Task> tasks;

    public VariableDiscount(int accountNo, List<Task> tasks) {
        super(accountNo, "Variable");
        this.tasks = tasks;
    }

    @Override
    protected void buildQuery() {
        sb.append("INSERT INTO discountplan_tasks VALUES");
        for (Task t : tasks) {
            sb.append("(").append(t.getTaskId()).append(",").append(accountNo).append(",").append(t.getDiscountRate()).append("),");
        }
        sb.deleteCharAt(sb.length() - 1);
    }

    @Override
    public double applyDiscount(Job job) {
        double total = 0;
        float VAT = 0.2f;
        for (StandardJob j : job.getStandardJobs()) {
            for (Task t : j.getTasks()) {
                for (Task discountTask : tasks) {
                    System.out.println(discountTask.getTaskId() + ":" + t.getTaskId());
                    if (discountTask.getTaskId() == t.getTaskId()) {
                        total += (discountTask.getPrice() * (1 - (discountTask.getDiscountRate() / 100.0)));
                        break;
                    }
                }
            }
        }

        System.out.println(total + "HEREE");
        total *= ((job.getSurcharge() / 100.0) + VAT + 1);

        return total;
    }
}
