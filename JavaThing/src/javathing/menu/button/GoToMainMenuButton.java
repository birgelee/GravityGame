/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.menu.button;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javathing.MainClass;
import javathing.animation.SinAnimator;
import javathing.utils.Convenience;

/**
 *
 * @author Henry
 */
public class GoToMainMenuButton extends RectangleMenuButton {
    public GoToMainMenuButton(int x, int y, BufferedImage buttonBackground) {
        super(x, y, 100, 50, buttonBackground, SinAnimator.getDefualtAnimator(), "Main Menu", Color.white, new ButtonEvent() {
            @Override
            public void pressed() {
                if (MainClass.getMenuManager() != null) {
                    MainClass.getMenuManager().deactivateListeners();
                }
                Convenience.initMainMenu();
            }
        });
    }
}
