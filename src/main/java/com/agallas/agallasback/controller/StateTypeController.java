package com.agallas.agallasback.controller;

import com.agallas.agallasback.model.dto.StateTypeDTO;
import com.agallas.agallasback.service.StateTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states/types")
@RequiredArgsConstructor
public class StateTypeController {

    private final StateTypeService stateTypeService;

    @GetMapping("/")
    private List<StateTypeDTO> getAll(){
        return this.stateTypeService.getAll();
    }

}
