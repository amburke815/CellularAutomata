package view;

import model.ICellularAutomataModel;

public class AboutTheProjectCommand extends ACellularAutomataGUICommand {

  AboutTheProjectCommand(ICellularAutomataModel mdl, CellularAutomataGUIView frame) {
    super(mdl, frame);
  }

  @Override
  public void execute() {
    // bring up a popup window with a blurb about the project
  }
}
