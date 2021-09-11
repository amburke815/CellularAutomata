package model;

import auxiliary.Utils;

public class Posn implements IPair<Integer, Integer>{

  private final Integer first;
  private final Integer second;

  public Posn(Integer first, Integer second) {
    this.first = Utils.checkNotNull(first, "Cannot make a pair with a null first element");
    this.second = Utils.checkNotNull(second, "Cannot make a pair with a null second element");
  }


  @Override
  public Integer first() {
    return first;
  }

  @Override
  public Integer second() {
    return second;
  }

  @Override
  public IPair<Integer, Integer> swap() {
    return new Posn(second, first);
  }
}
