/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javathing.ui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javathing.MainClass;
import javathing.settings.Settings;

/**
 *
 * @author Henry
 */
public class GUI {
    public GUI() {
	
    }
    private List<Component> components = new ArrayList<Component>();
    public void addComponenet(Component component) {
	components.add(component);
	KeyListener kl = component.getKeyListener();
	MouseListener ml = component.getMouseListener();
	if (ml != null) {
	    MainClass.getLevelManager().addMouseListener(ml);           //Warning!!!!!!!!!!!!!!!!!! could cause null pointer exception and breaks coding rules
	}
	if (kl != null) {
	    MainClass.getLevelManager().addKeyListener(kl);
	}
	
    }
    
    public Image getUIOverlay() {
	BufferedImage buf = new BufferedImage(Settings.SCREEN_WIDTH, Settings.SCREEN_HTIGHT, BufferedImage.TYPE_4BYTE_ABGR);
	Graphics2D g2D = buf.createGraphics();
	for (Component component : components) {
	    component.paint(g2D);
	}
	return buf;
    }
}
