package com.projeto.departamentoSpringBoot.web.controle;

import com.projeto.departamentoSpringBoot.dominio.Cargo;
import com.projeto.departamentoSpringBoot.dominio.Funcionario;
import com.projeto.departamentoSpringBoot.dominio.Uf;
import com.projeto.departamentoSpringBoot.servico.CargoServico;
import com.projeto.departamentoSpringBoot.servico.FuncionarioServico;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;

import com.projeto.departamentoSpringBoot.web.validator.FuncionarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioControle {

    @Autowired
    private FuncionarioServico funcionarioServico;

    @Autowired
    private CargoServico cargoServico;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new FuncionarioValidator());
    }
    
    @GetMapping("/cadastrar")
    public String cadastrar(Funcionario funcionario) {

        return "funcionario/cadastro";

    }

    
    @GetMapping("/listar")
    public String listar(ModelMap model) {

        model.addAttribute("funcionarios", funcionarioServico.buscarTodos());
        return "funcionario/lista";

    }

    
    //Metodo para salvar um funcionario
    @PostMapping("/salvar")
    public String salvar(@Valid Funcionario funcionario,BindingResult result, RedirectAttributes attr) {

        
        if(result.hasErrors()){
            return "funcionario/cadastro";
        }
        
        funcionarioServico.salvar(funcionario);
        attr.addFlashAttribute("success", "Funcionario inserido com sucesso");
        return "redirect:/funcionarios/cadastrar";

    }

    
    
    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("funcionario", funcionarioServico.buscarPorId(id));
        return "funcionario/cadastro";
    }

    
    
    @PostMapping("/editar")
    public String editar(@Valid Funcionario funcionario,BindingResult result, RedirectAttributes attr) {
        
        if(result.hasErrors()){
            return "funcionario/cadastro";
        }
        
        funcionarioServico.editar(funcionario);
        attr.addFlashAttribute("success", "Funcionário editado com sucesso.");
        return "redirect:/funcionarios/cadastrar";
    }

    
    
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        funcionarioServico.excluir(id);
        attr.addFlashAttribute("success", "Funcionário removido com sucesso.");
        return "redirect:/funcionarios/listar";
    }

    
    
    @GetMapping("/buscar/nome")
    public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {

        model.addAttribute("funcionarios", funcionarioServico.buscarPorNome(nome));

        return "funcionario/lista";
    }

    
    
    @GetMapping("/buscar/cargo")
    public String getPorCargo(@RequestParam("id") Long id, ModelMap model) {
        model.addAttribute("funcionarios", funcionarioServico.buscarPorCargo(id));
        return "funcionario/lista";
    }
    
    

    @GetMapping("/buscar/data")
    public String getPorDatas(@RequestParam("entrada") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
            @RequestParam("saida") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida,
            ModelMap model) {

        model.addAttribute("funcionarios", funcionarioServico.buscarPorDatas(entrada, saida));
        return "funcionario/lista";
    }

    
    
    //Metodo para buscar todos os cargos
    @ModelAttribute("cargos")
    public List<Cargo> getCargos() {

        return cargoServico.buscarTodos();

    }

    
    
    //Metodo para retornar as ufs
    @ModelAttribute("ufs")
    public Uf[] getUfs() {

        return Uf.values();

    }

}
