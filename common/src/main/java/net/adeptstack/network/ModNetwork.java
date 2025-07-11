package net.adeptstack.network;

import de.mrjulsen.mcdragonlib.client.OverlayManager;

public class ModNetwork {
    private static long currentRouteOverlayId;

    public static void setRouteOverlay(long id) {
        removeRouteOverlay();
        currentRouteOverlayId = id;
    }

    public static void removeRouteOverlay() {
        if (OverlayManager.has(currentRouteOverlayId)) {
            OverlayManager.remove(currentRouteOverlayId);
        }
    }
}

//import dev.architectury.networking.NetworkChannel;
//import net.adeptstack.network.packets.ChangeDoorSoundPacket;
//import net.adeptstack.network.packets.PlatformBlockPacket;
//import net.minecraft.resources.ResourceLocation;
//
//import static net.adeptstack.Main.MOD_ID;
//
//@SuppressWarnings({"unused","removal"})
//public class ModNetwork {
//
//    public static final NetworkChannel CHANNEL = NetworkChannel.create(ResourceLocation.fromNamespaceAndPath(MOD_ID, MOD_ID + "_network"));
//
//    public static void init() {
//        CHANNEL.register(PlatformBlockPacket.class, PlatformBlockPacket::encode, PlatformBlockPacket::new, PlatformBlockPacket::apply);
//        CHANNEL.register(ChangeDoorSoundPacket.class, ChangeDoorSoundPacket::encode, ChangeDoorSoundPacket::new, ChangeDoorSoundPacket::apply);
//    }
//}
