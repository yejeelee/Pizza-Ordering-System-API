package io.swagger.exceptions;

public class SpecialNotFoundException extends ItemNotFoundException {
  private static final long serialVersionUID = 1920135874530621916L;

  public SpecialNotFoundException(String message) {
    super(message);
  }
}
