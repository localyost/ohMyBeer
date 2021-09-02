package de.omb.ohmybeer.entity.translation;

import de.omb.ohmybeer.entity.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslationService extends GenericService<Translation, Long, TranslationRepository> {

    @Autowired
    public TranslationService(TranslationRepository repository) { super(repository); }
}
