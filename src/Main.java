import auxiliary.IMatrix;
import auxiliary.MatrixImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiFunction;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Cell;
import model.CellularAutomataBoard;
import model.CellularAutomataModelImpl;
import model.ECellState;
import model.EqualsAboveNeighborRule;
import model.ICell;
import model.ICellularAutomataBoard;
import model.ICellularAutomataModel;
import model.ICellularAutomataRule;
import model.LeftAndRightOn;
import view.CellularAutomataGUIView;
import view.CellularAutomataTextView;
import view.GUICellularAutomataBoard;
import view.ICellularAutomataView;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
//    ICellularAutomataModel mdl = new CellularAutomataModelImpl(10, 10,
//        new MatrixImpl<ICell>().mapInd((i, j) ->
//        { if (i % 2 == j % 2) {
//        return new Cell(i, j, ECellState.OFF);
//        }
//        else {
//         return new Cell(i, j, ECellState.ON);
//        }
//        }),
//        null);

    IMatrix<ICell> cells = new MatrixImpl<>(((i, j) -> {
      if (i % 2 == j % 2) {
        return new Cell(i, j, ECellState.OFF);
      } else {
        return new Cell(i, j, ECellState.ON);
      }
    }), 10, 10);
    ICellularAutomataBoard board = new CellularAutomataBoard(cells);

    ICellularAutomataModel mdl = new CellularAutomataModelImpl(10, 10, board,
        new LeftAndRightOn(board));


    CellularAutomataGUIView.setDefaultLookAndFeelDecorated(true);
    CellularAutomataGUIView GUI = new CellularAutomataGUIView(mdl);

    GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GUI.setVisible(true);

    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

    } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
        | IllegalAccessException e) {
      throw new IllegalArgumentException("could not open GUI");
    }

    // ICellularAutomataView vw = new CellularAutomataTextView(mdl);

   // System.out.println(vw.toString());

//    Scanner scanner = new Scanner(System.in);
//    if (scanner.hasNext()) {
//      String inp = scanner.next();
//      if (inp.equals("iterate")) {
//
//        for (int i = 0; i < 2000; i++) {
//          Runtime.getRuntime().exec("cls");
//          mdl.iterate();
//
//          System.out.println(vw.toString());
//        }
//      }
//    }


  }

}
