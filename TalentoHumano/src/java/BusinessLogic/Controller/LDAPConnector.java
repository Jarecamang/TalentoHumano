/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author ArqSoft
 */
public class LDAPConnector {

    LDAPConnection lcPublic = new LDAPConnection();
    LDAPConnection lcAdmin = new LDAPConnection();
    String ldapHost = "127.0.0.1";
    String dn = "cn=admin,dc=arqsoft,dc=com";
    String password = "1234";
    int ldapPort = LDAPConnection.DEFAULT_PORT;
    int ldapVersion = LDAPConnection.LDAP_V3;

    char base64Table[] = new char[64];

    private void startBase64Table() {
        int j = 0;
        for (int i = 0; i < 26; i++) {
            base64Table[j++] = (char) ('A' + i);
        }
        for (int i = 0; i < 26; i++) {
            base64Table[j++] = (char) ('a' + i);
        }
        for (int i = 0; i < 10; i++) {
            base64Table[j++] = (char) ('0' + i);
        }
        base64Table[j++] = '+';
        base64Table[j++] = '/';
    }

    public Boolean conectar() {
        if (lcPublic.isConnected()) {
            return true;
        } else {
            try {
                lcPublic.connect(ldapHost, ldapPort);
                System.out.println("====Conectado al Servidor LDAP====");
                lcPublic.bind(ldapVersion, dn, password.getBytes("UTF8"));
                System.out.println("====Autenticado en el servidor====");
                return true;
            } catch (Exception ex) {
                System.out.println("====ERROR al conectarse al Servidor LDAP====");
                ex.printStackTrace();
                return false;
            }
        }
    }

    public Boolean validarContrasena(String nombreUsuario, String contrasena) {
        if (!lcPublic.isConnected()) {
            if (!conectar()) {
                return false;
            }
        }
        String dn = "cn=" + nombreUsuario + ",ou=TalentoHumano,dc=arqsoft,dc=com";
        try {
            lcPublic.bind(dn, contrasena);
            System.out.println("====Contrasena Validada====");
            return true;
        } catch (Exception ex) {
            System.out.println("====ERROR al validar la contrasena====");
            return false;
        }
    }

    public String searchRole(String user) throws LDAPException {
        if (!lcPublic.isConnected()) {
            if (!conectar()) {
                return "";
            }
        }
        String role = "User";
        LDAPSearchResults sr = lcPublic.search("ou=TalentoHumano,dc=arqsoft,dc=com", LDAPConnection.SCOPE_ONE, "(cn=" + user + ")", new String[]{"gidNumber"}, false);
        while (sr.hasMore()) {
            LDAPEntry nextEntry = null;
            nextEntry = sr.next();
            if (nextEntry.getAttribute("gidNumber").getStringValue().equals("600")) {
                role = "Administrator";
            }
        }
        desconectar();
        return role;
    }

    public void desconectar() throws LDAPException {
        if (lcPublic.isConnected()) {
            lcPublic.disconnect();
        }
    }

    public boolean registrar(String id, String name, String lastName, String password, String role) throws LDAPException {
        try {

            if (conectar()) {
                LDAPAttribute attribute = null;
                LDAPAttributeSet attributeSet = new LDAPAttributeSet();
                attributeSet.add(new LDAPAttribute("objectclass", new String[]{"inetOrgPerson", "posixAccount", "top"}));
                attributeSet.add(new LDAPAttribute("cn", name+" "+lastName));
                attributeSet.add(new LDAPAttribute("givenname", name));
                attributeSet.add(new LDAPAttribute("uidNumber", id));
                attributeSet.add(new LDAPAttribute("uid", name+" "+lastName));
                attributeSet.add(new LDAPAttribute("gidNumber", role));
                attributeSet.add(new LDAPAttribute("homeDirectory", "/home/users/"+name.toLowerCase().charAt(0)+lastName.toLowerCase()));
                attributeSet.add(new LDAPAttribute("sn", lastName));
                attributeSet.add(new LDAPAttribute("userPassword", "{MD5}" + hexToBase64(MD5(password))));
                String dn = "cn=" + name+" "+lastName + ",ou=TalentoHumano,dc=arqsoft,dc=com";
                LDAPEntry newEntry = new LDAPEntry(dn, attributeSet);
                lcPublic.add( newEntry );
                System.out.println( "\nAdded object: " + dn + " successfully." );
                desconectar();
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            desconectar();
            ex.printStackTrace();
            return false;
        }
    }

    private String MD5(String s) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(), 0, s.length());
        return new BigInteger(1, m.digest()).toString(16);
    }

    private String hexToBase64(String s) {
        startBase64Table();
        s = s.toUpperCase();
        String bitS = "";
        int aux;
        String auxS;
        for (int i = 0; i < s.length(); i++) {
            aux = Integer.parseInt(s.substring(i, i + 1), 16);
            auxS = Integer.toBinaryString(aux);
            while (auxS.length() < 4) {
                auxS = "0" + auxS;
            }
            bitS += auxS;
        }
        int sigE = 0;
        while (bitS.length() % 6 != 0) {
            bitS += "00";
            sigE++;
        }
        String encoded = "";
        for (int i = 0; i < bitS.length(); i += 6) {
            encoded += base64Table[Integer.parseInt(bitS.substring(i, i + 6), 2)];
        }
        for (int i = 0; i < sigE; i++) {
            encoded += "=";
        }
        return encoded;
    }
}
