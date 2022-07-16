package com.tomgregory.optional.data;

import com.tomgregory.optional.model.Guitarist;
import com.tomgregory.optional.repository.GuitaristRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DemoData {
    private final GuitaristRepository guitaristRepository;

    public DemoData(GuitaristRepository guitaristRepository) {
        this.guitaristRepository = guitaristRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent applicationReadyEvent) {
        guitaristRepository.save(Guitarist.builder().firstName("Jimi").lastName("Hendrix").signatureSong("Purple Haze").build());
        guitaristRepository.save(Guitarist.builder().firstName("Ed").lastName("Sheeran").signatureSong("The A Team").build());
        guitaristRepository.save(Guitarist.builder().firstName("Mark").lastName("Knopfler").signatureSong("Money For Nothing").build());
    }

}