/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.container;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javathing.GameContainer;
import javathing.MainClass;
import javathing.settings.Settings;

/**
 *
 * @author Henry
 */
public class MenuContainer implements GameContainer {
    private String name;
    public MenuContainer(String name) {
        this.name = name;
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage buf = new BufferedImage(Settings.SCREEN_WIDTH, Settings.SCREEN_HTIGHT, BufferedImage.TYPE_INT_BGR);
        Graphics gr = buf.createGraphics();

        MainClass.getMenuManager().paint(gr);

        g.drawImage(buf, 0, 0, null);
    }

    @Override
    public void update() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void dispose() {
        MainClass.getMenuManager().deactivateListeners();
    }

    @Override
    public void init() {
    }
}
