package io.swagger.exceptions;

public class ToppingNotFoundException extends ItemNotFoundException {
  private static final long serialVersionUID = 5032166290629164207L;

  public ToppingNotFoundException(String message) {
    super(message);
  }
}
