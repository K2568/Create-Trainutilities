package net.adeptstack.registry;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.adeptstack.Blocks.Doors.TrainSlidingDoorBlockEntity;
import net.adeptstack.Blocks.Doors.TrainSlidingDoorBlockRenderer;

import static net.adeptstack.Main.REGISTRATE;

public class ModBlockEntities {

    public static final BlockEntityEntry<TrainSlidingDoorBlockEntity> SLIDING_DOOR =
            REGISTRATE.blockEntity("sliding_door", TrainSlidingDoorBlockEntity::new)
                    .renderer(() -> TrainSlidingDoorBlockRenderer::new)
                    .validBlocks(ModBlocks.DOOR_ICE, ModBlocks.DOOR_IC2, ModBlocks.DOOR_RRX, ModBlocks.DOOR_FLIRT)
                    .register();

    public static void register() {}
}
