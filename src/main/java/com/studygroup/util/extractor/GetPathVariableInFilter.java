package com.studygroup.util.extractor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class GetPathVariableInFilter {

  private GetPathVariableInFilter(){

  }
  public static String getFirstPathVarAfterGivenURI(HttpServletRequest request, String pat) {

    String pathVariable = null;
    Pattern pattern = Pattern.compile(pat + "(\\w+)");
    Matcher matcher = pattern.matcher(request.getRequestURI());

    if (matcher.find()) {
      pathVariable = matcher.group(1);
    }
    return pathVariable;
  }

  public static String getNonASCIIFirstURI(HttpServletRequest request, String pat)
      throws UnsupportedEncodingException {
    String pathVariable = null;
    Pattern pattern = Pattern.compile(pat + "([^/]+)");
    Matcher matcher = pattern.matcher(request.getRequestURI());

    if (matcher.find()) {
      pathVariable = matcher.group(1);
    }
    return URLDecoder.decode(pathVariable, StandardCharsets.UTF_8.toString());
  }
}
