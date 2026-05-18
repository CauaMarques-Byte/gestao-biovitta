package com.biovitta.gestaocnpj.service;

import com.biovitta.gestaocnpj.domain.entity.Mensalidade;
import com.biovitta.gestaocnpj.domain.entity.enums.StatusMensalidade;
import com.biovitta.gestaocnpj.repository.MensalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MensalidadeService {

    @Autowired
    private MensalidadeRepository mensalidadeRepository;

    public Mensalidade cadastrar(Mensalidade mensalidade) {
        return mensalidadeRepository.save(mensalidade);
    }

    public Mensalidade buscarPorId(Long id) {
        return mensalidadeRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Mensalidade não encontrada" + id));
    }


    public List<Mensalidade> buscarPendentes() {
        return mensalidadeRepository.findByStatus(StatusMensalidade.PENDENTE);
    }

    public Mensalidade registrarPagamento(Long id){
        Mensalidade mensalidade = mensalidadeRepository.findById(id).orElseThrow(() ->
                                                        new RuntimeException("Mensalidade não encontrada"));
        if(mensalidade.getStatus() == StatusMensalidade.PAGO){
            throw new IllegalArgumentException("Mensalidade já paga");
        }

        mensalidade.marcarComoPago();
        return mensalidadeRepository.save(mensalidade);
    }

    public List<Mensalidade> listarInadimplente(Long empresaId) {
        return mensalidadeRepository.findByEmpresaIdAndStatusAndDataVencimentoBefore(
                empresaId, StatusMensalidade.PENDENTE, LocalDate.now()
        );
    }

    public Mensalidade marcarComoAtrasada(Long id) {
    Mensalidade mensalidade = buscarPorId(id);
    mensalidade.marcarComoAtrasada();
    return mensalidadeRepository.save(mensalidade);
    }



}
