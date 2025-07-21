package net.adeptstack.network.packets;

import de.mrjulsen.mcdragonlib.net.BaseNetworkPacket;
import dev.architectury.networking.NetworkManager;
import net.adeptstack.blocks.panelBlocks.platformBlocks.PlatformBlockCH;
import net.adeptstack.blocks.panelBlocks.platformBlocks.PlatformBlockDE;
import net.adeptstack.blocks.panelBlocks.platformBlocks.PlatformBlockNL;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class PlatformBlockPacket extends BaseNetworkPacket<PlatformBlockPacket> {

    public BlockPos pos;
    public int signblock;

    public PlatformBlockPacket () { }

    public PlatformBlockPacket(FriendlyByteBuf buf) {
        this(buf.readBlockPos(), buf.readInt());
    }

    public PlatformBlockPacket(BlockPos pos, int signblock) {
        this.pos = pos;
        this.signblock = signblock;
    }

    @Override
    public void encode(PlatformBlockPacket packet, RegistryFriendlyByteBuf buf) {
        buf.writeBlockPos(packet.pos);
        buf.writeInt(packet.signblock);
    }

    @Override
    public PlatformBlockPacket decode(RegistryFriendlyByteBuf buf) {
        return new PlatformBlockPacket(buf.readBlockPos(), buf.readInt());
    }

    @Override
    public void handle(PlatformBlockPacket packet, Supplier<NetworkManager.PacketContext> supplier) {
        apply(packet, supplier);
    }


    public void apply(PlatformBlockPacket packet, Supplier<NetworkManager.PacketContext> contextSupplier) {
        contextSupplier.get().queue(() -> {
            BlockState state = contextSupplier.get().getPlayer().level().getBlockState(packet.pos);
            if (signblock >= 0) {
                if (state.getBlock() instanceof PlatformBlockNL) {
                    state = state.setValue(PlatformBlockNL.SIGN_BLOCKS, packet.signblock);
                } else if (state.getBlock() instanceof PlatformBlockDE) {
                    state = state.setValue(PlatformBlockDE.SIGN_BLOCKS, packet.signblock);
                } else if (state.getBlock() instanceof PlatformBlockCH) {
                    state = state.setValue(PlatformBlockCH.SIGN_BLOCKS, packet.signblock);
                }
            }
            contextSupplier.get().getPlayer().level().setBlockAndUpdate(packet.pos, state);
        });
    }
}
