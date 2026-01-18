package com.github.argon4w.fkfr.mixins;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.extensions.IBakedModelExtension;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Collections;
import java.util.List;

@Mixin(SimpleBakedModel.class)
public class SimpleBakedModelMixin implements IBakedModelExtension {

	// The declared render types that are used for rendering this model as blocks.
	// 模型所指定的在方块渲染中所使用的RenderType.
	@Shadow @Final protected ChunkRenderTypeSet blockRenderTypes;

	// Forgified Fabric Renderer API uses NeoForge's extended getQuads method for VanillaModelEncoder.
	// Forgified Fabric Renderer API使用了NeoForge提供的扩展getQuads方法来为原版未兼容Fabric模型的BakedModel兼容.
	@Unique
	@NotNull
	@Override
	public List<BakedQuad> getQuads(
			@Nullable	BlockState		blockState,
			@Nullable	Direction		direction,
			@NotNull	RandomSource	random,
			@NotNull	ModelData		modelData,
			@Nullable	RenderType		renderType
	) {
		// Make sure that this model is rendered as "blocks" by checking blockState and renderType, or we just fall back to return the original quads for safety.
		// 通过blockState和renderType确保模型是作为方块渲染的, 否则直接回退到原模型的quads防止出现渲染异常.
		if (		blockState			== null
				||	renderType			== null
				||	blockRenderTypes	== null
				||	blockRenderTypes.contains(renderType)
		) {
			// If this model has not declared its render types, or the render type check is passed, than return the original quads.
			// 如果这个模型没有声明RenderType, 或者RenderType检查通过, 那么返回原模型的quads.
			return IBakedModelExtension.super.getQuads(
					blockState,
					direction,
					random,
					modelData,
					renderType
			);
		}

		// Otherwise, return an empty quad list to prevent the quads from being rendered wrongly because the check is failed.
		// 否则, 测试未通过, 返回一个空的quad列表防止quads被错误渲染.
		return Collections.emptyList();
	}
}
