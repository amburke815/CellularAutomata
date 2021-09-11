package model;

public interface IPair<X, Y> {

  X first();

  Y second();

  IPair<Y, X> swap();
}
