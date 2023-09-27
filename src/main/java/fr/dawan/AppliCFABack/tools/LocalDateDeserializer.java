package fr.dawan.AppliCFABack.tools;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
	@Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        String dateStr = parser.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateStr, formatter);
        return offsetDateTime.toLocalDate();
    }
}
