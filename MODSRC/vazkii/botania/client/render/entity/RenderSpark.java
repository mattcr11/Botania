/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 * 
 * File Created @ [Aug 21, 2014, 5:53:22 PM (GMT)]
 */
package vazkii.botania.client.render.entity;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.common.entity.EntitySpark;
import vazkii.botania.common.item.ItemSpark;
import vazkii.botania.common.item.ItemSparkUpgrade;

public class RenderSpark extends RenderEntity {

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		EntitySpark spark = (EntitySpark) par1Entity;
		IIcon iicon = ItemSpark.worldIcon;

		GL11.glPushMatrix();
		GL11.glTranslatef((float)par2, (float)par4, (float)par6);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.05F);

		int time = ClientTickHandler.ticksInGame;
		time += new Random(par1Entity.getEntityId()).nextInt();
		GL11.glColor4f(1F, 1F, 1F, 0.7F + 0.3F * (float) (Math.sin(time / 5.0) + 0.5) * 2);

		float scale = 0.75F + 0.1F * (float) Math.sin((double) time / 10);
		GL11.glScalef(scale, scale, scale);
		bindEntityTexture(par1Entity);
		Tessellator tessellator = Tessellator.instance;

		float r = 180.0F - renderManager.playerViewY;
		GL11.glRotatef(r, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(25F * (float) Math.sin((double) time / 15), 1F, 0F, 0F);
		func_77026_a(tessellator, iicon);
		int upgrade = spark.getUpgrade() - 1;
		if(upgrade >= 0 && upgrade < ItemSparkUpgrade.worldIcons.length) {
			GL11.glTranslatef(-0.02F + (float) Math.sin((double) time / 20) * 0.2F, 0.24F + (float) Math.cos((double) time / 20) * 0.2F, 0.005F);
			GL11.glScalef(0.2F, 0.2F, 0.2F);
			func_77026_a(tessellator, ItemSparkUpgrade.worldIcons[upgrade]);
		}
		GL11.glRotatef(-r, 0.0F, 1.0F, 0.0F);

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return TextureMap.locationItemsTexture;
	}

	private void func_77026_a(Tessellator p_77026_1_, IIcon p_77026_2_) {
		float f = p_77026_2_.getMinU();
		float f1 = p_77026_2_.getMaxU();
		float f2 = p_77026_2_.getMinV();
		float f3 = p_77026_2_.getMaxV();
		float f4 = 1.0F;
		float f5 = 0.5F;
		float f6 = 0.25F;


		p_77026_1_.startDrawingQuads();
		p_77026_1_.setNormal(0.0F, 1.0F, 0.0F);
		p_77026_1_.setBrightness(240);
		p_77026_1_.addVertexWithUV(0.0F - f5, 0.0F - f6, 0.0D, f, f3);
		p_77026_1_.addVertexWithUV(f4 - f5, 0.0F - f6, 0.0D, f1, f3);
		p_77026_1_.addVertexWithUV(f4 - f5, f4 - f6, 0.0D, f1, f2);
		p_77026_1_.addVertexWithUV(0.0F - f5, f4 - f6, 0.0D, f, f2);
		p_77026_1_.draw();

	}

}
