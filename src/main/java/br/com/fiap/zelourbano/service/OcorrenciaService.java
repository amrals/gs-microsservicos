package br.com.fiap.zelourbano.service;

import br.com.fiap.zelourbano.domain.entity.*;
import br.com.fiap.zelourbano.dto.request.OcorrenciaRequestDTO;
import br.com.fiap.zelourbano.dto.response.OcorrenciaResponseDTO;
import br.com.fiap.zelourbano.exception.ResourceNotFoundException;
import br.com.fiap.zelourbano.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;
    private final UsuarioRepository usuarioRepository;
    private final RegiaoRepository regiaoRepository;
    private final SolicitacaoPrefeituraRepository solicitacaoRepository;
    private final ModelMapper modelMapper;

    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository,
                             UsuarioRepository usuarioRepository,
                             RegiaoRepository regiaoRepository,
                             SolicitacaoPrefeituraRepository solicitacaoRepository,
                             ModelMapper modelMapper) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.usuarioRepository = usuarioRepository;
        this.regiaoRepository = regiaoRepository;
        this.solicitacaoRepository = solicitacaoRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public OcorrenciaResponseDTO criar(OcorrenciaRequestDTO dto) {
        Usuario autor = usuarioRepository.findById(dto.getUsuarioAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + dto.getUsuarioAutorId()));
        Regiao regiao = regiaoRepository.findById(dto.getRegiaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada com ID: " + dto.getRegiaoId()));

        Ocorrencia ocorrencia = modelMapper.map(dto, Ocorrencia.class);
        ocorrencia.setUsuarioAutor(autor);
        ocorrencia.setRegiao(regiao);
        ocorrencia.setStatus("EM_COLETA");

        Ocorrencia salva = ocorrenciaRepository.save(ocorrencia);
        verificarGatilhoOitentaPorcento(salva);
        return toResponseDTO(salva);
    }

    @Transactional(readOnly = true)
    public List<OcorrenciaResponseDTO> listarTodas() {
        return ocorrenciaRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OcorrenciaResponseDTO buscarPorId(Long id) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ocorrência não encontrada com ID: " + id));
        return toResponseDTO(ocorrencia);
    }

    @Transactional
    public OcorrenciaResponseDTO atualizar(Long id, OcorrenciaRequestDTO dto) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ocorrência não encontrada com ID: " + id));
        Usuario autor = usuarioRepository.findById(dto.getUsuarioAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + dto.getUsuarioAutorId()));
        Regiao regiao = regiaoRepository.findById(dto.getRegiaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada com ID: " + dto.getRegiaoId()));

        modelMapper.map(dto, ocorrencia);
        ocorrencia.setUsuarioAutor(autor);
        ocorrencia.setRegiao(regiao);

        Ocorrencia atualizada = ocorrenciaRepository.save(ocorrencia);
        verificarGatilhoOitentaPorcento(atualizada);
        return toResponseDTO(atualizada);
    }

    @Transactional
    public OcorrenciaResponseDTO adicionarCurtida(Long id) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ocorrência não encontrada com ID: " + id));
        ocorrencia.setQuantidadeCurtidas(ocorrencia.getQuantidadeCurtidas() + 1);
        Ocorrencia atualizada = ocorrenciaRepository.save(ocorrencia);
        verificarGatilhoOitentaPorcento(atualizada);
        return toResponseDTO(atualizada);
    }

    @Transactional
    public void excluir(Long id) {
        if (!ocorrenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ocorrência não encontrada com ID: " + id);
        }
        ocorrenciaRepository.deleteById(id);
    }

    // REGRA DE NEGÓCIO: Gatilho automático de 80% da população regional
    private void verificarGatilhoOitentaPorcento(Ocorrencia ocorrencia) {
        int populacaoAtiva = ocorrencia.getRegiao().getPopulacaoAtivaApp();
        double limiteOitentaPorcento = Math.ceil(populacaoAtiva * 0.80);

        if (ocorrencia.getQuantidadeCurtidas() >= limiteOitentaPorcento
                && solicitacaoRepository.findByOcorrenciaId(ocorrencia.getId()).isEmpty()) {

            ocorrencia.setStatus("SOLICITACAO_GERADA");
            ocorrenciaRepository.save(ocorrencia);

            double percentual = ((double) ocorrencia.getQuantidadeCurtidas() / populacaoAtiva) * 100.0;

            SolicitacaoPrefeitura solicitacao = new SolicitacaoPrefeitura();
            solicitacao.setProtocoloOficial("REQ-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            solicitacao.setOrgaoDestinatario(ocorrencia.getRegiao().getSubprefeituraResponsavel() != null
                    ? ocorrencia.getRegiao().getSubprefeituraResponsavel()
                    : "Prefeitura Municipal - Canal 156");
            solicitacao.setPercentualApoioAtingido(Math.min(percentual, 100.0));
            solicitacao.setStatusEnvio("ENVIADA_AUTOMATICAMENTE");
            solicitacao.setMensagemFormal("Demanda formal gerada automaticamente pelo ZeloUrbano após atingir "
                    + ocorrencia.getQuantidadeCurtidas() + " apoios no bairro " + ocorrencia.getRegiao().getNomeBairro());
            solicitacao.setOcorrencia(ocorrencia);

            solicitacaoRepository.save(solicitacao);
        }
    }

    private OcorrenciaResponseDTO toResponseDTO(Ocorrencia ocorrencia) {
        OcorrenciaResponseDTO dto = modelMapper.map(ocorrencia, OcorrenciaResponseDTO.class);
        dto.setUsuarioAutorId(ocorrencia.getUsuarioAutor().getId());
        dto.setRegiaoId(ocorrencia.getRegiao().getId());
        dto.setMetaLikesParaDisparo80Porcento((int) Math.ceil(ocorrencia.getRegiao().getPopulacaoAtivaApp() * 0.80));
        return dto;
    }
}