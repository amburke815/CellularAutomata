package view;

import model.ICellularAutomataModel;

public class TestButtonGUICommand extends ACellularAutomataGUICommand {

  TestButtonGUICommand(ICellularAutomataModel mdl, CellularAutomataGUIView frame) {
    super(mdl, frame);
  }

  @Override
  public void execute() {
    System.out.println("executed");
  }
}
