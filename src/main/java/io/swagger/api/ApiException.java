package io.swagger.api;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
public class ApiException extends Exception {
  private static final long serialVersionUID = -2174637728997742359L;
  public int code;

  public ApiException(int code, String msg) {
    super(msg);
    this.code = code;
  }
}
