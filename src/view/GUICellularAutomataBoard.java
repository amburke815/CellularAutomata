package view;

import auxiliary.IMatrix;
import auxiliary.Utils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JPanel;
import model.CellularAutomataBoard;
import model.ICell;

public class GUICellularAutomataBoard extends JPanel {

  private int width = 100;
  private int height = 100;
  private IMatrix<ICell> cells;

  GUICellularAutomataBoard(int width, int height, IMatrix<ICell> cells) {
    this.width = Utils.checkIntBetween(width, 0, Integer.MAX_VALUE);
    this.height = Utils.checkIntBetween(height, 0, Integer.MAX_VALUE);
    this.cells = Utils.checkNotNull(cells, "Cannot make a GUICellularAutomataBoard with null "
        + "cells");
  }




  @Override
  protected void paintComponent(Graphics g) {
    int SCALE = 30;
    int SZ = SCALE * 2;
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    cells.mapOp(c ->  { // might be able to delete mapOp(.)


      if (c.state().isOn()) {
        g2.setColor(Color.BLACK);
      } else {
        g2.setColor(Color.WHITE);
      }

      g2.fillRect(c.col() * SCALE, c.row() * SCALE, SZ, SZ);



      return null; // to satisfy Lambda
    });

    Toolkit.getDefaultToolkit().sync();

  }




}
