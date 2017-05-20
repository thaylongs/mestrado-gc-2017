package br.uff.ic.controller;

import br.uff.ic.entity.Documento;
import br.uff.ic.entity.TesteJavers;
import br.uff.ic.repository.DocumentoRepository;
import br.uff.ic.repository.TesteJaversRepository;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.commit.Commit;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.GlobalIdDTO;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guilherme on 13/04/17.
 */
@Controller
@RequestMapping("/javers")
public class TesteJaversController {

    private DocumentoRepository repository;
    private Javers javers;

    public TesteJaversController(DocumentoRepository repository) {
        this.repository = repository;
        this.javers = JaversBuilder.javers().build();
    }

//    @ResponseBody
//    @GetMapping("/save")
//    public ResponseEntity<TesteJavers> save(){
//        StringBuilder html = new StringBuilder("<html><head></head><body><p class='paragraph'>OldButGold</p></body></html>");
//        TesteJavers teste = new TesteJavers(1L, html.toString());
//        teste = repository.save(teste);
//        javers.commit("autor", teste);
//
//        teste.setValue("<html><head></head><body><p class='paragraph'>OldButBold</p></body></html>");
//        teste = repository.save(teste);
//        javers.commit("autor", teste);
//
//        return new ResponseEntity<TesteJavers>(teste, HttpStatus.OK);
//    }
//
//    @ResponseBody
//    @GetMapping("/all")
//    public ResponseEntity<List<String>> getAll(){
//        List<CdoSnapshot> snapshots = javers.findSnapshots(
//                QueryBuilder.byInstanceId(1L,TesteJavers.class).limit(2).build());
//        List<String> states = new ArrayList<>(2);
//        snapshots.forEach(cdoSnapshot -> states.add(cdoSnapshot.getState().getPropertyValue("value").toString()));
//        return new ResponseEntity<List<String>>(states, HttpStatus.OK);
//    }

    @ResponseBody
    @GetMapping("/save")
    public ResponseEntity<Documento> save(){
        Documento documento = new Documento("","teste");
        documento = repository.save(documento);
        javers.commit("autor", documento);

        documento.setConteudo("teste2");
        documento.setDataModificacao("2");
        documento = repository.save(documento);
        javers.commit("autor", documento);

        return new ResponseEntity<Documento>(documento, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/all")
    public ResponseEntity<String> getAll(){
        List<CdoSnapshot> snapshots = javers.findSnapshots(
                QueryBuilder.byInstanceId(1l, Documento.class).build());
        System.out.println(snapshots.size());
        //List<String> states = new ArrayList<>(2);
        //snapshots.forEach(cdoSnapshot -> states.add(cdoSnapshot.getState().getPropertyValue("value").toString()));
        return new ResponseEntity<String>(snapshots.toString(), HttpStatus.OK);
    }
}
