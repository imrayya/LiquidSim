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
package sim.force;

/**
 *
 * @author Kareem Horstink
 */
public abstract class Force {

    private double[] vector;

    public Force(double[] vector) {
        this.vector = vector;
    }

    /**
     * Get the value of vector
     *
     * @return the value of vector
     */
    public double[] getVector() {
        return vector;
    }

    /**
     * Set the value of vector
     *
     * @param vector new value of vector
     */
    public void setVector(double[] vector) {
        this.vector = vector;
    }

    /**
     * Get the value of vector at specified index
     *
     * @param index the index of vector
     * @return the value of vector at specified index
     */
    public double getVector(int index) {
        return this.vector[index];
    }

    /**
     * Set the value of vector at specified index.
     *
     * @param index the index of vector
     * @param vector new value of vector at specified index
     */
    public void setVector(int index, double vector) {
        this.vector[index] = vector;
    }

    public abstract Force multi(double t);
}
