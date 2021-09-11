package view;

import model.ICellularAutomataModel;

public class HelpCommand extends ACellularAutomataGUICommand {

  HelpCommand(ICellularAutomataModel mdl, CellularAutomataGUIView frame) {
    super(mdl, frame);
  }

  @Override
  public void execute() {
    frame.errorPopup("This is a help command", "help message");
  }
}
