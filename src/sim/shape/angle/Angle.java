/*
 * Copyright (C) 2015 Kareem Horstink
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
package sim.shape.angle;

import sim.shape.Line2D;
import sim.shape.position.Position2D;

/**
 * Represents the angle of an item in radians
 *
 * @author Kareem Horstink
 */
public class Angle {

    private static double PI = Math.PI;
    private double x = 0;

    private Angle(Angle g) {
        this.x = g.x;
    }

    /**
     * Creates an angle based on the value given
     *
     * @param x
     */
    public Angle(double x) {
        this.x = x;
    }

    /**
     * Default constructor which sets an angle to be 0
     */
    public Angle() {
    }

    public Angle(Position2D point1, Position2D point2) {
        x = new Line2D(point1, point2).getAngleComplicated().x;
    }

    /**
     * Gets the angle of a line assuming point1 is higher and to the right of
     * point2 (point1 lies on the first quadrant [unit circle] of point 2)
     */
    public Angle(Line2D line) {
        //Basically returns Tan^-1(x/y) where x is the difference in the x 
        //position and y is the difference in the y position
        x = Math.tanh(line.getXheight() / line.getYheight());
    }

    /**
     * Get the value of x
     *
     * @return the value of x
     */
    public double getX() {
        return x;
    }

    /**
     * Set the value of x
     *
     * @param x new value of x
     */
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Angle: " + x * 180 / PI;
    }

    public Angle copy() {
        return new Angle(this);
    }

    public int quadrant() {
        if (0 <= x && x < PI / 2) {
            return 1;
        }
        if (PI / 2 <= x && x < PI) {
            return 2;
        }
        if (PI <= x && x < PI * 3d / 2d) {
            return 3;
        }
        return 4;
    }
    
    
}
