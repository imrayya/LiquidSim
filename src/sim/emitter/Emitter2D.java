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
import sim.force.Force2D;
import sim.partical.Partical2D;
import sim.position.Position;

/**
 *
 * @author Kareem Horstink
 * @version 0.15
 */
public class Emitter2D extends Emitter {

    public Emitter2D(Simulation sim, Position position, double size, double spacing) {
        super(sim, position, size, spacing);
    }

    @Override
    public void create() {
        for (double i = getPosition().getX() - getSize() / 2; i < getPosition().getX() + getSize() / 2; i += getSpacing()) {
            for (double j = getPosition().getY() - getSize() / 2; j < getPosition().getY() + getSize() / 2; j += getSpacing()) {
                System.out.println("emit: " + i + " " + j);
                Partical2D p = new Partical2D(new Position(i, j));
                p.setForce(new Force2D(new double[]{0, 0}));
                getSim().getCONTAINER().addPartical(p);
            }
        }

    }

}
