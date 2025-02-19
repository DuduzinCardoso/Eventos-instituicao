package event.service.service.impl;

import event.service.repository.InstituicaoRepository;
import event.service.service.InstituicaoService;
import event.service.entity.Instituicao;
import org.springframework.stereotype.Service;

@Service
public class InstituicaoServiceImpl implements InstituicaoService {

    private final InstituicaoRepository instituicaoRepository;

    public InstituicaoServiceImpl(InstituicaoRepository instituicaoRepository) {
        this.instituicaoRepository = instituicaoRepository;
    }

    @Override
    public Instituicao salvarInstituicao(Instituicao instituicao) {
        return instituicaoRepository.save(instituicao);
    }
}
