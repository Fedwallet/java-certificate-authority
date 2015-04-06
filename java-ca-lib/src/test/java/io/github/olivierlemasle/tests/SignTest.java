package io.github.olivierlemasle.tests;

import static io.github.olivierlemasle.ca.CA.createCertificateAuthority;
import static io.github.olivierlemasle.ca.CA.dn;
import static io.github.olivierlemasle.ca.CA.newCsr;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import io.github.olivierlemasle.ca.CSR;
import io.github.olivierlemasle.ca.CertificateAuthority;
import io.github.olivierlemasle.ca.DistinguishedName;

import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;

import org.junit.Test;

public class SignTest {

  @Test
  public void sign() {
    final DistinguishedName caName = dn("CN=CA-Test");
    final CertificateAuthority ca = createCertificateAuthority(caName).build();
    final CSR csr = newCsr().generateRequest(dn("CN=test"));
    final X509Certificate cert = ca.sign(csr);

    try {
      cert.checkValidity();
    } catch (CertificateExpiredException | CertificateNotYetValidException e) {
      fail("Invalid certificate: " + e.toString());
    }
    assertEquals("CN=CA-Test", cert.getIssuerX500Principal().getName());
    assertEquals("CN=test", cert.getSubjectX500Principal().getName());
    assertEquals(csr.getPublicKey(), cert.getPublicKey());
  }

}
