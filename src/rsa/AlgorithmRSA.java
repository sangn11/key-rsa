package rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class AlgorithmRSA {
	private BigInteger e, n, d; // e,n: public key, d: private key

	public synchronized BigInteger getE() {
		return e;
	}

	public synchronized void setE(BigInteger e) {
		this.e = e;
	}

	public synchronized BigInteger getN() {
		return n;
	}

	public synchronized void setN(BigInteger n) {
		this.n = n;
	}

	public synchronized BigInteger getD() {
		return d;
	}

	public synchronized void setD(BigInteger d) {
		this.d = d;
	}
	
	public AlgorithmRSA() {

	}

	public AlgorithmRSA(BigInteger e, BigInteger n) {
		this.e = e;
		this.n = n;
	}
	
	public synchronized void keyRSA(int lenghtBits) {
		SecureRandom r = new SecureRandom();
		BigInteger p = new BigInteger(lenghtBits, 100, r);
		BigInteger q = new BigInteger(lenghtBits, 100, r);
		n = p.multiply(q);
		BigInteger phiN = (p.subtract(BigInteger.ONE))
					.multiply(q.subtract(BigInteger.ONE));
		boolean found = false;
		do {
			e = new BigInteger(lenghtBits, 100, r);
			if(phiN.gcd(e).equals(BigInteger.ONE) && e.compareTo(phiN) < 0) {
                found = true;
            }
		} while (!found);
		d = e.modInverse(phiN);
	}
	// Tin nhắn sẽ được chuyển thành bytes, nếu giá trị của bytes lớn hơn n (n = tích của p và q) thì sẽ báo lỗi
	public synchronized String encrypt(String message) throws Exception {
		BigInteger messageBigInt = new BigInteger(message.getBytes());
		if (messageBigInt.compareTo(n) >= 0) {
			throw new Exception("Kích thước tin nhắn quá lớn! mã hóa không thể thực hiện!");
		}
		return new BigInteger(message.getBytes()).modPow(e, n).toString();
	}
	
	public synchronized String decrypt(String message) {
		return new String(new BigInteger(message).modPow(d, n).toByteArray());
	}
	
	public synchronized BigInteger encrypt(BigInteger message) {
		return message.modPow(e, n);
	}
	
	public synchronized BigInteger decrypt(BigInteger message) {
		return message.modPow(d, n);
	}
}
