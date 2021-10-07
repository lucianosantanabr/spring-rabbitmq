package com.github.lucianosantanabr.commons;

import java.text.Normalizer;
import java.util.Optional;
import org.springframework.util.StringUtils;

public interface StringUtility {

  default boolean isEmpty(String str) {
    return str == null || Constants.BLANK.equals(str.trim());
  }

  default boolean isNotEmpty(String str) {
    return str != null && !Constants.BLANK.equals(str.trim());
  }

  default boolean isNull(Object obj) {
    return obj == null;
  }

  default boolean notNull(Object obj) {
    return obj != null;
  }

  default Optional<String> firstOf(String... strings) {
    for (String str : strings) {
      if (isNotEmpty(str)) {
        return Optional.of(str);
      }
    }
    return Optional.empty();
  }

  default String simpleName(Object instance) {
    return instance.getClass().getSimpleName();
  }

  default String join(String separator, String... strings) {
    return String.join(separator, strings);
  }

  default String joinHyphen(String... strings) {
    return String.join(Constants.HYPHEN, strings);
  }

  default String joinDot(String... strings) {
    return String.join(Constants.DOT, strings);
  }

  default Optional<String> findFirst(String... strings) {
    for (String str : strings) {
      if (StringUtils.hasLength(str)) {
        return Optional.of(str);
      }
    }
    return Optional.empty();
  }

  default Double toDouble(String amount) {
    if (isEmpty(amount)) {
      return null;
    }
    return Double.valueOf(amount.replaceAll(",", ""));
  }

  default String removeAccents(String str) {
    if (str != null){
      str = Normalizer.normalize(str, Normalizer.Form.NFD);
      str = str.replaceAll("[^\\p{ASCII}]", "");
    }
    return str;
  }

}
