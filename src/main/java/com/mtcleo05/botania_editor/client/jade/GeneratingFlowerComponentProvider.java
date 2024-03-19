package com.mtcleo05.botania_editor.client.jade;

import com.mtcleo05.botania_editor.BotaniaEditor;
import com.mtcleo05.botania_editor.config.CommonConfig;
import com.mtcleo05.botania_editor.utils.JadeUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;
import vazkii.botania.api.block_entity.GeneratingFlowerBlockEntity;
import vazkii.botania.common.item.BotaniaItems;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public enum GeneratingFlowerComponentProvider implements IBlockComponentProvider {
    INSTANCE;

    private static final ResourceLocation UID = new ResourceLocation(BotaniaEditor.MODID, "botania_flower");

    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {

        try{
            GeneratingFlowerBlockEntity generatingFlowerEntity = (GeneratingFlowerBlockEntity) blockAccessor.getBlockEntity();

            if(generatingFlowerEntity != null){
                IElementHelper elements = iTooltip.getElementHelper();
                IElement icon = elements.item(new ItemStack(BotaniaItems.spark), 0.5f).size(new Vec2(10, 10)).translate(new Vec2(0, -1));
                icon.message(null);
                iTooltip.add(icon);
                iTooltip.append(Component.translatable("botania_editor.mana_display", generatingFlowerEntity.getMana(), generatingFlowerEntity.getMaxMana()));

                if(CommonConfig.SHOULD_SHOW_ADVANCED_TOOLTIP.get()){
                    if(JadeUtils.JADE_FLOWER_MAP.containsKey(generatingFlowerEntity.getClass())){

                        List<ForgeConfigSpec.ConfigValue<?>> configs = JadeUtils.JADE_FLOWER_MAP.get(generatingFlowerEntity.getClass());

                        for (int i = 0; i < configs.size(); i++) {
                            if(i == 0){
                                iTooltip.add(Component.translatable("botania_editor.prod", configs.get(i).get()));
                            }

                            if(i == 1){
                                iTooltip.add(Component.translatable("botania_editor.decay", configs.get(i).get()));
                            }
                        }
                    }
                }
            }
        } catch (Exception ignored){}
    }


    @Override
    public ResourceLocation getUid() {
        return UID;
    }

}
