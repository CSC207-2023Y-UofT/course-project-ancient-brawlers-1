package entities.cards;

/**
 * EssenceCard represents the energy card in the game, which is the cost for
 * making an attack or defend move using a creature.
 */
public class EssenceCard extends Card {

    /**
     * Constructs an EssenceCard instance. The Essence cards always have the
     * same name.
     *
     * @param id the id of this EssenceCard.
     */
    public EssenceCard(int id) {
        super(id, "Essence");
    }
}
