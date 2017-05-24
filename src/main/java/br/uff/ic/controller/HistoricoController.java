package br.uff.ic.controller;

import br.uff.ic.entity.Documento;
import br.uff.ic.repository.DocumentoRepository;
import br.uff.ic.service.JaversFactory;
import org.apache.log4j.Logger;
import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.core.metamodel.object.CdoSnapshotState;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by guilherme on 20/05/17.
 */
@Controller
@RequestMapping("/historico")
public class HistoricoController {
    private final Logger logger = Logger.getLogger(HistoricoController.class);

    private Javers javers;
    private JaversFactory factory;

    public HistoricoController(JaversFactory factory) {
        this.factory = factory;
        this.javers = this.factory.getInstance();
    }

    @GetMapping
    public String getPaginaHistorico(){
        logger.info("Acessando página histórico");
        return "historico";
    }

    private Documento fromState(CdoSnapshotState state){
        return new Documento(state.getPropertyValue("dataModificacao").toString(),
                             state.getPropertyValue("titulo").toString(),
                             state.getPropertyValue("conteudo").toString());
    }

    private String fromDataModificacao(String data){
        return data.substring(0, 10);
    }

    private Map<String, List<Documento>> mapearPelaDataModificacao(List<CdoSnapshot> snapshots){
        return snapshots.stream().map(CdoSnapshot::getState).map(state->fromState(state))
                .collect(Collectors.groupingBy(
                        doc->fromDataModificacao(doc.getDataModificacao()), Collectors.toList()));
    }

    @ModelAttribute("revisoesMapeadas")
    public Map<String, List<Documento>> getRevisoes(){
        List<CdoSnapshot> snapshots = javers.findSnapshots(
                QueryBuilder.byInstanceId(1L, Documento.class).build());
        return mapearPelaDataModificacao(snapshots);
    }
}
