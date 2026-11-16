package br.com.fiap.zelourbano.controller;

import br.com.fiap.zelourbano.dto.request.SolicitacaoPrefeituraRequestDTO;
import br.com.fiap.zelourbano.dto.response.SolicitacaoPrefeituraResponseDTO;
import br.com.fiap.zelourbano.service.SolicitacaoPrefeituraService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/solicitacoes-prefeitura")
@Tag(name = "Solicitações à Prefeitura", description = "Ofícios formais gerados quando ocorrências atingem >= 80% de aprovação")
public class SolicitacaoPrefeituraController {

    private final SolicitacaoPrefeituraService solicitacaoService;

    public SolicitacaoPrefeituraController(SolicitacaoPrefeituraService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    @PostMapping
    public ResponseEntity<SolicitacaoPrefeituraResponseDTO> criar(@Valid @RequestBody SolicitacaoPrefeituraRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<SolicitacaoPrefeituraResponseDTO>> listar() {
        return ResponseEntity.ok(solicitacaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoPrefeituraResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(solicitacaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoPrefeituraResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody SolicitacaoPrefeituraRequestDTO dto) {
        return ResponseEntity.ok(solicitacaoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        solicitacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}