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
package sim.shape.position;

/**
 * Basically a point in 2D space with some methods attached to it
 *
 * @author Kareem Horstink
 * @version 0.3
 */
public class Position2D {

    private double x;
    private double y;

    public Position2D() {
        x = 0;
        y = 0;
    }

    public Position2D(double x, double y) {
        this.x = x;
        this.y = y;
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

    /**
     * Get the value of y
     *
     * @return the value of y
     */
    public double getY() {
        return y;
    }

    /**
     * Set the value of y
     *
     * @param y new value of y
     */
    public void setY(double y) {
        this.y = y;
    }

    public double[] difference(Position2D pos) {
        return new double[]{x - pos.x, y - pos.y};
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position2D other = (Position2D) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the position of this position is higher than the given
     * position. If they are on the same height, then it returns false
     *
     * @param check The position to check against
     *
     * @return True if the position is higher than the given one else gives a
     * false
     */
    public boolean isHigherThan(Position2D check) {
        return check.y < y;
    }

    /**
     * *Please ignore that I used lefter - by lefter I mean its check.x > x
     * <p>
     * Checks if the position of this position is lefter than the given
     * position. If they are on the same leftness, then it returns false
     *
     * @param check The position to check against
     *
     * @return True if the position is higher than the given one else gives a
     * false
     */
    public boolean isLefterThan(Position2D check) {
        return check.x > x;
    }

    @Override
    public String toString() {
        return "x: " + x + "; y: " + y;
    }

}
