package com.ejemplo.sprint5.sprint5.auth;

public class JwtConfig 
{
	//Llave secreta tipo MAC
	public static final String  LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
//	Llave RSA Publica
	public static final String  RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEpAIBAAKCAQEAw2xIDtWkX6q1meMFd9HfBZBKsAmpSTyLNeciHyqiXllGfA6k\r\n"
			+ "0EiIVM2OQRkUujPch9NvbwShap83Lb4/nV7QjQwJJAMeZ1kJG7vEL1Lh3jb72Xjb\r\n"
			+ "+0PtimvTOIdZCCxIoSMeZN6Bzg28ofGUUIjuD/j25KH1jBe5bgSGWlehxB1Rnppu\r\n"
			+ "2ZG8jgiRDtU3JH/bv+0Zed50E/ahz0vOYH6rBXrmRn4zUokiMDsJhrei+TYZEHI9\r\n"
			+ "sTUxNRgPASLefqO1shiir/UI9C8iMHXtyq44/m0XD1L01l+p2KrYu7WAmUsxaGnz\r\n"
			+ "8Lw4XbaVbWiWOlQaKGtMAtOAvAfOeUDzn+LxFwIDAQABAoIBAHZMwV5BlGzZUW/j\r\n"
			+ "zzaWlLSwgZYJczJDdV5fGffV/k6xKosE7l6r2ePEP75GqZaCuJh8tTN2kSSTV3rY\r\n"
			+ "dXgVopgP1iS3wpr7ehkQ1JV1TggQLuuaT0jO+ShXV5kbzhi1fg4VzVtwmxGanTtH\r\n"
			+ "m+oYykxfAU6sgJGa9wSTtqVP8TxfF+rkPgeE964OpJdJJ28PT0g0EZ/eNaCiw+nw\r\n"
			+ "H+pE0BR2erbpISITJ3z8ByJ1LMiVOi5YhQEvPAhRHnLuIE7ZhDhToBDzqSlfjDrE\r\n"
			+ "XTLUhYe6BcgInp0oZj9n16kL5XRKR8h8Eq5OiTZtsKvGXK3XRhYad+WlKFy1sN1l\r\n"
			+ "zczWMSECgYEA7gjR1Fu3Z8wWtI1yXXPziuU5qp9Y3L/DXQJalcE2k+rg3/t0lSA+\r\n"
			+ "cmv5lUGmLLueP8kVseASwWMvFAd/KkVleDXY8fY5wzbF2LwnSne/ZiNMxyDHpbUL\r\n"
			+ "JdixPrcNMUGBWRwxpfkftHMn9Iq1dagoA2+8j1+7BSNLGtS4fhmb+7ECgYEA0iwl\r\n"
			+ "A4s/sIqXSvUmb19rWLXfb3k1fxLykD9/ajp41qo61sJ3UiZDD/ACKjIOcMA4+18T\r\n"
			+ "ygRUQCmvlaxP/AYFtrA6CQIiFv71z6F8SpqUYSt3QoDWnxZxuycfjmkQrGqMXnv1\r\n"
			+ "VJQvnYVfRwwepYC3w/Zi+0eYY6HIlYBXeZeSE0cCgYA3NRR1jFAdYthhR+0ef6Yc\r\n"
			+ "sM2PJSYXDOLiz4jy8r0V0YQWbtu7Op9BKonYdEF2Xo11fc3qU24GLFre6l40D6RZ\r\n"
			+ "HSQT+merPyjj+7+hJeTea5GDHbcwV60eOgPh59LHecnmQQPC2uTflVXPAdzUTQv6\r\n"
			+ "wA+bsThPGMBymKOxm37yAQKBgQCk733QrSu7ONgIPCEAkApnILuK+L0xz0XKS/qK\r\n"
			+ "+/4uwILSXORU9K/2Ruz7/jJ063UMWA3ooMMZY11nAUX3yyYau2uf/CuSjl2Lpvgq\r\n"
			+ "d+rv4rOB3igPywZP0CEkmnE6TZeA4pih2nUPkkY2NF2sljogsC/3cLU3oxQNtx1u\r\n"
			+ "z7MUKwKBgQDpmpQLYmv10s7yQvbItNoso77eWPegr6lctnsK64uq7mrwX6Nv/fCt\r\n"
			+ "N2Td3CHumRzHCSD6HizPzvc8RR6Bsz4hRTfLJPT6uT6srGPlOvdxWxQt1EW1RV+J\r\n"
			+ "Kpkyw7fMZI01PpXIwbBOcKaPtUtzDOnrdq5CrTvR3CQffdgnGE0Scw==\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String  RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw2xIDtWkX6q1meMFd9Hf\r\n"
			+ "BZBKsAmpSTyLNeciHyqiXllGfA6k0EiIVM2OQRkUujPch9NvbwShap83Lb4/nV7Q\r\n"
			+ "jQwJJAMeZ1kJG7vEL1Lh3jb72Xjb+0PtimvTOIdZCCxIoSMeZN6Bzg28ofGUUIju\r\n"
			+ "D/j25KH1jBe5bgSGWlehxB1Rnppu2ZG8jgiRDtU3JH/bv+0Zed50E/ahz0vOYH6r\r\n"
			+ "BXrmRn4zUokiMDsJhrei+TYZEHI9sTUxNRgPASLefqO1shiir/UI9C8iMHXtyq44\r\n"
			+ "/m0XD1L01l+p2KrYu7WAmUsxaGnz8Lw4XbaVbWiWOlQaKGtMAtOAvAfOeUDzn+Lx\r\n"
			+ "FwIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
}
