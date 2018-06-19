package ml.wonwoo.shorturl.web;

public class CodeNotFoundException extends RuntimeException {
  private final String code;

  public CodeNotFoundException(String code) {
    this.code = code;
  }

  @Override
  public String getMessage() {
    return code + " not found";
  }
}
