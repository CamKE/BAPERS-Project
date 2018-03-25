/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.job;

import java.util.List;

/**
 *
 * @author CameronE
 */
public class StandardJobs {
       List<Material> stdJobList;

    public StandardJobs(List<Material> stdJobList) {
        this.stdJobList = stdJobList;
    }

    public List<Material> getStdJobList() {
        return stdJobList;
    }

    public void setStdJobList(List<Material> stdJobList) {
        this.stdJobList = stdJobList;
    }
       
       

}
