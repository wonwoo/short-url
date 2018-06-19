package ml.wonwoo.shorturl.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URI;

@Document
public class Short {

  @Id
  private String id;

  private URI uri;

  private String code;

  Short() {}

  public Short(URI uri, String code) {
    this.uri = uri;
    this.code = code;
  }

  public String getId() {
    return id;
  }

  public URI getUri() {
    return uri;
  }

  public void setUri(URI uri) {
    this.uri = uri;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
