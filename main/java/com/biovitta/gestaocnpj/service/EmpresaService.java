package com.biovitta.gestaocnpj.service;

import com.biovitta.gestaocnpj.domain.entity.Empresa;
import com.biovitta.gestaocnpj.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    /*O Autowired Serve para fazer a instanciação da classe, o exemplo aqui seria
     Empresarepository empresarepository = new Empresarepository(), porém como uso a anotação, não preciso
     fazer a instanciação, já é realizado por baixo dos panos
     */
    @Autowired
    //Estamos relacionando service com repository
    private EmpresaRepository empresaRepository;

    public Empresa cadastrar(Empresa empresa) {
        Optional<Empresa> empresaExistente = empresaRepository.findByCnpj(empresa.getCnpj());

        if (!empresaExistente.isEmpty()) {
            throw new IllegalArgumentException("Empresa já cadastrada");
        }
        return empresaRepository.save(empresa);
    }

    public Optional<Empresa> buscarPorCnpj(String cnpj) {
        return empresaRepository.findByCnpj(cnpj);
    }

    public void deletarEmpresa(Long Id) {
        empresaRepository.deleteById(Id);
    }

    public Empresa desativarEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new RuntimeException
                ("Empresa não existe"));
        if (empresa.isAtivo()) {
            empresa.setAtivo(false);
        } else {
            throw new IllegalArgumentException("Empresa ja esta desativada");
        }
        return empresaRepository.save(empresa);
    }
}
