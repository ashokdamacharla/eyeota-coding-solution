package com.eyeota.codingfun.cache;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LookupCacheImpl implements LookupCache {

    private String data;

    public static String NAME_SEGMENT_ID = "segmentId";

    public LookupCacheImpl(String data) {
        this.data = data;
    }

    @Override
    public SegmentConfig[] getSegmentFor(String orgKey, String paramKey) throws IOException {
        return getSegmentFor(orgKey, paramKey, "");
    }

    @Override
    public SegmentConfig[] getSegmentFor(String orgKey, String paramKey, String paramValKey) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);
        JsonNode segConfNodeList = jsonNode.findValue(orgKey).findValue(paramKey);
        JsonNode segConfNode = objectMapper.valueToTree(searchSegmentConfig(segConfNodeList, paramValKey));
        return objectMapper.readValue(segConfNode, SegmentConfig[].class);
    }

    private SegmentConfig[] searchSegmentConfig(JsonNode node, String valueKey) throws IOException {
        if(node  == null) {
            return new SegmentConfig[0];
        }
        JsonParser parser = node.traverse();
        List<SegmentConfig> configList = new ArrayList<>();
        SegmentConfig config;
        while(parser.nextToken() != JsonToken.END_ARRAY) {
            String name = parser.getCurrentName();
            if(name != null && (("".equals(valueKey) && name.equals(valueKey)) ||  (!"".equals(valueKey) && name.contains(valueKey)))) {
                while(parser.nextToken() != JsonToken.END_OBJECT) {
                    String elementName = parser.getCurrentName();
                    if(elementName != null && elementName.contains(NAME_SEGMENT_ID)) {
                        parser.nextToken();
                        config = new SegmentConfig();
                        config.setSegmentId(parser.getText());
                        configList.add(config);
                    }
                }
            }
        }
        return configList.toArray(new SegmentConfig[configList.size()]);
    }
}
