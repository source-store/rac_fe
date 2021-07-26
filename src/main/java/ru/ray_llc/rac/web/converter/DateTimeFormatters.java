package ru.ray_llc.rac.web.converter;

import static ru.ray_llc.rac.util.DateTimeUtil.parseLocalDate;
import static ru.ray_llc.rac.util.DateTimeUtil.parseLocalTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.format.Formatter;

public class DateTimeFormatters {

  public static class LocalDateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String text, Locale locale) {
      return parseLocalDate(text);
    }

    @Override
    public String print(LocalDate lt, Locale locale) {
      return lt.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
  }

  public static class LocalTimeFormatter implements Formatter<LocalTime> {

    @Override
    public LocalTime parse(String text, Locale locale) {
      return parseLocalTime(text);
    }

    @Override
    public String print(LocalTime lt, Locale locale) {
      return lt.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
  }
}
