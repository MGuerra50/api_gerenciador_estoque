package com.inventory.manager.services;

import com.inventory.manager.domain.location.Location;
import com.inventory.manager.domain.location.LocationDTO;
import com.inventory.manager.domain.location.LocationRepository;
import com.inventory.manager.domain.location.LocationRequestDTO;
import com.inventory.manager.domain.warehouse.Warehouse;
import com.inventory.manager.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    WarehouseService warehouseService;

    public Location findById (Long id){
        return locationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Localização com id \"" + id + "\" não encontrado."));
    }

    public List<LocationDTO> findAll (){
        List<Location> locations = locationRepository.findAll();
        return locations.stream().map(LocationDTO::new).collect(Collectors.toList());
    }

    public LocationDTO createLocation (LocationRequestDTO locationRequestDTO){
        Location location = new Location();
        Warehouse warehouse = warehouseService.findById(locationRequestDTO.warehouseId());
        location.setWarehouse(warehouse);
        location.setLocationCode(locationRequestDTO.locationCode());
        location.setIsActive(true);

        Location savedLocation = locationRepository.save(location);
        return new LocationDTO(savedLocation);
    }

    @Transactional
    public LocationDTO updateLocation (Long id, LocationDTO locationDTO){
        Location location = findById(id);
        if(location == null){
            throw new ResourceNotFoundException("Location id \"" + id + "\" não encontrado.");
        }
        location.setWarehouse(warehouseService.findById(locationDTO.warehouseId()));
        location.setLocationCode(locationDTO.locationCode());
        location.setIsActive(locationDTO.isActive());

        Location savedLocation = locationRepository.save(location);
        return new LocationDTO(savedLocation);
    }

    public void deleteById (Long id){
        if(!locationRepository.existsById(id)){
            throw new ResourceNotFoundException("Location id \"" + id + "\" não encontrado.");
        }
        locationRepository.deleteById(id);
    }

}
