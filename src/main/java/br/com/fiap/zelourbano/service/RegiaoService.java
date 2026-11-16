package br.com.fiap.zelourbano.service;

import br.com.fiap.zelourbano.domain.entity.Regiao;
import br.com.fiap.zelourbano.dto.request.RegiaoRequestDTO;
import br.com.fiap.zelourbano.dto.response.RegiaoResponseDTO;
import br.com.fiap.zelourbano.exception.ResourceNotFoundException;
import br.com.fiap.zelourbano.repository.RegiaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegiaoService {

    private final RegiaoRepository regiaoRepository;
    private final ModelMapper modelMapper;

    public RegiaoService(RegiaoRepository regiaoRepository, ModelMapper modelMapper) {
        this.regiaoRepository = regiaoRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public RegiaoResponseDTO criar(RegiaoRequestDTO dto) {
        Regiao regiao = modelMapper.map(dto, Regiao.class);
        return modelMapper.map(regiaoRepository.save(regiao), RegiaoResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public List<RegiaoResponseDTO> listarTodas() {
        return regiaoRepository.findAll().stream()
                .map(r -> modelMapper.map(r, RegiaoResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RegiaoResponseDTO buscarPorId(Long id) {
        Regiao regiao = regiaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada com ID: " + id));
        return modelMapper.map(regiao, RegiaoResponseDTO.class);
    }

    @Transactional
    public RegiaoResponseDTO atualizar(Long id, RegiaoRequestDTO dto) {
        Regiao regiao = regiaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada com ID: " + id));
        modelMapper.map(dto, regiao);
        return modelMapper.map(regiaoRepository.save(regiao), RegiaoResponseDTO.class);
    }

    @Transactional
    public void excluir(Long id) {
        if (!regiaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Região não encontrada com ID: " + id);
        }
        regiaoRepository.deleteById(id);
    }
}