package br.com.joston.brzip.v1.controller;

import br.com.joston.brzip.v1.domain.Address;
import br.com.joston.brzip.v1.service.SearchCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller
 * @author Joston Jorge
 * */
@RestController
@RequestMapping("/api/v1/cep")
public class SearchCepController{

    private final SearchCepService searchCepService;

    @Autowired
    public SearchCepController(SearchCepService searchCepService){
        this.searchCepService = searchCepService;
    }
    @GetMapping("/{cep}")
    @ResponseBody
    public Address searchCep(@PathVariable String cep){
        return this.searchCepService.execute(cep);
    }
}
