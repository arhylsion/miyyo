package com.whitrgg.miyyo.client.render;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexRendering;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class BlockVisualizer {

    public static void register() {
        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null || client.world == null) return;

            HitResult hit = client.crosshairTarget;
            if (hit instanceof BlockHitResult blockHit) {
                renderTargetBox(
                        context.matrixStack(),
                        blockHit.getBlockPos(),
                        context.camera().getPos(),
                        context.consumers().getBuffer(RenderLayer.getLines())
                );
            }
        });
    }

    private static void renderTargetBox(MatrixStack matrices, BlockPos pos, Vec3d cameraPos, VertexConsumer vertices) {
        matrices.push();

        double x = pos.getX() - cameraPos.x;
        double y = pos.getY() - cameraPos.y;
        double z = pos.getZ() - cameraPos.z;

        matrices.translate(x, y, z);

        Box box = new Box(0, 0, 0, 1, 1, 1).expand(0.01);

        VertexRendering.drawBox(
                matrices,
                vertices,
                box,
                0f, 1f, 0f, 1f
        );

        matrices.pop();
    }
}