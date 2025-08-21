package com.inventory.manager.controller.Project;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.inventory.manager.domain.project.ProjectDTO;
import com.inventory.manager.domain.project.ProjectRequestDTO;
import com.inventory.manager.services.ProjectService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/project")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> findAll() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new ProjectDTO(projectService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectRequestDTO dto) {
        ProjectDTO project = projectService.createProject(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(project.id())
            .toUri();
        return ResponseEntity.created(location).body(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectRequestDTO dto) {
        return ResponseEntity.ok(projectService.updateProject(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
