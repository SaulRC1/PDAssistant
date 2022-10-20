/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doppelganger.role.system.pdassistant.controller;

import com.doppelganger.role.system.pdassistant.ui.PDAssistantMainWindow;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author SaulRC1
 */
public class PDAssistantMainWindowController {
    
    private PDAssistantMainWindow pdAssistantMainWindow = new PDAssistantMainWindow();

    public PDAssistantMainWindowController() {
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/icono.png"));
        pdAssistantMainWindow.setIconImage(icon.getImage());
        
        pdAssistantMainWindow.setVisible(true);
        
    }
    
    
    
}
