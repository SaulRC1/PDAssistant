/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doppelganger.role.system.pdassistant.model.spinner;

import javax.swing.SpinnerNumberModel;

/**
 *
 * @author SaulRC1
 */
public class TiradaSpinnerModel extends SpinnerNumberModel {

    public TiradaSpinnerModel() {
        
        this.setStepSize(1);
        
        this.setMinimum(0);
        this.setMaximum(1000);
        
    }
    
}
