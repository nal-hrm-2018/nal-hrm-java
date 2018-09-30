package nals.hrm.api_nals_hrm.define;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class ConvertStringLibrary {
	
	public static String createSlug(String title) {
		title = title.replaceAll("\\s+", " ");
		title = title.replaceAll("(^\\s+|\\s+$)", "");
		String slug = Normalizer.normalize(title, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		slug = pattern.matcher(slug).replaceAll("");
		slug = slug.toLowerCase();
		slug = slug.replaceAll("đ", "d");
		slug = slug.replaceAll("([^0-9a-z-\\s])", "");
		return slug;
		}
}
