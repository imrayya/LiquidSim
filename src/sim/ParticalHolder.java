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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import sim.collider.Collider;
import sim.collider.Collider2D_Rect;
import sim.partical.Partical;

/**
 *
 * @author Karem Horstink
 * @version 0.1
 */
public class ParticalHolder {

    private final Stack<Partical> PARTICLES;
    private final Stack<Collider> COLLIDERS;
    private final Stack<Partical> TO_BE_ADDED;

    public ParticalHolder() {
        this.PARTICLES = new Stack<>();
        this.TO_BE_ADDED = new Stack<>();
        this.COLLIDERS = new Stack<>();
        COLLIDERS.add(new Collider2D_Rect(0, 100, 100, 100));
        COLLIDERS.add(new Collider2D_Rect(0, 700, 740, 40, 1));
        COLLIDERS.add(new Collider2D_Rect(0, 0, 40, 700, 1));
        COLLIDERS.add(new Collider2D_Rect(740, 0, 40, 740, 1));
        COLLIDERS.add(new Collider2D_Rect(40, 0, 700, 40, 1));

    }

    public Iterator getIteratorP() {
        return PARTICLES.iterator();
    }

    public Iterator getIteratorC() {
        return COLLIDERS.iterator();
    }

    protected Partical removePartical(Partical p) {
        PARTICLES.remove(p);
        return p;
    }

    public void addPartical(Partical p) {
        TO_BE_ADDED.push(p);
    }

    public void triggerAdd() {
        for (int i = 0; i < TO_BE_ADDED.size(); i++) {
            PARTICLES.push(TO_BE_ADDED.pop());
        }
    }

    protected int size() {
        return PARTICLES.size();
    }
}
