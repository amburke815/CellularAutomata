package model;

import auxiliary.IMatrix;
import auxiliary.MatrixImpl;
import auxiliary.Utils;
import java.util.ArrayList;
import java.util.List;

public class CellularAutomataModelImpl implements ICellularAutomataModel {

  private static final int DEFAULT_DIMENSION = 20;
  public static int WIDTH; // TODO: make this final later and track down error
  public static int HEIGHT; // TODO: make this final later and track down error

  private ICellularAutomataBoard cells; // TODO: make this final later
  private ICellularAutomataRule rule; // TODO: make this final later

  /**
   * Sets all cells to OFF
   *
   * @param WIDTH
   * @param HEIGHT
   */
  public CellularAutomataModelImpl(int WIDTH, int HEIGHT, ICellularAutomataRule rule) {
    IMatrix<ICell> _cellsMatrix = new MatrixImpl<>(null, WIDTH, HEIGHT);
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        _cellsMatrix.updateEntry(new Cell(i, j), i, j);
      }
    }

    this.WIDTH = Utils.checkIntBetween(WIDTH, 0, Integer.MAX_VALUE);
    this.HEIGHT = Utils.checkIntBetween(HEIGHT, 0, Integer.MAX_VALUE);
    this.cells = new CellularAutomataBoard(_cellsMatrix);
    this.rule = Utils.checkNotNull(rule, "Cannot create a CellularAutomataModelImpl with a "
        + "null rule");
  }

  /**
   * Sets the cells to a desired configuration
   *
   * @param WIDTH
   * @param HEIGHT
   * @param cells
   */
  public CellularAutomataModelImpl(int WIDTH, int HEIGHT, ICellularAutomataBoard cells,
      ICellularAutomataRule rule) {
    this.WIDTH = Utils.checkIntBetween(WIDTH, 0, Integer.MAX_VALUE);
    this.HEIGHT = Utils.checkIntBetween(HEIGHT, 0, Integer.MAX_VALUE);
    this.cells = Utils.checkNotNull(cells, "Cannot construct a cellular automata model with a "
        + "null grid of cells");
    this.rule = rule; /*Utils.checkNotNull(rule, "Cannot create a CellularAutomataModelImpl with a "
        + "null rule");*/ // TODO: uncomment this check and resolve circular reference issue between
    // rule and model ctors/fields so that a model can be declared in one line with a rule
  }

  /**
   * For a square grid
   *
   * @param dimension
   */
  public CellularAutomataModelImpl(int dimension, ICellularAutomataRule rule) {
    this(dimension, dimension, rule);
  }

  /**
   * Square grid with default size
   */
  public CellularAutomataModelImpl(ICellularAutomataRule rule) {
    this(DEFAULT_DIMENSION, rule);
  }

  @Override
  public void iterate() {
    this.cells = new CellularAutomataBoard(
        cells.cellMatrix().map(c -> {
          if (rule.test(c)) {
            return new Cell(c.row(), c.col(), ECellState.ON);
          } else {
            return new Cell(c.row(), c.col(), ECellState.OFF);
          }
        }));
    this.rule = new LeftAndRightOn(cells); // TODO: bandaid fix, this is where the big issue occurs
    // maybe use factory method to fix

  }

  @Override
  public ICellularAutomataBoard getCells() {
    return this.cells;
  }

  @Override
  public ECellState nextCellState(int row, int col) {
    return null;
  }

  @Override
  public ICell getCell(int row, int col)
      throws NoSuchCellException {
    return cells.getCell(row, col);
  }

  @Override
  public List<ICell> eightNeighbors(int row, int col) {
    List<ICell> eightNeighbors = new ArrayList<>();

    // loop through indices that make a 3x3 square around this Cell
    for (int i = Utils.checkIntBetween(row, 0, WIDTH) - 1; i < row + 1; i++) {
      for (int j = Utils.checkIntBetween(col, 0, HEIGHT) - 1; j < col + 1; j++) {

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
    return cells.eightNeighbors(centerCell);
  }

  @Override
  public ICell right(int row, int col) {
    return cells.right(row, col);
  }

  @Override
  public ICell upperRight(int row, int col) {
    return cells.upperRight(row, col);
  }

  @Override
  public ICell above(int row, int col) {
    return cells.above(row, col);
  }

  @Override
  public ICell upperLeft(int row, int col) {
    return cells.upperLeft(row, col);
  }

  @Override
  public ICell left(int row, int col) {
    return cells.left(row, col);
  }

  @Override
  public ICell lowerLeft(int row, int col) {
    return cells.lowerLeft(row, col);
  }

  @Override
  public ICell below(int row, int col) {
    return cells.below(row, col);
  }

  @Override
  public ICell lowerRight(int row, int col) {
    return cells.lowerRight(row, col);
  }

  //!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ private helper methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!

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

  //!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ helper classes ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!

}
