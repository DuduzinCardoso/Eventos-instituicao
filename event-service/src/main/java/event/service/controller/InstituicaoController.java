package event.service.controller;

import event.service.service.InstituicaoService;
import event.service.entity.Instituicao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instituicoes")
public class InstituicaoController {
    private final InstituicaoService instituicaoService;

    public InstituicaoController(InstituicaoService instituicaoService) {
        this.instituicaoService = instituicaoService;
    }

    @PostMapping()
    public ResponseEntity<Instituicao> criarInstituicao(@RequestBody Instituicao instituicao) {
        return  ResponseEntity.status(201).body(instituicaoService.salvarInstituicao(instituicao));
    }
}
