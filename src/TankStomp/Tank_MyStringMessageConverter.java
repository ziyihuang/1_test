package TankStomp;

import java.nio.charset.Charset;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;

public class Tank_MyStringMessageConverter extends AbstractMessageConverter {
	
	private final Charset defaultCharset;


	public Tank_MyStringMessageConverter() {
		this(Charset.forName("UTF-8"));
	}

	public Tank_MyStringMessageConverter(Charset defaultCharset) {
		super(new MimeType("application", "json", defaultCharset));
		this.defaultCharset = defaultCharset;
	}


	@Override
	protected boolean supports(Class<?> clazz) {
		return (String.class == clazz);
	}

	@Override
	protected Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint) {
		Charset charset = getContentTypeCharset(getMimeType(message.getHeaders()));
		Object payload = message.getPayload();
		return (payload instanceof String ? payload : new String((byte[]) payload, charset));
	}

	@Override
	protected Object convertToInternal(Object payload, MessageHeaders headers, Object conversionHint) {
		if (byte[].class == getSerializedPayloadClass()) {
			Charset charset = getContentTypeCharset(getMimeType(headers));
			payload = ((String) payload).getBytes(charset);
		}
		return payload;
	}

	private Charset getContentTypeCharset(MimeType mimeType) {
		if (mimeType != null && mimeType.getCharset() != null) {
			return mimeType.getCharset();
		}
		else {
			return this.defaultCharset;
		}
	}
}
