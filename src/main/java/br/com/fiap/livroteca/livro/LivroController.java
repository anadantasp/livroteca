package br.com.fiap.livroteca.livro;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("livros")
public class LivroController {

    @GetMapping
    public String index(Model model){
        model.addAttribute("livros", List.of(
            "Livro 1",
            "Livro 2",
            "Livro 3",
            "Livro 4",
            "Livro 5",
            "Livro 6",
            "Livro 7",
            "Livro 8"
        ));
        return "livro/index";
    }
    
}
