package br.com.fiap.zelourbano.controller;

import br.com.fiap.zelourbano.dto.request.RegiaoRequestDTO;
import br.com.fiap.zelourbano.dto.response.RegiaoResponseDTO;
import br.com.fiap.zelourbano.service.RegiaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/regioes")
@Tag(name = "Regiões", description = "Gerenciamento de bairros e população ativa regional")
public class RegiaoController {

    private final RegiaoService regiaoService;

    public RegiaoController(RegiaoService regiaoService) {
        this.regiaoService = regiaoService;
    }

    @PostMapping
    public ResponseEntity<RegiaoResponseDTO> criar(@Valid @RequestBody RegiaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(regiaoService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<RegiaoResponseDTO>> listar() {
        return ResponseEntity.ok(regiaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegiaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(regiaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegiaoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody RegiaoRequestDTO dto) {
        return ResponseEntity.ok(regiaoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        regiaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}