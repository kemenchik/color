package com.example.testcolor.repo;

import com.example.testcolor.model.Color;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, UUID> {

  List<Color> findByPaletteIdAndPaletteOwnerLogin(UUID paletteId, String ownerLogin);

  Optional<Color> findByIdAndPaletteOwnerLogin(UUID id, String ownerLogin);
}
