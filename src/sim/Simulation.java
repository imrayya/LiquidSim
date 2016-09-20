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
package sim;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import sim.collider.Collider;
import sim.partical.Partical;

/**
 *
 * @author Kareem Horstink
 * @version 0.11
 */
public class Simulation extends Observable {

    public static int tick = 0;
    public boolean processing = false;
    private final ParticalHolder CONTAINER;
    private boolean pause = false;
    private final Timer TIMER;
    private final BlockingQueue<Partical> toBeRomeved = new LinkedBlockingQueue<>();

    public Simulation() {
        this.CONTAINER = new ParticalHolder();
        this.TIMER = new Timer();
        TIMER.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                if (!processing) {
                    simulationLoop();
                } else {
                    System.out.println("still processing: " + tick);
                }
                tick++;
            }
        }, 0, GlobalSetting.getTickLength());

    }

    public ParticalHolder getCONTAINER() {
        return CONTAINER;
    }

    private void simulationLoop() {
        processing = true;
        if (!pause) {
            //Sets the predicted location based on previous tick
            Iterator<Partical> i = CONTAINER.getIteratorP();
            while (i.hasNext()) {
                Partical next = i.next();
                next.setPredicted();
            }

            //finds the colliders
            i = CONTAINER.getIteratorP();
            while (i.hasNext()) {
                Partical partical = i.next();
                Iterator<Collider> t = CONTAINER.getIteratorC();
                while (t.hasNext()) {
                    Collider collider = t.next();
                    if (collider.contains(partical.getPredicted())) {
                        partical.handleCollision(collider);
                    }
                }
            }

            //Handles collision with particulas
//            i = CONTAINER.getIteratorP();
//            while (i.hasNext()) {
//                Partical partical1 = i.next();
//                Iterator<Partical> j = CONTAINER.getIteratorP();
//                while (j.hasNext()) {
//                    Partical partical2 = j.next();
//                    if (partical1 != partical2 && partical1.detectCollision(partical2)) {
//                        partical1.handleCollision(partical2);
//                    }
//                }
//            }
            i = CONTAINER.getIteratorP();
            while (i.hasNext()) {
                Partical next = i.next();
                next.setPosition(next.getPredicted());
                if (next.isKill()) {
                    toBeRomeved.add(next);
                }
            }

            //resets the external forces on the object
            i = CONTAINER.getIteratorP();
            while (i.hasNext()) {
                Partical partical = i.next();
                if (!partical.isKill()) {
                    partical.resetExternalForce();
                }
            }

            for (int j = 0; j < toBeRomeved.size(); j++) {
                try {
                    CONTAINER.removePartical(toBeRomeved.take());
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
            CONTAINER.triggerAdd();
            setChanged();
            notifyObservers();
        }
        processing = false;
    }

    protected List<Partical> findNeibours(Partical p) {
        return null;
    }

    /**
     * Get the value of pause
     *
     * @return the value of pause
     */
    public boolean isPause() {
        return pause;
    }

    /**
     * Set the value of pause
     *
     * @param pause new value of pause
     */
    public void setPause(boolean pause) {
        this.pause = pause;
    }

}
//http://mmacklin.com/pbf_sig_preprint.pdf
// 1: for all particles i do
//    2: apply forces vi ⇐ vi +∆tfext(xi)
//    3: predict position x∗i ⇐ xi +∆tvi
// 4: end for
// 5: for all particles i do
//    6: find neighboring particles Ni(x∗i)
// 7: end for
// 8: while iter < solverIterations do
//    9: for all particles i do
//       10: calculate λi
//    11: end for
//    12: for all particles i do
//       13: calculate ∆pi
//       14: perform collision detection and response
//    15: end for
//    16: for all particles i do
//        17: update position x∗i ⇐ x∗i +∆pi
//    18: end for
// 19: end while
// 20: for all particles i do
//    21: update velocity vi ⇐ 1/∆t x∗i −xi
//    22: apply vorticity confinement and XSPH viscosity
//    23: update position xi ⇐ x∗i
// 24: end for

