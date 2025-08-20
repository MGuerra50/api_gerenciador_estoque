package com.inventory.manager.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.manager.domain.project.Project;
import com.inventory.manager.domain.project.ProjectDTO;
import com.inventory.manager.domain.project.ProjectRepository;
import com.inventory.manager.domain.project.ProjectRequestDTO;
import com.inventory.manager.domain.users.Users;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UsersServices usersService;

    public List<ProjectDTO> findAll() {
        return projectRepository.findAll().stream().map(ProjectDTO::new).collect(Collectors.toList());
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Projeto com id \"" + id + "\" não encontrado.")
        );
    }

    public ProjectDTO createProject(ProjectRequestDTO dto) {
        Users createdByUser = usersService.findById(dto.userId());
        Project project = new Project();
        project.setCode(dto.code());
        project.setName(dto.name());
        project.setDescription(dto.description());
        project.setStartDate(dto.startDate());
        project.setEndDate(dto.endDate());
        project.setBudget(dto.budget());
        project.setStatus(dto.status());
        project.setCreatedByUser(createdByUser);

        Project saved = projectRepository.save(project);
        return new ProjectDTO(saved);
    }

    public ProjectDTO updateProject(Long id, ProjectRequestDTO dto) {
        Users updatedByUser = usersService.findById(dto.userId());

        Project project = findById(id);

        project.setCode(dto.code());
        project.setName(dto.name());
        project.setDescription(dto.description());
        project.setStartDate(dto.startDate());
        project.setEndDate(dto.endDate());
        project.setBudget(dto.budget());
        project.setStatus(dto.status());
        project.setUpdatedByUser(updatedByUser);

        Project updated = projectRepository.save(project);
        return new ProjectDTO(updated);
    }

    public void deleteById(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Projeto com id \"" + id + "\" não encontrado.");
        }
        projectRepository.deleteById(id);
    }
}
