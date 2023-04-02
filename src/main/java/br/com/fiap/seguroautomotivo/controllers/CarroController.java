package br.com.fiap.seguroautomotivo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.seguroautomotivo.models.Carro;
import br.com.fiap.seguroautomotivo.models.RestValidationError;
import br.com.fiap.seguroautomotivo.repository.CarroRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carroCliente")
public class CarroController {
    
    @Autowired
    CarroRepository carroRepository;

    @GetMapping
    public List<Carro> todosOsCarros() {
        return carroRepository.findAll(); 
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarCarro(@RequestBody @Valid Carro carro, BindingResult result){
        // if(result.hasErrors()){
        //     return ResponseEntity.badRequest().body(new RestValidationError("erro de validação"));
        // }
        carroRepository.save(carro);
        return ResponseEntity.status(HttpStatus.CREATED).body(carro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> encontraCarroPorId(@PathVariable Long id){
        
        var carroEncontrado = carroRepository.findById(id);

        if (carroEncontrado.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(carroEncontrado.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizarCarro(@PathVariable Long id, @RequestBody Carro carro) {

        var carroEncontrado = carroRepository.findById(id);

        if (carroEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        carro.setId(id);
        carroRepository.save(carro);
        

        return ResponseEntity.ok(carro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Carro> removerCarro(@PathVariable Long id) {
        
       var carroEncontrado = carroRepository.findById(id);
        
       if(carroEncontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        carroRepository.delete(carroEncontrado.get());

        return ResponseEntity.noContent().build();
    }
}
