package nemtsov.gleb.GGReader.controllers;

import jakarta.validation.Valid;
import nemtsov.gleb.GGReader.models.Book;
import nemtsov.gleb.GGReader.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BookService bookService;


    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/addBook")
    public String addBookPage(@ModelAttribute("book") Book Book) { return "book/addBook"; }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("book") @Valid Book book,
                          BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "book/addBook";


        bookService.addBook(book);

        return "redirect:/homePage";
    }
}
