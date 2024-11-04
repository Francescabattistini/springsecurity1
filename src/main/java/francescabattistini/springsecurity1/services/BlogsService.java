package francescabattistini.springsecurity1.services;

import francescabattistini.springsecurity1.entities.Author;
import francescabattistini.springsecurity1.entities.Blogpost;
import francescabattistini.springsecurity1.exceptions.NotFoundException;
import francescabattistini.springsecurity1.payloads.NewBlogPostPayload;
import francescabattistini.springsecurity1.repositories.BlogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogsService {
    @Autowired
    private BlogsRepository blogsRepository;
    @Autowired
    private AuthorsService authorsService;

    public Blogpost save(NewBlogPostPayload body) {
        Author author = authorsService.findById(body.getAuthorId());
        Blogpost newBlogPost = new Blogpost();
        newBlogPost.setReadingTime(body.getReadingTime());
        newBlogPost.setContent(body.getContent());
        newBlogPost.setTitle(body.getTitle());
        newBlogPost.setAuthor(author);
        newBlogPost.setCategory(body.getCategory());
        newBlogPost.setCover("http://picsum.photos/200/300");
        return blogsRepository.save(newBlogPost);
    }

    public List<Blogpost> getBlogs() {
        return blogsRepository.findAll();
    }

    public Blogpost findById(int id) {
        return blogsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Blogpost found = this.findById(id);
        blogsRepository.delete(found);
    }

    public Blogpost findByIdAndUpdate(int id, NewBlogPostPayload body) {
        Blogpost found = this.findById(id);

        found.setReadingTime(body.getReadingTime());
        found.setContent(body.getContent());
        found.setTitle(body.getTitle());
        found.setCategory(body.getCategory());

        if(found.getAuthor().getId()!= body.getAuthorId()) {
            Author newAuthor = authorsService.findById(body.getAuthorId());
            found.setAuthor(newAuthor);
        }

        return blogsRepository.save(found);
    }

    public List<Blogpost> findByAuthor(int authorId){
        Author author = authorsService.findById(authorId);
        return blogsRepository.findByAuthor(author);
    }
}
