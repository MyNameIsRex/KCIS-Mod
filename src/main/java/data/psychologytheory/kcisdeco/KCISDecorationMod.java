package data.psychologytheory.kcisdeco;

import data.psychologytheory.kcisdeco.blocks.ModBlocks;
import data.psychologytheory.kcisdeco.blocks.blockentities.ModBlockEntities;
import data.psychologytheory.kcisdeco.entities.ModEntities;
import data.psychologytheory.kcisdeco.items.itemgroups.ModItemGroups;
import data.psychologytheory.kcisdeco.networking.ModPackets;
import data.psychologytheory.kcisdeco.screens.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KCISDecorationMod implements ModInitializer
{
	public static final String MOD_ID = "kcisdeco";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModEntities.registerEntities();
		ModBlockEntities.registerModBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModPackets.registerCommon();
		ModPackets.registerC2SPackets();
	}
}