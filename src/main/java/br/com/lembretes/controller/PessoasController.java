package br.com.lembretes.controller;


import br.com.lembretes.entity.Pessoas;
import br.com.lembretes.repository.PessoasRepository;
import br.com.lembretes.service.PessoasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/pessoas")
public class PessoasController {

    @Autowired
    private PessoasRepository pessoasRep;

    @Autowired
    private PessoasService pessoasServ;


    @GetMapping("/{id}")
    public ResponseEntity<Pessoas> findById(@PathVariable("id") final Long id) {
        final Pessoas pessoas = this.pessoasRep.findById(id).orElse(null);
        return ResponseEntity.ok(pessoas);
    }


    @GetMapping("/lista")
    public ResponseEntity<List<Pessoas>> ListaCompleta() {
        return ResponseEntity.ok(this.pessoasRep.findAll());

    }

    @GetMapping("/nome")
    public ResponseEntity<List <Pessoas>> buscarPorNome(@RequestParam("nome") String nome) {
        try {
            List<Pessoas> pessoa = pessoasRep.findByNome(nome);
            if (pessoa != null) {
                return ResponseEntity.ok(pessoa);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Pessoas pessoas) {
        try {

            this.pessoasRep.save(pessoas);

            return ResponseEntity.ok("Registro cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable("id") final Long id,
            @RequestBody final Pessoas pessoas
    ) {
        try {
            this.pessoasRep.save(pessoas);
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
            this.pessoasRep.deleteById(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }








}
