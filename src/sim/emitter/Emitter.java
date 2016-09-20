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
package sim.emitter;

import sim.Simulation;
import sim.shape.position.Position2D;

/**
 *
 * @author Kareem Horstink
 * @version 0.15
 */
public abstract class Emitter {

    private Simulation sim;

    private Position2D position;

    private double size;

    private double spacing;

    public Emitter(Simulation sim, Position2D position, double size, double spacing) {
        this.sim = sim;
        this.position = position;
        this.size = size;
        this.spacing = spacing;
    }

    public abstract void create();

    /**
     * Get the value of sim
     *
     * @return the value of sim
     */
    public Simulation getSim() {
        return sim;
    }

    /**
     * Set the value of sim
     *
     * @param sim new value of sim
     */
    public void setSim(Simulation sim) {
        this.sim = sim;
    }

    /**
     * Get the value of size
     *
     * @return the value of size
     */
    public double getSize() {
        return size;
    }

    /**
     * Set the value of size
     *
     * @param size new value of size
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * Get the value of spacing
     *
     * @return the value of spacing
     */
    public double getSpacing() {
        return spacing;
    }

    /**
     * Set the value of spacing
     *
     * @param spacing new value of spacing
     */
    public void setSpacing(double spacing) {
        this.spacing = spacing;
    }

    /**
     * Get the value of position
     *
     * @return the value of position
     */
    public Position2D getPosition() {
        return position;
    }

    /**
     * Set the value of position
     *
     * @param position new value of position
     */
    public void setPosition(Position2D position) {
        this.position = position;
    }

}
