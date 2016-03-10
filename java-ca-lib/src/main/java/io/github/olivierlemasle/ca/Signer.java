package io.github.olivierlemasle.ca;

import io.github.olivierlemasle.ca.ext.CertExtension;

import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.time.ZonedDateTime;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

public interface Signer {
  public SignerWithSerial setSerialNumber(final BigInteger serialNumber);

  public SignerWithSerial setRandomSerialNumber();

  public static interface SignerWithSerial {
    public X509Certificate sign();

    public SignerWithSerial setNotBefore(final ZonedDateTime notBefore);

    public SignerWithSerial setNotAfter(final ZonedDateTime notAfter);

    public SignerWithSerial validDuringYears(final int years);

    public SignerWithSerial addExtension(final CertExtension extension);

    public SignerWithSerial addExtension(ASN1ObjectIdentifier oid, boolean isCritical,
        ASN1Encodable value);
  }
}
