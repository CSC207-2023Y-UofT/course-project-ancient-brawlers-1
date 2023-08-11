package entities.cards;

import entities.cardEffects.CardEffect;

import java.util.List;

public interface Playable {

    String getDescription();

    TargetType getTargetType();

    List<CardEffect> getEffects();
}
