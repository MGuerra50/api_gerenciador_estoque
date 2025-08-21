package com.inventory.manager.services;

import com.inventory.manager.domain.position.Position;
import com.inventory.manager.domain.position.PositionDTO;
import com.inventory.manager.domain.position.PositionRepository;
import com.inventory.manager.domain.position.PositionRequestDTO;
import com.inventory.manager.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionService {

    @Autowired
    PositionRepository positionRepository;

    public Position findById(Long id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position id \"" + id + "\" não encontrado.")
                );
    }

    public List<PositionDTO> findAll (){
        return positionRepository.findAll().stream().map(PositionDTO::new).collect(Collectors.toList());
    }

    public PositionDTO createPosition (PositionRequestDTO positionRequestDTO){
        Position position = new Position();
        position.setName(positionRequestDTO.name());
        position.setIsActive(true);

        Position savedPosition = positionRepository.save(position);
        return new PositionDTO(savedPosition);
    }

    public PositionDTO updatePosition (Long id, PositionDTO positionDTO){
        Position position = findById(id);
        if (position == null) throw new ResourceNotFoundException("Position id \"" + id + "\" não encontrado.");

        position.setName(positionDTO.name());
        position.setIsActive(positionDTO.isActive());

        Position savedPosition = positionRepository.save(position);
        return new PositionDTO(savedPosition);
    }

    public void deleteById (Long id){
        if(!positionRepository.existsById(id))
            throw new ResourceNotFoundException("Position id \"" + id + "\" não encontrado.");

        positionRepository.deleteById(id);
    }
}
