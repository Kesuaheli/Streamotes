package xeed.mc.streamotes.mixin;

import net.minecraft.network.chat.contents.PlainTextContents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xeed.mc.streamotes.EmoteReplace;

@Mixin(PlainTextContents.LiteralContents.class)
public class MixinLiteralTextContent {
	@Mutable @Shadow
	@Final private String text;

	@Inject(method = "<init>", at = @At("RETURN"))
	void init(String text, CallbackInfo ci) {
		this.text = EmoteReplace.findAndReplaceAll(text);
	}
}
