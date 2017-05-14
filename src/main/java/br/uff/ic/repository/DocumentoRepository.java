package br.uff.ic.repository;

import br.uff.ic.entity.Documento;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by guilherme on 13/05/17.
 */
@Repository
@JaversSpringDataAuditable
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
