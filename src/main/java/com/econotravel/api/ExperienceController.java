package com.econotravel.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
// NO INCLUIR NUNCA LA CABECERA CrossOrigin en un proyecto real
@CrossOrigin
public class ExperienceController {

    private final ExperienceRepository experienceRepository;

    @Autowired
    public ExperienceController(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @GetMapping
    public List<Experience> allExperiences() {
        return experienceRepository.findAll();
    }

    @PostMapping
    public Experience createExperience(@RequestBody Experience experience) {
        return experienceRepository.save(experience);
    }

    @GetMapping("/edit/{id}")
    public Experience updateExperience(@RequestBody Experience experience) {
        return experienceRepository.save(experience);
    }

    @GetMapping("/delete/{id}")
    public void deleteExperience(@PathVariable Long id) {
        experienceRepository.deleteById(id);
    }

}
