package io.swagger.exceptions;

public class CartNotAtStoreException extends ItemNotFoundException {
  public CartNotAtStoreException(String message) {
    super(message);
  }
}
