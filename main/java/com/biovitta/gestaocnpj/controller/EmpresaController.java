package com.biovitta.gestaocnpj.controller;

import com.biovitta.gestaocnpj.domain.entity.Empresa;
import com.biovitta.gestaocnpj.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<Empresa> cadastrar(@RequestBody Empresa empresa) {
        Empresa empresaSalva = empresaService.cadastrar(empresa);
        return ResponseEntity.ok(empresaSalva);
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Optional<Empresa>> buscarPorCnpj(@PathVariable String cnpj) {
        Optional<Empresa> empresa = empresaService.buscarPorCnpj(cnpj);
        return ResponseEntity.ok(empresa);
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<Empresa> desativar(@PathVariable Long id) {
        Empresa empresa = empresaService.desativarEmpresa(id);
        return ResponseEntity.ok(empresa);
    }
}