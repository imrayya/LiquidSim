/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import sim.GlobalSetting;
import sim.Simulation;
import sim.emitter.Emitter;
import sim.emitter.Emitter2D;
import sim.position.Position;

/**
 *
 * @author Kareem
 */
public class MouseList implements MouseListener {

    private Simulation sim;
    private Emitter emitter;
    private Timer timer;

    public MouseList(Simulation sim) {
        this.sim = sim;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (emitter == null) {
            switch (GlobalSetting.getCalculationMode()) {
                case GlobalSetting.PLANER:
                    emitter = new Emitter2D(sim, new Position(e.getX(), e.getY()), 1, 5);
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
        }, 0, GlobalSetting.getDeltaT() * 10);
    }

    private void create() {

        emitter.create();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        timer.cancel();
        timer = null;
        emitter = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
