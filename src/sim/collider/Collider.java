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
package sim.collider;

import sim.shape.angle.Angle;
import sim.partical.Partical;
import sim.shape.position.Position2D;

/**
 *
 * @author Kareem Horstink
 */
public abstract class Collider {

    public abstract Angle getAngle(Partical p);

    private double elasticity = 1;

    /**
     * Get the value of elasticity
     *
     * @return the value of elasticity
     */
    public final double getElasticity() {
        return elasticity;
    }

    /**
     * Set the value of elasticity
     *
     * @param elasticity new value of elasticity
     */
    public final void setElasticity(double elasticity) {
        this.elasticity = elasticity;
    }

    public abstract boolean contains(Position2D p);
}
