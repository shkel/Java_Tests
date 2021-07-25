package controllers;

import bll.entities.Isin;
import bll.services.IsinElvlService;
import dal.entities.IsinElvl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: annotations and validator

@RestController
public class IsinController {

    private final IsinElvlService service;

    IsinController(IsinElvlService service) {
        this.service = service;
    }

    @PutMapping("/isins")
    boolean addIsin(@RequestBody Isin isin) {
        return service.addIsin(isin);
    }

    @GetMapping("/elvls/{isin}")
    Double getElvl(@PathVariable String isin) {
        return service.getElvl(isin);
    }

    @GetMapping("/elvls/")
    List<IsinElvl> getElvls() {
        return service.getElvls();
    }
}
