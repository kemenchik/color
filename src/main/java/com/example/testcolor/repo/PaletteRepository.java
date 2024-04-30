package com.example.testcolor.repo;

import com.example.testcolor.model.Palette;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaletteRepository extends JpaRepository<Palette, UUID> {

  List<Palette> findByOwnerLogin(String login);

  Optional<Palette> findByIdAndOwnerLogin(UUID id, String login);
}
