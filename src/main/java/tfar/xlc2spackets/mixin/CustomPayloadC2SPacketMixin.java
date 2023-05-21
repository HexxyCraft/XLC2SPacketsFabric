package tfar.xlc2spackets.mixin;

import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(CustomPayloadC2SPacket.class)
public class CustomPayloadC2SPacketMixin {
    // https://github.com/TheRandomLabs/RandomPatches/blob/a2a9f19caab38f3f4078bf5acadcca7e300d4788/src/main/java/com/therandomlabs/randompatches/mixin/client/packetsizelimits/CustomPayloadC2SPacketMixin.java
    int newLimit = 0x1000000;

    @ModifyConstant(method = "<init>(Lnet/minecraft/network/PacketByteBuf;)V",constant = @Constant(intValue = 32767))
    private int xlC2SPackets(int old) {
        return newLimit;
    }

    @ModifyConstant(method = "<init>(Lnet/minecraft/network/PacketByteBuf;)V",constant = @Constant(stringValue = "Payload may not be larger than 32767 bytes"))
    private String xlC2SPackets(String old) {
        return "Payload may not be larger than " + newLimit + " bytes";
    }
}