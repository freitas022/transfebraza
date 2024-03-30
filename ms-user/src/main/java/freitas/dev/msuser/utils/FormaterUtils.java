package freitas.dev.msuser.utils;

public class FormaterUtils {

	private FormaterUtils() {
	}

	public static String formatCpf(String cpf) {
		return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
	}

	public static String formatPhone(String phone) {
		return phone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
	}
}
