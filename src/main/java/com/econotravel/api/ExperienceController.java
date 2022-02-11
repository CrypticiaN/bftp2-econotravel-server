package com.econotravel.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
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


    @GetMapping("/{id}")
    public Experience findExperience(@PathVariable Long id){
        return experienceRepository.findById(id).orElseThrow(null);
    }


    @DeleteMapping("/delete/{id}")
    public Experience deleteExperienceById(@PathVariable Long id) {
        Experience experience = experienceRepository.findById(id).orElseThrow(ExperienceNotFoundException::new);
        experienceRepository.deleteById(id);
        return experience;
    }

    @PutMapping
    public Experience updateExperience(@RequestBody Experience experience) {
        experienceRepository.findById(experience.getId()).orElseThrow(ExperienceNotFoundException::new);
        return experienceRepository.save(experience);
    }

}
