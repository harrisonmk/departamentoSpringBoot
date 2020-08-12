package com.projeto.departamentoSpringBoot.web.controle;

import com.projeto.departamentoSpringBoot.dominio.Departamento;
import com.projeto.departamentoSpringBoot.servico.DepartamentoServico;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoControle {

    @Autowired
    private DepartamentoServico servico;

   /* @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(new FuncionarioValidator());
    }*/
    
    //Rediciona para a pagina de cadastro
    @GetMapping("/cadastrar")
    public String cadastrar(Departamento departamento) {

        return "departamento/cadastro";

    }

    
    
    //Redireciona para a pagina de listagem de departamentos
    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("departamentos", servico.buscarTodos());
        return "departamento/lista";

    }

    
    
    //metodo para salvar um departamento
    @PostMapping("/salvar")
    public String salvar(@Valid Departamento departamento,BindingResult result, RedirectAttributes attr) {

        
        if(result.hasErrors()){
            return "departamento/cadastro";
        }
        
        servico.salvar(departamento);
        attr.addFlashAttribute("success", "Departamento inserido com sucesso");
        return "redirect:/departamentos/cadastrar";

    }

    
    
    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) { //pathVaviable recupera o id da url

        model.addAttribute("departamento", servico.buscarPorId(id));

        return "departamento/cadastro";
    }

    
    
    @PostMapping("/editar")
    public String editar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {

        
         if(result.hasErrors()){
            return "departamento/cadastro";
        }
        
        servico.editar(departamento);
        attr.addFlashAttribute("success", "Departamento editado com sucesso");
        return "redirect:/departamentos/cadastrar";
    }

    
    
    
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {

        if (servico.departamentoTemCargos(id)) {
            model.addAttribute("fail", "Departamento nao removido. possui cargo(s) vinculado(s).");
        } else {
            servico.excluir(id);
            model.addAttribute("success", "Departamento excluido com sucesso.");
        }

        return listar(model);
    }

    
    
    
}
