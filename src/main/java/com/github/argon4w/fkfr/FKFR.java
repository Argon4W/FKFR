package com.github.argon4w.fkfr;

import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(
		value	= FKFR.MOD_ID,
		dist	= Dist.CLIENT
)
public class FKFR {

	public static final String MOD_ID = "fkfr";
	public static final Logger LOGGER = LogUtils.getLogger();

    public FKFR(ModContainer container) {

    }
}
