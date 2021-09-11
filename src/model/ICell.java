package model;

import java.util.List;

public interface ICell {

  /**
   * Gets the cell's state
   */
  ECellState state();

  /**
   * Gets the cells x pos'n, indexed from 0, left to right
   *
   * @return
   */
  int col();

  /**
   * Gets the cell's y pos'n, indexed from 0, top to bottom
   *
   * @return
   */
  int row();

  /**
   * Turn this cell's state to OFF
   */
  void off();

  /**
   * Turn this cell's state to ON
   */
  void on();

  /**
   * Returns the position (y,x)<-->(row, col), in logical coordinates indexed from 0 in the
   * upper left corner of the grid
   * @return
   */
  IPair<Integer, Integer> posn();



  //~~~~~~~~~~~~~~~~~~~~ Static convenience methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~

}
