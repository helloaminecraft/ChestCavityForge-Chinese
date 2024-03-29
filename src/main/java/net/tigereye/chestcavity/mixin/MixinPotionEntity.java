package net.tigereye.chestcavity.mixin;



import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.tigereye.chestcavity.util.ChestCavityUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PotionEntity.class, priority = 1100)
public class MixinPotionEntity extends ProjectileItemEntity {

    public MixinPotionEntity(EntityType<? extends ProjectileItemEntity> entityType, World world) {
        super(entityType, world);
    }
    @Shadow
    protected Item getDefaultItem() {
        return null;
    }

    @Inject(at = @At("TAIL"), method = "applyWater")
    private void ChestCavityPotionEntityDamageEntitiesHurtByWaterMixin(CallbackInfo info) {
        ChestCavityUtil.splashHydrophobicWithWater((PotionEntity) (Object) this);
    }

}
