/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.menu.button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import gravitygame.animation.Animator;

/**
 *
 * @author Henry
 */
public class RectangleMenuButton extends MenuButton {

    private Dimension size;
    private Point position;
    Color backgroundColor;
    Color textColor = Color.BLACK;
    BufferedImage backgroundImage;
    String text;
    Animator opacityAnimator;
    ButtonEvent buttonEvent;

    public RectangleMenuButton(int x, int y, int width, int height, Color color, String text, Color textColor, ButtonEvent buttonEvent) {
	size = new Dimension(width, height);
	position = new Point(x, y);
	this.backgroundColor = color;
	this.buttonEvent = buttonEvent;
	this.text = text;
	this.textColor = textColor;
    }

    public RectangleMenuButton(int x, int y, int width, int height, BufferedImage backgroundImage, Animator opacityAnimator, String text, Color textColor, ButtonEvent buttonEvent) {
	size = new Dimension(width, height);
	position = new Point(x, y);
	this.backgroundImage = backgroundImage;
	this.buttonEvent = buttonEvent;
	this.text = text;
	this.textColor = textColor;
	this.opacityAnimator = opacityAnimator;
	opacityAnimator.start();
    }

    public RectangleMenuButton(int x, int y, int width, int height, Color color, String text, ButtonEvent buttonEvent) {
	this(x, y, width, height, color, text, Color.black, buttonEvent);
    }

    @Override
    public void paint(Graphics g) {
	
	if (backgroundColor != null) {
	    g.setColor(backgroundColor);
	    g.fillRect(position.x, position.y, size.width, size.height);
	} else {
	    float[] scales = {1f, 1f, 1f, (float) opacityAnimator.getPercent()};
	    float[] offsets = new float[4];
	    RescaleOp rop = new RescaleOp(scales, offsets, null);
	    ((Graphics2D)g).drawImage(backgroundImage, rop, position.x, position.y);
	}
	g.setColor(textColor);
	FontMetrics fm = g.getFontMetrics();
	g.drawString(text, position.x + (size.width - fm.stringWidth(text)) / 2, position.y + fm.getHeight() / 2 + (size.height - fm.getHeight()) / 2);

    }

    @Override
    public void onClick() {
	buttonEvent.pressed();
    }

    @Override
    public boolean isClicked(MouseEvent me) {
	if (me.getX() > position.x && me.getX() < position.x + size.width) {
	    if (me.getY() > position.y && me.getY() < position.y + size.height) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public void update() {
        if (opacityAnimator != null) {
            opacityAnimator.update();
        }
    }
}
