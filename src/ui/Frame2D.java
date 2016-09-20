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
package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import sim.GlobalSetting;
import sim.Simulation;
import sim.emitter.Emitter;
import sim.emitter.Emitter2D;
import sim.shape.position.Position2D;

/**
 *
 * @author Kareem Horstink
 * @version 0.15
 */
public class Frame2D extends JFrame implements Observer {

    protected final Panel panel;
    private Emitter emitter;
    private Simulation sim;
    private int x = -1;
    private int y = -1;

    public Frame2D(Simulation sim) throws NoModeSelectedException {
        this.sim = sim;
        this.panel = new Panel(sim);
        panel.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (x != -1 && y != -1) {
                    emitter.setPosition(new Position2D(e.getX(), panel.getHeight()-e.getY()));
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        panel.addMouseListener(new MouseListener() {

            private Timer timer;

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                if (emitter == null) {
                    switch (GlobalSetting.getCalculationMode()) {
                        case GlobalSetting.PLANER:
                            emitter = new Emitter2D(sim, new Position2D(x, panel.getHeight()-y), 2, 5);
                            break;
                        case GlobalSetting.SPACE:
                            emitter = null;
                            break;
                        default:
                            break;
                    }
                }
                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {

                    @Override
                    public void run() {
                        create();
                    }
                }, 0, GlobalSetting.getTickLength()* 10);
            }

            private void create() {

                emitter.create();

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                timer.cancel();
                timer = null;
                emitter = null;
                x = -1;
                y = -1;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        this.add(panel);
        sim.addObserver(this);
        setLocationRelativeTo(null);
        setSize(GlobalSetting.getWidth(), GlobalSetting.getHeight());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
        setDefaultCloseOperation(3);
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        panel.repaint();
    }

}
