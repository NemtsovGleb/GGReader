package nemtsov.gleb.GGReader.services;

import nemtsov.gleb.GGReader.models.Book;
import nemtsov.gleb.GGReader.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(int id) throws ChangeSetPersister.NotFoundException {
        return bookRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }


}
