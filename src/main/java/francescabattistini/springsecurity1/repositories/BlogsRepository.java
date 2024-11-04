package francescabattistini.springsecurity1.repositories;

import francescabattistini.springsecurity1.entities.Author;
import francescabattistini.springsecurity1.entities.Blogpost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogsRepository extends JpaRepository<Blogpost, Integer> {
    List<Blogpost> findByAuthor(Author author);
}
