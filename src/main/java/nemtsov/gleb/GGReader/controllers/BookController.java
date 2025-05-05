package nemtsov.gleb.GGReader.controllers;

import jakarta.validation.Valid;
import nemtsov.gleb.GGReader.models.Book;
import nemtsov.gleb.GGReader.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
                          BindingResult bindingResult,
                          @RequestParam("file") MultipartFile file) throws IOException {

        if (bindingResult.hasErrors())
            return "book/addBook";

        book.setContent(file.getBytes());
        bookService.addBook(book);

        return "redirect:/homePage";
    }

    @GetMapping("/goReadRoom")
    public String readRoom(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book/readRoom";
    }

    @GetMapping("/goReadRoom")
    public String read(@RequestParam int id, Model model) {
        model.addAttribute("bookId", id);
        return "book/show";
    }

}
