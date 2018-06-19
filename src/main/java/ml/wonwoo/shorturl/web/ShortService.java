package ml.wonwoo.shorturl.web;

import ml.wonwoo.shorturl.domain.Short;
import ml.wonwoo.shorturl.domain.ShortRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;
import java.util.Random;

@Service
public class ShortService {

  private static final String ALPHABET = "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_";
  private static final int BASE = ALPHABET.length();
  private final Random random = new Random();

  private final ShortRepository shortRepository;

  public ShortService(ShortRepository shortRepository) {
    this.shortRepository = shortRepository;
  }

  Short shortUri(URI uri) {
    String scheme = uri.getScheme();
    Short shortUri = new Short(scheme == null ?
        URI.create("http://" + uri) : uri, encode(Math.abs(this.random.nextInt())));
    return shortRepository.save(shortUri);
  }

  Optional<Short> shortUri(String code) {
    return shortRepository.findByCode(code);
  }

  private String encode(int num) {
    StringBuilder str = new StringBuilder();
    while (num > 0) {
      str.insert(0, ALPHABET.charAt(num % BASE));
      num = num / BASE;
    }
    return str.toString();
  }
}
