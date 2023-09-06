package br.com.lembretes.controller;



import br.com.lembretes.entity.Lembretes;


import br.com.lembretes.repository.LembretesRepository;
import br.com.lembretes.service.LembretesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/lembretes")
public class LembretesController {

    @Autowired
    private LembretesRepository lembretesRep;

    @Autowired
    private LembretesService lembretesServ;



    @GetMapping("/{id}")
    public ResponseEntity<Lembretes> findById(@PathVariable("id") final Long id) {
        final Lembretes lembretes = this.lembretesRep.findById(id).orElse(null);
        return ResponseEntity.ok(lembretes);
    }



    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Lembretes lembretes) {
        try {

            this.lembretesRep.save(lembretes);

            return ResponseEntity.ok("Registro cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable("id") final Long id,
            @RequestBody final Lembretes lembretes
    ) {
        try {
            this.lembretesRep.save(lembretes);
            return ResponseEntity.ok("Registro atualizado com sucesso. ");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") final Long id
    ) {
        try {
            this.lembretesRep.deleteById(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


}
