package model;

import auxiliary.IMatrix;
import auxiliary.Utils;
import java.util.ArrayList;
import java.util.List;

public class CellularAutomataBoard implements ICellularAutomataBoard {
  private final IMatrix<ICell> cells;

  public CellularAutomataBoard(IMatrix<ICell> cells) {
    this.cells = Utils.checkNotNull(cells, "Cannot create a CellularAutomataBoard with null " +
        "cells");
  }

  @Override
  public ECellState nextCellState(int row, int col) {
    return null;
  }

  @Override
  public ICell getCell(int row, int col)
      throws NoSuchCellException {
    try {
      return cells.getElement(row, col);
    } catch (IllegalArgumentException e) {
      throw new NoSuchCellException("No cell at row: " + row + ", col: " + col);
    }
  }

  @Override
  public List<ICell> eightNeighbors(int row, int col) {
    List<ICell> eightNeighbors = new ArrayList<>();

    // loop through indices that make a 3x3 square around this Cell
    for (int i = Utils.checkIntBetween(row, 0,
        CellularAutomataModelImpl.WIDTH) - 1; i < row + 1; i++) {
      for (int j = Utils.checkIntBetween(col, 0,
          CellularAutomataModelImpl.HEIGHT) - 1; j < col + 1; j++) {

        try {
          eightNeighbors.add(getCell(i, j));
        } catch (NoSuchCellException e) {
          // do nothing, omit the nonexistent Cell from the returned list
        }

      }
    }

    return eightNeighbors;
  }

  @Override
  public List<ICell> eightNeighbors(ICell centerCell) {
    return eightNeighbors(centerCell.row(), centerCell.col());
  }

  @Override
  public ICell right(int row, int col) {
    return ifExists(row, col + 1);
  }

  @Override
  public ICell upperRight(int row, int col) {
    return ifExists(row - 1, col + 1);
  }

  @Override
  public ICell above(int row, int col) {
    return ifExists(row - 1, col);
  }

  @Override
  public ICell upperLeft(int row, int col) {
    return ifExists(row - 1, col - 1);
  }

  @Override
  public ICell left(int row, int col) {
    return ifExists(row, col - 1);
  }

  @Override
  public ICell lowerLeft(int row, int col) {
    return ifExists(row + 1, col - 1);
  }

  @Override
  public ICell below(int row, int col) {
    return ifExists(row, col + 1);
  }

  @Override
  public ICell lowerRight(int row, int col) {
    return ifExists(row + 1, col + 1);
  }

  @Override
  public IMatrix<ICell> cellMatrix() {
    return cells;
  }

  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ private helper methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  /**
   * Checks if the cell queried is null and returns null if so. Otherwise, returns the cell at the
   * specified location.
   *
   * @param row
   * @param col
   * @return
   */
  private ICell ifExists(int row, int col) {
    try {
      return getCell(row, col);
    } catch (NoSuchCellException e) {
      return null;
    }
  }

}
