package br.uff.ic.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by guilherme on 07/05/17.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private final Logger logger = Logger.getLogger(IndexController.class);

    public IndexController() {
    }

    @GetMapping
    public String getLayout(){
        logger.info("Acessando controlador index");
        return "index";
    }
}
