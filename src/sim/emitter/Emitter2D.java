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

import java.util.Random;
import sim.Simulation;
import sim.force.Force2D;
import sim.partical.Partical2Dsimple;
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
                Partical2Dsimple p = new Partical2Dsimple(new Position(i, j));
                Random r = new Random();
                double x = r.nextDouble();
                double y = r.nextDouble();
                if (x > 0.5) {
                    x = -1;
                } else {
                    x = 1;
                }

                if (y > 0.5) {
                    y = -1;
                } else {
                    y = 1;
                }

                p.setForce(new Force2D(new double[]{x*r.nextInt(10000) * 0.01, y*r.nextInt(10000) * 0.01}));
                getSim().getCONTAINER().addPartical(p);
            }
        }

    }

}
