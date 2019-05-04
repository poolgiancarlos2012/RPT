package clase;

public class Password{

	protected static int Longitud;
	private static char GetAscii;
	private static int NumAleatorio;
	private static int esFuerte;
	private static int Minuscula = 0;
	private static int mayuscula = 0;
	private static int Numeros = 0;
	private static int longitudpass = 0;

	public static int getLongitud() {
		return Longitud;
	}

	public static void setLongitud(int Longitud) {
		Password.Longitud = Longitud;
	}

	public static int getNumAleatorio() {
		return NumAleatorio;
	}

	public static void setNumAleatorio(int NumAleatorio) {
		Password.NumAleatorio = NumAleatorio;
	}

	public static int getMinuscula() {
		return Minuscula;
	}

	public static void setMinuscula(int Minuscula) {
		Password.Minuscula = Minuscula;
	}

	public static int getMayuscula() {
		return mayuscula;
	}

	public static void setMayuscula(int mayuscula) {
		Password.mayuscula = mayuscula;
	}

	public static int getNumeros() {
		return Numeros;
	}

	public static void setNumeros(int Numeros) {
		Password.Numeros = Numeros;
	}
	
	
	
	public Password() {
		this.Longitud = longitudpass;
	}

	public Password(int longitud) {
		this.Longitud = longitud;
	}
	
	public static char generarPassword() {
		GetAscii = 'A';
		for (int i = 1; i <= Longitud; i++) {
			NumAleatorio = 32 + (int) (Math.random() * 92);
			GetAscii = (char) NumAleatorio;
			if (i == Longitud) {
				return GetAscii;
			}
		}
		return GetAscii;
	}
	
	public static void GetSeguridadContraseña() {
		if (NumAleatorio >= 65 && NumAleatorio <= 90) {
			mayuscula++;
		}
		if (NumAleatorio >= 97 && NumAleatorio <= 122) {
			Minuscula++;
		}
		if (NumAleatorio >= 48 && NumAleatorio <= 57) {
			Numeros++;
		}
	}
	
	public static String esFuerte() {
		if (Minuscula > 1 && mayuscula > 2 && Numeros > 5) {
			return "La contraseña es Segura!";
		} else {
			return "la contraseña es Débil!!";
		}
	}	

}