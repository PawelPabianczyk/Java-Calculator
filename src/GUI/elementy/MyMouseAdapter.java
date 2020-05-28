package GUI.elementy;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseAdapter implements MouseListener {

    private MyTextField poleR;

    public MyMouseAdapter(MyTextField poleR){
        this.poleR = poleR;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        poleR.setText("");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
