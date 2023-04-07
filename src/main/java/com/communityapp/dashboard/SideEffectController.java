package com.communityapp.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SideEffectController {

    @Autowired
    private SideEffectRepository sideEffectRepository;

    @PostMapping("/side-effects")
    public SideEffect createSideEffect(@RequestBody SideEffect sideEffect) {
        return sideEffectRepository.save(sideEffect);
    }

    @GetMapping("/side-effects")
    public List<SideEffect> getAllSideEffects() {
        return sideEffectRepository.findAll();
    }

    @GetMapping("/side-effects/{id}")
    public SideEffect getSideEffectById(@PathVariable Long id) throws Exception {
        return sideEffectRepository.findById(id).orElseThrow(() -> new Exception("Side effect not found"));
    }

    @PutMapping("/side-effects/{id}")
    public SideEffect updateSideEffect(@PathVariable Long id, @RequestBody SideEffect sideEffect) throws Exception {
        SideEffect existingSideEffect = sideEffectRepository.findById(id).orElseThrow(() -> new Exception("Side effect not found"));
        existingSideEffect.setDescription(sideEffect.getDescription());
        // set any other relevant fields
        return sideEffectRepository.save(existingSideEffect);
    }
}