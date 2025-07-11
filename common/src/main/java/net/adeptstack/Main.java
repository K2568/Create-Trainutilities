package net.adeptstack;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;
import de.mrjulsen.mcdragonlib.net.DLNetworkManager;
import net.adeptstack.blocks.doors.slidingDoor.TrainSlidingDoorBlock;
import net.adeptstack.network.ModNetwork;
import net.adeptstack.network.packets.ChangeDoorSoundPacket;
import net.adeptstack.network.packets.PlatformBlockPacket;
import net.adeptstack.registry.*;
import net.createmod.catnip.lang.FontHelper;
import net.fabricmc.api.EnvType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class Main {
    public static final String MOD_ID = "trainutilities";
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public static void init() {
        // Write common init code here.
        ModBlockEntities.register();
        networkInit();
        ModTabs.CREATIVE_MODE_TABS.register();
        ModBlocks.register();
        ModItems.register();
        ModSounds.SOUND_EVENTS.register();
        if (Dist.CLIENT.isClient()) {
            ModPartialModels.init();
        }
        ModTags.register();
    }

    static void networkInit() {
        DLNetworkManager.registerPackets(MOD_ID, List.of(
                PlatformBlockPacket.class
        ), List.of(
                ChangeDoorSoundPacket.class
        ));
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    @Nullable
    public static KineticStats create(Item item) {
        if (item instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            if (block instanceof TrainSlidingDoorBlock) {
                return new KineticStats(block);
            }
            // ...
        }
        return null;
    }

    static {
        REGISTRATE.setTooltipModifierFactory(item -> {
            return new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE).andThen(TooltipModifier.mapNull(create(item)));
        }).defaultCreativeTab(ModTabs.TRAINUTILS_TAB.getKey()); // <-----
    }
}