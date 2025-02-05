package com.mtcleo05.botania_editor.mixin;

import com.mtcleo05.botania_editor.config.server.GeneratingFloraConfig;
import com.mtcleo05.botania_editor.config.server.SpreaderConfig;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import vazkii.botania.common.block.flower.generating.MunchdewBlockEntity;

@Mixin(MunchdewBlockEntity.class)
public class MunchdewMixin {

    @ModifyArg(remap = false, method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/generating/MunchdewBlockEntity;addMana(I)V"))
    public int changeManaLeaf(int par1){
        return GeneratingFloraConfig.MUNCHDEW_MANA.get();
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;betweenClosed(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)Ljava/lang/Iterable;"))
    public Iterable<BlockPos> changeRange(BlockPos pFirstPos, BlockPos pSecondPos){
        return BlockPos.betweenClosed(
            new BlockPos(
                -GeneratingFloraConfig.MUNCHDEW_RANGE.get(),
                -GeneratingFloraConfig.MUNCHDEW_RANGE_Y.get(),
                -GeneratingFloraConfig.MUNCHDEW_RANGE.get()
            ),
            new BlockPos(
                GeneratingFloraConfig.MUNCHDEW_RANGE.get(),
                GeneratingFloraConfig.MUNCHDEW_RANGE_Y.get(),
                GeneratingFloraConfig.MUNCHDEW_RANGE.get()
            )
        );
    }
}
