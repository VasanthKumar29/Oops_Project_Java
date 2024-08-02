package SnackGame;

public class Node {
  private int row;
  private int col;

  Node(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int getColumn() {
    return col;
  }

  public int getRow() {
    return row;
  }
}
