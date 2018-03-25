/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.job;

/**
 *
 * @author kelvin
 */
public class Material {
    private int materialID;
    private String materialDescription;

    public Material(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }
    
}
