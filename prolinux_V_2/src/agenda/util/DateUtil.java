package agenda.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Funciones de ayuda (Toolkit), para manejar fechas
 * 
 * @author Agustin Aguilera
 */
public class DateUtil {

    /** Patrón usado para la conversion */
    private static final String FECHA_PATRON = "dd.MM.yyyy";

    /** Formato de la fecha */
    private static final DateTimeFormatter FECHA_FORMATO = 
            DateTimeFormatter.ofPattern(FECHA_PATRON);

    /**
     * Returns the given date as a well formatted String. The above defined 
     * {@link DateUtil#DATE_PATTERN} is used.
     * 
     * @param date the date to be returned as a string
     * @return formatted string
     */
    public static String formatear(LocalDate fecha) {
        if (fecha == null) {
            return null;
        }
        return FECHA_FORMATO.format(fecha);
    }

    /**
     * Converts a String in the format of the defined {@link DateUtil#DATE_PATTERN} 
     * to a {@link LocalDate} object.
     * 
     * Returns null if the String could not be converted.
     * 
     * @param dateString the date as String
     * @return the date object or null if it could not be converted
     */
    public static LocalDate parse(String fechaString) {
        try {
            return FECHA_FORMATO.parse(fechaString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Checks the String whether it is a valid date.
     * 
     * @param dateString
     * @return true if the String is a valid date
     */
    public static boolean validDate(String fechaString) {
        // Try to parse the String.
        return DateUtil.parse(fechaString) != null;
    }
}