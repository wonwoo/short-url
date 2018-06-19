package ml.wonwoo.shorturl.web;

import ml.wonwoo.shorturl.domain.Short;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
public class ShortController {

  private final ShortService shortService;

  public ShortController(ShortService shortService) {
    this.shortService = shortService;
  }

  @ModelAttribute("host")
  String host(@Value("${server.domain}") String domain) {
    return domain;
  }

  @GetMapping("/")
  String index() {
    return "index";
  }

  @PostMapping("/")
  String shortUri(@RequestParam URI uri, Model model) {
    model.addAttribute("uriShort", shortService.shortUri(uri));
    return "index";
  }

  @GetMapping("/{code}")
  ResponseEntity<?> shortUri(@PathVariable String code) {
    Short shortUri = this.shortService.shortUri(code)
        .orElseThrow(() -> new CodeNotFoundException(code));
    return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
        .location(shortUri.getUri()).build();
  }
}
