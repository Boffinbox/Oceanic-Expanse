package com.sirsquidly.oe.util.handlers;

import com.sirsquidly.oe.entity.*;
import com.sirsquidly.oe.entity.render.*;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler 
{
	public static void registerEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCod.class, RenderCod::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySalmon.class, RenderSalmon::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPufferfish.class, RenderPufferfish::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTurtle.class, RenderTurtle::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDrowned.class, RenderDrowned::new);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityGlowSquid.class, RenderGlowSquid::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCrab.class, RenderCrab::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPickled.class, RenderPickled	::new);	
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTrident.class, RenderTrident	::new);	
	}
}