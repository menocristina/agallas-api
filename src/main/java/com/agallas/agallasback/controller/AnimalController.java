package com.agallas.agallasback.controller;

import com.agallas.agallasback.model.dto.AnimalDTO;
import com.agallas.agallasback.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController()
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public void saveAnimal(@RequestPart("animal") String animal,
                                @RequestPart(value = "photo", required = false) MultipartFile photo,
                                @RequestPart(value = "photoGal1", required = false) MultipartFile photoGal1,
                                @RequestPart(value = "photoGal2", required = false) MultipartFile photoGal2,
                                @RequestPart(value = "photoGal3", required = false) MultipartFile photoGal3,
                                @RequestPart(value = "photoGal4", required = false) MultipartFile photoGal4){
        try{
            AnimalDTO animalDTO = new AnimalDTO();
            animalDTO = new ObjectMapper().readValue(animal, AnimalDTO.class);
            animalDTO = setPhotos(photo, photoGal1, photoGal2, photoGal3, photoGal4, animalDTO);
            animalDTO.setCreatedAt(new Date());
            this.animalService.save(animalDTO);
        } catch (Exception exception){

        }
    }

    @GetMapping("/page/{page}")
    public Page<AnimalDTO> getAll(@PathVariable Integer page){
        return this.animalService.getAll(PageRequest.of(page,6));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAnimal(@PathVariable("id") Long id){
        this.animalService.delete(id);
    }

    @GetMapping("/{id}")
    public AnimalDTO getAnimal(@PathVariable("id") String id){
        return this.animalService.get(Long.parseLong(id));
    }

    private AnimalDTO setPhotos(MultipartFile photo, MultipartFile photoGal1, MultipartFile photoGal2, MultipartFile photoGal3, MultipartFile photoGal4, AnimalDTO animalDTO) throws IOException {
        if(photo != null && !photo.isEmpty()){
            animalDTO.setPhoto(photo.getBytes());
        }
        if(photoGal1 != null && !photoGal1.isEmpty()){
            animalDTO.setPhotoGal1(photoGal1.getBytes());
        }
        if(photoGal2 != null && !photoGal2.isEmpty()){
            animalDTO.setPhotoGal2(photoGal2.getBytes());
        }
        if(photoGal3 != null && !photoGal3.isEmpty()){
            animalDTO.setPhotoGal3(photoGal3.getBytes());
        }
        if(photoGal4 != null && !photoGal4.isEmpty()){
            animalDTO.setPhotoGal4(photoGal4.getBytes());
        }
        return animalDTO;
    }
}
