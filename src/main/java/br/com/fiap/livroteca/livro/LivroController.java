package br.com.fiap.livroteca.livro;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("livros")
public class LivroController {
    
    @Autowired
    LivroRepository repository;

    @GetMapping("/{id}")
    @ResponseBody
    public String getOneById(@PathVariable UUID id){
        return "Página de detalhes do livro " + id ;
    }

    @GetMapping("/admin")
    public String indexAdmin(Model model){
        model.addAttribute("livros", repository.findAll());
        return "livro/admin";
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("livros", repository.findAll());
        return "livro/index";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable UUID id, RedirectAttributes redirect){
        var livro = repository.findById(id);

        if(livro.isEmpty()){
            redirect.addFlashAttribute("mensagem", "Erro ao apagar. Livro não encontrado");
            return "redirect:/livros/admin";

        }

        repository.deleteById(id);
        redirect.addFlashAttribute("mensagem", "Livro deletado com sucesso");
        return "redirect:/livros/admin";
    }

    @GetMapping("/admin/new")
    public String form(Livro livro){
        return "livro/form";
    }

    @PostMapping("/admin")
    public String create(@Valid Livro livro, BindingResult result, RedirectAttributes redirect){
        if(result.hasErrors()){
            return "livro/form";
        }

        livro.setDisponibilidade(true);
        repository.save(livro);
        redirect.addFlashAttribute("mensagem", "Livro cadastrado com sucesso");

        return "redirect:/livros/admin";
    }

    
}
