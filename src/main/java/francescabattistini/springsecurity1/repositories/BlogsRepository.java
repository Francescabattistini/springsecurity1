package francescabattistini.springsecurity1.repositories;

import epicode.u5d8hw.entities.Author;
import epicode.u5d8hw.entities.Blogpost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogsRepository extends JpaRepository<Blogpost, Integer> {
    List<Blogpost> findByAuthor(Author author);
}
