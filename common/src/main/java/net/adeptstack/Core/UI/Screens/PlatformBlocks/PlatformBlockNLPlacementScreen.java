package net.adeptstack.Core.UI.Screens.PlatformBlocks;

import net.adeptstack.Core.UI.Screens.PlatformBlockScreenBase;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.function.Consumer;
import java.util.function.Function;

public class PlatformBlockNLPlacementScreen extends PlatformBlockScreenBase {
    public <T extends IntegerProperty> PlatformBlockNLPlacementScreen(int variant, T property, Function<Integer, TextureResult> textureGetter, Consumer<Integer> onDone, String id, int typeID) {
        super(variant, property, textureGetter, onDone, id, typeID);
    }
}
