package br.uff.ic.repository;

import br.uff.ic.entity.TesteJavers;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by guilherme on 13/04/17.
 */
@Repository
@JaversSpringDataAuditable
public interface TesteJaversRepository extends JpaRepository<TesteJavers, Long> {
}
