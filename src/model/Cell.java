package model;

import auxiliary.Utils;
import java.util.ArrayList;
import java.util.List;

// TODO: find a way for each cell's (row,col) to be unique
public class Cell implements ICell {

  private final int row;
  private final int col;
  private ECellState state;

  public Cell(int row, int col, ECellState state) { // TODO: enforce invariant that cells cannot be
    // set to have a (row,col) outside of the dimensions of the grid
    this.row = row; /*Utils.checkIntBetween(row, 0, CellularAutomataModelImpl.HEIGHT);*/
    this.col = col; /*Utils.checkIntBetween(col, 0, CellularAutomataModelImpl.WIDTH);*/
    this.state = Utils.checkNotNull(state, "Cannot construct a Cell with a null state");
  }

  /**
   * Sets the default state of the cell to OFF
   *
   * @param row
   * @param col
   */
  public Cell(int row, int col) {
    this(row, col, ECellState.OFF);
  }


  @Override
  public ECellState state() {
    return state;
  }

  @Override
  public int col() {
    return col;
  }

  @Override
  public int row() {
    return row;
  }

  @Override
  public void off() {
    this.state = ECellState.OFF;
  }

  @Override
  public void on() {
    this.state = ECellState.ON;
  }

  @Override
  public IPair<Integer, Integer> posn() {
    return new Posn(row, col);
  }








}
