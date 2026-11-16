package br.com.fiap.zelourbano.service;

import br.com.fiap.zelourbano.domain.entity.Ocorrencia;
import br.com.fiap.zelourbano.domain.entity.SolicitacaoPrefeitura;
import br.com.fiap.zelourbano.dto.request.SolicitacaoPrefeituraRequestDTO;
import br.com.fiap.zelourbano.dto.response.SolicitacaoPrefeituraResponseDTO;
import br.com.fiap.zelourbano.exception.ResourceNotFoundException;
import br.com.fiap.zelourbano.repository.OcorrenciaRepository;
import br.com.fiap.zelourbano.repository.SolicitacaoPrefeituraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitacaoPrefeituraService {

    private final SolicitacaoPrefeituraRepository solicitacaoRepository;
    private final OcorrenciaRepository ocorrenciaRepository;
    private final ModelMapper modelMapper;

    public SolicitacaoPrefeituraService(SolicitacaoPrefeituraRepository solicitacaoRepository,
                                        OcorrenciaRepository ocorrenciaRepository,
                                        ModelMapper modelMapper) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public SolicitacaoPrefeituraResponseDTO criar(SolicitacaoPrefeituraRequestDTO dto) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(dto.getOcorrenciaId())
                .orElseThrow(() -> new ResourceNotFoundException("Ocorrência não encontrada com ID: " + dto.getOcorrenciaId()));
        SolicitacaoPrefeitura solicitacao = modelMapper.map(dto, SolicitacaoPrefeitura.class);
        solicitacao.setOcorrencia(ocorrencia);
        return toResponseDTO(solicitacaoRepository.save(solicitacao));
    }

    @Transactional(readOnly = true)
    public List<SolicitacaoPrefeituraResponseDTO> listarTodas() {
        return solicitacaoRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SolicitacaoPrefeituraResponseDTO buscarPorId(Long id) {
        SolicitacaoPrefeitura solicitacao = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitação não encontrada com ID: " + id));
        return toResponseDTO(solicitacao);
    }

    @Transactional
    public SolicitacaoPrefeituraResponseDTO atualizar(Long id, SolicitacaoPrefeituraRequestDTO dto) {
        SolicitacaoPrefeitura solicitacao = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitação não encontrada com ID: " + id));
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(dto.getOcorrenciaId())
                .orElseThrow(() -> new ResourceNotFoundException("Ocorrência não encontrada com ID: " + dto.getOcorrenciaId()));
        modelMapper.map(dto, solicitacao);
        solicitacao.setOcorrencia(ocorrencia);
        return toResponseDTO(solicitacaoRepository.save(solicitacao));
    }

    @Transactional
    public void excluir(Long id) {
        if (!solicitacaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Solicitação não encontrada com ID: " + id);
        }
        solicitacaoRepository.deleteById(id);
    }

    private SolicitacaoPrefeituraResponseDTO toResponseDTO(SolicitacaoPrefeitura s) {
        SolicitacaoPrefeituraResponseDTO dto = modelMapper.map(s, SolicitacaoPrefeituraResponseDTO.class);
        dto.setOcorrenciaId(s.getOcorrencia().getId());
        return dto;
    }
}