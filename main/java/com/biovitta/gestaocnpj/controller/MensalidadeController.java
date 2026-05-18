package com.biovitta.gestaocnpj.controller;

import com.biovitta.gestaocnpj.domain.entity.Mensalidade;
import com.biovitta.gestaocnpj.service.MensalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensalidades")
public class MensalidadeController  {

    @Autowired
    private MensalidadeService mensalidadeService;

    @PostMapping
    public ResponseEntity <Mensalidade> cadastrarMensalidade(@RequestBody Mensalidade mensalidade) {
        Mensalidade mensalidadeSalva = mensalidadeService.cadastrar(mensalidade);
        return ResponseEntity.ok(mensalidadeSalva);
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<Mensalidade> pagarMensalidade(@PathVariable Long id) {
        Mensalidade mensalidade = mensalidadeService.registrarPagamento(id);
                return ResponseEntity.ok(mensalidade);
    }

    @GetMapping("/pendentes")
    public ResponseEntity <List<Mensalidade>> listarPendentes() {
        List <Mensalidade> mensalidade = mensalidadeService.buscarPendentes();
                return ResponseEntity.ok(mensalidade);
    }

    @GetMapping("/inadimplente")
    public ResponseEntity <List<Mensalidade>> listarInadimplente(@RequestParam Long id) {
        List<Mensalidade> mensalidade = mensalidadeService.listarInadimplente(id);
                return ResponseEntity.ok(mensalidade);
    }


}
