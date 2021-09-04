package com.back.labhidro.auth;

/*
 * Configuracion firma publica y privada para el token
 * 
 * */

public class JwtConfig {
	
	public static final String RSA_PRIVADA = "\r\n"
			+ "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEowIBAAKCAQEAyqptDjmUbV0eSEkbQo1XwntZ8ggXFSgVWFhFwfdAwjV2/8S/\r\n"
			+ "gsvq8rfvsI+/Qhi42+EMI9j5c5dcV/F5gUIE8MIsKVfYxqyGLJi2Dt6f1kciiFoP\r\n"
			+ "ds6+Cw0rPTU+jKKN9Anj8dwKsLZbP0muGQnkqdWQ5JtanWkT/GT1cqptueBSn2Ug\r\n"
			+ "3HkMRsBfpRnEhTl/zOMEpwgO04HNH+a0z24jDt+wgs3NdYyo3EAsNfzn11nlMDsr\r\n"
			+ "UVO2n140CGu3RG0Xq7Er1q0KD5OAak9IUK2ayB/HxG12tVN0KDtWex/ydRzOzsaW\r\n"
			+ "zwXF/lYSl1H4O1KKqK0ryDBk/QlLXrD5ewRa5QIDAQABAoIBAQCQ7k2s9Ypqp0Pn\r\n"
			+ "p7hB4LhY0UOibRysuYyCMCY6run1uKxP/lCCZfev+dAEMNgymWFhhM/ctua1PhIA\r\n"
			+ "piQhRqgn0IQqULMqaOhO7iqL+bLOVvKgFd+nM/P+DT3VuMTxZ7IiSgxjwts2QkKA\r\n"
			+ "wN0q9MM8nQjQIzUrCqELRtusU6rCWB3q7+kEEcjQtL5AakzfGPltxOx+RTdFv6YN\r\n"
			+ "W+HQUddLiR7mqf+zt1hhXXtYtdLaZJd42OdDPcrTnNUlf5dH67QgXvC9tgokYirB\r\n"
			+ "wExnkVoFfAKyiwZHv4NgFY44hC465Cwmm2Hle9rOpc623kkBXMw1YucRlZ9N3xjG\r\n"
			+ "bUcY+dEFAoGBAPLT3JqTnPL2Uqy61C7c9m3EZpl80LM7rjxoLdOCI7mm+Ucb0X2i\r\n"
			+ "HghU5kh7ihSfmfWseDVq8otrlV9/aftwBulJLRcRViQcCDVsAM5g3fW8+rhNKdT5\r\n"
			+ "8fZ4HWP9pBMyeYX1B15oJzLZ1nCmFVeVc9KJChUHJJMutesl4zNBKlejAoGBANWo\r\n"
			+ "1oJzQOULBwIuwpkQRRPi4cDNHnUzSzpuIokmmQ3YZgpnW4n5KOUvfcifnfDtBJ/U\r\n"
			+ "KgAIGQttExiz07Ah1nf+cQA2eZ9nZEDcgBKlY5pz/ibV/KJbHAmfWonZYmGP8Ad3\r\n"
			+ "7QCVa5N75DZ38+MhjwFZhopknvDZzLo8tulwmEvXAoGAFrpJrZYfXIY9mrkp3vED\r\n"
			+ "h6aYS8lo5z2wwyqlD2Tg3yGHt2YdJ3sjAHihzSry85c0m8/2uFk4NmBtbLDKRKRp\r\n"
			+ "oTAeLkiLHXoaMpXXRXm/jJ6RgOqH/c0fHoiDh7ZXwww4X5ddhzKMaLAG6ChDWx07\r\n"
			+ "5/q3nNw8SiJXGJlFOKjiw60CgYAkTgwlHnBoevc157wpRhp2xpaVrEUApTajNxOn\r\n"
			+ "OmfHcn3kRqvHRg8s4mT9MiIkTiH7BpIcb0PSLTaRe/z8cMOAoDuTTa5c57ShUNZy\r\n"
			+ "VKgYFp2Y+8Nx0wlwmSNJSSbubQbcPgSHlNwys1qT5s4KSimCkTPLUE2xn1CqVKbA\r\n"
			+ "A+HdAQKBgDnMEq0BoggdyRBbRgzKm9bpdWEVlQ5sP9v5zcPMHZaJEeS/MFD1MtkY\r\n"
			+ "vOcbJA7Duj1+OKnkKpbluO+JblJSYPfaDECVufXrbeRI2CEtEqEb+lBh41SuzdAv\r\n"
			+ "okbY4cLDwhMwsPY7fAjUBosrTykB8hgTpgAEC52+WByjnv1lGX9w\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyqptDjmUbV0eSEkbQo1X\r\n"
			+ "wntZ8ggXFSgVWFhFwfdAwjV2/8S/gsvq8rfvsI+/Qhi42+EMI9j5c5dcV/F5gUIE\r\n"
			+ "8MIsKVfYxqyGLJi2Dt6f1kciiFoPds6+Cw0rPTU+jKKN9Anj8dwKsLZbP0muGQnk\r\n"
			+ "qdWQ5JtanWkT/GT1cqptueBSn2Ug3HkMRsBfpRnEhTl/zOMEpwgO04HNH+a0z24j\r\n"
			+ "Dt+wgs3NdYyo3EAsNfzn11nlMDsrUVO2n140CGu3RG0Xq7Er1q0KD5OAak9IUK2a\r\n"
			+ "yB/HxG12tVN0KDtWex/ydRzOzsaWzwXF/lYSl1H4O1KKqK0ryDBk/QlLXrD5ewRa\r\n"
			+ "5QIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
}
