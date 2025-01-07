package xeed.mc.streamotes;

import xeed.mc.streamotes.emoticon.EmoticonRegistry;

import java.util.regex.MatchResult;

;

public class EmoteReplace {
	public static String findAndReplaceAll(String s) {
		return Streamotes.EMOTE_PATTERN.matcher(s).replaceAll(EmoteReplace::findAndReplaceResult);
	}

	private static String findAndReplaceResult(MatchResult x) {
		var mode = Streamotes.INSTANCE.getConfig().activationMode;
		var name = x.group();
		var hasFix = name.length() > 1 && name.charAt(0) == ':' && name.charAt(name.length() - 1) == ':';

		var emoticon = mode != ActivationOption.Required || hasFix
				? EmoticonRegistry.fromName(name)
				: null;

		if (emoticon == null && mode != ActivationOption.Disabled && hasFix) {
			name = name.substring(1, name.length() - 1);
			emoticon = EmoticonRegistry.fromName(name);
		}
		if (emoticon != null) Streamotes.log("Emote found: " + emoticon.getName());
		return emoticon != null ? Streamotes.CHAT_TRIGGER + emoticon.code + Streamotes.CHAT_SEPARATOR : name;
	}
}
