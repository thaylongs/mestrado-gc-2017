package br.uff.ic.controller;

import br.uff.ic.entity.TesteJavers;
import br.uff.ic.repository.TesteJaversRepository;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.commit.Commit;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by guilherme on 13/04/17.
 */
@Controller
@RequestMapping("/javers")
public class TesteJaversController {

    private TesteJaversRepository repository;
    private Javers javers;

    public TesteJaversController(TesteJaversRepository repository) {
        this.repository = repository;
        this.javers = JaversBuilder.javers().build();
    }

    @ResponseBody
    @GetMapping("/save")
    public ResponseEntity<TesteJavers> save(){
        StringBuilder html = new StringBuilder("<html><head></head><body><p class='paragraph'>OldButGold</p></body></html>");
        TesteJavers teste = new TesteJavers(1L, html.toString());
        teste = repository.save(teste);
        javers.commit("teste de commit", teste);

        teste.setValue("<html><head></head><body><p class='paragraph'>OldButBold</p></body></html>");
        teste = repository.save(teste);
        javers.commit("teste de modificação", teste);

        return new ResponseEntity<TesteJavers>(teste, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/all")
    public ResponseEntity<List<CdoSnapshot>> getAll(){
        List<CdoSnapshot> snapshots = javers.findSnapshots(
                QueryBuilder.byInstanceId(1L,TesteJavers.class).limit(2).build());
        return new ResponseEntity<List<CdoSnapshot>>(snapshots, HttpStatus.OK);
    }
}
