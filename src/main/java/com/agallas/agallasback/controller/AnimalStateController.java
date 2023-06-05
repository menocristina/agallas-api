package com.agallas.agallasback.controller;

import com.agallas.agallasback.service.AnimalStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animals/states")
@RequiredArgsConstructor
public class AnimalStateController {

    private final AnimalStateService animalStateService;
}
