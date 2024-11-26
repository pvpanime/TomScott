package dev.nemi.tomscott;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public final class FightingSpirit {
  private FightingSpirit() {}

  public static final ModelMapper mapper;

  static {
    mapper = new ModelMapper();
    mapper.getConfiguration()
      .setFieldMatchingEnabled(true)
      .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
      .setMatchingStrategy(MatchingStrategies.STRICT);
  }

}
