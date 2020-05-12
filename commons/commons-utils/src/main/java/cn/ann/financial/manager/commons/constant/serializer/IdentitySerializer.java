package cn.ann.financial.manager.commons.constant.serializer;

import cn.ann.financial.manager.commons.constant.Identity;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Description：identity 序列化接口
 * <p>
 * Date: 2020-4-29 9:27
 *
 * @author 88475
 * @version v1.0
 */
public class IdentitySerializer extends StdSerializer<Identity> {
    private static final long serialVersionUID = 7916596276107481740L;

    public IdentitySerializer() {
        super(Identity.class);
    }

    protected IdentitySerializer(Class<Identity> t) {
        super(t);
    }

    @Override
    public void serialize(Identity identity, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeFieldName("name");
        generator.writeString(identity.name());
        generator.writeFieldName("id");
        generator.writeNumber(identity.getId());
        generator.writeFieldName("description");
        generator.writeString(identity.getDescription());
        generator.writeEndObject();
    }
}
