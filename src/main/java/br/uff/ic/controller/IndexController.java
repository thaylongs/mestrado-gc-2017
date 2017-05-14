package br.uff.ic.controller;

import br.uff.ic.entity.Documento;
import br.uff.ic.repository.DocumentoRepository;
import org.apache.log4j.Logger;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.metamodel.object.CdoSnapshot;
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

    public IndexController(DocumentoRepository repository) {
        javers = JaversBuilder.javers().build();
        this.repository = repository;
    }

    @GetMapping
    public String getLayout(){
        logger.info("Acessando controlador index");
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
        String[]jsonArray =  jsonString.split(",");
        String titulo = jsonArray[0].split(":")[1].replace("\"", "");
        String data = jsonArray[1].split(":")[1].replace("\"", "");
        String date = jsonArray[2].split(":\"")[1].replace("\"}", "");
        Documento documento = repository.save(new Documento(date, titulo, data));
        return new ResponseEntity<Documento>(documento, HttpStatus.OK);
    }
}
