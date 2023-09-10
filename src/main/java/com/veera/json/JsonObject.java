package com.veera.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class JsonObject {

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        Path path = Path.of("D:\\Intellij Workspace\\Maven-Project\\src\\employee.json");
        Path schemaPath = Path.of("D:\\Intellij Workspace\\Maven-Project\\src\\employeeSchema.json");
        String json= Files.readString(path);
        String schema = Files.readString(schemaPath);

        JsonNode jsonNode = mapper.readTree(json);
        JsonSchemaFactory schemaFactory=JsonSchemaFactory.getInstance();
        JsonSchema jsonSchema=schemaFactory.getSchema(schema);

        Set<ValidationMessage> msgs = jsonSchema.validate(jsonNode);

        for(ValidationMessage msg:msgs)
        {
            System.out.println(msg);
        }

        if(msgs.isEmpty())
        {
            Object obj=mapper.readValue(json,Object.class);
            System.out.println(obj);
        }

    }
}
