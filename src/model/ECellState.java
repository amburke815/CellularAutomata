package model;

public enum ECellState {

  ON('■'), OFF('□');

  private char txtSymbol;

  ECellState(char txtSymbol) {
    this.txtSymbol = txtSymbol;
  }

  public boolean isOn() {
    return this.txtSymbol == '■';
  }

  @Override
  public String toString() {
    return Character.toString(this.txtSymbol);
  }

}
