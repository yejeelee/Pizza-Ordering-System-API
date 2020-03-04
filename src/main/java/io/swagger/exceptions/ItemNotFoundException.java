package io.swagger.exceptions;

public abstract class ItemNotFoundException extends Exception {
  private static final long serialVersionUID = 554781777096647901L;

  public ItemNotFoundException(String message) {
    super(message);
  }
}
