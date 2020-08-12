package com.projeto.departamentoSpringBoot.web.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeControle {

    //metodo para redirecionar para a pagina principal de hoje
    @GetMapping("/")
    public String home(){
        return "home";
    }


}
