/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javathing.settings.Settings;
import javathing.sprite.Sprite;

/**
 *
 * @author Henry
 */
public class ZoneMap {

    private HashMap<Point, Zone> zones;

    public static Point getZone(Point location) {
        return new Point((int) (TileMap.getTileLocation(location.getX()) / Settings.ZoneSize), (int) (TileMap.getTileLocation(location.getY()) / Settings.ZoneSize));
    }

    public ZoneMap() {
        zones = new HashMap<Point, Zone>();
    }

    public List<Zone> regesterSprite(Sprite sprite) {
        List<Zone> spriteZones = new ArrayList<Zone>();
        Point[] corners = {new Point((int) sprite.getX(), (int) sprite.getY()), new Point((int) sprite.getX() + (int) sprite.getWidth(), (int) sprite.getY()), new Point((int) sprite.getX(), (int) sprite.getY() + (int) sprite.getHeight()), new Point((int) sprite.getX() + (int) sprite.getWidth(), (int) sprite.getY() + (int) sprite.getHeight())};
        for (Zone z : sprite.getRegistaredZones()) {
            
            z.deregestarSprite(sprite);
            
            if (z.getSprites().isEmpty()) {
                zones.remove(z.getLocation());
            }
        }
        for (Point p : corners) {
            Point zoneLocation = getZone(p);
            try {
            Zone zone = zones.get(zoneLocation);
            zone.regesterSprite(sprite);
            spriteZones.add(zone);
            } catch (Exception ex) {
                zones.put(zoneLocation, new Zone(zoneLocation));
                Zone zone = zones.get(zoneLocation);
                zone.regesterSprite(sprite);
                spriteZones.add(zone);
            }
        }
        return spriteZones;
    }
}
