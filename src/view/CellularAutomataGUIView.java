package view;

import auxiliary.IMatrix;
import auxiliary.MatrixImpl;
import auxiliary.Utils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DebugGraphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Cell;
import model.ECellState;
import model.ICell;
import model.ICellularAutomataModel;

public class CellularAutomataGUIView extends JFrame
    implements ICellularAutomataView, ItemListener, ActionListener {

  private static final int SCREEN_WIDTH = 1200;
  private static final int SCREEN_HEIGHT = 600;
  public static final String GITHUB_URL = "change me later";

  private final ICellularAutomataModel mdl;
  private final Map<String, ICellularAutomataGUICommand> commandsMap = new HashMap<>();

  //!~~~~~~~~~~~~~~~~~~~~~~~~GUI elements~~~~~~~~~~~~~~~~~~~~~~~~~~~~!
  private final JPanel mainPanel;
  private final JMenuBar menuRibbon;
  private final JPanel rulePanel;
  private GUICellularAutomataBoard board;

  /**
   * Set up the GUI frame
   */
  public CellularAutomataGUIView(ICellularAutomataModel mdl) {
    super();
    this.mdl = Utils.checkNotNull(mdl, "Cannot create a CellularAutomataGUIView with "
        + "a null model");
    setTitle("Cellular Automata");
    setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

    this.mainPanel = new JPanel();
    this.mainPanel.setLayout(new BorderLayout());

    this.menuRibbon = initMenusBar();
    mainPanel.add(menuRibbon, BorderLayout.PAGE_START);

    this.rulePanel = initRulePanel();
    mainPanel.add(rulePanel, BorderLayout.WEST);

    IMatrix<ICell> cells = new MatrixImpl<>(((i, j) -> {
      if (i % 2 == j % 2) {
        return new Cell(i, j, ECellState.OFF);
      } else {
        return new Cell(i, j, ECellState.ON);
      }
    }), 10, 10);
    this.board = new GUICellularAutomataBoard(100, 100, cells);

    mainPanel.add(board);

    add(mainPanel);

  }

  private JMenuBar initMenusBar() {
    JMenuBar _menuRibbon = new JMenuBar();

    // the rules menu
    JMenu rulesMenu = new JMenu("Rules");
    rulesMenu.setMnemonic(KeyEvent.VK_R);
    JMenuItem presetRulesItem = new JMenuItem("Preset rules...");
    presetRulesItem.addActionListener(this);
    rulesMenu.add(presetRulesItem);
    JMenuItem customRuleItem = new JMenuItem("Custom rule...");
    customRuleItem.addActionListener(this);
    rulesMenu.add(customRuleItem);

    _menuRibbon.add(rulesMenu);

    // the board menu
    JMenu boardMenu = new JMenu("Board");
    boardMenu.setMnemonic(KeyEvent.VK_B);
    JMenuItem dimensionsItem = new JMenuItem("Dimensions...");
//    dimensionsItem.addActionListener(this);
//    dimensionsItem.setActionCommand("dimensions");
//    commandsMap.putIfAbsent("dimensions", new TestButtonGUICommand(mdl));
    makeAction(dimensionsItem, this, "dimensions",
        new TestButtonGUICommand(mdl, this));
    boardMenu.add(dimensionsItem);
    _menuRibbon.add(boardMenu);

    // the control menu
    JMenu controlMenu = new JMenu("Control");
    controlMenu.setMnemonic(KeyEvent.VK_C);

    JMenuItem iterateItem = new JMenuItem("Iterate");
    makeAction(iterateItem, this, "iterate",
        new IterateCommand(mdl, this));
    controlMenu.add(iterateItem);

    _menuRibbon.add(controlMenu);

    // the about menu
    JMenu aboutMenu = new JMenu("About");
    aboutMenu.setMnemonic(KeyEvent.VK_A);

    JMenuItem aboutTheProjectItem = new JMenuItem("About the project");
    makeAction(aboutTheProjectItem, this, "about the project",
        new AboutTheProjectCommand(mdl, this));
    aboutMenu.add(aboutTheProjectItem);

    JMenuItem whatIsCAItem = new JMenuItem("What is a Cellular Automata?");
    makeAction(whatIsCAItem, this, "what is CA",
        new WhatIsCACommand(mdl, this));
    aboutMenu.add(whatIsCAItem);

    JMenuItem gitHubSourceItem = new JMenuItem("GitHub source");
    makeAction(gitHubSourceItem, this, "github source",
        new GitHubSourceCommand(mdl, this));
    aboutMenu.add(gitHubSourceItem);

    _menuRibbon.add(aboutMenu);

    JMenuItem helpItem = new JMenuItem("Help");
    makeAction(helpItem, this, "Help",
        new HelpCommand(mdl, this));
    aboutMenu.add(helpItem);

    return _menuRibbon;
  }

  private JPanel initRulePanel() {
    JPanel _rulePanel = new JPanel();
    _rulePanel.setLayout(new BoxLayout(_rulePanel, 0));
    _rulePanel.setBorder(BorderFactory.createTitledBorder("Rule"));
    // TODO: add grid element here for nbhd, this is a visual placeholder
    // Rectangle CABoard = new Rectangle(25, 25);
    // rulePanel.add(CABoard);
    JButton setRuleButton = new JButton("set rule");
    makeAction(setRuleButton, this, "set rule",
        new SetRuleCommand(mdl, this));
    _rulePanel.add(setRuleButton);
    Appendable logicExpression = new StringBuilder("EDIT ME");
    JTextField logicTextField = new JTextField(logicExpression.toString());
    logicTextField.setEditable(false);
    logicTextField.setBorder(BorderFactory.createTitledBorder("Logic:"));
    _rulePanel.add(logicTextField);


    return _rulePanel;

  }


  @Override
  public String toString() {
    return null; // TODO
  }

  @Override
  public void renderBoard() {
//    mainPanel.remove(board);

    this.board = new GUICellularAutomataBoard(1,1,
        new MatrixImpl<>(new Cell(0,0, ECellState.ON), 1, 1));
    //mainPanel.add(board);

    this.board.repaint();
    // FIXME
  }

  @Override
  public void itemStateChanged(ItemEvent e) {

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if ( !(commandsMap.containsKey(e.getActionCommand()))) {
      return;
    }

    commandsMap.get(e.getActionCommand()).execute();
  }

  public void errorPopup(String dialogMsg, String title)
      throws IllegalArgumentException {
    JOptionPane.showMessageDialog(CellularAutomataGUIView.this,
        Utils.checkNotNull(dialogMsg, "cannot display a popup window"
            + " with a null dialog message"),
        Utils.checkNotNull(title, "cannot display a popup window"
            + " with a null title"),
        JOptionPane.ERROR_MESSAGE);
  }

  //!~~~~~~~~~~~~~~~~~~~~~~~private methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~!
  private void makeAction(AbstractButton GUIElement, ActionListener listener, String commandName,
      ICellularAutomataGUICommand cmd) {
    GUIElement.addActionListener(listener);
    GUIElement.setActionCommand(commandName);
    commandsMap.putIfAbsent(commandName, cmd);
  }
}
