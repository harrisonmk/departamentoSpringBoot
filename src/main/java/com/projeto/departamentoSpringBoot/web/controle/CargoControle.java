package com.projeto.departamentoSpringBoot.web.controle;

import com.projeto.departamentoSpringBoot.dominio.Cargo;
import com.projeto.departamentoSpringBoot.dominio.Departamento;
import com.projeto.departamentoSpringBoot.servico.CargoServico;
import com.projeto.departamentoSpringBoot.servico.DepartamentoServico;
import com.projeto.departamentoSpringBoot.util.PaginacaoUtil;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cargos")
public class CargoControle {

    @Autowired
    CargoServico cargoServico;

    @Autowired
    DepartamentoServico departamentoServico;

    //Metodo para redirecionar para a pagina de cadastro de cargos
    @GetMapping("/cadastrar")
    public String cadastrar(Cargo cargo) {

        return "cargo/cadastro";

    }

    //Metodo para listar todos os cargos
    @GetMapping("/listar")
    public String listar(ModelMap model,@RequestParam("page") Optional<Integer> page,@RequestParam("dir") Optional<String> dir) {

        int paginaAtual = page.orElse(1);
        String ordem = dir.orElse("asc");
        PaginacaoUtil<Cargo> pageCargo = cargoServico.buscarPorPagina(paginaAtual, ordem);
        
        model.addAttribute("pageCargo", pageCargo);
        return "cargo/lista";

    }

    //Metodo para salvar um cargo
    @PostMapping("/salvar") //BindingResult serve para verificar se houve algum erro nas validacoes
    public String salvar(@Valid Cargo cargo,BindingResult result, RedirectAttributes attr) {

        if(result.hasErrors()){
            return "cargo/cadastro";
        }
        
        cargoServico.salvar(cargo);
        attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
        return "redirect:/cargos/cadastrar";

    }

    //Metodo para listar todos os departamentos no combobox
    @ModelAttribute("departamentos")
    public List<Departamento> listaDepartamentos() {

        return departamentoServico.buscarTodos();

    }

    //Metodo para editar, busca o objeto pelo id e redireciona para a pagina de cadastro
    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {

        model.addAttribute("cargo", cargoServico.buscarPorId(id));
        return "cargo/cadastro"; //pasta/arquito html

    }

    //Metodo para editar Post
    @PostMapping("/editar") //BindingResult serve para verificar se houve algum erro nas validacoes
    public String editar(@Valid Cargo cargo ,BindingResult result, RedirectAttributes attr) {

        
         if(result.hasErrors()){
            return "cargo/cadastro";
        }
        
        cargoServico.editar(cargo);
        attr.addFlashAttribute("success", "Registro atualizado com sucesso.");
        return "redirect:/cargos/cadastrar";

    }

    //Metodo para excluir
    @GetMapping("/excluir/{id}") //
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) { //pathVaviable recupera o id da url || RedirectAttributes attr para retornar uma mensagem de sucesso ou erro

        if (cargoServico.cargoTemFuncionarios(id)) {
            attr.addFlashAttribute("success", "Cargo Excluido com sucesso");
        }
        return "redirect:/cargos/listar";

    }

    
    //Metodo para retornar uma lista de departamentos
    @ModelAttribute("departamentos")
    public List<Departamento> listaDeDepartamentos(){
        
     return departamentoServico.buscarTodos();
        
    }
    
    
}
