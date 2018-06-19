package ml.wonwoo.shorturl.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShortRepository extends MongoRepository<Short, String> {

  Optional<Short> findByCode(String code);
}
