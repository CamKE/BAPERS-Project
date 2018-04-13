/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.acct;

import java.util.Date;

/**
 *
 * @author kelvin
 */
public interface Scheduler {
    public Date next();
    public Date checkNextDay();
}
