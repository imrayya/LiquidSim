/*
 * Copyright (C) 2015 Karem Horstink
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
package sim;

import java.util.Iterator;
import java.util.LinkedList;
import sim.force.Force2D;
import sim.partical.Partical;
import sim.partical.Partical2D;
import sim.position.Position;

/**
 *
 * @author Karem Horstink
 * @version 0.1
 */
public class ParticalHolder {

    private final LinkedList<Partical> PARTICLES;

    public ParticalHolder() {
        this.PARTICLES = new LinkedList<>();
        PARTICLES.add(new Partical2D(new Position(250, 400)));
        PARTICLES.getFirst().setForce(new Force2D(new double[]{10, 0}));
        PARTICLES.add(new Partical2D(new Position(250, 250)));
        PARTICLES.getLast().setForce(new Force2D(new double[]{-10, 0}));
    }

    public Iterator getIterator() {
        return PARTICLES.iterator();
    }

    protected Partical removePartical(Partical p) {
        return PARTICLES.remove(PARTICLES.indexOf(p));
    }

    protected void addPartical(Partical p) {
        PARTICLES.add(p);
    }

    protected int size() {
        return PARTICLES.size();
    }
}
