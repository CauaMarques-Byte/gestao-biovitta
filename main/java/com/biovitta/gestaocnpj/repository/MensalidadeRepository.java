package com.biovitta.gestaocnpj.repository;

import com.biovitta.gestaocnpj.domain.entity.Mensalidade;
import com.biovitta.gestaocnpj.domain.entity.enums.StatusMensalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MensalidadeRepository extends JpaRepository<Mensalidade, Long>{

    List<Mensalidade> findByStatusAndDataVencimentoBefore
                                                (StatusMensalidade status,
                                                 LocalDate data);

    List<Mensalidade> findByStatus(StatusMensalidade status);

    List<Mensalidade> findByEmpresaIdAndStatusAndDataVencimentoBefore(
            Long empresaId, StatusMensalidade status, LocalDate data
    );

}
