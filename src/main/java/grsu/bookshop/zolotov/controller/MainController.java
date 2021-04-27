package grsu.bookshop.zolotov.controller;

import grsu.bookshop.zolotov.model.Book;
import grsu.bookshop.zolotov.model.User;
import grsu.bookshop.zolotov.repository.BookRepository;
import grsu.bookshop.zolotov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private BookRepository bookRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @PostMapping("/greeting")
    public String gr() {
        return "greeting";
    }

    @GetMapping("/home")
    public String homePage(@RequestParam(required = false, defaultValue = "") String filter,
            Model model) {
        Iterable<Book> books;

        if (filter != null && !filter.isEmpty())
            books = bookRepository.findBookByBookname(filter);
        else
            books =  bookRepository.findAll();

        model.addAttribute("books", books);
        model.addAttribute("filter", filter);
        return "home";
    }

    @PostMapping("/home")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam("file") MultipartFile file,
                      Model model) throws IOException {
        Book book = new Book(name, description);

        if(file != null && !file.isEmpty() && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            book.setFilename(resultFilename);
        }
        bookRepository.save(book);



        Iterable<Book> books = bookRepository.findAll();

        model.addAttribute("books", books);
        return "home";
    }


}