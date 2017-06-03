package br.uff.ic.repository;

import br.uff.ic.entity.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by guilherme on 03/06/17.
 */
@Repository
public interface EditorRepository extends JpaRepository<Editor, Long> {
}
