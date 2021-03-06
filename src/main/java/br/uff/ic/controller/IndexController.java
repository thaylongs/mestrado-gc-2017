package br.uff.ic.controller;

import br.uff.ic.entity.Documento;
import br.uff.ic.entity.Editor;
import br.uff.ic.repository.DocumentoRepository;
import br.uff.ic.repository.EditorRepository;
import br.uff.ic.service.JaversFactory;
import org.apache.log4j.Logger;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by guilherme on 07/05/17.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private final Logger logger = Logger.getLogger(IndexController.class);

    private Javers javers;
    private DocumentoRepository repository;
    private EditorRepository editorRepository;
    private JaversFactory factory;

    public IndexController(DocumentoRepository repository, EditorRepository repository2, JaversFactory factory) {
        this.factory = factory;
        this.javers = this.factory.getInstance();
        this.repository = repository;
        this.editorRepository = repository2;
    }

    @GetMapping
    public String getLayout(){
        logger.info("Acessando página inicial");
        return "index";
    }

    @GetMapping(value = "/last")
    @ResponseBody
    public ResponseEntity<Documento> getVersaoAtualDocumento(){
        Optional<Documento> documento = Optional.ofNullable(repository.findOne(1L));
        return new ResponseEntity<Documento>(documento.orElse(null), HttpStatus.OK);
    }

    @PostMapping(value = "/post")
    @ResponseBody
    public ResponseEntity<Documento> post(@RequestBody String jsonString){
        String[] jsonArray = separarDadosDeStringJSON(jsonString);
        Integer[] inconformidades = new Integer[]{Integer.parseInt(jsonArray[5]), Integer.parseInt(jsonArray[6]), Integer.parseInt(jsonArray[7])};
        Editor editor = editorRepository.save(new Editor(jsonArray[3], jsonArray[4]));
        Documento documento = repository.save(new Documento(jsonArray[2], jsonArray[0], jsonArray[1], editor, inconformidades, jsonArray[8]));
        javers.commit("gsag", documento);
        return new ResponseEntity<>(documento, HttpStatus.OK);
    }

    private String[] separarDadosDeStringJSON(String jsonString){
        String[] jsonArray =  jsonString.split(",");
        jsonArray[0] = jsonArray[0].split(":\"")[1].replace("\"", "");
        jsonArray[1] = jsonArray[1].split(":\"")[1].replace("\"", "");
        jsonArray[2] = jsonArray[2].split(":\"")[1].replace("\"", "");
        jsonArray[3] = jsonArray[3].split(":\"")[1].replace("\"", "");
        jsonArray[4] = jsonArray[4].split(":\"")[1].replace("\"", "");
        jsonArray[5] = jsonArray[5].split(":\"")[1].replace("\"", "");
        jsonArray[6] = jsonArray[6].split(":\"")[1].replace("\"", "");
        jsonArray[7] = jsonArray[7].split(":\"")[1].replace("\"", "");
        jsonArray[8] = jsonArray[8].split(":\"")[1].replace("\"}", "");
        return jsonArray;
    }
}
