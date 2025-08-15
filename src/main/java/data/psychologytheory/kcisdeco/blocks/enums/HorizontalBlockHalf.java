package data.psychologytheory.kcisdeco.blocks.enums;

import net.minecraft.util.StringIdentifiable;

public enum HorizontalBlockHalf implements StringIdentifiable
{
    LEFT("left"),
    RIGHT("right");

    private final String name;

    private HorizontalBlockHalf(final String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    @Override
    public String asString()
    {
        return this.name;
    }
}
