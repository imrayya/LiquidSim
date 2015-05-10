/*
 * Copyright (C) 2015 Imray
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package sim.collider;

import java.awt.geom.Rectangle2D;
import sim.angle.Angle;
import sim.force.Force2D;
import sim.partical.Partical;
import sim.position.Position;

/**
 *
 * @author Imray
 */
public class Collider2Drect extends Collider {

    private int x;

    private int y;

    private int w;

    private int h;

    public Collider2Drect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Collider2Drect(int x, int y, int w, int h, double e) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        setElasticity(e);
    }

    public Rectangle2D getRect() {
        return new Rectangle2D.Double(x, y, w, h);
    }

    @Override
    public boolean contains(Position p) {
        return new Rectangle2D.Double(x, y, w, h).contains(p.getX(), p.getY());
    }

    /**
     * Get the value of x
     *
     * @return the value of x
     */
    public int getX() {
        return x;
    }

    /**
     * Set the value of x
     *
     * @param x new value of x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get the value of y
     *
     * @return the value of y
     */
    public int getY() {
        return y;
    }

    /**
     * Set the value of y
     *
     * @param y new value of y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get the value of w
     *
     * @return the value of w
     */
    public int getW() {
        return w;
    }

    /**
     * Set the value of w
     *
     * @param w new value of w
     */
    public void setW(int w) {
        this.w = w;
    }

    /**
     * Get the value of h
     *
     * @return the value of h
     */
    public int getH() {
        return h;
    }

    /**
     * Set the value of h
     *
     * @param h new value of h
     */
    public void setH(int h) {
        this.h = h;
    }

    @Override
    public Angle getAngle(Partical p) {
        Force2D f = (Force2D)p.getForce();
       return new Angle(f.angle().getX()*-1);
    }
}
