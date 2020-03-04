package io.swagger.api;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
public class NotFoundException extends ApiException {
  private static final long serialVersionUID = -8000903005649088467L;
  public int code;

  public NotFoundException(int code, String msg) {
    super(code, msg);
    this.code = code;
  }
}
